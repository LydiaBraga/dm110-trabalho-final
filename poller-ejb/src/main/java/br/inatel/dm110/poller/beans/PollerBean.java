package br.inatel.dm110.poller.beans;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.inatel.dm110.poller.dao.PollerDAO;
import br.inatel.dm110.poller.interfaces.PollerLocal;
import br.inatel.dm110.poller.interfaces.PollerRemote;

@Stateless
@Remote(PollerRemote.class)
@Local(PollerLocal.class)
public class PollerBean implements PollerRemote, PollerLocal {

	@EJB
	private PollerMessageSender pollerMessageSender;
	
	@EJB
	private PollerDAO pollerDAO;
	
	@Override
	public void startPoller(String ip, int mask) {
		// Pegar ips e mandar de 10 em 10 para o sender		
	}

	@Override
	public String getDeviceStatus(String deviceIp) {
		return pollerDAO.getDeviceStatus(deviceIp);
	}

}
