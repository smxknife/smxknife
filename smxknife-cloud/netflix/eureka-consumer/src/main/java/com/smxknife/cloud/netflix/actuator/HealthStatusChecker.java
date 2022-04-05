package com.smxknife.cloud.netflix.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;

/**
 * @author smxknife
 * 2021/4/29
 */
@Service
public class HealthStatusChecker implements HealthIndicator {

	private volatile boolean up = true;

	@Override
	public Health health() {
		if (up) {
			return new Health.Builder().withDetail("quality", 192).up().build();
		}
		return new Health.Builder().withDetail("quality", 170).down().build();
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}
}
