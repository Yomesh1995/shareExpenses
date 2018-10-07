package project.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

	public List<UserInfo> getSimiliarUsername(String username) throws CustomException {
		List<UserInfo> similiarUser = null;
		try {
			similiarUser = userDao.similiarUsername(username);
			if (similiarUser == null)
				throw new CustomException(ResponseMessages.USER_NOT_FOUND_CODE,
						ResponseMessages.USER_NOT_FOUND_MESSAGE);
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
			List<Integer> verifiedUsers = commonDao.validateUsersId(groupMembersId);
			int groupId = userDao.groupRegistration(group);
			if (groupId == 0)
				throw new CustomException(ResponseMessages.GROUP_NOT_CREATED_CODE,
						ResponseMessages.GROUP_NOT_CREATED_MESSAGE);
			userDao.groupMembersRegistration(groupId, verifiedUsers);
		} catch (CustomException e) {
			throw e;
		} catch (Exception e) {
			throw new CustomException(ResponseMessages.INTERNAL_SERVER_ERROR_CODE,
					ResponseMessages.INTERNAL_SERVER_ERROR_MESSAGE);
		}
	}

	public List<GroupRegistration> getSimiliarGroupname(String groupname) throws CustomException {
		List<GroupRegistration> similiarGroup = null;
		try {
			similiarGroup = userDao.similiarGroupname(groupname);
			if (similiarGroup == null)
				throw new CustomException(ResponseMessages.GROUP_NOT_FOUND_CODE,
						ResponseMessages.GROUP_NOT_FOUND_MESSAGE);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new CustomException(ResponseMessages.INTERNAL_SERVER_ERROR_CODE,
					ResponseMessages.INTERNAL_SERVER_ERROR_MESSAGE);
		}
		return similiarGroup;
	}
}
