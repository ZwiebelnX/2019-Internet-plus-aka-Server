package com.aka.server.akaminiprogramserver.service;

import com.aka.server.akaminiprogramserver.DTO.activity.ActivityDTO;
import com.aka.server.akaminiprogramserver.DTO.activity.LeaderDTO;
import com.aka.server.akaminiprogramserver.DTO.activity.ParticipantDTO;
import com.aka.server.akaminiprogramserver.DTO.result.ResponseDataDTO;
import com.aka.server.akaminiprogramserver.DTO.result.ResultDTO;
import com.aka.server.akaminiprogramserver.repo.docker.ActivityRepo;
import com.aka.server.akaminiprogramserver.repo.entity.ActivityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


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
    //创建活动
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
    //参加活动
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
    //获取我发起的活动
    public ResponseDataDTO findByLeader(LeaderDTO leaderDTO){
        List<ActivityEntity> lists=activityRepo.findByLeaderOpenid(leaderDTO.getOpenid());
        ResponseDataDTO response = new ResponseDataDTO();
        response.setSuccess(true);
        response.setResult(lists);
        return response;
    }
}
