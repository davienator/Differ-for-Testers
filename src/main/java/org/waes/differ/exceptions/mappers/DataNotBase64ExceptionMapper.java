package org.waes.differ.exceptions.mappers;

import org.waes.differ.exceptions.DataNotBase64Exception;
import org.waes.differ.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DataNotBase64ExceptionMapper implements ExceptionMapper<DataNotBase64Exception>{

	@Override
	public Response toResponse(DataNotBase64Exception exception) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 415);
		return Response.status(Status.UNSUPPORTED_MEDIA_TYPE)
				.entity(errorMessage)
				.build();
	}
}
