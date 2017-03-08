package com.sumit.service.interfce;

import java.util.List;

import com.sumit.entity.Person;

public interface IPersonService {
	void savePerson(Person person);

	List<Person> findPersonByName(String name);

	List<Person> findPersonByCountry(String country);

	Person findPersonById(Integer id);

	void deletePersonById(Integer id);

	void updatePersonNameById(Integer id, String name);
}
