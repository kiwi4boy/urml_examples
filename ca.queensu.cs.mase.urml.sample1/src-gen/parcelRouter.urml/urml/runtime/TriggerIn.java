package urml.runtime;
public class TriggerIn {
	public MessagePort port;
	public Signal signal;
	public TriggerIn(MessagePort p, Signal s) {
		port = p;
		signal = s;
	}
}	
