package com.smxknife.energy.clouds.ents.entmgr.repository;

import com.smxknife.energy.clouds.ents.entmgr.entity.EntUserMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author smxknife
 * 2021/5/19
 */
@Repository
public interface EntUserMappingRepository extends JpaRepository<EntUserMapping, Long> {
}
