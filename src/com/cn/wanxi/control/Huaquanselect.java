package com.cn.wanxi.control;

import com.cn.wanxi.common.JDBC;
import com.cn.wanxi.model.TestModel;
import com.mysql.cj.xdevapi.JsonArray;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: SSJ
 * @Date: 11月14日 14:44
 */
@WebServlet("/testselect")
public class Huaquanselect extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sql = "SELECT max(aa.id),aa.name, aa.shou ,aa.zui FROM (\n" +
                "select id,name, shou ,zui from market_table  order by id  desc ) as aa  GROUP BY aa.name order by aa.id desc  LIMIT 2";
        ResultSet resultSet = JDBC.getResultSet(sql);
        int shouint = 0;
        int zuiint = 0;
        List<TestModel> list=new ArrayList<>();
        try {
            while (resultSet.next()) {
                TestModel testModel=new TestModel();

                shouint = Integer.parseInt(resultSet.getString("shou"));
                zuiint = Integer.parseInt(resultSet.getString("zui"));
                testModel.setShou(shouint);
                testModel.setZui(zuiint);
                testModel.setName(resultSet.getString("name"));

                list.add(testModel);
            }
        } catch (Exception e) {

        }
//        JsonArray jsonValues=new JsonArray();
        JSONArray jsonArray=JSONArray.fromObject(list);

        resp.getWriter().println(jsonArray.toString());
    }
}
