package com.sd.laborator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.sql.SQLException

import java.sql.DatabaseMetaData

import java.sql.DriverManager


@SpringBootApplication
open class LibraryApp

fun main(args: Array<String>) {
    runApplication<LibraryApp>(*args)
}



