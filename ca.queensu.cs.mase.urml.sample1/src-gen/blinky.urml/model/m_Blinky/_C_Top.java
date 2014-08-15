package model.m_Blinky;
import urml.runtime.*;
import java.util.*;
public class _C_Top extends Capsule {
	public _C_Top() {
		this(null);
	}
	
	public _C_Top(Capsule parent_) {
		this.parent = parent_;
		internalports = Arrays.asList(
		);
		
		externalports = Arrays.asList(
		);
		capsules = Arrays.asList(
			_ci_blinky, _ci_controller
		);
		connectors = Arrays.asList(
				new Connector(
				_ci_blinky,
				((_C_BlinkingLight) _ci_blinky)._p_connectToController,
				_ci_controller,
				((_C_Controller) _ci_controller)._p_connectToLight
				)
		);
	}
	Capsule _ci_blinky = new _C_BlinkingLight(this);
	Capsule _ci_controller = new _C_Controller(this);
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
