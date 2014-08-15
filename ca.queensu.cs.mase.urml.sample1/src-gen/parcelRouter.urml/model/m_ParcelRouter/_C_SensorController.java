package model.m_ParcelRouter;
import java.time.*;
import java.util.*;
import urml.runtime.*;
public class _C_SensorController extends Capsule {
	public _C_SensorController() {
		this(null);
	}
	
	public _C_SensorController(Capsule parent_) {
		this.parent = parent_;
		internalports = Arrays.asList(
		);
		
		externalports = Arrays.asList(
			_p_sense, _p_setSwitch, _p_fromStage
		);
		capsules = Arrays.asList(
		);
		connectors = Arrays.asList(
			);
	}
	private int _a_level;
	public int _f_leftShiftAndBitmask(int _l_destination, int _l_level) {
		int _l_counter = 0;
		while (_l_counter < _l_level) {
			_l_destination = _l_destination / 2;
			_l_counter = _l_counter + 1;
		}
		if (_l_destination % 2 == 1) {
			return 1;
		}else {
			return 0;
		}
	}
	MessagePort _p_sense = new MessagePort("sense", new _P_ParcelPassage());
	MessagePort _p_setSwitch = new MessagePort("setSwitch", new _P_SensorProtocol());
	MessagePort _p_fromStage = new MessagePort("fromStage", new _P_LevelNumberProtocol());
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
			_a_level = _i_levelNumber;
		},
		Arrays.asList(
			new TriggerIn(
				_p_fromStage, _P_LevelNumberProtocol._s_sendLevelNumber
			)
		),
		null
	);
	private Transition _tran_senseParcel = new Transition(
		"senseParcel",
		() -> {
			return true;
		},
		params -> {
			int _i_destination = ((Int) (params.get(0))).val;
			passMessage(_p_setSwitch, new Message(
				_p_setSwitch, 
				_P_SensorProtocol._s_sendDirection,
				Arrays.asList(
					new Bool(_f_leftShiftAndBitmask(_i_destination,_a_level) != 0)
				)));
		},
		Arrays.asList(
			new TriggerIn(
				_p_sense, _P_ParcelPassage._s_sendParcel
			)
		),
		null
	);
	public List<? extends Transition> findPossibleTrans() {
		switch (currentState.name) {
			case "single":
				return Arrays.asList(_tran_setLevelNumber, _tran_senseParcel);
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
				case "senseParcel":
					_tran_senseParcel.action.accept(params);
					currentState = _state_single;
					return false;
				default:
					return false;
			}
		}
	}
}
