package parking.hfq.com.smartparking.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import parking.hfq.com.smartparking.dto.PublishDTO;
import parking.hfq.com.smartparking.dto.ResultDTO;
import parking.hfq.com.smartparking.mapper.NoticeMapper;
import parking.hfq.com.smartparking.model.Notice;

/**
 * @Created by hfq on 2020/3/19 13:57
 * @used to: 完成发布公告功能
 * @return:
 */
@Controller
public class PublishController {
    @Autowired
    NoticeMapper noticeMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    /**
     * 发布公告
     * @return
     */
    @ResponseBody
    @PostMapping("/publish")
    public Object publishNotice(@RequestBody PublishDTO publishDTO){
        System.out.println(publishDTO.toString());
        Notice notice = new Notice();
        BeanUtils.copyProperties(publishDTO,notice);
        notice.setGmtCreate(System.currentTimeMillis());
        noticeMapper.insertSelective(notice);
        return new ResultDTO(2000, "成功",null);
    }
}
