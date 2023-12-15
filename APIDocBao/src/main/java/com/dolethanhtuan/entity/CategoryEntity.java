package com.dolethanhtuan.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="category")
public class CategoryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="cateCode",unique = true,nullable = false)
	private String cateCode;
	@Column(name="cateName",nullable = false)
	private String cateName;
	@Column(name="cateDescription",columnDefinition = "text")
	private String cateDescription;
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
	private List<NewsEntity> listNews = new ArrayList<>();
	
	@ManyToMany(mappedBy = "listCateCare")
	private List<UserEntity> users = new ArrayList<>();
}
