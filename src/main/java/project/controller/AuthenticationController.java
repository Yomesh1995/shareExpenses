package project.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import project.common.CustomException;
import project.model.Authentication.LoginRequest;
import project.model.common.CommonResponse;
import project.service.AuthenticationServiceImplementation;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController{

    Logger logger = Logger.getLogger(AuthenticationController.class.getName());
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

}