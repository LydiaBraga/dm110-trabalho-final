package br.inatel.dm110.poller.impl;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;

import br.inatel.dm110.poller.api.PollerService;
import br.inatel.dm110.poller.exception.PollerException;
import br.inatel.dm110.poller.interfaces.PollerRemote;

@RequestScoped
public class PollerServiceImpl implements PollerService {
	
	@EJB(lookup = "java:app/poller-ejb-0.1-SNAPSHOT/PollerBean!br.inatel.dm110.poller.interfaces.PollerRemote")
	private PollerRemote poller;

	@Override
	public String startPoller(String ip, int mask) {
		try {
			poller.startPoller(ip, mask);
			
			return "Teste dos ips iniciado!";
		} catch (PollerException e) {
			throw new InternalServerErrorException(Response.serverError()
					.entity("Erro ao testar status dos ips: " + e.getMessage()).build());
		}
	}

	@Override
	public String getDeviceStatus(String ip) {
		return poller.getDeviceStatus(ip);
	}

}
