package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.pojos.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Integer> {

	// Display tutorials under the selected topic,
	// sorted by visits in descending order
	@Query("SELECT t FROM Tutorial t WHERE t.topic.id = :topicId ORDER BY t.visits DESC")
	List<Tutorial> getTutorialsByTopicId(@Param("topicId") int topicId);

	// Display selected tutorial details by name
	Tutorial findByName(String name);

}
