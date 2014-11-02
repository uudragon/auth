package com.uud.auth.ws;

import java.text.SimpleDateFormat;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;

@Provider
@Consumes({MediaType.APPLICATION_JSON, "text/json"})
@Produces({MediaType.APPLICATION_JSON, "text/json"})
public class MyJacksonJsonProvider extends JacksonJsonProvider {

	@Override
    public ObjectMapper locateMapper(Class<?> type, MediaType mediaType) {
        ObjectMapper om = super.locateMapper(type, mediaType);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        om.getSerializationConfig().setDateFormat(df);
        om.getDeserializationConfig().setDateFormat(df);
        return om;
    }
}
