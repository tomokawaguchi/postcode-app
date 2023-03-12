package io.nology.postcodeRestApi.suburb;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

public class SuburbDTO {
	private Long id;

	@NotBlank
	private String suburbName;
	
	@NotNull(message = "Digit value is required")
	@Range(min=200, max=9999, message="Postcode must be between 200 to 9999")
	private Integer postcode;

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

	public Integer getPostcode() {
		return postcode;
	}

	public void setPostcode(Integer postcode) {
		this.postcode = postcode;
	}
}
