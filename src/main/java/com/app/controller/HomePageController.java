
package com.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageController {

	@GetMapping("/")
	public ResponseEntity<String> homePage() {
		return ResponseEntity.ok("Welcome to the Tutorial Application");
	}
}
