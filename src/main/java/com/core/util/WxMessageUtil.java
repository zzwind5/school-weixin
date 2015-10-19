package com.core.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class WxMessageUtil {
	
    private static MessageSource messageBundle;
	
	@Autowired
	private void setMessageSource(MessageSource messageBundle) {
		WxMessageUtil.messageBundle = messageBundle;
	}

	public static String getMessage(String messageKey, Object... params) {
		return messageBundle.getMessage(messageKey, params, LocaleContextHolder.getLocale());
	}
}
