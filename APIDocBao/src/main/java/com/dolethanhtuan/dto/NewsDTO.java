package com.dolethanhtuan.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NewsDTO implements Serializable {
	private Integer id;
	@NotBlank(message = "title must not blank")
	private String title;
	@NotBlank(message = "image must not blank")
	private String image;
	@NotNull(message = "linkDetails must not blank")
	private String linkDetail;
	private int countFavourite;
	private int countShare;
	private Date createDate;
	private String author;
	private String fullnameAuthor;
	private CategoryDTO category;

}
