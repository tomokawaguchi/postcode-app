package io.nology.postcodeRestApi.suburb;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SuburbDTO {
private Long id;
	
	@NotBlank
	private String suburbName;
	
	@NotBlank
	@Size(min = 4, max = 4, message = "postcode should be 4 digit numbers")
	private String postcode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSuburbName() {
		return suburbName;
	}

	public void setSuburbName(String suburbName) {
		this.suburbName = suburbName;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
}
