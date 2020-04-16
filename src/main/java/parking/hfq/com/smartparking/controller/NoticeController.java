package parking.hfq.com.smartparking.controller;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import parking.hfq.com.smartparking.dto.CommentDTO;
import parking.hfq.com.smartparking.dto.ResultDTO;
import parking.hfq.com.smartparking.enums.CommentTypeEnum;
import parking.hfq.com.smartparking.mapper.CommentMapper;
import parking.hfq.com.smartparking.mapper.NoticeMapper;
import parking.hfq.com.smartparking.model.Comment;
import parking.hfq.com.smartparking.model.CommentExample;
import parking.hfq.com.smartparking.model.Notice;
import parking.hfq.com.smartparking.model.NoticeExample;

import java.util.List;

/**
 * @Created by hfq on 2020/4/16 18:02
 * @used to: 公告详情页
 * @return:
 */
@Controller
public class NoticeController {
    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private CommentMapper commentMapper;

    @GetMapping("/notices")
    public String notice(
            @RequestParam(name = "page" ,defaultValue = "1",required = false) Integer page,
            @RequestParam(name = "size" ,defaultValue = "5",required = false) Integer size,
            Model model)
    {
        NoticeExample example = new NoticeExample();
        example.setOrderByClause("gmt_create desc");
        List<Notice> notices = noticeMapper.selectByExampleWithRowbounds(example, new RowBounds(size * (page - 1), size));
        model.addAttribute("noticeList",notices);
        return "notices";
    }

    /**
     * 获取某条通知的全部回复
     * @param noticeId
     * @param model
     * @return
     */
    @GetMapping("/noticeDetail/{noticeId}")
    public String noticeDetail(@PathVariable(name = "noticeId") Integer noticeId,
                               Model model){
        Notice notice = noticeMapper.selectByPrimaryKey(noticeId);
        model.addAttribute("notice",notice);

        CommentExample example = new CommentExample();
        example.createCriteria()
                .andTypeEqualTo(CommentTypeEnum.FIRST_CLASS_COMMENT.getType())
                .andParentIdEqualTo(noticeId);
        example.setOrderByClause("gmt_create desc");
        List<Comment> commentList = commentMapper.selectByExample(example);
        model.addAttribute("commentList",commentList);
        return "noticeDetail";
    }

    /**
     * 获取某条一级回复的二级回复
     * @param comemntId
     * @return
     */
    @ResponseBody
    @GetMapping("/noticeDetail/comment/{comemntId}")
    public Object noticeComment(@PathVariable(name = "comemntId") Integer comemntId){ //查询二级回复
        CommentExample example = new CommentExample();
        example.createCriteria()
                .andTypeEqualTo(CommentTypeEnum.SECOND_CLASS_COMMENT.getType())
                .andParentIdEqualTo(comemntId);
        example.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(example);
        return comments;
    }


    /**
     * 插入一级/二级回复
     * @return
     */
    @ResponseBody
    @PostMapping("/noticeDetail/reply")
    @Transactional
    public ResultDTO comment(@RequestBody CommentDTO commentDTO){
        String content = commentDTO.getContent();
        int parentId = commentDTO.getParentId();
        int type = commentDTO.getType();
        if(content==null)
            return new ResultDTO(301,"回复失败,回复内容不能为空！",null);
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setParentId(parentId);
        comment.setType(type);
        comment.setGmtCreate(System.currentTimeMillis());
        commentMapper.insertSelective(comment);


        if(type==CommentTypeEnum.SECOND_CLASS_COMMENT.getType()){
            Comment parentComment = commentMapper.selectByPrimaryKey(parentId);
            parentComment.setCommentCount(parentComment.getCommentCount()+1); //这里会有并发问题！
            commentMapper.updateByPrimaryKeySelective(parentComment);
        }else if (type==CommentTypeEnum.FIRST_CLASS_COMMENT.getType()){
            Notice noticeParent = noticeMapper.selectByPrimaryKey(parentId);
            noticeParent.setCommentCount(noticeParent.getCommentCount()+1);
            noticeMapper.updateByPrimaryKeySelective(noticeParent);
        }

        return new ResultDTO(200,"回复成功",null);
    }
}
