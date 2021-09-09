package com.hansen.mobileplan.ctrlr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hansen.mobileplan.dao.MobilePlanDao;
import com.hansen.mobileplan.model.MobilePlan;
import com.hansen.mobileplan.srvc.MobilePlanSrvc;

@RestController
@RequestMapping("/mp")
public class MobilePlanController {
	
	@Autowired
	MobilePlanSrvc mpSrvc;
	
	@Autowired
	MobilePlanDao mobilePlanDao;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody MobilePlan inputentity) {
	ResponseEntity<Object> mpResponse;

	Object mobilePlan = mpSrvc.create(inputentity);
	String m="Already ID : "+inputentity.getId()+ " Exist";
	if(mobilePlan !=null)
	{
	mpResponse = new ResponseEntity<Object>(mobilePlan, null, HttpStatus.CREATED);
	return mpResponse;
	}
	else
	{
	mpResponse = new ResponseEntity<Object>(m, null, HttpStatus.NOT_ACCEPTABLE);
	return mpResponse;
	}
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> read(@PathVariable(value = "id") Long id) {
		ResponseEntity<Object> mpResponse;
		
		Object mobilePlan = mpSrvc.read(id);
		String m1="ID : "+id+" Not Found";
		if(mobilePlan != null)
		{
		mpResponse = new ResponseEntity<Object>(mobilePlan, null, HttpStatus.OK);
		return mpResponse;
		}
		else
		{
			mpResponse = new ResponseEntity<Object>(m1, null, HttpStatus.NOT_FOUND);
			return mpResponse;
			
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Iterable<MobilePlan>> readAll() {
		ResponseEntity<Iterable<MobilePlan>> mpResponse;
		
		Iterable<MobilePlan> mobilePlanList = mpSrvc.readAll();
		
		mpResponse = new ResponseEntity<Iterable<MobilePlan>>(mobilePlanList, null, HttpStatus.OK);
		return mpResponse;
	}
	
	// Homework: Write methods for update and delete below
	
	@RequestMapping(method = RequestMethod.PATCH) //OR PUT
	public ResponseEntity<Object> update(@RequestBody MobilePlan tobemerged) {
	ResponseEntity<Object> planResponse;
	Object mobilePlanList = mpSrvc.update(tobemerged);
	String m="ID : "+tobemerged.getId()+" Not Found";
	if(mobilePlanList != null)
	{
	planResponse = new ResponseEntity<Object>(mobilePlanList, null, HttpStatus.CREATED);
	return planResponse;
	}
	else
	{
	planResponse = new ResponseEntity<Object>(m, null, HttpStatus.NOT_FOUND);
	return planResponse;
	}
	}



	@RequestMapping(value= "{planid}",method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable(value = "planid") Long planid) 
	{
		 ResponseEntity<Object> bookResponse;
		 Boolean person=mpSrvc.delete(planid);
		 String m="ID: "+planid+" deleted successfully";
		 String m1="ID: "+planid+" Not Found";
		if(person)
		{
			 bookResponse = new ResponseEntity<Object>(m, null, HttpStatus.OK);
		}
		else
		{
			 bookResponse = new ResponseEntity<Object>(m1, null, HttpStatus.NOT_FOUND);
		}
		return bookResponse;
	}
	
}
