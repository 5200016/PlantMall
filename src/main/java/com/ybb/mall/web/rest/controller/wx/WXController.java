package com.ybb.mall.web.rest.controller.wx;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.service.WXService;
import com.ybb.mall.service.wx.WXFormService;
import com.ybb.mall.web.rest.controller.wx.vm.WXBindVM;
import com.ybb.mall.web.rest.controller.wx.vm.WXFormVM;
import com.ybb.mall.web.rest.util.ResultObj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * @Description : 微信小程序管理
 * @Author 黄志成
 * @Date 2019-05-09
 * @Version
 */

@Api(description="微信小程序管理")
@RestController
@RequestMapping("/mall/wx")
public class WXController {

    private final WXService wxService;

    private final WXFormService wxFormService;

    public WXController(WXService wxService, WXFormService wxFormService) {
        this.wxService = wxService;
        this.wxFormService = wxFormService;
    }

    /**
     * 绑定用户信息
     */
    @ApiOperation("绑定用户信息")
    @PostMapping("/bind_user")
    @Timed
    public ResultObj bindUser(@RequestBody WXBindVM wxBindVM) throws URISyntaxException {
        return wxService.decodeUserInfo(wxBindVM.getEncryptedData(), wxBindVM.getIv(), wxBindVM.getCode());
    }

    /**
     * 绑定用户手机号
     */
    @ApiOperation("绑定用户手机号")
    @PostMapping("/bind_phone")
    @Timed
    public ResultObj bindPhone(@RequestBody WXBindVM wxBindVM) throws URISyntaxException {
        return wxService.decodeUserPhone(wxBindVM.getOpenid(), wxBindVM.getEncryptedData(), wxBindVM.getIv(), wxBindVM.getCode());
    }

    /**
     * 微信formId存储
     */
    @ApiOperation("微信formId存储")
    @PostMapping("/form")
    @Timed
    public ResultObj saveFormId(@RequestBody WXFormVM wxFormVM) throws URISyntaxException {
        return wxFormService.insertForm(wxFormVM);
    }
}
