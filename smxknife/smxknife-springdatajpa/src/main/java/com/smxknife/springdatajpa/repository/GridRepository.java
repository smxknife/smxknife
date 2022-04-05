package com.smxknife.springdatajpa.repository;

import com.smxknife.springdatajpa.model.grid.Grid;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author smxknife
 * 2020/11/19
 */
public interface GridRepository extends JpaRepository<Grid, Long> {
}
