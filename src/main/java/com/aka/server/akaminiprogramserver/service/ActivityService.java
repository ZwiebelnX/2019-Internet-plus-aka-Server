package com.aka.server.akaminiprogramserver.service;

import com.aka.server.akaminiprogramserver.DTO.activity.ActivityDTO;
import com.aka.server.akaminiprogramserver.DTO.result.ResponseDataDTO;
import com.aka.server.akaminiprogramserver.repo.docker.ActivityRepo;
import com.aka.server.akaminiprogramserver.repo.entity.ActivityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.LinkedList;
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
    public ActivityService(ActivityRepo activityRepo) {
        this.activityRepo = activityRepo;
    }

    //创建活动
    @Transactional
    public boolean createActivity(ActivityDTO activityDTO) {
        ActivityEntity newActivity = new ActivityEntity();
        try {
            newActivity.setCreatorOpenid(activityDTO.getCreatorOpenid());
            newActivity.setCreatorNickname(activityDTO.getCreatorNickname());
            newActivity.setActivityName(activityDTO.getName());
            newActivity.setStartTime(activityDTO.getTime());
            newActivity.setLocation(activityDTO.getLocation());
            newActivity.setMaxPeopleCounting(Integer.parseInt(activityDTO.getMaxPeopleCounting()));
            newActivity.setNowPeopleCounting(0);
            newActivity.setComment(activityDTO.getComment());
            newActivity.setPhone(activityDTO.getPhone());
            newActivity.setParticipant("");

            activityRepo.save(newActivity);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //参加活动
    public int joinActivity(long activityId, String openid) {
        ActivityEntity nowActivity = activityRepo.findById(activityId);
        if (nowActivity == null) {
            return -1;
        }
        try {
            int count = nowActivity.getNowPeopleCounting() + 1;
            nowActivity.setNowPeopleCounting(count);
            String nowParticipant = nowActivity.getParticipant() + openid + ";";
            nowActivity.setParticipant(nowParticipant);
            activityRepo.save(nowActivity);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //删除活动
    public int deleteActivity(long activityId, String openid) {
        ActivityEntity nowActivity = activityRepo.findById(activityId);
        if (nowActivity == null) {
            return -1;
        }
        String leaderId = nowActivity.getCreatorOpenid();
        if (!leaderId.equals(openid)) {
            return -2;
        }
        try {
            activityRepo.delete(nowActivity);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //获取我发起的活动
    public ResponseDataDTO findActivityICreated(String openid) {
        List<ActivityEntity> activityEntityList = activityRepo.findByCreatorOpenid(openid);
        ResponseDataDTO response = new ResponseDataDTO();
        response.setSuccess(true);
        response.setResult(createActivityDTOs(activityEntityList));
        return response;
    }

    //获取我参与的活动
    public ResponseDataDTO findActivitiesByParticipant(String openid){
        List<ActivityEntity> activityEntityList = activityRepo.findByParticipantContains(openid);

        ResponseDataDTO response = new ResponseDataDTO();
        response.setSuccess(true);
        response.setResult(createActivityDTOs(activityEntityList));
        return response;
    }

    //获取活动列表
    public ResponseDataDTO getActivityList(){
        List<ActivityEntity> activityEntityList = activityRepo.findAll();//获取所有
        ResponseDataDTO response = new ResponseDataDTO();
        response.setSuccess(true);
        response.setResult(createActivityDTOs(activityEntityList));
        return response;
    }

    private List<ActivityDTO> createActivityDTOs(List<ActivityEntity> activityEntityList){
        List<ActivityDTO> activityDTOList = new LinkedList<>();
        for(ActivityEntity activityEntity : activityEntityList){
            ActivityDTO activityDTO = new ActivityDTO();
            activityDTO.setActivityId(Long.toString(activityEntity.getId()));
            activityDTO.setCreatorOpenid(activityEntity.getCreatorOpenid());
            activityDTO.setCreatorNickname(activityEntity.getCreatorNickname());
            activityDTO.setName(activityEntity.getActivityName());
            activityDTO.setTime(activityEntity.getStartTime());
            activityDTO.setLocation(activityEntity.getLocation());
            activityDTO.setPhone(activityEntity.getPhone());
            activityDTO.setMaxPeopleCounting(Long.toString(activityEntity.getMaxPeopleCounting()));
            activityDTO.setNowPeopleCounting(Long.toString(activityEntity.getNowPeopleCounting()));
            activityDTO.setParticipant(activityEntity.getParticipant().split(";"));
            activityDTO.setComment(activityEntity.getComment());
            activityDTOList.add(activityDTO);
        }

        return activityDTOList;
    }
}
