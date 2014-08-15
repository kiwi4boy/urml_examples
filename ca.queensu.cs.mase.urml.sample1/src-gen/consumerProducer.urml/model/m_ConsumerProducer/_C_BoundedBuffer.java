package model.m_ConsumerProducer;
import urml.runtime.*;
import java.util.*;
public class _C_BoundedBuffer extends Capsule {
	public _C_BoundedBuffer() {
		this(null);
	}
	
	public _C_BoundedBuffer(Capsule parent_) {
		this.parent = parent_;
		internalports = Arrays.asList(
		);
		
		externalports = Arrays.asList(
			_p_consumer, _p_producer
		);
		capsules = Arrays.asList(
		);
		connectors = Arrays.asList(
			);
	}
	private int _a_a = 0;
	private int _a_b = 0;
	private int _a_c = 0;
	private int _a_d = 0;
	private int _a_e = 0;
	Port _p_consumer = new Port("consumer", new _P_BufferProtocol());
	Port _p_producer = new Port("producer", new _P_BufferProtocol());
	private State _state_zero = new State(
		"zero",
		() -> {
		},
		() -> {
		});
	private State _state_one = new State(
		"one",
		() -> {
		},
		() -> {
		});
	private State _state_two = new State(
		"two",
		() -> {
		},
		() -> {
		});
	private State _state_three = new State(
		"three",
		() -> {
		},
		() -> {
		});
	private State _state_four = new State(
		"four",
		() -> {
		},
		() -> {
		});
	private State _state_five = new State(
		"five",
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
	private Transition _tran_ = new Transition(
		"",
		() -> {
			return true;
		},
		params -> {
			int _i_a_ = ((Int) (params.get(0))).val;
			_a_a = _i_a_;
		},
		Arrays.asList(
			new TriggerIn(
				_p_producer, _P_BufferProtocol._s_put
			)
		)
	);
	private Transition _tran_ = new Transition(
		"",
		() -> {
			return true;
		},
		params -> {
			int _i_b_ = ((Int) (params.get(0))).val;
			_a_b = _i_b_;
		},
		Arrays.asList(
			new TriggerIn(
				_p_producer, _P_BufferProtocol._s_put
			)
		)
	);
	private Transition _tran_ = new Transition(
		"",
		() -> {
			return true;
		},
		params -> {
			int _i_c_ = ((Int) (params.get(0))).val;
			_a_c = _i_c_;
		},
		Arrays.asList(
			new TriggerIn(
				_p_producer, _P_BufferProtocol._s_put
			)
		)
	);
	private Transition _tran_ = new Transition(
		"",
		() -> {
			return true;
		},
		params -> {
			int _i_d_ = ((Int) (params.get(0))).val;
			_a_d = _i_d_;
		},
		Arrays.asList(
			new TriggerIn(
				_p_producer, _P_BufferProtocol._s_put
			)
		)
	);
	private Transition _tran_ = new Transition(
		"",
		() -> {
			return true;
		},
		params -> {
			int _i_e_ = ((Int) (params.get(0))).val;
			_a_e = _i_e_;
		},
		Arrays.asList(
			new TriggerIn(
				_p_producer, _P_BufferProtocol._s_put
			)
		)
	);
	private Transition _tran_ = new Transition(
		"",
		() -> {
			return true;
		},
		params -> {
			passMessage(_p_consumer, new Message(
				_p_consumer, 
				_P_BufferProtocol._s_get,
				Arrays.asList(
					new Int(_a_e)
				)));
			_a_e = 0;
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
			passMessage(_p_consumer, new Message(
				_p_consumer, 
				_P_BufferProtocol._s_get,
				Arrays.asList(
					new Int(_a_d)
				)));
			_a_d = 0;
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
			passMessage(_p_consumer, new Message(
				_p_consumer, 
				_P_BufferProtocol._s_get,
				Arrays.asList(
					new Int(_a_c)
				)));
			_a_c = 0;
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
			passMessage(_p_consumer, new Message(
				_p_consumer, 
				_P_BufferProtocol._s_get,
				Arrays.asList(
					new Int(_a_b)
				)));
			_a_b = 0;
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
			passMessage(_p_consumer, new Message(
				_p_consumer, 
				_P_BufferProtocol._s_get,
				Arrays.asList(
					new Int(_a_a)
				)));
			_a_a = 0;
		},
		Arrays.asList(
		)
	);
	public List<? extends Transition> findPossibleTrans() {
		switch (currentState.name) {
			case "zero":
				return Arrays.asList(_tran_null);
			case "one":
				return Arrays.asList(_tran_null, _tran_null);
			case "two":
				return Arrays.asList(_tran_null, _tran_null);
			case "three":
				return Arrays.asList(_tran_null, _tran_null);
			case "four":
				return Arrays.asList(_tran_null, _tran_null);
			case "five":
				return Arrays.asList(_tran_null);
			default:
				return new ArrayList<>();
		}
	}
	public void startInit() {
			_tran_init.action.accept(new ArrayList<>());
			currentState = _state_zero;
			_state_zero.entry.run();
	}
	public boolean transitionAndIfFinal(
			Transition t, List<? extends CommonObj> params)  {
			switch (t.name) {
				case "":
					_state_zero.exit.run();
					_tran_.action.accept(params);
					_state_one.entry.run();
					currentState = _state_one;
					return false;
				case "":
					_state_one.exit.run();
					_tran_.action.accept(params);
					_state_two.entry.run();
					currentState = _state_two;
					return false;
				case "":
					_state_two.exit.run();
					_tran_.action.accept(params);
					_state_three.entry.run();
					currentState = _state_three;
					return false;
				case "":
					_state_three.exit.run();
					_tran_.action.accept(params);
					_state_four.entry.run();
					currentState = _state_four;
					return false;
				case "":
					_state_four.exit.run();
					_tran_.action.accept(params);
					_state_five.entry.run();
					currentState = _state_five;
					return false;
				case "":
					_state_five.exit.run();
					_tran_.action.accept(params);
					_state_four.entry.run();
					currentState = _state_four;
					return false;
				case "":
					_state_four.exit.run();
					_tran_.action.accept(params);
					_state_three.entry.run();
					currentState = _state_three;
					return false;
				case "":
					_state_three.exit.run();
					_tran_.action.accept(params);
					_state_two.entry.run();
					currentState = _state_two;
					return false;
				case "":
					_state_two.exit.run();
					_tran_.action.accept(params);
					_state_one.entry.run();
					currentState = _state_one;
					return false;
				case "":
					_state_one.exit.run();
					_tran_.action.accept(params);
					_state_zero.entry.run();
					currentState = _state_zero;
					return false;
				default:
					return false;
			}
	}
}
