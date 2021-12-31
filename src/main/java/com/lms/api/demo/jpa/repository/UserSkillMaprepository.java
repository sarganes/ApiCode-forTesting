package com.lms.api.demo.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lms.api.demo.entity.Tbl_lms_user;
import com.lms.api.demo.entity.Tbl_lms_userskill_map;

@Repository
public interface UserSkillMaprepository extends JpaRepository<Tbl_lms_userskill_map, String> {
	@Query(nativeQuery = true,value = "select  * from tbl_lms_userskill_map where skill_id = ?1")
	public List<Tbl_lms_userskill_map> getAllUsers(int id);
//	@Query("select user_id from tbl_lms_userskill_map")
//	public List<String> getAllUsers();
//
//	@Query(nativeQuery = true, value = "select  user_id, user_first_name, user_last_name from tbl_lms_user where user_id in :list")
//	public List<String> getUserData(@Param("list") List<String> list);
}
