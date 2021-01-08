package fact.it.edgeservice.model;

import java.util.ArrayList;
import java.util.List;

public class EmployeesOfGardenCenter {
    private int gardenCenterId;
    private String name;
    private String city;
    private String address;
    private List<Employee> employees;

    public EmployeesOfGardenCenter(GardenCenter gardenCenter, List<Employee> employees) {
        setGardenCenterId(gardenCenter.getGardenCenterId());
        setName(gardenCenter.getName());
        setCity(gardenCenter.getCity());
        setAddress(gardenCenter.getAddress());
        employees = new ArrayList<>();
        setEmployees(employees);
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
