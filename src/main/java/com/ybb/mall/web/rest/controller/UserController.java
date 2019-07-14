package com.ybb.mall.web.rest.controller;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.service.SUserService;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.user.UpdateUserVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * @Description : 用户信息管理
 * @Author 黄志成
 * @Date 2019-04-15
 * @Version
 */

@Api(description="用户信息管理")
@RestController
@RequestMapping("/mall")
public class UserController {
    private final SUserService userService;

    public UserController(SUserService userService) {
        this.userService = userService;
    }


    /**
     * 根据手机号模糊查询用户列表（分页）
     */
    @ApiOperation("根据手机号模糊查询用户列表（分页）")
    @GetMapping("/users")
    @Timed
    public ResultObj selectUserList(@ApiParam(name="phone",value="手机号",required=true) @RequestParam String phone,
                                    @ApiParam(name="pageNum",value="页码",required=true) @RequestParam Integer pageNum,
                                    @ApiParam(name="pageSize",value="数量",required=true) @RequestParam Integer pageSize) throws URISyntaxException {
        return ResultObj.back(200, userService.findUserList(phone, pageNum, pageSize));
    }

    /**
     * 查询用户简略信息
     */
    @ApiOperation("查询用户简略信息")
    @GetMapping("/users/brief")
    @Timed
    public ResultObj selectUserListBrief() throws URISyntaxException {
        return ResultObj.back(200, userService.findUserListBrief());
    }

    /**
     * 新增用户
     */
    @ApiOperation("新增用户")
    @PostMapping("/user")
    @Timed
    public ResultObj insertUser(@ApiParam(name="phone",value="手机号",required=true) @RequestParam String phone) throws URISyntaxException {
        return null;
    }

    /**
     * 修改用户
     */
    @ApiOperation("修改用户")
    @PutMapping("/user")
    @Timed
    public ResultObj updateUser(@RequestBody UpdateUserVM updateUser) throws URISyntaxException {
        return userService.updateUserInfo(updateUser);
    }

    /**
     * 删除用户
     */
    @ApiOperation("删除用户")
    @DeleteMapping("/user/{id}")
    @Timed
    public ResultObj deleteUser(@ApiParam(name="id",value="主键id",required=true) @PathVariable Long id) throws URISyntaxException {
        return null;
    }
}
