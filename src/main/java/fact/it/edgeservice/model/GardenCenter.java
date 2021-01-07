package fact.it.edgeservice.model;

public class GardenCenter {
    private String id;
    private int gardenCenterId;
    private String name;
    private String City;
    private String address;

    public GardenCenter() {
    }

    public GardenCenter( int gardenCenterId, String name, String address) {
        this.gardenCenterId = gardenCenterId;
        this.name = name;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
