package com.wxapi.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.wxapi.model.WxSchoolMessage;

public interface WxSchoolMessageRepository extends JpaRepository<WxSchoolMessage, Long> {

	@Query("SELECT MAX(id) FROM WxSchoolMessage WHERE createdAt >= ?1 GROUP BY wxMenuKey")
	public List<Long> findMaxIdByOperation(Timestamp createTime);
	
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE WxSchoolMessage o SET o.externalUrl=?1 WHERE o.id=?2")
	public void updateExternalUrlById(String exUrl, Long id);
	
	public List<WxSchoolMessage> findByIdIn(List<Long> ids);
}
