package org.waes.differ.exceptions.mappers;

import org.waes.differ.model.ErrorMessage;

import javax.json.stream.JsonParsingException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonParsingExceptionMapper implements ExceptionMapper<JsonParsingException>{

	@Override
	public Response toResponse(JsonParsingException exception) {
		ErrorMessage errorMessage = new ErrorMessage("Value in request body must be in JSON format.", 415);
		return Response.status(Status.UNSUPPORTED_MEDIA_TYPE)
				.entity(errorMessage)
				.build();
	}


}
