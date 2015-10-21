package md.pharm.restservice.service.util;

/**
 * Created by Andrei on 10/18/2015.
 */
public enum Country {

    MD("MD"),
    RO("RO");

    private String name;

    Country(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
