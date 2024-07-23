package cn.regexp.web.controller.plugin;

import cn.regexp.common.annotation.Log;
import cn.regexp.common.core.controller.BaseController;
import cn.regexp.common.core.domain.AjaxResult;
import cn.regexp.common.core.page.TableDataInfo;
import cn.regexp.common.enums.BusinessType;
import cn.regexp.common.utils.poi.ExcelUtil;
import cn.regexp.plugin.domain.PluginIssue;
import cn.regexp.plugin.service.IPluginIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 插件登记问题Controller
 *
 * @author Regexpei
 * @date 2024-07-20
 */
@RestController
@RequestMapping("/plugin/issue")
public class PluginIssueController extends BaseController {

    @Autowired
    private IPluginIssueService pluginIssueService;

    /**
     * 查询插件登记问题列表
     */
    @PreAuthorize("@ss.hasPermi('plugin:issue:list')")
    @GetMapping("/list")
    public TableDataInfo list(PluginIssue pluginIssue) {
        startPage();
        List<PluginIssue> list = pluginIssueService.selectPluginIssueList(pluginIssue);
        return getDataTable(list);
    }

    /**
     * 导出插件登记问题列表
     */
    @PreAuthorize("@ss.hasPermi('plugin:issue:export')")
    @Log(title = "插件登记问题", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PluginIssue pluginIssue) {
        List<PluginIssue> list = pluginIssueService.selectPluginIssueList(pluginIssue);
        ExcelUtil<PluginIssue> util = new ExcelUtil<>(PluginIssue.class);
        util.exportExcel(response, list, "插件登记问题数据");
    }

    /**
     * 获取插件登记问题详细信息
     */
    @PreAuthorize("@ss.hasPermi('plugin:issue:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(pluginIssueService.selectPluginIssueById(id));
    }

    /**
     * 新增插件登记问题
     */
    @PreAuthorize("@ss.hasPermi('plugin:issue:add')")
    @Log(title = "插件登记问题", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody PluginIssue pluginIssue) {
        return toAjax(pluginIssueService.insertPluginIssue(pluginIssue));
    }

    /**
     * 修改插件登记问题
     */
    @PreAuthorize("@ss.hasPermi('plugin:issue:edit')")
    @Log(title = "插件登记问题", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PluginIssue pluginIssue) {
        return toAjax(pluginIssueService.updatePluginIssue(pluginIssue));
    }

    /**
     * 删除插件登记问题
     */
    @PreAuthorize("@ss.hasPermi('plugin:issue:remove')")
    @Log(title = "插件登记问题", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(pluginIssueService.deletePluginIssueByIds(ids));
    }
}
