package com.aka.server.akaminiprogramserver.controller;

import com.aka.server.akaminiprogramserver.DTO.activity.ActivityDTO;
import com.aka.server.akaminiprogramserver.DTO.result.ResponseDataDTO;
import com.aka.server.akaminiprogramserver.service.ActivityService;
import com.aka.server.akaminiprogramserver.util.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


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
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    //创建活动
    @PostMapping(value = "/activity")
    @ResponseBody
    public ResponseDataDTO postActivity(@RequestBody String jsonString) {
        ActivityDTO activityDTO;
        try {
            activityDTO = JsonMapper.getMapper().readValue(jsonString, ActivityDTO.class);
        } catch (Exception e) {
            return new ResponseDataDTO("json格式错误！");
        }

        ResponseDataDTO responseData = new ResponseDataDTO();
        if (activityService.createActivity(activityDTO)) {
            responseData.setSuccess(true);
        } else {
            responseData.setReason("数据存储失败！请稍后再试");
        }
        return responseData;
    }

    //加入活动
    @PostMapping(value = "/activity/{activityId}")
    @ResponseBody
    public ResponseDataDTO joinActivity(@RequestBody String jsonString, @PathVariable long activityId) {
        Map requestParams;
        try {
            requestParams = JsonMapper.getMapper().readValue(jsonString, Map.class);
        } catch (Exception e) {
            return new ResponseDataDTO("json格式错误！");
        }
        ResponseDataDTO responseData = new ResponseDataDTO();
        int flag = activityService.joinActivity(activityId, (String)requestParams.get("openid"));
        if (flag == 1) {
            responseData.setSuccess(true);
        } else if (flag == -1) {
            responseData.setReason("没有该活动请重新查询");
        } else {
            responseData.setReason("加入失败！请稍后再试");
        }
        return responseData;
    }


    //删除活动
    @DeleteMapping(value = "/activity/{activityId}")
    @ResponseBody
    public ResponseDataDTO delete(@RequestBody String jsonString, @PathVariable long activityId) {
        Map requestParams;
        try {
            requestParams = JsonMapper.getMapper().readValue(jsonString, Map.class);
        } catch (Exception e) {
            return new ResponseDataDTO("json格式错误！");
        }
        ResponseDataDTO responseData = new ResponseDataDTO();
        int flag = activityService.deleteActivity(activityId, (String)requestParams.get("openid"));
        if (flag == 1) {
            responseData.setSuccess(true);
        } else if (flag == -1) {
            responseData.setReason("不存在该活动，请重新搜索！");
        } else if (flag == -2) {
            responseData.setReason("您无权限删除该活动！");
        } else {
            responseData.setReason("数据存储失败！请稍后再试");
        }
        return responseData;
    }

    //获取我发起的活动
    @GetMapping(value = "/activity/myactivity")
    public ResponseDataDTO findByLeader(@RequestBody String jsonString) {
        Map requestParams;
        try {
            requestParams = JsonMapper.getMapper().readValue(jsonString, Map.class);
        } catch (Exception e) {
            return new ResponseDataDTO("json格式错误！");
        }
        return activityService.findActivityICreated((String)requestParams.get("openid"));
    }

    //获取我参与的活动
    @GetMapping(value = "/activity/participateactivity")
    public ResponseDataDTO findByParticipant(@RequestParam String openid) {
        return activityService.findActivitiesByParticipant(openid);
    }

    //获取活动列表
    @GetMapping(value = "/activity/activitylist")
    public ResponseDataDTO getActivityList() {
        return activityService.getActivityList();
    }
}
