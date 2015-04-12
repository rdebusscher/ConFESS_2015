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

import be.c4j.demo.model.Gender;
import be.c4j.demo.model.Person;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Startup
@Singleton
@Lock(LockType.READ)
public class InMemoryDatabase {

    private List<Person> persons;

    @PostConstruct
    public void init() {
        persons = new ArrayList<>();
        persons.add(new Person("Lucas", Gender.MALE, 34));
        persons.add(new Person("Jules", Gender.MALE, 67));
        persons.add(new Person("Milan", Gender.MALE, 12));
        persons.add(new Person("Marie", Gender.FEMALE, 54));
        persons.add(new Person("Emma", Gender.FEMALE, 17));
        persons.add(new Person("Louise", Gender.FEMALE, 45));
    }

    public List<Person> getPersons() {
        return persons.stream().map(Person::clonePerson).collect(Collectors.toList());
    }

    public void addPerson(Person person) {
        persons.add(person);
    }
}
