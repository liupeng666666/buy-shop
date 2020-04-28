package com.whp.buyshop.utils.util;

import com.alibaba.fastjson.JSONObject;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.UUID;

/**
 * @author : 张吉伟
 * @data : 2018/7/14 18:17
 * @descrpition :
 */
@Component
public class ImgUtil {
    @Autowired
    private FastDFSUtils fastDFSUtils;
    @Value("${fdfs.thumb-image.width}")
    private int width;
    @Value("${fdfs.thumb-image.height}")
    private int height;
    @Value("${file.location}")
    private String url;

    public String FileImg(MultipartFile file) {

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
            //获取文件后缀--.doc
            String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
            String u = url + "/demo." + extension;
            Thumbnails.of(inputStream).size(width, height).keepAspectRatio(false).outputQuality(0.8).toFile(u);
            bytes = image2byte(u);
            //获取文件大小
            long fileSize = bytes.length;
            return fastDFSUtils.uploadFile(bytes, fileSize, extension);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String ClassImg(MultipartFile file) {
        width = 100;
        height = 100;
        String fu1 = FileImg(file);
        return fu1;
    }

    public JSONObject ImgText(String url, MultipartFile file) {
        JSONObject json = new JSONObject();
        width = 360;
        height = 200;
        String fu1 = FileImg(file);
        json.put("thumbnail", url + fu1);
        width = 200;
        height = 200;
        String fu2 = FileImg(file);
        json.put("secondary", url + fu2);

        return json;
    }

    public JSONObject ImgMediaPic(String url, MultipartFile file) {
        JSONObject json = new JSONObject();
        width = 360;
        height = 200;
        String fu1 = FileImg(file);
        json.put("thumbnail", url + fu1);
        return json;
    }

    public JSONObject ImgMediaSuo(String url, MultipartFile file) {
        JSONObject json = new JSONObject();
        width = 200;
        height = 200;
        String fu2 = FileImg(file);
        json.put("secondary", url + fu2);
        return json;
    }

    public JSONObject GoodsImg(String url, MultipartFile file) {
        JSONObject json = new JSONObject();
        width = 100;
        height = 100;
        String fu1 = FileImg(file);
        json.put("thumbnail", url + fu1);
        width = 360;
        height = 360;
        String fu2 = FileImg(file);
        json.put("secondary", url + fu2);
        width = 720;
        height = 720;
        String fu3 = FileImg(file);
        json.put("bigpicture", url + fu3);
        return json;
    }

    public JSONObject GoodsImgPic(String url, MultipartFile filepic) {
        JSONObject json = new JSONObject();
        width = 100;
        height = 50;
        String fu1 = FileImg(filepic);
        json.put("thumbnail", url + fu1);
        width = 200;
        height = 100;
        String fu2 = FileImg(filepic);
        json.put("secondary", url + fu2);
        width = 400;
        height = 200;
        String fu3 = FileImg(filepic);
        json.put("bigpicture", url + fu3);
        return json;
    }

    public String AdImg(MultipartFile file) {
        width = 688;
        height = 300;
        String fu1 = FileImg(file);
        return fu1;
    }

    /**
     * 图片转化为byte数组
     *
     * @param path
     * @return
     */
    public byte[] image2byte(String path) {
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        } catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        } catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }

    public String FileImg_Ueditor(MultipartFile file) {

        try {

            byte[] bytes = new byte[0];
            try {
                bytes = file.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
            int y_width = 720;
            int y_height = 360;
            try {
                BufferedImage image = ImageIO.read(file.getInputStream());
                if (image != null) {//如果image=null 表示上传的不是图片格式
                    if (image.getWidth() > 720) {
                        y_height = image.getHeight();
                    } else {
                        y_width = image.getWidth();
                        y_height = image.getHeight();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            //获取源文件名称
            String originalFileName = file.getOriginalFilename();
            //获取文件后缀--.doc
            String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
            String u = url + "/demo." + extension;
            Thumbnails.of(inputStream).size(y_width, y_height).outputQuality(0.8).toFile(u);
            bytes = image2byte(u);
            //获取文件大小
            long fileSize = bytes.length;
            return fastDFSUtils.uploadFile(bytes, fileSize, extension);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
