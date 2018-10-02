package project.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import project.common.CustomException;
import project.model.CommonResponse;
import project.model.LoginRequest;
import project.service.AuthenticationServiceImplementation;

@RestController
@RequestMapping("/auth")
public class AuthenticationController{

    private Logger logger = Logger.getLogger(AuthenticationController.class.getName());
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