package model.m_DiningPhilosophers;
import urml.runtime.*;
import java.util.*;
public class _P_PhilosopherForkProtocol extends Protocol {
	public _P_PhilosopherForkProtocol() {
		incomingSignals = Arrays.asList(new Signal[] {
			_s_ack
		});
		outgoingSignals = Arrays.asList(new Signal[] {
			_s_up, _s_down
		});
	}
	public static Signal _s_ack = new Signal();
	public static Signal _s_up = new Signal();
	public static Signal _s_down = new Signal();
}
