package project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.common.CommonResponse;

import java.util.logging.Logger;

@RestController
@RequestMapping("/auth")
public class AuthenticationController{

    Logger logger = Logger.getLogger(AuthenticationController.class.getName());

    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = "application/json")
    public CommonResponse getGameList(
            @RequestParam(value = "request", required = true) String stringRequest)
    {
        logger.info("Games List - request{} " + stringRequest);
        CommonResponse response = new CommonResponse();
        response.setResponseCode(1001);
        response.setResposneMessage("SUCCESS");
        logger.info("Games List - response{} " + response.toString());
        return response;
    }

}