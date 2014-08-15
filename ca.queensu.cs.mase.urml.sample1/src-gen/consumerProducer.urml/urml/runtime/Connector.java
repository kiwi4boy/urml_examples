package urml.runtime;
public class Connector {
	public Capsule cap1;
	public Capsule cap2;
	public Port port1;
	public Port port2;
	public Connector(Capsule c1, Port p1, Capsule c2, Port p2) {
		cap1 = c1; cap2 = c2; port1 = p1; port2 = p2;
	}
}
