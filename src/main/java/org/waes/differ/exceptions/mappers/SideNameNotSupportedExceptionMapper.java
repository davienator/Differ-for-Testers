package org.waes.differ.exceptions.mappers;

import org.waes.differ.exceptions.SideNameNotSupportedException;
import org.waes.differ.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SideNameNotSupportedExceptionMapper implements ExceptionMapper<SideNameNotSupportedException>{

	@Override
	public Response toResponse(SideNameNotSupportedException exception) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 501);
		return Response.status(Status.NOT_IMPLEMENTED)
				.entity(errorMessage)
				.build();
	}
}
