package com.baizhi.controller;

import com.baizhi.conf.CreateValidateCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/code")
public class ImgController {
    @RequestMapping("/vCode")
    public void vCode(HttpServletResponse response, HttpSession session) throws IOException {
        CreateValidateCode validateCode = new CreateValidateCode();
        String code = validateCode.getCode();
        System.out.printf("生成的验证码====" + code);
        session.setAttribute("vCode", code);
        //String de = (String) session.getAttribute("vCode");
        //System.out.println("qqqqqqqqqqqqq"+de);

        validateCode.write(response.getOutputStream());

    }
}
