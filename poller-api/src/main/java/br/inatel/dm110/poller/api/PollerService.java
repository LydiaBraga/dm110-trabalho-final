package br.inatel.dm110.poller.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/poller")
public interface PollerService {

	@GET
	@Path("/start/{ip}/{mask}")
	void startPoller(@PathParam("ip") String ip, @PathParam("mask") int mask);
	
	@GET
	@Path("/status/{ip}")
	@Produces(MediaType.TEXT_PLAIN)
	String getDeviceStatus(@PathParam("ip") String ip);
	
}
