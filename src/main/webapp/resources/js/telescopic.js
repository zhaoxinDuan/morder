/*水平伸缩*/
var hTelescopicState=false;/*水平方向是否处于收缩状态，此参数只允许HTelescopic()方法使用*/
var hTelescopicDelay = 300;/*水平伸缩延时，以毫秒为单位，此参数只允许HTelescopic()方法使用*/
function HTelescopic(){
    if(!hTelescopicState){
        hTelescopicState = true;/*更新状态*/
        $('#arrowH').animate({left:'0px'},hTelescopicDelay);
        $('#arrowHImg').removeClass().addClass('arrowHD');
        $('.cRight').animate({left:'6px'},hTelescopicDelay);
    } else {
        hTelescopicState = false;/*更新状态*/
        $('#arrowH').animate({left:'210px'},hTelescopicDelay);
        $('#arrowHImg').removeClass().addClass('arrowHA');
        $('.cRight').animate({left:'216px'},hTelescopicDelay);
    }
    event.cancelBubble = true;/*取消事件冒泡*/
}

/*垂直伸缩*/
var vTelescopicState=false;/*垂直方向是否处于收缩状态，此参数只允许VTelescopic()方法使用*/
var vTelescopicDelay = 300;/*垂直伸缩延时，以毫秒为单位，此参数只允许VTelescopic()方法使用*/
function VTelescopic(){
    if(!vTelescopicState){
        vTelescopicState = true;/*更新状态*/
        $('#arrowV').animate({top:'0px'},vTelescopicDelay);
        $('#arrowVImg').removeClass().addClass('arrowVS');
        $('.indContent').animate({top:'6px'},vTelescopicDelay);
    } else {
        vTelescopicState = false;/*更新状态*/
        $('#arrowV').animate({top:'80px'},vTelescopicDelay);
        $('#arrowVImg').removeClass().addClass('arrowVW');
        $('.indContent').animate({top:'86px'},vTelescopicDelay);
    }
    event.cancelBubble = true;/*取消事件冒泡*/
}

