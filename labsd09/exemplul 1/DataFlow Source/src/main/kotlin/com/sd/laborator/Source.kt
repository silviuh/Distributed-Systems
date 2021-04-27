package com.sd.laborator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Source
import org.springframework.context.annotation.Bean
import org.springframework.integration.annotation.InboundChannelAdapter
import org.springframework.integration.annotation.Poller
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import java.io.File
import java.util.*

@EnableBinding(Source::class)
@SpringBootApplication
class SpringDataFlowTimeSourceApplication {
    @Bean
    @InboundChannelAdapter(value = Source.OUTPUT, poller = [Poller(fixedDelay = "10000", maxMessagesPerPoll = "1")])
    fun timeMessageSource(): () -> Message<String> {
        val fileContent = File("/Users/silviuh1/Downloads/labsd09/exemplul 1/DataFlow Source/pipeline.txt").readLines();
        return { MessageBuilder.withPayload(fileContent[0]).build() }
    }
}

fun main(args: Array<String>) {
    runApplication<SpringDataFlowTimeSourceApplication>(*args)
}