package cn.regexp.plugin.service.impl;

import cn.regexp.common.utils.DateUtils;
import cn.regexp.common.utils.SecurityUtils;
import cn.regexp.plugin.domain.PluginIssue;
import cn.regexp.plugin.mapper.PluginIssueMapper;
import cn.regexp.plugin.service.IPluginIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 插件登记问题Service业务层处理
 *
 * @author Regexpei
 * @date 2024-07-20
 */
@Service
public class PluginIssueServiceImpl implements IPluginIssueService {
    @Autowired
    private PluginIssueMapper pluginIssueMapper;

    /**
     * 查询插件登记问题
     *
     * @param id 插件登记问题主键
     * @return 插件登记问题
     */
    @Override
    public PluginIssue selectPluginIssueById(String id) {
        return pluginIssueMapper.selectPluginIssueById(id);
    }

    /**
     * 查询插件登记问题列表
     *
     * @param pluginIssue 插件登记问题
     * @return 插件登记问题
     */
    @Override
    public List<PluginIssue> selectPluginIssueList(PluginIssue pluginIssue) {
        return pluginIssueMapper.selectPluginIssueList(pluginIssue);
    }

    /**
     * 新增插件登记问题
     *
     * @param pluginIssue 插件登记问题
     * @return 结果
     */
    @Override
    public int insertPluginIssue(PluginIssue pluginIssue) {
        pluginIssue.setCreateBy(SecurityUtils.getUsername());
        pluginIssue.setUpdateBy(SecurityUtils.getUsername());
        pluginIssue.setCreateTime(DateUtils.getNowDate());
        return pluginIssueMapper.insertPluginIssue(pluginIssue);
    }

    /**
     * 修改插件登记问题
     *
     * @param pluginIssue 插件登记问题
     * @return 结果
     */
    @Override
    public int updatePluginIssue(PluginIssue pluginIssue) {
        pluginIssue.setUpdateBy(SecurityUtils.getUsername());
        pluginIssue.setUpdateTime(DateUtils.getNowDate());
        return pluginIssueMapper.updatePluginIssue(pluginIssue);
    }

    /**
     * 批量删除插件登记问题
     *
     * @param ids 需要删除的插件登记问题主键
     * @return 结果
     */
    @Override
    public int deletePluginIssueByIds(String[] ids) {
        return pluginIssueMapper.deletePluginIssueByIds(ids);
    }

    /**
     * 删除插件登记问题信息
     *
     * @param id 插件登记问题主键
     * @return 结果
     */
    @Override
    public int deletePluginIssueById(String id) {
        return pluginIssueMapper.deletePluginIssueById(id);
    }
}
