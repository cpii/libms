package com.hurontg.common.util;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class ConstraintViolationMessageMapperImpl implements
		ConstraintViolationMessageMapper {
	/**
	 * 
	 */
	@Resource(name = "libmsProperties")
	private Properties props;

	/**
	 * returns a message string from libms.properties
	 */
	@Override
	public String getMessage(String msg) {
		msg = msg.substring(msg.indexOf("'") + 1);

		int index = msg.indexOf("'");
		msg = msg.substring(0, index);

		String cv = props.getProperty(msg);
		if (cv == null) {
			cv = "Constraint Violation";
		}
		return cv;
	}

}
