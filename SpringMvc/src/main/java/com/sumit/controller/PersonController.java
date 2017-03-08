package com.sumit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sumit.entity.Person;
import com.sumit.service.interfce.IPersonService;

@Controller
public class PersonController {

	@Autowired
	private IPersonService personService;
	
	@RequestMapping("/findPersonByName")
	@ResponseBody
	public List<Person> findPersonByName(@RequestParam("name") String name){
		List<Person> persons = personService.findPersonByName(name);
		return persons;
	}
	
	@RequestMapping("/deletePersonById")
	@ResponseBody
	public String deletePersonById(@RequestParam("id") Integer id){
		personService.deletePersonById(id);
		return "deleted";
	}
	
	@RequestMapping("/updatePersonNameById")
	@ResponseBody
	public String updatePersonNameById(@RequestParam("id") Integer id,@RequestParam("name") String name){
		personService.updatePersonNameById(id,name);
		return "updated";
	}
}
