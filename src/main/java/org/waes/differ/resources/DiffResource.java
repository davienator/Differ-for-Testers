package org.waes.differ.resources;

import org.waes.differ.model.Comparison;
import org.waes.differ.model.Sides;
import org.waes.differ.service.DifferService;
import org.waes.differ.service.SidesService;

import javax.json.JsonString;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1/diff/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DiffResource {

	private SidesService sidesService = new SidesService();
	private DifferService differService = new DifferService();
	
	@GET
	public List<Sides> getAllInstances() {
		return sidesService.getAllSides();
	}
		
	@POST
	@Path("/{id}/{side}")
	public Sides setSideValue(@PathParam("id") long id,
							  @PathParam("side") String side,
							  JsonString jsonValue) {
		String value = jsonValue.getString();
		return sidesService.addSideValue(id, side, value);	//This does not provide status or headers
	}

	@GET
	@Path("/{id}")
	public Comparison getDiffById(@PathParam("id") long id) {
		return differService.validateDiff(sidesService.getSidesById(id));
	}
}
