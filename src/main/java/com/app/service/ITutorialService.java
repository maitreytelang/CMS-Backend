package com.app.service;

import java.util.List;

import com.app.pojos.Tutorial;

public interface ITutorialService {

	List<Tutorial> getTutorialsByTopicId(int topicId);

	Tutorial getTutorialDetails(String tutName);

	Tutorial getTutorialDetails(int id);

	String addTutorial(Tutorial t, int topicId);

	String deleteTutorial(int id);

	String updateTutorial(int id, String contents);
}
