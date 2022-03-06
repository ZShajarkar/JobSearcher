package com.example.demo.controller;

import com.example.demo.dto.ListMainUserInfoDto;
import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.SignUpUserRequestDto;
import com.example.demo.dto.SignUpUserResponseDto;
import com.example.demo.services.UserService;
import com.example.demo.util.ResponseFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(value = "/public/user/v1")
@SecurityScheme(name = "Authorization", scheme = "Bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/process_register", consumes = "application/json", produces = "application/json")
    @Operation(summary = "it saves the user",
            tags = "Register",
            responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = SignUpUserResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<?> save(@Valid @RequestBody SignUpUserRequestDto userDto) {
        try {
            return ResponseFactory.ok(userService.save(userDto));
        } catch (Exception e) {
            return ResponseFactory.badRequest(e.getMessage());
        }
    }

    @PostMapping(path = "/process_register/admin", consumes = "application/json", produces = "application/json")
    @Operation(summary = "it saves the admin",
            tags = "Register",
            responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = SignUpUserResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<?> saveAdmin(@Valid @RequestBody SignUpUserRequestDto userDto) {
        try {
            return ResponseFactory.ok(userService.saveAdmin(userDto));
        } catch (Exception e) {
            return ResponseFactory.badRequest(e.getMessage());
        }
    }

    @PostMapping(path = "/sign_in", consumes = "application/json", produces = "application/json")
    @Operation(summary = "user and admin can log in",
            tags = "Log in",
            responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = SignUpUserResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        try {
            return ResponseFactory.ok(userService.authenticateUser(loginRequestDto));
        } catch (Exception e) {
            return ResponseFactory.badRequest(e.getMessage());
        }
    }

    @GetMapping(path = "/jobId/{job-id}", produces = "application/json")
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "Company can see who has been sent resume for a job",
            tags = "Company Operation",
            security = @SecurityRequirement(name = "Authorization"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = ListMainUserInfoDto.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<?> getUserSentResume(@RequestHeader("Authorization") String token, @PathVariable("job-id") Long jobId) {
        try {
            return ResponseFactory.ok(userService.getUsersSentResume(jobId, token));
        } catch (Exception e) {
            return ResponseFactory.badRequest(e.getMessage());
        }
    }
}
