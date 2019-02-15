/**
 * 用户添加对象
 * @author hrabbit
 * @Param:
 * @Date: 2019/1/11 9:43 PM
 */
var RoleAddInfo = {
    roleAddInfoData: null,
    //表单规则
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '角色姓名不能为空'
                }
            }
        },
        code: {
            validators: {
                notEmpty: {
                    message: '角色代码不能为空'
                }
            }
        },
        parentName: {
            validators: {
                notEmpty: {
                    message: '上级角色不能为空'
                }
            }
        },
        deptName: {
            validators: {
                notEmpty: {
                    message: '所属部门不能为空'
                }
            }
        }
    }
};


/**
 * 表单验证
 */
RoleAddInfo.validate = function () {
    $('#roleForm').data("bootstrapValidator").resetForm();
    $('#roleForm').bootstrapValidator('validate');
    return $("#roleForm").data('bootstrapValidator').isValid();
};

/**
 * 清除数据
 */
RoleAddInfo.clearData = function () {
    this.roleAddInfoData = {};
};

/**
 * 收集数据
 */
RoleAddInfo.collectData = function () {
    this.set("id").set('name').set('code').set('parentId').set('parentName').set('deptId').set('deptName')
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RoleAddInfo.set = function (key, val) {
    this.roleAddInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};


/**
 * 添加用户角色
 */
RoleAddInfo.submitAddRole = function () {
    this.clearData();
    this.collectData();
    //验证表单
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax("/role/add", function (data) {
        parent.HRABBIT.success("添加成功！");
        window.parent.RoleInfo.roleTable.refresh();
        RoleAddInfo.close();
    }, function (data) {
        parent.HRABBIT.error('添加失败！');
    });
    ajax.set(RoleAddInfo.roleAddInfoData);
    ajax.start();
};


/**
 * 添加用户角色
 */
RoleAddInfo.submitUpdateRole = function () {
    this.clearData();
    this.collectData();
    //验证表单
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax("/role/update", function (data) {
        parent.HRABBIT.success("修改成功！");
        window.parent.RoleInfo.roleTable.refresh();
        RoleAddInfo.close();
    }, function (data) {
        parent.HRABBIT.error('修改失败！');
    });
    ajax.set(RoleAddInfo.roleAddInfoData);
    ajax.start();
};


/**
 * 显示部门选择的树
 *
 * @returns
 */
RoleAddInfo.showDeptTree = function () {
    $("#deptTreeView").slideDown("fast");
    $("body").bind("mousedown", onBodyDown);
};


/**
 * 显示角色选择的树
 *
 * @returns
 */
RoleAddInfo.showRoleTree = function () {
    $("#roleTreeView").slideDown("fast");
    $("body").bind("mousedown", onBodyDown);
};

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "deptTreeView" || $(
        event.target).parents("#deptTreeView").length > 0 || event.target.id == "roleTreeView" || $(
        event.target).parents("#roleTreeView").length > 0)) {
        RoleAddInfo.hideDeptSelectTree();
        RoleAddInfo.hideRoleSelectTree();
    }
}

/**
 * 隐藏部门选择的树
 */
RoleAddInfo.hideDeptSelectTree = function () {
    $("#deptTreeView").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

/**
 * 隐藏部门选择的树
 */
RoleAddInfo.hideRoleSelectTree = function () {
    $("#roleTreeView").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

/**
 * 点击下拉树事件
 * @author hrabbit
 * @Param:
 * @Date: 2019/1/13 12:43 PM
 */
RoleAddInfo.onClickDept = function (e, data) {
    //设置数据
    $('#deptName').val(data.node.text);
    $('#deptId').val(data.node.id);
    $("#deptTreeView").fadeOut("fast");
};


/**
 * 点击下拉树事件
 * @author hrabbit
 * @Param:
 * @Date: 2019/1/13 12:43 PM
 */
RoleAddInfo.onClickRole = function (e, data) {
    //设置数据
    $('#parentName').val(data.node.text);
    $('#parentId').val(data.node.id);
    $("#roleTreeView").fadeOut("fast");
};

/**
 * 关闭此对话框
 */
RoleAddInfo.close = function () {
    parent.layer.close(window.parent.RoleInfo.layerIndex);
};

/**
 * 初始化
 */
$(function () {

    //开启表单验证
    HRABBIT.initValidator('roleForm', RoleAddInfo.validateFields);

    //初始化部门下拉树
    var deptTree = new $JSTree('deptTree', '/dept/findDeptTreeNode');
    deptTree.bindOnClick(RoleAddInfo.onClickDept);
    deptTree.init();


    //初始化上级角色下拉树
    var roleTree = new $JSTree('roleTree', '/role/findRoleTreeNode');
    roleTree.bindOnClick(RoleAddInfo.onClickRole);
    roleTree.init();
});