package project.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import project.model.user.UserInfo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public class UserDaoImplementation {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void userRegistration(UserInfo user) throws Exception{
        String query="insert into user_master(user_name,password,first_name,mobile_nbr,email_id,reg_date) values(?,?,?,?,?,?);";
        try{
            final int update = jdbcTemplate.update(query, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setString(1, user.getUserName());
                    ps.setString(2, user.getPassword());
                    ps.setString(3, user.getName());
                    ps.setString(4, user.getMobileNumber());
                    ps.setString(5, user.getEmailId());
                    ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
                }
            });
        }catch (Exception ex){
            throw ex;
        }
    }
}
