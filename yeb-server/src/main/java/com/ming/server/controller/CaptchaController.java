package com.ming.server.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author: ming
 * @create: 2021-07-19 13:36
 * @program: yeb
 */
@RestController
public class CaptchaController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @ApiOperation(value = "验证码")
    @GetMapping(value = "/captcha", produces = "image/jpeg")
    public void captchaCode(HttpServletRequest request, HttpServletResponse response, String time) {
        System.out.println(time);

        response.setHeader("Cache-Control", "no-store,np-cache,must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        //生成验证码
        //获取验证码文本
        String code = defaultKaptcha.createText();
        System.out.println(code);
        //获取session
        HttpSession session = request.getSession();
        //将验证码存入到session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, code);

        //生成验证码图片
        BufferedImage image = defaultKaptcha.createImage(code);

        ServletOutputStream outputStream = null;
        try {
            //输出
            outputStream = response.getOutputStream();
            ImageIO.write(image, "jpeg", outputStream);
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
