package com.aka.server.akaminiprogramserver.service;

import com.aka.server.akaminiprogramserver.DTO.activity.ActivityDTO;
import com.aka.server.akaminiprogramserver.repo.docker.ActivityRepo;
import com.aka.server.akaminiprogramserver.repo.entity.ActivityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


/**
 * <p>Title: ActivityService</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019版权</p>
 * <p>Company: </p>
 *
 * @author Zwiebeln_Chan
 * @version V1.0
 * @date 2019/5/24 20:21
 */

@Service
public class ActivityService {
    private final ActivityRepo activityRepo;
    @Autowired
    public ActivityService(ActivityRepo activityRepo){
        this.activityRepo=activityRepo;
    }
    public boolean postActivity(ActivityDTO activityCTO){
        ActivityEntity newActivity=new ActivityEntity();
        try {
            newActivity.setLeaderOpenid(activityCTO.getOpenid());
            newActivity.setName(activityCTO.getName());
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            try {
                ts = Timestamp.valueOf(activityCTO.getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
            newActivity.setStartTime(ts);
            newActivity.setLocation(activityCTO.getLocation());
            int maxTmp=Integer.parseInt(activityCTO.getMaxPeopleCounting());
            newActivity.setMaxPeopleCounting(maxTmp);
            newActivity.setComment(activityCTO.getComment());
            newActivity.setPhone(activityCTO.getPhone());
            activityRepo.save(newActivity);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
