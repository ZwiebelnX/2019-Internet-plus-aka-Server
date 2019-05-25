package com.aka.server.akaminiprogramserver.repo.docker;

import com.aka.server.akaminiprogramserver.repo.entity.ActivityEntity;
import org.springframework.data.repository.CrudRepository;

public interface ActivityRepo extends CrudRepository<ActivityEntity, Long> {
}
