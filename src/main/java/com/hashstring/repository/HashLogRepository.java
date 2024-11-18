package com.hashstring.repository;

import com.hashstring.entity.HashLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashLogRepository extends CrudRepository<HashLog, Long> { }
