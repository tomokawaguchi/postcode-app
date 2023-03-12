package io.nology.postcodeRestApi.suburb;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/suburbs")
@Validated
@ControllerAdvice
public class SuburbController {
	@Autowired
	private SuburbService service;

	@GetMapping
	public ResponseEntity<List<Suburb>> getAll() {
		List<Suburb> result = this.service.getAll();

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Suburb> getById(@PathVariable @Positive Long id) {
		Optional<Suburb> result = this.service.getById(id);

		if (result.isEmpty()) {
			throw new SuburbNotFoundException("Suburb not found with requested id: " + id);
		}

		return new ResponseEntity<>(result.get(), HttpStatus.OK);
	}

	@GetMapping("/postcode/{postcode}")
	public ResponseEntity<List<Suburb>> getSuburbByPostcode(@PathVariable @Positive int postcode) {
		List<Suburb> results = this.service.getSuburbInfo(postcode);

		if (results.size() == 0) {
			throw new SuburbNotFoundException("Suburb not found with requested postcode: " + postcode);
		}

		return new ResponseEntity<>(results, HttpStatus.OK);
	}

	@GetMapping("/name/{suburbName}")
	public ResponseEntity<List<Suburb>> getSuburbBySuburbName(@PathVariable @NotBlank String suburbName) {
		List<Suburb> results = this.service.getPostcode(suburbName);

		if (results.size() == 0) {
			throw new SuburbNotFoundException("Suburb not found with requested suburb name: " + suburbName);
		}

		return new ResponseEntity<>(results, HttpStatus.OK);
	}

//	@PreAuthorize("hasRole('EDITOR')")
	@PostMapping("/create")
	public ResponseEntity<Suburb> createSuburbRecord(@RequestBody @Valid SuburbDTO suburbDto) {
		Suburb result = this.service.createSuburbRecord(suburbDto);

		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public static class SuburbNotFoundException extends RuntimeException {
		public SuburbNotFoundException() {
			super();
		}

		public SuburbNotFoundException(String message) {
			super(message);
		}
	}
}


//@RequestMapping(params = "postcode")
//@ResponseBody
//public ResponseEntity<List<Suburb>> getSuburbInfo(
//		@RequestParam @Size(min = 4, max = 4, message = "Australian postcodes must be 4 digits") String postcode) {
//	List<Suburb> results = this.service.getSuburbInfo(postcode);
//
//	if (results.size() == 0) {
//		return new ResponseEntity<>(results, HttpStatus.NOT_FOUND);
//	}
//
//	return new ResponseEntity<>(results, HttpStatus.OK);
//}

//@RequestMapping(params = "suburbName")
//@ResponseBody
//public ResponseEntity<List<Suburb>> getPostcode(@RequestParam @NotBlank String suburbName) {
//	List<Suburb> results = this.service.getPostcode(suburbName);
//
//	if (results.size() == 0) {
//		return new ResponseEntity<>(results, HttpStatus.NOT_FOUND);
//	}
//
//	return new ResponseEntity<>(results, HttpStatus.OK);
//}
