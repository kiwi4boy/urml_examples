package model.m_ParcelRouter;
import urml.runtime.*;
import java.util.*;
public class _P_LevelNumberProtocol extends Protocol {
	public _P_LevelNumberProtocol() {
		incomingSignals = Arrays.asList(new Signal[] {
		});
		outgoingSignals = Arrays.asList(new Signal[] {
			_s_sendLevelNumber
		});
	}
	public static Signal _s_sendLevelNumber = new Signal();
}
