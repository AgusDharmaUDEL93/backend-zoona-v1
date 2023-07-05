package com.udeldev.backendzoona.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.udeldev.backendzoona.entities.models.interfaces.DisplayData;
import com.udeldev.backendzoona.entities.models.tabels.AnimalEntity;
import com.udeldev.backendzoona.entities.utils.respons.animals.DetailRespons;
import com.udeldev.backendzoona.entities.utils.respons.animals.DisplayRespons;
import com.udeldev.backendzoona.entities.utils.respons.globals.ResponsData;
import com.udeldev.backendzoona.entities.utils.respons.globals.ResponsListData;
import com.udeldev.backendzoona.entities.utils.respons.globals.ResponsNoData;
import com.udeldev.backendzoona.repositories.AnimalRepositories;

@Service
public class AnimalServices {
    @Autowired
    AnimalRepositories animalRepositories;

    public ResponsNoData saveData(String name, String latinName, String region, String status, String desc,
            MultipartFile image) {

        AnimalEntity animalEntity;
        try {
            animalEntity = new AnimalEntity(name, latinName, region, status, desc, image.getBytes());
        } catch (IOException e) {
            return new ResponsNoData(0, 500, "Failed to parse image to bytes");
        }

        if (name.isEmpty() || latinName.isEmpty() || region.isEmpty() || status.isEmpty() || desc.isEmpty()
                || image.isEmpty()) {
            return new ResponsNoData(0, 400, "Response body not match");
        }

        animalRepositories.save(animalEntity);
        return new ResponsNoData(1, 200, "Success to create new animal data");
    }

    public byte[] getImageById(Long id) {
        Optional<AnimalEntity> animalEntityOptional = animalRepositories.findById(id);
        byte[] image = animalEntityOptional.orElse(null).getImage();
        return image;
    }

    public ResponsListData<DisplayRespons> getAllDisplayData() {
        List<DisplayData> displayData = animalRepositories.findAllDisplayData();

        List<DisplayRespons> respons = new ArrayList<DisplayRespons>();

        for (DisplayData displayData2 : displayData) {
            DisplayRespons temp = new DisplayRespons(displayData2.getId(), displayData2.getName(),
                    displayData2.getRegion(), displayData2.getStatus(), displayData2.getLatinName(),
                    "/image/" + displayData2.getId());
            respons.add(temp);
        }

        return new ResponsListData<DisplayRespons>(1, 200, "Success get animal data", respons);
    }

    public ResponsData<DetailRespons> getDetailAnimalData(Long id) {
        AnimalEntity detailRespons = animalRepositories.findById(id).orElse(null);

        DetailRespons respons = new DetailRespons();
        if (detailRespons != null) {
            respons.setId(id);
            respons.setImage("/image/" + id);
            respons.setLatinName(detailRespons.getLatinName());
            respons.setDescription(detailRespons.getDescription());
            respons.setName(detailRespons.getName());
            respons.setRegion(detailRespons.getRegion());
            respons.setStatus(detailRespons.getStatus());
        } else {
            return new ResponsData<DetailRespons>(0, 404, "No Data", null);
        }

        return new ResponsData<DetailRespons>(1, 200, "Succes get detail animal data", respons);
    }

}
