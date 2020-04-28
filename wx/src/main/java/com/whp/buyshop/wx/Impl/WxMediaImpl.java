package com.whp.buyshop.wx.Impl;


import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.wx.Dao.WxMediaDao;
import com.whp.buyshop.wx.Interface.WxMediaInterface;
import com.whp.buyshop.wx.Utils.WxConfigUtils;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.material.WxMpMaterial;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialUploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Service
public class WxMediaImpl implements WxMediaInterface {
    @Autowired
    private WxMediaDao wxMediaDao;
    @Autowired
    private WxConfigUtils wxConfigUtils;


    @Override
    public JSONObject WxMediaInsert(Map<String, Object> map, MultipartFile file) {
        JSONObject json = new JSONObject();
        try {

            //微信url,media
            WxMpService wxMpService = wxConfigUtils.WxConfig(map.get("buyid").toString());
            String originalFileName = file.getOriginalFilename();
            //获取文件后缀--.doc
            String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
            InputStream inputStream = file.getInputStream();


            String mediaType = mediaType(Integer.parseInt(map.get("style").toString()));
            if (Integer.parseInt(map.get("type").toString()) == 0) {
                WxMediaUploadResult wxMediaImgUploadResult = wxMpService.getMaterialService().mediaUpload(mediaType, extension, inputStream);
                map.put("mediaid", wxMediaImgUploadResult.getMediaId());
            } else {
                WxMpMaterial mpMaterial = new WxMpMaterial();
                String url = JSONObject.parseObject(map.get("url").toString()).getString("thumbnail");
                mpMaterial.setFile(getFileByUrl(url, extension));
                mpMaterial.setName(originalFileName);
                mpMaterial.setVideoTitle(map.get("memo").toString());
                mpMaterial.setVideoIntroduction(map.get("memo").toString());
                WxMpMaterialUploadResult wxMediaImgUploadResult = wxMpService.getMaterialService().materialFileUpload(mediaType, mpMaterial);
                map.put("wx_url", wxMediaImgUploadResult.getUrl());
                map.put("mediaid", wxMediaImgUploadResult.getMediaId());
            }
            wxMediaDao.WxMediaInsert(map);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject WxMediaSelect(Map<String, Object> map, int page, int num) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = wxMediaDao.WxMediaSelectByPage(map, page, num);
            int count = wxMediaDao.WxMediaSelectByCount(map);
            json.put("code", 100);
            json.put("list", list);
            json.put("count", count);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject WxMediaUpdateState(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {

            if (map.containsKey("isdel")) {
                wxMediaDao.WxMediaUpdateState(map);
                json.put("code", 100);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject WxMediaSelectById(String buyid) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = wxMediaDao.WxMediaSelectById(buyid);
            json.put("code", 100);
            json.put("media", list);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject WxMediaSelectByStyle(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = wxMediaDao.WxMediaSelectByStyle(map);
            json.put("code", 100);
            json.put("media", list);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    public String mediaType(int type) {
        switch (type) {
            case 0:
                return "image";
            case 1:
                return "voice";
            case 2:
                return "video";
            case 3:
                return "thumb";

        }
        return null;
    }


    private File getFileByUrl(String fileUrl, String suffix) {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        BufferedOutputStream stream = null;

        InputStream inputStream = null;

        File file = null;

        try {

            URL imageUrl = new URL(fileUrl);

            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();

            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            inputStream = conn.getInputStream();

            byte[] buffer = new byte[1024];

            int len = 0;

            while ((len = inputStream.read(buffer)) != -1) {

                outStream.write(buffer, 0, len);

            }

            file = File.createTempFile("pattern", "." + suffix);

            FileOutputStream fileOutputStream = new FileOutputStream(file);

            stream = new BufferedOutputStream(fileOutputStream);

            stream.write(outStream.toByteArray());

        } catch (Exception e) {


        } finally {

            try {

                if (inputStream != null) inputStream.close();

                if (stream != null) stream.close();

                outStream.close();

            } catch (Exception e) {

            }

        }

        return file;

    }
}
