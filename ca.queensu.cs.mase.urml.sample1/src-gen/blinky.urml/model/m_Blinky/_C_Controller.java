package model.m_Blinky;
import urml.runtime.*;
import java.util.*;
public class _C_Controller extends Capsule {
	public _C_Controller() {
		this(null);
	}
	
	public _C_Controller(Capsule parent_) {
		this.parent = parent_;
		internalports = Arrays.asList(
		);
		
		externalports = Arrays.asList(
			_p_connectToLight
		);
		capsules = Arrays.asList(
		);
		connectors = Arrays.asList(
			);
	}
	private int _a_blinkingCyclePeriod = 5000;
	Port _p_connectToLight = new Port("connectToLight", new _P_ControllerProtocol());
	private State _state_on = new State(
		"on",
		() -> {
		},
		() -> {
		});
	private State _state_off = new State(
		"off",
		() -> {
		},
		() -> {
		});
	private Transition _tran_init = new Transition(
		"init",
		() -> {
			return true;
		},
		params -> {
			// dummy
			passMessage(_p_connectToLight, new Message(
				_p_connectToLight, 
				_P_ControllerProtocol._s_start,
				Arrays.asList(
				)));
			System.out.println("logging to logger with: " + "connect to light start");
		},
		Arrays.asList(
		)
	);
	private Transition _tran_on2off = new Transition(
		"on2off",
		() -> {
			return true;
		},
		params -> {
			// dummy
			passMessage(_p_connectToLight, new Message(
				_p_connectToLight, 
				_P_ControllerProtocol._s_stop,
				Arrays.asList(
				)));
			System.out.println("logging to logger with: " + "Blinky stops blinking for " + _a_blinkingCyclePeriod / 1000 + " seconds");
		},
		Arrays.asList(
		)
	);
	private Transition _tran_off2On = new Transition(
		"off2On",
		() -> {
			return true;
		},
		params -> {
			// dummy
			passMessage(_p_connectToLight, new Message(
				_p_connectToLight, 
				_P_ControllerProtocol._s_start,
				Arrays.asList(
				)));
			System.out.println("logging to logger with: " + "Blinky blinks for " + _a_blinkingCyclePeriod / 1000 + " seconds");
		},
		Arrays.asList(
		)
	);
	public List<? extends Transition> findPossibleTrans() {
		switch (currentState.name) {
			case "on":
				return Arrays.asList(_tran_on2off);
			case "off":
				return Arrays.asList(_tran_off2On);
			default:
				return new ArrayList<>();
		}
	}
	public void startInit() {
			_tran_init.action.accept(new ArrayList<>());
			currentState = _state_on;
			_state_on.entry.run();
	}
	public boolean transitionAndIfFinal(
			Transition t, List<? extends CommonObj> params)  {
			switch (t.name) {
				case "on2off":
					_state_on.exit.run();
					_tran_on2off.action.accept(params);
					_state_off.entry.run();
					currentState = _state_off;
					return false;
				case "off2On":
					_state_off.exit.run();
					_tran_off2On.action.accept(params);
					_state_on.entry.run();
					currentState = _state_on;
					return false;
				default:
					return false;
			}
	}
}
