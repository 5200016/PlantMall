package com.ybb.mall.web.rest.util;

import com.alibaba.fastjson.JSONObject;
import com.ybb.mall.config.ApplicationProperties;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 上传工具类
 */
@Component
public class UploadUtils {
	private static Logger logger = LoggerFactory.getLogger(UploadUtils.class);

    private final ApplicationProperties applicationProperties;
    private static String fictitiousUrl;
    private static String documentUrl;

    public UploadUtils(ApplicationProperties applicationProperties){
        this.applicationProperties = applicationProperties;
        fictitiousUrl = applicationProperties.getFilePath();  //虚拟路径
        documentUrl = applicationProperties.getFilePrefix();  //虚拟路径（替代）
    }

    /**
     * 上传文件
     * @param myfile   文件流对象
     * @param fileName 存放文件夹名称
     * @return
     */
    public static String uploadDoc(MultipartFile myfile, String fileName){
    	if(!TypeUtils.isEmpty(myfile)){
            try{
                if(!myfile.isEmpty()){
                	logger.debug("上传文件长度: " + myfile.getSize());
                    logger.debug("上传文件类型: " + myfile.getContentType());
                    logger.debug("上传文件名称: " + myfile.getName());
                    logger.debug("上传文件原名: " + myfile.getOriginalFilename());
                	//截取得到文件的格式
                	String st = myfile.getOriginalFilename();
                	//获取图片名称
                	String realName = TypeUtils.getPicName(st);
                    //临时存放图片路径
                	StringBuffer tempUrl = new StringBuffer();
                    tempUrl.append("/");
                	//文件夹名称
                    tempUrl.append(fileName.trim());
                    tempUrl.append("/");

                	//向配置的虚拟路径存入图片
                    String realPath = fictitiousUrl + tempUrl.toString();
                    //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
                    FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, realName));
                    //将文件全名传递到前台
                    return tempUrl.toString() + realName;
                 }
    		}catch(Exception e){
    			logger.error(e.getMessage());
    			return "";
    		}
    	 }
    	return "";
    }

    public static String getFullPath(String filePath , String custom){
        String fullPath = filePath + custom;
        return fullPath.trim();
    }

    public static ResultObj start(MultipartFile file, String filePath, String fileName){
        if (TypeUtils.isEmpty(file)) {
            return ResultObj.backInfo(false, 200, "上传失败", null);
        }

        //创建文件夹
        new File(getFullPath(filePath, fileName)).mkdirs();

        String url = uploadDoc(file, fileName);
        String fullUrl = documentUrl + url ;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url",fullUrl);

        return ResultObj.backInfo(true, 200, "上传成功", jsonObject);
    }
}
