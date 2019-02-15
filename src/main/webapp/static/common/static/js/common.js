var HRABBIT = {
    //弹框的index值
    layerIndex: null,

    //服务器跟路径
    serverPath: "",

    //提示框
    confirm: function (tip, ensure) {//询问框
        layer.confirm(tip, {
            btn: ['确定', '取消']
        }, function (index) {
            ensure();
            layer.close(index);
        });
    },
    info: function (info) {
        $.toast({
            heading: '提示',
            text: info,
            position: 'bottom-right',
            loaderBg: '#ff6849',
            icon: 'info',
            hideAfter: 3000,
            stack: 6
        });
    },
    success: function (info) {
        $.toast({
            heading: '成功',
            text: info,
            position: 'bottom-right',
            loaderBg: '#ff6849',
            icon: 'success',
            hideAfter: 3000,
            stack: 6
        });
    },
    error: function (info) {
        $.toast({
            heading: '错误',
            text: info,
            position: 'bottom-right',
            loaderBg: '#ff6849',
            icon: 'error',
            hideAfter: 3000,
            stack: 6
        });
    },
    /**
     * 打开弹窗页面
     */
    openPopup: function (title, wide, hight, url) {
        var path = window.parent.location.pathname;
        var index = path.indexOf("/#!");
        path = path.substring(0, index);
        //设置打开添加页面的宽度和高度，或者发送ajax打开界面
        var layerIndex = layer.open({
            type: 2,
            title: title,
            fix: false,
            maxmin: true,
            area: [wide + 'px', hight + 'px'],
            content: this.serverPath + url
        });

        this.layerIndex = layerIndex;
        return layerIndex;
    },
    initValidator: function(formId,fields){
        $('#' + formId).bootstrapValidator({
            fields: fields,
            live: 'enabled',
            message: '该字段不能为空'
        });
    },
};