package com.wxapi.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wxapi.model.WxSchoolMessage;

public interface WxSchoolMessageRepository extends JpaRepository<WxSchoolMessage, Long> {

	@Query("SELECT MAX(id) FROM WxSchoolMessage WHERE createdAt >= ?1 GROUP BY wxMenuKey")
	public List<Long> findMaxIdByOperation(Timestamp createTime);
	
	public List<WxSchoolMessage> findByIdIn(List<Long> ids);
}
