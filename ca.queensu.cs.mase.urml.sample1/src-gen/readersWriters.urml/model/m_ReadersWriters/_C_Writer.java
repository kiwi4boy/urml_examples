package model.m_ReadersWriters;
import urml.runtime.*;
import java.util.*;
public class _C_Writer extends Capsule {
	public _C_Writer() {
		this(null);
	}
	
	public _C_Writer(Capsule parent_) {
		this.parent = parent_;
		internalports = Arrays.asList(
		);
		
		externalports = Arrays.asList(
			_p_con, _p_write
		);
		capsules = Arrays.asList(
		);
		connectors = Arrays.asList(
			);
	}
	private boolean _a_writeRequestSent = false;
	private int _a_timeoutPeriod = 200;
	Port _p_con = new Port("con", new _P_ControllerRW());
	Port _p_write = new Port("write", new _P_ReadAndWriteThings());
	private State _state_notWriting = new State(
		"notWriting",
		() -> {
		},
		() -> {
		});
	private State _state_writing = new State(
		"writing",
		() -> {
			System.out.println("logging to logger with: " + "writing 1234");
			passMessage(_p_write, new Message(
				_p_write, 
				_P_ReadAndWriteThings._s_write,
				Arrays.asList(
					new Int(1234)
				)));
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
	private Transition _tran_getWriting = new Transition(
		"getWriting",
		() -> {
			return _a_writeRequestSent == false;
		},
		params -> {
			passMessage(_p_con, new Message(
				_p_con, 
				_P_ControllerRW._s_acquireWrite,
				Arrays.asList(
				)));
			_a_writeRequestSent = true;
		},
		Arrays.asList(
		)
	);
	private Transition _tran_goWriting = new Transition(
		"goWriting",
		() -> {
			return true;
		},
		params -> {
			_a_writeRequestSent = false;System.out.println("logging to logger with: " + "goWriting");
			// dummy
		},
		Arrays.asList(
			new TriggerIn(
				_p_con, _P_ControllerRW._s_acknowledge
			)
		)
	);
	private Transition _tran_backToNotWriting = new Transition(
		"backToNotWriting",
		() -> {
			return true;
		},
		params -> {
			passMessage(_p_con, new Message(
				_p_con, 
				_P_ControllerRW._s_releaseWrite,
				Arrays.asList(
				)));
		},
		Arrays.asList(
		)
	);
	public List<? extends Transition> findPossibleTrans() {
		switch (currentState.name) {
			case "notWriting":
				return Arrays.asList(_tran_getWriting, _tran_goWriting);
			case "writing":
				return Arrays.asList(_tran_backToNotWriting);
			default:
				return new ArrayList<>();
		}
	}
	public void startInit() {
			_tran_init.action.accept(new ArrayList<>());
			currentState = _state_notWriting;
			_state_notWriting.entry.run();
	}
	public boolean transitionAndIfFinal(
			Transition t, List<? extends CommonObj> params)  {
			switch (t.name) {
				case "getWriting":
					_tran_getWriting.action.accept(params);
					currentState = _state_notWriting;
					return false;
				case "goWriting":
					_state_notWriting.exit.run();
					_tran_goWriting.action.accept(params);
					_state_writing.entry.run();
					currentState = _state_writing;
					return false;
				case "backToNotWriting":
					_state_writing.exit.run();
					_tran_backToNotWriting.action.accept(params);
					_state_notWriting.entry.run();
					currentState = _state_notWriting;
					return false;
				default:
					return false;
			}
	}
}
