package com.uud.auth.service.impl;

import com.uud.auth.util.ConfigHelper;

public class ConfigHelperTest {

	public static void main(String[] args) {
		System.out.println( ConfigHelper.getInstance().getLong( "token.timeout" ) );

	}

}
