package com.dolethanhtuan.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dolethanhtuan.dto.NewsDTO;
import com.dolethanhtuan.service.INewsService;

@RestController
public class NewsAPI {
	@Autowired
	private INewsService newsService;
	@GetMapping(path= {"api/v1/news/all"})
	public List<NewsDTO> getListNews(){
		return newsService.findAllNews();
	}
	@GetMapping(path= {"api/v1/news/count"})
	public int countNews(){
		return newsService.countNews();
	}
	@GetMapping(path= {"api/v1/news/newest"})
	public NewsDTO getNewsNewest(){
		return newsService.findAllNews().get(0);
	}
	@GetMapping(path="api/v1/news/search/{txtSearch}")
	public List<NewsDTO> searchNews(@PathVariable("txtSearch") String txtSearch){
		return newsService.findAllLikeTitle(txtSearch);
	}
	@GetMapping(path="api/v1/news/{id}")
	public NewsDTO getNewsById(@PathVariable("id") Integer id) {
		if(id == null)
			return null;
		NewsDTO newsDTO = newsService.findOneById(id);
		return newsDTO;
	}
	@GetMapping(path="api/v1/news")
	public List<NewsDTO> getNewsByCategory(@RequestParam("cate_id") Integer cate_id) {
		if(cate_id == null)
			return null;
		return newsService.findAllNewsByCate_id(cate_id);
	}
	@PostMapping(path="api/v1/news")
	public NewsDTO postNews(@RequestBody NewsDTO news) {
		if(news.getId() != null)
			return null;
		return newsService.insertNews(news);
	}
	@PutMapping(path="api/v1/news")
	public NewsDTO updateNews(@RequestBody NewsDTO news) {
		return newsService.updateNews(news);
	}
	@DeleteMapping(path="api/v1/news/{id}")
	public void deleteNews(@PathVariable("id") Integer id) {
		newsService.deleteNews(id);
	}
}
