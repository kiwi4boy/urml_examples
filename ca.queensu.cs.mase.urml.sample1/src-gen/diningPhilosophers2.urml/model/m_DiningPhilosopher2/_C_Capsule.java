package model.m_DiningPhilosopher2;
import urml.runtime.*;
import java.util.*;
public class _C_Capsule extends Capsule {
	public _C_Capsule() {
		this(null);
	}
	
	public _C_Capsule(Capsule parent_) {
		this.parent = parent_;
		internalports = Arrays.asList(
		);
		
		externalports = Arrays.asList(
		);
		capsules = Arrays.asList(
			_ci_phil0, _ci_phil1, _ci_phil2, _ci_fork0, _ci_fork1, _ci_fork2
		);
		connectors = Arrays.asList(
				new Connector(
				_ci_phil0,
				((_C_Philosopher) _ci_phil0)._p_left,
				_ci_fork0,
				((_C_Fork) _ci_fork0)._p_right
				), 
				new Connector(
				_ci_fork0,
				((_C_Fork) _ci_fork0)._p_left,
				_ci_phil1,
				((_C_Philosopher) _ci_phil1)._p_right
				), 
				new Connector(
				_ci_phil1,
				((_C_Philosopher) _ci_phil1)._p_left,
				_ci_fork1,
				((_C_Fork) _ci_fork1)._p_right
				), 
				new Connector(
				_ci_fork1,
				((_C_Fork) _ci_fork1)._p_left,
				_ci_phil2,
				((_C_Philosopher) _ci_phil2)._p_right
				), 
				new Connector(
				_ci_phil2,
				((_C_Philosopher) _ci_phil2)._p_left,
				_ci_fork2,
				((_C_Fork) _ci_fork2)._p_right
				), 
				new Connector(
				_ci_fork2,
				((_C_Fork) _ci_fork2)._p_left,
				_ci_phil0,
				((_C_Philosopher) _ci_phil0)._p_right
				)
		);
	}
	Capsule _ci_phil0 = new _C_Philosopher(this);
	Capsule _ci_phil1 = new _C_Philosopher(this);
	Capsule _ci_phil2 = new _C_Philosopher(this);
	Capsule _ci_fork0 = new _C_Fork(this);
	Capsule _ci_fork1 = new _C_Fork(this);
	Capsule _ci_fork2 = new _C_Fork(this);
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
