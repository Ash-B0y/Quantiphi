package com.quantiphi.tutorial.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantiphi.tutorial.model.Tutorial;
import com.quantiphi.tutorial.repository.TutorialRepository;

@Service
public class TutorialService {
	
	@Autowired
	private TutorialRepository tutorialRepository;
	
	public Tutorial addTutorial(Tutorial tutorial) {
		
		tutorialRepository.save(tutorial);
		Optional<Tutorial> persistedTutorial = tutorialRepository.findById(tutorial.getId());
			
		 if(!persistedTutorial.isPresent())
			 return null;
		 
		 return persistedTutorial.get();
		 
	 }
	
   public Tutorial publishTutorial(Long id) {
	   
	   tutorialRepository.publishTutorial("Published", id);
	   Optional<Tutorial> publishedTutorial = tutorialRepository.findById(id);
	   if(publishedTutorial.get().getStatus().equals("Published"))
		   return publishedTutorial.get();
	   
	   return null;
		 
	 }
   
   public Tutorial unpublishTutorial(Long id) {
	   
	   tutorialRepository.unpublishTutorial("Pending", id);
	   Optional<Tutorial> unpublishedTutorial = tutorialRepository.findById(id);
	   if(unpublishedTutorial.get().getStatus().equals("Pending"))
		   return unpublishedTutorial.get();
	   
	   return null;
		 
	 }
	 
   public Tutorial updateTutorial(Tutorial tutorial) {

	   tutorialRepository.updateTutorial(tutorial.getTitle(),tutorial.getDescription(),tutorial.getStatus(),tutorial.getId());
	   Optional<Tutorial> updatedTutorial = tutorialRepository.findById(tutorial.getId());
	   if(updatedTutorial.get().getTitle().equals(tutorial.getTitle()) && updatedTutorial.get().getDescription().equals(tutorial.getDescription()) && updatedTutorial.get().getStatus().equals(tutorial.getStatus()))
		   return updatedTutorial.get();
	   
	   return null;
		 
	 }
   
   public Tutorial deleteTutorial(Long id) {
	   
	   tutorialRepository.deleteById(id);
	   Optional<Tutorial> deletedTutorial = tutorialRepository.findById(id);
	   if(!deletedTutorial.isPresent())
			 return new Tutorial();
	   
	   return null;
	 }
   
   public List<Tutorial> deleteAllTutorials() {
	   
	   List<Tutorial> availableTutorials = tutorialRepository.findAll();
	   
	   if(availableTutorials.isEmpty())
		   return new ArrayList<Tutorial>();
	   
	   tutorialRepository.deleteAll();
	   
	   if(tutorialRepository.findAll().isEmpty())
		   return null;
	   
	   return availableTutorials;
	 }
   
   public List<Tutorial> fetchAllTutorials() {
	   
	   List<Tutorial> AllTutorials = tutorialRepository.findAll();
	   if(!AllTutorials.isEmpty())
			 return AllTutorials;
	   
	   return null;
	 }
   
   public List<Tutorial> fetchTutorials(String title) {
	   
	   List<Tutorial> AllTutorials = tutorialRepository.findAll();
	   if(!AllTutorials.isEmpty()) {
		   List<Tutorial> Tutorials = AllTutorials.stream().filter(t->t.getTitle().toLowerCase().contains(title.toLowerCase())).collect(Collectors.toList());
		   if(Tutorials.size() != 0)
			   return Tutorials;
		   return null;
	   }
	   
	   return null;
	 }

   
   
}


