package br.inatel.dm110.poller.exception;

import java.io.Serializable;

public class PollerException extends Exception implements Serializable {
	
	private static final long serialVersionUID = -6002664775199041430L;

	public PollerException(String message) {
        super(message);
    }
	
}
