package io.nology.postcodeRestApi.suburb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface SuburbRepository extends JpaRepository<Suburb, Long> {
List<Suburb> findSuburbByPostcode(int postcode);
	
	
	List<Suburb> findSuburbBySuburbName(String suburbName);
}
