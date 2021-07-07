package com.fitness.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "food")
@NoArgsConstructor
public class Food implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "fdc_id")
    private Long fdc_id;
    @Column(name = "data_type")
    private String data_type = "";


    @Column(name = "description")
    private String description;
    @Column(name = "food_category_id")
    private Long food_category_id;
    @Column(name = "publication_date")
    private Date publication_date;


    public Long getFdc_id() {
        return fdc_id;
    }

    public void setFdc_id(Long fdc_id) {
        this.fdc_id = fdc_id;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        if (data_type != null) this.data_type = data_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null) this.description = description;
    }

    public Long getFood_category_id() {
        return food_category_id;
    }

    public void setFood_category_id(Long food_category_id) {
        if (food_category_id != null)
            this.food_category_id = food_category_id;
    }

    public Date getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(Date publication_date) {
        if (publication_date != null)
            this.publication_date = publication_date;
    }
}
