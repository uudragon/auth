package com.uud.auth.ws;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.uud.auth.entity.User;
import com.uud.commons.office.ExcelUtils;

@Path("export")
public class ExportTest {

	@GET
	public String export( @Context HttpServletRequest request,
						@Context HttpServletResponse response ){
		response.setContentType( MediaType.APPLICATION_OCTET_STREAM );
		response.setHeader("Content-disposition", "attachment; filename=test.xlsx");
		try {
			OutputStream out = response.getOutputStream();
			List<Object> list = new ArrayList<Object>();
			User user = new User();
			user.setAccount("test1");
			user.setEmail("123");
			user.setId( 1l );
			list.add( user );

			User user2 = new User();
			user2.setAccount("test2");
			user2.setEmail("123");
			user2.setId( 2l );
			list.add( user2 );
			
			String[] titles = {"account","id","email"};
			try {
				ExcelUtils.writeObject(out, "test", list, titles);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
