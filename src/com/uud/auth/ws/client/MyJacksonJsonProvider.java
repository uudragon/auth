package com.uud.auth.ws.client;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class MyJacksonJsonProvider extends JacksonJsonProvider{
	
	private static final String DF = "yyyy-MM-dd";
	
	@Override
	public boolean isWriteable(Class arg0, Type arg1, Annotation[] arg2,
	  MediaType arg3) {
	 return super.isWriteable(arg0, arg1, arg2,
	   arg3);
	}
	
	@Override
	public void writeTo(Object target, Class arg1, Type arg2, Annotation[] arg3,
	  MediaType arg4, MultivaluedMap arg5, OutputStream outputStream)
	  throws IOException, WebApplicationException {
	  SimpleDateFormat sdf=new SimpleDateFormat(DF);
	 ObjectMapper om = new ObjectMapper();
	 om.getDeserializationConfig().setDateFormat(sdf);
	 om.getSerializationConfig().setDateFormat(sdf);
	 try {
	  om.writeValue(outputStream, target);
	 } catch (JsonGenerationException e) {
	  throw e;
	 } catch (JsonMappingException e) {
	  throw e;
	 } catch (IOException e) {
	  throw e;
	 }
	}
}
