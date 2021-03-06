
package com.stalary.usercenter.controller;

import com.stalary.usercenter.data.dto.ResponseMessage;
import com.stalary.usercenter.service.ProjectService;
import com.stalary.usercenter.service.StatService;
import com.stalary.usercenter.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * FacadeController
 *
 * @author lirongqian
 * @since 2018/03/27
 */
@RequestMapping("/facade")
@Api(value = "对外提供的controller", tags = "提供一些基本的信息")
@RestController
public class FacadeController {

    @Resource
    private ProjectService projectService;

    @Resource
    private UserService userService;

    @Resource
    private StatService statService;

    @PostMapping("/project")
    @ApiOperation(value = "注册", notes = "传入项目名和负责人手机号")
    public ResponseMessage register(
            @RequestParam String name,
            @RequestParam String phone) {
        return ResponseMessage.successMessage(projectService.save(name, phone));
    }

    @GetMapping("/project")
    @ApiOperation(value = "获得项目信息", notes = "传入项目名和负责人手机号")
    public ResponseMessage getInfo(
            @RequestParam String name,
            @RequestParam String phone) {
        return ResponseMessage.successMessage(projectService.get(name, phone));
    }

    @GetMapping("/token")
    @ApiOperation(value = "通过token获取用户信息", notes = "传入token和key")
    public ResponseMessage getUser(
            @RequestParam String token,
            @RequestParam String key) {
        return ResponseMessage.successMessage(userService.findByToken(token, key));
    }

    @GetMapping("/statistics")
    @ApiOperation(value = "获取所有用户统计信息", notes = "传入项目id和key")
    public ResponseMessage getAllUserStat(
            @RequestParam Long projectId,
            @RequestParam String key) {
        return ResponseMessage.successMessage(statService.findByProjectId(projectId, key));
    }

    @GetMapping("/role")
    @ApiOperation(value = "获取指定角色的用户", notes = "传入项目id和key和role")
    public ResponseMessage getByRole(
            @RequestParam Long projectId,
            @RequestParam String key,
            @RequestParam Integer role) {
        return ResponseMessage.successMessage(userService.findByRole(projectId, key, role));
    }

    @GetMapping("/user")
    @ApiOperation(value = "获得一名用户", notes = "传入用户id和key以及项目id")
    public ResponseMessage getById(
            @RequestParam Long userId,
            @RequestParam String key,
            @RequestParam Long projectId) {
        return ResponseMessage.successMessage(userService.findById(userId, key, projectId));
    }
}