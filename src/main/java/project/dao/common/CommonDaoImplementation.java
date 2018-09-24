package project.dao.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import project.model.user.UserInfo;

import java.util.StringJoiner;

@Repository
public class CommonDaoImplementation {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Boolean isUserExist(String username) throws Exception{
        StringBuilder query=new StringBuilder("select user_id from user_master where user_name='").append(username).append("'");
        Integer userId = null;
        try{
            userId=jdbcTemplate.queryForObject(query.toString(),Integer.class);
        } catch (EmptyResultDataAccessException ex){

        } catch (Exception ex){
            throw ex;
        }
        return userId==null?false:true;
    }

}
