package com.uud.auth.ws;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

public class APIApplication extends ResourceConfig {
    public APIApplication() {
       
        //注册数据转换器
        register(MyJacksonJsonProvider.class);

        // Logging.
        register(LoggingFilter.class);
        
        //加载Resource
        register(UserRestService.class);
        register(AuthorService.class);
        register(ExportTest.class);
        
        register(NoteRestService.class);
        register(MessageRestService.class);
        register(OrderRestService.class);
        register(WorkFormRestService.class);
        register(CustomerRestService.class);
        register(CommunicationRestService.class);
    }
}
