package com.gdinant.unicodedemo.dao;

import com.gdinant.unicodedemo.dao.model.MyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyEntityDao extends JpaRepository<MyEntity, Long> {}
