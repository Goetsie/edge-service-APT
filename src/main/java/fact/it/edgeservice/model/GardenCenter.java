package fact.it.edgeservice.model;

public class GardenCenter {
    private int gardenCenterId;
    private String name;
    private String city;
    private String address;

    public GardenCenter() {
    }

    public GardenCenter(int gardenCenterId, String name, String city, String address) {
        this.gardenCenterId = gardenCenterId;
        this.name = name;
        this.city = city;
        this.address = address;
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
}
