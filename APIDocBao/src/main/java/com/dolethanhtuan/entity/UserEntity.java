package com.dolethanhtuan.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="[user]")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="username",unique = true,nullable = false)
	private String username;
	@Column(name="password",nullable = false)
	private String password;
	@Column(name="fullname",nullable = false)
	private String fullname;
	@Column(name="status")
	private int status;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role",
	joinColumns = @JoinColumn(name="user_id"),
	inverseJoinColumns = @JoinColumn(name="role_id")
			)
	private List<RoleEntity> roles = new ArrayList<>();
	
	// Danh sach bai bao da tao
	@OneToMany(mappedBy = "createBy",cascade = CascadeType.ALL)
	private List<NewsEntity> news = new ArrayList<>();
	
	// Danh sach bai bao da thich
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "usersFavouriteThis",fetch = FetchType.LAZY)
	private List<NewsEntity> listNewsfavourite = new ArrayList<>();
	
	// Danh sach thong bao
	@OneToMany(mappedBy = "notificationId.user")
	private List<NotificationEntity> listNotification = new ArrayList<>();
	
	// Danh sach danh muc quan tam
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_category",
	joinColumns = @JoinColumn(name="user_id"),
	inverseJoinColumns = @JoinColumn(name="cate_id")
	)
	private List<CategoryEntity> listCateCare = new ArrayList<>();
	
	
	public UserEntity(int id) {
		this.id = id;
	}
	
	
}
