import request from '@/utils/request'

// 查询插件登记问题列表
export function listIssue(query) {
  return request({
    url: '/plugin/issue/list',
    method: 'get',
    params: query
  })
}

// 查询插件登记问题详细
export function getIssue(id) {
  return request({
    url: '/plugin/issue/' + id,
    method: 'get'
  })
}

// 新增插件登记问题
export function addIssue(data) {
  return request({
    url: '/plugin/issue',
    method: 'post',
    data: data
  })
}

// 修改插件登记问题
export function updateIssue(data) {
  return request({
    url: '/plugin/issue',
    method: 'put',
    data: data
  })
}

// 删除插件登记问题
export function delIssue(id) {
  return request({
    url: '/plugin/issue/' + id,
    method: 'delete'
  })
}
