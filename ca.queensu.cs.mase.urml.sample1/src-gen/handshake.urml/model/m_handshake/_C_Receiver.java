package model.m_handshake;
import urml.runtime.*;
import java.util.*;
public class _C_Receiver extends Capsule {
	public _C_Receiver() {
		this(null);
	}
	
	public _C_Receiver(Capsule parent_) {
		this.parent = parent_;
		internalports = Arrays.asList(
		);
		
		externalports = Arrays.asList(
			_p_hand
		);
		capsules = Arrays.asList(
		);
		connectors = Arrays.asList(
			);
	}
	Port _p_hand = new Port("hand", new _P_HandshakeProtocol());
	private State _state_start = new State(
		"start",
		() -> {
		},
		() -> {
		});
	private State _state_end = new State(
		"end",
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
	private Transition _tran_receiveHandshake = new Transition(
		"receiveHandshake",
		() -> {
			return true;
		},
		params -> {
			System.out.println("logging to logger with: " + "received a handshake");
		},
		Arrays.asList(
			new TriggerIn(
				_p_hand, _P_HandshakeProtocol._s_shake
			)
		)
	);
	public List<? extends Transition> findPossibleTrans() {
		switch (currentState.name) {
			case "start":
				return Arrays.asList(_tran_receiveHandshake);
			case "end":
				return Arrays.asList();
			default:
				return new ArrayList<>();
		}
	}
	public void startInit() {
			_tran_init.action.accept(new ArrayList<>());
			currentState = _state_start;
			_state_start.entry.run();
	}
	public boolean transitionAndIfFinal(
			Transition t, List<? extends CommonObj> params)  {
			switch (t.name) {
				case "receiveHandshake":
					_state_start.exit.run();
					_tran_receiveHandshake.action.accept(params);
					_state_end.entry.run();
					currentState = _state_end;
					return true;
				default:
					return false;
			}
	}
}
