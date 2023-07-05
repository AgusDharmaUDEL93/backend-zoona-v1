package com.udeldev.backendzoona.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.udeldev.backendzoona.entities.models.interfaces.DisplayData;
import com.udeldev.backendzoona.entities.models.tabels.AnimalEntity;

public interface AnimalRepositories extends CrudRepository<AnimalEntity, Long> {
    @Query("SELECT p FROM AnimalEntity p")
    List<DisplayData> findAllDisplayData();
}