package model.m_handshake;
import urml.runtime.*;
import java.util.*;
public class _P_HandshakeProtocol extends Protocol {
	public _P_HandshakeProtocol() {
		incomingSignals = Arrays.asList(new Signal[] {
		});
		outgoingSignals = Arrays.asList(new Signal[] {
			_s_shake
		});
	}
	public static Signal _s_shake = new Signal();
}
