package ua.lviv.lgs.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.dao.StatementRepository;
import ua.lviv.lgs.domain.Statement;

@Service
public class StatementService {
	
	private Logger logger = LoggerFactory.getLogger(StatementService.class);

	@Autowired
	private StatementRepository statementRepository;

	public void save(Statement statement) {
		logger.info("Save statement {} - " + statement);	
		statementRepository.save(statement);
	}

	public List<Statement> findAllStatements() {
		logger.info("Find all registered statement");
		return statementRepository.findAll();
	}

}
