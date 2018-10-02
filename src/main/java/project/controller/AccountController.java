package project.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.common.CustomException;
import project.model.CommonResponse;
import project.model.TransactionRequest;
import project.service.AccountServiceImplementation;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountServiceImplementation accountServiceImplementation;

	@PostMapping("/one")
	public CommonResponse oneToOneTransaction(@RequestBody(required = true) @Valid TransactionRequest request)
			throws CustomException {
		CommonResponse response = new CommonResponse();
		accountServiceImplementation.oneToOneTransaction(request);
		response.setSuccessResponse();
		return response;
	}
}
