package project.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.common.CustomException;
import project.common.ResponseMessages;
import project.common.TransactionStatus;
import project.dao.AccountDaoImplementation;
import project.dao.CommonDaoImplementation;
import project.model.GroupTransactionRequest;
import project.model.TransactionRequest;

@Service
public class AccountServiceImplementation {

	@Autowired
	private CommonDaoImplementation commonDao;
	@Autowired
	private AccountDaoImplementation accontDao;

	@Transactional(rollbackFor = { CustomException.class })
	public void ownTransaction(TransactionRequest request) throws CustomException {
		try {
			int txnId = accontDao.generateTransaction(request.getAmount(), request.getFromUser(), 0);
			if (txnId == 0)
				throw new CustomException(ResponseMessages.TRANSACTION_NOT_GENERATED_CODE,
						ResponseMessages.TRANSACTION_NOT_GENERATED_MESSAGE);
		} catch (CustomException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException(ResponseMessages.INTERNAL_SERVER_ERROR_CODE,
					ResponseMessages.INTERNAL_SERVER_ERROR_MESSAGE);
		}
	}
	
	@Transactional(rollbackFor = { CustomException.class })
	public void oneToOneTransaction(TransactionRequest request) throws CustomException {
		try {
			commonDao.getUserNameFromId(request.getToUser());
			int txnId = accontDao.generateTransaction(request.getAmount(), request.getFromUser(), 0);
			if (txnId == 0)
				throw new CustomException(ResponseMessages.TRANSACTION_NOT_GENERATED_CODE,
						ResponseMessages.TRANSACTION_NOT_GENERATED_MESSAGE);
			accontDao.transactionDetail(request.getAmount(), request.getToUser(), txnId);
		} catch (CustomException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException(ResponseMessages.INTERNAL_SERVER_ERROR_CODE,
					ResponseMessages.INTERNAL_SERVER_ERROR_MESSAGE);
		}
	}

	@Transactional(rollbackFor = { CustomException.class })
	public void groupTransaction(GroupTransactionRequest request) throws CustomException {
		try {
			Map<Integer, List<Integer>> groupUsers = commonDao.validateGroupUsers(request.getToUsers(),
					request.getGroupId());
			if (groupUsers.isEmpty() || groupUsers.get(request.getGroupId()).size() != request.getToUsers().size())
				throw new CustomException(ResponseMessages.INVALID_USERS_CODE, ResponseMessages.INVALID_USERS_MESSAGE);
			int txnId = accontDao.generateTransaction(request.getAmount(), request.getFromUser(), 0);
			if (txnId == 0)
				throw new CustomException(ResponseMessages.TRANSACTION_NOT_GENERATED_CODE,
						ResponseMessages.TRANSACTION_NOT_GENERATED_MESSAGE);
			accontDao.groupTransactionDetail(TransactionStatus.PENDING.getValue(), request.getToUsers(), txnId);
			accontDao.updateGroupWiseUserBalance(request.getGroupId(), request.getToUsers(), 0,
					request.getAmount() / request.getToUsers().size());
		} catch (CustomException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException(ResponseMessages.INTERNAL_SERVER_ERROR_CODE,
					ResponseMessages.INTERNAL_SERVER_ERROR_MESSAGE);
		}
	}
}
