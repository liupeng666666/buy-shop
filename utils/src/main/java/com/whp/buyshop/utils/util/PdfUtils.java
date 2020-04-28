package com.whp.buyshop.utils.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author : 张吉伟
 * @data : 2019/11/18 11:59
 * @descrpition :
 */
public class PdfUtils {

    public static String pdf() {
        //0.创建文件输出流
        File file = new File("pdfdemo.pdf");
        System.out.println(file.getAbsolutePath());//查看文件存放位置
        FileOutputStream fo = null;
        try {
            fo = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            System.out.print("创建文件失败");
            e.printStackTrace();
        }
        //1.创建doc对象
        try {
            Document doc = new Document();
            float width = (595 / 210 * 40);
            float height = (842 / 297 * 30);

            Rectangle pageSize = new Rectangle(width, height);
            pageSize.setBorder(0);
            // pageSize.rotate();
            doc.setPageSize(pageSize);
            doc.setMargins(0, 0, 0, 0);
            PdfWriter.getInstance(doc, fo);
            //3.打开文档
            doc.open();
            //4.接下来想添什么就添什么了

            Font font = new Font(BaseFont.createFont(BaseFont.SYMBOL, "utf-8", false), 8);
            Paragraph pa1 = new Paragraph("this is paragraph one.", font);


            doc.add(pa1);
            //5.关闭文档
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        pdf();
    }

}
