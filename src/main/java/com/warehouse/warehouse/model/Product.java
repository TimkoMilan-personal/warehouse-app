package com.warehouse.warehouse.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
@Lazy
public class Product{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "place")
    private String place;

    @Column(name = "idCode")
    private String idCode;

    @Column(name = "note")
    private String note;

    @Column(name = "count")
    private int count;

    @JsonIgnore
    @ManyToOne
    private Category category;

    @JsonCreator
    public Product(@JsonProperty("id") Long id) {
        this.id = id;
    }

    public Product() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", place='" + place + '\'' +
                ", idCode='" + idCode + '\'' +
                ", note='" + note + '\'' +
                ", count=" + count +
                ", category=" + category +
                '}';
    }
}
