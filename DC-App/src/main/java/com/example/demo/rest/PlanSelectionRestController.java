package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.binding.PlanSelection;
import com.example.demo.service.DcService;

@RestController
public class PlanSelectionRestController {
	
	@Autowired
	private DcService service;
	
	@PostMapping("/planSel")
	public ResponseEntity <Long> planSelection(@RequestBody PlanSelection planSel) {
		
		Long caseNum  = service.saveplanSelection(planSel);
		
		return new ResponseEntity<>(caseNum, HttpStatus.CREATED);
		
	}

}
