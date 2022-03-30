package com.itchenyang.controller;

import com.itchenyang.entity.MessageInformation;
import com.itchenyang.entity.UserInformation;
import com.itchenyang.entity.UserSearchQuery;
import com.itchenyang.exception.Assert;
import com.itchenyang.listerner.WebSocket;
import com.itchenyang.result.R;
import com.itchenyang.result.ResponseEnum;
import com.itchenyang.service.ThirdRequestService;
import com.itchenyang.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@Api(tags = "三方请求管理管理")
@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin
public class ThirdRequestController {

    @Resource
    private ThirdRequestService thirdRequestService;

    @Resource
    private WebSocket webSocket;

    /**
     * 三方用户请求后，进入到等待页面时，会给admin发送消息
     * admin做出处理后，会给三方用户反馈消息
     * @param username
     * @return
     */
    @ApiOperation("推送消息给对应用户")
    @GetMapping("/send")
    public R sentOneMessage(@ApiParam(value = "消息推送的对应用户")
                            @RequestParam("name") String username) {
        // 查看数据库中有多少条未被处置
        Integer count = thirdRequestService.getNotDealCount();
        Pair<Boolean,String> pair = webSocket.sendOneMessage(username, count.toString());
        Assert.isTrue(pair.getLeft(),ResponseEnum.ERROR);
        log.info(pair.getRight());
        return R.ok().playMessage(pair.getRight());
    }

    @ApiOperation("三方用户请求信息的保存")
    @PostMapping("/save/request_message")
    public R saveRequestMessage(@ApiParam(value = "请求信息",required = true)
                                @RequestBody UserInformation userInformation) {
        Boolean flag = thirdRequestService.saveRequestMessage(userInformation);
        Assert.isTrue(flag, ResponseEnum.ERROR);
        return R.ok().playMessage("请等待管理员处理请求");
    }

    @ApiOperation("请求信息列表")
    @GetMapping("/message/list/{page}/{limit}")
    public R getMessageList(@ApiParam(value = "第几页",required = true) @PathVariable("page") Integer page,
                            @ApiParam(value = "每页数据量",required = true) @PathVariable("limit") Integer limit,
                            @ApiParam(value = "查询对象") UserSearchQuery searchQuery) {
        Integer offset = (page -1) * limit;
        Map<String, Object> map = thirdRequestService.getMessageList(offset, limit, searchQuery);
        return R.ok().playData("messageList",map);
    }

    @ApiOperation("处理请求列表")
    @GetMapping("/deal/message/{id}/{flag}")
    public R dealRequestMessage(@ApiParam(value = "请求者id",required = true) @PathVariable("id") Integer id,
                                @ApiParam(value = "处理动作",required = true) @PathVariable("flag") Integer flag) {
        // 获取到处置的对应用户
        MessageInformation info = thirdRequestService.getMsgInfo(id);
        // 同意则生成token发送给请求用户
        String token = "0";
        if (flag == 1) {
            token = JwtUtils.createToken(info.getPhone(), info.getUsername(), info.getRolePid() + "-" + info.getRoleCid());
        }
        Pair<Boolean, String> pair = webSocket.sendOneMessage(info.getUsername(), token);
        Boolean result = thirdRequestService.dealRequestMessage(id,flag);
        Assert.isTrue(result && pair.getLeft(), ResponseEnum.ERROR);
        return R.ok();
    }

    @ApiOperation("获取消息通知数量")
    @GetMapping("/message/count")
    public Integer getMessageCount() {
        return thirdRequestService.getNotDealCount();
    }

    @ApiOperation("删除请求")
    @GetMapping("/message/del/{id}")
    public R delMessage(@ApiParam(value = "请求者id",required = true) @PathVariable("id") Integer id) {
        Boolean flag = thirdRequestService.delMessage(id);
        Assert.isTrue(flag, ResponseEnum.ERROR);
        return R.ok();
    }
}
