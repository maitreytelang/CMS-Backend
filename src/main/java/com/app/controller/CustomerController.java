
package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.ITopicService;
import com.app.service.ITutorialService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private ITopicService topicService;

	@Autowired
	private ITutorialService tutorialService;

	// Get all topics
	@GetMapping("/topics")
	public ResponseEntity<?> customerHome() {
		return ResponseEntity.ok(topicService.getTopic());
	}

	// Get tutorials under the selected topic
	@GetMapping("/tutorials")
	public ResponseEntity<?> showTutorialsByTopic(@RequestParam int topicId) {

		return ResponseEntity.ok(tutorialService.getTutorialsByTopicId(topicId));
	}

	// Get details of a tutorial by name
	@GetMapping("/tutorial_details")
	public ResponseEntity<?> getTutorialDetails(@RequestParam(name = "tut_name") String tutName) {

		return ResponseEntity.ok(tutorialService.getTutorialDetails(tutName));
	}
}
