package cn.regexp.plugin.mapper;

import java.util.List;

import cn.regexp.plugin.domain.PluginIssue;
import org.springframework.stereotype.Repository;

/**
 * 插件登记问题Mapper接口
 *
 * @author Regexpei
 * @date 2024-07-20
 */
@Repository
public interface PluginIssueMapper {
    /**
     * 查询插件登记问题
     *
     * @param id 插件登记问题主键
     * @return 插件登记问题
     */
    public PluginIssue selectPluginIssueById(String id);

    /**
     * 查询插件登记问题列表
     *
     * @param pluginIssue 插件登记问题
     * @return 插件登记问题集合
     */
    public List<PluginIssue> selectPluginIssueList(PluginIssue pluginIssue);

    /**
     * 新增插件登记问题
     *
     * @param pluginIssue 插件登记问题
     * @return 结果
     */
    public int insertPluginIssue(PluginIssue pluginIssue);

    /**
     * 修改插件登记问题
     *
     * @param pluginIssue 插件登记问题
     * @return 结果
     */
    public int updatePluginIssue(PluginIssue pluginIssue);

    /**
     * 删除插件登记问题
     *
     * @param id 插件登记问题主键
     * @return 结果
     */
    public int deletePluginIssueById(String id);

    /**
     * 批量删除插件登记问题
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePluginIssueByIds(String[] ids);
}
