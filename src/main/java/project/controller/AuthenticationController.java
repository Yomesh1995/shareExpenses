package project.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.common.CustomException;
import project.model.CommonResponse;
import project.model.LoginRequest;
import project.service.AuthenticationServiceImplementation;

@RestController
@RequestMapping("/auth")
public class AuthenticationController{

    @Autowired
    private AuthenticationServiceImplementation authService;

    @PostMapping("/login")
    public CommonResponse login(@RequestBody(required = true) @Valid LoginRequest request)throws CustomException
    {
        CommonResponse response = new CommonResponse();
        authService.login(request);
        response.setSuccessResponse();
        return response;
    }

    @GetMapping("/")
    public CommonResponse loginTest()
    {
        CommonResponse response = new CommonResponse();
        response.setSuccessResponse();
        return response;
    }
}