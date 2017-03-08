package com.sumit.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sumit.entity.Person;
import com.sumit.service.interfce.IPersonDao;

@Repository("personDao")
public class PersonDaoImpl implements IPersonDao{

	
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void savePerson(Person person) {
		sessionFactory.getCurrentSession().save(person);
	}

	public List<Person> findPersonByName(String name) {
		Query query = sessionFactory.getCurrentSession().createSQLQuery("select * from person where name=:name");
		query.setParameter("name", name);
		return (ArrayList<Person>)query.list();
	}

	public List<Person> findPersonByCountry(String country) {
		// TODO Auto-generated method stub
		return null;
	}

	public Person findPersonById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deletePersonById(Integer id) {
		Query query = sessionFactory.getCurrentSession().createQuery("delete from Person where id=:id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

	public void updatePersonNameById(Integer id,String name) {
		Query query = sessionFactory.getCurrentSession().createSQLQuery("update person set name=:name where id=:id");
		query.setParameter("name", name);
		query.setParameter("id", id);
		query.executeUpdate();
	}
}
