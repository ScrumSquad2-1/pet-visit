package com.jump.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jump.exception.VisitIdMismatchException;
import com.jump.model.Visit;
import com.jump.service.VisitService;

@RestController
//@RequestMapping("visit")
public class VisitController {
	
	@Autowired
	VisitService visitServ;
	
	@PostMapping
	public ResponseEntity<Visit> addVisit(@RequestBody Visit visit) throws URISyntaxException {
		System.out.println(visit);
		Visit result = visitServ.addVisit(visit);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(result.getId())
				.toUri();
		return ResponseEntity.created(location).body(result);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateVisit(@PathVariable int id, @Valid @RequestBody Visit visit) {
		if (id == visit.getId()) {
			visitServ.updateVisit(visit);
			return ResponseEntity.ok("Updated");
		} else {
			throw new VisitIdMismatchException();
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Visit>> getVisits() {
		return ResponseEntity.ok(visitServ.getAllVisits());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Visit> getVisitById(@PathVariable int id) {
		Visit result = visitServ.getVisitById(id);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/pet/{pet_id}")
	public ResponseEntity<List<Visit>> getVisitsByPetId(@PathVariable int pet_id) {
		List<Visit> result = visitServ.getVisitsByPetId(pet_id);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/date")
	public ResponseEntity<List<Visit>> getVisitsByDate(@QueryParam("date") String date) {
		System.out.println(date);
		return ResponseEntity.ok(visitServ.getVisitsByDate(date));
	}
	
	
}
