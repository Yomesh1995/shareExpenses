package project.model;

import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class GroupTransactionRequest {

	@Min(value = 1, message = "Invalid Username")
	private int fromUser;
	@NotEmpty(message = "Invalid Target Users")
	private List<Integer> toUsers;
	@DecimalMin(value = "0.01", message = "Invalid Amount")
	private double amount;
	@Min(value = 1, message = "Invalid Group")
	private int groupId;

	public int getFromUser() {
		return fromUser;
	}

	public void setFromUser(int fromUser) {
		this.fromUser = fromUser;
	}

	public List<Integer> getToUsers() {
		return toUsers;
	}

	public void setToUsers(List<Integer> toUsers) {
		this.toUsers = toUsers;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

}
