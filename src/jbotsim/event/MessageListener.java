/*
 * This file is part of JBotSim.
 * 
 *    JBotSim is free software: you can redistribute it and/or modify it
 *    under the terms of the GNU Lesser General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *  
 *    Authors:
 *    Arnaud Casteigts		<casteig@site.uottawa.ca>
 */
package jbotsim.event;

import jbotsim.Message;

public interface MessageListener {
	/**
	 * Notifies the underlying MessageListener that a message has arrived.
	 * @param msg The incoming message.
	 */
	public void onMessage(Message msg);
}
