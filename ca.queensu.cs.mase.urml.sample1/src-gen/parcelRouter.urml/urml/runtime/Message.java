package urml.runtime;
import java.util.*;
public class Message {
	public MessagePort port;
	public Signal signal;
	public List<CommonObj> parameters;
	public Message(MessagePort msgPort, Signal signal, 
			List<CommonObj> param) {
		this.port = msgPort;
		this.signal = signal;
		this.parameters = param;
	}
}
