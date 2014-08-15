package urml.runtime;
public class Connector {
	public Capsule cap1;
	public Capsule cap2;
	public MessagePort port1;
	public MessagePort port2;
	public Connector(Capsule c1, MessagePort p1, Capsule c2, MessagePort p2) {
		cap1 = c1; cap2 = c2; port1 = p1; port2 = p2;
	}
}
