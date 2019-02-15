/**
 * 系统用户
 * @type {{}}
 */
var RoleInfo = {
    roleTable: null,//用户Table实例
    roleTableId: 'roleTable',
    parentRoleId:0,
    deptRoleId: 'deptTree',//用户角色id
    roleDataInfo: {},//用户查询数据
    layerIndex: -1,
    seItem: null
};


/**
 * 初始化列
 */
RoleInfo.initColumn = function () {
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
RoleInfo.clearData = function () {
    this.roleDataInfo = {};
};


/**
 * 收集数据
 */
RoleInfo.collectData = function () {
    this.set("loginName");
};


/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RoleInfo.set = function (key, val) {
    this.roleDataInfo[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};


/**
 * 查询
 */
RoleInfo.search = function () {
    var queryData = {};
    queryData['roleName'] = $('#roleName').val();
    queryData['roleId'] = RoleInfo.parentRoleId;
    //执行刷新
    this.roleTable.refresh({query: queryData});
};


/**
 * 点击Ztree触碰事件
 */
RoleInfo.onClickUser = function (e, data) {
    RoleInfo.parentRoleId = data.node.id;
    //调用刷新
    RoleInfo.search();
};

/**
 * 检查是否选中
 */
RoleInfo.check = function () {
    var selected = $('#' + this.roleTableId).bootstrapTable('getSelections');
    if (selected.length == 0) {
        HRABBIT.error("请先选中表格中的某一记录！");
        return false;
    } else {
        RoleInfo.seItem = selected[0];
        return true;
    }
};


/**
 * 打开添加角色的页面
 */
RoleInfo.openAddRole = function () {
    RoleInfo.layerIndex = HRABBIT.openPopup('添加角色', '780', '380', '/role/openAddRole');
};

/**
 * 打开修改角色的页面
 */
RoleInfo.openUpdateRole = function () {
    //判断是否选中用户信息
    if (!this.check())
        return;
    RoleInfo.layerIndex = HRABBIT.openPopup('修改角色', '780', '380', '/role/openUpdateRole/' + RoleInfo.seItem.id);
};

/**
 * 删除角色的信息
 */
RoleInfo.openDeleteRole = function () {
    //判断是否选中用户信息
    if (!this.check())
        return;

    //发送ajax
    function operation() {
        var roleId = RoleInfo.seItem.id;
        //发送ajax
        var ajax = new $ax("/role/delete/" + roleId, function (data) {
            if (data.status!=200){
                HRABBIT.error(data.message);
            }else{
                HRABBIT.success(data.message);
            }
            RoleInfo.roleTable.refresh();
        }, function (data) {
            HRABBIT.error("删除失败!");
        });
        ajax.setType("DELETE");
        ajax.start();
    };
    //弹框提示
    HRABBIT.confirm("是否删除角色<span style='color: red;'>" + RoleInfo.seItem.name + "</span>?", operation);
};

/**
 * 分配角色权限
 */
RoleInfo.openPermission = function () {
    //判断是否选中用户信息
    if (!this.check())
        return;
};

/**
 * 初始化加载
 */
$(function () {
    //初始化左侧树
    var roleTree = new $JSTree(RoleInfo.deptRoleId, '/role/findRoleTreeNode');
    roleTree.bindOnClick(RoleInfo.onClickUser);
    roleTree.init();

    //获取table的初始化列
    var roleTable = new BSTable(RoleInfo.roleTableId, "/role/list", RoleInfo.initColumn());
    roleTable.setPaginationType("server");
    RoleInfo.roleTable = roleTable.init();
});