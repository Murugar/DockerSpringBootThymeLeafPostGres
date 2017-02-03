package com.iqmsoft.docker.thymeleaf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iqmsoft.docker.thymeleaf.domain.Book;
import com.iqmsoft.docker.thymeleaf.domain.BookLoan;
import com.iqmsoft.docker.thymeleaf.service.BookLoanRepo;
import com.iqmsoft.docker.thymeleaf.service.BookRepo;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@RequestMapping(path = "/loan")
public class BookLoanController {

  @Autowired
  private BookLoanRepo loanRepo;
  @Autowired
  private BookRepo bookRepo;

  @RequestMapping(method = GET)
  public List<BookLoan> getEvents() {
    return loanRepo.findAll();
  }

  @RequestMapping(method = POST)
  public BookLoan create(@RequestBody final BookLoan bookLoan) {
    return loanRepo.save(bookLoan);
  }

  @RequestMapping(method = GET, path = "/{id}")
  public BookLoan find(@PathVariable final Long id) {
    return loanRepo.findOne(id);
  }

  /**
   * Get the list of BookLoan's for a person.
   * @param id the person id
   * @return the list of BookLoan instances
   */
  @RequestMapping(method = GET, path = "/person/{id}")
  public List<BookLoan> findForPerson(@PathVariable final Long id) {
    final BookLoan probe = new BookLoan();
    probe.setCreated(null);
    probe.setPersonId(id);
    final Example<BookLoan> example = Example.of(probe);
    return loanRepo.findAll(example);
  }

  /**
   * Get the list of Books that have been loaned by a Person.
   * @param id the person id
   * @return the list of Book instances
   */
  @RequestMapping(method = GET, path = "/person/{id}/books")
  public List<Book> findBooksForPerson(@PathVariable final Long id) {
    final List<BookLoan> loans = findForPerson(id);
    final List<Long> bookIds = loans.stream()
            .map(BookLoan::getBookId)
            .collect(Collectors.toList());
    return bookRepo.findAll(bookIds);
  }
}
