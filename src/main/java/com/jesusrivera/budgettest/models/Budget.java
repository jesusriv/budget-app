package com.jesusrivera.budgettest.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="budgets")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property="id")
public class Budget {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=2)
	private String name;
	
	private double totalInBudget;

	@JsonBackReference
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "users_id")
	private User defaultUser;
	
	@JsonBackReference
	@OneToMany(mappedBy="budget", fetch = FetchType.LAZY)
	private List<SubCategory> subcategories;
	
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
	
	public Budget() {
		this.name = "My Budget";
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
	
	public List<SubCategory> getSubCategories() {
		return subcategories;
	}

	public void setSubCategories(List<SubCategory> subcategories) {
		this.subcategories = subcategories;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public double getTotalInBudget() {
		return totalInBudget;
	}

	public void setTotalInBudget(double totalInBudget) {
		this.totalInBudget = totalInBudget;
	}
	
	public List<SubCategory> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<SubCategory> subcategories) {
		this.subcategories = subcategories;
	}
	
	public User getDefaultUser() {
		return defaultUser;
	}

	public void setDefaultUser(User defaultUser) {
		this.defaultUser = defaultUser;
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
	
	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate(){
		this.updatedAt = new Date();
	}
}
