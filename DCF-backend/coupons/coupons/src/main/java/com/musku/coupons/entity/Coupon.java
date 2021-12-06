package com.musku.coupons.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Document(collection = "coupons")
public class Coupon {

	//Attributes
	@Id
	private String id;
	private String companyID;
	private Category category;
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	private int offer;
	private String code;
	
	
	
	
public Coupon(String id, String companyID, Category category, String title, String description, Date startDate,
			Date endDate, int offer, String code) {
		super();
		this.id = id;
		this.companyID = companyID;
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.offer = offer;
		this.code = code;
	}



	//Default constructor
	public Coupon() {
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
		return "Coupon [id=" + id + ", companyID=" + companyID + ", category=" + category + ", title=" + title
				+ ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + ", offer="
				+ offer + ", code=" + code + "]";
	}

	
	
}