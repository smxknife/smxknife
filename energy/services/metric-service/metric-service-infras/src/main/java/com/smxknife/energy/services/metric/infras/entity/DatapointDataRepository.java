package com.smxknife.energy.services.metric.infras.entity;

import com.smxknife.energy.services.metric.spi.domain.Datapoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author smxknife
 * 2021/5/17
 */
@Repository
public interface DatapointDataRepository extends JpaRepository<DatapointData, Long> {
	Datapoint findByMetricAndDateTimeAndQuality(String metric, LocalDateTime dateTime, int quality);

	List<Datapoint> findByMetricAndQualityAndDateTimeBetween(String metric, int quality, LocalDateTime startTime, LocalDateTime endTime);
}
