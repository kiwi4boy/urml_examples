package model.m_ReadersWriters;
import urml.runtime.*;
import java.util.*;
public class _C_Database extends Capsule {
	public _C_Database() {
		this(null);
	}
	
	public _C_Database(Capsule parent_) {
		this.parent = parent_;
		internalports = Arrays.asList(
		);
		
		externalports = Arrays.asList(
			_p_read, _p_write
		);
		capsules = Arrays.asList(
		);
		connectors = Arrays.asList(
			);
	}
	private int _a_thing = 1;
	Port _p_read = new Port("read", new _P_ReadAndWriteThings());
	Port _p_write = new Port("write", new _P_ReadAndWriteThings());
	private State _state_single = new State(
		"single",
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
		},
		Arrays.asList(
		)
	);
	private Transition _tran_readData = new Transition(
		"readData",
		() -> {
			return true;
		},
		params -> {
			System.out.println("logging to logger with: " + "read: " + _a_thing);
		},
		Arrays.asList(
			new TriggerIn(
				_p_read, _P_ReadAndWriteThings._s_read
			)
		)
	);
	private Transition _tran_writeData = new Transition(
		"writeData",
		() -> {
			return true;
		},
		params -> {
			int _i_data = ((Int) (params.get(0))).val;
			System.out.println("logging to logger with: " + "original: " + _a_thing);
			_a_thing = _i_data;System.out.println("logging to logger with: " + "modified: " + _a_thing);
		},
		Arrays.asList(
			new TriggerIn(
				_p_write, _P_ReadAndWriteThings._s_write
			)
		)
	);
	public List<? extends Transition> findPossibleTrans() {
		switch (currentState.name) {
			case "single":
				return Arrays.asList(_tran_readData, _tran_writeData);
			default:
				return new ArrayList<>();
		}
	}
	public void startInit() {
			_tran_init.action.accept(new ArrayList<>());
			currentState = _state_single;
			_state_single.entry.run();
	}
	public boolean transitionAndIfFinal(
			Transition t, List<? extends CommonObj> params)  {
			switch (t.name) {
				case "readData":
					_tran_readData.action.accept(params);
					currentState = _state_single;
					return false;
				case "writeData":
					_tran_writeData.action.accept(params);
					currentState = _state_single;
					return false;
				default:
					return false;
			}
	}
}
