package model.m_ReadersWriters;
import urml.runtime.*;
import java.util.*;
public class _C_Reader extends Capsule {
	public _C_Reader() {
		this(null);
	}
	
	public _C_Reader(Capsule parent_) {
		this.parent = parent_;
		internalports = Arrays.asList(
		);
		
		externalports = Arrays.asList(
			_p_con, _p_read
		);
		capsules = Arrays.asList(
		);
		connectors = Arrays.asList(
			);
	}
	private boolean _a_readRequestSent = false;
	private int _a_timeoutPeriod = 200;
	Port _p_con = new Port("con", new _P_ControllerRW());
	Port _p_read = new Port("read", new _P_ReadAndWriteThings());
	private State _state_notReading = new State(
		"notReading",
		() -> {
		},
		() -> {
		});
	private State _state_reading = new State(
		"reading",
		() -> {
			passMessage(_p_read, new Message(
				_p_read, 
				_P_ReadAndWriteThings._s_read,
				Arrays.asList(
				)));
			System.out.println("logging to logger with: " + "");
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
	private Transition _tran_getReading = new Transition(
		"getReading",
		() -> {
			return _a_readRequestSent == false;
		},
		params -> {
			passMessage(_p_con, new Message(
				_p_con, 
				_P_ControllerRW._s_acquireRead,
				Arrays.asList(
				)));
			_a_readRequestSent = true;
		},
		Arrays.asList(
		)
	);
	private Transition _tran_goReading = new Transition(
		"goReading",
		() -> {
			return true;
		},
		params -> {
			System.out.println("logging to logger with: " + "goReading");
			_a_readRequestSent = false;// dummy
		},
		Arrays.asList(
			new TriggerIn(
				_p_con, _P_ControllerRW._s_acknowledge
			)
		)
	);
	private Transition _tran_backToNotReading = new Transition(
		"backToNotReading",
		() -> {
			return true;
		},
		params -> {
			passMessage(_p_con, new Message(
				_p_con, 
				_P_ControllerRW._s_releaseRead,
				Arrays.asList(
				)));
		},
		Arrays.asList(
		)
	);
	public List<? extends Transition> findPossibleTrans() {
		switch (currentState.name) {
			case "notReading":
				return Arrays.asList(_tran_getReading, _tran_goReading);
			case "reading":
				return Arrays.asList(_tran_backToNotReading);
			default:
				return new ArrayList<>();
		}
	}
	public void startInit() {
			_tran_init.action.accept(new ArrayList<>());
			currentState = _state_notReading;
			_state_notReading.entry.run();
	}
	public boolean transitionAndIfFinal(
			Transition t, List<? extends CommonObj> params)  {
			switch (t.name) {
				case "getReading":
					_tran_getReading.action.accept(params);
					currentState = _state_notReading;
					return false;
				case "goReading":
					_state_notReading.exit.run();
					_tran_goReading.action.accept(params);
					_state_reading.entry.run();
					currentState = _state_reading;
					return false;
				case "backToNotReading":
					_state_reading.exit.run();
					_tran_backToNotReading.action.accept(params);
					_state_notReading.entry.run();
					currentState = _state_notReading;
					return false;
				default:
					return false;
			}
	}
}
