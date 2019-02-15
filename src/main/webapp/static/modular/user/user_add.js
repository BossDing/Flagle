/**
 * 用户添加对象
 * @author hrabbit
 * @Param:
 * @Date: 2019/1/11 9:43 PM
 */
var UserAddInfo = {
    //提交JSON对象
    userAddInfoData: {},
    //表单规则
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '姓名不能为空'
                }
            }
        },
        loginName: {
            validators: {
                notEmpty: {
                    message: '登录名称不能为空'
                }
            }
        },
        phone: {
            validators: {
                notEmpty: {
                    message: '电话不能为空'
                },
                stringLength: {
                    min: 11,
                    max: 11,
                    message: '请输入11位手机号码'
                },
                regexp: {
                    regexp: /^1[3|5|8]{1}[0-9]{9}$/,
                    message: '请输入正确的手机号码'
                }
            }
        },
        password: {
            validators: {
                notEmpty: {
                    message: '密码不能为空'
                },
                regexp: {
                    regexp: /^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])|(?=.*[A-Z])(?=.*[a-z])(?=.*[^A-Za-z0-9])|(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])|(?=.*[a-z])(?=.*[0-9])(?=.*[^A-Za-z0-9])).{6,16}$/,
                    message: '用户新密码长度为6至16位,并且需要包含数字、小写字母、大写字母、符号(至少三种)'
                }
            },
        }
    }
};


/**
 * 关闭此对话框
 */
UserAddInfo.close = function () {
    parent.layer.close(window.parent.UserInfo.layerIndex);
};

/**
 * 表单验证
 */
UserAddInfo.validate = function () {
    $('#userForm').data("bootstrapValidator").resetForm();
    $('#userForm').bootstrapValidator('validate');
    return $("#userForm").data('bootstrapValidator').isValid();
};

/**
 * 清除数据
 */
UserAddInfo.clearData = function () {
    this.userAddInfoData = {};
};

/**
 * 收集数据
 */
UserAddInfo.collectData = function () {
    this.set("id").set('loginName').set('password').set('deptId').set('deptName').set('name').set('phone').set('role');
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserAddInfo.set = function (key, val) {
    this.userAddInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 提交添加用户
 * @author hrabbit
 * @Param:
 * @Date: 2019/1/11 9:45 PM
 */
UserAddInfo.submitAddUser = function () {

    this.clearData();
    this.collectData();
    //验证表单
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax("/user/add", function (data) {
        parent.HRABBIT.success("添加成功！");
        window.parent.UserInfo.userTable.refresh();
        UserAddInfo.close();
    }, function (data) {
        parent.HRABBIT.error('添加失败！');
    });
    ajax.set(UserAddInfo.userAddInfoData);
    ajax.start();
};


/**
 * 提交修改用户
 * @author hrabbit
 * @Param:
 * @Date: 2019/1/11 9:45 PM
 */
UserAddInfo.submitUpdateUser = function () {

    this.clearData();
    this.collectData();
    //验证表单
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax("/user/update", function (data) {
        parent.HRABBIT.success("修改成功！");
        window.parent.UserInfo.userTable.refresh();
        UserAddInfo.close();
    }, function (data) {
        parent.HRABBIT.error('修改失败！');
    });
    ajax.set(UserAddInfo.userAddInfoData);
    ajax.start();
};


/**
 * 点击下拉树事件
 * @author hrabbit
 * @Param:
 * @Date: 2019/1/13 12:43 PM
 */
UserAddInfo.onClickDept = function (e, data) {
    //设置数据
    $('#deptName').val(data.node.text);
    $('#deptId').val(data.node.id);
    $("#deptTreeView").fadeOut("fast");
    //初始化角色信息
    UserAddInfo.loadRoleSelect(data.node.id);
};


/**
 * 初始化角色信息
 * @param deptId
 */
UserAddInfo.loadRoleSelect = function (deptId) {
    var options = "";
    $('#role').empty();
    //提交信息
    var ajax = new $ax("/role/findRoleByDeptId/" + deptId, function (data) {
        options += "<optgroup label='" + data.name + "'>";
        for (var i = 0; i < data.linkedList.length; i++) {
            options += "<option value='" + data.linkedList[i].id + "'>" + data.linkedList[i].name + "</option>";
        }
        options += "</optgroup>";
        $('#role').append(options);
    }, function (data) {
        HRABBIT.error(data.message)
    });
    ajax.setType('get');
    ajax.start();
};

/**
 * 显示部门选择的树
 *
 * @returns
 */
UserAddInfo.showDeptTree = function () {
    $("#deptTreeView").slideDown("fast");
    $("body").bind("mousedown", onBodyDown);
};

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "deptTreeView" || $(
        event.target).parents("#deptTreeView").length > 0)) {
        UserAddInfo.hideDeptSelectTree();
    }
}

/**
 * 隐藏部门选择的树
 */
UserAddInfo.hideDeptSelectTree = function () {
    $("#deptTreeView").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

/**
 * 初始化
 * @author hrabbit
 * @Param:
 * @Date: 2019/1/13 12:41 PM
 */
$(function () {
    //开启表单验证
    HRABBIT.initValidator('userForm', UserAddInfo.validateFields);
    //初始化下拉树
    var deptTree = new $JSTree('deptTree', '/dept/findDeptTreeNode');
    deptTree.bindOnClick(UserAddInfo.onClickDept);
    deptTree.init();
});