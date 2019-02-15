/**
 * 系统日志对象
 * @author hrabbit
 * @Param:
 * @Date: 2019/1/13 1:21 PM
 */
var LogInfo = {
    logTableId: 'LogTable',//tableId
    logTable: null, //table对象
    logDataInfo:{}
};

/**
 * 初始化列
 */
LogInfo.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: '名称', field: 'logName', align: 'center', valign: 'middle', sortable: true},
        {title: '类型', field: 'logType', align: 'center', valign: 'middle', sortable: true},
        {title: '用户', field: 'createName', align: 'center', valign: 'middle', sortable: true},
        {title: '类名', field: 'className', align: 'center', valign: 'middle', sortable: true},
        {title: '方法名称', field: 'method', align: 'center', valign: 'middle', sortable: true},
        {title: '具体消息', field: 'message', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle', sortable: true}
    ];
    return columns;
};

/**
 * 清除数据
 */
LogInfo.clearData = function () {
    this.logDataInfo = {};
};

/**
 * 收集数据
 */
LogInfo.collectData = function () {
    this.set("logName");
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LogInfo.set = function (key, val) {
    this.logDataInfo[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 查询日志
 * @author hrabbit
 * @Param:
 * @Date: 2019/1/13 2:42 PM
 */
LogInfo.search = function(){
    this.clearData();
    this.collectData();
    this.logTable.refresh({query:LogInfo.logDataInfo});
};

/**
 * 系统初始化
 * @author hrabbit
 * @Param:
 * @Date: 2019/1/13 1:22 PM
 */
$(function () {
    //获取table的初始化列
    var logTable = new BSTable("logTable", "/log/list", LogInfo.initColumn());
    logTable.setPaginationType("server");
    LogInfo.logTable = logTable.init();
});