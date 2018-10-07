package project.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import project.common.Utility;

@Repository
public class AccountDaoImplementation {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static Logger logger = LogManager.getLogger(AccountDaoImplementation.class);

	public int generateTransaction(double transactionAmount, int fromUserId, int groupId) throws Exception {
		String query = "insert into `transaction_master`(txn_date,group_id,from_user_id,txn_amount) values(?,?,?,?)";
		final KeyHolder txnId = new GeneratedKeyHolder();
		try {
			jdbcTemplate.update(query, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setTimestamp(1, Utility.getCurrentDateTimeForQuery());
					ps.setInt(2, groupId);
					ps.setInt(3, fromUserId);
					ps.setDouble(4, transactionAmount);
					logger.debug(ps.toString());
				}
			}, txnId);
		} catch (Exception e) {
			throw e;
		}
		return txnId.getKey().intValue();
	}

	public void transactionDetail(double transactionAmount, int toUserId, int txnId) throws Exception {
		String query = "insert into `transaction_user_detail`(txn_id,to_user_id,amount) values(?,?,?)";
		try {
			jdbcTemplate.update(query, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, txnId);
					ps.setInt(2, toUserId);
					ps.setDouble(3, transactionAmount);
					logger.debug(ps.toString());
				}
			});
		} catch (Exception e) {
			throw e;
		}
	}

	public void groupTransactionDetail(String status, List<Integer> toUsersId, int txnId) throws Exception {
		String query = "insert into `group_txn_detail`(txn_id,to_user_id,status) values(?,?,?)";
		try {
			jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setInt(1, txnId);
					ps.setInt(2, toUsersId.get(i));
					ps.setString(3, status);
					logger.info(ps.toString());
				}

				@Override
				public int getBatchSize() {
					return toUsersId.size();
				}
			});
		} catch (Exception e) {
			throw e;
		}
	}

	public void updateGroupWiseUserBalance(int groupId, List<Integer> toUsersId, double creditAmountPerUser,
			double debitAmountPerUser) throws Exception {
		String query = "update `group_users_detail`set credit_amount=credit_amount+?,debit_amount=debit_amount+? where user_id=? and group_id=?;";
		try {
			jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setDouble(1, creditAmountPerUser);
					ps.setDouble(2, debitAmountPerUser);
					ps.setInt(3, toUsersId.get(i));
					ps.setInt(4, groupId);
					logger.info(ps.toString());
				}

				@Override
				public int getBatchSize() {
					return toUsersId.size();
				}
			});
		} catch (Exception e) {
			throw e;
		}
	}
}
