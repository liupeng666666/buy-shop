package com.whp.buyshop.utils.util;

import com.alibaba.fastjson.JSONObject;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.stream.FileImageInputStream;
import java.io.*;

/**
 * @author : 张吉伟
 * @data : 2018/7/14 18:17
 * @descrpition :
 */
@Component
public class MVUtil {
    @Autowired
    private FastDFSUtils fastDFSUtils;
//    @Value("${file.location}")
//    private String url;

    public String MVFile(MultipartFile file) {

        try {
            byte[] bytes = new byte[0];
            try {
                bytes = file.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            //获取源文件名称
            String originalFileName = file.getOriginalFilename();
            //获取文件后缀
            String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

            //获取文件大小
            long fileSize = bytes.length;
            return fastDFSUtils.uploadFileStream(inputStream, fileSize, extension);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    public JSONObject MVMedia(String url, MultipartFile file) {
        JSONObject json = new JSONObject();
        String fu1 = MVFile(file);
        json.put("thumbnail", url + fu1);
        return json;
    }


}
