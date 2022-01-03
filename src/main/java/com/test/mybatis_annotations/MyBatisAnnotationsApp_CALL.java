package com.test.mybatis_annotations;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisAnnotationsApp_CALL {

	public static void main(String[] args) throws IOException, SQLException {

		Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sqlSessionFactory.openSession();   
		session.getConfiguration().addMapper(PersonAnnotationsMapper.class);
		PersonAnnotationsMapper mapper = session.getMapper(PersonAnnotationsMapper.class);

		personStoredProcedure(mapper, 3);

		session.close();

	}

	private static void personStoredProcedure(PersonAnnotationsMapper mapper, int id) {
		try {
			System.out.println("Using MyBatis - Stored Procedures...");
			Person person = (Person) mapper.getCallByPersonId(id);
			System.out.println("Using MyBatis - Stored Procedures..." + person);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void deletePersonById(SqlSession session, PersonAnnotationsMapper mapper, int id) {
		try {
			System.out.println("Deleting In Table...");

			mapper.deletePersonById(id);
			session.commit();
			System.out.println("Person Deleted Successfully ");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void updatePerson(SqlSession session, PersonAnnotationsMapper mapper) {
		try {
			System.out.println("Updating In Table...");
			List<Person> personList = fetchAllPerson(mapper);
			for (Person person : personList) {
				person.setLastName(person.getLastName() + " - Updated");
				person.setEmail("Updated" + "-" + person.getEmail());
				mapper.updatePerson(person);
			}
			session.commit();
			System.out.println("Person Updated Successfully ");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void fetchPersonById(PersonAnnotationsMapper mapper, int id) {
		try {
			System.out.println("Fetching By Person Id From Table...");

			Person person = mapper.getPersonById(id);
			System.out.println("Person by id : " + person);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static List<Person> fetchAllPerson(PersonAnnotationsMapper mapper) {
		try {
			System.out.println("Fetching All Persons From Table...");

			List<Person> personList = mapper.getAllPersons();
			for (Person person : personList) {
				System.out.println(person);
			}

			return personList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Person>();
	}

	private static void insertPerson(SqlSession session, PersonAnnotationsMapper mapper) {
		try {
			System.out.println("Inserting Into Table...");

			Person newPerson = new Person("Rahamath", "S", "rahamath18@yahoo.com");
			System.out.println("Before Save : " + newPerson);
			mapper.insertPerson(newPerson);
			System.out.println("After Save : " + newPerson);

			for (int i = 2; i <= 3; i++) {
				Person person = new Person("Person-" + i, "U-" + i, "person-" + i + "@info.com");
				mapper.insertPerson(person);
			}
			session.commit();
			System.out.println("Record Inserted Successfully ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
