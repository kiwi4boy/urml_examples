package urml.runtime;
public class Port {
	public String name;
	public Protocol prot;
	public Port(String name, Protocol protocol) {
		this.name = name;
		this.prot = protocol;
	}
}
