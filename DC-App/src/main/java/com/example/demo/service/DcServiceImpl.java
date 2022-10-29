package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.binding.Child;
import com.example.demo.binding.ChildRequest;
import com.example.demo.binding.DcSummary;
import com.example.demo.binding.Education;
import com.example.demo.binding.Income;
import com.example.demo.binding.PlanSelection;
import com.example.demo.entity.DcCaseEntity;
import com.example.demo.entity.DcChildrenEntity;
import com.example.demo.entity.DcEducationEntity;
import com.example.demo.entity.DcIncomeEntity;
import com.example.demo.entity.PlanEntity;
import com.example.demo.repository.CitizenAppRepo;
import com.example.demo.repository.DcCaseRepo;
import com.example.demo.repository.DcChildrenRepo;
import com.example.demo.repository.DcEducationRepo;
import com.example.demo.repository.DcIncomeRepo;
import com.example.demo.repository.PlanRepository;

@Service
public class DcServiceImpl implements DcService{
	
	@Autowired
	private DcCaseRepo dcCaseRepo;
	
	@Autowired
	private PlanRepository planRepo;
	
	@Autowired
	private DcIncomeRepo incomeRepo;
	
	@Autowired
	private DcEducationRepo eduRepo;
	
	@Autowired
	private DcChildrenRepo childRepo;
	
	@Autowired
	private CitizenAppRepo appRepo;
	
	@Override
	public Long loadCaseNum(Integer appId) {
		DcCaseEntity findByAppId = dcCaseRepo.findByAppId(appId);
		if(findByAppId != null) {
			return findByAppId.getCaseNum();
		}
		
		
		return (long) 01;
	}

	@Override
	public Map<Integer, String> getPlanNames() {
		List<PlanEntity> findAll = planRepo.findAll();
		
		
		Map<Integer, String> plansMap = new HashMap<>();
		
		for(PlanEntity entity : findAll) {
		plansMap.put(entity.getPlanId(), entity.getPlanName());
		}
		return plansMap;
		}

	@Override
	public Long saveplanSelection(PlanSelection planSelection) {
		
		Optional<DcCaseEntity> findById = dcCaseRepo.findById(planSelection.getCaseNum());
		
		if(findById.isPresent()) {
		DcCaseEntity dcCaseEntity = findById.get();
		
		dcCaseEntity.setPlanId(planSelection.getPlanId());
		// dcCaseEntity.setAppId(planSelection.getAppId());
		
		 dcCaseRepo.save(dcCaseEntity);
		
		//if(dcCaseEntity.getCaseNum()!=null) {
			return planSelection.getCaseNum();
		}
		
		return null;
		
		
	}

	@Override
	public Long saveIncomeData(Income income) {
		
		DcIncomeEntity entity = new DcIncomeEntity();
		BeanUtils.copyProperties(income, entity);
		incomeRepo.save(entity);
		return income.getCaseNum();
		}

	@Override
	public Long saveEducation(Education education) {
		
		DcEducationEntity entity = new DcEducationEntity();
		BeanUtils.copyProperties(education, entity);
		eduRepo.save(entity);
		
		return education.getCaseNum();
		
		
	}

	@Override
	public Long saveChildrens(ChildRequest request) {
		List<Child> childs = request.getChilds();
		for(Child c : childs) {
			DcChildrenEntity entity =  new DcChildrenEntity();
			BeanUtils.copyProperties(c, entity);
			childRepo.save(entity);
		}
			
		return request.getCaseNum();
		
		
	}

	@Override
	public DcSummary getSummary(Long caseNum) {
		
		String planName = " ";
		
		DcIncomeEntity incomeEntity = incomeRepo.findByCaseNum(caseNum);
		DcEducationEntity educationEntity = eduRepo.findByCaseNum(caseNum);
		List<DcChildrenEntity> childsEntity = childRepo.findByCaseNum(caseNum);
		
		Optional<DcCaseEntity> dcCase = dcCaseRepo.findById(caseNum);
		
		if(dcCase.isPresent()) {
			Integer planId = dcCase.get().getPlanId();
			Optional<PlanEntity>plan = planRepo.findById(planId);
			if(plan.isPresent()) {
				 planName = plan.get().getPlanName();
			}
		}
		DcSummary summary = new DcSummary();
		
		summary.setPlanName(planName);
		
		Income income = new Income();
		BeanUtils.copyProperties(incomeEntity, income);
		summary.setIncome(income);
		
		Education edu = new Education();
		BeanUtils.copyProperties(educationEntity, edu);
		summary.setEducation(edu);
		
		List<Child> childs = new ArrayList<>();
		for(DcChildrenEntity entity : childsEntity) {
			Child ch = new Child();
			BeanUtils.copyProperties(entity, ch);
			childs.add(ch);
			}
		summary.setChilds(childs);
		return summary;
	}
		
}		
	


 

