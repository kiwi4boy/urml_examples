package model.m_ConsumerProducer;
import urml.runtime.*;
import java.util.*;
public class _P_BufferProtocol extends Protocol {
	public _P_BufferProtocol() {
		incomingSignals = Arrays.asList(new Signal[] {
			_s_get
		});
		outgoingSignals = Arrays.asList(new Signal[] {
			_s_put
		});
	}
	public static Signal _s_get = new Signal();
	public static Signal _s_put = new Signal();
}
