package model.m_DiningPhilosophers;
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
	private boolean _a_leftWaiting = false;
	private boolean _a_rightWaiting = false;
	Port _p_left = new Port("left", new _P_PhilosopherForkProtocol());
	Port _p_right = new Port("right", new _P_PhilosopherForkProtocol());
	private State _state_down = new State(
		"down",
		() -> {
			System.out.println("logging to logger with: " + "down");
		},
		() -> {
		});
	private State _state_up = new State(
		"up",
		() -> {
			System.out.println("logging to logger with: " + "up");
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
	private Transition _tran_pickUpFromLeft = new Transition(
		"pickUpFromLeft",
		() -> {
			return true;
		},
		params -> {
			passMessage(_p_left, new Message(
				_p_left, 
				_P_PhilosopherForkProtocol._s_ack,
				Arrays.asList(
				)));
		},
		Arrays.asList(
			new TriggerIn(
				_p_left, _P_PhilosopherForkProtocol._s_up
			)
		)
	);
	private Transition _tran_pickUpFromRight = new Transition(
		"pickUpFromRight",
		() -> {
			return true;
		},
		params -> {
			passMessage(_p_right, new Message(
				_p_right, 
				_P_PhilosopherForkProtocol._s_ack,
				Arrays.asList(
				)));
		},
		Arrays.asList(
			new TriggerIn(
				_p_right, _P_PhilosopherForkProtocol._s_up
			)
		)
	);
	private Transition _tran_pickUpFromLeftButIsAlreadyUp = new Transition(
		"pickUpFromLeftButIsAlreadyUp",
		() -> {
			return true;
		},
		params -> {
			_a_leftWaiting = true;
		},
		Arrays.asList(
			new TriggerIn(
				_p_left, _P_PhilosopherForkProtocol._s_up
			)
		)
	);
	private Transition _tran_pickUpFromRightButIsAlreadyUp = new Transition(
		"pickUpFromRightButIsAlreadyUp",
		() -> {
			return true;
		},
		params -> {
			_a_rightWaiting = true;
		},
		Arrays.asList(
			new TriggerIn(
				_p_right, _P_PhilosopherForkProtocol._s_up
			)
		)
	);
	private Transition _tran_switchFromLeftToRight = new Transition(
		"switchFromLeftToRight",
		() -> {
			return _a_rightWaiting == true;
		},
		params -> {
			passMessage(_p_right, new Message(
				_p_right, 
				_P_PhilosopherForkProtocol._s_ack,
				Arrays.asList(
				)));
			_a_rightWaiting = false;System.out.println("logging to logger with: " + "fork exchanged from left to right");
		},
		Arrays.asList(
			new TriggerIn(
				_p_left, _P_PhilosopherForkProtocol._s_down
			)
		)
	);
	private Transition _tran_switchFromRightToLeft = new Transition(
		"switchFromRightToLeft",
		() -> {
			return _a_leftWaiting == true;
		},
		params -> {
			passMessage(_p_left, new Message(
				_p_left, 
				_P_PhilosopherForkProtocol._s_ack,
				Arrays.asList(
				)));
			_a_leftWaiting = false;System.out.println("logging to logger with: " + "fork exchanged from right to left");
		},
		Arrays.asList(
			new TriggerIn(
				_p_right, _P_PhilosopherForkProtocol._s_down
			)
		)
	);
	private Transition _tran_putDownForLeft = new Transition(
		"putDownForLeft",
		() -> {
			return _a_rightWaiting == false;
		},
		params -> {
		},
		Arrays.asList(
			new TriggerIn(
				_p_left, _P_PhilosopherForkProtocol._s_down
			)
		)
	);
	private Transition _tran_putDownForRight = new Transition(
		"putDownForRight",
		() -> {
			return _a_leftWaiting == false;
		},
		params -> {
		},
		Arrays.asList(
			new TriggerIn(
				_p_right, _P_PhilosopherForkProtocol._s_down
			)
		)
	);
	public List<? extends Transition> findPossibleTrans() {
		switch (currentState.name) {
			case "down":
				return Arrays.asList(_tran_pickUpFromLeft, _tran_pickUpFromRight);
			case "up":
				return Arrays.asList(_tran_pickUpFromLeftButIsAlreadyUp, _tran_pickUpFromRightButIsAlreadyUp, _tran_switchFromLeftToRight, _tran_switchFromRightToLeft, _tran_putDownForLeft, _tran_putDownForRight);
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
				case "pickUpFromLeft":
					_state_down.exit.run();
					_tran_pickUpFromLeft.action.accept(params);
					_state_up.entry.run();
					currentState = _state_up;
					return false;
				case "pickUpFromRight":
					_state_down.exit.run();
					_tran_pickUpFromRight.action.accept(params);
					_state_up.entry.run();
					currentState = _state_up;
					return false;
				case "pickUpFromLeftButIsAlreadyUp":
					_tran_pickUpFromLeftButIsAlreadyUp.action.accept(params);
					currentState = _state_up;
					return false;
				case "pickUpFromRightButIsAlreadyUp":
					_tran_pickUpFromRightButIsAlreadyUp.action.accept(params);
					currentState = _state_up;
					return false;
				case "switchFromLeftToRight":
					_tran_switchFromLeftToRight.action.accept(params);
					currentState = _state_up;
					return false;
				case "switchFromRightToLeft":
					_tran_switchFromRightToLeft.action.accept(params);
					currentState = _state_up;
					return false;
				case "putDownForLeft":
					_state_up.exit.run();
					_tran_putDownForLeft.action.accept(params);
					_state_down.entry.run();
					currentState = _state_down;
					return false;
				case "putDownForRight":
					_state_up.exit.run();
					_tran_putDownForRight.action.accept(params);
					_state_down.entry.run();
					currentState = _state_down;
					return false;
				default:
					return false;
			}
	}
}
