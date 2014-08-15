package urml.runtime;
import java.util.*;
public class Message {
	public Port port;
	public Signal signal;
	public List<CommonObj> parameters;
	public Message(Port p, Signal s, List<CommonObj> param) {
		port = p;
		signal = s;
		parameters = param;
	}
}
