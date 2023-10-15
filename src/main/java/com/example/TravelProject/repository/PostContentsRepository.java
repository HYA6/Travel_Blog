package com.example.TravelProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TravelProject.entity.PostContents;

public interface PostContentsRepository extends JpaRepository<PostContents, Long> {

};
