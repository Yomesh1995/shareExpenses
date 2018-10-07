package project.common;

public enum TransactionStatus {
	PENDING("Pending"), SETTLED("Settled");

	public String value;

	public String getValue() {
		return value;
	}

	TransactionStatus(String value) {
		this.value = value;
	}

}
