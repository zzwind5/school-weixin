package com.data.repositories.wxapi;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.model.wxapi.WxOwner;

public interface WxOwnerRepository extends JpaRepository<WxOwner, Long> {

}
