package model.m_ConsumerProducer;
import urml.runtime.*;
import java.util.*;
public class _C_ConsumerProducer extends Capsule {
	public _C_ConsumerProducer() {
		this(null);
	}
	
	public _C_ConsumerProducer(Capsule parent_) {
		this.parent = parent_;
		internalports = Arrays.asList(
		);
		
		externalports = Arrays.asList(
		);
		capsules = Arrays.asList(
			_ci_c, _ci_p, _ci_b
		);
		connectors = Arrays.asList(
				new Connector(
				_ci_c,
				((_C_Consumer) _ci_c)._p_toGet,
				_ci_b,
				((_C_BoundedBuffer) _ci_b)._p_consumer
				), 
				new Connector(
				_ci_p,
				((_C_Producer) _ci_p)._p_toPut,
				_ci_b,
				((_C_BoundedBuffer) _ci_b)._p_producer
				)
		);
	}
	Capsule _ci_c = new _C_Consumer(this);
	Capsule _ci_p = new _C_Producer(this);
	Capsule _ci_b = new _C_BoundedBuffer(this);
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
