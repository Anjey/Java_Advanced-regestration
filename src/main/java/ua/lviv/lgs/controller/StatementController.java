package ua.lviv.lgs.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.lviv.lgs.domain.Faculty;
import ua.lviv.lgs.domain.Statement;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.service.FacultyService;
import ua.lviv.lgs.service.RegisteredEntrantService;
import ua.lviv.lgs.service.StatementService;
import ua.lviv.lgs.service.UserService;

@Controller
public class StatementController {

	@Autowired
	private UserService userService;

	@Autowired
	private FacultyService facultyService;

	@Autowired
	private StatementService statementService;
	
	@Autowired
	private RegisteredEntrantService registeredEntrantService;

	@RequestMapping(value = "/entrantSubmiting", method = RequestMethod.POST)
	public ModelAndView entrantSubmiting(@RequestParam("facultyId") Integer facultyId,
			@RequestParam("userId") Integer userId, @RequestParam("entrantId") Integer entrantId) {
		Statement statement = new Statement();
		
		statement.setUserId(userId);
		statement.setFacultyId(facultyId);
		statement.setStatementMarks(new ArrayList<Double>(registeredEntrantService.findById(entrantId).getMarks()));
		
		statementService.save(statement);
		
		registeredEntrantService.deleteById(entrantId);
		
		ModelAndView modelAndView = new ModelAndView("registeredEntrants");
		modelAndView.addObject("registeredEntrants", registeredEntrantService.findAllRegisteredEntrants());
		return modelAndView;
	}

	@RequestMapping(value = "/rating", method = RequestMethod.GET)
	public ModelAndView showRating(@RequestParam("currentFacultyId") Integer currentFacultyId) {
		List<Statement> listofAllStatements = statementService.findAllStatements();
		Faculty currentFaculty = facultyService.findFacultyById(currentFacultyId);
		
		List<Statement> statementOfCurrentFaculty = listofAllStatements.stream().filter(statement -> facultyService.findFacultyById(statement.getFacultyId()).getName().equals(currentFaculty.getName())).collect(Collectors.toList());
		
		System.out.println("Before sorting: " + statementOfCurrentFaculty);
		
		Collections.sort(statementOfCurrentFaculty);
		statementOfCurrentFaculty.stream().forEach(statement -> {
			if (statementOfCurrentFaculty.indexOf(statement) < facultyService.findFacultyById(currentFacultyId).getQuantityOfStudents())
				statement.setAccepted(true);
		});
		
		System.out.println("After sorting and rating: " + statementOfCurrentFaculty);
		
		List<User> userOfCurrentFaculty = statementOfCurrentFaculty.stream().map(u -> userService.findById(u.getUserId())).collect(Collectors.toList());
		List<Boolean> isUserAccepted = statementOfCurrentFaculty.stream().map(a -> a.isAccepted()).collect(Collectors.toList());
		
		ModelAndView modelAndView = new ModelAndView("rating");
		modelAndView.addObject("statements", statementOfCurrentFaculty);
		modelAndView.addObject("users", userOfCurrentFaculty);
		modelAndView.addObject("faculty", currentFaculty);
		modelAndView.addObject("accepting", isUserAccepted);
		return modelAndView;
	}

}
