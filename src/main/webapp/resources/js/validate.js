

/*电话号码类校验*/
/*校验普通电话、传真号码,必填非必填*/
function isTel(id, note, isNotNull) {
    var obj = $("#" + id);
    var str = obj.val();
    var reg0 = /^[0-9]{3,4}(\-)[0-9]{7,8}(\-[0-9]{1,4})?$/;
    str = trimstr(id);
    obj.val(str);
    var my = false;
    if (reg0.test(str))my = true;//正则判断
    if (!isNotNull) {//可为空
        if (!my && !isEmptyJd(id)) {
            $.messager.alert("提示", "【" + note + "】格式错误", "info", function () {
                obj.focus();
            });
            return true;
        }
    } else {//不可为空
        if (my || (!isEmptyJd(id) && !isNumberLenJd(id, 7, 8))) {

        } else {
            $.messager.alert("提示", "【" + note + "】为空或格式错误", "info", function () {
                obj.focus();
            });
            return true;
        }
    }
    return false;
}
/*手机号校验，必填项非必填项*/
function isMobile(id, note, isNotNull) {
    var obj = $("#" + id);
    var str = obj.val();
    var reg0 = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/;//11位数字
    str = trimstr(id);
    obj.val(str);
    var my = false;
    if (reg0.test(str))my = true;//正则判断
    if (!isNotNull) {//可为空
        if (!my && !isEmptyJd(id)) {
            $.messager.alert("提示", "【" + note + "】格式错误", "info", function () {
                obj.focus();
            });
            return true
        }
    } else {//不可为空
        if (my || (!isEmptyJd(id) && !isNumberLenJd(id, 10, 12))) {

        } else {
            $.messager.alert("提示", "【" + note + "】为空或格式错误", "info", function () {
                obj.focus();
            });
            return true
        }
    }
    return false;
}
/*判断手机或固话格式，必填非必填*/
function isLinkNo(id, note, isNotNull) {//isNotNull默认为fase，可为空，isNotNull为true默认为不能为空
    var obj = $("#" + id);
    var str = obj.val();
    var reg0 = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/;//11位数字
    //var reg1 = /^153\d{4,8}$/;
    var reg2 = /^[0-9]{3,4}(\-)[0-9]{7,8}(\-[0-9]{1,4})?$/;//国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(3位)"
    str = trimstr(id);
    obj.val(str);
    var my = false;
    if (reg0.test(str) || reg2.test(str))my = true;//正则判断
    if (!isNotNull) {//可为空
        if (!my && !isEmptyJd(id)) {
            $.messager.alert("提示", "【" + note + "】格式错误", "info", function () {
                obj.focus();
            });
            return true
        }
    } else if (isNotNull) { //不可为空
        if (my || (!isEmptyJd(id) && !isNumberLenJd(id, 6, 8))) {

        } else {
            $.messager.alert("提示", "【" + note + "】为空或格式错误", "info", function () {
                obj.focus();
            });
            return true
        }
    }
    return false;
}
/*电话号码类校验结束*/


/*金钱、数字格式校验*/
/* 判断数据是否为金额格式(如：5或5.00)！(要判断的ID，自定义提示内容)	*/
function isMoney(id, note) {
    var obj = $("#" + id);
    var str = obj.val();
    if (str != "") {
        str = trimstr(id);//去空格
        obj.val(str);//重定义空格
        var a = /^[0-9]*(\.[0-9]{1,7})?$/;
        if (!a.test(str)) {//判断格式
            $.messager.alert("提示", "【" + note + "】格式不符合！", "info", function () {
                obj.focus();
            });
            return true;
        }
    } else {
        isEmpty(id, note);
        return true;
    }
    return false;
}
/*判断数据是否为数字  (要判断的ID，自定义提示内容)*/
function isNumber(id, note) {
    var obj = $("#" + id);
    var str = obj.val();
    if (str != "") {
        str = trimstr(id);
        obj.val(str);//重定义空格
        var strP = /^\d+$/;
        if (!strP.test(str)) {
            $.messager.alert("提示", " 【" + note + "】格式不正确 : 必须为数字", "info", function () {
                obj.focus();
            });
            return true;
        }
    }
    return false;
}
/*判断数据是否为数字  (要判断的ID，自定义提示内容)*/
function isNumberEasyUI(id, note, isNotNull) {
    var obj = $("#" + id);
    var str = obj.numberbox('getValue');
    var reg0 = /^\d+$/;
    str = trimstr(id);
    obj.val(str);
    var my = false;
    if (reg0.test(str))my = true;//正则判断
    if (!isNotNull) {//可为空
        if (!my && !isEmptyJd(id)) {
            $.messager.alert("提示", "【" + note + "】必须为数字格式！", "info", function () {
                obj.focus();
            });
            return true;
        }
    } else {//不可为空
        if (my || (!isEmptyJd(id))) {

        } else {
            $.messager.alert("提示", "【" + note + "】不可为空且必须为数字格式！", "info", function () {
                obj.focus();
            });
            return true;
        }
    }
    return false;
}
/*判断数据是否为数字  (要判断的ID，自定义提示内容)*/
function isNumberLen(id, note, starLen, endLen) {
    var obj = $("#" + id);
    var str = obj.val();
    if (str != "") {
        str = trimstr(id);
        obj.val(str);//重定义空格
        var strP = /^\d+$/;
        if (!strP.test(str)) {
            $.messager.alert("提示", " 【" + note + "】格式不正确 : 必须为数字", "info", function () {
                obj.focus();
            });
            return true;
        } else {
            if (str.length < starLen || str.length > endLen)
                $.messager.alert("提示", " 【" + note + "】长度应该在" + starLen + "~" + endLen + "位", "info", function () {
                    obj.focus();
                    return true;
                });

        }
    }
    return false
}
/*判断数据是否为数字  (要判断的ID，自定义提示内容)*/
function isNumberLenJd(id, starLen, endLen) {
    var obj = $("#" + id);
    var str = obj.val();
    if (str != "") {
        str = trimstr(id);
        obj.val(str);//重定义空格
        var strP = /^\d+$/;
        if (!strP.test(str)) {
            return true;
        } else {
            if (str.length < starLen || str.length > endLen)
                return true;
        }
    }
    return false
}
/*长度判断*/
function isStrLen(id, note, starLen, endLen) {
    var obj = $("#" + id);
    var str = obj.val();
    var lenTotal = 0;
    var arr = str.split("");
    if (starLen != 0) {
        if (isEmpty(id, note))return true;
    }
    for (var i = 0; i < arr.length; i++) {
        if (arr[i].charCodeAt(0) < 299) {
            lenTotal++
        } else {
            lenTotal += 2;
        }
    }
    if (lenTotal / 2 < starLen) {
        $.messager.alert("提示", " 【" + note + "】长度过短。(长度为" + starLen + "~" + endLen + "个汉字)", "info", function () {
            obj.focus();
        });
        return true;
    } else if (lenTotal / 2 > endLen) {
        $.messager.alert("提示", " 【" + note + "】长度过长。(长度为" + starLen + "~" + endLen + "个汉字)", "info", function () {
            obj.focus();
        });
        return true;
    }
    return false;
}
/*
 * @param divisor 除数
 * @param dividend 被除数
 * @param  flag  是否转百分比 true为转百分比 ，false为不转百分比
 * @param  digit 小数位
 * @returns {number}
 */
function divisionForShow(divisor, dividend, flag, digit) {
    var total;
    digit = digit == null ? 2 : digit;//取小数位
    var t1 = 0, t2 = 0, r1, r2;
    try {
        t1 = divisor.toString().split(".")[1].length;
    } catch (e) {
        alert(e);
    }
    try {
        t2 = dividend.toString().split(".")[1].length;
    } catch (e) {
        alert(e);
    }
    with (Math) {
        r1 = Number(divisor.toString().replace(".", ""));
        r2 = Number(dividend.toString().replace(".", ""));
        total = (r1 / r2) * pow(10, t2 - t1);
        if (flag) {
            total = total.toPercent(digit);
        } else {
            total = total.toFixed(digit);
        }
        return total;
    }
}
/*金钱、数字格式校验结束*/


/*邮箱、邮政编码类校验*/
/*邮箱*/
function isMail(id, note, isNotNull) {
    var obj = $("#" + id);
    var str = obj.val();
    var reg0 = /^[a-zA-Z0-9_\-]{1,}@[a-zA-Z0-9_\-]{1,}\.[a-zA-Z0-9_\-.]{1,}$/;
    str = trimstr(id);
    obj.val(str);
    var my = false;
    if (reg0.test(str))my = true;//正则判断
    if (!isNotNull) {//可为空
        if (!my && !isEmptyJd(id)) {
            $.messager.alert("提示", "【" + note + "】格式错误", "info", function () {
                obj.focus();
            });
            return true;
        }
    } else {//不可为空
        if (my || (!isEmptyJd(id) && !isNumberLenJd(id, 6, 6))) {

        } else {
            $.messager.alert("提示", "【" + note + "】为空或格式错误", "info", function () {
                obj.focus();
            });
            return true;
        }
    }
    return false;
}
/*邮政编码校验，必填非必填*/
function isPostalCode(id, note, isNotNull) {
    var obj = $("#" + id);
    var str = obj.val();
    var reg0 = /^[0-9]{6}$/;
    str = trimstr(id);
    obj.val(str);
    var my = false;
    if (reg0.test(str))my = true;//正则判断
    if (!isNotNull) {//可为空
        if (!my && !isEmptyJd(id)) {
            $.messager.alert("提示", "【" + note + "】格式错误", "info", function () {
                obj.focus();
            });
            return true;
        }
    } else {//不可为空
        if (my || (!isEmptyJd(id) && !isNumberLenJd(id, 6, 6))) {

        } else {
            $.messager.alert("提示", "【" + note + "】为空或格式错误", "info", function () {
                obj.focus();
            });
            return true;
        }
    }
    return false;
}
/*邮政编码去空判断空*/
function isPostalCodeNotEmpty(id, note) {
    var obj = $("#" + id);
    var str = obj.val();
    str = trimstr(id);
    obj.val(str);
    var pattern = /^[0-9]{6}$/;
    if (str != "") {
        if (!pattern.test(str)) {
            var content = "";
            if (note == null || note == "") {
                content = "请输入正确的【邮政编码】";
            } else {
                content = "请输入正确的【" + note + "】";
            }
            $.messager.alert("提示", content, "info", function () {
                obj.focus();
            });
            return true;
        }
    } else {
        return;
    }
}
/*邮箱、邮政编码类校验结束*/


/*日期、时间类校验*/
/*
 用途：检查输入的起止日期是否正确，规则为两个日期的格式正确，
 且结束如期>=起始日期
 输入：
 startDate：起始日期，字符串
 endDate：结束如期，字符串
 返回：
 如果通过验证返回true,否则返回false
 */
function checkTwoDate(startID, endID) {
    var $startDate = $("#" + startID);
    var $endDate = $("#" + endID);
    var startDate = $startDate.val();
    var endDate = $endDate.val();
    alert(startDate);
    alert(endDate);
    var d1 = new Date(startDate.replace(/\-/g, "\/"));
    var d2 = new Date(endDate.replace(/\-/g, "\/"));
    if (startDate != "" && endDate != "" && d1 > d2) {
        $.messager.alert("提示", "起始日期不能大于终止日期!", "info", function () {
            $startDate.focus();
        });
        return true;
    }
    return false;
}
/*easyui判断2个日期输入框专用*/
function checkTwoDateEasyUI(startID, endID, paramName) {
    var $startDate = $("#" + startID);
    var $endDate = $("#" + endID);
    var startDate = $startDate.datebox('getValue');
    var endDate = $endDate.datebox('getValue');
    if (startDate != undefined && startDate != '' && startDate != null && endDate != undefined && endDate != '' && endDate != null) {
        var d1 = new Date(startDate.replace(/\-/g, "\/"));
        var d2 = new Date(endDate.replace(/\-/g, "\/"));
        if (startDate != "" && endDate != "" && d1 > d2) {
            var alertStr = '';
            if (paramName != undefined && paramName != '' && paramName != null) {
                alertStr = paramName;
            }
            $.messager.alert("提示", alertStr + "起始日期不能大于终止日期!", "info", function () {
                $startDate.focus();
            });
            return true;
        }
    }
    return false;
}
/*Date对象转化成英文时间格式 2014-12-15*/
function formatDateManually(date) {
    if (date != undefined || date != null || date != '') {
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
        return date.getFullYear() + "-" + month + "-" + currentDate;
    } else {
        return "";
    }
}
/*Date对象转化成英文时间格式 2014-12-15*/
function strFormatDate(val) {
    if (val != null) {
        var date = new Date(parseInt(val, 10));
        //月份为0-11，所以+1，月份小于10时补个0
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
        return date.getFullYear() + "-" + month + "-" + currentDate + "";
    }
    return "";
}
//两位短时间，形如 (13:35)
function isTime2w(name, note) {
    var obj = $("input[name=" + name + "]");
    var e = obj.val();
    obj.val(e);
    var a = e.match(/^(\d{1,2})(:)?(\d{1,2})$/);
    if (a == null) {
        $.messager.alert("提示", "【" + note + "】不是时间格式(08:11)", "info", function () {
            obj.focus();
        });
        return true;
    }
    if (a[1] > 24 || a[3] > 60 || a[4] > 60) {
        $.messager.alert("提示", "【" + note + "不是时间格式,例如 (08:11)", "info", function () {
            obj.focus();
        });
        return true;
    }
    return false;
}
//三位短时间，形如 (13:35:38)    (方法无效，待测试)
function isTime3w(name, note) {
    var obj = $("input[name=" + name + "]");
    var e = obj.val();
    var a = e.match(/^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})$/);
    if (a == null) {
        $.messager.alert("提示", "【" + note + "】不是时间格式(08:10:15)", "info", function () {
            obj.focus();
        });
        return true;
    }
    if (a[1] > 24 || a[3] > 60 || a[4] > 60) {
        $.messager.alert("提示", "【" + note + "】不是时间格式,例如 (08:10:15)", "info", function () {
            obj.focus();
        });
        return true;
    }
    return false;
}
// 短日期，形如 (1988-05)
function strDateTime2w(name, note) {
    var obj = $("input[name=" + name + "]");
    var e = obj.val();
    var r = e.match(/^(\d{1,4})(-|\/)(\d{1,2})$/);
    if (e != null && e != "") {
        if (r == null) {
            $.messager.alert("提示", "【" + note + "】不是时间格式,例如  (1988-05或1988/05)", "info", function () {
                obj.focus();
            });
            return true;
        }
    }
    return false;
}
// 短日期，形如 (1988-05-01)
function strDateTime3w(name, note) {
    var obj = $("input[name='" + name + "']");
    var e = obj.val();
    var r = e.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
    if (e != null && e != "") {
        if (r == null) {
            $.messager.alert("提示", "【" + note + "】不是时间格式,例如  (1988-05-01或1988/05/01)", "info", function () {
                obj.focus();
            });
            return true;
        }
    }
    return false;
}
// 短日期，形如 (1988-05-01)
function strDateTime3wNotNull(name, note) {
    var obj = $("input[name='" + name + "']");
    var e = obj.val();
    if (isEmptyByName(name, note))return true;
    var r = e.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
    if (e != null && e != "") {
        if (r == null) {
            $.messager.alert("提示", "【" + note + "】不是时间格式,例如  (1988-05-01或1988/05/01)", "info", function () {
                obj.focus();
            });
            return true;
        }
    }
    return false;
}
// 短日期，形如 (1988-05-01)
function strDateTime3wByFrmNotNull(frm, name, note) {
    var $subForm = $("#" + frm);
    var obj = $subForm.find("input[name='" + name + "']");
    var e = obj.val();
    if (strIsEmpty(e, note)) {
        return true;
    }
    var r = e.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
    if (e != null && e != "") {
        if (r == null) {
            $.messager.alert("提示", "【" + note + "】不是时间格式,例如  (1988-05-01或1988/05/01)", "info", function () {
                obj.focus();
            });
            return true;
        }
    }
    return false;
}
// 长时间，形如 (1988-05-01 13:35:38)
function strDateTimeAll(name, note) {
    var obj = $("input[name=" + name + "]");
    var e = obj.val();
    var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
    var r = e.match(reg);
    if (r == null) {
        $.messager.alert("提示", "【" + note + "】不是时间格式,例如  (1988-05-01 13:35:38)", "info", function () {
            obj.focus();
        });
        return true;
    }
    return false;
}
/*日期、时间类校验结束*/


/*身份证校检类*/
/*身份证校验，长度*/
function creNoJudgeSimple(id, isNotNull) {
    var obj = $("#" + id);
    var str = obj.val();
    var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;//11位数字
    str = trimstr(id);
    obj.val(str);
    var my = false;
    if (reg.test(str))my = true;//正则判断
    if (!isNotNull && !isEmptyJd(id)) {//可为空
        if (!my) {
            $.messager.alert("提示", "证件号码格式错误", "info", function () {
                obj.focus();
            });
            return true;
        }
    } else if (isNotNull && !my) { //不可为空
        $.messager.alert("提示", "证件号码为空或格式错误", "info", function () {
            obj.focus();
        });
        return true;
    }
    return false;
}
/*身份证验证校检*/
function creNoJudge(id, isNotNull) {
    var num = $('#' + id).val();
    if (num != '' && num != undefined && num != null) {
        num = num.toUpperCase();
        if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num))) {
            $.messager.alert("提示", "证件号格式错误！", "info");
            $('#' + id).focus();
            return true;
        } else {
            var len, re;
            len = num.length;
            if (len == 15) {
                re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
                var arrSplit = num.match(re);
                var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]);
                var bGoodDay;
                bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
                if (!bGoodDay) {
                    $.messager.alert("提示", "证件号格式错误！", "info");
                    $('#' + id).focus();
                    return true;
                } else {
                    var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
                    var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
                    var nTemp = 0, i;
                    num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6);
                    for (i = 0; i < 17; i++) {
                        nTemp += num.substr(i, 1) * arrInt[i];
                    }
                    num += arrCh[nTemp % 11];
                    return false;
                }
            }
            else if (len == 18) {
                re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
                var arrSplit = num.match(re);
                var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
                var bGoodDay;
                bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
                if (!bGoodDay) {
                    $.messager.alert("提示", "证件号格式错误！", "info");
                    $('#' + id).focus();
                    return true;
                } else {
                    var valnum;
                    var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
                    var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
                    var nTemp = 0, i;
                    for (i = 0; i < 17; i++) {
                        nTemp += num.substr(i, 1) * arrInt[i];
                    }
                    valnum = arrCh[nTemp % 11];
                    if (valnum != num.substr(17, 1)) {
                        $.messager.alert("提示", "证件号格式错误！", "info");
                        $('#' + id).focus();
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            else {
                $.messager.alert("提示", "证件号格式错误！", "info");
                $('#' + id).focus();
                return true;
            }
        }
    } else {
        if (isNotNull) {/*必填字段*/
            $.messager.alert("提示", "证件号码不可为空或格式错误!", "info");
            $('#' + id).focus();
            return true;
        } else {/*非必填字段*/
            return false;
        }
    }
}
/*身份证校检类结束*/


/*字符串类校验*/
/*
 用途：字符1是包含字符串2
 输入：str1：字符串；str2：被包含的字符串
 返回：如果通过验证返回true,否则返回false
 */
function isContainStr(id, cStr) {
    var obj = $("#" + id);
    var str = obj.val();
    var index = str.indexOf(cStr);
    if (index == -1) {
        $.messager.alert("提示", "内容不存在【" + cStr + "】", "info", function () {
            obj.focus();
        });
    }
    return false;
}
/*去掉空格*/
function trimstrAll(id) {
    var obj = $("#" + id);
    var str = obj.val();
    obj.val(str.replace(/\s+/g, ""))
}
/*去掉所有空格后   (方法无郊,待完善)*/
function trimstr(id) {
    var obj = $("#" + id);
    var str = obj.val();
    return str.replace(/\s+/g, "");
}
/*去掉空格后，判断是否为空 true 是*/
function isEmptyJd(id) {
    var obj = $("#" + id);
    var newString = trimstr(id);
    if (newString == null || newString == "") {
        return true;
    } else {
        return false;
    }
}
/*去掉所有空格后(方法无郊,待完善)*/
function strTrimAll(str) {
    return str.replace(/\s+/g, "");
}
/*(左右)前后空格   (方法无郊,待完善) */
function LRtrimstr(id) {
    var obj = $("#" + id);
    var str = obj.val();
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
/*(左)前空格(方法无郊,待完善)*/
function Ltrimstr(id) {
    var obj = $("#" + id);
    var str = obj.val();
    return str.replace(/(^\s*)/g, "");
}
/*(右)后空格 */
function Rtrimstr(id) {
    var obj = $("#" + id);
    var str = obj.val();
    return str.replace(/(\s*$)/g, "");
}
/*过滤文本框中输入的特殊字符  (方法无效、待测试)*/
function inputTxt(txtValue) {
    var forbidChar = new Array("@", "#", "$", "%", "^", "&", "*", "……", "“", "'", "￥", "×", "\"", "<", ">", "’", "”");
    for (var i = 0; i < forbidChar.length; i++) {
        if (txtValue.indexOf(forbidChar[i]) >= 0) {
            return "您输入的信息: " + txtValue + " 中含有非法字符: " + forbidChar[i] + " 请更正！";
        }
    }
    return "";
}
/*获取中英文字符串长度*/
function getStrLength(strValue) {
    var strLength = strValue.length;
    for (var j = 0; j < strValue.length; j++) {
        if (strValue.charCodeAt(j) > 255) {
            strLength++;
        }
    }
    return strLength;
}
/*替换字符串; str ; 内容字符串, Cstring::旧字符串,Dstring::新字符串*/
function strReplace(id, Cstring, Dstring) {
    var obj = $("#" + id);
    var str = obj.val();
    var blength = Cstring.length;
    var firstbyte = str.indexOf(Cstring, 0);
    for (var i = 0; i <= str.length - blength; i++) {
        tstring = str.substring(i, i + blength);
        if (tstring == Cstring) {
            str = str.substring(0, i) + Dstring + str.substring(i + blength, str.length);
        }
    }
    obj.val(str);
    return str;
}
// 格式字符串并返回
// 参数说明：format 格式化字符串, args(其余所有参数) 用于填充格式化字符串中占位符(%s)的参数
// 例：StringFormat("怒%s冲%s!", "发", "冠"); // 怒发冲冠!
function StringFormat(format, args) {
    // arguments是一个伪数组，每一个js函数都有一个arguments对象，用于封装该函数接收的所有参数(也就是函数实参的集合)
    var len = arguments.length;
    if (len == 0) {
        return '';
    }
    if (len == 1) {
        return arguments[0];
    }
    for (var i = 1; i < len; i++) {
        arguments[0] = arguments[0].replace("%s", arguments[i]);
    }
    return arguments[0];
}
/**判断是否是汉字、字母、数字组成**/
function isChinaOrNumbOrLett(id, note) {
    var obj = $("#" + id);
    var str = obj.val();
    str = trimstr(id);
    obj.val(str);
    var pattern = /[\u4E00-\u9FA5\w]{2,}$/;
    if (str != "") {
        if (!pattern.test(str)) {
            $.messager.alert("提示", "【" + note + "】必须由汉字、字母、数字组成", "info", function () {
                obj.focus();
            });
            return true;
        }
    } else {
        isEmpty(id, note);
        return true;
    }
    return false;
}
/*有空格 */
function havSpace(id, note) {
    var obj = $("#" + id);
    var str = obj.val();
    if (str.indexOf(' ') >= 0) {
        $.messager.alert("提示", "【" + note + "】不能有空格", "info", function () {
            obj.focus();
        });
        return true;
    } else {
        return false;
    }
}
/*字符串类校验结束*/


/*判空类*/
/*普通combo判断是否选中*/
function isSelect(id, note) {
    var obj = $("#" + id);
    var newString = trimstr(id);
    if (newString == null || newString == "") {
        $.messager.alert("提示", "请选择【" + note + "】", "info", function () {
            obj.focus();
        });
        return true;
    } else {
        return false;
    }
}
/*easyui combo依赖组件判空*/
function isEmptyEasyUI(id, note) {
    var classes = $('#' + id).attr('class').split(' ');
    var controlType = '';
    for (var i = 0; i <= classes.length; i++) {
        if (classes[i] != undefined && classes[i].indexOf('easyui-') >= 0) {
            controlType = classes[i];
        }
    }
    if (note == undefined || note == null || note == '') {
        note = $('#' + id).parent().prev().text().replace('*', '');
    }
    var newString = trimEasyUIValue(id, controlType);
    if (newString == null || newString == "") {
        $.messager.alert("提示", "【" + note + "】不能为空", "info", function () {
            $('#' + id).focus();
        });
        return true;
    } else {
        return false;
    }
}
/*去掉空格后，判断是否为空 true 是*/
function isEmpty(id, note) {
    var obj = $("#" + id);
    var newString = trimstr(id);
    if (newString == null || newString == "") {
        $.messager.alert("提示", "【" + note + "】不能为空", "info", function () {
            obj.focus();
        });
        return true;
    } else {
        return false;
    }
}
/*去掉空格后，判断是否为空 true 是*/
function strIsEmpty(newString, note) {
    if (newString == null || newString == "") {
        $.messager.alert("提示", "【" + note + "】不能为空", "info");
        return true;
    } else {
        return false;
    }
}
/*去掉空格后，判断是否为空 true 是*/
function isEmptyByName(name, note) {
    var obj = $("input[name='" + name + "']");
    var newString = obj.val();
    newString = newString.replace(/(^\s*)|(\s*$)/g, "");//去空格
    if (newString == null || newString == "") {
        $.messager.alert("提示", "【" + note + "】不能为空", "info", function () {
            obj.focus();
        });
        return true;
    } else {
        return false;
    }
}
/*easyui combo依赖类组件去空格*/
function trimEasyUIValue(id, controlType) {
    var str = '';
    if (controlType == "easyui-combo") {
        str = $("#" + id).combo('getValue');
    } else if (controlType == "easyui-combobox") {
        str = $("#" + id).combobox('getValue');
    } else if (controlType == "easyui-datebox") {
        str = $("#" + id).datebox('getValue');
    } else if (controlType == "easyui-numberbox") {
        str = $("#" + id).numberbox('getValue');
    } else if (controlType == "easyui-combogrid") {
        str = $("#" + id).combogrid('getValue');
    } else if (controlType == "easyui-combotree") {
        str = $("#" + id).combotree('getValue');
    } else if (controlType == "easyui-validatebox") {
        str = $("#" + id).val();
    } else if (controlType == "easyui-datetimebox") {
        str = $("#" + id).datetimebox('getValue');
    }
    if (str != undefined && str != null && str != '') {
        str = str.replace(/\s+/g, "");
    }
    return str;
}
/*判空类结束*/

/*checkbox、radio必选项验证*/

/* check必选项判断
 *页面代码格式：<span>&nbsp;<input type="checkbox" name="reqExportItem" value="01" checked/>&nbsp;<label>企业名称</label><span class="impSpan">*</span></span>
 * itemsName：checkbox name值
 * itemArray：必填项checkbox值数组
 */
function checkboxRequiredJudge(itemsName, itemArray) {
    var selectItems = itemArray;
    var flag = false;
    var note = '';
    $('input[name=' + itemsName + ']').each(function () {
        if (selectItems.indexOf($(this).val()) > -1 && !$(this).is(":checked")) {
            note += $(this).siblings('label').text() + '、';
            flag = true;
        }
    });
    if (flag) {
        if (note.length > 0 && "、" == note.substring(note.length - 1, note.length))note = note.substring(0, note.length - 1);
        $.messager.alert("提示", "【" + note + "】为必选项");
    }
    return flag;
}

/*checkbox、radio必选项验证*/



