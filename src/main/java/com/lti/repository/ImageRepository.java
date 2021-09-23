package com.lti.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lti.model.ImageModel;

@Repository
public interface ImageRepository extends JpaRepository<ImageModel, Long> {
	 ImageModel findByName(String name);
}
