package model.m_ParcelRouter;
import java.time.*;
import java.util.*;
import urml.runtime.*;
public class _C_Switch extends Capsule {
	public _C_Switch() {
		this(null);
	}
	
	public _C_Switch(Capsule parent_) {
		this.parent = parent_;
		internalports = Arrays.asList(
		);
		
		externalports = Arrays.asList(
			_p_setSwitch, _p_enter, _p_leaveLeft, _p_leaveRight
		);
		capsules = Arrays.asList(
		);
		connectors = Arrays.asList(
			);
	}
	private boolean _a_leftNotRight = true;
	private int _a_timeoutPeriod = 2000;
	private int _a_storedParcelDestination = -1;
	MessagePort _p_setSwitch = new MessagePort("setSwitch", new _P_SensorProtocol());
	MessagePort _p_enter = new MessagePort("enter", new _P_ParcelPassage());
	MessagePort _p_leaveLeft = new MessagePort("leaveLeft", new _P_ParcelPassage());
	MessagePort _p_leaveRight = new MessagePort("leaveRight", new _P_ParcelPassage());
	final TimerPort _tp_timer = new TimerPort();
	private State _state_empty = new State(
		"empty",
		() -> {
		},
		() -> {
		});
	private State _state_entered = new State(
		"entered",
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
	private Transition _tran_setDirectionWhileEmpty = new Transition(
		"setDirectionWhileEmpty",
		() -> {
			return true;
		},
		params -> {
			boolean _i_direction = ((Bool) (params.get(0))).val;
			_a_leftNotRight = _i_direction;
		},
		Arrays.asList(
			new TriggerIn(
				_p_setSwitch, _P_SensorProtocol._s_sendDirection
			)
		),
		null
	);
	private Transition _tran_setDirectionWhileEntered = new Transition(
		"setDirectionWhileEntered",
		() -> {
			return true;
		},
		params -> {
			boolean _i_direction = ((Bool) (params.get(0))).val;
			_a_leftNotRight = _i_direction;
		},
		Arrays.asList(
			new TriggerIn(
				_p_setSwitch, _P_SensorProtocol._s_sendDirection
			)
		),
		null
	);
	private Transition _tran_parcelEnters = new Transition(
		"parcelEnters",
		() -> {
			return true;
		},
		params -> {
			int _i_destination = ((Int) (params.get(0))).val;
			_a_storedParcelDestination = _i_destination;System.out.println("logging to logger with: " + "Switch: parcel entered, sending to " + _i_destination);
			Instant timeoutInstant = Instant.now().plusMillis(_a_timeoutPeriod);
			instants.put(_tp_timer, timeoutInstant);
		},
		Arrays.asList(
			new TriggerIn(
				_p_enter, _P_ParcelPassage._s_sendParcel
			)
		),
		null
	);
	private Transition _tran_parcelLeaves = new Transition(
		"parcelLeaves",
		() -> {
			return true;
		},
		params -> {
			if (_a_leftNotRight) {
				passMessage(_p_leaveLeft, new Message(
					_p_leaveLeft, 
					_P_ParcelPassage._s_sendParcel,
					Arrays.asList(
						new Int(_a_storedParcelDestination)
					)));
			}  else {
				passMessage(_p_leaveRight, new Message(
					_p_leaveRight, 
					_P_ParcelPassage._s_sendParcel,
					Arrays.asList(
						new Int(_a_storedParcelDestination)
					)));
			}
		},
		Arrays.asList(
		),
		_tp_timer
	);
	public List<? extends Transition> findPossibleTrans() {
		switch (currentState.name) {
			case "empty":
				return Arrays.asList(_tran_setDirectionWhileEmpty, _tran_parcelEnters);
			case "entered":
				return Arrays.asList(_tran_setDirectionWhileEntered, _tran_parcelLeaves);
			default:
				return new ArrayList<>();
		}
	}
	public void startInit() {
		synchronized (lock) {
			_tran_init.action.accept(new ArrayList<>());
			currentState = _state_empty;
			_state_empty.entry.run();
		}
	}
	public boolean transitionAndIfFinal(
			Transition t, List<? extends CommonObj> params)  {
		synchronized (lock) {
			switch (t.name) {
				case "setDirectionWhileEmpty":
					_tran_setDirectionWhileEmpty.action.accept(params);
					currentState = _state_empty;
					return false;
				case "setDirectionWhileEntered":
					_tran_setDirectionWhileEntered.action.accept(params);
					currentState = _state_entered;
					return false;
				case "parcelEnters":
					_state_empty.exit.run();
					_tran_parcelEnters.action.accept(params);
					_state_entered.entry.run();
					currentState = _state_entered;
					return false;
				case "parcelLeaves":
					_state_entered.exit.run();
					_tran_parcelLeaves.action.accept(params);
					_state_empty.entry.run();
					currentState = _state_empty;
					return false;
				default:
					return false;
			}
		}
	}
}
