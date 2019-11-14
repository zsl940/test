var daojishi = null;
var daojishishu = 0;
var shou = 0;
var zui = 0;
var numshou = 0;
var numzui = 0;

function computermodel() {

    var btvalue = document.getElementById("start").value;
    if (btvalue == "开始") {
        daojishi = setInterval(daojishifangfa, 1000);
        document.getElementById("start").value = "暂停";
    } else {
        clearInterval(daojishi);

        document.getElementById("start").value = "开始";
    }


}

$(function () {
    clearInterval(daojishi);
    daojishi = setInterval(shijianfangfa, 1000);

})


//显示倒计时
function shijianfangfa() {

    daojishishu = (60 - new Date().getSeconds()) % 10 + 1;
    document.getElementById("daojishi").innerHTML = daojishishu;
//查询数据库有没有新数据
    selectdata();
}
function selectdata() {
    $.ajax({
        url: "testselect",
        datatype: 'text',
        data: {},
        type: 'get',
        success: function (data) {
            data=eval(data);
            for (var i=0;i<data.length;i++){
                console.log(data)
                if(data[i].name=="aaa"){//自己
                    document.getElementById("shou").value = data[i].shou;
                    document.getElementById("zui").value = data[i].zui;
                }else {//对家
                    document.getElementById("computershou").value = data[i].shou;
                    document.getElementById("computerzui").value = data[i].zui;
                }
                daojishifangfa();
            }

        }
    })
}
function xunhuan() {
    clearInterval(daojishi);
    daojishi = setInterval(daojishifangfa, 1000);
}

function daojishifangfa() {


    if (daojishishu == 1) {
        // clearInterval(daojishi);
        // daojishishu = 5;
        document.getElementById("daojishi").innerHTML = "游戏结束了";
        // xianshidiannaoshuju();

        panduan();
        // xunhuan();
    } else {
        // daojishishu--;

    }

}

function xianshidiannaoshuju() {

    //显示电脑数据
    // numshou = parseInt(Math.random() * 10) + 1;
    // numzui = parseInt(Math.random() * 10) + 1;

    // document.getElementById("computer").innerHTML = "手：" + numshou + "</br>嘴：" + numzui;
    //显示用户数据

}

function panduan() {
    if (zui <= shou) {
        document.getElementById("youxijieguo").innerHTML = "电脑胜利";
        return;
    }
    if (numzui <= numshou) {
        document.getElementById("youxijieguo").innerHTML = "玩家胜利";
        return;
    }
    var count = numshou + shou;
    if (numzui == count && zui == count) {
        document.getElementById("youxijieguo").innerHTML = "平局";
        return;
    }
    if (numzui == count) {
        document.getElementById("youxijieguo").innerHTML = "电脑胜利";
    } else if (zui == count) {
        document.getElementById("youxijieguo").innerHTML = "玩家胜利";
    } else {
        document.getElementById("youxijieguo").innerHTML = "平局";

    }

}

function shoufangfa() {

    shou = document.getElementById("shou").value;
    if (shou.trim() == "") {
        shou = 0;
    } else {
        shou = parseInt(shou);
    }

}

function zuifangfa() {
    zui = document.getElementById("zui").value;
    if (zui.trim() == "") {
        zui = 0;
    } else {
        zui = parseInt(zui);
    }
}

function youxikaishi() {
    shoufangfa();
    zuifangfa();
    $.ajax({
        url: "testhuaquan",
        datatype: 'text',
        data: {shou: shou, zui: zui,name:document.getElementById("name").value},
        type: 'post',
        success: function (data) {
            numshou = data.shou;
            numzui = data.zui;
        }
    })
}