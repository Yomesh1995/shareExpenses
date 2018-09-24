package project.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.common.CustomException;
import project.common.ResponseMessages;
import project.dao.common.CommonDaoImplementation;
import project.dao.user.UserDaoImplementation;
import project.model.user.UserInfo;

import java.util.logging.Logger;

@Service
public class UserServiceImplementation {

    private Logger logger= Logger.getLogger(UserServiceImplementation.class.getName());
    @Autowired
    private UserDaoImplementation userDao;
    @Autowired
    private CommonDaoImplementation commonDao;

    public void userRegistration(UserInfo userInfo) throws CustomException{
        try{
            if(commonDao.isUserExist(userInfo.getUserName()))
                throw new CustomException(ResponseMessages.USER_EXIST_CODE,ResponseMessages.USER_EXIST_MESSAGE);
            userDao.userRegistration(userInfo);
        } catch (CustomException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CustomException(ResponseMessages.INTERNAL_SERVER_ERROR_CODE,ResponseMessages.INTERNAL_SERVER_ERROR_MESSAGE);
        }
    }

}
