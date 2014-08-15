package model.m_ParcelRouter;
import java.time.*;
import java.util.*;
import urml.runtime.*;
public class _C_ParcelRouter extends Capsule {
	public _C_ParcelRouter() {
		this(null);
	}
	
	public _C_ParcelRouter(Capsule parent_) {
		this.parent = parent_;
		internalports = Arrays.asList(
			_p_toStage0, _p_toStage1, _p_toStage2
		);
		
		externalports = Arrays.asList(
		);
		capsules = Arrays.asList(
			_ci_generator, _ci_stage0, _ci_stage1, _ci_stage2, _ci_bin0, _ci_bin1, _ci_bin2, _ci_bin3
		);
		connectors = Arrays.asList(
				new Connector(
				_ci_generator,
				((_C_Generator) _ci_generator)._p_gettingOut,
				_ci_stage0,
				((_C_Stage) _ci_stage0)._p_goingIn
				), 
				new Connector(
				_ci_stage0,
				((_C_Stage) _ci_stage0)._p_gettingOutRight,
				_ci_stage1,
				((_C_Stage) _ci_stage1)._p_goingIn
				), 
				new Connector(
				_ci_stage0,
				((_C_Stage) _ci_stage0)._p_gettingOutLeft,
				_ci_stage2,
				((_C_Stage) _ci_stage2)._p_goingIn
				), 
				new Connector(
				_ci_stage1,
				((_C_Stage) _ci_stage1)._p_gettingOutRight,
				_ci_bin0,
				((_C_Bin) _ci_bin0)._p_goingIn
				), 
				new Connector(
				_ci_stage1,
				((_C_Stage) _ci_stage1)._p_gettingOutLeft,
				_ci_bin1,
				((_C_Bin) _ci_bin1)._p_goingIn
				), 
				new Connector(
				_ci_stage2,
				((_C_Stage) _ci_stage2)._p_gettingOutRight,
				_ci_bin2,
				((_C_Bin) _ci_bin2)._p_goingIn
				), 
				new Connector(
				_ci_stage2,
				((_C_Stage) _ci_stage2)._p_gettingOutLeft,
				_ci_bin3,
				((_C_Bin) _ci_bin3)._p_goingIn
				), 
				new Connector(
				this,
		_p_toStage0,
				_ci_stage0,
				((_C_Stage) _ci_stage0)._p_fromTop
				), 
				new Connector(
				this,
		_p_toStage1,
				_ci_stage1,
				((_C_Stage) _ci_stage1)._p_fromTop
				), 
				new Connector(
				this,
		_p_toStage2,
				_ci_stage2,
				((_C_Stage) _ci_stage2)._p_fromTop
				)
		);
	}
	MessagePort _p_toStage0 = new MessagePort("toStage0", new _P_LevelNumberProtocol());
	MessagePort _p_toStage1 = new MessagePort("toStage1", new _P_LevelNumberProtocol());
	MessagePort _p_toStage2 = new MessagePort("toStage2", new _P_LevelNumberProtocol());
	Capsule _ci_generator = new _C_Generator(this);
	Capsule _ci_stage0 = new _C_Stage(this);
	Capsule _ci_stage1 = new _C_Stage(this);
	Capsule _ci_stage2 = new _C_Stage(this);
	Capsule _ci_bin0 = new _C_Bin(this);
	Capsule _ci_bin1 = new _C_Bin(this);
	Capsule _ci_bin2 = new _C_Bin(this);
	Capsule _ci_bin3 = new _C_Bin(this);
	private State _state_single = new State(
		"single",
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
			passMessage(_p_toStage0, new Message(
				_p_toStage0, 
				_P_LevelNumberProtocol._s_sendLevelNumber,
				Arrays.asList(
					new Int(1)
				)));
			passMessage(_p_toStage1, new Message(
				_p_toStage1, 
				_P_LevelNumberProtocol._s_sendLevelNumber,
				Arrays.asList(
					new Int(0)
				)));
			passMessage(_p_toStage2, new Message(
				_p_toStage2, 
				_P_LevelNumberProtocol._s_sendLevelNumber,
				Arrays.asList(
					new Int(0)
				)));
		},
		Arrays.asList(
		),
		null
	);
	public List<? extends Transition> findPossibleTrans() {
		switch (currentState.name) {
			case "single":
				return Arrays.asList();
			default:
				return new ArrayList<>();
		}
	}
	public void startInit() {
		synchronized (lock) {
			_tran_init.action.accept(new ArrayList<>());
			currentState = _state_single;
			_state_single.entry.run();
		}
	}
	public boolean transitionAndIfFinal(
			Transition t, List<? extends CommonObj> params)  {
		synchronized (lock) {
			switch (t.name) {
				default:
					return false;
			}
		}
	}
}
