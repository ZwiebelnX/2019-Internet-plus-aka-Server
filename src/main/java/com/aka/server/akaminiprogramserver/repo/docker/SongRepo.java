package com.aka.server.akaminiprogramserver.repo.docker;

import com.aka.server.akaminiprogramserver.repo.entity.SongEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * <p>Title: SongRepo</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019版权</p>
 * <p>Company: </p>
 *
 * @author Zwiebeln_Chan
 * @version V1.0
 * @date 2019/5/24 20:36
 */
public interface SongRepo extends CrudRepository<SongEntity, Long> {
    List<SongEntity> findAllByCreatorOpenid(String openid);
    List<SongEntity> findAll();
}
