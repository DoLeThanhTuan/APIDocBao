package com.dolethanhtuan.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.dolethanhtuan.dto.NewsDTO;
import com.dolethanhtuan.entity.NewsEntity;
import com.dolethanhtuan.entity.NotificationEntity;
import com.dolethanhtuan.entity.NotificationId;
import com.dolethanhtuan.entity.UserEntity;
import com.dolethanhtuan.repository.NewsRepository;
import com.dolethanhtuan.repository.NotificationRepository;
import com.dolethanhtuan.service.INewsService;
import com.dolethanhtuan.utils.converter.NewsConverter;

@Component
public class NewsServiceImpl implements INewsService{
	@Autowired
	private NewsRepository newsRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private NewsConverter newsConverter;
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Override
	public int countNews() {
		return newsRepository.findAll().size();
	}
	
	@Override
	public List<NewsDTO> findAllNews() {
		List<NewsDTO> listNewsD = new ArrayList<>();
		for (NewsEntity newsE : newsRepository.findAll(Sort.by(Sort.Direction.DESC,"createDate"))) {
			NewsDTO newsD = newsConverter.toDTO(newsE);
			listNewsD.add(newsD);
		}
		return listNewsD;
	}
	@Override
	public List<NewsDTO> findAllNewsByCate_id(Integer cate_id) {
		List<NewsEntity> listNewsE = newsRepository.findAllByCategory_id(cate_id);
		List<NewsDTO> listNewsD = new ArrayList<>();
		for (NewsEntity newsE : listNewsE) {
			listNewsD.add(newsConverter.toDTO(newsE));
		}
		return listNewsD;
	}
	@Override
	public NewsDTO findOneById(int id) {
		NewsEntity newsE = newsRepository.findOneById(id);
		if(newsE == null)
			return null;
		return newsConverter.toDTO(newsE);
	}
	@Override
	public NewsDTO insertNews(NewsDTO newsD) {
		NewsEntity newsE = new NewsEntity();
		newsE = newsConverter.toEntity(newsD);
		newsE.setCreateDate(new Date());
		newsE = newsRepository.save(newsE);
		// trong newsE nay co category 
		// sau do dung category lay danh sach user quan tam den cate nay
		List<UserEntity> listUser = newsE.getCategory().getUsers();
		// insert vao bang noti du lieu news cho tung user
		for (UserEntity userEntity : listUser) {
			notificationRepository.save(new NotificationEntity(new NotificationId(userEntity, newsE), 1,new Date()));
		}
		return newsConverter.toDTO(newsE);
	}

	@Override
	public NewsDTO updateNews(NewsDTO newsD) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		NewsEntity newsEOld = newsRepository.findOneById(newsD.getId());
		newsEOld = newsConverter.toEntity(newsEOld, newsD);
		newsEOld = newsRepository.save(newsEOld);
		return newsConverter.toDTO(newsEOld);
	}

	@Override
	public void deleteNews(int idNews) {
		newsRepository.deleteById(idNews);
	}
	@Override
	public List<NewsDTO> findAllLikeTitle(String txtSearch) {
		List<NewsEntity> listNewsE = newsRepository.findByTitleContaining(txtSearch);
		List<NewsDTO> listNewsD = new ArrayList<>();
		for (NewsEntity newsEntity : listNewsE) {
			listNewsD.add(newsConverter.toDTO(newsEntity));
		}
		return listNewsD;
	}
}
