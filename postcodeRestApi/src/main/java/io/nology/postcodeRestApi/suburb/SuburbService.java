package io.nology.postcodeRestApi.suburb;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@Transactional
public class SuburbService {
	@Autowired
	private SuburbRepository repositry;
	
	
	public List<Suburb> getAll() {
		return this.repositry.findAll();
	}
	
	
	public Optional<Suburb> getById(long id) {
		return this.repositry.findById(id);
	}
	
	public List<Suburb> getSuburbInfo(int postcode) {
		List<Suburb> suburbs = this.repositry.findSuburbByPostcode(postcode);
		
		return suburbs;
	}
	
	
	public List<Suburb> getPostcode(String suburbName) {
		List<Suburb> suburbs = this.repositry.findSuburbBySuburbName(suburbName);
		
		return suburbs; 
	}
	
	
	public Suburb createSuburbRecord(SuburbDTO suburbDto) {
		String lowerCasedName = suburbDto.getSuburbName().toLowerCase();
		
		Suburb cleanSuburb = new Suburb(lowerCasedName, suburbDto.getPostcode());
		
		this.repositry.save(cleanSuburb);
		
		return cleanSuburb;
	}

}
