package fact.it.edgeservice.model;


public class Plant {
    private String id;
    private String name;
    private String description;
    // Tuincentra? !!!!


    public Plant() {
    }

    // Change when center is needed !!!!
    public Plant(String name, String description) {
        this.name = name;
        this.description = description;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
