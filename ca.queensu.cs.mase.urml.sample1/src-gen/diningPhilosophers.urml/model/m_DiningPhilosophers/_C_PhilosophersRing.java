package model.m_DiningPhilosophers;
import urml.runtime.*;
import java.util.*;
public class _C_PhilosophersRing extends Capsule {
	public _C_PhilosophersRing() {
		this(null);
	}
	
	public _C_PhilosophersRing(Capsule parent_) {
		this.parent = parent_;
		internalports = Arrays.asList(
			_p_phil0signer, _p_phil1signer, _p_phil2signer, _p_phil3signer, _p_phil4signer
		);
		
		externalports = Arrays.asList(
		);
		capsules = Arrays.asList(
			_ci_phil0, _ci_phil1, _ci_phil2, _ci_phil3, _ci_phil4, _ci_fork0, _ci_fork1, _ci_fork2, _ci_fork3, _ci_fork4
		);
		connectors = Arrays.asList(
				new Connector(
				this,
		_p_phil0signer,
				_ci_phil0,
				((_C_Philosopher) _ci_phil0)._p_signer
				), 
				new Connector(
				this,
		_p_phil1signer,
				_ci_phil1,
				((_C_Philosopher) _ci_phil1)._p_signer
				), 
				new Connector(
				this,
		_p_phil2signer,
				_ci_phil2,
				((_C_Philosopher) _ci_phil2)._p_signer
				), 
				new Connector(
				this,
		_p_phil3signer,
				_ci_phil3,
				((_C_Philosopher) _ci_phil3)._p_signer
				), 
				new Connector(
				this,
		_p_phil4signer,
				_ci_phil4,
				((_C_Philosopher) _ci_phil4)._p_signer
				), 
				new Connector(
				_ci_phil0,
				((_C_Philosopher) _ci_phil0)._p_left,
				_ci_fork4,
				((_C_Fork) _ci_fork4)._p_right
				), 
				new Connector(
				_ci_fork4,
				((_C_Fork) _ci_fork4)._p_left,
				_ci_phil4,
				((_C_Philosopher) _ci_phil4)._p_right
				), 
				new Connector(
				_ci_phil4,
				((_C_Philosopher) _ci_phil4)._p_left,
				_ci_fork3,
				((_C_Fork) _ci_fork3)._p_right
				), 
				new Connector(
				_ci_fork3,
				((_C_Fork) _ci_fork3)._p_left,
				_ci_phil3,
				((_C_Philosopher) _ci_phil3)._p_right
				), 
				new Connector(
				_ci_phil3,
				((_C_Philosopher) _ci_phil3)._p_left,
				_ci_fork2,
				((_C_Fork) _ci_fork2)._p_right
				), 
				new Connector(
				_ci_fork2,
				((_C_Fork) _ci_fork2)._p_left,
				_ci_phil2,
				((_C_Philosopher) _ci_phil2)._p_right
				), 
				new Connector(
				_ci_phil2,
				((_C_Philosopher) _ci_phil2)._p_left,
				_ci_fork1,
				((_C_Fork) _ci_fork1)._p_right
				), 
				new Connector(
				_ci_fork1,
				((_C_Fork) _ci_fork1)._p_left,
				_ci_phil1,
				((_C_Philosopher) _ci_phil1)._p_right
				), 
				new Connector(
				_ci_phil1,
				((_C_Philosopher) _ci_phil1)._p_left,
				_ci_fork0,
				((_C_Fork) _ci_fork0)._p_right
				), 
				new Connector(
				_ci_fork0,
				((_C_Fork) _ci_fork0)._p_left,
				_ci_phil0,
				((_C_Philosopher) _ci_phil0)._p_right
				)
		);
	}
	Port _p_phil0signer = new Port("phil0signer", new _P_RingPhilosopherProtocol());
	Port _p_phil1signer = new Port("phil1signer", new _P_RingPhilosopherProtocol());
	Port _p_phil2signer = new Port("phil2signer", new _P_RingPhilosopherProtocol());
	Port _p_phil3signer = new Port("phil3signer", new _P_RingPhilosopherProtocol());
	Port _p_phil4signer = new Port("phil4signer", new _P_RingPhilosopherProtocol());
	Capsule _ci_phil0 = new _C_Philosopher(this);
	Capsule _ci_phil1 = new _C_Philosopher(this);
	Capsule _ci_phil2 = new _C_Philosopher(this);
	Capsule _ci_phil3 = new _C_Philosopher(this);
	Capsule _ci_phil4 = new _C_Philosopher(this);
	Capsule _ci_fork0 = new _C_Fork(this);
	Capsule _ci_fork1 = new _C_Fork(this);
	Capsule _ci_fork2 = new _C_Fork(this);
	Capsule _ci_fork3 = new _C_Fork(this);
	Capsule _ci_fork4 = new _C_Fork(this);
	private State _state_one = new State(
		"one",
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
			passMessage(_p_phil0signer, new Message(
				_p_phil0signer, 
				_P_RingPhilosopherProtocol._s_sign,
				Arrays.asList(
					new Int(0)
				)));
			passMessage(_p_phil1signer, new Message(
				_p_phil1signer, 
				_P_RingPhilosopherProtocol._s_sign,
				Arrays.asList(
					new Int(1)
				)));
			passMessage(_p_phil2signer, new Message(
				_p_phil2signer, 
				_P_RingPhilosopherProtocol._s_sign,
				Arrays.asList(
					new Int(2)
				)));
			passMessage(_p_phil3signer, new Message(
				_p_phil3signer, 
				_P_RingPhilosopherProtocol._s_sign,
				Arrays.asList(
					new Int(3)
				)));
			passMessage(_p_phil4signer, new Message(
				_p_phil4signer, 
				_P_RingPhilosopherProtocol._s_sign,
				Arrays.asList(
					new Int(4)
				)));
		},
		Arrays.asList(
		)
	);
	public List<? extends Transition> findPossibleTrans() {
		switch (currentState.name) {
			case "one":
				return Arrays.asList();
			default:
				return new ArrayList<>();
		}
	}
	public void startInit() {
			_tran_init.action.accept(new ArrayList<>());
			currentState = _state_one;
			_state_one.entry.run();
	}
	public boolean transitionAndIfFinal(
			Transition t, List<? extends CommonObj> params)  {
			switch (t.name) {
				default:
					return false;
			}
	}
}
