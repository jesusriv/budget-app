package com.jesusrivera.budgettest.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="subcategories")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property="id")
public class SubCategory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=4)
	private String name;
	
	private double budgeted;
	
	private double activity;
	
	private double available;
	
	@JsonBackReference
	@OneToMany
	private List<Transaction> transactions;
	
	@JsonManagedReference
	@OneToOne(mappedBy="subcategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Goal goal;
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY)
	private Category category;
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY)
	private Budget budget;
	
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	
	public SubCategory() {}
	
	public SubCategory(String name, double budgeted, double available) {
		this.name = name;
		this.budgeted = budgeted;
		this.available = available;
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

	public double getBudgeted() {
		return budgeted;
	}

	public void setBudgeted(double budgeted) {
		this.budgeted = budgeted;
	}

	public double getActivity() {
		return activity;
	}

	public void setActivity(double activity) {
		this.activity = activity;
	}

	public double getAvailable() {
		return available;
	}

	public void setAvailable(double available) {
		this.available = available;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Goal getGoal() {
		return goal;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
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
