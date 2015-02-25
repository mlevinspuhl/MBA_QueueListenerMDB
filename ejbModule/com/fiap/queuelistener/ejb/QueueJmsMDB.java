package com.fiap.queuelistener.ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: QueueJmsMDB
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/queue/FilaFIAP") })
public class QueueJmsMDB implements MessageListener {
	/**
	 * Default constructor.
	 */
	public QueueJmsMDB() {
		// TODO Auto-generated constructor stub
	}



	/**
	 * @see MessageListener#onMessage(Message)
	 */
	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			try {
				TextMessage txtMessage = (TextMessage) message;
				System.out.println("*** A mensagem recebida: "
						+ txtMessage.getText());
			} catch (JMSException e) {
				/*
				 * Aqui a exceção deve ser tratada. No mínimo deve-se logar a
				 * causa da falha.
				 */
				throw new EJBException("Falha ao processar a mensagem...", e);
			}
		} else {
			throw new EJBException("*** Erro: Não foi forncecida "
					+ "mensagem em formato TextMessage !!");
		}
	}
}