package com.jump.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jump.model.Visit;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Integer>{

	List<Visit> findVisitsByPetId(int id);

	List<Visit> findVisitsByVisitDate(String date);

	List<Visit> findVisitsByCustomerId(int customer_id);

}
