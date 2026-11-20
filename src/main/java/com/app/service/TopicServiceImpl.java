package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.TopicRepository;
import com.app.pojos.Topic;

@Service
@Transactional
public class TopicServiceImpl implements ITopicService {

	@Autowired
	private TopicRepository topicDao;

//	@Override
//	public List<String> getTopicNames(){
//
//		return topicDao.getTopics().stream()
//									.map(Topic::getTopicName)
//									.collect(Collectors.toList());
//	}
	@Override
	public List<Topic> getTopic(){

		return topicDao.getTopics();
	}

}
