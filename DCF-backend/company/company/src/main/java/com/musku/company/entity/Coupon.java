package com.musku.company.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;



import java.util.Date;


@Document(collection = "couponsdb")
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
    private String imagename;

    private String imagetype;

    private byte[] image;
    private String url;
    //private String code;

    private int cost;
    



    public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getUrl() {
        return url;
    }

    public Coupon() {
		super();
	}

	public Coupon(String code, String company, Category category, String couponname, String description, Date startDate,
			Date endDate, int offer, String imagename, String imagetype, byte[] image, String url,int cost) {
		super();
		this.code = code;
		this.company = company;
		this.category = category;
		this.couponname = couponname;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.offer = offer;
		this.imagename = imagename;
		this.imagetype = imagetype;
		this.image = image;
		this.url = url;
		this.cost=cost;
	}

	public void setUrl(String url) {
        this.url = url;
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


    public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public String getImagetype() {
		return imagetype;
	}

	public void setImagetype(String imagetype) {
		this.imagetype = imagetype;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

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