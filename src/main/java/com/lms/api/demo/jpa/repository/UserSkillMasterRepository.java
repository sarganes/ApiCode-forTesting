package com.lms.api.demo.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lms.api.demo.entity.Tbl_lms_skill_master;
import com.lms.api.demo.entity.Tbl_lms_userskill_map;
@Repository
public interface UserSkillMasterRepository extends JpaRepository<Tbl_lms_skill_master, Integer> {
	
}
