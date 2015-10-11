package com.data.repositories.wxapi;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.model.wxapi.WxUser;

public interface WxUserRepository extends JpaRepository<WxUser, Long> {

}
