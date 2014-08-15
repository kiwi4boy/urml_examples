package model.m_widgetManufacturing;
import urml.runtime.*;
import java.util.*;
public class _P_WorkstationProtocol extends Protocol {
	public _P_WorkstationProtocol() {
		incomingSignals = Arrays.asList(new Signal[] {
			_s_widgetProduced
		});
		outgoingSignals = Arrays.asList(new Signal[] {
			_s_produceWidget, _s_shutDown
		});
	}
	public static Signal _s_widgetProduced = new Signal();
	public static Signal _s_produceWidget = new Signal();
	public static Signal _s_shutDown = new Signal();
}
