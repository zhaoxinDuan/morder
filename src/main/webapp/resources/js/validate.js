/*�绰������У��*/
/*У����ͨ�绰���������,����Ǳ���*/
function isTel(id,note,isNotNull){
    var obj = $("#" + id);
    var str = obj.val();
    var reg0 = /^[0-9]{3,4}(\-)[0-9]{7,8}(\-[0-9]{1,4})?$/;
    str = trimstr(id);
    obj.val(str);
    var my = false;
    if (reg0.test(str))my = true;//�����ж�
    if (!isNotNull) {//��Ϊ��
        if(!my&&!isEmptyJd(id)){
            $.messager.alert("��ʾ",  "��"+note+"����ʽ����", "info", function () {
                obj.focus();
            });
            return true;
        }
    } else {//����Ϊ��
        if(my||(!isEmptyJd(id)&&!isNumberLenJd(id,7,8))){

        }else{
            $.messager.alert("��ʾ", "��"+note+"��Ϊ�ջ��ʽ����", "info", function () {
                obj.focus();
            });
            return true  ;
        }
    }
    return false;
}
/*�ֻ���У�飬������Ǳ�����*/
function isMobile(id,note,isNotNull) {
    var obj = $("#" + id);
    var str = obj.val();
    var reg0 = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/;//11λ����
    str = trimstr(id);
    obj.val(str);
    var my = false;
    if (reg0.test(str))my = true;//�����ж�
    if (!isNotNull) {//��Ϊ��
        if(!my&&!isEmptyJd(id)){
            $.messager.alert("��ʾ",  "��"+note+"����ʽ����", "info", function () {
                obj.focus();
            });
            return true
        }
    } else {//����Ϊ��
        if(my||(!isEmptyJd(id)&&!isNumberLenJd(id,10,12))){

        }else{
            $.messager.alert("��ʾ", "��"+note+"��Ϊ�ջ��ʽ����", "info", function () {
                obj.focus();
            });
            return true
        }
    }
    return false;
}
/*�ж��ֻ���̻���ʽ������Ǳ���*/
function isLinkNo(id,note,isNotNull) {//isNotNullĬ��Ϊfase����Ϊ�գ�isNotNullΪtrueĬ��Ϊ����Ϊ��
    var obj = $("#" + id);
    var str = obj.val();
    var reg0 = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/;//11λ����
    //var reg1 = /^153\d{4,8}$/;
    var reg2 = /^[0-9]{3,4}(\-)[0-9]{7,8}(\-[0-9]{1,4})?$/;//���Ҵ���(2��3λ)-����(2��3λ)-�绰����(7��8λ)-�ֻ���(3λ)"
    str = trimstr(id);
    obj.val(str);
    var my = false;
    if (reg0.test(str)||reg2.test(str))my = true;//�����ж�
    if (!isNotNull) {//��Ϊ��
        if(!my&&!isEmptyJd(id)){
            $.messager.alert("��ʾ",  "��"+note+"����ʽ����", "info", function () {
                obj.focus();
            });
            return true
        }
    } else if(isNotNull) { //����Ϊ��
        if(my||(!isEmptyJd(id)&&!isNumberLenJd(id,6,8))){

        }else{
            $.messager.alert("��ʾ", "��"+note+"��Ϊ�ջ��ʽ����", "info", function () {
                obj.focus();
            });
            return true
        }
    }
    return false;
}
/*�绰������У�����*/


/*��Ǯ�����ָ�ʽУ��*/
/* �ж������Ƿ�Ϊ����ʽ(�磺5��5.00)��(Ҫ�жϵ�ID���Զ�����ʾ����)	*/
function isMoney(id, note) {
    var obj = $("#" + id);
    var str = obj.val();
    if (str != "") {
        str = trimstr(id);//ȥ�ո�
        obj.val(str);//�ض���ո�
        var a = /^[0-9]*(\.[0-9]{1,7})?$/;
        if (!a.test(str)) {//�жϸ�ʽ
            $.messager.alert("��ʾ", "��" + note + "����ʽ�����ϣ�", "info", function () {
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
/*�ж������Ƿ�Ϊ����  (Ҫ�жϵ�ID���Զ�����ʾ����)*/
function isNumber(id, note) {
    var obj = $("#" + id);
    var str = obj.val();
    if (str != "") {
        str = trimstr(id);
        obj.val(str);//�ض���ո�
        var strP = /^\d+$/;
        if (!strP.test(str)) {
            $.messager.alert("��ʾ", " ��" + note + "����ʽ����ȷ : ����Ϊ����", "info", function () {
                obj.focus();
            });
            return true;
        }
    }
    return false;
}
/*�ж������Ƿ�Ϊ����  (Ҫ�жϵ�ID���Զ�����ʾ����)*/
function isNumberEasyUI(id,note,isNotNull){
    var obj = $("#" + id);
    var str = obj.numberbox('getValue');
    var reg0 = /^\d+$/;
    str = trimstr(id);
    obj.val(str);
    var my = false;
    if (reg0.test(str))my = true;//�����ж�
    if (!isNotNull) {//��Ϊ��
        if(!my&&!isEmptyJd(id)){
            $.messager.alert("��ʾ",  "��"+note+"������Ϊ���ָ�ʽ��", "info", function () {
                obj.focus();
            });
            return true;
        }
    } else {//����Ϊ��
        if(my||(!isEmptyJd(id))){

        }else{
            $.messager.alert("��ʾ", "��"+note+"������Ϊ���ұ���Ϊ���ָ�ʽ��", "info", function () {
                obj.focus();
            });
            return true;
        }
    }
    return false;
}
/*�ж������Ƿ�Ϊ����  (Ҫ�жϵ�ID���Զ�����ʾ����)*/
function isNumberLen(id, note, starLen, endLen) {
    var obj = $("#" + id);
    var str = obj.val();
    if (str != "") {
        str = trimstr(id);
        obj.val(str);//�ض���ո�
        var strP = /^\d+$/;
        if (!strP.test(str)) {
            $.messager.alert("��ʾ", " ��" + note + "����ʽ����ȷ : ����Ϊ����", "info", function () {
                obj.focus();
            });
            return true;
        } else {
            if (str.length < starLen || str.length > endLen)
                $.messager.alert("��ʾ", " ��" + note + "������Ӧ����" + starLen + "~" + endLen + "λ", "info", function () {
                    obj.focus();
                    return true;
                });

        }
    }
    return false
}
/*�ж������Ƿ�Ϊ����  (Ҫ�жϵ�ID���Զ�����ʾ����)*/
function isNumberLenJd(id,starLen, endLen) {
    var obj = $("#" + id);
    var str = obj.val();
    if (str != "") {
        str = trimstr(id);
        obj.val(str);//�ض���ո�
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
/*�����ж�*/
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
        $.messager.alert("��ʾ", " ��" + note + "�����ȹ��̡�(����Ϊ" + starLen + "~" + endLen + "������)", "info", function () {
            obj.focus();
        });
        return true;
    } else if (lenTotal / 2 > endLen) {
        $.messager.alert("��ʾ", " ��" + note + "�����ȹ�����(����Ϊ" + starLen + "~" + endLen + "������)", "info", function () {
            obj.focus();
        });
        return true;
    }
    return false;
}
/*
 * @param divisor ����
 * @param dividend ������
 * @param  flag  �Ƿ�ת�ٷֱ� trueΪת�ٷֱ� ��falseΪ��ת�ٷֱ�
 * @param  digit С��λ
 * @returns {number}
 */
function divisionForShow(divisor, dividend, flag, digit) {
    var total;
    digit = digit == null ? 2 : digit;//ȡС��λ
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
/*��Ǯ�����ָ�ʽУ�����*/


/*���䡢����������У��*/
/*����*/
function isMail(id,note,isNotNull){
    var obj = $("#" + id);
    var str = obj.val();
    var reg0 = /^[a-zA-Z0-9_\-]{1,}@[a-zA-Z0-9_\-]{1,}\.[a-zA-Z0-9_\-.]{1,}$/;
    str = trimstr(id);
    obj.val(str);
    var my = false;
    if (reg0.test(str))my = true;//�����ж�
    if (!isNotNull) {//��Ϊ��
        if(!my&&!isEmptyJd(id)){
            $.messager.alert("��ʾ",  "��"+note+"����ʽ����", "info", function () {
                obj.focus();
            });
            return true;
        }
    } else {//����Ϊ��
        if(my||(!isEmptyJd(id)&&!isNumberLenJd(id,6,6))){

        }else{
            $.messager.alert("��ʾ", "��"+note+"��Ϊ�ջ��ʽ����", "info", function () {
                obj.focus();
            });
            return true  ;
        }
    }
    return false;
}
/*��������У�飬����Ǳ���*/
function isPostalCode(id,note,isNotNull){
    var obj = $("#" + id);
    var str = obj.val();
    var reg0 = /^[0-9]{6}$/;
    str = trimstr(id);
    obj.val(str);
    var my = false;
    if (reg0.test(str))my = true;//�����ж�
    if (!isNotNull) {//��Ϊ��
        if(!my&&!isEmptyJd(id)){
            $.messager.alert("��ʾ",  "��"+note+"����ʽ����", "info", function () {
                obj.focus();
            });
            return true;
        }
    } else {//����Ϊ��
        if(my||(!isEmptyJd(id)&&!isNumberLenJd(id,6,6))){

        }else{
            $.messager.alert("��ʾ", "��"+note+"��Ϊ�ջ��ʽ����", "info", function () {
                obj.focus();
            });
            return true  ;
        }
    }
    return false;
}
/*��������ȥ���жϿ�*/
function isPostalCodeNotEmpty(id, note) {
    var obj = $("#" + id);
    var str = obj.val();
    str = trimstr(id);
    obj.val(str);
    var pattern = /^[0-9]{6}$/;
    if (str != "") {
        if (!pattern.test(str)) {
            var content = "" ;
            if (note == null || note == ""){
                content = "��������ȷ�ġ��������롿" ;
            }else{
                content = "��������ȷ�ġ�"+note+"��" ;
            }
            $.messager.alert("��ʾ", content, "info", function () {
                obj.focus();
            });
            return true;
        }
    } else {
        return;
    }
}
/*���䡢����������У�����*/


/*���ڡ�ʱ����У��*/
/*
 ��;������������ֹ�����Ƿ���ȷ������Ϊ�������ڵĸ�ʽ��ȷ��
 �ҽ�������>=��ʼ����
 ���룺
 startDate����ʼ���ڣ��ַ���
 endDate���������ڣ��ַ���
 ���أ�
 ���ͨ����֤����true,���򷵻�false
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
        $.messager.alert("��ʾ", "��ʼ���ڲ��ܴ�����ֹ����!", "info", function () {
            $startDate.focus();
        });
        return true;
    }
    return false;
}
/*easyui�ж�2�����������ר��*/
function checkTwoDateEasyUI(startID, endID, paramName) {
    var $startDate = $("#" + startID);
    var $endDate = $("#" + endID);
    var startDate = $startDate.datebox('getValue');
    var endDate = $endDate.datebox('getValue');
    if(startDate!=undefined&&startDate!=''&&startDate!=null&&endDate!=undefined&&endDate!=''&&endDate!=null){
        var d1 = new Date(startDate.replace(/\-/g, "\/"));
        var d2 = new Date(endDate.replace(/\-/g, "\/"));
        if (startDate != "" && endDate != "" && d1 > d2) {
            var alertStr = '';
            if (paramName != undefined && paramName != '' && paramName != null) {
                alertStr = paramName;
            }
            $.messager.alert("��ʾ", alertStr + "��ʼ���ڲ��ܴ�����ֹ����!", "info", function () {
                $startDate.focus();
            });
            return true;
        }
    }
    return false;
}
/*Date����ת����Ӣ��ʱ���ʽ 2014-12-15*/
function formatDateManually(date) {
    if (date != undefined || date != null || date != '') {
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
        return date.getFullYear() + "-" + month + "-" + currentDate;
    } else {
        return "";
    }
}
/*Date����ת����Ӣ��ʱ���ʽ 2014-12-15*/
function strFormatDate(val) {
    if (val != null) {
        var date = new Date(parseInt(val, 10));
        //�·�Ϊ0-11������+1���·�С��10ʱ����0
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
        return date.getFullYear() + "-" + month + "-" + currentDate + "";
    }
    return "";
}
//��λ��ʱ�䣬���� (13:35)
function isTime2w(name, note) {
    var obj = $("input[name=" + name + "]");
    var e = obj.val();
    obj.val(e);
    var a = e.match(/^(\d{1,2})(:)?(\d{1,2})$/);
    if (a == null) {
        $.messager.alert("��ʾ", "��" + note + "������ʱ���ʽ(08:11)", "info", function () {
            obj.focus();
        });
        return true;
    }
    if (a[1] > 24 || a[3] > 60 || a[4] > 60) {
        $.messager.alert("��ʾ", "��" + note + "����ʱ���ʽ,���� (08:11)", "info", function () {
            obj.focus();
        });
        return true;
    }
    return false;
}
//��λ��ʱ�䣬���� (13:35:38)    (������Ч��������)
function isTime3w(name, note) {
    var obj = $("input[name=" + name + "]");
    var e = obj.val();
    var a = e.match(/^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})$/);
    if (a == null) {
        $.messager.alert("��ʾ", "��" + note + "������ʱ���ʽ(08:10:15)", "info", function () {
            obj.focus();
        });
        return true;
    }
    if (a[1] > 24 || a[3] > 60 || a[4] > 60) {
        $.messager.alert("��ʾ", "��" + note + "������ʱ���ʽ,���� (08:10:15)", "info", function () {
            obj.focus();
        });
        return true;
    }
    return false;
}
// �����ڣ����� (1988-05)
function strDateTime2w(name, note) {
    var obj = $("input[name=" + name + "]");
    var e = obj.val();
    var r = e.match(/^(\d{1,4})(-|\/)(\d{1,2})$/);
    if (e != null && e != "") {
        if (r == null) {
            $.messager.alert("��ʾ", "��" + note + "������ʱ���ʽ,����  (1988-05��1988/05)", "info", function () {
                obj.focus();
            });
            return true;
        }
    }
    return false;
}
// �����ڣ����� (1988-05-01)
function strDateTime3w(name, note) {
    var obj = $("input[name='" + name + "']");
    var e = obj.val();
    var r = e.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
    if (e != null && e != "") {
        if (r == null) {
            $.messager.alert("��ʾ", "��" + note + "������ʱ���ʽ,����  (1988-05-01��1988/05/01)", "info", function () {
                obj.focus();
            });
            return true;
        }
    }
    return false;
}
// �����ڣ����� (1988-05-01)
function strDateTime3wNotNull(name, note) {
    var obj = $("input[name='" + name + "']");
    var e = obj.val();
    if (isEmptyByName(name, note))return true;
    var r = e.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
    if (e != null && e != "") {
        if (r == null) {
            $.messager.alert("��ʾ", "��" + note + "������ʱ���ʽ,����  (1988-05-01��1988/05/01)", "info", function () {
                obj.focus();
            });
            return true;
        }
    }
    return false;
}
// �����ڣ����� (1988-05-01)
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
            $.messager.alert("��ʾ", "��" + note + "������ʱ���ʽ,����  (1988-05-01��1988/05/01)", "info", function () {
                obj.focus();
            });
            return true;
        }
    }
    return false;
}
// ��ʱ�䣬���� (1988-05-01 13:35:38)
function strDateTimeAll(name, note) {
    var obj = $("input[name=" + name + "]");
    var e = obj.val();
    var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
    var r = e.match(reg);
    if (r == null) {
        $.messager.alert("��ʾ", "��" + note + "������ʱ���ʽ,����  (1988-05-01 13:35:38)", "info", function () {
            obj.focus();
        });
        return true;
    }
    return false;
}
/*���ڡ�ʱ����У�����*/


/*���֤У����*/
/*���֤У�飬����*/
function creNoJudgeSimple(id,isNotNull){
    var obj = $("#" + id);
    var str = obj.val();
    var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;//11λ����
    str = trimstr(id);
    obj.val(str);
    var my = false;
    if (reg.test(str))my = true;//�����ж�
    if (!isNotNull&&!isEmptyJd(id)) {//��Ϊ��
        if (!my) {
            $.messager.alert("��ʾ",  "֤�������ʽ����", "info", function () {
                obj.focus();
            });
            return true;
        }
    } else if(isNotNull&&!my) { //����Ϊ��
        $.messager.alert("��ʾ", "֤������Ϊ�ջ��ʽ����", "info", function () {
            obj.focus();
        });
        return true;
    }
    return false;
}
/*���֤��֤У��*/
function creNoJudge(id,isNotNull){
    var num = $('#'+id).val();
    if(num!=''&&num!=undefined&&num!=null){
        num = num.toUpperCase();
        if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num)))
        {
            $.messager.alert("��ʾ",  "֤���Ÿ�ʽ����", "info");
            $('#'+id).focus();
            return true;
        }else{
            var len, re;
            len = num.length;
            if (len == 15)
            {
                re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
                var arrSplit = num.match(re);
                var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]);
                var bGoodDay;
                bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
                if (!bGoodDay)
                {
                    $.messager.alert("��ʾ",  "֤���Ÿ�ʽ����", "info");
                    $('#'+id).focus();
                    return true;
                }else{
                    var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
                    var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
                    var nTemp = 0, i;
                    num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6);
                    for(i = 0; i < 17; i ++)
                    {
                        nTemp += num.substr(i, 1) * arrInt[i];
                    }
                    num += arrCh[nTemp % 11];
                    return false;
                }
            }
            else if (len == 18)
            {
                re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
                var arrSplit = num.match(re);
                var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
                var bGoodDay;
                bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
                if (!bGoodDay)
                {
                    $.messager.alert("��ʾ",  "֤���Ÿ�ʽ����", "info");
                    $('#'+id).focus();
                    return true;
                }else{
                    var valnum;
                    var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
                    var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
                    var nTemp = 0, i;
                    for(i = 0; i < 17; i ++)
                    {
                        nTemp += num.substr(i, 1) * arrInt[i];
                    }
                    valnum = arrCh[nTemp % 11];
                    if (valnum != num.substr(17, 1))
                    {
                        $.messager.alert("��ʾ",  "֤���Ÿ�ʽ����", "info");
                        $('#'+id).focus();
                        return true;
                    }else{
                        return false;
                    }
                }
            }
            else{
                $.messager.alert("��ʾ",  "֤���Ÿ�ʽ����", "info");
                $('#'+id).focus();
                return true;
            }
        }
    }else{
        if(isNotNull){/*�����ֶ�*/
            $.messager.alert("��ʾ",  "֤�����벻��Ϊ�ջ��ʽ����!", "info");
            $('#'+id).focus();
            return true;
        }else{/*�Ǳ����ֶ�*/
            return false;
        }
    }
}
/*���֤У�������*/


/*�ַ�����У��*/
/*
 ��;���ַ�1�ǰ����ַ���2
 ���룺str1���ַ�����str2�����������ַ���
 ���أ����ͨ����֤����true,���򷵻�false
 */
function isContainStr(id, cStr) {
    var obj = $("#" + id);
    var str = obj.val();
    var index = str.indexOf(cStr);
    if (index == -1) {
        $.messager.alert("��ʾ", "���ݲ����ڡ�" + cStr + "��", "info", function () {
            obj.focus();
        });
    }
    return false;
}
/*ȥ���ո�*/
function trimstrAll(id) {
    var obj = $("#" + id);
    var str = obj.val();
    obj.val(str.replace(/\s+/g, ""))
}
/*ȥ�����пո��   (�����޽�,������)*/
function trimstr(id) {
    var obj = $("#" + id);
    var str = obj.val();
    return str.replace(/\s+/g, "");
}
/*ȥ���ո���ж��Ƿ�Ϊ�� true ��*/
function isEmptyJd(id) {
    var obj = $("#" + id);
    var newString = trimstr(id);
    if (newString == null || newString == "") {
        return true;
    } else {
        return false;
    }
}
/*ȥ�����пո��(�����޽�,������)*/
function strTrimAll(str) {
    return str.replace(/\s+/g, "");
}
/*(����)ǰ��ո�   (�����޽�,������) */
function LRtrimstr(id) {
    var obj = $("#" + id);
    var str = obj.val();
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
/*(��)ǰ�ո�(�����޽�,������)*/
function Ltrimstr(id) {
    var obj = $("#" + id);
    var str = obj.val();
    return str.replace(/(^\s*)/g, "");
}
/*(��)��ո� */
function Rtrimstr(id) {
    var obj = $("#" + id);
    var str = obj.val();
    return str.replace(/(\s*$)/g, "");
}
/*�����ı���������������ַ�  (������Ч��������)*/
function inputTxt(txtValue) {
    var forbidChar = new Array("@", "#", "$", "%", "^", "&", "*", "����", "��", "'", "��", "��", "\"", "<", ">", "��", "��");
    for (var i = 0; i < forbidChar.length; i++) {
        if (txtValue.indexOf(forbidChar[i]) >= 0) {
            return "���������Ϣ: " + txtValue + " �к��зǷ��ַ�: " + forbidChar[i] + " �������";
        }
    }
    return "";
}
/*��ȡ��Ӣ���ַ�������*/
function getStrLength(strValue) {
    var strLength = strValue.length;
    for (var j = 0; j < strValue.length; j++) {
        if (strValue.charCodeAt(j) > 255) {
            strLength++;
        }
    }
    return strLength;
}
/*�滻�ַ���; str ; �����ַ���, Cstring::���ַ���,Dstring::���ַ���*/
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
// ��ʽ�ַ���������
// ����˵����format ��ʽ���ַ���, args(�������в���) ��������ʽ���ַ�����ռλ��(%s)�Ĳ���
// ����StringFormat("ŭ%s��%s!", "��", "��"); // ŭ�����!
function StringFormat(format, args) {
    // arguments��һ��α���飬ÿһ��js��������һ��arguments�������ڷ�װ�ú������յ����в���(Ҳ���Ǻ���ʵ�εļ���)
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
/**�ж��Ƿ��Ǻ��֡���ĸ���������**/
function isChinaOrNumbOrLett(id, note) {
    var obj = $("#" + id);
    var str = obj.val();
    str = trimstr(id);
    obj.val(str);
    var pattern = /[\u4E00-\u9FA5\w]{2,}$/;
    if (str != "") {
        if (!pattern.test(str)) {
            $.messager.alert("��ʾ", "��" + note + "�������ɺ��֡���ĸ���������", "info", function () {
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
/*�пո� */
function havSpace(id, note) {
    var obj = $("#" + id);
    var str = obj.val();
    if (str.indexOf(' ') >= 0) {
        $.messager.alert("��ʾ", "��" + note + "�������пո�", "info", function () {
            obj.focus();
        });
        return true;
    } else {
        return false;
    }
}
/*�ַ�����У�����*/


/*�п���*/
/*��ͨcombo�ж��Ƿ�ѡ��*/
function isSelect(id, note) {
    var obj = $("#" + id);
    var newString = trimstr(id);
    if (newString == null || newString == "") {
        $.messager.alert("��ʾ", "��ѡ��" + note + "��", "info", function () {
            obj.focus();
        });
        return true;
    } else {
        return false;
    }
}
/*easyui combo��������п�*/
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
        $.messager.alert("��ʾ", "��" + note + "������Ϊ��", "info",function(){
            $('#'+id).focus();
        });
        return true;
    } else {
        return false;
    }
}
/*ȥ���ո���ж��Ƿ�Ϊ�� true ��*/
function isEmpty(id, note) {
    var obj = $("#" + id);
    var newString = trimstr(id);
    if (newString == null || newString == "") {
        $.messager.alert("��ʾ", "��" + note + "������Ϊ��", "info", function () {
            obj.focus();
        });
        return true;
    } else {
        return false;
    }
}
/*ȥ���ո���ж��Ƿ�Ϊ�� true ��*/
function strIsEmpty(newString, note) {
    if (newString == null || newString == "") {
        $.messager.alert("��ʾ", "��" + note + "������Ϊ��", "info");
        return true;
    } else {
        return false;
    }
}
/*ȥ���ո���ж��Ƿ�Ϊ�� true ��*/
function isEmptyByName(name, note) {
    var obj = $("input[name='" + name + "']");
    var newString = obj.val();
    newString = newString.replace(/(^\s*)|(\s*$)/g, "");//ȥ�ո�
    if (newString == null || newString == "") {
        $.messager.alert("��ʾ", "��" + note + "������Ϊ��", "info", function () {
            obj.focus();
        });
        return true;
    } else {
        return false;
    }
}
/*easyui combo���������ȥ�ո�*/
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
    }else if (controlType == "easyui-datetimebox") {
        str = $("#" + id).datetimebox('getValue');
    }
    if (str != undefined && str != null && str != '') {
        str = str.replace(/\s+/g, "");
    }
    return str;
}
/*�п������*/

/*checkbox��radio��ѡ����֤*/

/* check��ѡ���ж�
 *ҳ������ʽ��<span>&nbsp;<input type="checkbox" name="reqExportItem" value="01" checked/>&nbsp;<label>��ҵ����</label><span class="impSpan">*</span></span>
 * itemsName��checkbox nameֵ
 * itemArray��������checkboxֵ����
*/
function checkboxRequiredJudge(itemsName,itemArray){
    var selectItems = itemArray;
    var flag = false;
    var note = '';
    $('input[name='+itemsName+']').each(function(){
        if(selectItems.indexOf($(this).val())>-1&&!$(this).is(":checked")){
            note += $(this).siblings('label').text()+'��';
            flag = true;
        }
    });
    if(flag){
        if (note.length > 0 && "��" == note.substring(note.length - 1, note.length))note = note.substring(0, note.length - 1);
        $.messager.alert("��ʾ", "��" + note + "��Ϊ��ѡ��");
    }
    return flag;
}

/*checkbox��radio��ѡ����֤*/




