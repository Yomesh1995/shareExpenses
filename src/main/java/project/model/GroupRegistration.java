package project.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class GroupRegistration {

	@NotEmpty(message = "Group name must required")
	private String groupName;
	@NotEmpty(message = "Group members must required")
	@Size(min = 1, message = "Their should be at least one member in group")
	private List<UserInfo> groupMembers;

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
		return "GroupRegistration [groupName=" + groupName + ", groupMembers=" + groupMembers + "]";
	}

}
