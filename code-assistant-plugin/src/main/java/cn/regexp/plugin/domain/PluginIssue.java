package cn.regexp.plugin.domain;

import cn.regexp.common.annotation.Excel;
import cn.regexp.common.core.domain.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 插件登记问题对象 plugin_issue
 *
 * @author Regexpei
 * @date 2024-07-20
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class PluginIssue extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 仓库地址
     */
    @Excel(name = "仓库地址")
    private String gitUrl;

    /**
     * 项目名称
     */
    @Excel(name = "项目名称")
    private String projectName;

    /**
     * 分支名称
     */
    @Excel(name = "分支名称")
    private String branchName;

    /**
     * 责任人
     */
    @Excel(name = "责任人")
    private String assignee;

    /**
     * 登记人
     */
    @Excel(name = "登记人")
    private String marker;

    /**
     * 问题类型
     */
    @Excel(name = "问题类型")
    private String issueType;

    /**
     * 问题级别
     */
    @Excel(name = "问题级别")
    private String issueLevel;

    /**
     * 问题状态
     */
    @Excel(name = "问题状态")
    private String issueStatus;

    /**
     * 优先级
     */
    @Excel(name = "优先级")
    private String priority;

    /**
     * 起始行号
     */
    @Excel(name = "起始行号")
    private String startPos;

    /**
     * 结束行号
     */
    @Excel(name = "结束行号")
    private String endPos;

    /**
     * 文件路径
     */
    @Excel(name = "文件路径")
    private String filePath;

    /**
     * 代码片段
     */
    @Excel(name = "代码片段")
    private String codeSegment;

    /**
     * 问题描述
     */
    @Excel(name = "问题描述")
    private String issueDetail;

    /**
     * 修复建议
     */
    @Excel(name = "修复建议")
    private String suggestion;
}
