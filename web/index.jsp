<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 11月14日
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<head>
    <title>$Title$</title>
</head>
<script src="huaquan.js">

</script>
<body>
<div>
    <div style="float: left">游戏倒计时：</div>
    <div id="daojishi" style="float: left"></div>

</div>
<%--<div style="clear: both"></div>--%>
<br>
<div>
    电脑玩家：
    <div id="computer">
        <label>手：</label>
        <input type="text" id="computershou">
        <label>嘴：</label>
        <input type="text" id="computerzui">
    </div>
    <br>
    <div>自己: <input type="text" name="name" id="name" value="aaa"></div>
    <label>手：</label>
    <input type="text" id="shou">
    <label>嘴：</label>
    <input type="text" id="zui">
    <br>
    <input type="button" value="提交" onclick="youxikaishi()">
    游戏结果：
    <div id="youxijieguo"></div>
</div>


</body>
</html>
