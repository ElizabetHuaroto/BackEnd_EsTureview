package com.upc.estureview.entitys;

import javax.persistence.*;

@Entity
@Table(name = "school")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 60, nullable = false)
    private String name;
    private double pension;
    private String district;
    private String photo;
    private int calification;

    public School(Long id, String name, double pension, String district, String photo, int calification) {
        this.id = id;
        this.name = name;
        this.pension = pension;
        this.district = district;
        this.photo = photo;
        this.calification = calification;
    }

    public School() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPension() {
        return pension;
    }

    public void setPension(double pension) {
        this.pension = pension;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public double getCalification() {
        return calification;
    }

    public void setCalification(int calification) {
        this.calification = calification;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pension=" + pension +
                ", district='" + district + '\'' +
                ", photo='" + photo + '\'' +
                ", calification=" + calification +
                '}';
    }
}
