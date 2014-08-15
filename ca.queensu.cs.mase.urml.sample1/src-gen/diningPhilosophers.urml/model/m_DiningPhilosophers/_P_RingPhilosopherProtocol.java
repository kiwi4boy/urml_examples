package model.m_DiningPhilosophers;
import urml.runtime.*;
import java.util.*;
public class _P_RingPhilosopherProtocol extends Protocol {
	public _P_RingPhilosopherProtocol() {
		incomingSignals = Arrays.asList(new Signal[] {
		});
		outgoingSignals = Arrays.asList(new Signal[] {
			_s_sign
		});
	}
	public static Signal _s_sign = new Signal();
}
