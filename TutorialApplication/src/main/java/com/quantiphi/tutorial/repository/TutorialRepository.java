package com.quantiphi.tutorial.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quantiphi.tutorial.model.Tutorial;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long>{
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value="update Tutorial set status = :status where id=:TutorialId")
	void publishTutorial(@Param("status") String status,@Param("TutorialId") Long TutorialId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value="update Tutorial set status = :status where id=:TutorialId")
	void unpublishTutorial(@Param("status") String status,@Param("TutorialId") Long TutorialId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value="update Tutorial set title = :title , description = :description , status = :status where id=:TutorialId")
	void updateTutorial(@Param("title") String title,@Param("description") String description,@Param("status") String status,@Param("TutorialId") Long TutorialId);

}
