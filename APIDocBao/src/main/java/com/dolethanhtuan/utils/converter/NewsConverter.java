package com.dolethanhtuan.utils.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dolethanhtuan.dto.NewsDTO;
import com.dolethanhtuan.entity.NewsEntity;
import com.dolethanhtuan.repository.CategoryRepository;
import com.dolethanhtuan.repository.UserRepository;
@Component
public class NewsConverter {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	public NewsDTO toDTO(NewsEntity newsE) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		NewsDTO newsDTO = new NewsDTO();
		newsDTO = modelMapper.map(newsE, NewsDTO.class);
		if(newsE.getCreateBy() != null) {
			newsDTO.setAuthor(newsE.getCreateBy().getUsername());
			newsDTO.setFullnameAuthor(newsE.getCreateBy().getFullname());
		}
		return newsDTO;
	}
	public NewsEntity toEntity(NewsEntity newsEOld,NewsDTO newsD) {
		if(newsD.getTitle() != null && !newsD.getTitle().isEmpty()) {
			newsEOld.setTitle(newsD.getTitle());
		}
		if(newsD.getImage() != null) {
			newsEOld.setImage(newsD.getImage());
		}
		if(newsD.getLinkDetail() != null && !newsD.getLinkDetail().isEmpty()) {
			newsEOld.setLinkDetail(newsD.getLinkDetail());
		}
		if(newsD.getCategory() != null) {
			newsEOld.setCategory(categoryRepository.findOneByCateCode(newsD.getCategory().getCateCode()));
		}
		return newsEOld;
	}
	public NewsEntity toEntity(NewsDTO newsD) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		NewsEntity newsE = modelMapper.map(newsD, NewsEntity.class);
		if(newsD.getAuthor() != null)
			newsE.setCreateBy(userRepository.findOneByUsername(newsD.getAuthor()));
		if(newsD.getCategory() != null)
			newsE.setCategory(categoryRepository.findOneByCateCode(newsD.getCategory().getCateCode()));
		return newsE;
	}
}
