package com.kkk.neuron;

public class NeuronException extends RuntimeException {

	private static final long serialVersionUID = -2380632201806214807L;

	public NeuronException(String message) {
		super(message);
	}
	
	public NeuronException(String message, Throwable cause) {
		super(message, cause);
	}

}
