package com.gdinant.unicodedemo.dao;

import com.gdinant.unicodedemo.dao.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {}
