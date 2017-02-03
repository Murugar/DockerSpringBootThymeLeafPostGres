package com.iqmsoft.docker.thymeleaf.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import com.iqmsoft.docker.thymeleaf.domain.Book;
import com.iqmsoft.docker.thymeleaf.domain.BookLoan;
import com.iqmsoft.docker.thymeleaf.domain.Person;
import com.iqmsoft.docker.thymeleaf.service.BookLoanRepo;
import com.iqmsoft.docker.thymeleaf.service.BookRepo;
import com.iqmsoft.docker.thymeleaf.service.PersonRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class DataGenerator implements CommandLineRunner {

  private static final Logger LOG = LoggerFactory.getLogger(DataGenerator.class);
  private static final int BOOKS_PER_PERSON = 3;
  private static final int TOTAL_BOOKS = 20;
  private static final int TOTAL_PEOPLE = 10;

  private final BookLoanRepo bookLoanRepo;
  private final BookRepo bookRepo;
  private final PersonRepo personRepo;

  /**
   * Constructor.
   * @param bookLoanRepo BookLoan repository
   * @param bookRepo Book repository
   * @param personRepo Person repository
   */
  public DataGenerator(final BookLoanRepo bookLoanRepo, final BookRepo bookRepo,
                       final PersonRepo personRepo) {

    this.bookLoanRepo = bookLoanRepo;
    this.bookRepo = bookRepo;
    this.personRepo = personRepo;
  }

  @Override
  public void run(final String... args) throws Exception {
    generateData();
  }

  private void generateData() {
    LOG.info("Generating mock data.");
    generateBooks();
    generatePeople();
    generateLoans();
  }

  private void generateBooks() {
    bookRepo.save(DataProvider.getBookList(TOTAL_BOOKS));
  }

  private void generatePeople() {
    for (int i = 0; i < TOTAL_PEOPLE; i++) {
      generatePerson();
    }
  }

  private void generatePerson() {
    final Person person = DataProvider.getPerson();
    personRepo.save(person);
  }

  private void generateLoans() {
    final List<Book> books = bookRepo.findAll();
    final List<Person> people = personRepo.findAll();

    for (final Person person : people) {
      generateLoans(person, books);
    }
  }

  private void generateLoans(final Person person, final List<Book> books) {
    final Set<Book> loans = getRandomBookList(books, BOOKS_PER_PERSON);
    for (final Book book : loans) {
      createBookLoan(book.getId(), person.getId());
    }
  }

  private Set<Book> getRandomBookList(final List<Book> books, final int totalBooks) {
    final Set<Book> loans = new HashSet<>();
    while (loans.size() < totalBooks) {
      final Book book = books.get(new Random().nextInt(books.size() - 1));
      if (loans.contains(book)) {
        continue;
      }
      loans.add(book);
    }
    return loans;
  }

  private void createBookLoan(final long bookId, final long personId) {
    final BookLoan bookLoan = new BookLoan();
    bookLoan.setPersonId(personId);
    bookLoan.setBookId(bookId);
    bookLoanRepo.save(bookLoan);
  }
}
