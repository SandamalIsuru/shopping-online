package com.example.shoppingcart.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
 
@Entity
@Table(name = "Products")
public class Product implements Serializable {
 
    private static final long serialVersionUID = -1000119078147252957L;
 
    @Id
    @Column(name = "Code", length = 20, nullable = false)
    private String code;
 
    @Column(name = "Name", length = 255, nullable = false)
    private String name;
 
    @Column(name = "Price", nullable = false)
    private double price;
 
    @Lob
    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;
     
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Create_Date", nullable = false)
    private Date createDate;
    
    @ManyToOne(targetEntity = SubCategory.class)
    @JoinColumn(name = "SUB_CATEGORY_ID", nullable = false, //
            foreignKey = @ForeignKey(name = "FK_SUB_CATERGORY_ID"))
	private SubCategory subCategory;
    
    @ManyToOne(targetEntity = Color.class)
    @JoinColumn(name = "COLOR_ID", nullable = false, //
            foreignKey = @ForeignKey(name = "FK_COLOR_ID"))
	private Color color;
    
    @ManyToOne(targetEntity = Size.class)
    @JoinColumn(name = "SIZE_ID", nullable = false, //
            foreignKey = @ForeignKey(name = "FK_SIZE_ID"))
	private Size size;
 
    public Product() {
    }
 
    public String getCode() {
        return code;
    }
 
    public void setCode(String code) {
        this.code = code;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public double getPrice() {
        return price;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }
 
    public Date getCreateDate() {
        return createDate;
    }
 
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
 
    public byte[] getImage() {
        return image;
    }
 
    public void setImage(byte[] image) {
        this.image = image;
    }

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size category) {
		this.size = category;
	}

	@Override
	public String toString() {
		return "Product [code=" + code + ", name=" + name + ", price=" + price + ", image=" + Arrays.toString(image)
				+ ", createDate=" + createDate + ", subCategory=" + subCategory + ", color=" + color + ", category="
				+ size + "]";
	}
 
}