package urml.runtime;
import java.util.*;
import java.util.function.*;
public class Transition {
	public String name;
	public Supplier<Boolean> guard;
	public Consumer<List<? extends CommonObj>> action;
	public List<TriggerIn> triggerIn;
	public TimerPort timerPort;
	public Transition(String name, Supplier<Boolean> guard, 
			Consumer<List<? extends CommonObj>> action, 
			List<TriggerIn> triggerIn, TimerPort timerPort) {
		this.name = name;
		this.guard = guard;
		this.action = action;
		this.triggerIn = triggerIn;
		this.timerPort = timerPort;
	}
}	
