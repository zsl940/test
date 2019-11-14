package com.cn.wanxi.control;

import com.cn.wanxi.common.JDBC;
import com.cn.wanxi.model.TestModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: SSJ
 * @Date: 11月14日 10:37
 */
@WebServlet("/testhuaquan")
public class HuaquanControl extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String shou = req.getParameter("shou");
        String zui = req.getParameter("zui");
        String name=req.getParameter("name");
//        System.out.println(shou + "-------" + zui);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("sss");
        String date = new Date().getTime() + "";
        System.out.println(date);
        String sqladd = "insert into market_table (name,shou,zui,time)values('"+name+"','" + shou + "','" + zui + "','" + date + "')";
        JDBC.upDate(sqladd);




    }
}
