package com.wxapi.workflow;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

import org.springframework.data.jpa.repository.JpaRepository;

@Data
public abstract class WxWorkflowCtx<T> {

	protected WxWorkflowStep[] steps;
	
	protected List<T> stepContents = new ArrayList<>();
	
	private int index = 0;
	
	protected void close(){}
	
	public WxWorkflowStep getCurrentStep(){
		return steps[index];
	}
	
	public void cacheContent(T content){
		stepContents.add(content);
		
		if (isFinished()) {
			close();
		} else {
			index ++;
		}
	}
	
	public boolean isFinished() {
		return index == steps.length - 1;
	}
}
