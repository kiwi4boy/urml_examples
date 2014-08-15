package model.m_widgetManufacturing;
import urml.runtime.*;
import java.util.*;
public class _C_ProductionLine extends Capsule {
	public _C_ProductionLine() {
		this(null);
	}
	
	public _C_ProductionLine(Capsule parent_) {
		this.parent = parent_;
		internalports = Arrays.asList(
		);
		
		externalports = Arrays.asList(
			_p_toWSPort, _p_toobotPort
		);
		capsules = Arrays.asList(
			_ci_workstationRef, _ci_robotRef
		);
		connectors = Arrays.asList(
				new Connector(
				this,
		_p_toWSPort,
				_ci_workstationRef,
				((_C_Workstation) _ci_workstationRef)._p_WS2CSPort
				), 
				new Connector(
				this,
		_p_toobotPort,
				_ci_robotRef,
				((_C_Robot) _ci_robotRef)._p_Robot2CSPort
				)
		);
	}
	Port _p_toWSPort = new Port("toWSPort", new _P_WorkstationProtocol());
	Port _p_toobotPort = new Port("toobotPort", new _P_RobotProtocol());
	Capsule _ci_workstationRef = new _C_Workstation(this);
	Capsule _ci_robotRef = new _C_Robot(this);
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
