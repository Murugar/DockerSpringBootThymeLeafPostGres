package com.iqmsoft.docker.thymeleaf.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iqmsoft.docker.thymeleaf.domain.Person;


@Repository
@Transactional
public interface PersonRepo extends JpaRepository<Person, Long> {

}
