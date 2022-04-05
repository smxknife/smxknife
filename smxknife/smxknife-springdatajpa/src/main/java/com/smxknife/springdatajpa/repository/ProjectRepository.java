package com.smxknife.springdatajpa.repository;

import com.smxknife.springdatajpa.model.ent.Project;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author smxknife
 * 2020/11/19
 */
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
