package model.m_widgetManufacturing;
import urml.runtime.*;
import java.util.*;
public class _C_Robot extends Capsule {
	public _C_Robot() {
		this(null);
	}
	
	public _C_Robot(Capsule parent_) {
		this.parent = parent_;
		internalports = Arrays.asList(
		);
		
		externalports = Arrays.asList(
			_p_Robot2CSPort
		);
		capsules = Arrays.asList(
		);
		connectors = Arrays.asList(
			);
	}
	private int _a_widgetDeliveringTime = 5000;
	Port _p_Robot2CSPort = new Port("Robot2CSPort", new _P_RobotProtocol());
	private State _state_on = new State(
		"on",
		() -> {
		},
		() -> {
		});
	private State _state_standby = new State(
		"standby",
		() -> {
			System.out.println("logging to RobotLog with: " + "Robot: standby");
		},
		() -> {
		});
	private State _state_delivering = new State(
		"delivering",
		() -> {
			// dummy
			System.out.println("logging to RobotLog with: " + "Robot: delivering");
		},
		() -> {
		});
	private State _state_shutDown = new State(
		"shutDown",
		() -> {
			System.out.println("logging to RobotLog with: " + "Robot: shutDown");
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
			new TriggerIn(
				_p_Robot2CSPort, _P_RobotProtocol._s_deliverWidget
			)
		)
	);
	private Transition _tran_finished = new Transition(
		"finished",
		() -> {
			return true;
		},
		params -> {
			passMessage(_p_Robot2CSPort, new Message(
				_p_Robot2CSPort, 
				_P_RobotProtocol._s_widgetDelivered,
				Arrays.asList(
				)));
		},
		Arrays.asList(
		)
	);
	private Transition _tran_stop1 = new Transition(
		"stop1",
		() -> {
			return true;
		},
		params -> {
		},
		Arrays.asList(
			new TriggerIn(
				_p_Robot2CSPort, _P_RobotProtocol._s_shutDown
			)
		)
	);
	private Transition _tran_stop2 = new Transition(
		"stop2",
		() -> {
			return true;
		},
		params -> {
		},
		Arrays.asList(
			new TriggerIn(
				_p_Robot2CSPort, _P_RobotProtocol._s_shutDown
			)
		)
	);
	public List<? extends Transition> findPossibleTrans() {
		switch (currentState.name) {
			case "on":
				return Arrays.asList();
			case "standby":
				return Arrays.asList(_tran_start, _tran_stop1);
			case "delivering":
				return Arrays.asList(_tran_finished, _tran_stop2);
			case "shutDown":
				return Arrays.asList();
			default:
				return new ArrayList<>();
		}
	}
	public void startInit() {
			_tran_init.action.accept(new ArrayList<>());
			currentState = _state_standby;
			_state_standby.entry.run();
	}
	public boolean transitionAndIfFinal(
			Transition t, List<? extends CommonObj> params)  {
			switch (t.name) {
				case "start":
					_state_standby.exit.run();
					_tran_start.action.accept(params);
					_state_delivering.entry.run();
					currentState = _state_delivering;
					return false;
				case "finished":
					_state_delivering.exit.run();
					_tran_finished.action.accept(params);
					_state_standby.entry.run();
					currentState = _state_standby;
					return false;
				case "stop1":
					_state_standby.exit.run();
					_state_on.exit.run();
					_tran_stop1.action.accept(params);
					_state_shutDown.entry.run();
					currentState = _state_shutDown;
					return true;
				case "stop2":
					_state_delivering.exit.run();
					_state_on.exit.run();
					_tran_stop2.action.accept(params);
					_state_shutDown.entry.run();
					currentState = _state_shutDown;
					return true;
				default:
					return false;
			}
	}
}
