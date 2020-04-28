package com.whp.buyshop.buy.Controller;

import com.baidu.ueditor.ActionEnter;
import com.whp.buyshop.utils.util.ImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2018/7/14 17:07
 * @descrpition :
 */
@Controller
@RequestMapping("ueditor")
public class UeditorController {
    @Value("${fast.url}")
    private String url;
    @Autowired
    private ImgUtil imgUtil;


    @RequestMapping("/exec")
    @ResponseBody
    public String exec(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String savePath = (String) request.getSession().getAttribute("impath");
        String rootPath = request.getRealPath("/");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "X-Requested-With,X_Requested_With");
        return new ActionEnter(request, rootPath).exec();
    }


    @RequestMapping(value = "/imgUpload")
    @ResponseBody
    public Map<String, Object> imgUpload(HttpServletRequest req) {
        Map<String, Object> rs = new HashMap<>();
        MultipartHttpServletRequest mReq = null;
        MultipartFile file = null;
        String fileName = "";
        // 原始文件名 UEDITOR创建页面元素时的alt和title属性
        String originalFileName = "";
        try {
            mReq = (MultipartHttpServletRequest) req;
            // 从config.json中取得上传文件的ID
            file = mReq.getFile("upfile");

            if (!file.isEmpty()) {
                String urlimg = imgUtil.FileImg_Ueditor(file);

                rs.put("state", "SUCCESS");// UEDITOR的规则:不为SUCCESS则显示state的内容
                rs.put("url", url + urlimg); // 能访问到你现在图片的路径
                rs.put("title", urlimg);
                rs.put("original", urlimg);
            }

        } catch (Exception e) {
            e.printStackTrace();
            rs.put("state", "文件上传失败!"); // 在此处写上错误提示信息，这样当错误的时候就会显示此信息
            rs.put("url", "");
            rs.put("title", "");
            rs.put("original", "");
        }
        return rs;
    }

}
