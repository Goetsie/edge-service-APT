package fact.it.edgeservice.controller;

import fact.it.edgeservice.model.GardenCenter;
import fact.it.edgeservice.model.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GardenCenterDataController {

    @Autowired
    private RestTemplate restTemplate;

    //url's van de services
    @Value("${gardencenterservice.baseurl}")
    private String gardenCenterServiceBaseUrl;
    @Value("${plantservice.baseurl}")
    private String plantServiceBaseUrl;
    @Value("${employeeservice.baseurl}")
    private String employeeServiceBaseUrl;

    @GetMapping("/gardencenters")
    public List<GardenCenter> getAllGardenCenters(){

        ResponseEntity<List<GardenCenter>> responseEntityGardenCenters =
                restTemplate.exchange("http://" + gardenCenterServiceBaseUrl + "/gardencenters",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<GardenCenter>>() {
                        });

        return responseEntityGardenCenters.getBody();
    }

    @GetMapping("/plants")
    public List<Plant> getAllPlants(){

        ResponseEntity<List<Plant>> responseEntityPlants =
                restTemplate.exchange("http://" + plantServiceBaseUrl + "/plants",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Plant>>() {
                        });

        return responseEntityPlants.getBody();
    }
}
