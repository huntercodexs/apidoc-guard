package com.huntercodexs.apidocguarddemo.apidocguard;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@CrossOrigin(origins = "*")
public class ApiDocGuard {

	@Autowired
	ApiDocGuardService apiDocGuardService;

	@Operation(hidden = true)
	@GetMapping(path = {
			"{$springdoc.api-docs.path:/api/docs}",
			"/api-docs",
			"/api-doc-guard",
			"/api-docs-guard"})
	public String sentinelApiDocsRoute() {
		return "redirect:/doc-protect/logout";
	}

	@Operation(hidden = true)
	@GetMapping(path = {
			"/",
			"/error",
			"/doc-protect",
			"/doc-protect/",
			"/doc-protect/login",
			"/doc-protect/sign",
			"/doc-protect/viewer",
			"/doc-protect/logout",
			"/doc-protect/protector",
			"/doc-protect/doc-protected",
			"/doc-protect/index",
			"/doc-protect/index.html",
			"/doc-protect/swagger",
			"/doc-protect/swagger-ui",
			"/doc-protect/adobe",
			"/doc-protect/adobe-aem",
			"/doc-protect/authentiq",
			"/doc-protect/authentiq-api"
	})
	public String sentinelDocProtectRoute(HttpServletRequest request, HttpServletResponse response) {
		if (request.getServletPath().equals("/doc-protect/logout")) {
			response.setHeader("Api-Doc-Guard-User", null);
			return "redirect:/doc-protect/sentinel";
		}
		return "redirect:/doc-protect/logout";
	}

	@Operation(hidden = true)
	@GetMapping(path = "/doc-protect/sentinel")
	public ModelAndView sentinel() {
		return apiDocGuardService.protector(null, null, null);
	}

}
