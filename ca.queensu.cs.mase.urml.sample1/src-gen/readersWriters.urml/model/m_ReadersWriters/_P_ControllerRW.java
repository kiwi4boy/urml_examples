package model.m_ReadersWriters;
import urml.runtime.*;
import java.util.*;
public class _P_ControllerRW extends Protocol {
	public _P_ControllerRW() {
		incomingSignals = Arrays.asList(new Signal[] {
			_s_acquireRead, _s_releaseRead, _s_acquireWrite, _s_releaseWrite
		});
		outgoingSignals = Arrays.asList(new Signal[] {
			_s_acknowledge
		});
	}
	public static Signal _s_acquireRead = new Signal();
	public static Signal _s_releaseRead = new Signal();
	public static Signal _s_acquireWrite = new Signal();
	public static Signal _s_releaseWrite = new Signal();
	public static Signal _s_acknowledge = new Signal();
}
