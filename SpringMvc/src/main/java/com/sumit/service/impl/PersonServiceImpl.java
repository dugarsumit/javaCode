package com.sumit.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumit.entity.Person;
import com.sumit.service.interfce.IPersonDao;
import com.sumit.service.interfce.IPersonService;

@Service("personService")
@Transactional
public class PersonServiceImpl implements IPersonService{

	@Autowired
	private IPersonDao personDao;
	
	public void savePerson(Person person) {
		personDao.savePerson(person);
	}

	public List<Person> findPersonByName(String name) {
		return personDao.findPersonByName(name);
	}

	public List<Person> findPersonByCountry(String country) {
		return personDao.findPersonByCountry(country);
	}

	public Person findPersonById(Integer id) {
		return personDao.findPersonById(id);
	}

	public void deletePersonById(Integer id) {
		personDao.deletePersonById(id);
	}

	public void updatePersonNameById(Integer id, String name) {
		personDao.updatePersonNameById(id, name);
	}

}
