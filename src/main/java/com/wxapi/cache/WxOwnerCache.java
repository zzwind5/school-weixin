package com.wxapi.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wxapi.model.WxOwner;
import com.wxapi.repositories.WxOwnerRepository;

@Component
@NoArgsConstructor
public class WxOwnerCache {

	@Autowired
	private WxOwnerRepository wxOwnerRep;
	
	@Getter
	private Map<String, WxOwner> nameCacheMap = new HashMap<>();
	
	@Getter
	private Map<Long, WxOwner> idCacheMap = new HashMap<>();
	
	@PostConstruct
	public void init(){
		nameCacheMap.clear();
		idCacheMap.clear();
		List<WxOwner> allOwners = wxOwnerRep.findAll();
		for (WxOwner owner : allOwners) {
			nameCacheMap.put(owner.getOwnerName(), owner);
			idCacheMap.put(owner.getId(), owner);
		}
	}
	
	public WxOwner getWxOwner(String ownerName) {
		return nameCacheMap.get(ownerName);
	}
	
	public WxOwner getWxOwner(Long ownerId) {
		return idCacheMap.get(ownerId);
	}
}
