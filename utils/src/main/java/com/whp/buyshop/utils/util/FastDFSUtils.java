package com.whp.buyshop.utils.util;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author : 张吉伟
 * @data : 2019/9/10 15:19
 * @descrpition :
 */
@Component
public class FastDFSUtils {
    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    /**
     * 文件上传
     * 最后返回fastDFS中的文件名称;group1/M00/01/04/CgMKrVvS0geAQ0pzAACAAJxmBeM793.doc
     *
     * @param bytes     文件字节
     * @param fileSize  文件大小
     * @param extension 文件扩展名
     * @return fastDfs路径
     */
    public String uploadFile(byte[] bytes, long fileSize, String extension) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        StorePath storePath = fastFileStorageClient.uploadFile(byteArrayInputStream, fileSize, extension, null);
        return storePath.getFullPath();
    }

    public String uploadFileStream(InputStream is, long size, String fileName) {
        StorePath path = fastFileStorageClient.uploadFile(is, size, fileName, null);
        return path.getFullPath();
    }
}
