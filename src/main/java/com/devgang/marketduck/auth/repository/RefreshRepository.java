package com.devgang.marketduck.auth.repository;

import com.devgang.marketduck.auth.entity.RefreshEntity;
import org.springframework.data.repository.CrudRepository;

public interface RefreshRepository extends CrudRepository<RefreshEntity, String> {
}
