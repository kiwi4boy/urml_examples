package model.m_Blinky;
import urml.runtime.*;
import java.util.*;
public class _C_BlinkingLight extends Capsule {
	public _C_BlinkingLight() {
		this(null);
	}
	
	public _C_BlinkingLight(Capsule parent_) {
		this.parent = parent_;
		internalports = Arrays.asList(
		);
		
		externalports = Arrays.asList(
			_p_connectToController
		);
		capsules = Arrays.asList(
		);
		connectors = Arrays.asList(
			);
	}
	private int _a_onAndOff_Period = 1000;
	Port _p_connectToController = new Port("connectToController", new _P_ControllerProtocol());
	private State _state_off = new State(
		"off",
		() -> {
		},
		() -> {
		});
	private State _state_blinking = new State(
		"blinking",
		() -> {
		},
		() -> {
		});
	private State _state_subOn = new State(
		"subOn",
		() -> {
			// dummy
			System.out.println("logging to logger with: " + "Lights " + "turn to yellow for " + _a_onAndOff_Period / 1000 + " seconds...");
		},
		() -> {
		});
	private State _state_subOff = new State(
		"subOff",
		() -> {
			// dummy
			System.out.println("logging to logger with: " + "Lights turn off for " + _a_onAndOff_Period / 1000 + " seconds");
		},
		() -> {
		});
	private Transition _tran_on2off = new Transition(
		"on2off",
		() -> {
			return true;
		},
		params -> {
		},
		Arrays.asList(
		)
	);
	private Transition _tran_subOff2on = new Transition(
		"subOff2on",
		() -> {
			return true;
		},
		params -> {
		},
		Arrays.asList(
		)
	);
	private Transition _tran_subInit = new Transition(
		"subInit",
		() -> {
			return true;
		},
		params -> {
		},
		Arrays.asList(
		)
	);
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
	private Transition _tran_startBlinking = new Transition(
		"startBlinking",
		() -> {
			return true;
		},
		params -> {
		},
		Arrays.asList(
			new TriggerIn(
				_p_connectToController, _P_ControllerProtocol._s_start
			)
		)
	);
	private Transition _tran_stopBlinking = new Transition(
		"stopBlinking",
		() -> {
			return true;
		},
		params -> {
		},
		Arrays.asList(
			new TriggerIn(
				_p_connectToController, _P_ControllerProtocol._s_stop
			)
		)
	);
	public List<? extends Transition> findPossibleTrans() {
		switch (currentState.name) {
			case "off":
				return Arrays.asList(_tran_startBlinking);
			case "blinking":
				return Arrays.asList(_tran_stopBlinking);
			case "subOn":
				return Arrays.asList(_tran_on2off, _tran_stopBlinking);
			case "subOff":
				return Arrays.asList(_tran_subOff2on, _tran_stopBlinking);
			default:
				return new ArrayList<>();
		}
	}
	public void startInit() {
			_tran_init.action.accept(new ArrayList<>());
			currentState = _state_off;
			_state_off.entry.run();
	}
	public boolean transitionAndIfFinal(
			Transition t, List<? extends CommonObj> params)  {
			switch (t.name) {
				case "on2off":
					_state_subOn.exit.run();
					_tran_on2off.action.accept(params);
					_state_subOff.entry.run();
					currentState = _state_subOff;
					return false;
				case "subOff2on":
					_state_subOff.exit.run();
					_tran_subOff2on.action.accept(params);
					_state_subOn.entry.run();
					currentState = _state_subOn;
					return false;
				case "startBlinking":
					_state_off.exit.run();
					_tran_startBlinking.action.accept(params);
					_state_blinking.entry.run();
					_state_subOn.entry.run();
					currentState = _state_subOn;
					return false;
				case "stopBlinking":
					_state_blinking.exit.run();
					_tran_stopBlinking.action.accept(params);
					_state_off.entry.run();
					currentState = _state_off;
					return false;
				default:
					return false;
			}
	}
}
