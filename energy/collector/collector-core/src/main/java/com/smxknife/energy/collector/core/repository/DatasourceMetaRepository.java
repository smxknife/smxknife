package com.smxknife.energy.collector.core.repository;

import com.smxknife.energy.collector.core.entity.DatasourceMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author smxknife
 * 2021/5/12
 */
@Repository
public interface DatasourceMetaRepository extends JpaRepository<DatasourceMeta, Long> {
}
