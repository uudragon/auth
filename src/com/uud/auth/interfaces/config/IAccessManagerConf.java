package com.uud.auth.interfaces.config;

import java.io.IOException;
import java.util.Properties;

public interface IAccessManagerConf {
	/**
	 * java启动命令-D参数名字（安装文件目录）
	 */
	static final String ACCESS_SYSTEM_ATTRIBUTE = "com.uud.install_path";
	
	/**
	 * 系统配置文件的名字。
	 */
	static final String CONFIGURATION_NAME = "access.properties";
	
	/**
	 * 资源映射实现类。
	 */
	static final String RESOURCE_MAPPING_CLASS_IMPL = "resource_mapping_class_impl";
	
	/**
	 * 加载用户的实现类配置属性。
	 */
	static final String USER_LOAD_CLASS_IMPL = "user_load_class_impl";
	
	/**
	 * 访问资源时，报文内容过滤实现类。
	 */
	static final String REVALUATION_CLASS_IMPL = "revaluation_class_impl";
	
	/**
	 * 客户端所属的domain。
	 */
	static final String DOMAIN_NAME = "domain_name";
	
	/**
	 * policy加载实现类配置
	 * 
	 */
	static final String POLICY_LOAD_CLASS_IMPL = "policy_load_class_impl";
	
	/**
	 * 加載用戶所需屬性配置文件的名字。（路径在”ACCESS_SYSTEM_ATTRIBUTE“）
	 */
	static final String USER_LOAD_PROPERTY_FILE = "user_load_property_file";	
	
	/**
	 * user信息加载实现类配置，用于读取用户权限信息
	 * 
	 */
	static final String USER_ACCESSREADER_CLASS_IMPL = "user_accessreader_class_impl";

	/**
	 * plolica信息加载实现类配置，用于读取用户权限信息
	 * 
	 */
	static final String POLICY_ACCESSREADER_CLASS_IMPL = "policy_accrssreader_class_impl";
		
	/**
	 * ACTION信息加载实现类配置，用于读取用户权限信息
	 * 
	 */
	static final String ACTION_ACCESSREADER_CLASS_IMPL = "action_accrssreader_class_impl";
		
	/**
	 * 获取配置文件的路径。
	 * 
	 * @return
	 */
	public String getInstallRoot();

	/**
	 * 获取配置属性。
	 * 
	 * @param prop
	 * @return
	 * @throws IOException
	 */
	public String getProperty(String prop) throws IOException;

	/**
	 * 获取配置属性集。
	 * 
	 * @return
	 * @throws IOException
	 */
	public Properties getProperties() throws IOException;
}
