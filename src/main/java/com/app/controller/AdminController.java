
package com.app.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Tutorial;
import com.app.service.ITopicService;
import com.app.service.ITutorialService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ITopicService topicService;

	@Autowired
	private ITutorialService tutService;

	// Show all topics
	@GetMapping("/choose_topic")
	public ResponseEntity<?> chooseTopic() {
		return ResponseEntity.ok(topicService.getTopic());
	}

	// Show tutorials under the selected topic
	@GetMapping("/tutorials")
	public ResponseEntity<?> showTutorials(@RequestParam int topicId) {
		return ResponseEntity.ok(tutService.getTutorialsByTopicId(topicId));
	}

	// Show tutorial details by ID
	@GetMapping("/tut_details")
	public ResponseEntity<?> tutDetails(@RequestParam int tutorialId) {
		return ResponseEntity.ok(tutService.getTutorialDetails(tutorialId));
	}

	// Delete a tutorial
	@DeleteMapping("/delete_tutorial")
	public ResponseEntity<String> deleteTutorial(@RequestParam int tutorialId) {
		tutService.deleteTutorial(tutorialId);
		return ResponseEntity.ok("Tutorial deleted successfully");
	}

	// Get topics needed when adding a tutorial
	@GetMapping("/addTutorial")
	public ResponseEntity<?> addTutorial() {
		return ResponseEntity.ok(topicService.getTopic());
	}

	// Add a new tutorial
	@PostMapping("/process_form")
	public ResponseEntity<String> processForm(@RequestParam int topicId, @RequestParam String name,
			@RequestParam String author, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate pubDate,
			@RequestParam String content) {

		if (content == null || content.isBlank() || content.length() >= 255) {
			return ResponseEntity.badRequest().body("Content must be non-empty and less than 255 characters");
		}

		if (pubDate == null || pubDate.isAfter(LocalDate.now()) || pubDate.isBefore(LocalDate.now().minusMonths(6))) {
			return ResponseEntity.badRequest()
					.body("Publish date must be within the last 6 months and not in the future");
		}

		Tutorial tutorial = new Tutorial(name, author, pubDate, 0, content);
		tutService.addTutorial(tutorial, topicId);

		return ResponseEntity.status(HttpStatus.CREATED).body("Tutorial added successfully");
	}

	// Get tutorial details before updating
	@GetMapping("/update_tutorial")
	public ResponseEntity<?> updateTut(@RequestParam int tutorialId) {
		return ResponseEntity.ok(tutService.getTutorialDetails(tutorialId));
	}

	// Update tutorial content
	@PutMapping("/update_tutorial")
	public ResponseEntity<String> updateTutorial(@RequestParam int tutorialId, @RequestParam String contents) {

		if (contents == null || contents.isBlank() || contents.length() >= 255) {
			return ResponseEntity.badRequest().body("Content must be non-empty and less than 255 characters");
		}

		tutService.updateTutorial(tutorialId, contents);

		return ResponseEntity.ok("Tutorial updated successfully");
	}
}
