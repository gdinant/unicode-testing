package com.gdinant.unicodedemo.service;

import com.gdinant.unicodedemo.dao.model.MyEntity;
import com.gdinant.unicodedemo.dao.MyEntityDao;
import org.springframework.stereotype.Service;

@Service
public class MyEntityService {

	private final MyEntityDao demoRepository;

	public MyEntityService(MyEntityDao demoRepository) {
		this.demoRepository = demoRepository;
	}

	public MyEntity find(Long id) {
		return demoRepository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
	}

	public MyEntity save(String comment) {

		var truncatedComment = Strings.truncate(comment, MyEntity.COMMENT_MAX_LENGTH);

		var entity = MyEntity.builder().comment(truncatedComment).nComment(truncatedComment).build();

		return demoRepository.save(entity);
	}



}
