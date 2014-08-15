package model.m_ParcelRouter;
import urml.runtime.*;
import java.util.*;
public class _P_ParcelPassage extends Protocol {
	public _P_ParcelPassage() {
		incomingSignals = Arrays.asList(new Signal[] {
		});
		outgoingSignals = Arrays.asList(new Signal[] {
			_s_sendParcel
		});
	}
	public static Signal _s_sendParcel = new Signal();
}
