package fact.it.edgeservice.model;

import java.util.List;

public class PlantsOfGardenCenter {
    private int gardenCenterId;
    private String name;
    private String city;
    private String address;
    private List<Plant> plants;

    public PlantsOfGardenCenter(GardenCenter gardenCenter, List<Plant> plants) {
        setGardenCenterId(gardenCenter.getGardenCenterId());
        setName(gardenCenter.getName());
        setCity(gardenCenter.getCity());
        setAddress(gardenCenter.getAddress());
        setPlants(plants);
    }

    public int getGardenCenterId() {
        return gardenCenterId;
    }

    public void setGardenCenterId(int gardenCenterId) {
        this.gardenCenterId = gardenCenterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }
}
