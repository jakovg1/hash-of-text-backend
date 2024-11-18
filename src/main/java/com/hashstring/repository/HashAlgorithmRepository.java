package com.hashstring.repository;

import com.hashstring.entity.HashAlgorithm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HashAlgorithmRepository extends JpaRepository<HashAlgorithm, Long> {
    Optional<HashAlgorithm> findFirstByOrderByIdDesc();
}
