package model.m_widgetManufacturing;
import urml.runtime.*;
import java.util.*;
public class _C_ControlSoftware extends Capsule {
	public _C_ControlSoftware() {
		this(null);
	}
	
	public _C_ControlSoftware(Capsule parent_) {
		this.parent = parent_;
		internalports = Arrays.asList(
		);
		
		externalports = Arrays.asList(
			_p_CS2WSPort, _p_CS2RobotPort
		);
		capsules = Arrays.asList(
		);
		connectors = Arrays.asList(
			);
	}
	private int _a_startUpDelay = 2000;
	private int _a_systemStopTime = 30000;
	Port _p_CS2WSPort = new Port("CS2WSPort", new _P_WorkstationProtocol());
	Port _p_CS2RobotPort = new Port("CS2RobotPort", new _P_RobotProtocol());
	private State _state_on = new State(
		"on",
		() -> {
		},
		() -> {
		});
	private State _state_startup = new State(
		"startup",
		() -> {
			// dummy
			// dummy
			System.out.println("logging to CSLog with: " + "CS: startup");
		},
		() -> {
		});
	private State _state_produce = new State(
		"produce",
		() -> {
			passMessage(_p_CS2WSPort, new Message(
				_p_CS2WSPort, 
				_P_WorkstationProtocol._s_produceWidget,
				Arrays.asList(
				)));
			System.out.println("logging to CSLog with: " + "CS: produce");
		},
		() -> {
		});
	private State _state_deliver = new State(
		"deliver",
		() -> {
			passMessage(_p_CS2RobotPort, new Message(
				_p_CS2RobotPort, 
				_P_RobotProtocol._s_deliverWidget,
				Arrays.asList(
				)));
			System.out.println("logging to CSLog with: " + "CS: deliver");
		},
		() -> {
		});
	private State _state_shutDown = new State(
		"shutDown",
		() -> {
			passMessage(_p_CS2WSPort, new Message(
				_p_CS2WSPort, 
				_P_WorkstationProtocol._s_shutDown,
				Arrays.asList(
				)));
			passMessage(_p_CS2RobotPort, new Message(
				_p_CS2RobotPort, 
				_P_RobotProtocol._s_shutDown,
				Arrays.asList(
				)));
			System.out.println("logging to CSLog with: " + "CS: shutDown");
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
	private Transition _tran_start = new Transition(
		"start",
		() -> {
			return true;
		},
		params -> {
		},
		Arrays.asList(
		)
	);
	private Transition _tran_deliverMe = new Transition(
		"deliverMe",
		() -> {
			return true;
		},
		params -> {
		},
		Arrays.asList(
			new TriggerIn(
				_p_CS2WSPort, _P_WorkstationProtocol._s_widgetProduced
			)
		)
	);
	private Transition _tran_goAgain = new Transition(
		"goAgain",
		() -> {
			return true;
		},
		params -> {
		},
		Arrays.asList(
			new TriggerIn(
				_p_CS2RobotPort, _P_RobotProtocol._s_widgetDelivered
			)
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
		)
	);
	public List<? extends Transition> findPossibleTrans() {
		switch (currentState.name) {
			case "on":
				return Arrays.asList(_tran_stop);
			case "startup":
				return Arrays.asList(_tran_start, _tran_stop);
			case "produce":
				return Arrays.asList(_tran_deliverMe, _tran_stop);
			case "deliver":
				return Arrays.asList(_tran_goAgain, _tran_stop);
			case "shutDown":
				return Arrays.asList();
			default:
				return new ArrayList<>();
		}
	}
	public void startInit() {
			_tran_init.action.accept(new ArrayList<>());
			currentState = _state_startup;
			_state_startup.entry.run();
	}
	public boolean transitionAndIfFinal(
			Transition t, List<? extends CommonObj> params)  {
			switch (t.name) {
				case "start":
					_state_startup.exit.run();
					_tran_start.action.accept(params);
					_state_produce.entry.run();
					currentState = _state_produce;
					return false;
				case "deliverMe":
					_state_produce.exit.run();
					_tran_deliverMe.action.accept(params);
					_state_deliver.entry.run();
					currentState = _state_deliver;
					return false;
				case "goAgain":
					_state_deliver.exit.run();
					_tran_goAgain.action.accept(params);
					_state_produce.entry.run();
					currentState = _state_produce;
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
