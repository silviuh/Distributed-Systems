package com.sd.laborator.interfaces

import com.sd.laborator.model.Book

interface XMLPrinter {
    fun printXML(books: Set<Book>): String
}