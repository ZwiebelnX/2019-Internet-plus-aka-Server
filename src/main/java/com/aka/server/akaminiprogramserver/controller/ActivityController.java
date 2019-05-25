package com.aka.server.akaminiprogramserver.controller;
import com.aka.server.akaminiprogramserver.DTO.activity.ActivityDTO;
import com.aka.server.akaminiprogramserver.DTO.activity.DeleteDTO;
import com.aka.server.akaminiprogramserver.DTO.activity.LeaderDTO;
import com.aka.server.akaminiprogramserver.DTO.activity.ParticipantDTO;
import com.aka.server.akaminiprogramserver.DTO.result.ResponseDataDTO;
import com.aka.server.akaminiprogramserver.service.ActivityService;
import com.aka.server.akaminiprogramserver.util.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>Title: ActivityController</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019版权</p>
 * <p>Company: </p>
 *
 * @author Zwiebeln_Chan
 * @version V1.0
 * @date 2019/5/24 20:20
 */

@RestController
public class ActivityController {
    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService){
        this.activityService=activityService;
    }
    //创建活动
    @RequestMapping(value = "/activity")
    @ResponseBody
    public ResponseDataDTO postActivity(@RequestBody String jsonString) {
        ActivityDTO activityDTO;
        try{
            activityDTO = JsonMapper.getMapper().readValue(jsonString, ActivityDTO.class);
        } catch (Exception e){
            return new ResponseDataDTO("json格式错误！");
        }

        ResponseDataDTO responseData = new ResponseDataDTO();
        if(activityService.postActivity(activityDTO)){
            responseData.setSuccess(true);
        }
        else{
            responseData.setReason("数据存储失败！请稍后再试");
        }
        return responseData;
    }
    //加入活动
    @RequestMapping(value = "/activity/{activityId}")
    @ResponseBody
    public ResponseDataDTO joinActivity(@RequestBody String jsonString){
        ParticipantDTO participantDTO;
        try{
            participantDTO = JsonMapper.getMapper().readValue(jsonString, ParticipantDTO.class);
        } catch (Exception e){
            return new ResponseDataDTO("json格式错误！");
        }
        ResponseDataDTO responseData = new ResponseDataDTO();
        int flag=activityService.joinActivity(participantDTO);
        if(flag==1){
            responseData.setSuccess(true);
        }
        else if(flag==-1){
            responseData.setReason("没有该活动请重新查询");
        }
        else{
            responseData.setReason("加入失败！请稍后再试");
        }
        return responseData;
    }
    //删除活动
    @RequestMapping(value="/activity/{id}")
    @ResponseBody
    public ResponseDataDTO delete(@RequestBody String jsonString) {
        DeleteDTO deleteDTO;
        try{
            deleteDTO = JsonMapper.getMapper().readValue(jsonString, DeleteDTO.class);
        } catch (Exception e){
            return new ResponseDataDTO("json格式错误！");
        }
        ResponseDataDTO responseData = new ResponseDataDTO();
        int flag=activityService.delete(deleteDTO);
        if(flag==1){
            responseData.setSuccess(true);
        }
        else if(flag==-1){
            responseData.setReason("不存在该活动，请重新搜索！");
        }
        else if(flag==-2){
            responseData.setReason("您无权限删除该活动！");
        }
        else{
            responseData.setReason("数据存储失败！请稍后再试");
        }
        return responseData;
    }
    //获取我发起的活动
    @RequestMapping(value="/activity/myactivity")
    @ResponseBody
    public ResponseDataDTO findByLeader(@RequestBody String jsonString){
        LeaderDTO leaderDTO;
        try{
           leaderDTO=JsonMapper.getMapper().readValue(jsonString, LeaderDTO.class);
        }catch (Exception e){
            return new ResponseDataDTO("json格式错误！");
        }
        return activityService.findByLeader(leaderDTO);
    }
}
