package com.example.demo.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.binding.CreateCaseResponse;
import com.example.demo.service.DcService;

@RestController
public class CreateCaseRestController {
	
	@Autowired
	private DcService service;
	
	@GetMapping("/case/{appId}")
	public ResponseEntity<CreateCaseResponse> createCase(@PathVariable Integer appId){
		
		Long caseNum = service.loadCaseNum(appId);
		
		 Map<Integer, String> plansMap = service.getPlanNames();
		 
		 CreateCaseResponse response = new CreateCaseResponse();
		 
		 response.setCaseNum(caseNum);
		 response.setPlanNames(plansMap);
		 
		 return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
