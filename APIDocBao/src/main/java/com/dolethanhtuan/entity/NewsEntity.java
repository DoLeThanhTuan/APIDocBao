package com.dolethanhtuan.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Table(name="news")
public class NewsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="title",nullable = false,columnDefinition = "text")
	private String title;
	@Column(name="image",columnDefinition = "text")
	private String image;
	@Column(name="linkDetail",columnDefinition = "text")
	private String linkDetail;
	@Column(name="countFavourite")
	private Integer countFavourite;
	@Column(name="countShare")
	private Integer countShare;
	@Column(name="createDate")
	private Date createDate;
	
	// User da tao bai bao
	@ManyToOne()
	@JoinColumn(name="createBy")
	private UserEntity createBy;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private CategoryEntity category;
	
	// Danh sach user da thich bai bao nay
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "user_news_Favourite",
			joinColumns  = @JoinColumn(name="news_id"),
			inverseJoinColumns = @JoinColumn(name="user_id")
			)
	private List<UserEntity> usersFavouriteThis = new ArrayList<>();
	
	// Danh sach thong bao tham chieu den
	@OneToMany(mappedBy = "notificationId.news",cascade = CascadeType.ALL)
	private List<NotificationEntity> listNoti = new ArrayList<>();
	
	public NewsEntity(int id) {
		this.id = id;
	}
	
}
