package model.m_DiningPhilosopher2;
import urml.runtime.*;
import java.util.*;
public class _C_Philosopher extends Capsule {
	public _C_Philosopher() {
		this(null);
	}
	
	public _C_Philosopher(Capsule parent_) {
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
	private State _state_think = new State(
		"think",
		() -> {
			System.out.println("logging to logger with: " + "think");
			passMessage(_p_left, new Message(
				_p_left, 
				_P_PhilosopherForkProtocol._s_checkAvail,
				Arrays.asList(
				)));
			passMessage(_p_right, new Message(
				_p_right, 
				_P_PhilosopherForkProtocol._s_checkAvail,
				Arrays.asList(
				)));
		},
		() -> {
		});
	private State _state_pickedL = new State(
		"pickedL",
		() -> {
			System.out.println("logging to logger with: " + "pickedL");
			passMessage(_p_right, new Message(
				_p_right, 
				_P_PhilosopherForkProtocol._s_checkAvail,
				Arrays.asList(
				)));
		},
		() -> {
		});
	private State _state_pickedR = new State(
		"pickedR",
		() -> {
			System.out.println("logging to logger with: " + "pickedR");
			passMessage(_p_left, new Message(
				_p_left, 
				_P_PhilosopherForkProtocol._s_checkAvail,
				Arrays.asList(
				)));
		},
		() -> {
		});
	private State _state_eat = new State(
		"eat",
		() -> {
			System.out.println("logging to logger with: " + "eat");
		},
		() -> {
		});
	private State _state_droppedLeft = new State(
		"droppedLeft",
		() -> {
			System.out.println("logging to logger with: " + "droppedLeft");
		},
		() -> {
		});
	private State _state_droppedRight = new State(
		"droppedRight",
		() -> {
			System.out.println("logging to logger with: " + "droppedRight");
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
	private Transition _tran_reask1L = new Transition(
		"reask1L",
		() -> {
			return true;
		},
		params -> {
			passMessage(_p_left, new Message(
				_p_left, 
				_P_PhilosopherForkProtocol._s_checkAvail,
				Arrays.asList(
				)));
			System.out.println("logging to logger with: " + "reask1L");
		},
		Arrays.asList(
			new TriggerIn(
				_p_left, _P_PhilosopherForkProtocol._s_notAvail
			)
		)
	);
	private Transition _tran_pick1L = new Transition(
		"pick1L",
		() -> {
			return true;
		},
		params -> {
			passMessage(_p_left, new Message(
				_p_left, 
				_P_PhilosopherForkProtocol._s_pick,
				Arrays.asList(
				)));
			System.out.println("logging to logger with: " + "pick1L");
		},
		Arrays.asList(
			new TriggerIn(
				_p_left, _P_PhilosopherForkProtocol._s_avail
			)
		)
	);
	private Transition _tran_reask2R = new Transition(
		"reask2R",
		() -> {
			return true;
		},
		params -> {
			passMessage(_p_right, new Message(
				_p_right, 
				_P_PhilosopherForkProtocol._s_checkAvail,
				Arrays.asList(
				)));
			System.out.println("logging to logger with: " + "reask2R");
		},
		Arrays.asList(
			new TriggerIn(
				_p_right, _P_PhilosopherForkProtocol._s_notAvail
			)
		)
	);
	private Transition _tran_pick2R = new Transition(
		"pick2R",
		() -> {
			return true;
		},
		params -> {
			passMessage(_p_right, new Message(
				_p_right, 
				_P_PhilosopherForkProtocol._s_pick,
				Arrays.asList(
				)));
			System.out.println("logging to logger with: " + "pick2R");
		},
		Arrays.asList(
			new TriggerIn(
				_p_right, _P_PhilosopherForkProtocol._s_avail
			)
		)
	);
	private Transition _tran_reask1R = new Transition(
		"reask1R",
		() -> {
			return true;
		},
		params -> {
			passMessage(_p_right, new Message(
				_p_right, 
				_P_PhilosopherForkProtocol._s_checkAvail,
				Arrays.asList(
				)));
			System.out.println("logging to logger with: " + "reask1r");
		},
		Arrays.asList(
			new TriggerIn(
				_p_right, _P_PhilosopherForkProtocol._s_notAvail
			)
		)
	);
	private Transition _tran_pick1R = new Transition(
		"pick1R",
		() -> {
			return true;
		},
		params -> {
			passMessage(_p_right, new Message(
				_p_right, 
				_P_PhilosopherForkProtocol._s_pick,
				Arrays.asList(
				)));
			System.out.println("logging to logger with: " + "pick1R");
		},
		Arrays.asList(
			new TriggerIn(
				_p_right, _P_PhilosopherForkProtocol._s_avail
			)
		)
	);
	private Transition _tran_reask2L = new Transition(
		"reask2L",
		() -> {
			return true;
		},
		params -> {
			passMessage(_p_left, new Message(
				_p_left, 
				_P_PhilosopherForkProtocol._s_checkAvail,
				Arrays.asList(
				)));
			System.out.println("logging to logger with: " + "reask2L");
		},
		Arrays.asList(
			new TriggerIn(
				_p_left, _P_PhilosopherForkProtocol._s_notAvail
			)
		)
	);
	private Transition _tran_pick2L = new Transition(
		"pick2L",
		() -> {
			return true;
		},
		params -> {
			passMessage(_p_left, new Message(
				_p_left, 
				_P_PhilosopherForkProtocol._s_pick,
				Arrays.asList(
				)));
			System.out.println("logging to logger with: " + "pick2L");
		},
		Arrays.asList(
			new TriggerIn(
				_p_left, _P_PhilosopherForkProtocol._s_avail
			)
		)
	);
	private Transition _tran_drop1L = new Transition(
		"drop1L",
		() -> {
			return true;
		},
		params -> {
			passMessage(_p_left, new Message(
				_p_left, 
				_P_PhilosopherForkProtocol._s_drop,
				Arrays.asList(
				)));
			System.out.println("logging to logger with: " + "drop1L");
		},
		Arrays.asList(
		)
	);
	private Transition _tran_drop1R = new Transition(
		"drop1R",
		() -> {
			return true;
		},
		params -> {
			passMessage(_p_right, new Message(
				_p_right, 
				_P_PhilosopherForkProtocol._s_drop,
				Arrays.asList(
				)));
			System.out.println("logging to logger with: " + "drop1R");
		},
		Arrays.asList(
		)
	);
	private Transition _tran_drop2L = new Transition(
		"drop2L",
		() -> {
			return true;
		},
		params -> {
			passMessage(_p_left, new Message(
				_p_left, 
				_P_PhilosopherForkProtocol._s_drop,
				Arrays.asList(
				)));
			System.out.println("logging to logger with: " + "drop2L");
		},
		Arrays.asList(
		)
	);
	private Transition _tran_drop2R = new Transition(
		"drop2R",
		() -> {
			return true;
		},
		params -> {
			passMessage(_p_right, new Message(
				_p_right, 
				_P_PhilosopherForkProtocol._s_drop,
				Arrays.asList(
				)));
			System.out.println("logging to logger with: " + "drop2R");
		},
		Arrays.asList(
		)
	);
	public List<? extends Transition> findPossibleTrans() {
		switch (currentState.name) {
			case "think":
				return Arrays.asList(_tran_reask1L, _tran_pick1L, _tran_reask1R, _tran_pick1R);
			case "pickedL":
				return Arrays.asList(_tran_reask2R, _tran_pick2R);
			case "pickedR":
				return Arrays.asList(_tran_reask2L, _tran_pick2L);
			case "eat":
				return Arrays.asList(_tran_drop1L, _tran_drop1R);
			case "droppedLeft":
				return Arrays.asList(_tran_drop2R);
			case "droppedRight":
				return Arrays.asList(_tran_drop2L);
			default:
				return new ArrayList<>();
		}
	}
	public void startInit() {
			_tran_init.action.accept(new ArrayList<>());
			currentState = _state_think;
			_state_think.entry.run();
	}
	public boolean transitionAndIfFinal(
			Transition t, List<? extends CommonObj> params)  {
			switch (t.name) {
				case "reask1L":
					_tran_reask1L.action.accept(params);
					currentState = _state_think;
					return false;
				case "pick1L":
					_state_think.exit.run();
					_tran_pick1L.action.accept(params);
					_state_pickedL.entry.run();
					currentState = _state_pickedL;
					return false;
				case "reask2R":
					_tran_reask2R.action.accept(params);
					currentState = _state_pickedL;
					return false;
				case "pick2R":
					_state_pickedL.exit.run();
					_tran_pick2R.action.accept(params);
					_state_eat.entry.run();
					currentState = _state_eat;
					return false;
				case "reask1R":
					_tran_reask1R.action.accept(params);
					currentState = _state_think;
					return false;
				case "pick1R":
					_state_think.exit.run();
					_tran_pick1R.action.accept(params);
					_state_pickedR.entry.run();
					currentState = _state_pickedR;
					return false;
				case "reask2L":
					_tran_reask2L.action.accept(params);
					currentState = _state_pickedR;
					return false;
				case "pick2L":
					_state_pickedR.exit.run();
					_tran_pick2L.action.accept(params);
					_state_eat.entry.run();
					currentState = _state_eat;
					return false;
				case "drop1L":
					_state_eat.exit.run();
					_tran_drop1L.action.accept(params);
					_state_droppedLeft.entry.run();
					currentState = _state_droppedLeft;
					return false;
				case "drop1R":
					_state_eat.exit.run();
					_tran_drop1R.action.accept(params);
					_state_droppedRight.entry.run();
					currentState = _state_droppedRight;
					return false;
				case "drop2L":
					_state_droppedRight.exit.run();
					_tran_drop2L.action.accept(params);
					_state_think.entry.run();
					currentState = _state_think;
					return false;
				case "drop2R":
					_state_droppedLeft.exit.run();
					_tran_drop2R.action.accept(params);
					_state_think.entry.run();
					currentState = _state_think;
					return false;
				default:
					return false;
			}
	}
}
