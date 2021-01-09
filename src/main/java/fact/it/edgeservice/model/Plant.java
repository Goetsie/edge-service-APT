package fact.it.edgeservice.model;


public class Plant {
    private String id;
    private String plantNumber;
    private String name;
    private String description;
    private int gardenCenterId;

    public Plant() {
    }

    public Plant(int gardenCenterId, String plantNumber, String name, String description) {
        this.gardenCenterId = gardenCenterId;
        this.plantNumber = plantNumber;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlantNumber() {
        return plantNumber;
    }

    public void setPlantNumber(String plantNumber) {
        this.plantNumber = plantNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGardenCenterId() {
        return gardenCenterId;
    }

    public void setGardenCenterId(int gardenCenterId) {
        this.gardenCenterId = gardenCenterId;
    }
}
