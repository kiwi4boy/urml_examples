package model.m_ParcelRouter;
import java.time.*;
import java.util.*;
import urml.runtime.*;
public class _C_Bin extends Capsule {
	public _C_Bin() {
		this(null);
	}
	
	public _C_Bin(Capsule parent_) {
		this.parent = parent_;
		internalports = Arrays.asList(
		);
		
		externalports = Arrays.asList(
			_p_goingIn
		);
		capsules = Arrays.asList(
		);
		connectors = Arrays.asList(
			);
	}
	private int _a_numOfParcels = 0;
	MessagePort _p_goingIn = new MessagePort("goingIn", new _P_ParcelPassage());
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
	private Transition _tran_sending = new Transition(
		"sending",
		() -> {
			return true;
		},
		params -> {
			int _i_designatedBin = ((Int) (params.get(0))).val;
			_a_numOfParcels = _a_numOfParcels + 1;System.out.println("logging to logger with: " + "Bin: a parcel with id " + _i_designatedBin + " has entered bin");
		},
		Arrays.asList(
			new TriggerIn(
				_p_goingIn, _P_ParcelPassage._s_sendParcel
			)
		),
		null
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
