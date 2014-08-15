package model.m_handshake;
import urml.runtime.*;
import java.util.*;
public class _C_Handshake extends Capsule {
	public _C_Handshake() {
		this(null);
	}
	
	public _C_Handshake(Capsule parent_) {
		this.parent = parent_;
		internalports = Arrays.asList(
		);
		
		externalports = Arrays.asList(
		);
		capsules = Arrays.asList(
			_ci_sender, _ci_receiver
		);
		connectors = Arrays.asList(
				new Connector(
				_ci_sender,
				((_C_Originator) _ci_sender)._p_hand,
				_ci_receiver,
				((_C_Receiver) _ci_receiver)._p_hand
				)
		);
	}
	Capsule _ci_sender = new _C_Originator(this);
	Capsule _ci_receiver = new _C_Receiver(this);
	public List<? extends Transition> findPossibleTrans() {
		switch (currentState.name) {
			default:
				return new ArrayList<>();
		}
	}
	public void startInit() {
	}
	public boolean transitionAndIfFinal(
			Transition t, List<? extends CommonObj> params)  {
			switch (t.name) {
				default:
					return false;
			}
	}
}
