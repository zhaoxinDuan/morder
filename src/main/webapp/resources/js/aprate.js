/**
 * Created by amis on 2016/1/18.
 */

//加法函数
function accAdd(arg1, arg2) {
    var r1, r2, m;
    try {
        r1 = arg1.toString().split(".")[1].length;
    }
    catch (e) {
        r1 = 0;
    }
    try {
        r2 = arg2.toString().split(".")[1].length;
    }
    catch (e) {
        r2 = 0;
    }
    m = Math.pow(10, Math.max(r1, r2));
    return (arg1 * m + arg2 * m) / m;
}
//给Number类型增加一个add方法，，使用时直接用 .add 即可完成计算。
Number.prototype.add = function (arg) {
    return accAdd(arg, this);
};

//减法函数
function accSub(arg1, arg2) {
    var r1, r2, m, n;
    try {
        r1 = arg1.toString().split(".")[1].length;
    }
    catch (e) {
        r1 = 0;
    }
    try {
        r2 = arg2.toString().split(".")[1].length;
    }
    catch (e) {
        r2 = 0;
    }
    m = Math.pow(10, Math.max(r1, r2));
    //last modify by deeka
    //动态控制精度长度
    n = (r1 >= r2) ? r1 : r2;
    return ((arg1 * m - arg2 * m) / m).toFixed(n);
}

//给Number类型增加一个add方法，，使用时直接用 .sub 即可完成计算。
Number.prototype.sub = function (arg) {
    return accSub(this, arg);
};

//乘法函数
function accMul(arg1, arg2) {
    var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
    try {
        m += s1.split(".")[1].length;
    }
    catch (e) {
    }
    try {
        m += s2.split(".")[1].length;
    }
    catch (e) {
    }
    return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
}
//给Number类型增加一个mul方法，使用时直接用 .mul 即可完成计算。
Number.prototype.mul = function (arg) {
    return accMul(arg, this);
};

//除法函数
function accDiv(arg1, arg2) {
    var t1 = 0, t2 = 0, r1, r2;
    try {
        t1 = arg1.toString().split(".")[1].length;
    }
    catch (e) {
    }
    try {
        t2 = arg2.toString().split(".")[1].length;
    }
    catch (e) {
    }
    with (Math) {
        r1 = Number(arg1.toString().replace(".", ""));
        r2 = Number(arg2.toString().replace(".", ""));
        return (r1 / r2) * pow(10, t2 - t1);
    }
}
//给Number类型增加一个div方法，，使用时直接用 .div 即可完成计算。
Number.prototype.div = function (arg) {
    return accDiv(this, arg);
};

function momrateRate(topData){
    //获取data
    var data = eval(topData.data);
    var newJsonData = {};
    var newData = [];
    newData.push('[');
    for(var i in data){
        if(i!=0){
            newData.push(',');
        }
        var dataArr = data[i].data;
        var newDataArr = new Array();
        var preArrData = 0;
        for(var j=0;j<dataArr.length;j++){
            if(preArrData==0){
                newDataArr[j] = 0;
            }else{
                newDataArr[j] = accMul(accDiv(accSub(dataArr[j],preArrData),preArrData),100).toFixed(4);
            }
            preArrData = dataArr[j];
        }
        newData.push('{data:[');
        newData.push(newDataArr);
        newData.push('],name:"');
        newData.push(data[i].name);
        newData.push('"}');

    }
    newData.push(']');
    newJsonData['data'] = eval(newData.join(""));
    newJsonData['type'] = "line";
    newJsonData['title'] = topData.title+"（环比增长率）";
    newJsonData['subtitle'] = topData.subtitle;
    newJsonData['xcategories'] = topData.xcategories;
    newJsonData['ytitle'] = topData.ytitle;
    return newJsonData;
}

function anRate(pastFullData,curFullData){

    //获取当期data
    var curData = eval(curFullData.data);
    //获取往期data
    var pastData = eval(pastFullData.data);
    if(curData.length!=pastData.length){
        $.messager.alert('提示', '当期和往期数据项不一致，请联系管理员检查数据！', 'error');
    }
    var newJsonData = {};
    var newData = [];
    var pastXcategoriesArr = eval(pastFullData.xcategories);
    var curXcategoriesArr = eval(curFullData.xcategories);
    var pastXcategories = null;
    var curXcategories = null;
    var waring;
    newData.push('[');
    for(var i in curData){
        if(i!=0){
            newData.push(',');
        }
        var dataArr = curData[i].data;
        var pastDataArr = pastData[i].data;
        var newDataArr = new Array();
        var preArrData = 0;
        for(var j=0;j<dataArr.length;j++){
            waring = false;
            curXcategories = curXcategoriesArr[j];
            for(var k in pastXcategoriesArr){
                //暂时只判断年，截取年后对比数据
                pastXcategories = pastXcategoriesArr[k];
                if(pastXcategories.substring(4,pastXcategories.length)==curXcategories.substring(4,pastXcategories.length)){
                    preArrData = pastDataArr[k];
                    waring = true;
                    break;
                }
            }
            if(!waring){
                newDataArr[j] = 0;
            }else{
                newDataArr[j] = accMul(accDiv(accSub(dataArr[j],preArrData),preArrData),100).toFixed(4);
            }


        }
        newData.push('{data:[');
        newData.push(newDataArr);
        newData.push('],name:"');
        newData.push(curData[i].name);
        newData.push('"}');
    }
    newData.push(']');
    newJsonData['data'] = eval(newData.join(""));
    newJsonData['type'] = "line";
    newJsonData['title'] = curFullData.title+"（同比增长率）";
    newJsonData['subtitle'] = curFullData.subtitle;
    newJsonData['xcategories'] = curFullData.xcategories;
    newJsonData['ytitle'] = curFullData.ytitle;
    return newJsonData;
}



