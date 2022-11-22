package com.ideas2It.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * The BaseModel class have id, created at, updated at, isDeleted attributes 
 * This class contain getter and setter method for BaseModel attributes
 * This class attribute common for all other classes 
 *
 * @version 1.0
 * @author arunkumar
 */
@MappedSuperclass
public class BaseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name = "is_deleted", columnDefinition = "tinyint default 0")
    private boolean isDeleted;
    
	@CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;
    
	@UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}