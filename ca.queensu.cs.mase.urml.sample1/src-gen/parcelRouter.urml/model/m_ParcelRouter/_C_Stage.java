package model.m_ParcelRouter;
import java.time.*;
import java.util.*;
import urml.runtime.*;
public class _C_Stage extends Capsule {
	public _C_Stage() {
		this(null);
	}
	
	public _C_Stage(Capsule parent_) {
		this.parent = parent_;
		internalports = Arrays.asList(
			_p_toSensorController
		);
		
		externalports = Arrays.asList(
			_p_goingIn, _p_gettingOutRight, _p_gettingOutLeft, _p_fromTop
		);
		capsules = Arrays.asList(
			_ci_chute0, _ci_chute1, _ci_sensorCntl, _ci_switch
		);
		connectors = Arrays.asList(
				new Connector(
				this,
		_p_goingIn,
				_ci_chute0,
				((_C_Chute) _ci_chute0)._p_enter
				), 
				new Connector(
				_ci_chute0,
				((_C_Chute) _ci_chute0)._p_leave,
				_ci_chute1,
				((_C_Chute) _ci_chute1)._p_enter
				), 
				new Connector(
				_ci_chute0,
				((_C_Chute) _ci_chute0)._p_toControl,
				_ci_sensorCntl,
				((_C_SensorController) _ci_sensorCntl)._p_sense
				), 
				new Connector(
				_ci_chute1,
				((_C_Chute) _ci_chute1)._p_leave,
				_ci_switch,
				((_C_Switch) _ci_switch)._p_enter
				), 
				new Connector(
				_ci_chute1,
				((_C_Chute) _ci_chute1)._p_toControl,
				_ci_chute1,
				((_C_Chute) _ci_chute1)._p_dummy
				), 
				new Connector(
				_ci_sensorCntl,
				((_C_SensorController) _ci_sensorCntl)._p_setSwitch,
				_ci_switch,
				((_C_Switch) _ci_switch)._p_setSwitch
				), 
				new Connector(
				_ci_switch,
				((_C_Switch) _ci_switch)._p_leaveLeft,
				this,
		_p_gettingOutLeft
				), 
				new Connector(
				_ci_switch,
				((_C_Switch) _ci_switch)._p_leaveRight,
				this,
		_p_gettingOutRight
				), 
				new Connector(
				this,
		_p_toSensorController,
				_ci_sensorCntl,
				((_C_SensorController) _ci_sensorCntl)._p_fromStage
				)
		);
	}
	private int _a_level;
	MessagePort _p_toSensorController = new MessagePort("toSensorController", new _P_LevelNumberProtocol());
	MessagePort _p_goingIn = new MessagePort("goingIn", new _P_ParcelPassage());
	MessagePort _p_gettingOutRight = new MessagePort("gettingOutRight", new _P_ParcelPassage());
	MessagePort _p_gettingOutLeft = new MessagePort("gettingOutLeft", new _P_ParcelPassage());
	MessagePort _p_fromTop = new MessagePort("fromTop", new _P_LevelNumberProtocol());
	Capsule _ci_chute0 = new _C_Chute(this);
	Capsule _ci_chute1 = new _C_Chute(this);
	Capsule _ci_sensorCntl = new _C_SensorController(this);
	Capsule _ci_switch = new _C_Switch(this);
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
		},
		Arrays.asList(
		),
		null
	);
	private Transition _tran_setLevelNumber = new Transition(
		"setLevelNumber",
		() -> {
			return true;
		},
		params -> {
			int _i_levelNumber = ((Int) (params.get(0))).val;
			_a_level = _i_levelNumber;passMessage(_p_toSensorController, new Message(
				_p_toSensorController, 
				_P_LevelNumberProtocol._s_sendLevelNumber,
				Arrays.asList(
					new Int(_i_levelNumber)
				)));
		},
		Arrays.asList(
			new TriggerIn(
				_p_fromTop, _P_LevelNumberProtocol._s_sendLevelNumber
			)
		),
		null
	);
	public List<? extends Transition> findPossibleTrans() {
		switch (currentState.name) {
			case "single":
				return Arrays.asList(_tran_setLevelNumber);
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
				case "setLevelNumber":
					_tran_setLevelNumber.action.accept(params);
					currentState = _state_single;
					return false;
				default:
					return false;
			}
		}
	}
}
