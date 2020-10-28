package com.crud.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository repo;

    public PersonService (PersonRepository repo){

        this.repo = repo;
    }

    public void add(Person person){

        repo.save(person);
    }

    public List<Person> listAll(){

        return repo.findAll();
    }

    public Person getPerson(Long ID){

        return repo.findById(ID).get();
    }

    public void delete(Long ID){

        repo.deleteById(ID);
    }

}
