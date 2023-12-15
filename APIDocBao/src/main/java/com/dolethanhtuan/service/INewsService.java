package com.dolethanhtuan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dolethanhtuan.dto.NewsDTO;

@Service
public interface INewsService {
	int countNews();
	List<NewsDTO> findAllNews();
	List<NewsDTO> findAllNewsByCate_id(Integer id);
	NewsDTO findOneById(int id);
	NewsDTO insertNews(NewsDTO newsD);
	NewsDTO updateNews(NewsDTO newsD);
	void deleteNews(int idNews);
	List<NewsDTO> findAllLikeTitle(String txtSearch);
	
}
