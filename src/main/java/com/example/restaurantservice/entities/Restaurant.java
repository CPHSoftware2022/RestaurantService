package com.example.restaurantservice.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "restaurant_id", nullable = false)
    private Long id;
    private String name;
    private String phone;
    private boolean isPartner;
    @OneToMany(mappedBy = "restaurant")
    private List<Item> itemList;


    public Restaurant(String restaurantName, String restaurantPhone, boolean restaurantIsPartner) {
        System.out.println("Restaurant constructor");
        this.name = restaurantName;
        this.phone = restaurantPhone;
        this.isPartner = restaurantIsPartner;
        this.itemList = new ArrayList<>();
    }

    public Restaurant() {

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isPartner() {
        return isPartner;
    }

    public void setPartner(boolean partner) {
        isPartner = partner;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", isPartner=" + isPartner +
                ", itemList=" + itemList +
                '}';
    }


}