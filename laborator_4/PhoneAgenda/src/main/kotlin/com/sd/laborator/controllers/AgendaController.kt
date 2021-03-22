package com.sd.laborator.controllers

import com.sd.laborator.interfaces.AgendaService
import com.sd.laborator.pojo.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.ui.Model

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView


@Controller
class AgendaController {
    @Autowired
    private lateinit var agendaService: AgendaService

    @RequestMapping(value = ["/person"], method = [RequestMethod.GET])
        fun personForm(model: Model): String {
            model.addAttribute("person", Person())
        return "person"
    }

    @RequestMapping(value = ["/person"], method = [RequestMethod.POST])
    fun createPerson(@ModelAttribute person: Person, model: Model): String {
        agendaService.createPerson(person)
        return "result"
    }

    @RequestMapping(value = ["/person/{id}"], method = [RequestMethod.GET])
    fun getPerson(@PathVariable id: Int): ResponseEntity<Person?> {
        val person: Person? = agendaService.getPerson(id)
        val status = if (person == null) {
            HttpStatus.NOT_FOUND
        } else {
            HttpStatus.OK
        }
        return ResponseEntity(person, status)
    }


}