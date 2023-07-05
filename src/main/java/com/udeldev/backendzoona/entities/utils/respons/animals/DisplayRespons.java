package com.udeldev.backendzoona.entities.utils.respons.animals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DisplayRespons {
    private Long id;
    private String name;
    private String region;
    private String status;
    private String latinName;
    private String image;
}
