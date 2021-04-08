package com.sd.laborator.microservices

import com.sd.laborator.interfaces.LibraryDAO
import com.sd.laborator.interfaces.LibraryPrinter
import com.sd.laborator.services.LibraryPrinterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.GetMapping


@Controller
class LibraryPrinterMicroservice {
    @Autowired
    private lateinit var libraryDAO: LibraryDAO

    @Autowired
    private lateinit var libraryPrinter: LibraryPrinter


    @GetMapping("/find-and-print")
    @ResponseBody
    fun customFindAndPrint(
        @RequestParam(required = false, name = "author", defaultValue = "Jules Verne") author: String,
        @RequestParam(required = false, name = "format", defaultValue = "html") format: String
    ): String {

        libraryDAO.createBooks()

        return when (format) {
            "html" -> libraryPrinter.printHTML(libraryDAO.findAllByAuthor(author))
            "json" -> libraryPrinter.printJSON(libraryDAO.findAllByAuthor(author))
            "raw" -> libraryPrinter.printRaw(libraryDAO.findAllByAuthor(author))
            else -> "Not implemented"
        }
    }

    @RequestMapping("/print", method = [RequestMethod.GET])
    @ResponseBody
    fun customPrint(@RequestParam(required = true, name = "format", defaultValue = "") format: String): String {
        return when (format) {
            "html" -> libraryPrinter.printHTML(libraryDAO.getBooks())
            "json" -> libraryPrinter.printJSON(libraryDAO.getBooks())
            "raw" -> libraryPrinter.printRaw(libraryDAO.getBooks())
            else -> "Not implemented"
        }
    }

    @RequestMapping("/find", method = [RequestMethod.GET])
    @ResponseBody
    fun customFind(
        @RequestParam(required = false, name = "author", defaultValue = "") author: String,
        @RequestParam(required = false, name = "title", defaultValue = "") title: String,
        @RequestParam(required = false, name = "publisher", defaultValue = "") publisher: String
    ): String {
        if (author != "")
            return this.libraryPrinter.printJSON(this.libraryDAO.findAllByAuthor(author))
        if (title != "")
            return this.libraryPrinter.printJSON(this.libraryDAO.findAllByTitle(title))
        if (publisher != "")
            return this.libraryPrinter.printJSON(this.libraryDAO.findAllByPublisher(publisher))
        return "Not a valid field"
    }

}