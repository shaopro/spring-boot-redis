package com.github.springbootredis.controller;

import com.github.springbootredis.dao.PersonDao;
import com.github.springbootredis.pojo.Person;
import com.github.springbootredis.repository.PersonKeyValueRepository;
import org.jetbrains.annotations.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created in 16:04 2019-04-01
 * Project name spring-boot-redis
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */


@RestController
public class PersonController {

    @Resource
    private PersonDao dao;

    @Resource
    private PersonKeyValueRepository repository;

    @GetMapping(value = "person", produces = MediaType.APPLICATION_JSON_VALUE)
    @Contract(pure = true)
    public Page<Person> getPersons(@PageableDefault(size = 4, page = 0, sort = "name", direction = Sort.Direction.DESC) Pageable pageable) {
        return repository.findAll(pageable);
    }

    @DeleteMapping(value = "clear")
    public ResponseEntity<Void> clear() {
        dao.clearAll();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
