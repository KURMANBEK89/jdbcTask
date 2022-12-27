package org.example;

public class Country {
    private String name;
    private int cityId;
    private int majorId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", city=" + cityId +
                ", major=" + majorId +
                '}';
    }
}
