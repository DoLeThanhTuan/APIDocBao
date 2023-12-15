package com.dolethanhtuan.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NewsDTO {
	private Integer id;
	private String title;
	private String image;
	private String linkDetail;
	private int countFavourite;
	private int countShare;
	private Date createDate;
	private String author;
	private String fullnameAuthor;
	private CategoryDTO category;

}
