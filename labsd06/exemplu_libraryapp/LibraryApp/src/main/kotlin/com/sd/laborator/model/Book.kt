package com.sd.laborator.model

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.sql.SQLException


class Book(
    private var id: Int,
    private var title: String?,
    private var author: String?,
    private var publisher: String?,
    private var content: String?
) {

    var bookID: Int
        get() {
            return id
        }
        set(value) {
            id = value
        }

    var name: String?
        get() {
            return title
        }
        set(value) {
            name = value
        }

    var Author: String?
        get() {
            return author
        }
        set(value) {
            author = value
        }

    var Publisher: String?
        get() {
            return publisher
        }
        set(value) {
            publisher = value
        }

    var Content: String?
        get() {
            return content
        }
        set(value) {
            content = value
        }


    fun hasAuthor(author: String): Boolean {
        return author == author
    }

    fun hasTitle(title: String): Boolean {
        return name.equals(title)
    }

    fun publishedBy(publisher: String): Boolean {
        return publisher == publisher
    }

}