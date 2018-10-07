package project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
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
		StringBuilder query = new StringBuilder("select user_name from user_master where user_id=").append(userId);
		String userName = null;
		try {
			logger.info(query.toString());
			userName = jdbcTemplate.queryForObject(query.toString(), String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw e;
		}
		return userName;
	}

	public Map<Integer, List<Integer>> validateGroupUsers(List<Integer> userIds, int groupId) throws Exception {
		String query = "select group_id,user_id from group_user_detail where group_id=:groupId and user_id in (:usersId);";
		Map<Integer, List<Integer>> groupUsers = null;
		try {
			MapSqlParameterSource parameters = new MapSqlParameterSource("groupId", groupId);
			parameters.addValue("usersId", userIds);
			logger.info(query);
			logger.info(parameters.getValues());
			groupUsers = namedParameterJdbcTemplate.query(query, parameters, new ResultSetExtractor<Map<Integer, List<Integer>>>() {

				@Override
				public Map<Integer, List<Integer>> extractData(ResultSet rs) throws SQLException, DataAccessException {
					Map<Integer, List<Integer>> groupUsers = new HashMap<>(1);
					while(rs.next()) {
						List<Integer> users= groupUsers.get(rs.getInt("group_id"));
						if(users == null)
							users = new ArrayList<>();
						users.add(rs.getInt("user_id"));
						groupUsers.put(rs.getInt("group_id"),users);
					}
					return groupUsers;
				}
			});
		} catch (Exception e) {
			throw e;
		}
		return groupUsers;
	}

}
