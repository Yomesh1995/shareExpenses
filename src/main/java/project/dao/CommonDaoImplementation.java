package project.dao;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommonDaoImplementation {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static Logger logger = LogManager.getLogger(CommonDaoImplementation.class);

	public Boolean isUserExist(String username) throws Exception {
		StringBuilder query = new StringBuilder("select user_id from user_master where user_name='").append(username)
				.append("'");
		Integer userId = null;
		try {
			logger.info(query.toString());
			userId = jdbcTemplate.queryForObject(query.toString(), Integer.class);
		} catch (EmptyResultDataAccessException ex) {

		} catch (Exception ex) {
			throw ex;
		}
		return userId == null ? false : true;
	}

	public List<Integer> validateUsersId(Set<Integer> usersId) throws Exception {
		String query = "select user_id from user_master where user_id in (:usersId)";
		List<Integer> usersList = null;
		try {
			MapSqlParameterSource parameters = new MapSqlParameterSource("usersId", usersId);
			logger.info(query.toString());
			logger.info(usersId.toString());
			usersList = namedParameterJdbcTemplate.queryForList(query, parameters, Integer.class);
		} catch (EmptyResultDataAccessException ex) {
			return null;
		} catch (Exception ex) {
			throw ex;
		}
		return usersList;
	}

	public int getUserIdFromName(String username) throws Exception {
		StringBuilder query = new StringBuilder("select user_id from user_master where user_name='").append(username)
				.append("'");
		Integer userId = null;
		try {
			logger.info(query.toString());
			userId = jdbcTemplate.queryForObject(query.toString(), Integer.class);
		} catch (EmptyResultDataAccessException ex) {
			return 0;
		} catch (Exception ex) {
			throw ex;
		}
		return userId;
	}

	public String getUserNameFromId(int userId) throws Exception {
		String query = "select user_name from user_master where user_id=" + userId;
		String userName = null;
		try {
			logger.info(query.toString());
			userName = jdbcTemplate.queryForObject(query, String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw e;
		}
		return userName;
	}

}
