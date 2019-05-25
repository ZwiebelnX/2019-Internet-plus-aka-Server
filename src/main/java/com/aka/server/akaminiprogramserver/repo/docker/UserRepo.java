package com.aka.server.akaminiprogramserver.repo.docker;

import com.aka.server.akaminiprogramserver.repo.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * <p>Title: UserRepo</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019版权</p>
 * <p>Company: </p>
 *
 * @author Zwiebeln_Chan
 * @version V1.0
 * @date 2019/5/25 17:03
 */
public interface UserRepo extends CrudRepository<UserEntity, Long> {
    UserEntity findByOpenid(String openid);
}
