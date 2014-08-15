package urml.runtime;
public class State {
	public String name;
	public Runnable entry;
	public Runnable exit;
	public State(String name, Runnable entry, Runnable exit) {
		this.name = name;
		this.entry = entry;
		this.exit = exit;
	}
	
}
