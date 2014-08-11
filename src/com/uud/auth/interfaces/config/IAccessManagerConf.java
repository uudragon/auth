package com.uud.auth.interfaces.config;

import java.io.IOException;
import java.util.Properties;

public interface IAccessManagerConf {
	/**
	 * java��������-D�������֣���װ�ļ�Ŀ¼��
	 */
	static final String ACCESS_SYSTEM_ATTRIBUTE = "com.uud.install_path";
	
	/**
	 * ϵͳ�����ļ������֡�
	 */
	static final String CONFIGURATION_NAME = "access.properties";
	
	/**
	 * ��Դӳ��ʵ���ࡣ
	 */
	static final String RESOURCE_MAPPING_CLASS_IMPL = "resource_mapping_class_impl";
	
	/**
	 * �����û���ʵ�����������ԡ�
	 */
	static final String USER_LOAD_CLASS_IMPL = "user_load_class_impl";
	
	/**
	 * ������Դʱ���������ݹ���ʵ���ࡣ
	 */
	static final String REVALUATION_CLASS_IMPL = "revaluation_class_impl";
	
	/**
	 * �ͻ���������domain��
	 */
	static final String DOMAIN_NAME = "domain_name";
	
	/**
	 * policy����ʵ��������
	 * 
	 */
	static final String POLICY_LOAD_CLASS_IMPL = "policy_load_class_impl";
	
	/**
	 * ���d�Ñ�������������ļ������֡���·���ڡ�ACCESS_SYSTEM_ATTRIBUTE����
	 */
	static final String USER_LOAD_PROPERTY_FILE = "user_load_property_file";	
	
	/**
	 * user��Ϣ����ʵ�������ã����ڶ�ȡ�û�Ȩ����Ϣ
	 * 
	 */
	static final String USER_ACCESSREADER_CLASS_IMPL = "user_accessreader_class_impl";

	/**
	 * plolica��Ϣ����ʵ�������ã����ڶ�ȡ�û�Ȩ����Ϣ
	 * 
	 */
	static final String POLICY_ACCESSREADER_CLASS_IMPL = "policy_accrssreader_class_impl";
		
	/**
	 * ACTION��Ϣ����ʵ�������ã����ڶ�ȡ�û�Ȩ����Ϣ
	 * 
	 */
	static final String ACTION_ACCESSREADER_CLASS_IMPL = "action_accrssreader_class_impl";
		
	/**
	 * ��ȡ�����ļ���·����
	 * 
	 * @return
	 */
	public String getInstallRoot();

	/**
	 * ��ȡ�������ԡ�
	 * 
	 * @param prop
	 * @return
	 * @throws IOException
	 */
	public String getProperty(String prop) throws IOException;

	/**
	 * ��ȡ�������Լ���
	 * 
	 * @return
	 * @throws IOException
	 */
	public Properties getProperties() throws IOException;
}
