package model.m_ParcelRouter;
import java.time.*;
import java.util.*;
import urml.runtime.*;
public class _C_Generator extends Capsule {
	public _C_Generator() {
		this(null);
	}
	
	public _C_Generator(Capsule parent_) {
		this.parent = parent_;
		internalports = Arrays.asList(
		);
		
		externalports = Arrays.asList(
			_p_gettingOut
		);
		capsules = Arrays.asList(
		);
		connectors = Arrays.asList(
			);
	}
	private int _a_timeoutPeriod = 10000;
	private int _a_destinationId = 1;
	MessagePort _p_gettingOut = new MessagePort("gettingOut", new _P_ParcelPassage());
	final TimerPort _tp_timer = new TimerPort();
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
			Instant timeoutInstant = Instant.now().plusMillis(_a_timeoutPeriod);
			instants.put(_tp_timer, timeoutInstant);
		},
		Arrays.asList(
		),
		null
	);
	private Transition _tran_sending = new Transition(
		"sending",
		() -> {
			return true;
		},
		params -> {
			Instant timeoutInstant = Instant.now().plusMillis(_a_timeoutPeriod);
			instants.put(_tp_timer, timeoutInstant);
			passMessage(_p_gettingOut, new Message(
				_p_gettingOut, 
				_P_ParcelPassage._s_sendParcel,
				Arrays.asList(
					new Int(_a_destinationId)
				)));
			System.out.println("logging to logger with: " + "generator sending out a parcel to bin " + _a_destinationId);
			_a_destinationId = _a_destinationId + 1;
		},
		Arrays.asList(
		),
		_tp_timer
	);
	public List<? extends Transition> findPossibleTrans() {
		switch (currentState.name) {
			case "single":
				return Arrays.asList(_tran_sending);
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
				case "sending":
					_tran_sending.action.accept(params);
					currentState = _state_single;
					return false;
				default:
					return false;
			}
		}
	}
}
