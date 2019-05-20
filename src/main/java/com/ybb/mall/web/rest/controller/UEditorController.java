package com.ybb.mall.web.rest.controller;

import com.ybb.mall.web.rest.baidu.ueditor.ActionEnter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author 黄志成
 * @version
 */
@RestController
@CrossOrigin
@RequestMapping("/mall")
public class UEditorController {

    private Logger UEditorController = LoggerFactory.getLogger(UEditorController.class);

    @RequestMapping(value = "/exec")
    @ResponseBody
    public void exec(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
