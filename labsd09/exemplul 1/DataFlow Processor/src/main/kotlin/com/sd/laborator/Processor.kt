package com.sd.laborator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Processor
import org.springframework.integration.annotation.Transformer
import java.io.File
import java.io.IOException
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit
import java.io.BufferedReader

import java.io.InputStreamReader


@EnableBinding(Processor::class)
@SpringBootApplication
class SpringDataFlowTimeProcessorApplication {
    @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    fun execute(comanda: String): String? {
        // cat fisier.txt | grep "word" | wc -l
        val rez = comanda.split("+")
        var tokens = mutableListOf<String>()
        val file = File("/Users/silviuh1/Downloads/labsd09/rezultat.txt")
        val rt = Runtime.getRuntime()

        if (rez.size == 1) {
            tokens = rez[0].split("|", limit = 2).toMutableList()
        } else {
            tokens = rez[1].split("|", limit = 2).toMutableList()
            file.writeText(rez[0], Charsets.UTF_8) // the result of the previous unix process.
            tokens[0] += " < /Users/silviuh1/Downloads/labsd09/rezultat.txt" // the file it's used as a input for commands like grep.
        }

        val unixProcess = rt.exec(tokens[0])
        val processResultLog = BufferedReader(InputStreamReader(unixProcess.inputStream))

        return if (tokens.size == 1) {
            val output =
                processResultLog.readLines().joinToString()
                    .removePrefix("/Users/silviuh1/Downloads/labsd09/rezultat.txt:")
            output
        } else {
            val output = processResultLog.readLines().joinToString()
                .removePrefix("/Users/silviuh1/Downloads/labsd09/rezultat.txt:") + " + " + tokens[1]
            output
        }
    }
}


fun main(args: Array<String>) {
    runApplication<SpringDataFlowTimeProcessorApplication>(*args)
}