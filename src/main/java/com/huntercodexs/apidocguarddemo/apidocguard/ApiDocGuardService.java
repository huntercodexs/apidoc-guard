package com.huntercodexs.apidocguarddemo.apidocguard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Service
public class ApiDocGuardService {

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

        ApiDocGuardEntity apiDocGuardEntity = apiDocGuardRepository.findByLogin(username, password);

        //TODO: INSERIR CRIPTOGRAFIA DOS DADOS
        if (apiDocGuardEntity != null) {
            System.out.println("LOGIN SUCCESS: " + body.get("username"));
            res.setHeader("Api-Doc-Guard-User", body.get("username"));
            return new ModelAndView("apidocguard/swagger-ui/index");
        } else {
            System.out.println("LOGIN FAIL: " + body.get("username"));
            return new ModelAndView("login");
        }
    }
}
