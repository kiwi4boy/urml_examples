package urml.runtime;
public class MessagePort {
	public String name;
	public Protocol prot;
	public MessagePort(String name, Protocol protocol) {
		this.name = name;
		this.prot = protocol;
	}
}
