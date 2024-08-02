package cn.regexp.web.controller.plugin;

import cn.regexp.common.core.controller.BaseController;
import cn.regexp.common.core.domain.AjaxResult;
import cn.regexp.common.core.domain.entity.SysUser;
import cn.regexp.common.core.domain.model.LoginBody;
import cn.regexp.framework.web.service.SysLoginService;
import cn.regexp.plugin.domain.dto.PluginUserDto;
import cn.regexp.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Regexpei
 * @date 2024/7/20 19:32
 * @description 插件用户 Controller
 * @since 1.0.0
 */
@RestController
@RequestMapping("/plugin/user")
public class PluginUserController extends BaseController {

    @Autowired
    private SysLoginService loginService;
    @Autowired
    private ISysUserService userService;

    /**
     * 插件登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult pluginLogin(@RequestBody LoginBody loginBody) {
        // 生成令牌
        Object token = loginService.pluginLogin(loginBody.getUsername(), loginBody.getPassword());
        return success(token);
    }

    @GetMapping("/listPluginUser")
    public AjaxResult listPluginUser() {
        List<SysUser> pluginUserList = userService.selectUserByRoleKey("pluginUser");
        return success(mapToPluginUser(pluginUserList));
    }

    private List<PluginUserDto> mapToPluginUser(List<SysUser> users) {
        if (CollectionUtils.isEmpty(users)) {
            return Collections.emptyList();
        }

        return users.stream().map(user -> {
            PluginUserDto pluginUserDto = new PluginUserDto();
            pluginUserDto.setName(user.getNickName());
            pluginUserDto.setUsername(user.getUserName());
            pluginUserDto.setEmail(user.getEmail());
            return pluginUserDto;
        }).collect(Collectors.toList());
    }


}
