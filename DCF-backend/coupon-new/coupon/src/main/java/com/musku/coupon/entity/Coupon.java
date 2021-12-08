package com.musku.coupon.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "coupons")
public class Coupon {


    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";
    //Attributes
    @Id
    private int id;
    private String company;
    private Category category;
    private String couponname;
    private String description;
    private Date startDate;
    private Date endDate;
    private int offer;
    private String code;




    public Coupon(int id, String company, Category category, String title, String description, Date startDate,
                  Date endDate, int offer, String code) {
        super();
        this.id = id;
        this.company = company;
        this.category = category;
        this.couponname = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.offer = offer;
        this.code = code;
    }



    //Default constructor
    public Coupon() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return couponname;
    }

    public void setTitle(String title) {
        this.couponname = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getOffer() {
        return offer;
    }

    public void setOffer(int offer) {
        this.offer = offer;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    //Print Coupon details
    @Override
    public String toString() {
        return "Coupon [id=" + id + ", company=" + company + ", category=" + category + ", title=" + couponname
                + ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + ", offer="
                + offer + ", code=" + code + "]";
    }



}