package com.uud.auth.impls.info;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class DomainConfigInfo {
	
	private static final Logger LOG = Logger.getLogger( DomainConfigInfo.class );
	
	private static DomainConfigInfo _instance;
	
	private Map<String,DomainConfig> map = new HashMap<String,DomainConfig>();
	
	public Map<String,DomainConfig> getConfig(){
		return map;
	}
	
	public static DomainConfigInfo getInstance() throws IOException{
		if( _instance == null ){
			synchronized( DomainConfigInfo.class ){
				if( _instance == null ){
					_instance = new DomainConfigInfo();
				}
			}
		}
		return _instance;
	}
	
	private DomainConfigInfo() throws IOException{
		init();
	}
	
	private void init() throws IOException{
		Document domainconfigDoc = getDomainConfigDoc();
		initLocationDomainMap( domainconfigDoc );
	}
	
	private void initLocationDomainMap(Document domainConfigDoc) {
		NodeList domains = (NodeList) domainConfigDoc
				.getElementsByTagName("domain");
		if ( domains != null ) {
			for (int i = 0; i < domains.getLength(); i++) {
				DomainConfig config = new DomainConfig();
				Element domainEle = (Element) domains.item(i);
				String domain = domainEle.getAttribute("name");
				String loaction = domainEle.getAttribute("loaction");
				config.setDomain( domain );
				config.setLoaction( Boolean.valueOf( loaction ) );
				if( !config.isLoaction() ){
					//TODO 非本地系统资源需要加载
				}
				map.put( domain, config );
			}
		}
	}
	
	private Document getDomainConfigDoc() throws IOException {
		Document domainConfigDoc = null;
		String domainconfig_path = this.getClass().getClassLoader().getResource("conf/domainconfig.xml").getPath();
		if (LOG.isInfoEnabled())
			LOG.info("create domainconfig instance  getDomainConfigDoc:" + domainconfig_path);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = dbf.newDocumentBuilder();
			domainConfigDoc = builder.parse(domainconfig_path);
			domainConfigDoc.normalize();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			throw new IOException(
					"domainconfig has a ParserConfigurationException...");
		} catch (SAXException e) {
			e.printStackTrace();
			throw new IOException("domainconfig has a SAXException...");
		}
		return domainConfigDoc;
	}

}

class DomainConfig{
	
	private boolean loaction; 
	
	private String domain;		
	
	public boolean isLoaction() {
		return loaction;
	}
	public void setLoaction(boolean loaction) {
		this.loaction = loaction;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
}