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
import project.model.SimiliarGroupNamesResponse;
import project.model.SimiliarUserNamesResponse;
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
	public SimiliarUserNamesResponse getSimiliarUsername(
			@RequestParam(value = "userName", required = true) String username) throws CustomException {
		SimiliarUserNamesResponse response = new SimiliarUserNamesResponse();
		response.setSimiliarNames(userServiceImplementation.getSimiliarUsername(username));
		response.setSuccessResponse();
		return response;
	}
	
	@PostMapping("/groupRegistration")
	public CommonResponse groupRegistration(@RequestBody(required = true) @Valid GroupRegistration group) throws CustomException {
		CommonResponse response = new CommonResponse();
		userServiceImplementation.groupRegistration(group);
		response.setSuccessResponse();
		return response;
	}
	
	@GetMapping("/getGroupnames")
	public SimiliarGroupNamesResponse getSimiliarGroup(
			@RequestParam(value = "groupName", required = true) String groupname) throws CustomException {
		SimiliarGroupNamesResponse response = new SimiliarGroupNamesResponse();
		response.setSimiliarNames(userServiceImplementation.getSimiliarGroupname(groupname));
		response.setSuccessResponse();
		return response;
	}

}
