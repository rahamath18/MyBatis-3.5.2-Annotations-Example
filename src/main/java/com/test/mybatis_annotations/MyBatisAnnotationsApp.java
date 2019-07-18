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

public class MyBatisAnnotationsApp {

	public static void main(String[] args) throws IOException, SQLException {

		Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sqlSessionFactory.openSession();   
		session.getConfiguration().addMapper(UserAnnotationsMapper.class);
		UserAnnotationsMapper mapper = session.getMapper(UserAnnotationsMapper.class);

		insertUser(session, mapper);

		fetchAllUser(mapper);
		fetchUserById(mapper, 2);
		fetchUserById(mapper, 3);

		updateUser(session, mapper);
		fetchAllUser(mapper);

		deleteUserById(session, mapper, 2);
		fetchAllUser(mapper);

		userStoredProcedure(mapper, 3);

		session.close();

	}

	private static void userStoredProcedure(UserAnnotationsMapper mapper, int id) {
		try {
			System.out.println("Using MyBatis - Stored Procedures...");
			User user = (User) mapper.getCallByUserId(id);
			System.out.println("Using MyBatis - Stored Procedures..." + user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void deleteUserById(SqlSession session, UserAnnotationsMapper mapper, int id) {
		try {
			System.out.println("Deleting In Table...");

			mapper.deleteUserById(id);
			session.commit();
			System.out.println("User Deleted Successfully ");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void updateUser(SqlSession session, UserAnnotationsMapper mapper) {
		try {
			System.out.println("Updating In Table...");
			List<User> userList = fetchAllUser(mapper);
			for (User user : userList) {
				user.setLastName(user.getLastName() + " - Updated");
				user.setEmail("Updated" + "-" + user.getEmail());
				mapper.updateUser(user);
			}
			session.commit();
			System.out.println("User Updated Successfully ");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void fetchUserById(UserAnnotationsMapper mapper, int id) {
		try {
			System.out.println("Fetching By User Id From Table...");

			User user = mapper.getUserById(id);
			System.out.println("User by id : " + user);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static List<User> fetchAllUser(UserAnnotationsMapper mapper) {
		try {
			System.out.println("Fetching All Users From Table...");

			List<User> userList = mapper.getAllUsers();
			for (User user : userList) {
				System.out.println(user);
			}

			return userList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<User>();
	}

	private static void insertUser(SqlSession session, UserAnnotationsMapper mapper) {
		try {
			System.out.println("Inserting Into Table...");

			User newUser = new User("Rahamath", "S", "rahamath18@yahoo.com");
			System.out.println("Before Save : " + newUser);
			mapper.insertUser(newUser);
			System.out.println("After Save : " + newUser);

			for (int i = 2; i <= 3; i++) {
				User user = new User("User-" + i, "U-" + i, "user-" + i + "@info.com");
				mapper.insertUser(user);
			}
			session.commit();
			System.out.println("Record Inserted Successfully ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
