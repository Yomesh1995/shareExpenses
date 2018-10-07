package project.model;

import java.util.List;

public class SimiliarUserNamesResponse extends CommonResponse {

	private List<UserInfo> similiarNames;

	public List<UserInfo> getSimiliarNames() {
		return similiarNames;
	}

	public void setSimiliarNames(List<UserInfo> similiarNames) {
		this.similiarNames = similiarNames;
	}

}
