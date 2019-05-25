package com.aka.server.akaminiprogramserver.controller;
import com.aka.server.akaminiprogramserver.DTO.activity.ActivityDTO;
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
}
