package com.sd.laborator.services

import com.sd.laborator.interfaces.LibraryPrinter
import com.sd.laborator.model.Book
import org.springframework.stereotype.Service

@Service
class LibraryPrinterService: LibraryPrinter {
    override fun printHTML(books: Set<Book>): String {
        var Content: String = "<html><head><title>Libraria mea HTML</title></head><body>"
        books.forEach {
            Content += "<p><h3>${it.name}</h3><h4>${it.Author}</h4><h5>${it.Publisher}</h5>${it.Content}</p><br/>"
        }
        Content += "</body></html>"
        return Content
    }

    override fun printJSON(books: Set<Book>): String {
        var Content: String = "[\n"
        books.forEach {
            if (it != books.last())
                Content += "    {\"Titlu\": \"${it.name}\", \"Autor\":\"${it.Author}\", \"Editura\":\"${it.Publisher}\", \"Text\":\"${it.Content}\"},\n"
            else
                Content += "    {\"Titlu\": \"${it.name}\", \"Autor\":\"${it.Author}\", \"Editura\":\"${it.Publisher}\", \"Text\":\"${it.Content}\"}\n"
        }
        Content += "]\n"
        return Content
    }

    override fun printRaw(books: Set<Book>): String {
        var Content: String = ""
        books.forEach {
            Content += "${it.name}\n${it.Author}\n${it.Publisher}\n${it.Content}\n\n"
        }
        return Content
    }
}