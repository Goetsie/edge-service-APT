package fact.it.edgeservice.model;

public class Employee {
    private int gardenCenterId;

    private String surName;

    private String name;

    private String employeeNumber;

    public Employee() {
    }

    public Employee(int gardenCenterId, String surName, String name, String employeeNumber) {
        setGardenCenterId(gardenCenterId);
        setSurName(surName);
        setName(name);
        setEmployeeNumber(employeeNumber);
    }

    public int getGardenCenterId() {
        return gardenCenterId;
    }

    public String getSurName() {
        return surName;
    }

    public String getName() {
        return name;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setGardenCenterId(int gardenCenterId) {
        this.gardenCenterId = gardenCenterId;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
}