package com.sd.laborator.model

class Book(private var data: Content) {

    var name: String?
        get() {
            return data.name
        }
        set(value) {
            data.name = value
        }

    var author: String?
        get() {
            return data.author
        }
        set(value) {
            data.author = value
        }

    var publisher: String?
        get() {
            return data.publisher
        }
        set(value) {
            data.publisher = value
        }

    var content: String?
        get() {
            return data.text
        }
        set(value) {
            data.text = value
        }

    fun hasAuthor(author: String): Boolean {
        val percentage = author.length.toDouble() / data.author!!.length
        if (percentage < (1 / 3.toDouble()))
            return false;

        return data.author?.toLowerCase()?.contains(author.toLowerCase()) ?: false;
    }

    fun hasTitle(title: String): Boolean {
        return data.name?.toLowerCase()?.contains(title.toLowerCase()) ?: false;
    }

    fun publishedBy(publisher: String): Boolean {
        return data.publisher?.toLowerCase()?.contains(publisher.toLowerCase()) ?: false;
    }

}