package com.iqmsoft.docker.thymeleaf.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iqmsoft.docker.thymeleaf.domain.BookLoan;

@Repository
@Transactional
public interface BookLoanRepo extends JpaRepository<BookLoan, Long> {

}
