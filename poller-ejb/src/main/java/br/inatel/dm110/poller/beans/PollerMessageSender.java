package br.inatel.dm110.poller.beans;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import br.inatel.dm110.poller.message.PollerIpsMessage;

@Stateless
public class PollerMessageSender {

	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;
	
	@Resource(lookup = "java:/jms/queue/productQueue")
	private Queue queue;
	
	public void sendMessage(List<String> ips) {
		try (
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession();
			MessageProducer producer = session.createProducer(queue);
		) {
			PollerIpsMessage pollerIpsMessage = new PollerIpsMessage(ips);
			ObjectMessage productMessage = session.createObjectMessage(pollerIpsMessage);		
			producer.send(productMessage);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}
	
}
