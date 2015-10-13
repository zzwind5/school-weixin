package com.wxapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wxapi.model.WxOwner;

public interface WxOwnerRepository extends JpaRepository<WxOwner, Long> {

	WxOwner findOneByOwnerName(String ownerName);
}
