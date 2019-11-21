/**
 * * 增强通过Jquery获取的对象的功能  $("#id")
 */
$.fn.extend({
    /** 定义表单序列化转json */
    hf_serializeObject : function() {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    }
});

