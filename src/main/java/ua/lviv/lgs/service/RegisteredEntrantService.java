package ua.lviv.lgs.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.dao.RegisteredEntrantRepository;
import ua.lviv.lgs.domain.RegisteredEntrant;

@Service
public class RegisteredEntrantService {
	
	private Logger logger = LoggerFactory.getLogger(RegisteredEntrantService.class);

	@Autowired
	private RegisteredEntrantRepository registeredEntrantRepository;
	
	public void save(RegisteredEntrant entrant) {
		logger.info("Save registered entrant {} - " + entrant);	
		registeredEntrantRepository.save(entrant);
	}
	
	public RegisteredEntrant findById(Integer id) {
		logger.info("Find registered entrant by id - " + id);	
		return registeredEntrantRepository.findById(id).get();
	}
	
	public List<RegisteredEntrant> findAllRegisteredEntrants() {
		logger.info("Find all registered entrant");	
		return registeredEntrantRepository.findAll();
	}
	
	public void deleteById(Integer id) {
		logger.info("Delete registered entrant by id - " + id);	
		registeredEntrantRepository.deleteById(id);
	}
	
}
