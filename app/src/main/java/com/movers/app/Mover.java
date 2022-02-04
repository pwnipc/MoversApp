package com.movers.app;

public class Mover {
    private String name;
    private String services;

    public Mover(String name, String services) {
        this.name = name;
        this.services = services;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "Mover{" +
                "name='" + name + '\'' +
                ", services='" + services + '\'' +
                '}';
    }
}
