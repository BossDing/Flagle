$(function () {

    var SelectDom = $('.select2');

    //初始化下拉框
    if (SelectDom.length > 0) {
        $('.select2').select2({
            width: "100%",
            minimumResultsForSearch: -1
        });
    }

    var BootStrapDatePicker = $('.datePicker');
    //初始化日期
    if (BootStrapDatePicker.length > 0) {
        /**
         * 初始化日期
         */
        $('.datePicker').datepicker({
            language: "zh-CN",
            showButtonPanel: true, //显示按钮
            format: "yyyy-mm-dd",
            autoclose: true,//选中之后自动隐藏日期选择框
        });
    }
});