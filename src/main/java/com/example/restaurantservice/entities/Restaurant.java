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
    private String address;
    private String city;
    private String category;

    @OneToMany(mappedBy = "restaurant")
    private List<Item> itemList;


    public Restaurant(String restaurantName, String restaurantPhone, boolean restaurantIsPartner , String restaurantAddress, String restaurantCity, String restaurantCategory) {
        this.name = restaurantName;
        this.phone = restaurantPhone;
        this.isPartner = restaurantIsPartner;
        this.address = restaurantAddress;
        this.city = restaurantCity;
        this.category = restaurantCategory;
        this.itemList = new ArrayList<>();
    }

    public Restaurant() {

    }

    public Restaurant(long l, String restaurant1, String address1, List<Item> itemList) {
        this.id = l;
        this.name = restaurant1;
        this.phone = address1;
        this.itemList = itemList;

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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