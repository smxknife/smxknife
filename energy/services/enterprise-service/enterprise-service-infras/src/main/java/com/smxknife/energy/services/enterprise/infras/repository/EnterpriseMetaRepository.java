package com.smxknife.energy.services.enterprise.infras.repository;

import com.smxknife.energy.services.enterprise.infras.entity.EnterpriseMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/19
 */
@Repository
public interface EnterpriseMetaRepository extends JpaRepository<EnterpriseMeta, Long> {

	List<EnterpriseMeta> findByCodeIn(List<String> codes);
}
