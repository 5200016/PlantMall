package com.ybb.mall.web.rest.controller;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.config.ApplicationProperties;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
import com.ybb.mall.web.rest.util.UploadUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URISyntaxException;

/**
 * @Description : 文件上传管理
 * @Author 黄志成
 * @Date 2019-04-19
 * @Version
 */

@Api(description="文件上传管理")
@RestController
@RequestMapping("/mall")
public class UploadController {
    private final ApplicationProperties applicationProperties;

    public UploadController(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    /**
     * 上传植物图片
     * @param request
     * @param file
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("上传植物图片")
    @PostMapping("/file/plant_image")
    @Timed
    public ResultObj uploadMenuLogo(@ApiParam(name="file",value="上传文件",required=true) HttpServletRequest request, @RequestParam MultipartFile file) throws URISyntaxException {
        String plantImage = applicationProperties.getPlantImage();
        new File(UploadUtils.getFullPath(applicationProperties.getFilePath(),plantImage)).mkdirs();                               //创建文件夹
        if (!TypeUtils.isEmpty(file)) {
            return ResultObj.back(200,UploadUtils.start(file,plantImage));
        }
        return ResultObj.back(200,false);
    }
}
