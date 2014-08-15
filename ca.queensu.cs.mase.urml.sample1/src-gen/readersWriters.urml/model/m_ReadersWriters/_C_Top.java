package model.m_ReadersWriters;
import urml.runtime.*;
import java.util.*;
public class _C_Top extends Capsule {
	public _C_Top() {
		this(null);
	}
	
	public _C_Top(Capsule parent_) {
		this.parent = parent_;
		internalports = Arrays.asList(
		);
		
		externalports = Arrays.asList(
		);
		capsules = Arrays.asList(
			_ci_r0, _ci_r1, _ci_r2, _ci_w0, _ci_w1, _ci_c, _ci_db
		);
		connectors = Arrays.asList(
				new Connector(
				_ci_r0,
				((_C_Reader) _ci_r0)._p_con,
				_ci_c,
				((_C_Controller) _ci_c)._p_r0
				), 
				new Connector(
				_ci_r1,
				((_C_Reader) _ci_r1)._p_con,
				_ci_c,
				((_C_Controller) _ci_c)._p_r1
				), 
				new Connector(
				_ci_r2,
				((_C_Reader) _ci_r2)._p_con,
				_ci_c,
				((_C_Controller) _ci_c)._p_r2
				), 
				new Connector(
				_ci_w0,
				((_C_Writer) _ci_w0)._p_con,
				_ci_c,
				((_C_Controller) _ci_c)._p_w0
				), 
				new Connector(
				_ci_w1,
				((_C_Writer) _ci_w1)._p_con,
				_ci_c,
				((_C_Controller) _ci_c)._p_w1
				), 
				new Connector(
				_ci_r0,
				((_C_Reader) _ci_r0)._p_read,
				_ci_db,
				((_C_Database) _ci_db)._p_read
				), 
				new Connector(
				_ci_r1,
				((_C_Reader) _ci_r1)._p_read,
				_ci_db,
				((_C_Database) _ci_db)._p_read
				), 
				new Connector(
				_ci_r2,
				((_C_Reader) _ci_r2)._p_read,
				_ci_db,
				((_C_Database) _ci_db)._p_read
				), 
				new Connector(
				_ci_w0,
				((_C_Writer) _ci_w0)._p_write,
				_ci_db,
				((_C_Database) _ci_db)._p_write
				), 
				new Connector(
				_ci_w1,
				((_C_Writer) _ci_w1)._p_write,
				_ci_db,
				((_C_Database) _ci_db)._p_write
				)
		);
	}
	Capsule _ci_r0 = new _C_Reader(this);
	Capsule _ci_r1 = new _C_Reader(this);
	Capsule _ci_r2 = new _C_Reader(this);
	Capsule _ci_w0 = new _C_Writer(this);
	Capsule _ci_w1 = new _C_Writer(this);
	Capsule _ci_c = new _C_Controller(this);
	Capsule _ci_db = new _C_Database(this);
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
