package com.gdinant.unicodedemo;

import org.springframework.stereotype.Service;

@Service
public class MyEntityService {

	private final MyEntityRepository demoRepository;

	public MyEntityService(MyEntityRepository demoRepository) {
		this.demoRepository = demoRepository;
	}

	public MyEntity find(Long id) {
		return demoRepository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
	}

	public MyEntity save(String comment) {

		var entity = MyEntity.builder().comment(comment).nComment(comment).build();

		return demoRepository.save(entity);
	}

}
