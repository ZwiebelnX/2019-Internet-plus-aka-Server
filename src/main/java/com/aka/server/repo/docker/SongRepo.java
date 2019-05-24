package com.aka.server.repo.docker;

import com.aka.server.repo.entity.SongEntity;
import org.springframework.data.repository.CrudRepository;

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
}
