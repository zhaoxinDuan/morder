(function () {
    var js = [
        "validate.js"
    ];
    var base = "";
    var scripts = document.getElementsByTagName("script");
    for (var i = 0; i < scripts.length; i++) {
        var src = scripts[i].src;
        if (!src) {
            continue;
        }
        var m = src.match(/common\.js(\W|$)/i);
        if (m) {
            base = src.substring(0, m.index);
        }
    }
    var head = document.getElementsByTagName("head")[0];
    for (var j = 0; j < js.length; j++) {
        var script = document.createElement("script");
        script.type = "text/javascript";
        script.language = "javascript";
        script.src = base + js[j];
        head.appendChild(script);
    }
})();
/*分页*/
function pagerFilter(data) {
    if (typeof data.length == 'number' && typeof data.splice == 'function') {
        data = {
            total: data.length,
            rows: data
        }
    }
    var dg = $(this);
    var opts = dg.datagrid('options');
    var pager = dg.datagrid('getPager');
    pager.pagination({
        onSelectPage: function (pageNum, pageSize) {
            opts.pageNumber = pageNum;
            opts.pageSize = pageSize;
            pager.pagination('refresh', {
                pageNumber: pageNum,
                pageSize: pageSize
            });
            dg.datagrid('loadData', data);
        }
    });
    if (!data.originalRows) {
        data.originalRows = (data.rows);
    }
    var start = (opts.pageNumber - 1) * parseInt(opts.pageSize);
    var end = start + parseInt(opts.pageSize);
    data.rows = (data.originalRows.slice(start, end));
    return data;
}
/*Ajax返回值*/
function checkAjaxResult(result) {
    if (result["status"] == "success") {
        return true;
    } else if (result["status"] == "info") {
        $.messager.alert("提示", result["message"], "info");
        return false;
    } else if (result["status"] == "noLogin") {
        $.messager.alert("超时", result["message"], "info");
        return false;
    } else if (result["status"] == "error") {
        $.messager.alert("错误", "操作失败！<br>原因：" + result["message"], "error");
        return false;
    }
}
//Ajax提交代码 (form的ID、请求地址、操作成功提示语、需要返回值的id)
function ajaxFormSubmit(frmId, urlStr, successNote, backId) {
    var $form = $("#" + frmId);
    $form.ajaxSubmit({
        url: urlStr,
        dataType: "json",
        beforeSubmit: function (formData, jqForm, options) {
            $("body .easyui-linkbutton").linkbutton("disable");
        },
        success: function (responseText, statusText) {
            if (responseText["status"] != "error") {
                if (backId != "")$('#' + backId).val(responseText[backId]);
                if (successNote == "") {
                    $.messager.alert("提示", "操作成功！", "info");
                } else {
                    $.messager.alert("提示", successNote, "info");
                }
            } else {
                $.messager.alert("错误", "操作失败！原因：" + responseText["message"], "error");
            }
            $("body .easyui-linkbutton").linkbutton("enable");
        }
    });
}
/*Json转换*/
function form2JSON(id) {
    var arr = $("#" + id).serializeArray();
    var jsonStr = {};
    for (var i = 0; i < arr.length; i++) {
        if (arr[i]["value"] != null && arr[i]["value"] != "") {
            if (jsonStr[arr[i]["name"]] != null) {
                jsonStr[arr[i]["name"]] = jsonStr[arr[i]["name"]] + "," + arr[i]["value"];
            } else {
                jsonStr[arr[i]["name"]] = arr[i]["value"];
            }
        }
    }
    return jsonStr;
}
/* DIV操作*/
function hideDiv(id) {
    var strHtml = $("#" + id);
    if (strHtml.is(":hidden") == true) {
        strHtml.show();
    } else if (strHtml.is(":hidden") == false) {
        strHtml.hide()
    }
    return;
}

/*List Map类*/
// 仿Map集合对象
function MapCopy() {
    this.elements = [];

    // 添加元素
    this.put = function (key, value) {
        this.elements.push({
            key: key,
            value: value
        });
    };

    // 获取元素
    this.get = function (key) {
        for (var i in this.elements) {
            if (this.elements[i].key == key) {
                return this.elements[i].value;
            }
        }
        return null;
    };

    // 包含指定的key, 包含返回true, 否则，返回false
    this.containKey = function (key) {
        for (var i in this.elements) {
            if (this.elements[i].key == key) {
                return true;
            }
        }
        return false;
    };

    // 获得Map集合中所有实体元素
    this.entrySet = function () {
        return this.elements;
    }
}
// 仿List集合
function ListCopy() {
    // 保存集合中数据的数组
    this.elements = [];
    // 集合长度
    this.length = 0;

    // 添加元素到集合
    this.add = function (value) {
        this.elements.push(value);
        this.length++;
    }

    // 获取集合中指定索引值的元素
    this.get = function (index) {
        if (this.length < index || index < 0) {
            throw StringFormat("非法的索引值, index: 应介于0到%s之间!", this.length);
            return null;
        }
        return this.elements[index];
    }

    // 判断集合中是否包含指定的value, 若包含返回true, 否则, 返回false
    this.contains = function (value) {
        for (var i in this.elements) {
            if (this.elements[i] == value) {
                return true;
            }
        }
        return false;
    }

    // 删除集合中指定索引值的value
    this.remove = function (index) {
        if (this.length < index || index < 0) {
            throw StringFormat("非法的索引值, index: 应介于0到%s之间!", this.length);
        }
        // 集合中index之后的所有数据向前移一位
        for (var i = index; i < this.length; i++) {
            this.elements[i] = this.elements[i + 1];
        }
        // 集合的长度-1
        this.length--;
        // 数组中的数据消除了, 但是数组的长度并没有改变
        var tempArr = [];
        for (var i = 0; i < this.length; i++) {
            var val = this.elements[i];
            if (val) {
                tempArr.push(val);
            }
        }
        this.elements = tempArr;
    }

    // 删除集合中指定的对象
    this.removeObject = function (value) {
        for (var i = 0; i < this.length; i++) {
            if (this.elements[i] == value) {
                this.remove(i);
                break;
            }
        }
    }

    // 获得集合的长度
    this.size = function () {
        return this.length;
    }

    // 将ListCopy对象转换为Array对象
    this.toArray = function () {
        return this.elements;
    }
}
// 静态方法, 目的是将一个数组对象转换为ListCopy对象并返回
ListCopy.newInstance = function (arr) {
    var listCopy = new ListCopy();
    listCopy.elements = arr;
    listCopy.length = arr.length;
    return listCopy;
};
/*List Map类结束*/

/*radio，checkBox,textArea、dialog、form操作*/
/*禁用body下所有easyui-linkbutton*/
function disableAllLinkBtn() {
    $("body .easyui-linkbutton").linkbutton("disable");
}
/*启用body下所有easyui-linkbutton*/
function enableAllLinkBtn() {
    $("body .easyui-linkbutton").linkbutton("enable");
}
/*禁用指定区域内的linkbutton*/
function disableRegLinkBtn(regionalID) {
    $('#' + regionalID + ' .easyui-linkbutton').linkbutton("disable");
}
/*启用指定区域内的linkbutton*/
function enableRegLinkBtn(regionalID) {
    $('#' + regionalID + ' .easyui-linkbutton').linkbutton("enable");
}
/*禁用单个linkbutton*/
function disableLinkBtn(btnId) {
    $('#' + btnId).linkbutton("disable");
}
/*启用单个linkbutton*/
function enableLinkBtn(btnId) {
    $('#' + btnId).linkbutton("enable");
}
/*清除textArea值*/
function cleanTextAreaValue(id) {
    $('#' + id).val('').text('');
}
/*checkBox全选反选*/
function checkBoxSelect(checkboxName, checkType) {
    $('input[name=' + checkboxName + ']').each(function () {
        $(this).prop('checked', checkType == 'unCheck' ? false : true);
    });
}
/*操作dialog的单选值一致(页面显示id,dialogRadioName)*/
function radioDialogSetValue(hostId, dialogRadioName) {
    var str = $('input[name=\"' + dialogRadioName + '\"]:checked').val();
    $("#" + hostId).val(str);//显示
    return false;
}
/*操作dialog的单选值一致(页面显示id,showid,dialogRadioName)*/
function radioDialogSetShowValue(hostId, showid, dialogRadioName) {
    var obj = $('input[name=\"' + dialogRadioName + '\"]:checked');
    var str = obj.val();
    var showValue = obj.attr('showValue');
    $("#" + hostId).val(str);//显示
    $("#" + showid).val(showValue);//显示
    return false;
}
/*操作dialog的多选值(页面显示id,dialogRadioName)*/
function checkBoxDialogSetValue(hostId, dialogCheckBoxName) {
    var str = "";
    $('input[name=\"' + dialogCheckBoxName + '\"]:checked').each(function () {
        str += $(this).val() + "、"
    });
    if (str.length > 0 && "、" == str.substring(str.length - 1, str.length))str = str.substring(0, str.length - 1);
    $('#' + hostId).val(str);
    return false;
}
/*操作dialog的多选值(页面显示id,,showid,dialogRadioName)*/
function checkBoxDialogSetShowValue(hostId, showid, dialogCheckBoxName) {
    var str = "";
    var showStr = "";
    $('input[name=\"' + dialogCheckBoxName + '\"]:checked').each(function () {
        str += $(this).val() + "、";
        showStr += $(this).attr('showValue') + "、";
    });
    if (str.length > 0 && "、" == str.substring(str.length - 1, str.length))str = str.substring(0, str.length - 1);
    if (showStr.length > 0 && "、" == showStr.substring(showStr.length - 1, showStr.length))showStr = showStr.substring(0, showStr.length - 1);
    $('#' + hostId).val(str);
    $('#' + showid).val(showStr);

    return false;
}
/*打开diolog，且赋值给dialog值 openSelectValueDialog(隐藏ID，名称,dialog的ID)*/
function openSelectValueDialog(hostId, dlgRadioName, dlgId) {
    var arr = $('#' + hostId).val().split("、");
    $("input[name='" + dlgRadioName + "']").each(function () {
        for (var i = 0; i < arr.length; i++) {
            if ($(this).val() == arr[i]) {
                $(this).attr("checked", true);
            }
        }
    });
    $('#' + dlgId).dialog('open');
}
/*加载diolog里面的值放在页面显示input内 readyValueFromDialog(隐藏ID，显示内容ID,dialog名称,dialog的ID)*/
function readyValueFromDialog(hostId, showId, dlgRadioName, dlgId) {
    var obj = $("#" + hostId);
    var arr = obj.val().split("、");
    var strTemp = "";
    $("input[name='" + dlgRadioName + "']").each(function () {
        for (var i = 0; i < arr.length; i++) {
            if ($(this).val() == arr[i]) {
                strTemp += $(this).attr("showValue") + "、";
            }
        }
    });
    if (strTemp.length > 0 && "、" == strTemp.substring(strTemp.length - 1, strTemp.length))strTemp = strTemp.substring(0, strTemp.length - 1);
    $("#" + showId).val(strTemp);
}
/*打开dialog*/
function openDialog(dialogId) {
    $('#' + dialogId).dialog('open');
}
/*关闭dialog*/
function closeDialog(dialogId) {
    $('#' + dialogId).dialog('close');
}
/*静态跳转*/
function pageLocation(url) {
    window.location.href = url;
}
/*表单充值*/
function resetForm(id) {
    $('#' + id).form('reset');
}
/*表单提交*/
function formSubmit(id) {
    $('#' + id).submit();
}
/*radio，checkBox,textArea、dialog、form操作类结束*/


/*日期类*/
/*日期格式转换*/
function formatDatebox(value) {
    if (value == null || value == '') {
        return '';
    }
    var dt;
    if (value instanceof Date) {
        dt = value;
    } else {
        dt = new Date(value);
    }
    var month = dt.getMonth() + 1;
    var day = dt.getDate();
    return dt.getFullYear() + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day); //扩展的Date的format方法(上述插件实现)
}
/*格式化所有easyui-datebox*/
function formatDate() {
    $('.easyui-datebox').each(function () {
        $(this).datebox({
            editable: false
        });
    });
    $.fn.datebox.defaults.formatter = function (date) {
        var y = date.getFullYear();
        var m = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var d = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
        return y + '-' + m + '-' + d;
    }
}
/****
 * 获取年份
 * param recentYears = 3 例如：近3年
 * */
function generatorYears(recentYears) {
    var currentYear = (new Date()).getYear() + 1900;
    var years = "[";
    for (var i = recentYears - 1; i > -1; i--) {
        years = years + "{value:'" + (currentYear - i) + "',text:'" + (currentYear - i) + "'}";
        if (0 != i)  years += ",";
    }
    years += "]";
    return eval('(' + years + ')');
}
/*日期类结束*/


/*预览图事件绑定（方法尚未完善）
 *id：存放预览图的class为‘forTempIco’的td的id
 * functionName：ajax删除文件和预览图的方法的名字
 */
function doCallback(fn, args) {
    fn.apply(this, args);
}
function tempIcon(id, functionName) {
    $('#' + id).delegate('.tempIco', {
        mouseover: function () {
            $(this).addClass('tempIcoBorder');
            $(this).find('.delIco').show();
            $(this).find('.checkIco').show();
        },
        mouseleave: function () {
            $(this).removeClass('tempIcoBorder');
            $(this).find('.delIco').hide();
            $(this).find('.checkIco').hide();
        }
    }).delegate('.delIco', 'click', function () {
        doCallback(eval(functionName));
//        if (flag) {
//            $(this).parent('.tempIco').remove();
//        } else {
//            return;
//        }
    }).delegate('.checkIco', 'click', function () {
        var tempIcoUrl = $(this).siblings('input').val();
        window.open(tempIcoUrl);
    });
}

function openPostWindow(url, name, data) {
    var tempForm = document.createElement("form");
    tempForm.id = "tempForm1";
    tempForm.method = "post";
    tempForm.action = url;
    tempForm.target = name;

    $.each(data, function (id, value) {
        var hideInput = document.createElement("input");
        hideInput.type = "hidden";
        hideInput.name = id;
        hideInput.value = value;
        tempForm.appendChild(hideInput);
    });
    if (window.ActiveXObject || "ActiveXObject" in window) {
        //IE9+是支持addEventListener的，但是IE11浏览器中是不支持原来IE中独有的事件绑定attachEvent。.
        try {
            tempForm.attachEvent("submit", function () {
            });        //IE
        } catch (e) {
            tempForm.addEventListener("submit", function () {
            }, false); //IE11
        }

    } else {
        tempForm.addEventListener("submit", function () {
        }, false);    //firefox
    }
    document.body.appendChild(tempForm);
    $("#tempForm1").trigger("submit")
    document.body.removeChild(tempForm);
}

Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
function formatDataFromNumber(t){
    if(t==null||t==""||t=="null")return "";
    var date = new Date(t);
    return date.Format("yyyy-MM-dd");

}