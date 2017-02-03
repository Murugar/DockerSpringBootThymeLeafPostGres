package com.iqmsoft.docker.thymeleaf.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.iqmsoft.docker.thymeleaf.domain.Book;
import com.iqmsoft.docker.thymeleaf.domain.Person;


public final class DataProvider {

  public static final int SEED = 100000000;

  /**
   * Private constructor.
   */
  private DataProvider() {}

  /**
   * Get a new mock instance of a Book.
   * @return the book
   */
  public static Book getBook() {
    final Book book = new Book();
    book.setAuthor(NameProvider.getName());
    book.setTitle(NameProvider.getBookTitle());
    book.setIsbn(String.valueOf(new Random().nextInt(SEED)));
    return book;
  }

  /**
   * Get a mock list of Books.
   * @param totalBooks the total number of books to return
   * @return the Book list
   */
  public static List<Book> getBookList(final int totalBooks) {
    final List<Book> bookList = new ArrayList<>();
    for (int i = 0; i < totalBooks; i++) {
      bookList.add(getBook());
    }
    return bookList;
  }

  /**
   * Get a new mock instance of Person.
   * @return the person
   */
  public static Person getPerson() {
    final Person person = new Person();
    person.setName(NameProvider.getName());
    person.setEmail(person.getName().replace(" ",".") + "@gmail.com");
    person.setPhone(String.valueOf(new Random().nextInt(SEED)));
    return person;
  }
}
