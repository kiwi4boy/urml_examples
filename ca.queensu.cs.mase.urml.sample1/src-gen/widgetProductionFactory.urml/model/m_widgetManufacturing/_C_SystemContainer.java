package model.m_widgetManufacturing;
import urml.runtime.*;
import java.util.*;
public class _C_SystemContainer extends Capsule {
	public _C_SystemContainer() {
		this(null);
	}
	
	public _C_SystemContainer(Capsule parent_) {
		this.parent = parent_;
		internalports = Arrays.asList(
		);
		
		externalports = Arrays.asList(
		);
		capsules = Arrays.asList(
			_ci_controlSoftware, _ci_productionLine
		);
		connectors = Arrays.asList(
				new Connector(
				_ci_controlSoftware,
				((_C_ControlSoftware) _ci_controlSoftware)._p_CS2RobotPort,
				_ci_productionLine,
				((_C_ProductionLine) _ci_productionLine)._p_toobotPort
				), 
				new Connector(
				_ci_controlSoftware,
				((_C_ControlSoftware) _ci_controlSoftware)._p_CS2WSPort,
				_ci_productionLine,
				((_C_ProductionLine) _ci_productionLine)._p_toWSPort
				)
		);
	}
	Capsule _ci_controlSoftware = new _C_ControlSoftware(this);
	Capsule _ci_productionLine = new _C_ProductionLine(this);
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
