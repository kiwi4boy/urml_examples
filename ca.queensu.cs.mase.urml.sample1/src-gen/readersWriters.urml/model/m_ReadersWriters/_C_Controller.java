package model.m_ReadersWriters;
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
			_p_r0, _p_r1, _p_r2, _p_w0, _p_w1
		);
		capsules = Arrays.asList(
		);
		connectors = Arrays.asList(
			);
	}
	private int _a_readers = 0;
	private boolean _a_writing = false;
	private boolean _a_w0waiting = false;
	private boolean _a_w1waiting = false;
	private boolean _a_r0waiting = false;
	private boolean _a_r1waiting = false;
	private boolean _a_r2waiting = false;
	Port _p_r0 = new Port("r0", new _P_ControllerRW());
	Port _p_r1 = new Port("r1", new _P_ControllerRW());
	Port _p_r2 = new Port("r2", new _P_ControllerRW());
	Port _p_w0 = new Port("w0", new _P_ControllerRW());
	Port _p_w1 = new Port("w1", new _P_ControllerRW());
	private State _state_none = new State(
		"none",
		() -> {
		},
		() -> {
		});
	private State _state_writing = new State(
		"writing",
		() -> {
			System.out.println("logging to logger with: " + "CONTROLLER WRITING");
		},
		() -> {
		});
	private State _state_reading = new State(
		"reading",
		() -> {
			System.out.println("logging to logger with: " + "CONTROLLER READING");
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
	private Transition _tran_askedWriteW0 = new Transition(
		"askedWriteW0",
		() -> {
			return _a_readers == 0 && !_a_writing;
		},
		params -> {
			_a_writing = true;System.out.println("logging to logger with: " + "acquire write W0");
			passMessage(_p_w0, new Message(
				_p_w0, 
				_P_ControllerRW._s_acknowledge,
				Arrays.asList(
				)));
		},
		Arrays.asList(
			new TriggerIn(
				_p_w0, _P_ControllerRW._s_acquireWrite
			)
		)
	);
	private Transition _tran_askedWriteW0A = new Transition(
		"askedWriteW0A",
		() -> {
			return _a_readers == 0 && !_a_writing && _a_w0waiting;
		},
		params -> {
			_a_writing = true;passMessage(_p_w0, new Message(
				_p_w0, 
				_P_ControllerRW._s_acknowledge,
				Arrays.asList(
				)));
			_a_w0waiting = false;
		},
		Arrays.asList(
		)
	);
	private Transition _tran_askedWriteW1 = new Transition(
		"askedWriteW1",
		() -> {
			return _a_readers == 0 && !_a_writing;
		},
		params -> {
			_a_writing = true;System.out.println("logging to logger with: " + "acquire write W1");
			passMessage(_p_w1, new Message(
				_p_w1, 
				_P_ControllerRW._s_acknowledge,
				Arrays.asList(
				)));
		},
		Arrays.asList(
			new TriggerIn(
				_p_w1, _P_ControllerRW._s_acquireWrite
			)
		)
	);
	private Transition _tran_askedWriteW1A = new Transition(
		"askedWriteW1A",
		() -> {
			return _a_readers == 0 && !_a_writing && _a_w1waiting;
		},
		params -> {
			_a_writing = true;passMessage(_p_w1, new Message(
				_p_w1, 
				_P_ControllerRW._s_acknowledge,
				Arrays.asList(
				)));
			_a_w1waiting = false;
		},
		Arrays.asList(
		)
	);
	private Transition _tran_askedReadR0 = new Transition(
		"askedReadR0",
		() -> {
			return _a_writing == false;
		},
		params -> {
			_a_readers = 1;System.out.println("logging to logger with: " + "acquire read R0");
			passMessage(_p_r0, new Message(
				_p_r0, 
				_P_ControllerRW._s_acknowledge,
				Arrays.asList(
				)));
		},
		Arrays.asList(
			new TriggerIn(
				_p_r0, _P_ControllerRW._s_acquireRead
			)
		)
	);
	private Transition _tran_askedReadR1 = new Transition(
		"askedReadR1",
		() -> {
			return _a_writing == false;
		},
		params -> {
			_a_readers = 1;System.out.println("logging to logger with: " + "acquire read R1");
			passMessage(_p_r1, new Message(
				_p_r1, 
				_P_ControllerRW._s_acknowledge,
				Arrays.asList(
				)));
		},
		Arrays.asList(
			new TriggerIn(
				_p_r1, _P_ControllerRW._s_acquireRead
			)
		)
	);
	private Transition _tran_askedReadR2 = new Transition(
		"askedReadR2",
		() -> {
			return _a_writing == false;
		},
		params -> {
			_a_readers = 1;System.out.println("logging to logger with: " + "acquire read R2 with one reader");
			passMessage(_p_r2, new Message(
				_p_r2, 
				_P_ControllerRW._s_acknowledge,
				Arrays.asList(
				)));
		},
		Arrays.asList(
			new TriggerIn(
				_p_r2, _P_ControllerRW._s_acquireRead
			)
		)
	);
	private Transition _tran_askedReadR0A = new Transition(
		"askedReadR0A",
		() -> {
			return !_a_writing && _a_r0waiting;
		},
		params -> {
			_a_readers = 1;_a_r0waiting = false;System.out.println("logging to logger with: " + "acquire read R0 with one reader");
			passMessage(_p_r0, new Message(
				_p_r0, 
				_P_ControllerRW._s_acknowledge,
				Arrays.asList(
				)));
		},
		Arrays.asList(
		)
	);
	private Transition _tran_askedReadR1A = new Transition(
		"askedReadR1A",
		() -> {
			return !_a_writing && _a_r1waiting;
		},
		params -> {
			_a_readers = 1;_a_r1waiting = false;System.out.println("logging to logger with: " + "acquire read R1 with one reader");
			passMessage(_p_r1, new Message(
				_p_r1, 
				_P_ControllerRW._s_acknowledge,
				Arrays.asList(
				)));
		},
		Arrays.asList(
		)
	);
	private Transition _tran_askedReadR2A = new Transition(
		"askedReadR2A",
		() -> {
			return !_a_writing && _a_r2waiting;
		},
		params -> {
			_a_readers = 1;_a_r2waiting = false;System.out.println("logging to logger with: " + "acquire read R2 with one reader");
			passMessage(_p_r2, new Message(
				_p_r2, 
				_P_ControllerRW._s_acknowledge,
				Arrays.asList(
				)));
		},
		Arrays.asList(
		)
	);
	private Transition _tran_multipleAskedReaderR0 = new Transition(
		"multipleAskedReaderR0",
		() -> {
			return true;
		},
		params -> {
			_a_readers = _a_readers + 1;System.out.println("logging to logger with: " + "acquire read R0 with " + _a_readers + " readers");
			passMessage(_p_r0, new Message(
				_p_r0, 
				_P_ControllerRW._s_acknowledge,
				Arrays.asList(
				)));
		},
		Arrays.asList(
			new TriggerIn(
				_p_r0, _P_ControllerRW._s_acquireRead
			)
		)
	);
	private Transition _tran_multipleAskedReaderR1 = new Transition(
		"multipleAskedReaderR1",
		() -> {
			return true;
		},
		params -> {
			_a_readers = _a_readers + 1;System.out.println("logging to logger with: " + "acquire read R1 with " + _a_readers + " readers");
			passMessage(_p_r1, new Message(
				_p_r1, 
				_P_ControllerRW._s_acknowledge,
				Arrays.asList(
				)));
		},
		Arrays.asList(
			new TriggerIn(
				_p_r1, _P_ControllerRW._s_acquireRead
			)
		)
	);
	private Transition _tran_multipleAskedReaderR2 = new Transition(
		"multipleAskedReaderR2",
		() -> {
			return true;
		},
		params -> {
			_a_readers = _a_readers + 1;System.out.println("logging to logger with: " + "acquire read R2 with " + _a_readers + " readers");
			passMessage(_p_r2, new Message(
				_p_r2, 
				_P_ControllerRW._s_acknowledge,
				Arrays.asList(
				)));
		},
		Arrays.asList(
			new TriggerIn(
				_p_r2, _P_ControllerRW._s_acquireRead
			)
		)
	);
	private Transition _tran_multipleAskedReaderR0A = new Transition(
		"multipleAskedReaderR0A",
		() -> {
			return _a_r0waiting;
		},
		params -> {
			_a_readers = _a_readers + 1;_a_r0waiting = false;System.out.println("logging to logger with: " + "acquire read R0 with " + _a_readers + " readers");
			passMessage(_p_r0, new Message(
				_p_r0, 
				_P_ControllerRW._s_acknowledge,
				Arrays.asList(
				)));
		},
		Arrays.asList(
		)
	);
	private Transition _tran_multipleAskedReaderR1A = new Transition(
		"multipleAskedReaderR1A",
		() -> {
			return _a_r1waiting;
		},
		params -> {
			_a_readers = _a_readers + 1;_a_r1waiting = false;System.out.println("logging to logger with: " + "acquire read R1 with " + _a_readers + " readers");
			passMessage(_p_r1, new Message(
				_p_r1, 
				_P_ControllerRW._s_acknowledge,
				Arrays.asList(
				)));
		},
		Arrays.asList(
		)
	);
	private Transition _tran_multipleAskedReaderR2A = new Transition(
		"multipleAskedReaderR2A",
		() -> {
			return _a_r2waiting;
		},
		params -> {
			_a_readers = _a_readers + 1;_a_r2waiting = false;System.out.println("logging to logger with: " + "acquire read R2 with " + _a_readers + " readers");
			passMessage(_p_r2, new Message(
				_p_r2, 
				_P_ControllerRW._s_acknowledge,
				Arrays.asList(
				)));
		},
		Arrays.asList(
		)
	);
	private Transition _tran_w0requestR = new Transition(
		"w0requestR",
		() -> {
			return true;
		},
		params -> {
			_a_w0waiting = true;
		},
		Arrays.asList(
			new TriggerIn(
				_p_w0, _P_ControllerRW._s_acquireWrite
			)
		)
	);
	private Transition _tran_w0requestW = new Transition(
		"w0requestW",
		() -> {
			return true;
		},
		params -> {
			_a_w0waiting = true;
		},
		Arrays.asList(
			new TriggerIn(
				_p_w0, _P_ControllerRW._s_acquireWrite
			)
		)
	);
	private Transition _tran_w1requestR = new Transition(
		"w1requestR",
		() -> {
			return true;
		},
		params -> {
			_a_w1waiting = true;
		},
		Arrays.asList(
			new TriggerIn(
				_p_w1, _P_ControllerRW._s_acquireWrite
			)
		)
	);
	private Transition _tran_w1requestW = new Transition(
		"w1requestW",
		() -> {
			return true;
		},
		params -> {
			_a_w1waiting = true;
		},
		Arrays.asList(
			new TriggerIn(
				_p_w1, _P_ControllerRW._s_acquireWrite
			)
		)
	);
	private Transition _tran_r0request = new Transition(
		"r0request",
		() -> {
			return true;
		},
		params -> {
			_a_r0waiting = true;
		},
		Arrays.asList(
			new TriggerIn(
				_p_r0, _P_ControllerRW._s_acquireRead
			)
		)
	);
	private Transition _tran_r1request = new Transition(
		"r1request",
		() -> {
			return true;
		},
		params -> {
			_a_r1waiting = true;
		},
		Arrays.asList(
			new TriggerIn(
				_p_r1, _P_ControllerRW._s_acquireRead
			)
		)
	);
	private Transition _tran_r2request = new Transition(
		"r2request",
		() -> {
			return true;
		},
		params -> {
			_a_r2waiting = true;
		},
		Arrays.asList(
			new TriggerIn(
				_p_r2, _P_ControllerRW._s_acquireRead
			)
		)
	);
	private Transition _tran_releaseWrite = new Transition(
		"releaseWrite",
		() -> {
			return true;
		},
		params -> {
			_a_writing = false;System.out.println("logging to logger with: " + "release write");
		},
		Arrays.asList(
			new TriggerIn(
				_p_w0, _P_ControllerRW._s_releaseWrite
			),
			new TriggerIn(
				_p_w1, _P_ControllerRW._s_releaseWrite
			)
		)
	);
	private Transition _tran_multipleReleaseRead = new Transition(
		"multipleReleaseRead",
		() -> {
			return _a_readers > 1;
		},
		params -> {
			_a_readers = _a_readers - 1;System.out.println("logging to logger with: " + "release read with " + _a_readers + " readers remaining");
		},
		Arrays.asList(
			new TriggerIn(
				_p_r0, _P_ControllerRW._s_releaseRead
			),
			new TriggerIn(
				_p_r1, _P_ControllerRW._s_releaseRead
			),
			new TriggerIn(
				_p_r2, _P_ControllerRW._s_releaseRead
			)
		)
	);
	private Transition _tran_releaseRead = new Transition(
		"releaseRead",
		() -> {
			return _a_readers == 1;
		},
		params -> {
			_a_readers = 0;System.out.println("logging to logger with: " + "release read with no readers remaining");
		},
		Arrays.asList(
			new TriggerIn(
				_p_r0, _P_ControllerRW._s_releaseRead
			),
			new TriggerIn(
				_p_r1, _P_ControllerRW._s_releaseRead
			),
			new TriggerIn(
				_p_r2, _P_ControllerRW._s_releaseRead
			)
		)
	);
	public List<? extends Transition> findPossibleTrans() {
		switch (currentState.name) {
			case "none":
				return Arrays.asList(_tran_askedWriteW0, _tran_askedWriteW0A, _tran_askedWriteW1, _tran_askedWriteW1A, _tran_askedReadR0, _tran_askedReadR1, _tran_askedReadR2, _tran_askedReadR0A, _tran_askedReadR1A, _tran_askedReadR2A);
			case "writing":
				return Arrays.asList(_tran_w0requestW, _tran_w1requestW, _tran_r0request, _tran_r1request, _tran_r2request, _tran_releaseWrite);
			case "reading":
				return Arrays.asList(_tran_multipleAskedReaderR0, _tran_multipleAskedReaderR1, _tran_multipleAskedReaderR2, _tran_multipleAskedReaderR0A, _tran_multipleAskedReaderR1A, _tran_multipleAskedReaderR2A, _tran_w0requestR, _tran_w1requestR, _tran_multipleReleaseRead, _tran_releaseRead);
			default:
				return new ArrayList<>();
		}
	}
	public void startInit() {
			_tran_init.action.accept(new ArrayList<>());
			currentState = _state_none;
			_state_none.entry.run();
	}
	public boolean transitionAndIfFinal(
			Transition t, List<? extends CommonObj> params)  {
			switch (t.name) {
				case "askedWriteW0":
					_state_none.exit.run();
					_tran_askedWriteW0.action.accept(params);
					_state_writing.entry.run();
					currentState = _state_writing;
					return false;
				case "askedWriteW0A":
					_state_none.exit.run();
					_tran_askedWriteW0A.action.accept(params);
					_state_writing.entry.run();
					currentState = _state_writing;
					return false;
				case "askedWriteW1":
					_state_none.exit.run();
					_tran_askedWriteW1.action.accept(params);
					_state_writing.entry.run();
					currentState = _state_writing;
					return false;
				case "askedWriteW1A":
					_state_none.exit.run();
					_tran_askedWriteW1A.action.accept(params);
					_state_writing.entry.run();
					currentState = _state_writing;
					return false;
				case "askedReadR0":
					_state_none.exit.run();
					_tran_askedReadR0.action.accept(params);
					_state_reading.entry.run();
					currentState = _state_reading;
					return false;
				case "askedReadR1":
					_state_none.exit.run();
					_tran_askedReadR1.action.accept(params);
					_state_reading.entry.run();
					currentState = _state_reading;
					return false;
				case "askedReadR2":
					_state_none.exit.run();
					_tran_askedReadR2.action.accept(params);
					_state_reading.entry.run();
					currentState = _state_reading;
					return false;
				case "askedReadR0A":
					_state_none.exit.run();
					_tran_askedReadR0A.action.accept(params);
					_state_reading.entry.run();
					currentState = _state_reading;
					return false;
				case "askedReadR1A":
					_state_none.exit.run();
					_tran_askedReadR1A.action.accept(params);
					_state_reading.entry.run();
					currentState = _state_reading;
					return false;
				case "askedReadR2A":
					_state_none.exit.run();
					_tran_askedReadR2A.action.accept(params);
					_state_reading.entry.run();
					currentState = _state_reading;
					return false;
				case "multipleAskedReaderR0":
					_tran_multipleAskedReaderR0.action.accept(params);
					currentState = _state_reading;
					return false;
				case "multipleAskedReaderR1":
					_tran_multipleAskedReaderR1.action.accept(params);
					currentState = _state_reading;
					return false;
				case "multipleAskedReaderR2":
					_tran_multipleAskedReaderR2.action.accept(params);
					currentState = _state_reading;
					return false;
				case "multipleAskedReaderR0A":
					_tran_multipleAskedReaderR0A.action.accept(params);
					currentState = _state_reading;
					return false;
				case "multipleAskedReaderR1A":
					_tran_multipleAskedReaderR1A.action.accept(params);
					currentState = _state_reading;
					return false;
				case "multipleAskedReaderR2A":
					_tran_multipleAskedReaderR2A.action.accept(params);
					currentState = _state_reading;
					return false;
				case "w0requestR":
					_tran_w0requestR.action.accept(params);
					currentState = _state_reading;
					return false;
				case "w0requestW":
					_tran_w0requestW.action.accept(params);
					currentState = _state_writing;
					return false;
				case "w1requestR":
					_tran_w1requestR.action.accept(params);
					currentState = _state_reading;
					return false;
				case "w1requestW":
					_tran_w1requestW.action.accept(params);
					currentState = _state_writing;
					return false;
				case "r0request":
					_tran_r0request.action.accept(params);
					currentState = _state_writing;
					return false;
				case "r1request":
					_tran_r1request.action.accept(params);
					currentState = _state_writing;
					return false;
				case "r2request":
					_tran_r2request.action.accept(params);
					currentState = _state_writing;
					return false;
				case "releaseWrite":
					_state_writing.exit.run();
					_tran_releaseWrite.action.accept(params);
					_state_none.entry.run();
					currentState = _state_none;
					return false;
				case "multipleReleaseRead":
					_tran_multipleReleaseRead.action.accept(params);
					currentState = _state_reading;
					return false;
				case "releaseRead":
					_state_reading.exit.run();
					_tran_releaseRead.action.accept(params);
					_state_none.entry.run();
					currentState = _state_none;
					return false;
				default:
					return false;
			}
	}
}
