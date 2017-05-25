package com.skillsimprover.usersdb.utils.http;

import javax.servlet.http.HttpServletRequest;

import com.skillsimprover.usersdb.utils.StaticClassInstantiationError;
import com.skillsimprover.usersdb.utils.StringUtils;

public final class HttpUtils {

	private HttpUtils() {
		throw new StaticClassInstantiationError();
	}

	public static Integer getIntParam(HttpServletRequest request, String paramName) {
		if (StringUtils.isBlank(paramName)) {
			throw new EmptyParamNameException();
		}

		String valueStr = request.getParameter(paramName);

		try {
			Integer value = new Integer(valueStr);
			return value;
		} catch(NumberFormatException e) {
			return null;
		}
	}
}
