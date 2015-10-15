package com.wxapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wxapi.model.WxSchoolMessage;

public interface WxSchoolMessageRepository extends JpaRepository<WxSchoolMessage, Long> {

}
