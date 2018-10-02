package project.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.common.CustomException;
import project.common.ResponseMessages;
import project.dao.AccountDaoImplementation;
import project.dao.CommonDaoImplementation;
import project.model.TransactionRequest;

@Service
public class AccountServiceImplementation {

	private static Logger logger = LogManager.getLogger(AccountServiceImplementation.class);
	@Autowired
	private CommonDaoImplementation commonDao;
	@Autowired
	private AccountDaoImplementation accontDao;

	@Transactional(rollbackFor = { CustomException.class })
	public void oneToOneTransaction(TransactionRequest request) throws CustomException {
		try {
			commonDao.getUserNameFromId(request.getToUser());
			int txnId = accontDao.generateTransaction(request.getAmount(), request.getFromUser(), 0);
			accontDao.transactionDetail(request.getAmount(), request.getToUser(), txnId);
		} catch (CustomException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException(ResponseMessages.INTERNAL_SERVER_ERROR_CODE,
					ResponseMessages.INTERNAL_SERVER_ERROR_MESSAGE);
		}
	}
}
