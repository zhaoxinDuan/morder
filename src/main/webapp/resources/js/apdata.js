/**
 * Created by amis on 2015/6/30.
 */

if (!this.APDATA) {
    this.APDATA = {};
}

(function () {

    //表格名称
    var title;
    //列数
    var colSize;
    //列名集合
    var colOptionArr;
    var colValueArr;
    var colValueJson;
    //总数据条数
    var rowTotal;
    //分组列名称-中文
    var xcategories;
    var subData;
    var fieldOpt;

    APDATA.parseData = function (data) {
        colOptionArr = [];
        colValueArr = [];
        colValueJson = [];
        title = data.title;
        xcategories = JSON.parse(data.xcategories);


        var subDataTem;
        if (data.type == "pie") {
            subDataTem = JSON.parse(data.data)[0];
            subData = subDataTem.data;
        } else {
            subData = JSON.parse(data.data);
            subDataTem = subData[0];
        }

        colSize = xcategories.length;
        rowTotal = subData.length;

        //将列信息和数据分离出来
        colOptionArr.push("[[");

        colOptionArr.push("{field:'");
        colOptionArr.push(0);
        colOptionArr.push("',title:'维度/周期',width:80}");
        for (var i = 0; i < colSize; i++) {
            //获取行数
            if (i == 0) {
                for (var j = 0; j < rowTotal; j++) {
                    colValueArr[j] = [];
                }
            }
            colOptionArr.push(",");
            //获取行定义
            colOptionArr.push("{field:'");
            colOptionArr.push(i + 1);
            colOptionArr.push("',sortable:true,title:'");


            colOptionArr.push(xcategories[i]);


            colOptionArr.push("',width:80}");
            //获取行数据

            for (var j = 0; j < rowTotal; j++) {
                if (i == 0) {
                    colValueArr[j].push("'");
                    colValueArr[j].push(i);
                    colValueArr[j].push("':'");
                    if (data.type == "pie") {
                        colValueArr[j].push(subData[j][0]);
                    } else {
                        colValueArr[j].push(subData[j].name);
                    }

                    colValueArr[j].push("'");
                }
                colValueArr[j].push(",");
                colValueArr[j].push("'");
                colValueArr[j].push(i + 1);
                colValueArr[j].push("':'");
                if (data.type == "pie") {
                    colValueArr[j].push(subData[j][1]);
                } else {
                    if (subData[j].data[i] instanceof Array) {
                        colValueArr[j].push(subData[j].data[i][subData[j].data[i].length - 1]);
                    } else {
                        colValueArr[j].push(subData[j].data[i]);
                    }
                }
                colValueArr[j].push("'");
            }


        }
        //组装数据 { "total":条数, "rows":[ {"i
        colValueJson.push("{'total':");
        colValueJson.push(rowTotal);
        colValueJson.push(",'rows':[{");
        for (var i = 0; i < rowTotal; i++) {
            if (i != 0) {
                colValueJson.push("},{");
            }
            colValueJson.push(colValueArr[i].join(""));
        }
        colValueJson.push("}]");
        colValueJson.push("}");

        colOptionArr.push("]]");
    }
    APDATA.getGridTitle = function () {
        return title;
    }
    APDATA.getGridColumnOpt = function () {
        return colOptionArr.join("");
    }
    APDATA.getGridColumnData = function () {
        return colValueJson.join("");
    }
})();
