package com.lms.api.demo.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lms.api.demo.entity.Tbl_lms_user;

@Repository
public interface UserRepository extends JpaRepository<Tbl_lms_user, String> {
	
	
//	@Query("select months_of_exp from tbl_lms_userskill_map where skill_id NOT IN(:skillId )")
//	public List<Integer> getMonthsOfExp(List<Integer> skillId);
//	

}
