package project.common;

public class CustomException extends Exception {

	private static final long serialVersionUID = 1L;
	private int errorCode;
	private String errorMessage;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public CustomException(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public CustomException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public CustomException(int errorCode) {
		this.errorCode = errorCode;
	}

	public CustomException() {
	}
}
