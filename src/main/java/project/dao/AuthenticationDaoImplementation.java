package project.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import project.model.LoginRequest;

@Repository
public class AuthenticationDaoImplementation {

	private static Logger logger = LogManager.getLogger(AuthenticationDaoImplementation.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean validateLogin(LoginRequest request) throws Exception{
        Integer user = null;
        try {
            StringBuilder query= new StringBuilder("select user_id from user_master where");
            query.append(" user_name='").append(request.getUserName()).append("'");
            query.append(" and password='").append(request.getPassword()).append("'");
            logger.info(query.toString());
            user = jdbcTemplate.queryForObject(query.toString(),Integer.class);
        } catch (Exception ex){
            throw ex;
        }
        return user == null ? false : true;
    }
}
