/*ˮƽ����*/
var hTelescopicState=false;/*ˮƽ�����Ƿ�������״̬���˲���ֻ����HTelescopic()����ʹ��*/
var hTelescopicDelay = 300;/*ˮƽ������ʱ���Ժ���Ϊ��λ���˲���ֻ����HTelescopic()����ʹ��*/
function HTelescopic(){
    if(!hTelescopicState){
        hTelescopicState = true;/*����״̬*/
        $('#arrowH').animate({left:'0px'},hTelescopicDelay);
        $('#arrowHImg').removeClass().addClass('arrowHD');
        $('.cRight').animate({left:'6px'},hTelescopicDelay);
    } else {
        hTelescopicState = false;/*����״̬*/
        $('#arrowH').animate({left:'210px'},hTelescopicDelay);
        $('#arrowHImg').removeClass().addClass('arrowHA');
        $('.cRight').animate({left:'216px'},hTelescopicDelay);
    }
    event.cancelBubble = true;/*ȡ���¼�ð��*/
}

/*��ֱ����*/
var vTelescopicState=false;/*��ֱ�����Ƿ�������״̬���˲���ֻ����VTelescopic()����ʹ��*/
var vTelescopicDelay = 300;/*��ֱ������ʱ���Ժ���Ϊ��λ���˲���ֻ����VTelescopic()����ʹ��*/
function VTelescopic(){
    if(!vTelescopicState){
        vTelescopicState = true;/*����״̬*/
        $('#arrowV').animate({top:'0px'},vTelescopicDelay);
        $('#arrowVImg').removeClass().addClass('arrowVS');
        $('.indContent').animate({top:'6px'},vTelescopicDelay);
    } else {
        vTelescopicState = false;/*����״̬*/
        $('#arrowV').animate({top:'80px'},vTelescopicDelay);
        $('#arrowVImg').removeClass().addClass('arrowVW');
        $('.indContent').animate({top:'86px'},vTelescopicDelay);
    }
    event.cancelBubble = true;/*ȡ���¼�ð��*/
}


