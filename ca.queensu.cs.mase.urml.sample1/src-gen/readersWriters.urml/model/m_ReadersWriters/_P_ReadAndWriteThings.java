package model.m_ReadersWriters;
import urml.runtime.*;
import java.util.*;
public class _P_ReadAndWriteThings extends Protocol {
	public _P_ReadAndWriteThings() {
		incomingSignals = Arrays.asList(new Signal[] {
		});
		outgoingSignals = Arrays.asList(new Signal[] {
			_s_read, _s_write
		});
	}
	public static Signal _s_read = new Signal();
	public static Signal _s_write = new Signal();
}
