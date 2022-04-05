package com.smxknife.energy.services.metric.infras.entity;

import com.smxknife.energy.services.metric.spi.domain.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/12
 */
@Repository
public interface MetricMetaRepository extends JpaRepository<MetricMeta, Long> {
	List<Metric> findByDatasourceUid(String datasourceUid);

	Metric findByMetric(String metric);

	List<Metric> findByCode(String code);
}
