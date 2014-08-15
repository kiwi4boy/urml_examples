package model.m_ConsumerProducer;
import urml.runtime.*;
import java.util.*;
public class _C_Consumer extends Capsule {
	public _C_Consumer() {
		this(null);
	}
	
	public _C_Consumer(Capsule parent_) {
		this.parent = parent_;
		internalports = Arrays.asList(
		);
		
		externalports = Arrays.asList(
			_p_toGet
		);
		capsules = Arrays.asList(
		);
		connectors = Arrays.asList(
			);
	}
	Port _p_toGet = new Port("toGet", new _P_BufferProtocol());
	private State _state_single = new State(
		"single",
		() -> {
		},
		() -> {
		});
	private Transition _tran_ = new Transition(
		"",
		() -> {
			return true;
		},
		params -> {
		},
		Arrays.asList(
		)
	);
	private Transition _tran_ = new Transition(
		"",
		() -> {
			return true;
		},
		params -> {
			int _i_toDisplay = ((Int) (params.get(0))).val;
			System.out.println("logging to logger with: " + _i_toDisplay);
		},
		Arrays.asList(
			new TriggerIn(
				_p_toGet, _P_BufferProtocol._s_get
			)
		)
	);
	public List<? extends Transition> findPossibleTrans() {
		switch (currentState.name) {
			case "single":
				return Arrays.asList(_tran_null);
			default:
				return new ArrayList<>();
		}
	}
	public void startInit() {
			_tran_.action.accept(new ArrayList<>());
			currentState = _state_single;
			_state_single.entry.run();
	}
	public boolean transitionAndIfFinal(
			Transition t, List<? extends CommonObj> params)  {
			switch (t.name) {
				case "":
					_tran_.action.accept(params);
					currentState = _state_single;
					return false;
				default:
					return false;
			}
	}
}
