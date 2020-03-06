package ua.lviv.lgs.admission;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.collection.IsCollectionWithSize.*;

import ua.lviv.lgs.dao.FacultyRepository;
import ua.lviv.lgs.dao.RegisteredEntrantRepository;
import ua.lviv.lgs.dao.StatementRepository;
import ua.lviv.lgs.dao.UserRepository;
import ua.lviv.lgs.domain.Faculty;
import ua.lviv.lgs.domain.RegisteredEntrant;
import ua.lviv.lgs.domain.Statement;
import ua.lviv.lgs.domain.Subjects;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.domain.UserRole;
import ua.lviv.lgs.service.FacultyService;
import ua.lviv.lgs.service.RegisteredEntrantService;
import ua.lviv.lgs.service.StatementService;
import ua.lviv.lgs.service.UserService;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AdmissionSystemApplicationTests {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FacultyService facultyService;
	
	@Autowired
	private StatementService statementService;
	
	@Autowired
	private RegisteredEntrantService registeredEntrantService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FacultyRepository facultyRepository;
	
	@Autowired
	private StatementRepository statementRepository;
	
	@Autowired
	private RegisteredEntrantRepository registeredEntrantRepository;

	@Test
 	public void testSaveUser() {
		List<User> users = userRepository.findAll();
		assertThat(users, hasSize(0));
		
		User user = new User();
		user.setEmail("test@mail.com");
		user.setFirstName("Andy");
		user.setLastName("Mask");
		user.setPassword("123");
		user.setPasswordConfirm("123");
		user.setRole(UserRole.ROLE_USER);
		
		userRepository.save(user);
		
		users = userRepository.findAll();
		assertThat(users, hasSize(1));
		
		User userFromDB = users.get(0);
		assertTrue(userFromDB.getEmail().equals(user.getEmail()));
		assertTrue(userFromDB.getFirstName().equals(user.getFirstName()));
		assertTrue(userFromDB.getLastName().equals(user.getLastName()));
		assertTrue(userFromDB.getRole().equals(user.getRole()));
	}
	
	@Test
	public void testFindUserByEmail() {
		List<User> users = userRepository.findAll();
		assertThat(users, hasSize(0));
		
		User user = new User();
		user.setEmail("test@mail.com");
		user.setFirstName("Andy");
		user.setLastName("Mask");
		user.setPassword("123");
		user.setPasswordConfirm("123");
		user.setRole(UserRole.ROLE_USER);
		
		userRepository.save(user);
		
		users = userRepository.findAll();
		assertThat(users, hasSize(1));
		
		User findByEmail = userService.findByEmail(user.getEmail());
		assertTrue(findByEmail.getEmail().equals(user.getEmail()));
		assertTrue(findByEmail.getFirstName().equals(user.getFirstName()));
		assertTrue(findByEmail.getLastName().equals(user.getLastName()));
		assertTrue(findByEmail.getRole().equals(user.getRole()));
	}
	
	@Test
	public void testFindUserById() {
		List<User> users = userRepository.findAll();
		assertThat(users, hasSize(0));
		
		User user = new User();
		user.setEmail("test@mail.com");
		user.setFirstName("Andy");
		user.setLastName("Mask");
		user.setPassword("123");
		user.setPasswordConfirm("123");
		user.setRole(UserRole.ROLE_USER);
		
		userRepository.save(user);
		
		users = userRepository.findAll();
		assertThat(users, hasSize(1));
		
		User findById = userService.findById(users.get(0).getId());
		assertTrue(findById.getEmail().equals(user.getEmail()));
		assertTrue(findById.getFirstName().equals(user.getFirstName()));
		assertTrue(findById.getLastName().equals(user.getLastName()));
		assertTrue(findById.getRole().equals(user.getRole()));
	}
	
	@Test
	public void testSaveFaculty() {
		List<Faculty> faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(0));
		
		Faculty faculty = new Faculty();
		faculty.setName("Faculty1");
		faculty.setQuantityOfStudents(40);
		faculty.setSubjects(Arrays.asList(Subjects.CHEMISTRY, Subjects.GEOGRAPHY, Subjects.CERTIFICATE_MARK));
		
		facultyRepository.save(faculty);
		
		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(1));
		
		Faculty facultyFromDB = faculties.get(0);
		assertTrue(facultyFromDB.getName().equals(faculty.getName()));
		assertTrue(facultyFromDB.getQuantityOfStudents().equals(faculty.getQuantityOfStudents()));
		assertTrue(facultyFromDB.getSubjects().equals(faculty.getSubjects()));
	} 

	@Test
	public void testFindFacultyById() {
		List<Faculty> faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(0));
		
		Faculty faculty = new Faculty();
		faculty.setName("Faculty2");
		faculty.setQuantityOfStudents(35);
		faculty.setSubjects(Arrays.asList(Subjects.HISTORY, Subjects.INFORMATIC, Subjects.MATHEMATIC));
		
		facultyRepository.save(faculty);
		
		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(1));
		
		Faculty findFacultyById = facultyService.findFacultyById(faculties.get(0).getId());
		assertTrue(findFacultyById.getName().equals(faculty.getName()));
		assertTrue(findFacultyById.getQuantityOfStudents().equals(faculty.getQuantityOfStudents()));
		assertTrue(findFacultyById.getSubjects().equals(faculty.getSubjects()));
	} 

	@Test
	public void testFindAllFaculties() {
		List<Faculty> faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(0));
		
		Faculty faculty = new Faculty();
		faculty.setName("Faculty2");
		faculty.setQuantityOfStudents(35);
		faculty.setSubjects(Arrays.asList(Subjects.HISTORY, Subjects.INFORMATIC, Subjects.MATHEMATIC));
		
		Faculty faculty2 = new Faculty();
		faculty2.setName("Faculty3");
		faculty2.setQuantityOfStudents(30);
		faculty2.setSubjects(Arrays.asList(Subjects.PHYSIC, Subjects.SPORTS, Subjects.UKRAINIAN_LANGUAGE));
		
		facultyRepository.saveAll(Arrays.asList(faculty, faculty2));
		
		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(2));
		
		List<Faculty> facultiesFromDB = facultyService.findAllFaculties();
		assertTrue(facultiesFromDB.get(0).getName().equals(faculty.getName()));
		assertTrue(facultiesFromDB.get(0).getQuantityOfStudents().equals(faculty.getQuantityOfStudents()));
		assertTrue(facultiesFromDB.get(0).getSubjects().equals(faculty.getSubjects()));
		
		assertTrue(facultiesFromDB.get(1).getName().equals(faculty2.getName()));
		assertTrue(facultiesFromDB.get(1).getQuantityOfStudents().equals(faculty2.getQuantityOfStudents()));
		assertTrue(facultiesFromDB.get(1).getSubjects().equals(faculty2.getSubjects()));		
	} 

	@Test
	public void testSaveStatement() {
		List<Statement> statements = statementRepository.findAll();
		assertThat(statements, hasSize(0));
		
		Statement statement = new Statement();
		statement.setFacultyId(1);
		statement.setUserId(2);
		statement.setStatementMarks(Arrays.asList(89.0, 90.0, 90.0));
		
		statementRepository.save(statement);
		
		statements = statementRepository.findAll();
		assertThat(statements, hasSize(1));
		
		Statement statementFromDB = statements.get(0);
		assertTrue(statementFromDB.getFacultyId().equals(statement.getFacultyId()));
		assertTrue(statementFromDB.getUserId().equals(statement.getUserId()));
		assertTrue(statementFromDB.getStatementMarks().equals(statement.getStatementMarks()));
	}
	
	@Test
	public void testFindAllStatements() {
		List<Statement> statements = statementRepository.findAll();
		assertThat(statements, hasSize(0));
		
		Statement statement = new Statement();
		statement.setFacultyId(1);
		statement.setUserId(1);
		statement.setStatementMarks(Arrays.asList(88.0, 89.0, 90.0));
		
		Statement statement2 = new Statement();
		statement2.setFacultyId(3);
		statement2.setUserId(12);
		statement2.setStatementMarks(Arrays.asList(77.0, 40.0, 99.0));
		
		statementRepository.saveAll(Arrays.asList(statement, statement2));
		
		statements = statementRepository.findAll();
		assertThat(statements, hasSize(2));
		
		List<Statement> statementsFromDB = statementService.findAllStatements();
		assertTrue(statementsFromDB.get(0).getFacultyId().equals(statement.getFacultyId()));
		assertTrue(statementsFromDB.get(0).getUserId().equals(statement.getUserId()));
		assertTrue(statementsFromDB.get(0).getStatementMarks().equals(statement.getStatementMarks()));
		
		assertTrue(statementsFromDB.get(1).getFacultyId().equals(statement2.getFacultyId()));
		assertTrue(statementsFromDB.get(1).getUserId().equals(statement2.getUserId()));
		assertTrue(statementsFromDB.get(1).getStatementMarks().equals(statement2.getStatementMarks()));
	} 

	@Test
	public void testSaveRegisteredEntrant() {
		List<User> users = userRepository.findAll();
		List<Faculty> faculties = facultyRepository.findAll();
		List<Double> marks = Arrays.asList(88.0, 89.0, 87.0);
		List<RegisteredEntrant> registeredEntrants = registeredEntrantRepository.findAll();
		
		assertThat(users, hasSize(0));
		assertThat(faculties, hasSize(0));
		assertThat(registeredEntrants, hasSize(0));
		
		User user = new User();
		user.setFirstName("Ilon");
		user.setLastName("Mask");
		user.setEmail("test@mail.com");
		user.setPassword("11");
		user.setPasswordConfirm("11");
		user.setRole(UserRole.ROLE_USER);
		
		userRepository.save(user);
		users = userRepository.findAll();
		assertThat(users, hasSize(1));
		
		Faculty faculty = new Faculty();
		faculty.setName("Faculty1");
		faculty.setQuantityOfStudents(40);
		faculty.setSubjects(Arrays.asList(Subjects.SPORTS, Subjects.INFORMATIC, Subjects.MATHEMATIC));
		
		facultyRepository.save(faculty);
		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(1));
		
		User userFromDB = users.get(0);
		Faculty facultyFromDB = faculties.get(0);
		
		RegisteredEntrant registeredEntrant = new RegisteredEntrant(userFromDB, facultyFromDB, marks);
		registeredEntrant.setEncodedEntrantImage("122334");
		
		registeredEntrantRepository.save(registeredEntrant);
		registeredEntrants = registeredEntrantRepository.findAll();
		assertThat(registeredEntrants, hasSize(1));

		RegisteredEntrant registeredEntrantFromDB = registeredEntrants.get(0);
		
		assertTrue(registeredEntrantFromDB.getUser().equals(registeredEntrant.getUser()));
		assertTrue(registeredEntrantFromDB.getFaculty().equals(registeredEntrant.getFaculty()));
		assertTrue(registeredEntrantFromDB.getMarks().equals(registeredEntrant.getMarks()));
		assertTrue(registeredEntrantFromDB.getEncodedEntrantImage().equals(registeredEntrant.getEncodedEntrantImage()));
	} 
	
	@Test
	public void testFindRegisteredEntrantById() {
		List<User> users = userRepository.findAll();
		List<Faculty> faculties = facultyRepository.findAll();
		List<Double> marks = Arrays.asList(88.0, 89.0, 87.0);
		List<RegisteredEntrant> registeredEntrants = registeredEntrantRepository.findAll();
		
		assertThat(users, hasSize(0));
		assertThat(faculties, hasSize(0));
		assertThat(registeredEntrants, hasSize(0));
		
		User user = new User();
		user.setFirstName("Ilon");
		user.setLastName("Mask");
		user.setEmail("test@mail.com");
		user.setPassword("11");
		user.setPasswordConfirm("11");
		user.setRole(UserRole.ROLE_USER);
		
		userRepository.save(user);
		users = userRepository.findAll();
		assertThat(users, hasSize(1));
		
		Faculty faculty = new Faculty();
		faculty.setName("Faculty1");
		faculty.setQuantityOfStudents(40);
		faculty.setSubjects(Arrays.asList(Subjects.SPORTS, Subjects.INFORMATIC, Subjects.MATHEMATIC));
		
		facultyRepository.save(faculty);
		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(1));
		
		User userFromDB = users.get(0);
		Faculty facultyFromDB = faculties.get(0);
		
		RegisteredEntrant registeredEntrant = new RegisteredEntrant(userFromDB, facultyFromDB, marks);
		registeredEntrant.setEncodedEntrantImage("122334");
		
		registeredEntrantRepository.save(registeredEntrant);
		registeredEntrants = registeredEntrantRepository.findAll();
		assertThat(registeredEntrants, hasSize(1));

		RegisteredEntrant registeredEntrantFromDB = registeredEntrantService.findById(registeredEntrants.get(0).getId());
		
		assertTrue(registeredEntrantFromDB.getUser().equals(registeredEntrant.getUser()));
		assertTrue(registeredEntrantFromDB.getFaculty().equals(registeredEntrant.getFaculty()));
		assertTrue(registeredEntrantFromDB.getMarks().equals(registeredEntrant.getMarks()));
		assertTrue(registeredEntrantFromDB.getEncodedEntrantImage().equals(registeredEntrant.getEncodedEntrantImage()));
	} 
	
	@Test
	public void testFindAllRegisteredEntrants() {
		List<User> users = userRepository.findAll();
		List<Faculty> faculties = facultyRepository.findAll();
		List<Double> marks1 = Arrays.asList(91.0, 90.0, 93.0);
		List<Double> marks2 = Arrays.asList(80.0, 84.0, 79.0);
		List<RegisteredEntrant> registeredEntrants = registeredEntrantRepository.findAll();
		
		assertThat(users, hasSize(0));
		assertThat(faculties, hasSize(0));
		assertThat(registeredEntrants, hasSize(0));
		
		User user1 = new User();
		user1.setFirstName("Ilon");
		user1.setLastName("Mask");
		user1.setEmail("example@mail.com");
		user1.setPassword("12");
		user1.setPasswordConfirm("12");
		user1.setRole(UserRole.ROLE_USER);
		
		User user2 = new User();
		user2.setFirstName("Mark");
		user2.setLastName("Zuckerberg");
		user2.setEmail("test@mail.com");
		user2.setPassword("123");
		user2.setPasswordConfirm("123");
		user2.setRole(UserRole.ROLE_USER);
		
		userRepository.saveAll(Arrays.asList(user1, user2));
		users = userRepository.findAll();
		assertThat(users, hasSize(2));
		
		Faculty faculty1 = new Faculty();
		faculty1.setName("Facultu1");
		faculty1.setQuantityOfStudents(40);
		faculty1.setSubjects(Arrays.asList(Subjects.GEOGRAPHY, Subjects.CHEMISTRY, Subjects.CERTIFICATE_MARK));
		
		Faculty faculty2 = new Faculty();
		faculty2.setName("Faculty2");
		faculty2.setQuantityOfStudents(38);
		faculty2.setSubjects(Arrays.asList(Subjects.ENGLISH, Subjects.MATHEMATIC, Subjects.INFORMATIC));
		
		facultyRepository.saveAll(Arrays.asList(faculty1, faculty2));
		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(2));
		
		RegisteredEntrant registeredEntrant1 = new RegisteredEntrant(users.get(0), faculties.get(0), marks1);
		registeredEntrant1.setEncodedEntrantImage("142124");
		
		RegisteredEntrant registeredEntrant2 = new RegisteredEntrant(users.get(1), faculties.get(1), marks2);
		registeredEntrant2.setEncodedEntrantImage("67896");
		
		registeredEntrantRepository.saveAll(Arrays.asList(registeredEntrant1, registeredEntrant2));
		List<RegisteredEntrant> registeredEntrantsFromDB = registeredEntrantService.findAllRegisteredEntrants();
		assertThat(registeredEntrantsFromDB, hasSize(2));
		
		assertTrue(registeredEntrantsFromDB.get(0).getUser().equals(registeredEntrant1.getUser()));
		assertTrue(registeredEntrantsFromDB.get(0).getFaculty().equals(registeredEntrant1.getFaculty()));
		assertTrue(registeredEntrantsFromDB.get(0).getMarks().equals(registeredEntrant1.getMarks()));
		assertTrue(registeredEntrantsFromDB.get(0).getEncodedEntrantImage().equals(registeredEntrant1.getEncodedEntrantImage()));
		
		assertTrue(registeredEntrantsFromDB.get(1).getUser().equals(registeredEntrant2.getUser()));
		assertTrue(registeredEntrantsFromDB.get(1).getFaculty().equals(registeredEntrant2.getFaculty()));
		assertTrue(registeredEntrantsFromDB.get(1).getMarks().equals(registeredEntrant2.getMarks()));
		assertTrue(registeredEntrantsFromDB.get(1).getEncodedEntrantImage().equals(registeredEntrant2.getEncodedEntrantImage()));
	} 

	@Test
	public void testDeleteRegisteredEntrantById() {
		List<User> users = userRepository.findAll();
		List<Faculty> faculties = facultyRepository.findAll();
		List<Double> marks1 = Arrays.asList(89.0, 89.0, 91.0);
		List<Double> marks2 = Arrays.asList(78.0, 57.0, 75.0);
		List<RegisteredEntrant> registeredEntrants = registeredEntrantRepository.findAll();
		
		assertThat(users, hasSize(0));
		assertThat(faculties, hasSize(0));
		assertThat(registeredEntrants, hasSize(0));
		
		User user1 = new User();
		user1.setFirstName("Ilon");
		user1.setLastName("Mask");
		user1.setEmail("example@mail.com");
		user1.setPassword("12");
		user1.setPasswordConfirm("12");
		user1.setRole(UserRole.ROLE_USER);
		
		User user2 = new User();
		user2.setFirstName("Mark");
		user2.setLastName("Zuckerberg");
		user2.setEmail("test@mail.com");
		user2.setPassword("123");
		user2.setPasswordConfirm("123");
		user2.setRole(UserRole.ROLE_USER);
		
		userRepository.saveAll(Arrays.asList(user1, user2));
		users = userRepository.findAll();
		assertThat(users, hasSize(2));
		
		Faculty faculty1 = new Faculty();
		faculty1.setName("Facultu1");
		faculty1.setQuantityOfStudents(40);
		faculty1.setSubjects(Arrays.asList(Subjects.GEOGRAPHY, Subjects.CHEMISTRY, Subjects.CERTIFICATE_MARK));
		
		Faculty faculty2 = new Faculty();
		faculty2.setName("Faculty2");
		faculty2.setQuantityOfStudents(38);
		faculty2.setSubjects(Arrays.asList(Subjects.ENGLISH, Subjects.MATHEMATIC, Subjects.INFORMATIC));
		
		facultyRepository.saveAll(Arrays.asList(faculty1, faculty2));
		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(2));
		
		RegisteredEntrant registeredEntrant1 = new RegisteredEntrant(users.get(0), faculties.get(0), marks1);
		registeredEntrant1.setEncodedEntrantImage("142124");
		
		RegisteredEntrant registeredEntrant2 = new RegisteredEntrant(users.get(1), faculties.get(1), marks2);
		registeredEntrant2.setEncodedEntrantImage("67896");
		
		registeredEntrantRepository.saveAll(Arrays.asList(registeredEntrant1, registeredEntrant2));
		List<RegisteredEntrant> registeredEntrantsFromDB = registeredEntrantService.findAllRegisteredEntrants();
		assertThat(registeredEntrantsFromDB, hasSize(2));
		
		registeredEntrantService.deleteById(registeredEntrantsFromDB.get(0).getId());
		
		registeredEntrantsFromDB = registeredEntrantRepository.findAll();
		assertThat(registeredEntrantsFromDB, hasSize(1));
		
		assertTrue(registeredEntrantsFromDB.get(0).getUser().equals(registeredEntrant2.getUser()));
		assertTrue(registeredEntrantsFromDB.get(0).getFaculty().equals(registeredEntrant2.getFaculty()));
		assertTrue(registeredEntrantsFromDB.get(0).getMarks().equals(registeredEntrant2.getMarks()));
		assertTrue(registeredEntrantsFromDB.get(0).getEncodedEntrantImage().equals(registeredEntrant2.getEncodedEntrantImage()));
	} 
}
