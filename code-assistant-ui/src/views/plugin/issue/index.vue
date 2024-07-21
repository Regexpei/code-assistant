<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="仓库地址" prop="gitUrl">
        <el-input
          v-model="queryParams.gitUrl"
          placeholder="请输入仓库地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目名称" prop="projectName">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入项目名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分支名称" prop="branchName">
        <el-input
          v-model="queryParams.branchName"
          placeholder="请输入分支名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="责任人" prop="assignee">
        <el-input
          v-model="queryParams.assignee"
          placeholder="请输入责任人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="登记人" prop="marker">
        <el-input
          v-model="queryParams.marker"
          placeholder="请输入登记人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文件路径" prop="filePath">
        <el-input
          v-model="queryParams.filePath"
          placeholder="请输入文件路径"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['plugin:issue:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['plugin:issue:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['plugin:issue:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['plugin:issue:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="issueList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="主键" align="center" prop="id"/>
      <el-table-column label="仓库地址" align="center" prop="gitUrl"/>
      <el-table-column label="项目名称" align="center" prop="projectName"/>
      <el-table-column label="分支名称" align="center" prop="branchName"/>
      <el-table-column label="责任人" align="center" prop="assignee"/>
      <el-table-column label="登记人" align="center" prop="marker"/>
      <el-table-column label="问题类型" align="center" prop="issueType"/>
      <el-table-column label="问题级别" align="center" prop="issueLevel"/>
      <el-table-column label="问题状态" align="center" prop="issueStatus"/>
      <el-table-column label="优先级" align="center" prop="priority"/>
      <el-table-column label="起始行号" align="center" prop="startPos"/>
      <el-table-column label="结束行号" align="center" prop="endPos"/>
      <el-table-column label="文件路径" align="center" prop="filePath"/>
      <el-table-column label="代码片段" align="center" prop="codeSegment"/>
      <el-table-column label="问题描述" align="center" prop="issueDetail"/>
      <el-table-column label="修复建议" align="center" prop="suggestion"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['plugin:issue:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['plugin:issue:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改插件登记问题对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="仓库地址" prop="gitUrl">
          <el-input v-model="form.gitUrl" placeholder="请输入仓库地址"/>
        </el-form-item>
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称"/>
        </el-form-item>
        <el-form-item label="分支名称" prop="branchName">
          <el-input v-model="form.branchName" placeholder="请输入分支名称"/>
        </el-form-item>
        <el-form-item label="责任人" prop="assignee">
          <el-input v-model="form.assignee" placeholder="请输入责任人"/>
        </el-form-item>
        <el-form-item label="登记人" prop="marker">
          <el-input v-model="form.marker" placeholder="请输入登记人"/>
        </el-form-item>
        <el-form-item label="文件路径" prop="filePath">
          <el-input v-model="form.filePath" placeholder="请输入文件路径"/>
        </el-form-item>
        <el-form-item label="代码片段" prop="codeSegment">
          <el-input v-model="form.codeSegment" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="问题描述" prop="issueDetail">
          <el-input v-model="form.issueDetail" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="修复建议" prop="suggestion">
          <el-input v-model="form.suggestion" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listIssue,
  getIssue,
  delIssue,
  addIssue,
  updateIssue
} from "@/api/plugin/issue";

export default {
  name: "Issue",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 插件登记问题表格数据
      issueList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        gitUrl: null,
        projectName: null,
        branchName: null,
        assignee: null,
        marker: null,
        issueType: null,
        issueLevel: null,
        issueStatus: null,
        priority: null,
        startPos: null,
        endPos: null,
        filePath: null,
        codeSegment: null,
        issueDetail: null,
        suggestion: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        issueType: [
          {
            required: true, message: "问题类型不能为空", trigger: "change"
          }
        ],
        issueLevel: [
          {
            required: true, message: "问题级别不能为空", trigger: "blur"
          }
        ],
        issueStatus: [
          {
            required: true, message: "问题状态不能为空", trigger: "change"
          }
        ],
        priority: [
          {
            required: true, message: "优先级不能为空", trigger: "blur"
          }
        ],
        startPos: [
          {
            required: true, message: "起始行号不能为空", trigger: "blur"
          }
        ],
        endPos: [
          {
            required: true, message: "结束行号不能为空", trigger: "blur"
          }
        ],
        filePath: [
          {
            required: true, message: "文件路径不能为空", trigger: "blur"
          }
        ],
        codeSegment: [
          {
            required: true, message: "代码片段不能为空", trigger: "blur"
          }
        ],
        createBy: [
          {
            required: true, message: "创建者不能为空", trigger: "blur"
          }
        ],
        createTime: [
          {
            required: true, message: "创建时间不能为空", trigger: "blur"
          }
        ],
        updateBy: [
          {
            required: true, message: "更新者不能为空", trigger: "blur"
          }
        ],
        updateTime: [
          {
            required: true, message: "更新时间不能为空", trigger: "blur"
          }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询插件登记问题列表 */
    getList() {
      this.loading = true;
      listIssue(this.queryParams).then(response => {
        this.issueList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        gitUrl: null,
        projectName: null,
        branchName: null,
        assignee: null,
        marker: null,
        issueType: null,
        issueLevel: null,
        issueStatus: null,
        priority: null,
        startPos: null,
        endPos: null,
        filePath: null,
        codeSegment: null,
        issueDetail: null,
        suggestion: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加插件登记问题";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getIssue(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改插件登记问题";
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('plugin/issue/export', {
        ...this.queryParams
      }, `issue_${new Date().getTime()}.xlsx`)
    }
  }
}
;
</script>
