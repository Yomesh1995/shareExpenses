package project.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.common.CustomException;
import project.common.ResponseMessages;
import project.dao.AuthenticationDaoImplementation;
import project.model.LoginRequest;


@Service
public class AuthenticationServiceImplementation {

	private static Logger logger= LogManager.getLogger(AuthenticationServiceImplementation.class);
    @Autowired
    private AuthenticationDaoImplementation authDao;

    public void login(LoginRequest request) throws CustomException{
        try{
            if(!authDao.validateLogin(request))
                throw new CustomException(ResponseMessages.INVALID_CREDENTIAL_CODE, ResponseMessages.INVALID_CREDENTIAL_MESSAGE);
        } catch (CustomException ex){
            throw ex;
        } catch (Exception ex){
            ex.printStackTrace();
            throw new CustomException(ResponseMessages.INTERNAL_SERVER_ERROR_CODE,ResponseMessages.INTERNAL_SERVER_ERROR_MESSAGE);
        }

    }
}
