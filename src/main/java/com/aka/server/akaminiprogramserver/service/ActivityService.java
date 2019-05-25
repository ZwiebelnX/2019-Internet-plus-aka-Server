package com.aka.server.akaminiprogramserver.service;

import com.aka.server.akaminiprogramserver.DTO.activity.ActivityDTO;
import com.aka.server.akaminiprogramserver.DTO.activity.ParticipantDTO;
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
    public boolean postActivity(ActivityDTO activityDTO){
        ActivityEntity newActivity=new ActivityEntity();
        try {
            newActivity.setLeaderOpenid(activityDTO.getOpenid());
            newActivity.setName(activityDTO.getName());
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            try {
                ts = Timestamp.valueOf(activityDTO.getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
            newActivity.setStartTime(ts);
            newActivity.setLocation(activityDTO.getLocation());
            int maxTmp=Integer.parseInt(activityDTO.getMaxPeopleCounting());
            newActivity.setMaxPeopleCounting(maxTmp);
            newActivity.setComment(activityDTO.getComment());
            newActivity.setPhone(activityDTO.getPhone());
            activityRepo.save(newActivity);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public int joinActivity(ParticipantDTO participantDTO){
        int tmpId=Integer.parseInt(participantDTO.getActivityId());
        ActivityEntity nowActivity=activityRepo.findById(tmpId);
        if(nowActivity==null){
            return -1;
        }
        try{
            int count=nowActivity.getNowPeopleCounting()+1;
            nowActivity.setNowPeopleCounting(count);
            String nowParticipant=nowActivity.getParticipant()+participantDTO.getOpenid()+";";
            nowActivity.setParticipant(nowParticipant);
            activityRepo.save(nowActivity);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }
}
