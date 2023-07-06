package com.udeldev.backendzoona.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.udeldev.backendzoona.entities.utils.respons.animals.DetailRespons;
import com.udeldev.backendzoona.entities.utils.respons.animals.DisplayRespons;
import com.udeldev.backendzoona.entities.utils.respons.globals.ResponsData;
import com.udeldev.backendzoona.entities.utils.respons.globals.ResponsListData;
import com.udeldev.backendzoona.entities.utils.respons.globals.ResponsNoData;
import com.udeldev.backendzoona.services.AnimalServices;

@RestController
@RequestMapping("api/v1")
public class AnimalControllers {

    @Autowired
    AnimalServices animalServices;

    @PostMapping(path = "/animals", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<ResponsNoData> postAnimal(
            @RequestParam("name") String name,
            @RequestParam("latinName") String latinName,
            @RequestParam("region") String region,
            @RequestParam("status") String status,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile image,
            @RequestParam(name = "api_key", required = false) String api_key) throws IOException {
        ResponsNoData response;

        if (api_key == null) {
            response = new ResponsNoData(0, 400, "Please insert the api_key");
            return ResponseEntity.badRequest().body(response);
        }

        if (api_key.equals("kelompok3")) {
            response = animalServices.saveData(name, latinName, region, status, description, image);
        } else {
            response = new ResponsNoData(0, 403, "Cannot insert data");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        return ResponseEntity.created(null).body(response);
    }

    @GetMapping(path = "/animals")
    public ResponseEntity<ResponsListData<DisplayRespons>> getAllAnimalDisplayData() {
        ResponsListData<DisplayRespons> responsListData = animalServices.getAllDisplayData();
        return ResponseEntity.ok().body(responsListData);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<?> getImage(@PathVariable String id) {

        Long idNumber;

        try {
            idNumber = Long.parseLong(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

        byte[] image = animalServices.getImageById(idNumber);
        if (image != null) {
            ByteArrayResource resource = new ByteArrayResource(image);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .contentLength(image.length)
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + idNumber + ".jpg\"")
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();

        }
    }

    @GetMapping(path = "/animal/{id}")
    public ResponseEntity<?> getDetailAnimal(@PathVariable String id) {
        Long idNumber;
        try {
            idNumber = Long.parseLong(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponsNoData(0, 400, "Bad request"));
        }
        ResponsData<DetailRespons> response = animalServices.getDetailAnimalData(idNumber);

        if (response.getCode() == 404) {
            return ((BodyBuilder) ResponseEntity.notFound()).body(new ResponsNoData(0, 404, "No data found"));
        }
        return ResponseEntity.ok().body(response);
    }
}
