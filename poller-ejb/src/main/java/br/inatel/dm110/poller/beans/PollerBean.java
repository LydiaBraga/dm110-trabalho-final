package br.inatel.dm110.poller.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.google.common.collect.Lists;

import br.inatel.dm110.poller.dao.PollerDAO;
import br.inatel.dm110.poller.exception.PollerException;
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
	
	@EJB
	private PollerAddressesCalculator addressesCalculator;
	
	@Override
	public void startPoller(String ip, int mask) throws PollerException {
		List<String> addresses = addressesCalculator.calculate(ip, mask);
		
		for(List<String> addressesList : Lists.partition(addresses, 10)) {
			pollerMessageSender.sendMessage(addressesList);
		}
	}

	@Override
	public String getDeviceStatus(String deviceIp) {
		String deviceStatus = pollerDAO.getDeviceStatus(deviceIp);
		
		if (deviceStatus == null) {
			deviceStatus = "Status não encontrado para este ip!";
		}
		
		return deviceStatus;
	}

}
