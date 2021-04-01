package com.sd.laborator.services

import com.sd.laborator.model.Book
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service

@Primary
@Service
class LibraryPrinterAdvancedService : LibraryPrinterService() {
    override fun printHTML(books: Set<Book>): String {
        var content: String = "<html><head><title>Libraria mea HTML</title></head><body>"
        books.forEach {
            content += "<p style=\"color:red;\"><h3 style=\"color:red;\">${it.name}</h3><h4 style=\"color:red;\">${it.author}</h4><h5 style=\"color:red;\">${it.publisher}</h5>${it.content}</p><br/>"
        }
        content += "</body></html>"
        return content
    }

    override fun printJSON(books: Set<Book>): String {
        var content: String = "[\n"
        books.forEach {
            content += if (it != books.last())
                "    {\"Titlu\": \"${it.name}\", \"Autor\":\"${it.author}\", \"Editura\":\"${it.publisher}\", \"Text\":\"${it.content}\"},\n"
            else
                "    {\"Titlu\": \"${it.name}\", \"Autor\":\"${it.author}\", \"Editura\":\"${it.publisher}\", \"Text\":\"${it.content}\"}\n"
        }
        content += "]\n"
        return content
    }

    override fun printRaw(books: Set<Book>): String {
        var content: String = ""
        books.forEach {
            content += "${it.name}\n${it.author}\n${it.publisher}\n${it.content}\n\n"
        }
        return content
    }

    override fun printXML(books: Set<Book>): String {
        var content: String = "<library>\n"
        books.forEach {
            content += " <book>\n" +
                    "       <title>${it.name}</title>\n" +
                    "       <author>${it.author}</author>\n" +
                    "       <publisher>${it.publisher}</publisher>\n" +
                    "       <text>${it.content}</text>\n" +
                    "   </book>\n"
        }
        content += "</library>"
        return content
    }
}