package model.m_Blinky;
import urml.runtime.*;
import java.util.*;
public class _P_ControllerProtocol extends Protocol {
	public _P_ControllerProtocol() {
		incomingSignals = Arrays.asList(new Signal[] {
			_s_start, _s_stop
		});
		outgoingSignals = Arrays.asList(new Signal[] {
		});
	}
	public static Signal _s_start = new Signal();
	public static Signal _s_stop = new Signal();
}
