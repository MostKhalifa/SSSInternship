package com.user.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.user.entity.TesterEntity;

@Repository
public interface TesterEntityRepo extends JpaRepository<TesterEntity, Long>{

}
