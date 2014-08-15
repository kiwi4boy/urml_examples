package model.m_ReadersWriters;
import urml.runtime.*;
public class Launcher {
	public static void main(String[] args) {
		Capsule root = new _C_Top();
		new Thread(root).start();
	}
}
