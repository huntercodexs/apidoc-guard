package com.huntercodexs.apidocguarddemo.apidocguard;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*")
public class ApiDocGuardController {

	@Autowired
    ApiDocGuardService apiDocGuardService;

	@Operation(hidden = true)
	@GetMapping(path = {"{$springdoc.api-docs.path:/api/docs}", "/api-docs", "/api-doc-guard", "/api-docs-guard"})
	public String sentinelApiDocs() {
		return "redirect:/swagger-ui/logout";
	}

	@Operation(hidden = true)
	@GetMapping(path = {
			"/",
			"/error",
			"/swagger-ui",
			"/swagger-ui/",
			"/swagger-ui/index",
			"/swagger-ui/index.html",
			"/swagger-ui/login",
			"/swagger-ui/sign",
			"/swagger-ui/viewer",
			"/swagger-ui/logout"
	})
	public String sentinelRoutes(HttpServletRequest request, HttpServletResponse response) {
		if (request.getServletPath().equals("/swagger-ui/logout")) {
			response.setHeader("Api-Doc-Guard-User", null);
			return "redirect:/swagger-ui/sentinel";
		}
		return "redirect:/swagger-ui/logout";
	}

//	@Operation(hidden = true)
//	@GetMapping(path = "/swagger-ui")
//	public String sentinelR1() {
//		return "redirect:/swagger-ui/logout";
//	}
//
//	@Operation(hidden = true)
//	@GetMapping(path = "/swagger-ui/")
//	public String sentinelR2() {
//		return "redirect:/swagger-ui/logout";
//	}
//
//	@Operation(hidden = true)
//	@GetMapping(path = "/swagger-ui/index.html")
//	public String sentinelIndexHtml() {
//		return "redirect:/swagger-ui/logout";
//	}
//
//	@Operation(hidden = true)
//	@GetMapping(path = "/swagger-ui/index")
//	public String sentinelIndex() {
//		return "redirect:/swagger-ui/logout";
//	}
//
//	@Operation(hidden = true)
//	@GetMapping(path = "/swagger-ui/login")
//	public String sentinelLogin() {
//		return "redirect:/swagger-ui/logout";
//	}
//
//	@Operation(hidden = true)
//	@GetMapping(path = "/swagger-ui/sign")
//	public String sentinelSign() {
//		return "redirect:/swagger-ui/logout";
//	}
//
//	@Operation(hidden = true)
//	@GetMapping(path = "/error")
//	public String error() {
//		return "redirect:/swagger-ui/logout";
//	}
//
//	@Operation(hidden = true)
//	@GetMapping(path = "/swagger-ui/viewer")
//	public String viewerReject() {
//		return "redirect:/swagger-ui/logout";
//	}
//
//	@Operation(hidden = true)
//	@GetMapping(path = "/swagger-ui/logout")
//	public String logout(HttpServletResponse response) {
//		response.setHeader("Api-Doc-Guard-User", null);
//		return "redirect:/swagger-ui/sentinel";
//	}

	@Operation(hidden = true)
	@GetMapping(path = "/swagger-ui/sentinel")
	public ModelAndView sentinel() {
		return new ModelAndView("login");
	}

	@Operation(hidden = true)
	@PostMapping(path = "/swagger-ui/viewer", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public ModelAndView viewer(
			HttpServletRequest req,
			HttpServletResponse res,
			@Valid @RequestParam Map<String, String> body
	) {
		return apiDocGuardService.login(req, res, body);
	}

}
