package fact.it.edgeservice.controller;

import fact.it.edgeservice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public List<GardenCenter> getAllGardenCenters() {

        ResponseEntity<List<GardenCenter>> responseEntityGardenCenters =
                restTemplate.exchange("http://" + gardenCenterServiceBaseUrl + "/gardencenters",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<GardenCenter>>() {
                        });

        return responseEntityGardenCenters.getBody();
    }

    @GetMapping("/plants")
    public List<Plant> getAllPlants() {

        ResponseEntity<List<Plant>> responseEntityPlants =
                restTemplate.exchange("http://" + plantServiceBaseUrl + "/plants",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Plant>>() {
                        });

        return responseEntityPlants.getBody();
    }

    @GetMapping("/gardencenters/{gardencenterid}/employees")
    public EmployeesOfGardenCenter getEmployeesOfGardenCenterByGardenCenterId(@PathVariable int gardencenterid) {

        GardenCenter gardenCenter = restTemplate.getForObject("http://" + gardenCenterServiceBaseUrl + "/gardencenters/{gardencenterid}",
                GardenCenter.class, gardencenterid);

        ResponseEntity<List<Employee>> responseEntityEmployees =
                restTemplate.exchange("http://" + employeeServiceBaseUrl + "/employees/gardenCenterId/{gardenCenterId}",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
                        }, gardencenterid);

        return new EmployeesOfGardenCenter(gardenCenter, responseEntityEmployees.getBody());
    }

    @PostMapping("/plants")
    public Plant addPlant(@RequestParam Integer gardenCenterId, @RequestParam String plantNumber, @RequestParam String name, @RequestParam String description){

        Plant addPlant =
                restTemplate.postForObject("http://" + plantServiceBaseUrl + "/plants",
                        new Plant(gardenCenterId,plantNumber,name, description),Plant.class);

        return addPlant;
    }

    @GetMapping("/gardencenters/{gardencenterid}/plants")
    public PlantsOfGardenCenter getPlantsOfGardenCenterByGardenCenterId(@PathVariable int gardencenterid) {

        GardenCenter gardenCenter = restTemplate.getForObject("http://" + gardenCenterServiceBaseUrl + "/gardencenters/{gardencenterid}",
                GardenCenter.class, gardencenterid);

        ResponseEntity<List<Plant>> responseEntityPlants =
                restTemplate.exchange("http://" + employeeServiceBaseUrl + "/plants/gardencenterid/{gardenCenterId}",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Plant>>() {
                        }, gardencenterid);

        return new PlantsOfGardenCenter(gardenCenter, responseEntityPlants.getBody());
    }
}

