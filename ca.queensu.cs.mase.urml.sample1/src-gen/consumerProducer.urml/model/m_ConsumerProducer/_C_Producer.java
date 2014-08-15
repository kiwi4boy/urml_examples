package model.m_ConsumerProducer;
import urml.runtime.*;
import java.util.*;
public class _C_Producer extends Capsule {
	public _C_Producer() {
		this(null);
	}
	
	public _C_Producer(Capsule parent_) {
		this.parent = parent_;
		internalports = Arrays.asList(
		);
		
		externalports = Arrays.asList(
			_p_toPut
		);
		capsules = Arrays.asList(
		);
		connectors = Arrays.asList(
			);
	}
	Port _p_toPut = new Port("toPut", new _P_BufferProtocol());
	private State _state_single = new State(
		"single",
		() -> {
			int _l_x = 1;
			while (_l_x < 8) {
				passMessage(_p_toPut, new Message(
					_p_toPut, 
					_P_BufferProtocol._s_put,
					Arrays.asList(
						new Int(_l_x)
					)));
				_l_x = _l_x + 1;
			}
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
	public List<? extends Transition> findPossibleTrans() {
		switch (currentState.name) {
			case "single":
				return Arrays.asList();
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
				default:
					return false;
			}
	}
}
