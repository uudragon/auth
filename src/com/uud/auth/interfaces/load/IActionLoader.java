package com.uud.auth.interfaces.load;

import java.util.ArrayList;
import java.util.HashMap;


public interface IActionLoader {
	/**
	 * һ��permissiontype���Զ�Ӧ���action��

	 * 
	 * 
	 * @return
	 * @throws LoaderException
	 */
	public HashMap<String, ArrayList<String>> getAllAction()
			throws LoaderException;

	public String getDomain();

	public void setDomain(String domain);
}
