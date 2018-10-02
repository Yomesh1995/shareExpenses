package project.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.common.CustomException;
import project.common.ResponseMessages;
import project.dao.CommonDaoImplementation;
import project.dao.UserDaoImplementation;
import project.model.GroupRegistration;
import project.model.UserInfo;

@Service
public class UserServiceImplementation {

	private static Logger logger = LogManager.getLogger(UserServiceImplementation.class);
	@Autowired
	private UserDaoImplementation userDao;
	@Autowired
	private CommonDaoImplementation commonDao;

	@Transactional(rollbackFor = { CustomException.class })
	public void userRegistration(UserInfo userInfo) throws CustomException {
		try {
			if (commonDao.isUserExist(userInfo.getUserName()))
				throw new CustomException(ResponseMessages.USER_EXIST_CODE, ResponseMessages.USER_EXIST_MESSAGE);
			userDao.userRegistration(userInfo);
		} catch (CustomException ex) {
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new CustomException(ResponseMessages.INTERNAL_SERVER_ERROR_CODE,
					ResponseMessages.INTERNAL_SERVER_ERROR_MESSAGE);
		}
	}

	public List<String> getSimiliarUsername(String username) throws CustomException {
		List<String> similiarUser = null;
		try {
			similiarUser = userDao.similiarUsername(username);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new CustomException(ResponseMessages.INTERNAL_SERVER_ERROR_CODE,
					ResponseMessages.INTERNAL_SERVER_ERROR_MESSAGE);
		}
		return similiarUser;
	}

	@Transactional(rollbackFor = { CustomException.class })
	public void groupRegistration(GroupRegistration group) throws CustomException {
		try {
			Set<Integer> groupMembersId = group.getGroupMembers().stream().map(UserInfo::getUserId)
					.collect(Collectors.toSet());
			if (groupMembersId.size() != group.getGroupMembers().size())
				throw new CustomException(ResponseMessages.DUPLICATE_USERS_CODE,
						ResponseMessages.DUPLICATE_USERS_MESSAGE);
			List<Integer> verifiedUsers=commonDao.validateUsersId(groupMembersId);
			int groupId = userDao.groupRegistration(group);
			userDao.groupMembersRegistration(groupId, verifiedUsers);			
		} catch (CustomException e) {
			throw e;
		} catch (Exception e) {
			throw new CustomException(ResponseMessages.INTERNAL_SERVER_ERROR_CODE,
					ResponseMessages.INTERNAL_SERVER_ERROR_MESSAGE);
		}
	}
}
