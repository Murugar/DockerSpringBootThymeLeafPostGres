package com.iqmsoft.docker.thymeleaf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iqmsoft.docker.thymeleaf.domain.Person;
import com.iqmsoft.docker.thymeleaf.service.PersonRepo;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/free")
public class FreemarkerController {

  @Autowired
  public PersonRepo personRepo;

  /**
   * Request endpoint for Freemarker page.
   * @param model the request model
   * @return the template to navigate display
   */
  @RequestMapping(method = RequestMethod.GET)
  public String getMembers(final Map<String, Object> model) {
    final List<Person> people = personRepo.findAll();
    model.put("people", people);
    return "index";
  }

  @RequestMapping(value = "/addperson", method = RequestMethod.POST)
  public String addPerson(@ModelAttribute("person") final Person person, final Model model) {
    personRepo.save(person);
    return "redirect:/free";
  }
}
