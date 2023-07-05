package com.udeldev.backendzoona.entities.utils.respons.animals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailRespons {
    private Long id;
    private String name;
    private String region;
    private String status;
    private String latinName;
    private String image;
    private String description;
}
