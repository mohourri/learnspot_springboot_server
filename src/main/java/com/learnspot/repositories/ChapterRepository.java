package com.learnspot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learnspot.entities.Chapter;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

}
