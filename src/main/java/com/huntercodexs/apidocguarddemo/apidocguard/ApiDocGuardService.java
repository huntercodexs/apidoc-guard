package com.huntercodexs.apidocguarddemo.apidocguard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Service
public class ApiDocGuardService {

    @Value("${apidocguard.data.crypt.type:}")
    String dataCryptTpe;

    @Value("${apidocguard.type:swagger}")
    String apiDocGuardType;

    @Autowired
    ApiDocGuardRepository apiDocGuardRepository;

    public ModelAndView login(
            HttpServletRequest req,
            HttpServletResponse res,
            Map<String, String> body
    ) {

        String username = body.get("username");
        String password = body.get("password");

        if (username.equals("") || password.equals("")) {
            System.out.println("MISSING DATA TO LOGIN");
            return new ModelAndView("login");
        }

        String passwordCrypt = dataCrypt(password);
        ApiDocGuardEntity apiDocGuardEntity = apiDocGuardRepository.findByLogin(username, passwordCrypt);

        if (apiDocGuardEntity != null) {

            System.out.println("LOGIN SUCCESS: " + body.get("username"));
            System.out.println("GUARD TYPE: " + apiDocGuardType);
            res.setHeader("Api-Doc-Guard-User", body.get("username"));

            switch (apiDocGuardType) {
                case "swagger":
                    return new ModelAndView("apidocguard/swagger-ui/index");
                case "adobe":
                    return new ModelAndView("apidocguard/adobe-aem/index");
                case "authentiq":
                    return new ModelAndView("apidocguard/authentiq-api/index");
            }

        }

        System.out.println("LOGIN FAIL: " + body.get("username"));
        return new ModelAndView("login");
    }

    public String dataCrypt(String data) {
        if (dataCryptTpe.equals("md5")) {
            return DigestUtils.md5DigestAsHex(data.getBytes());
        } else if (dataCryptTpe.equals("bcrypt")) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            return bCryptPasswordEncoder.encode(data);
        }
        return null;
    }
}
