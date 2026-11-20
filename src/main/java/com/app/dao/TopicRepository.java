
package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Topic;

public interface TopicRepository extends JpaRepository<Topic, Integer> {

	// Display all available topics
	default List<Topic> getTopics() {
		return findAll();
	}

}
