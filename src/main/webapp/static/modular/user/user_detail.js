/**
 * 用户详情对象
 * @author hrabbit
 * @Param:
 * @Date: 2019/1/11 9:43 PM
 */
var UserDetailInfo = {
    loginLogTableId: 'loginLogTable',//tableId
    loginLogTable: null, //table对象
    loginLogDataInfo: {}
};

/**
 * 初始化列
 */
UserDetailInfo.initColumn = function () {
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
 * 初始化
 * @author hrabbit
 * @Param:
 * @Date: 2019/1/13 12:41 PM
 */
$(function () {
    //获取table的初始化列
    var logTable = new BSTable("loginLogTable", "/loginLog/list", UserDetailInfo.initColumn());
    logTable.setPaginationType("server");
    logTable.setHeight(410);
    UserDetailInfo.loginLogTable = logTable.init();
});