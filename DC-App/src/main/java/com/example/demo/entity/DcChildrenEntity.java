package com.example.demo.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "DC_CHILDREN")
public class DcChildrenEntity {
	
	@Id
	@GeneratedValue
	private Integer childId;
	private Long caseNum;
	private Integer childAge;
	private LocalDate childDob;	
	private Long childSsn;
	

}
