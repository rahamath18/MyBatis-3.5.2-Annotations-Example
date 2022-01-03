package com.test.mybatis_annotations;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

public interface PersonAnnotationsMapper {

	   final String insertPerson = "INSERT INTO person (FIRST_NAME, LAST_NAME, EMAIL ) VALUES (#{firstName}, #{lastName}, #{email})";
	   final String getAllPersons = "SELECT * FROM person"; 
	   final String getPersonById = "SELECT * FROM person WHERE ID = #{id}";
	   final String updatePerson = "UPDATE person SET LAST_NAME = #{lastName}, EMAIL = #{email} WHERE ID = #{id}";
	   final String deletePersonById = "DELETE from person WHERE ID = #{id}";
	   final String callByPersonId = "call getPerson(#{id, jdbcType = INTEGER, mode = IN})";
	   final String getPersonsByEmail = "SELECT * FROM person WHERE EMAIL != null";
	   
	   @Select(getAllPersons)
	   @Results(value = {
	      @Result(property = "id", column = "ID"),
	      @Result(property = "firstName", column = "FIRST_NAME"),
	      @Result(property = "lastName", column = "LAST_NAME"),
	      @Result(property = "email", column = "EMAIL")
	   })
	   
	   List<Person> getAllPersons();
	  
	   @Select(getPersonById)
	   @Results(value = {
		  @Result(property = "id", column = "ID"),
	      @Result(property = "firstName", column = "FIRST_NAME"),
	      @Result(property = "lastName", column = "LAST_NAME"),
	      @Result(property = "email", column = "EMAIL")
	   })
	   
	   Person getPersonById(int id);

	   @Update(updatePerson)
	   void updatePerson(Person student);

	   @Delete(deletePersonById)
	   void deletePersonById(int id);
	  
	   @Insert(insertPerson)
	   @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn="id")
	   void insertPerson(Person person);
	   
	   @Select(callByPersonId)
	   @Results(value = {
	      @Result(property = "id", column = "ID"),
	      @Result(property = "firstName", column = "FIRST_NAME"),
	      @Result(property = "lastName", column = "LAST_NAME"),
	      @Result(property = "email", column = "EMAIL")
	   })
	   
	   Person getCallByPersonId(int id);
	   
}
