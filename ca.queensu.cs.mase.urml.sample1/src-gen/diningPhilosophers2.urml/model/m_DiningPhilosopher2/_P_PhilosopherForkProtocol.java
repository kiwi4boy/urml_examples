package model.m_DiningPhilosopher2;
import urml.runtime.*;
import java.util.*;
public class _P_PhilosopherForkProtocol extends Protocol {
	public _P_PhilosopherForkProtocol() {
		incomingSignals = Arrays.asList(new Signal[] {
			_s_avail, _s_notAvail
		});
		outgoingSignals = Arrays.asList(new Signal[] {
			_s_pick, _s_checkAvail, _s_drop
		});
	}
	public static Signal _s_avail = new Signal();
	public static Signal _s_notAvail = new Signal();
	public static Signal _s_pick = new Signal();
	public static Signal _s_checkAvail = new Signal();
	public static Signal _s_drop = new Signal();
}
