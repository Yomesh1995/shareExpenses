package project.model;

import java.util.List;

public class SimiliarGroupNamesResponse extends CommonResponse {

	private List<GroupRegistration> similiarNames;

	public List<GroupRegistration> getSimiliarNames() {
		return similiarNames;
	}

	public void setSimiliarNames(List<GroupRegistration> similiarNames) {
		this.similiarNames = similiarNames;
	}

}
