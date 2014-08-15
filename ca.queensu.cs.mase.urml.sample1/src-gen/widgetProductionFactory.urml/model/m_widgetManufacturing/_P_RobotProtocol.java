package model.m_widgetManufacturing;
import urml.runtime.*;
import java.util.*;
public class _P_RobotProtocol extends Protocol {
	public _P_RobotProtocol() {
		incomingSignals = Arrays.asList(new Signal[] {
			_s_widgetDelivered
		});
		outgoingSignals = Arrays.asList(new Signal[] {
			_s_deliverWidget, _s_shutDown
		});
	}
	public static Signal _s_widgetDelivered = new Signal();
	public static Signal _s_deliverWidget = new Signal();
	public static Signal _s_shutDown = new Signal();
}
