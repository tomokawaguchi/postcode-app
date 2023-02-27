package io.nology.postcodeRestApi.suburb;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/suburbs")
public class SuburbController {
	@Autowired
	private SuburbService service;


	@GetMapping
	public ResponseEntity<List<Suburb>> getAll() {
		List<Suburb> result = this.service.getAll();

		return new ResponseEntity<>(result, HttpStatus.OK);
	}


	@GetMapping("/{id}")
	public ResponseEntity<Suburb> getById(@PathVariable Long id) {
		Optional<Suburb> result = this.service.getById(id);

		if (result.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(result.get(), HttpStatus.NOT_FOUND);
	}


	@RequestMapping(params = "postcode")
	@ResponseBody
	public ResponseEntity<List<Suburb>> getSuburbInfo(@RequestParam String postcode) {
		// suburbs?postcode=1234
		List<Suburb> results = this.service.getSuburbInfo(postcode);

		if (results.size() == 0) {
			return new ResponseEntity<>(results, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(results, HttpStatus.OK);
	}


	@RequestMapping(params = "suburbName")
	@ResponseBody
	public ResponseEntity<List<Suburb>> getPostcode(@RequestParam String suburbName) {
		// suburbs?suburb=sydney
		List<Suburb> results = this.service.getPostcode(suburbName);

		if (results.size() == 0) {
			return new ResponseEntity<>(results, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(results, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('EDITOR')")
	@PostMapping("/create")
	public ResponseEntity<Suburb> createSuburbRecord(@RequestBody @Valid SuburbDTO suburbDto) {
		Suburb result = this.service.createSuburbRecord(suburbDto);

		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
}
