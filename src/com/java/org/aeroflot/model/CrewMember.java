package org.aeroflot.model;

public abstract class CrewMember {
    private String name;
    private String role;

    public CrewMember(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return role + ": " + name;
    }
}