package com.wxapi.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

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
	
	private Map<String, WxOwner> cacheMap = new HashMap<>();
	
	@PostConstruct
	public void init(){
		cacheMap.clear();
		List<WxOwner> allOwners = wxOwnerRep.findAll();
		for (WxOwner owner : allOwners) {
			cacheMap.put(owner.getOwnerName(), owner);
		}
	}
	
	public WxOwner getWxOwner(String ownerName) {
		return cacheMap.get(ownerName);
	}
}
