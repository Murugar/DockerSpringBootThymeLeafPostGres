package com.iqmsoft.docker.thymeleaf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iqmsoft.docker.thymeleaf.domain.Book;
import com.iqmsoft.docker.thymeleaf.service.BookRepo;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@RequestMapping(path = "/book")
public class BookController {

  @Autowired
  private BookRepo bookRepo;

  @RequestMapping(method = GET)
  public List<Book> getEvents() {
    return bookRepo.findAll();
  }

  /**
   * Get the unique list of Book titles.
   * @return the list
   */
  @RequestMapping(method = GET, path = "/titles")
  public List<String> getEventNames() {
    return bookRepo.findAll().stream()
            .map(Book::getTitle).distinct()
            .collect(Collectors.toList());
  }

  @RequestMapping(method = POST)
  public Book create(@RequestBody final Book book) {
    return bookRepo.save(book);
  }

  @RequestMapping(method = GET, path = "/{id}")
  public Book find(@PathVariable final Long id) {
    return bookRepo.findOne(id);
  }
}
