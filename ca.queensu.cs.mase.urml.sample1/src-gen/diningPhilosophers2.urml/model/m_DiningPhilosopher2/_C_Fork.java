package model.m_DiningPhilosopher2;
import urml.runtime.*;
import java.util.*;
public class _C_Fork extends Capsule {
	public _C_Fork() {
		this(null);
	}
	
	public _C_Fork(Capsule parent_) {
		this.parent = parent_;
		internalports = Arrays.asList(
		);
		
		externalports = Arrays.asList(
			_p_left, _p_right
		);
		capsules = Arrays.asList(
		);
		connectors = Arrays.asList(
			);
	}
	Port _p_left = new Port("left", new _P_PhilosopherForkProtocol());
	Port _p_right = new Port("right", new _P_PhilosopherForkProtocol());
	private State _state_up = new State(
		"up",
		() -> {
			System.out.println("logging to logger with: " + "up");
		},
		() -> {
		});
	private State _state_down = new State(
		"down",
		() -> {
			System.out.println("logging to logger with: " + "down");
		},
		() -> {
		});
	private Transition _tran_init = new Transition(
		"init",
		() -> {
			return true;
		},
		params -> {
		},
		Arrays.asList(
		)
	);
	private Transition _tran_sendAvailL = new Transition(
		"sendAvailL",
		() -> {
			return true;
		},
		params -> {
			passMessage(_p_left, new Message(
				_p_left, 
				_P_PhilosopherForkProtocol._s_avail,
				Arrays.asList(
				)));
			System.out.println("logging to logger with: " + "sendAvailL");
		},
		Arrays.asList(
			new TriggerIn(
				_p_left, _P_PhilosopherForkProtocol._s_checkAvail
			)
		)
	);
	private Transition _tran_sendAvailR = new Transition(
		"sendAvailR",
		() -> {
			return true;
		},
		params -> {
			passMessage(_p_right, new Message(
				_p_right, 
				_P_PhilosopherForkProtocol._s_avail,
				Arrays.asList(
				)));
			System.out.println("logging to logger with: " + "sendAvailR");
		},
		Arrays.asList(
			new TriggerIn(
				_p_right, _P_PhilosopherForkProtocol._s_checkAvail
			)
		)
	);
	private Transition _tran_sendNotAvailL = new Transition(
		"sendNotAvailL",
		() -> {
			return true;
		},
		params -> {
			passMessage(_p_left, new Message(
				_p_left, 
				_P_PhilosopherForkProtocol._s_notAvail,
				Arrays.asList(
				)));
			System.out.println("logging to logger with: " + "sendNotAvailL");
		},
		Arrays.asList(
			new TriggerIn(
				_p_left, _P_PhilosopherForkProtocol._s_checkAvail
			)
		)
	);
	private Transition _tran_sendNotAvailR = new Transition(
		"sendNotAvailR",
		() -> {
			return true;
		},
		params -> {
			passMessage(_p_right, new Message(
				_p_right, 
				_P_PhilosopherForkProtocol._s_notAvail,
				Arrays.asList(
				)));
			System.out.println("logging to logger with: " + "sendNotAvailR");
		},
		Arrays.asList(
			new TriggerIn(
				_p_right, _P_PhilosopherForkProtocol._s_checkAvail
			)
		)
	);
	private Transition _tran_picked = new Transition(
		"picked",
		() -> {
			return true;
		},
		params -> {
		},
		Arrays.asList(
			new TriggerIn(
				_p_left, _P_PhilosopherForkProtocol._s_pick
			),
			new TriggerIn(
				_p_right, _P_PhilosopherForkProtocol._s_pick
			)
		)
	);
	private Transition _tran_dropped = new Transition(
		"dropped",
		() -> {
			return true;
		},
		params -> {
		},
		Arrays.asList(
			new TriggerIn(
				_p_left, _P_PhilosopherForkProtocol._s_drop
			),
			new TriggerIn(
				_p_right, _P_PhilosopherForkProtocol._s_drop
			)
		)
	);
	public List<? extends Transition> findPossibleTrans() {
		switch (currentState.name) {
			case "up":
				return Arrays.asList(_tran_sendNotAvailL, _tran_sendNotAvailR, _tran_dropped);
			case "down":
				return Arrays.asList(_tran_sendAvailL, _tran_sendAvailR, _tran_picked);
			default:
				return new ArrayList<>();
		}
	}
	public void startInit() {
			_tran_init.action.accept(new ArrayList<>());
			currentState = _state_down;
			_state_down.entry.run();
	}
	public boolean transitionAndIfFinal(
			Transition t, List<? extends CommonObj> params)  {
			switch (t.name) {
				case "sendAvailL":
					_tran_sendAvailL.action.accept(params);
					currentState = _state_down;
					return false;
				case "sendAvailR":
					_tran_sendAvailR.action.accept(params);
					currentState = _state_down;
					return false;
				case "sendNotAvailL":
					_tran_sendNotAvailL.action.accept(params);
					currentState = _state_up;
					return false;
				case "sendNotAvailR":
					_tran_sendNotAvailR.action.accept(params);
					currentState = _state_up;
					return false;
				case "picked":
					_state_down.exit.run();
					_tran_picked.action.accept(params);
					_state_up.entry.run();
					currentState = _state_up;
					return false;
				case "dropped":
					_state_up.exit.run();
					_tran_dropped.action.accept(params);
					_state_down.entry.run();
					currentState = _state_down;
					return false;
				default:
					return false;
			}
	}
}
