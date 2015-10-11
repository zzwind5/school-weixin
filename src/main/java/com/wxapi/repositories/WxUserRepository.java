package com.wxapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wxapi.model.WxUser;

public interface WxUserRepository extends JpaRepository<WxUser, Long> {

}
