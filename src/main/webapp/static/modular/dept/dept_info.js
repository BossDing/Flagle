/**
 * 系统用户
 * @type {{}}
 */
var DeptInfo = {
    deptTable: null,//用户Table实例
    deptTableId:'deptTable',
    deptRoleId: 'deptTree',//用户角色id
    deptDataInfo:{}//用户查询数据
};


/**
 * 初始化列
 */
DeptInfo.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: '名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '编码', field: 'code', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle', sortable: true}
    ];
    return columns;
};


/**
 * 清除数据
 */
DeptInfo.clearData = function () {
    this.deptDataInfo = {};
};

/**
 * 收集数据
 */
DeptInfo.collectData = function () {
    this.set("loginName");
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DeptInfo.set = function (key, val) {
    this.deptDataInfo[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};


/**
 * 查询
 */
DeptInfo.search = function(){
    var queryData = {};
    queryData['loginName'] = $('#loginName').val();
    queryData['roleId'] = DeptInfo.userRoleId;
    //执行刷新
    this.userTable.refresh({query:queryData});
};

/**
 * 点击Ztree触碰事件
 */
DeptInfo.onClickUser = function (e, data) {
    DeptInfo.userRoleId = data.node.id;
    //调用刷新
    DeptInfo.search();
};

/**
 * 初始化加载
 */
$(function () {
    //初始化左侧树
    var roleTree = new $JSTree(DeptInfo.deptRoleId, '/dept/findDeptTreeNode');
    roleTree.bindOnClick(DeptInfo.onClickUser);
    roleTree.init();

    //获取table的初始化列
    var deptTable = new BSTable(DeptInfo.deptTableId, "/dept/list", DeptInfo.initColumn());
    deptTable.setPaginationType("server");
    DeptInfo.deptTable = deptTable.init();
});



