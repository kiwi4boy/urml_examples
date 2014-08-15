package model.m_DiningPhilosophers;
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
			_p_left, _p_right, _p_signer
		);
		capsules = Arrays.asList(
		);
		connectors = Arrays.asList(
			);
	}
	private int _a_id = -1;
	private int _a_delayTimeout = 300;
	private int _a_eatTimeout = 600;
	private int _a_thinkTimeout = 600;
	public int _f_isEvenPhil() {
		return _a_id % 2 == 0;
	}
	public void _f_setID(int _l_id_) {
		_a_id = _l_id_;
	}
	Port _p_left = new Port("left", new _P_PhilosopherForkProtocol());
	Port _p_right = new Port("right", new _P_PhilosopherForkProtocol());
	Port _p_signer = new Port("signer", new _P_RingPhilosopherProtocol());
	private State _state_start = new State(
		"start",
		() -> {
		},
		() -> {
		});
	private State _state_delay = new State(
		"delay",
		() -> {
			// dummy
		},
		() -> {
		});
	private State _state_think = new State(
		"think",
		() -> {
			// dummy
			System.out.println("logging to logger with: " + "thinking...");
		},
		() -> {
		});
	private State _state_pickingUpFirstFork = new State(
		"pickingUpFirstFork",
		() -> {
			System.out.println("logging to logger with: " + "picking up the first fork");
		},
		() -> {
		});
	private State _state_gotFirstFork = new State(
		"gotFirstFork",
		() -> {
			System.out.println("logging to logger with: " + "got the first fork");
		},
		() -> {
		});
	private State _state_pickingUpSecondFork = new State(
		"pickingUpSecondFork",
		() -> {
			System.out.println("logging to logger with: " + "picking up the second fork");
		},
		() -> {
		});
	private State _state_gotSecondFork = new State(
		"gotSecondFork",
		() -> {
			System.out.println("logging to logger with: " + "got the second fork");
		},
		() -> {
		});
	private State _state_eating = new State(
		"eating",
		() -> {
			// dummy
			System.out.println("logging to logger with: " + "eating... om nom nom");
		},
		() -> {
		});
	private State _state_puttingDownFirstFork = new State(
		"puttingDownFirstFork",
		() -> {
			System.out.println("logging to logger with: " + "putting down the first fork");
		},
		() -> {
		});
	private State _state_puttingDownSecondFork = new State(
		"puttingDownSecondFork",
		() -> {
			System.out.println("logging to logger with: " + "putting down the second fork");
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
	private Transition _tran_setPhilID = new Transition(
		"setPhilID",
		() -> {
			return true;
		},
		params -> {
			int _i_num = ((Int) (params.get(0))).val;
			_f_setID(_i_num);
		},
		Arrays.asList(
			new TriggerIn(
				_p_signer, _P_RingPhilosopherProtocol._s_sign
			)
		)
	);
	private Transition _tran_startToThink = new Transition(
		"startToThink",
		() -> {
			return true;
		},
		params -> {
		},
		Arrays.asList(
		)
	);
	private Transition _tran_pickUpFirstFork = new Transition(
		"pickUpFirstFork",
		() -> {
			return true;
		},
		params -> {
			if (_f_isEvenPhil()) {
				passMessage(_p_right, new Message(
					_p_right, 
					_P_PhilosopherForkProtocol._s_up,
					Arrays.asList(
					)));
			}  else {
				passMessage(_p_left, new Message(
					_p_left, 
					_P_PhilosopherForkProtocol._s_up,
					Arrays.asList(
					)));
			}
		},
		Arrays.asList(
		)
	);
	private Transition _tran_waitAckFromFirstFork = new Transition(
		"waitAckFromFirstFork",
		() -> {
			return true;
		},
		params -> {
		},
		Arrays.asList(
			new TriggerIn(
				_p_left, _P_PhilosopherForkProtocol._s_ack
			),
			new TriggerIn(
				_p_right, _P_PhilosopherForkProtocol._s_ack
			)
		)
	);
	private Transition _tran_pickUpSecondFork = new Transition(
		"pickUpSecondFork",
		() -> {
			return true;
		},
		params -> {
			if (_f_isEvenPhil()) {
				passMessage(_p_left, new Message(
					_p_left, 
					_P_PhilosopherForkProtocol._s_up,
					Arrays.asList(
					)));
			}  else {
				passMessage(_p_right, new Message(
					_p_right, 
					_P_PhilosopherForkProtocol._s_up,
					Arrays.asList(
					)));
			}
		},
		Arrays.asList(
		)
	);
	private Transition _tran_waitAckFromSecondFork = new Transition(
		"waitAckFromSecondFork",
		() -> {
			return true;
		},
		params -> {
		},
		Arrays.asList(
			new TriggerIn(
				_p_left, _P_PhilosopherForkProtocol._s_ack
			),
			new TriggerIn(
				_p_right, _P_PhilosopherForkProtocol._s_ack
			)
		)
	);
	private Transition _tran_goEat = new Transition(
		"goEat",
		() -> {
			return true;
		},
		params -> {
		},
		Arrays.asList(
		)
	);
	private Transition _tran_putDownFirstFork = new Transition(
		"putDownFirstFork",
		() -> {
			return true;
		},
		params -> {
			passMessage(_p_left, new Message(
				_p_left, 
				_P_PhilosopherForkProtocol._s_down,
				Arrays.asList(
				)));
		},
		Arrays.asList(
		)
	);
	private Transition _tran_putDownSecondFork = new Transition(
		"putDownSecondFork",
		() -> {
			return true;
		},
		params -> {
			passMessage(_p_right, new Message(
				_p_right, 
				_P_PhilosopherForkProtocol._s_down,
				Arrays.asList(
				)));
		},
		Arrays.asList(
		)
	);
	private Transition _tran_goThink = new Transition(
		"goThink",
		() -> {
			return true;
		},
		params -> {
		},
		Arrays.asList(
		)
	);
	public List<? extends Transition> findPossibleTrans() {
		switch (currentState.name) {
			case "start":
				return Arrays.asList(_tran_setPhilID);
			case "delay":
				return Arrays.asList(_tran_startToThink);
			case "think":
				return Arrays.asList(_tran_pickUpFirstFork);
			case "pickingUpFirstFork":
				return Arrays.asList(_tran_waitAckFromFirstFork);
			case "gotFirstFork":
				return Arrays.asList(_tran_pickUpSecondFork);
			case "pickingUpSecondFork":
				return Arrays.asList(_tran_waitAckFromSecondFork);
			case "gotSecondFork":
				return Arrays.asList(_tran_goEat);
			case "eating":
				return Arrays.asList(_tran_putDownFirstFork);
			case "puttingDownFirstFork":
				return Arrays.asList(_tran_putDownSecondFork);
			case "puttingDownSecondFork":
				return Arrays.asList(_tran_goThink);
			default:
				return new ArrayList<>();
		}
	}
	public void startInit() {
			_tran_init.action.accept(new ArrayList<>());
			currentState = _state_start;
			_state_start.entry.run();
	}
	public boolean transitionAndIfFinal(
			Transition t, List<? extends CommonObj> params)  {
			switch (t.name) {
				case "setPhilID":
					_state_start.exit.run();
					_tran_setPhilID.action.accept(params);
					_state_delay.entry.run();
					currentState = _state_delay;
					return false;
				case "startToThink":
					_state_delay.exit.run();
					_tran_startToThink.action.accept(params);
					_state_think.entry.run();
					currentState = _state_think;
					return false;
				case "pickUpFirstFork":
					_state_think.exit.run();
					_tran_pickUpFirstFork.action.accept(params);
					_state_pickingUpFirstFork.entry.run();
					currentState = _state_pickingUpFirstFork;
					return false;
				case "waitAckFromFirstFork":
					_state_pickingUpFirstFork.exit.run();
					_tran_waitAckFromFirstFork.action.accept(params);
					_state_gotFirstFork.entry.run();
					currentState = _state_gotFirstFork;
					return false;
				case "pickUpSecondFork":
					_state_gotFirstFork.exit.run();
					_tran_pickUpSecondFork.action.accept(params);
					_state_pickingUpSecondFork.entry.run();
					currentState = _state_pickingUpSecondFork;
					return false;
				case "waitAckFromSecondFork":
					_state_pickingUpSecondFork.exit.run();
					_tran_waitAckFromSecondFork.action.accept(params);
					_state_gotSecondFork.entry.run();
					currentState = _state_gotSecondFork;
					return false;
				case "goEat":
					_state_gotSecondFork.exit.run();
					_tran_goEat.action.accept(params);
					_state_eating.entry.run();
					currentState = _state_eating;
					return false;
				case "putDownFirstFork":
					_state_eating.exit.run();
					_tran_putDownFirstFork.action.accept(params);
					_state_puttingDownFirstFork.entry.run();
					currentState = _state_puttingDownFirstFork;
					return false;
				case "putDownSecondFork":
					_state_puttingDownFirstFork.exit.run();
					_tran_putDownSecondFork.action.accept(params);
					_state_puttingDownSecondFork.entry.run();
					currentState = _state_puttingDownSecondFork;
					return false;
				case "goThink":
					_state_puttingDownSecondFork.exit.run();
					_tran_goThink.action.accept(params);
					_state_think.entry.run();
					currentState = _state_think;
					return false;
				default:
					return false;
			}
	}
}
