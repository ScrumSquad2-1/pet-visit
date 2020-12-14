package com.jump.service;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jump.exception.VisitNotFoundException;
import com.jump.model.Visit;
import com.jump.repository.VisitRepository;

@Service
public class VisitService {
	
	@Autowired
	VisitRepository visitRepo;
	
	public List<Visit> getAllVisits() {
		return visitRepo.findAll();
	}
	
	public Visit getVisitById(int id) {
		return visitRepo.findById(id)
				.orElseThrow(VisitNotFoundException::new);
	}
	
	public List<Visit> getVisitsByPetId(int pet_id) {
//		possible pet not found exception
		return visitRepo.findVisitsByPetId(pet_id);
	}
	
	public List<Visit> getVisitsByDate(String date) {
		return visitRepo.findVisitsByVisitDate(date);
	}

	public Visit addVisit(@Valid Visit visit) {
		return visitRepo.save(visit);
	}

	public Visit updateVisit(@Valid Visit visit) {
//		Get visit will throw exception if not found
		getVisitById(visit.getId());
		return visitRepo.save(visit);
	}

	public List<Visit> getVisitsByCustomerId(int customer_id) {
		return visitRepo.findVisitsByCustomerId(customer_id);
	}
	
}
