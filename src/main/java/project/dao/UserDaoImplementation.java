package project.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import project.model.GroupRegistration;
import project.model.UserInfo;

@Repository
public class UserDaoImplementation {

	private static Logger logger = LogManager.getLogger(UserDaoImplementation.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	public void userRegistration(UserInfo user) throws Exception {
		String query = "insert into user_master(user_name,password,first_name,mobile_nbr,email_id,reg_date) values(?,?,?,?,?,?);";
		try {
			jdbcTemplate.update(query, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, user.getUserName());
					ps.setString(2, user.getPassword());
					ps.setString(3, user.getName());
					ps.setString(4, user.getMobileNumber());
					ps.setString(5, user.getEmailId());
					ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
					logger.info(ps.toString());
				}
			});
		} catch (Exception ex) {
			throw ex;
		}
	}

	public List<String> similiarUsername(String username) {
		String query = "select username from user_master where user_name like '" + username + "%'";
		List<String> similiarUser = null;
		try {
			similiarUser = jdbcTemplate.queryForList(query, String.class);
		} catch (EmptyResultDataAccessException ex) {
			return null;
		} catch (Exception ex) {
			throw ex;
		}
		return similiarUser;
	}

	public int groupRegistration(GroupRegistration group) throws Exception {
		String query = "insert into `group_master`(group_name,create_date,user_count) values(?,?,?);";
		final KeyHolder groupId = new GeneratedKeyHolder();
		try {
			jdbcTemplate.update(query, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, group.getGroupName());
					ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
					ps.setInt(3, group.getGroupMembers().size());
					logger.info(ps.toString());
				}
			});
		} catch (Exception ex) {
			throw ex;
		}
		return groupId.getKey().intValue();
	}

	public void groupMembersRegistration(int groupId, List<Integer> usersId) throws Exception {
		String query = "insert into `group_users_detail`(group_id,user_id,credit_amt,debit_amt) values(?,?,?,?);";
		try {
			jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setInt(1, groupId);
					ps.setInt(2, usersId.get(i));
					ps.setInt(3, 0);
					ps.setInt(4, 0);
					logger.info(ps.toString());
				}

				@Override
				public int getBatchSize() {
					// TODO Auto-generated method stub
					return usersId.size();
				}
			});
		} catch (Exception ex) {
			throw ex;
		}
	}
}
