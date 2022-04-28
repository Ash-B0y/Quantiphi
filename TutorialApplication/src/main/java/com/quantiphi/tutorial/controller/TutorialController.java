package com.quantiphi.tutorial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quantiphi.tutorial.model.Tutorial;
import com.quantiphi.tutorial.service.TutorialService;

@RestController
@PropertySource("classpath:configuration.properties")
public class TutorialController {
	
	@Autowired
	TutorialService tutorialService; 
	@Autowired
    private Environment environment;
	
	@PostMapping("/add")
	public ResponseEntity<?> addTutorial(@RequestBody Tutorial tutorial) {
		Tutorial addedTutorial = tutorialService.addTutorial(tutorial);
		
		if(addedTutorial==null)
			return new ResponseEntity<>(environment.getProperty("AddTutorialService.FAILURE"), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(environment.getProperty("AddTutorialService.SUCCESS"), HttpStatus.OK);
		
	}
	
	@PutMapping("/publish")
	public ResponseEntity<?> publishTutorial(@RequestParam(name="id",required=true) Long id) {
		Tutorial publishedTutorial = tutorialService.publishTutorial(id);
		
		if(publishedTutorial==null)
			return new ResponseEntity<>(environment.getProperty("PublishTutorialService.FAILURE"), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(environment.getProperty("PublishTutorialService.SUCCESS"), HttpStatus.OK);
		
	}
  
	@PutMapping("/unpublish")
	public ResponseEntity<?> unpublishTutorial(@RequestParam(name="id",required=true) Long id) {
		Tutorial unpublishedTutorial = tutorialService.unpublishTutorial(id);
		
		if(unpublishedTutorial==null)
			return new ResponseEntity<>(environment.getProperty("UnpublishTutorialService.FAILURE"), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(environment.getProperty("UnpublishTutorialService.SUCCESS"), HttpStatus.OK);
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateTutorial(@RequestBody Tutorial tutorial) {
		Tutorial updatedTutorial = tutorialService.updateTutorial(tutorial);
		
		if(updatedTutorial==null)
			return new ResponseEntity<>(environment.getProperty("UpdateTutorialService.FAILURE"), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(environment.getProperty("UpdateTutorialService.SUCCESS"), HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteTutorial(@RequestParam(name="id",required=true) Long id) {
		Tutorial deletedTutorial = tutorialService.deleteTutorial(id);
		
		if(deletedTutorial == null)
			return new ResponseEntity<>(environment.getProperty("DeleteTutorialService.FAILURE"), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(environment.getProperty("DeleteTutorialService.SUCCESS"), HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity<?> deleteAllTutorials() {
		List<Tutorial> deletedTutorials = tutorialService.deleteAllTutorials();
		
		if(deletedTutorials == null)
			return new ResponseEntity<>(environment.getProperty("DeleteAllTutorialsService.SUCCESS"), HttpStatus.OK);
		
		else if(deletedTutorials.isEmpty())
			return new ResponseEntity<>(environment.getProperty("DeleteAllTutorialsService.NIL"), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(environment.getProperty("DeleteAllTutorialsService.FAILURE"), HttpStatus.OK);
		
	}
	
	@GetMapping("/fetchAll")
	public ResponseEntity<?> fetchAllTutorials() {
		List<Tutorial> AllTutorials = tutorialService.fetchAllTutorials();
		
		if(AllTutorials == null)
			return new ResponseEntity<>(environment.getProperty("FetchAllTutorialsService.NIL"), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(AllTutorials, HttpStatus.OK);
		
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> fetchTutorials(@RequestParam(name="title",required=true) String title) {
		List<Tutorial> Tutorials = tutorialService.fetchTutorials(title);
		
		if(Tutorials == null)
			return new ResponseEntity<>(environment.getProperty("FetchTutorialsService.NIL"), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(Tutorials, HttpStatus.OK);
		
	}


}
