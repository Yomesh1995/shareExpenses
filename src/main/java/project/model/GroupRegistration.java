package project.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_DEFAULT)
public class GroupRegistration {

	private int groupId;
	@NotEmpty(message = "Group name must required")
	private String groupName;
	@NotEmpty(message = "Group members must required")
	@Size(min = 1, message = "Their should be at least one member in group")
	private List<UserInfo> groupMembers;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<UserInfo> getGroupMembers() {
		return groupMembers;
	}

	public void setGroupMembers(List<UserInfo> groupMembers) {
		this.groupMembers = groupMembers;
	}

	@Override
	public String toString() {
		return "GroupRegistration [groupId=" + groupId + ", groupName=" + groupName + ", groupMembers=" + groupMembers
				+ "]";
	}

}
