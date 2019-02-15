/**
 * 系统日志对象
 * @author hrabbit
 * @Param:
 * @Date: 2019/1/13 1:21 PM
 */
var LoginLogInfo = {
    loginLogTableId: 'loginLogTable',//tableId
    loginLogTable: null, //table对象
    loginLogDataInfo: {}
};

/**
 * 初始化列
 */
LoginLogInfo.initColumn = function () {
    var columns = [
        {title: '登录人', field: 'loginName', align: 'center', valign: 'middle', sortable: true},
        {title: 'IP', field: 'ip', align: 'center', valign: 'middle', sortable: true},
        {title: '名称', field: 'logName', align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'success', align: 'center', valign: 'middle', sortable: true},
        {title: '参数', field: 'message', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle', sortable: true}
    ];
    return columns;
};

/**
 * 清除数据
 */
LoginLogInfo.clearData = function () {
    this.loginLogDataInfo = {};
};

/**
 * 收集数据
 */
LoginLogInfo.collectData = function () {
    this.set("logName");
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LoginLogInfo.set = function (key, val) {
    this.loginLogDataInfo[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 查询日志
 * @author hrabbit
 * @Param:
 * @Date: 2019/1/13 2:42 PM
 */
LoginLogInfo.search = function () {
    this.clearData();
    this.collectData();
    this.loginLogTable.refresh({query: LoginLogInfo.loginLogDataInfo});
};

/**
 * 系统初始化
 * @author hrabbit
 * @Param:
 * @Date: 2019/1/13 1:22 PM
 */
$(function () {
    //获取table的初始化列
    var logTable = new BSTable("loginLogTable", "/loginLog/list", LoginLogInfo.initColumn());
    logTable.setPaginationType("server");
    LoginLogInfo.loginLogTable = logTable.init();
});