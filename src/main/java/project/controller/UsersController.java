package project.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.common.CustomException;
import project.model.CommonResponse;
import project.model.GroupRegistration;
import project.model.SimiliarUsernameResponse;
import project.model.UserInfo;
import project.service.UserServiceImplementation;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UserServiceImplementation userServiceImplementation;

	@PostMapping("/registration")
	public CommonResponse userRegistration(@RequestBody(required = true) @Valid UserInfo user) throws CustomException {
		CommonResponse response = new CommonResponse();
		userServiceImplementation.userRegistration(user);
		response.setSuccessResponse();
		return response;
	}

	@GetMapping("/getUsernames")
	public SimiliarUsernameResponse getSimiliarUsername(
			@RequestParam(value = "userName", required = true) String username) throws CustomException {
		SimiliarUsernameResponse response = new SimiliarUsernameResponse();
		response.setSimiliarUsername(userServiceImplementation.getSimiliarUsername(username));
		response.setSuccessResponse();
		return response;
	}
	
	@PostMapping("/groupRegistration")
	public CommonResponse groupRegistration(@RequestBody(required = true) @Valid GroupRegistration group) throws CustomException {
		CommonResponse response = new CommonResponse();
		
		response.setSuccessResponse();
		return response;
	}

}
