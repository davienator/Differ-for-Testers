package org.waes.differ.exceptions.mappers;

import org.waes.differ.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.NoSuchElementException;

@Provider
public class NoSuchElementExceptionMapper implements ExceptionMapper<NoSuchElementException>{

	@Override
	public Response toResponse(NoSuchElementException exception) {
		ErrorMessage errorMessage = new ErrorMessage("Value in request body cannot be empty.", 400);
		return Response.status(Status.BAD_REQUEST)
				.entity(errorMessage)
				.build();
	}

}
