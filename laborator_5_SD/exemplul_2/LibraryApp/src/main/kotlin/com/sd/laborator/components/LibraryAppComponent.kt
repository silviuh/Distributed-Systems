package com.sd.laborator.components

import com.sd.laborator.interfaces.LibraryDAO
import com.sd.laborator.interfaces.LibraryPrinter
import com.sd.laborator.model.Book
import com.sd.laborator.model.Content
import com.sd.laborator.services.LibraryPrinterAdvancedService
import com.sd.laborator.services.LibraryPrinterService
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.lang.Exception
import kotlin.system.exitProcess

@Component
class LibraryAppComponent {
    @Autowired
    private lateinit var libraryDAO: LibraryDAO

    @Autowired
    private lateinit var libraryPrinter: LibraryPrinter

    @Autowired
    private lateinit var connectionFactory: RabbitMqConnectionFactoryComponent
    private lateinit var amqpTemplate: AmqpTemplate

    @Autowired
    fun initTemplate() {
        this.amqpTemplate = connectionFactory.rabbitTemplate()
    }

    fun sendMessage(msg: String) {
        this.amqpTemplate.convertAndSend(
            connectionFactory.getExchange(),
            connectionFactory.getRoutingKey(),
            msg
        )
    }

    @RabbitListener(queues = ["\${libraryapp.rabbitmq.queue}"])
    fun recieveMessage(msg: String) {
        // the result needs processing
        val processedMsg = (msg.split(",").map { it.toInt().toChar() }).joinToString(separator = "")
        print("1$processedMsg")

        try {

            if (processedMsg.startsWith("add")) {
                val commandParameters = processedMsg.split(":")
                if (commandParameters[0] == "add") {
                    addBook(
                        Book
                            (
                            Content(
                                commandParameters[2].substringAfter("="),
                                commandParameters[4].substringAfter("="),
                                commandParameters[1].substringAfter("="),
                                commandParameters[3].substringAfter("=")
                            )
                        )
                    )
                    sendMessage("Book was added in the library.")
                }
            } else {
                val (commandOne, commandTwo) = processedMsg.split(" ")
                val (printMsg, printFunc) = commandOne.split(":")
                val (findCommand, findPair) = commandTwo.split(":")
                val (findType, findValue) = commandTwo.split("=")

                val result = customService(printFunc, findType.substringAfter("find:"), findValue)
                sendMessage(result)

                /*
                val (arg1, arg2) = processedMsg.split(" ")
                print("2$arg1 $arg2")

                val (function1, parameter1) = arg1.split(":")
                print("3$function1 $parameter1")

                val (function2, parameter2) = arg2.split(":")
                print("4$function2 $parameter2")

                val result1: String? = when (function1) {
                    "print" -> customPrint(parameter1)
                    "find" -> customFind(parameter1)
                    else -> null
                }

                val result2: String? = when (function2) {
                    "print" -> customPrint(parameter2)
                    "find" -> customFind(parameter2)
                    else -> null
                }

                if (result1 != null) sendMessage(result1)
                if (result2 != null) sendMessage(result2)

                 */
            }
        } catch (e: Exception) {
            println(e)
        }
    }

    fun customService(printFunction: String, findFunction: String, searchParameter: String): String {
        if (searchParameter == "") {
            return customPrint(printFunction)
        } else {
            if (printFunction == "html") {
                return when (findFunction) {
                    "author" -> this.libraryPrinter.printHTML(this.libraryDAO.findAllByAuthor(searchParameter))
                    "title" -> this.libraryPrinter.printHTML(this.libraryDAO.findAllByTitle(searchParameter))
                    "publisher" -> this.libraryPrinter.printHTML(this.libraryDAO.findAllByPublisher(searchParameter))
                    else -> "Not a valid field"
                }
            } else if (printFunction == "json") {
                return when (findFunction) {
                    "author" -> this.libraryPrinter.printHTML(this.libraryDAO.findAllByAuthor(searchParameter))
                    "title" -> this.libraryPrinter.printHTML(this.libraryDAO.findAllByTitle(searchParameter))
                    "publisher" -> this.libraryPrinter.printHTML(this.libraryDAO.findAllByPublisher(searchParameter))
                    else -> "Not a valid field"
                }
            } else if (printFunction == "xml") {
                return when (findFunction) {
                    "author" -> this.libraryPrinter.printHTML(this.libraryDAO.findAllByAuthor(searchParameter))
                    "title" -> this.libraryPrinter.printHTML(this.libraryDAO.findAllByTitle(searchParameter))
                    "publisher" -> this.libraryPrinter.printHTML(this.libraryDAO.findAllByPublisher(searchParameter))
                    else -> "Not a valid field"
                }
            } else if (printFunction == "raw") {
                return when (findFunction) {
                    "author" -> this.libraryPrinter.printHTML(this.libraryDAO.findAllByAuthor(searchParameter))
                    "title" -> this.libraryPrinter.printHTML(this.libraryDAO.findAllByTitle(searchParameter))
                    "publisher" -> this.libraryPrinter.printHTML(this.libraryDAO.findAllByPublisher(searchParameter))
                    else -> "Not a valid field"
                }
            }
        }
        return "Invalid query"
    }

    fun customPrint(format: String): String {
        return when (format) {
            "html" -> libraryPrinter.printHTML(libraryDAO.getBooks())
            "json" -> libraryPrinter.printJSON(libraryDAO.getBooks())
            "raw" -> libraryPrinter.printRaw(libraryDAO.getBooks())
            "xml" -> libraryPrinter.printXML(libraryDAO.getBooks())
            else -> "Not implemented"
        }
    }

    fun customFind(searchParameter: String): String {
        val (field, value) = searchParameter.split("=")
        return when (field) {
            "author" -> this.libraryPrinter.printJSON(this.libraryDAO.findAllByAuthor(value))
            "title" -> this.libraryPrinter.printJSON(this.libraryDAO.findAllByTitle(value))
            "publisher" -> this.libraryPrinter.printJSON(this.libraryDAO.findAllByPublisher(value))
            else -> "Not a valid field"
        }
    }

    fun addBook(book: Book): Boolean {
        return try {
            this.libraryDAO.addBook(book)
            true
        } catch (e: Exception) {
            false
        }
    }

}