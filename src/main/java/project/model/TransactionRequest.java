package project.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

public class TransactionRequest {

	@Min(value = 1, message = "Invalid Username")
	private int fromUser;
	@Min(value = 1, message = "Invalid TargetName")
	private int toUser;
	@DecimalMin(value = "0.01", message = "Invalid Amount")
	private double amount;

	public int getFromUser() {
		return fromUser;
	}

	public void setFromUser(int fromUser) {
		this.fromUser = fromUser;
	}

	public int getToUser() {
		return toUser;
	}

	public void setToUser(int toUser) {
		this.toUser = toUser;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
