package com.example.restaurantservice.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class RestaurantDTO extends RepresentationModel<RestaurantDTO> {
    private Long id;
    private String name;
    private String phone;
    private boolean isPartner;

    public RestaurantDTO() {
    }

    public RestaurantDTO(Long id, String name, String phone, boolean isPartner) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.isPartner = isPartner;
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
}
