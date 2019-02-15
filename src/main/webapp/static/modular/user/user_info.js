/**
 * 系统用户
 * @type {{}}
 */
var UserInfo = {
    userId: 'userTable',
    userTable: null,//用户Table实例
    userRoleId: null,//用户角色id
    userDataInfo: {},//用户查询数据
    layerIndex: -1,
    seItem: null
};


/**
 * 初始化列
 */
UserInfo.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: '登录名称', field: 'loginName', align: 'center', valign: 'middle', sortable: true},
        {title: '用户名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '部门', field: 'deptName', align: 'center', valign: 'middle', sortable: true},
        {title: '电话', field: 'phone', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle', sortable: true}
    ];
    return columns;
};


/**
 * 清除数据
 */
UserInfo.clearData = function () {
    this.userDataInfo = {};
};

/**
 * 收集数据
 */
UserInfo.collectData = function () {
    this.set("loginName").set('createTime');
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserInfo.set = function (key, val) {
    this.userDataInfo[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 查询
 */
UserInfo.search = function () {
    var queryData = {};
    queryData['loginName'] = $('#loginName').val();
    queryData['createTime'] = $('#createTime').val();
    queryData['roleId'] = UserInfo.userRoleId;
    //执行刷新
    this.userTable.refresh({query: queryData});
};

/**
 * 点击Ztree触碰事件
 */
UserInfo.onClickUser = function (e, data) {
    UserInfo.userRoleId = data.node.id;
    //调用刷新
    UserInfo.search();
};

/**
 * 检查是否选中
 */
UserInfo.check = function () {
    var selected = $('#' + this.userId).bootstrapTable('getSelections');
    if (selected.length == 0) {
        HRABBIT.error("请先选中表格中的某一记录！");
        return false;
    } else {
        UserInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 打开添加用户
 */
UserInfo.openAddUser = function () {
    UserInfo.layerIndex = HRABBIT.openPopup('添加用户', '780', '490', '/user/openAddUser');
};


/**
 * 打开修改用户界面
 */
UserInfo.openUpdateUser = function () {
    //判断是否选中用户信息
    if (!this.check())
        return;
    UserInfo.layerIndex = HRABBIT.openPopup('修改用户', '780', '490', '/user/openUpdateUser/' + UserInfo.seItem.id);
};

/**
 * 删除系统用户
 */
UserInfo.deleteUser = function () {
    //判断是否选中用户信息
    if (!this.check())
        return;

    //发送ajax
    function operation() {
        var userId = UserInfo.seItem.id;
        //发送ajax
        var ajax = new $ax("/user/delete/" + userId, function (data) {
            HRABBIT.success("删除成功！");
            UserInfo.userTable.refresh();
        }, function (data) {
            HRABBIT.error("删除失败!");
        });
        ajax.setType("DELETE");
        ajax.start();
    };
    //弹框提示
    HRABBIT.confirm("是否删除用户" + UserInfo.seItem.name + "?", operation);
};

/**
 * 初始化加载
 */
$(function () {
    //初始化左侧树
    var userTree = new $JSTree('userTree', '/role/findRoleTreeNode');
    userTree.bindOnClick(UserInfo.onClickUser);
    userTree.init();

    //获取table的初始化列
    var initColumn = UserInfo.initColumn();
    var userTable = new BSTable("userTable", "/user/list", initColumn);
    userTable.setPaginationType("server");
    UserInfo.userTable = userTable.init();
});



