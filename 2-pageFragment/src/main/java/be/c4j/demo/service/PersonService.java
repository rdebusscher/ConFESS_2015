/*
 * Copyright 2014-2015 Rudy De Busscher
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package be.c4j.demo.service;

import be.c4j.demo.exception.PersonAlreadyRegisteredException;
import be.c4j.demo.model.Person;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.List;

/**
 *
 */
@Stateless
public class PersonService  {

    @EJB
    private InMemoryDatabase database;

    public List<Person> getAllPersons() {
        return database.getPersons();
    }

    public void savePerson(Person person) {
        List<Person> persons = database.getPersons();
        if (persons.contains(person)) {
            throw new PersonAlreadyRegisteredException();
        }
        database.addPerson(person);
    }


}
