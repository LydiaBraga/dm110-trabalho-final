package db.inatel.dm110.poller.mdb;

import java.sql.Timestamp;
import java.time.Instant;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import br.inatel.dm110.poller.beans.PollerPingExecutor;
import br.inatel.dm110.poller.dao.PollerDAO;
import br.inatel.dm110.poller.entities.DevicePollerIdentifier;
import br.inatel.dm110.poller.entities.DeviceStatus;
import br.inatel.dm110.poller.message.PollerIpsMessage;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/pollerQueue"),
		@ActivationConfigProperty(propertyName = "maxSession", propertyValue = "10")
})
public class PollerMDB implements MessageListener {
	
	@EJB
	private PollerPingExecutor pingExecutor;
	
	@EJB 
	private PollerDAO pollerDAO;

	@Override
	public void onMessage(Message message) {
		try {
			PollerIpsMessage ipsMessage = message.getBody(PollerIpsMessage.class);
			
			ipsMessage.getIps().stream().forEach(ip -> {
				String status = pingExecutor.pingAddress(ip) ? "Ativo" : "Inativo";

				DevicePollerIdentifier deviceIdentifier = new DevicePollerIdentifier(ip, Timestamp.from(Instant.now()));
				DeviceStatus deviceStatus = new DeviceStatus(deviceIdentifier, status);

				pollerDAO.insertDeviceStatus(deviceStatus);
				System.out.println("Status salvo para ip: " + ip);
			});
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
