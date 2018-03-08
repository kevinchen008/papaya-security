package com.papaya.core.validate.code;

import com.papaya.core.properties.PapayaSecurityProperties;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Random;

public class ImageCodeDefaultGenerator implements ValidateCodeGenerator<ImageCode> {

    private PapayaSecurityProperties securityProperties;

    @Override
    public ImageCode generatorCode(HttpServletRequest request) {
        int width = securityProperties.getValidateCode().getImageCode().getWidth();
        int height = securityProperties.getValidateCode().getImageCode().getHeight();
        int lines = 10;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = img.getGraphics();

        // 设置背景色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // 设置字体
        g.setFont(new Font("宋体", Font.BOLD, 20));

        // 随机数字
        String sRand = "";
        Random r = new Random(new Date().getTime());
        for (int i = 0; i < securityProperties.getValidateCode().getImageCode().getLength(); i++) {
            int a = r.nextInt(10);
            sRand +=a;
            int y = 10 + r.nextInt(20);// 10~30范围内的一个整数，作为y坐标

            Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
            g.setColor(c);

            g.drawString("" + a, 5 + i * width / 4, y);
        }

        // 干扰线
        for (int i = 0; i < lines; i++) {
            Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
            g.setColor(c);
            g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width), r.nextInt(height));
        }

        g.dispose();// 类似于流中的close()带动flush()---把数据刷到img对象当中

        return new ImageCode(img,sRand,securityProperties.getValidateCode().getImageCode().getExpried());
    }

    public PapayaSecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(PapayaSecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
