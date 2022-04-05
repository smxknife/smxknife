package com.smxknife.energy.services.user.infras.repository;

import com.smxknife.energy.services.user.infras.entity.UserMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/19
 */
@Repository
public interface UserMetaRepository extends JpaRepository<UserMeta, Long> {

	List<UserMeta> findByAccountIn(List<String> accounts);
}
