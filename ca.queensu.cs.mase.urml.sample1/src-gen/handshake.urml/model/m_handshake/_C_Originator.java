package model.m_handshake;
import urml.runtime.*;
import java.util.*;
public class _C_Originator extends Capsule {
	public _C_Originator() {
		this(null);
	}
	
	public _C_Originator(Capsule parent_) {
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
	private Transition _tran_doHandShake = new Transition(
		"doHandShake",
		() -> {
			return true;
		},
		params -> {
			passMessage(_p_hand, new Message(
				_p_hand, 
				_P_HandshakeProtocol._s_shake,
				Arrays.asList(
				)));
			System.out.println("logging to logger with: " + "sent a handshake");
		},
		Arrays.asList(
		)
	);
	public List<? extends Transition> findPossibleTrans() {
		switch (currentState.name) {
			case "start":
				return Arrays.asList(_tran_doHandShake);
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
				case "doHandShake":
					_state_start.exit.run();
					_tran_doHandShake.action.accept(params);
					_state_end.entry.run();
					currentState = _state_end;
					return true;
				default:
					return false;
			}
	}
}
