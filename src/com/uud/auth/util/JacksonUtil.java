package com.uud.auth.util;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 使用Jackson生成json格式字符串
 *
 * @author archie2010 since 2011-4-26下午05:59:46
 */
public class JacksonUtil {

	private static ObjectMapper objectMapper = null;

	public static String writeEntity2Json( Object object ) throws Exception {
		
		return objectMapper.writeValueAsString( object );
	}
}