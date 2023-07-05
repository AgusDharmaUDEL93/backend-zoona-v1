package com.udeldev.backendzoona.entities.models.tabels;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
public class AnimalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String latinName;

    private String region;

    private String status;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 10485760)
    @Lob
    private byte[] image;

    public AnimalEntity(String name, String latinName, String region, String status, String description, byte[] image) {
        this.name = name;
        this.latinName = latinName;
        this.region = region;
        this.status = status;
        this.description = description;
        this.image = image;
    }

    AnimalEntity() {

    }
}
