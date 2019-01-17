package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.service.impl.CategoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * ClassName:Lynx
 * Description:
 *
 * @Date:2019/1/14 22:31
 * @Author:chinabobcat2008@gmail.com
 */
@WebServlet(name = "CategoryServlet",urlPatterns = "/CategoryServlet")
public class CategoryServlet extends BaseServlet {

    public String findAllCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        CategoryService categoryService = new CategoryServiceImpl();
        List<Category> cateList = categoryService.findAll();
        /*ObjectMapper objectMapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(resp.getOutputStream(),categoryService);*/
        writeValue(cateList,resp);
        return null;
    }
}
