package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.model.common.CommonResponse;
import project.common.CustomException;
import project.model.user.UserInfo;
import project.service.user.UserServiceImplementation;

import javax.validation.Valid;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
public class UsersController {

    private Logger logger=Logger.getLogger(UsersController.class.getName());
    @Autowired
    private UserServiceImplementation userServiceImplementation;

    @PostMapping("/registration")
    public CommonResponse userRegistration(@RequestBody(required = true)@Valid UserInfo user) throws CustomException {
        CommonResponse response=new CommonResponse();
        logger.info("UserInfo {} "+ user.toString());
        userServiceImplementation.userRegistration(user);
        response.setSuccessResponse();
        return response;
    }

    @GetMapping("/username")
    public CommonResponse getSimiliarUsername(@RequestParam(required = true) String username) throws CustomException{
        CommonResponse response=new CommonResponse();
        response.setSuccessResponse();
        return response;
    }

}
