package model.m_widgetManufacturing;
import urml.runtime.*;
import java.util.*;
public class _C_Workstation extends Capsule {
	public _C_Workstation() {
		this(null);
	}
	
	public _C_Workstation(Capsule parent_) {
		this.parent = parent_;
		internalports = Arrays.asList(
		);
		
		externalports = Arrays.asList(
			_p_WS2CSPort
		);
		capsules = Arrays.asList(
		);
		connectors = Arrays.asList(
			);
	}
	private int _a_widgetProductionTime = 3000;
	Port _p_WS2CSPort = new Port("WS2CSPort", new _P_WorkstationProtocol());
	private State _state_on = new State(
		"on",
		() -> {
		},
		() -> {
		});
	private State _state_standby = new State(
		"standby",
		() -> {
			System.out.println("logging to WSLog with: " + "WS: standby");
		},
		() -> {
		});
	private State _state_producing = new State(
		"producing",
		() -> {
			// dummy
			System.out.println("logging to WSLog with: " + "WS: producing");
		},
		() -> {
		});
	private State _state_shutDown = new State(
		"shutDown",
		() -> {
			System.out.println("logging to WSLog with: " + "WS: shutDown");
		},
		() -> {
		});
	private Transition _tran_t1 = new Transition(
		"t1",
		() -> {
			return true;
		},
		params -> {
		},
		Arrays.asList(
		)
	);
	private Transition _tran_begin = new Transition(
		"begin",
		() -> {
			return true;
		},
		params -> {
		},
		Arrays.asList(
			new TriggerIn(
				_p_WS2CSPort, _P_WorkstationProtocol._s_produceWidget
			)
		)
	);
	private Transition _tran_finished = new Transition(
		"finished",
		() -> {
			return true;
		},
		params -> {
			passMessage(_p_WS2CSPort, new Message(
				_p_WS2CSPort, 
				_P_WorkstationProtocol._s_widgetProduced,
				Arrays.asList(
				)));
		},
		Arrays.asList(
		)
	);
	private Transition _tran_stop = new Transition(
		"stop",
		() -> {
			return true;
		},
		params -> {
		},
		Arrays.asList(
			new TriggerIn(
				_p_WS2CSPort, _P_WorkstationProtocol._s_shutDown
			)
		)
	);
	public List<? extends Transition> findPossibleTrans() {
		switch (currentState.name) {
			case "on":
				return Arrays.asList(_tran_stop);
			case "standby":
				return Arrays.asList(_tran_begin, _tran_stop);
			case "producing":
				return Arrays.asList(_tran_finished, _tran_stop);
			case "shutDown":
				return Arrays.asList();
			default:
				return new ArrayList<>();
		}
	}
	public void startInit() {
			_tran_t1.action.accept(new ArrayList<>());
			currentState = _state_standby;
			_state_standby.entry.run();
	}
	public boolean transitionAndIfFinal(
			Transition t, List<? extends CommonObj> params)  {
			switch (t.name) {
				case "begin":
					_state_standby.exit.run();
					_tran_begin.action.accept(params);
					_state_producing.entry.run();
					currentState = _state_producing;
					return false;
				case "finished":
					_state_producing.exit.run();
					_tran_finished.action.accept(params);
					_state_standby.entry.run();
					currentState = _state_standby;
					return false;
				case "stop":
					_state_on.exit.run();
					_tran_stop.action.accept(params);
					_state_shutDown.entry.run();
					currentState = _state_shutDown;
					return true;
				default:
					return false;
			}
	}
}
