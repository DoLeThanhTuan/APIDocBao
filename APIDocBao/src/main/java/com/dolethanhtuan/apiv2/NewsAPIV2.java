package com.dolethanhtuan.apiv2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dolethanhtuan.dto.NewsDTO;
import com.dolethanhtuan.response.ResponseSuccess;
import com.dolethanhtuan.service.INewsService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@Validated
public class NewsAPIV2 {
	@Autowired
	private INewsService newsService;
	@GetMapping(path= {"api/news/all/v2"})
	public ResponseSuccess<List<NewsDTO>> getListNews(){
		return new ResponseSuccess<>(HttpStatus.OK.value(),"Get list success",newsService.findAllNews());
	}
	@GetMapping(path= {"api/news/count/v2"})
	public ResponseSuccess<Integer> countNews(){
		return new ResponseSuccess<>(HttpStatus.OK.value(),"Success",newsService.countNews());
	}
	@GetMapping(path= {"api/news/newest/v2"})
	public ResponseSuccess<NewsDTO> getNewsNewest(){
		return new ResponseSuccess<NewsDTO>(HttpStatus.OK.value(),"Success",newsService.findAllNews().get(0));
	}
	@GetMapping(path="api/news/search/v2/{txtSearch}")
	public ResponseSuccess<List<NewsDTO>> searchNews(@PathVariable("txtSearch") String txtSearch){
		return new ResponseSuccess<>(HttpStatus.OK.value(),"Success",newsService.findAllLikeTitle(txtSearch));
	}
	@GetMapping(path="api/news/v2/{id}")
	public ResponseSuccess<NewsDTO> getNewsById(@PathVariable("id") @Min(1) Integer id) {
		if(id == null)
			return new ResponseSuccess<>(HttpStatus.BAD_REQUEST.value(), "Error");
		NewsDTO newsDTO = newsService.findOneById(id);
		return new ResponseSuccess<>(HttpStatus.OK.value(),"Success",newsDTO);
	}
	@GetMapping(path="api/news/v2")
	public ResponseSuccess<List<NewsDTO>> getNewsByCategory(@RequestParam("cate_id") Integer cate_id) {
		if(cate_id == null)
			return new ResponseSuccess<>(HttpStatus.BAD_REQUEST.value(), "Error");
		return new ResponseSuccess<>(HttpStatus.OK.value(),"Success",newsService.findAllNewsByCate_id(cate_id));
	}
	@PostMapping(path="api/news/v2")
	public ResponseSuccess<NewsDTO> postNews(@Valid @RequestBody NewsDTO news) {
		if(news.getId() != null)
			return new ResponseSuccess<>(HttpStatus.BAD_REQUEST.value(), "Error");
		return new ResponseSuccess<>(HttpStatus.OK.value(),"Posted news success",newsService.insertNews(news));
	}
	@PutMapping(path="api/news/v2")
	public ResponseSuccess<NewsDTO> updateNews(@RequestBody NewsDTO news) {
		return new ResponseSuccess<>(HttpStatus.OK.value(),"Updated success",newsService.updateNews(news));
	}
	@DeleteMapping(path="api/news/v2/{id}")
	public ResponseSuccess<?> deleteNews(@PathVariable("id") Integer id) {
		newsService.deleteNews(id);
		return new ResponseSuccess<>(HttpStatus.OK.value(),"Delete "+id+" success");
	}
}
