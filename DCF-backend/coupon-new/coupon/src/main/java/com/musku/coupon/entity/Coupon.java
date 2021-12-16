package com.musku.coupon.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "coupons")
public class Coupon {


//    @Transient
//    public static final String SEQUENCE_NAME = "user_sequence";
    //Attributes
    @Id
    private String code;
    private String company;
    private Category category;
    @Indexed(unique = true)
    private String couponname;
    private String description;
    private Date startDate;
    private Date endDate;
    private int offer;
    //private String code;




    public Coupon( String code,String company, Category category, String couponname, String description, Date startDate,
                  Date endDate, int offer) {
        super();
        //this.id = id;
        this.company = company;
        this.category = category;
        this.couponname = couponname;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.offer = offer;
        this.code = code;
    }



    //Default constructor
    public Coupon() {
    }

    public Coupon(String code) {
        this.code=code;
    }

    public Coupon(String code, String company,String couponname) {
        this.code=code;
        this.company = company;
        this.couponname=couponname;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

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
        return "Coupon{" +
                "code='" + code + '\'' +
                ", company='" + company + '\'' +
                ", category=" + category +
                ", couponname='" + couponname + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", offer=" + offer +
                '}';
    }

    public String getCouponname() {
        return couponname;
    }

    public void setCouponname(String couponname) {
        this.couponname = couponname;
    }
}