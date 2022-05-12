package com.huntercodexs.apidocguarddemo.restcontroller;

import com.huntercodexs.apidocguarddemo.service.SampleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("${api.prefix}")
@Tag(name = "Sample Documentation")
public class SampleController {

	@Autowired
    SampleService sampleService;

	@Operation(hidden = false)
	@GetMapping(path = "/dashboard-admin")
	public ResponseEntity<?> dashAdmin() {
		return sampleService.admin();
	}

	@Operation(
			hidden = false,
			summary = "Sample",
			description = "Sample method to get users"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = {
					@Content(mediaType = "application/json")
			}),
			@ApiResponse(responseCode = "201", description = "CREATED", content = {
					@Content(mediaType = "application/json")
			}),
			@ApiResponse(responseCode = "202", description = "ACCEPTED", content = {
					@Content(mediaType = "application/json")
			}),
			@ApiResponse(responseCode = "400", description = "BAD REQUEST", content = {
					@Content(mediaType = "")
			}),
			@ApiResponse(responseCode = "401", description = "UNAUTHORIZED", content = {
					@Content(mediaType = "text")
			}),
			@ApiResponse(responseCode = "404", description = "NOT FOUND", content = {
					@Content(mediaType = "application/json")
			}),
			@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR", content = {
					@Content(mediaType = "application/json")
			})
	})
	@PostMapping(path = "/dashboard-users")
	@ResponseBody
	public ResponseEntity<?> dashUsers() {
		return sampleService.users();
	}

}
