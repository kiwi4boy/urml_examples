package model.m_ParcelRouter;
import java.time.*;
import java.util.*;
import urml.runtime.*;
public class _C_Chute extends Capsule {
	public _C_Chute() {
		this(null);
	}
	
	public _C_Chute(Capsule parent_) {
		this.parent = parent_;
		internalports = Arrays.asList(
		);
		
		externalports = Arrays.asList(
			_p_enter, _p_leave, _p_dummy, _p_toControl
		);
		capsules = Arrays.asList(
		);
		connectors = Arrays.asList(
			);
	}
	private int _a_storedParcelDestination = -1;
	private int _a_timeoutPeriod = 1000;
	MessagePort _p_enter = new MessagePort("enter", new _P_ParcelPassage());
	MessagePort _p_leave = new MessagePort("leave", new _P_ParcelPassage());
	MessagePort _p_dummy = new MessagePort("dummy", new _P_ParcelPassage());
	MessagePort _p_toControl = new MessagePort("toControl", new _P_ParcelPassage());
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
	private Transition _tran_pass1 = new Transition(
		"pass1",
		() -> {
			return true;
		},
		params -> {
			int _i_destination = ((Int) (params.get(0))).val;
		},
		Arrays.asList(
			new TriggerIn(
				_p_dummy, _P_ParcelPassage._s_sendParcel
			)
		),
		null
	);
	private Transition _tran_pass2 = new Transition(
		"pass2",
		() -> {
			return true;
		},
		params -> {
			int _i_destination = ((Int) (params.get(0))).val;
		},
		Arrays.asList(
			new TriggerIn(
				_p_dummy, _P_ParcelPassage._s_sendParcel
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
			_a_storedParcelDestination = _i_destination;System.out.println("logging to logger with: " + "Chute: received to destination " + _i_destination);
			Instant timeoutInstant = Instant.now().plusMillis(_a_timeoutPeriod);
			instants.put(_tp_timer, timeoutInstant);
			System.out.println("logging to logger with: " + "Chute: informing timer for " + _a_timeoutPeriod / 1000 + " seconds");
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
			passMessage(_p_leave, new Message(
				_p_leave, 
				_P_ParcelPassage._s_sendParcel,
				Arrays.asList(
					new Int(_a_storedParcelDestination)
				)));
			passMessage(_p_toControl, new Message(
				_p_toControl, 
				_P_ParcelPassage._s_sendParcel,
				Arrays.asList(
					new Int(_a_storedParcelDestination)
				)));
			System.out.println("logging to logger with: " + "Chute: sending parcel with id to " + _a_storedParcelDestination);
			_a_storedParcelDestination = -1;
		},
		Arrays.asList(
		),
		_tp_timer
	);
	public List<? extends Transition> findPossibleTrans() {
		switch (currentState.name) {
			case "empty":
				return Arrays.asList(_tran_pass1, _tran_parcelEnters);
			case "entered":
				return Arrays.asList(_tran_pass2, _tran_parcelLeaves);
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
				case "pass1":
					_tran_pass1.action.accept(params);
					currentState = _state_empty;
					return false;
				case "pass2":
					_tran_pass2.action.accept(params);
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
