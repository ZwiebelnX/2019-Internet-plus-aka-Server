package com.aka.server.akaminiprogramserver.repo.docker;

import com.aka.server.akaminiprogramserver.repo.entity.ActivityEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActivityRepo extends CrudRepository<ActivityEntity, Long> {
    ActivityEntity findById(long id);
    List<ActivityEntity> findByLeaderOpenid(String id);
}
