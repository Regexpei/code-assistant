package cn.regexp.plugin.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Regexpei
 * @date 2024/7/20 19:59
 * @description
 * @since 1.0.0
 */
@Setter
@Getter
public class PluginUserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    private String name;
    /**
     * 用户名
     */
    private String username;
    /**
     * 邮箱
     */
    private String email;

}
