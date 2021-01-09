package fact.it.edgeservice.controller;

import fact.it.edgeservice.model.Employee;
import fact.it.edgeservice.model.EmployeesOfGardenCenter;
import fact.it.edgeservice.model.GardenCenter;
import fact.it.edgeservice.model.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
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

    @GetMapping("/plants/name/{name}")
    public Plant getPlantByName(@PathVariable String name) {

        Plant plant =
                restTemplate.getForObject("http://" + plantServiceBaseUrl + "/plants/name/{name}",
                        Plant.class, name);

        return plant;
    }

    @GetMapping("/plants/description/{description}")
    public List<Plant> getPlantByDescriptionContaining(@PathVariable String description) {

        ResponseEntity<List<Plant>> responseEntityPlants =
                restTemplate.exchange("http://" + plantServiceBaseUrl + "/plants/description/{description}",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Plant>>() {
                        }, description);

        return responseEntityPlants.getBody();
    }

    @GetMapping("/plants/{plantNumber}")
    public Plant getPlantByPlantNumber(@PathVariable String plantNumber) {

        Plant plant =
                restTemplate.getForObject("http://" + plantServiceBaseUrl + "/plants/{plantNumber}",
                        Plant.class, plantNumber);

        return plant;
    }

    @PostMapping("/plants")
    public Plant addPlant(@RequestParam Integer gardenCenterId, @RequestParam String plantNumber, @RequestParam String name, @RequestParam String description){

        Plant plant =
                restTemplate.postForObject("http://" + plantServiceBaseUrl + "/plants",
                        new Plant(gardenCenterId,plantNumber,name, description),Plant.class);

        return plant;
    }

    @PutMapping("/plants")
    public Plant updatePlant(@RequestParam Integer gardenCenterId, @RequestParam String plantNumber, @RequestParam String name, @RequestParam String description){

        Plant plant = new Plant(gardenCenterId,plantNumber,name, description);

        ResponseEntity<Plant> responseEntityBook =
                restTemplate.exchange("http://" + plantServiceBaseUrl + "/plants",
                        HttpMethod.PUT, new HttpEntity<>(plant), Plant.class);

        return plant;
    }

    @DeleteMapping("/plants/{plantNumber}")
    public ResponseEntity deletePlant(@PathVariable String plantNumber){

        restTemplate.delete("http://" + plantServiceBaseUrl + "/plants/" + plantNumber);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/gardencenters/{gardencenterid}/employees")
    public List<Employee> getEmployeesOfGardenCenterByName(@PathVariable int gardencenterid) {

        GardenCenter gardenCenter = restTemplate.getForObject("http://" + gardenCenterServiceBaseUrl + "/gardencenters/{gardencenterid}",
                GardenCenter.class, gardencenterid);

        ResponseEntity<List<Employee>> responseEntityEmployees =
                restTemplate.exchange("http://" + employeeServiceBaseUrl + "/employees/gardenCenterId/{gardenCenterId}",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
                        }, gardencenterid);

        return responseEntityEmployees.getBody();
    }

    @GetMapping("/employees/name/{name}")
    public Employee getAllEmployees(@PathVariable String name) {

        Employee gardenCenter = restTemplate.getForObject("http://" + gardenCenterServiceBaseUrl + "/employees/name/{name}",
                Employee.class, name);

        return gardenCenter;
    }
}

