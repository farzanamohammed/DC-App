package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.binding.Child;
import com.example.demo.binding.ChildRequest;
import com.example.demo.binding.CitizenApp;
import com.example.demo.binding.DcSummary;
import com.example.demo.binding.Education;
import com.example.demo.binding.Income;
import com.example.demo.binding.PlanSelection;

public interface DcService {
	
	
	public Long loadCaseNum(Integer appId);

	public Map <Integer,String> getPlanNames();
	
	public Long saveplanSelection(PlanSelection planSelection);
	
	public Long saveIncomeData(Income income);
	
	public Long saveEducation(Education education);
	
	public Long saveChildrens(ChildRequest request);
	
	public DcSummary getSummary(Long caseNum);

	
	

	
	
	
	}
