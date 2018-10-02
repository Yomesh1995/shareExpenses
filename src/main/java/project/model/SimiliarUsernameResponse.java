package project.model;

import java.util.List;

public class SimiliarUsernameResponse extends CommonResponse {

	private List<String> similiarUsername;

	public List<String> getSimiliarUsername() {
		return similiarUsername;
	}

	public void setSimiliarUsername(List<String> similiarUsername) {
		this.similiarUsername = similiarUsername;
	}

}
