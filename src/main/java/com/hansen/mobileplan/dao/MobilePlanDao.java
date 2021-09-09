package com.hansen.mobileplan.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.hansen.mobileplan.model.MobilePlan;

public interface MobilePlanDao extends PagingAndSortingRepository<MobilePlan, Long> 
{
	
	//@Query(value="select * from mobile_plan where id=:id",nativeQuery=true)
	//MobilePlan getall(@Param("id")Integer id);
}