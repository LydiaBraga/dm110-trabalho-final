package db.inatel.dm110.poller.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/productQueue"),
		@ActivationConfigProperty(propertyName = "maxSession", propertyValue = "5")
})
public class PollerMDB implements MessageListener {

	@Override
	public void onMessage(Message message) {
		// Dever� consumir mensagens e fazer a verifica��o do status do equipamento referente ao endere�o IP.
		// Salvar no banco o ip, status e hora
	}

}
