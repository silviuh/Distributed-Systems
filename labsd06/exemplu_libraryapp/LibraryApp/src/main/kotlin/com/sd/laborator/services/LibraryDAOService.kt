package com.sd.laborator.services

import com.sd.laborator.interfaces.LibraryDAO
import com.sd.laborator.model.Book
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Service
import java.sql.ResultSet
import java.sql.SQLException
import java.util.regex.Pattern


class BookRowMapper : RowMapper<Book> {
    @Throws(SQLException::class)
    override fun mapRow(rs: ResultSet, rowNum: Int): Book {
        return Book(
            rs.getInt("id"), rs.getString("title"),
            rs.getString("author"), rs.getString("publisher"), rs.getString("text")
        )
    }
}


@Service
class LibraryDAOService : LibraryDAO {
    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate
    var pattern: Pattern = Pattern.compile("\\W")

    private var books: MutableSet<Book> = mutableSetOf(
        Book(

            1,
            "ayy MACARENA",
            "Roberto Ierusalimschy",
            "Teora",
            "Preface. When Waldemar, Luiz, and I started the development of Lua, back in 1993, we could hardly imagine that it would spread as it did. ..."
        ),
        Book(

            2,
            "Progamming in LUA",
            "Roberto Ierusalimschy",
            "Teora",
            "Preface. When Waldemar, Luiz, and I started the development of Lua, back in 1993, we could hardly imagine that it would spread as it did. ..."
        ),
        Book(
            3,
            "HEHEHE",
            "Jules Verne",
            "Albatros",
            "a fost odata ca niciodata"
        ),
        Book(
            4,
            "MUCI",
            "Jules Verne",
            "Albatros",
            "lfnwrfnwlfqwnwrrwnla"
        ),
        Book(
            5,
            "NANANANA",
            "Jules Verne",
            "Albatros",
            "fmlfmamkmkal;f"
        )
    )


    override fun findAllByAuthor(author: String): Set<Book> {
        val result: MutableList<Book> = jdbcTemplate.query(
            "SELECT * FROM Book where author = '$author'", BookRowMapper()
        )
        return result.toSet()
    }

    override fun findAllByTitle(title: String): Set<Book> {
        return (this.books.filter { it.hasTitle(title) }).toSet()
    }

    override fun findAllByPublisher(publisher: String): Set<Book> {
        return (this.books.filter { it.publishedBy(publisher) }).toSet()
    }


    override fun createBooks() {
        jdbcTemplate.execute(
            """CREATE TABLE if not exists Book(
                    id        INTEGER PRIMARY KEY AUTOINCREMENT,
                    author    VARCHAR NOT NULL,
                    title     VARCHAR NOT NULL,
                    publisher VARCHAR NOT NULL,
                    text TEXT NOT NULL
                    )
                """
        )

        for (book in books) {
            addBook(book)
        }
    }

    override fun addBook(book: Book) {
        /*
        if (pattern.matcher(book.name).find()) {
            println("SQL Injection for book name")
            return
        }

         */

        jdbcTemplate.update(
            "INSERT INTO Book(author, title, publisher, text) VALUES (?, ?, ?, ?)",
            book.Author,
            book.name,
            book.Publisher,
            book.Content
        )
    }

    override fun getBooks(): Set<Book> {
        val result: MutableList<Book> = jdbcTemplate.query(
            "SELECT * FROM Book", BookRowMapper()
        )
        return result.toSet()
    }

    override fun deleteBook(name: String) {
        /*
        if (pattern.matcher(name).find()) {
            println("SQL Injection for Book name")
            return
        }

         */
        jdbcTemplate.update("DELETE FROM Book WHERE name = ?", name)
    }

    override fun updateBook(book: Book) {
        /*
        if (pattern.matcher(book.name).find()) {
            println("SQL Injection for book name")
            return
        }

         */

        jdbcTemplate.update(
            "UPDATE Book SET author = ?, title = ?, publisher =  ?, text = ? WHERE id = ?",
            book.name,
            book.Author,
            book.Publisher,
            book.Content,
            book.bookID
        )
    }
}


