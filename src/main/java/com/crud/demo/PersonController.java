package com.crud.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PersonController {

    private final PersonService service;

    @Autowired
    public PersonController(PersonService service){
        this.service = service;
    }

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<Person> listPersons = service.listAll();
        model.addAttribute("listPersons", listPersons);

        return "index";
    }

    @RequestMapping("/addPerson")
    public String ShowNewPersonForm(Model model)  {

        Person person = new Person();
        model.addAttribute("person", person);

        return "new";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePerson(@ModelAttribute("person") Person person)   {

        service.add(person);

        return "redirect:/";
    }

    @RequestMapping("/edit/{ID}")
    public ModelAndView showEditPersonFom(@PathVariable(name = "ID") Long ID)  {

        ModelAndView mav = new ModelAndView("edit_person");
        Person person = service.getPerson(ID);
        mav.addObject("person", person);

        return mav;
    }

    @RequestMapping("/delete/{ID}")
    public String deleteProduct(@PathVariable(name = "ID") Long ID) {

        service.delete(ID);

        return  "redirect:/";
    }
}
