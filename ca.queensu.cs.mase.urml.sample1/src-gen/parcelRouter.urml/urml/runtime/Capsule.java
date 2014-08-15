package urml.runtime;
import java.util.*;
import java.util.concurrent.*;
import java.time.*;

/**
 * Base class for capsules.
 *
 * This is part of the runtime for Urml codegen.
 */
public abstract class Capsule implements Runnable {
	// global variable to determine when to exit
	private static volatile boolean EXIT = false;
	public Capsule parent;
	public List<MessagePort> internalports;
	public List<MessagePort> externalports;
	public List<Capsule> capsules;
	public List<Connector> connectors;
	
	/**
	 * A message queue to keep track of messages sent by
	 * other action code.  Will be checked against with
	 * candidate transition to see if the transition's 
	 * trigger message and the head of the queue match.
	 */
	public Queue<Message> queue = new LinkedBlockingQueue<>();
	
	/**
	 * A map that stores the timers that is currently
	 * available.  Will be checked against with 
	 * candidate transition to see if the specified
	 * timer in the transition's trigger has timed out.
	 */
	public Map<TimerPort, Instant> instants = new HashMap<>();
	
	/**
	 * Lock used for synchronized blocks
	 */
	protected static Object lock = new Object();
	
	/**
	 * A dummy state
	 */
	public State currentState = new State("_NO_STATE",
			() -> { throw new IllegalArgumentException(); },
			() -> { throw new IllegalArgumentException(); });
	
	/**
	 * Starts the capsule and all its children capsules
	 */
	public void run() {
		for (Capsule c: capsules) {
			new Thread(c).start();
		}
		launch();
	}
	
	/**
	 * The main loop of the capsule.  
	 */
	private void launch() {
		// start the initial transition first
		startInit();
		while (!EXIT) {
			// find all possible transitions that is outgoing 
			// from the current state
			List<? extends Transition> generated = findPossibleTrans();
			
			// filter out with enabled transitions remaining in the list
			List<? extends Transition> found = findNextTransitions(generated);
			
			// if no enabled transition found, redo the loop
			if (found == null || found.size() == 0) continue;
			
			// if more than one transition is found, choose the first transition
			Transition t = found.get(0);
			
			// get the parameter
			
			List<? extends CommonObj> param;
			if (t.triggerIn != null && t.triggerIn.size() != 0) {
				Message m = queue.poll();
				if (m == null) {
					param = new ArrayList<>()
				} else {
					param = m.parameters;
				}
			} else {
				param = new ArrayList<>();
			}
			
			// execute the transition and return whether to exit
			boolean toExit = transitionAndIfFinal(t, param);
			if (toExit) EXIT = true;
		}
	}
	
	public abstract void startInit();
	
	public abstract List<? extends Transition> findPossibleTrans();
	
	private List<? extends Transition> findNextTransitions(
			List<? extends Transition> possibleTrans) {
		List<Transition> guardTrans = new ArrayList<>();
		for (Transition t : possibleTrans)
			if (t.guard.get())
				guardTrans.add(t);
		if (guardTrans.size() == 0) return new ArrayList<>();
		
		Message m = queue.peek();
		List<Transition> inQueueTrans = new ArrayList<>();
		if (m != null) {
			for (Transition t : guardTrans) {
				if (t.triggerIn.size() == 0) {
					inQueueTrans.add(t);
				} else {
					for (TriggerIn ti : t.triggerIn) {
						if (m.port == ti.port && m.signal == ti.signal) {
							inQueueTrans.add(t);
						}
					}
				}
			}
		}
		
		for (Transition t : guardTrans) {
			if (t.timerPort != null) {
				if (instants.containsKey(t.timerPort)) {
					Instant instant = instants.get(t.timerPort);
					if (Instant.now().isAfter(instant)) {
						instants.remove(t.timerPort);
						inQueueTrans.add(t);
					}
				}
			}
		}
		
		return inQueueTrans;
	}
	
	public abstract boolean transitionAndIfFinal(Transition t, List<? extends CommonObj> params);
	
	private void sendMessage(Message m) {
		queue.add(m);
	}
	
	protected void passMessage(MessagePort p, Message m) {
		if (internalports.contains(p)) {
			Connector conn = findConnector(this, p);
			if (conn == null) throw new ConnectorNotFoundInCapsuleException();
			CapsulePortPair pr = findTarget(conn, this, p);
			pr.cap.passMessageIn(pr.port, m);
		} else if (externalports.contains(p)) {
			passMessageOut(p, m);
		}
	}
	
	private void passMessageOut(MessagePort p, Message m) {
		// assert externalports.contains(p)
		if (parent == null) throw new ExternalPortInRootCapsuleException();
		Connector conn = parent.findConnector(this, p);
		if (conn == null) throw new ConnectorNotFoundInCapsuleException();
		CapsulePortPair pr = parent.findTarget(conn, this, p);
		if (pr.cap != parent) 
			pr.cap.passMessageIn(pr.port, m);
		else
			pr.cap.passMessageOut(pr.port, m);
	}
	
	private void passMessageIn(MessagePort p, Message m) {
		// assert externalports.contains(p)
		Connector conn = findConnector(this, p);
		if (conn == null) {
			m.port = p;
			sendMessage(m);
		} else {
			CapsulePortPair pr = findTarget(conn, this, p);
			pr.cap.passMessageIn(pr.port, m);
		}
	}

	private Connector findConnector(Capsule c, MessagePort p) {
		for (Connector conn : connectors) {
			if (conn.cap1 == c && conn.port1 == p ||
			    conn.cap2 == c && conn.port2 == p)
			    	return conn;
		}
		return null; 
	}
	
	private CapsulePortPair findTarget(Connector conn, Capsule c, MessagePort p) {
		CapsulePortPair result = new CapsulePortPair();
		if (conn.cap1 == c && conn.port1 == p) {
			result.cap = conn.cap2;
			result.port = conn.port2;
		} else if (conn.cap2 == c && conn.port2 == p) {
			result.cap = conn.cap1;
			result.port = conn.port1;
		} else {
			throw new ConnectorInconsistentException();
		}
		return result;
	}
	
	public class ConnectorNotFoundInCapsuleException extends RuntimeException {
		private static final long serialVersionUID = 1L;
	}
	public class ExternalPortInRootCapsuleException extends RuntimeException {
		private static final long serialVersionUID = 1L;
	}
	public class ConnectorInconsistentException extends RuntimeException {
		private static final long serialVersionUID = 1L;
	}
}
