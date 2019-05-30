package com.ybb.mall.web.rest.controller;

import com.ybb.mall.config.ApplicationProperties;
import com.ybb.mall.web.rest.baidu.ueditor.ActionEnter;
import com.ybb.mall.web.rest.util.UploadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private final ApplicationProperties applicationProperties;

    public UEditorController(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @RequestMapping(value = "/exec")
    @ResponseBody
    public String exec(@RequestParam("action") String param, MultipartFile upfile, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        if(param != null && param.equals("uploadimage") && upfile != null){
            return UploadUtils.uploadUEditor(upfile, applicationProperties.getFilePath(), applicationProperties.getPlantImage());
        }else {
            response.setContentType("application/javascript");
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
        return "";
    }


}
