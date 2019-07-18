package com.test.mybatis_annotations;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

public interface UserAnnotationsMapper {

	   final String insertUser = "INSERT INTO USER (FIRST_NAME, LAST_NAME, EMAIL ) VALUES (#{firstName}, #{lastName}, #{email})";
	   final String getAllUsers = "SELECT * FROM USER"; 
	   final String getUserById = "SELECT * FROM USER WHERE ID = #{id}";
	   final String updateUser = "UPDATE USER SET LAST_NAME = #{lastName}, EMAIL = #{email} WHERE ID = #{id}";
	   final String deleteUserById = "DELETE from USER WHERE ID = #{id}";
	   final String callByUserId = "{call getUserById(#{id, jdbcType = INTEGER, mode = IN})}";
	   final String getUsersByEmail = "SELECT * FROM USER WHERE EMAIL != null";
	   
	   @Select(getAllUsers)
	   @Results(value = {
	      @Result(property = "id", column = "ID"),
	      @Result(property = "firstName", column = "FIRST_NAME"),
	      @Result(property = "lastName", column = "LAST_NAME"),
	      @Result(property = "email", column = "EMAIL")
	   })
	   
	   List<User> getAllUsers();
	  
	   @Select(getUserById)
	   @Results(value = {
		  @Result(property = "id", column = "ID"),
	      @Result(property = "firstName", column = "FIRST_NAME"),
	      @Result(property = "lastName", column = "LAST_NAME"),
	      @Result(property = "email", column = "EMAIL")
	   })
	   
	   User getUserById(int id);

	   @Update(updateUser)
	   void updateUser(User student);

	   @Delete(deleteUserById)
	   void deleteUserById(int id);
	  
	   @Insert(insertUser)
	   @Options(useGeneratedKeys = true, keyProperty = "id")
	   void insertUser(User user);
	   
	   @Select(callByUserId)
	   @Results(value = {
	      @Result(property = "id", column = "ID"),
	      @Result(property = "firstName", column = "FIRST_NAME"),
	      @Result(property = "lastName", column = "LAST_NAME"),
	      @Result(property = "email", column = "EMAIL")
	   })
	   
	   User getCallByUserId(int id);
	   
}
