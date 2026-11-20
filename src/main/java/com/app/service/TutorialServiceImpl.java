
package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.TopicRepository;
import com.app.dao.TutorialRepository;
import com.app.pojos.Topic;
import com.app.pojos.Tutorial;

@Service
@Transactional
public class TutorialServiceImpl implements ITutorialService {

	@Autowired
	private TutorialRepository tutDao;

	@Autowired
	private TopicRepository topicDao;

	@Override
	public List<Tutorial> getTutorialsByTopicId(int topicId) {
		return tutDao.getTutorialsByTopicId(topicId);
	}

	@Override
	public Tutorial getTutorialDetails(String tutName) {
		Tutorial t = tutDao.findByName(tutName);

		if (t != null) {
			t.setVisits(t.getVisits() + 1);
		}

		return t;
	}

	@Override
	public Tutorial getTutorialDetails(int id) {
		Tutorial t = tutDao.findById(id).orElse(null);

		if (t != null) {
			t.setVisits(t.getVisits() + 1);
		}

		return t;
	}

	@Override
	public String addTutorial(Tutorial t, int topicId) {
		Topic topic = topicDao.findById(topicId).orElse(null);

		if (topic == null) {
			return "Topic not found";
		}

		t.setTopic(topic);
		tutDao.save(t);

		return "Tutorial added successfully";
	}

	@Override
	public String deleteTutorial(int id) {
		if (!tutDao.existsById(id)) {
			return "Tutorial not found";
		}

		tutDao.deleteById(id);

		return "Tutorial deleted successfully";
	}

	@Override
	public String updateTutorial(int id, String contents) {
		Tutorial t = tutDao.findById(id).orElse(null);

		if (t == null) {
			return "Tutorial not found";
		}

		t.setContents(contents);
		tutDao.save(t);

		return "Tutorial updated successfully";
	}
}
