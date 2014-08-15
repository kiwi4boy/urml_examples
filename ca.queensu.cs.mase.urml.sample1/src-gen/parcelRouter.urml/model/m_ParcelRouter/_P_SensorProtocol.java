package model.m_ParcelRouter;
import urml.runtime.*;
import java.util.*;
public class _P_SensorProtocol extends Protocol {
	public _P_SensorProtocol() {
		incomingSignals = Arrays.asList(new Signal[] {
		});
		outgoingSignals = Arrays.asList(new Signal[] {
			_s_sendDirection
		});
	}
	public static Signal _s_sendDirection = new Signal();
}
