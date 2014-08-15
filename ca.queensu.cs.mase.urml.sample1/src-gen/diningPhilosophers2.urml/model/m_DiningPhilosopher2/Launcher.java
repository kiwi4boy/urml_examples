package model.m_DiningPhilosopher2;
import urml.runtime.*;
public class Launcher {
	public static void main(String[] args) {
		Capsule root = new _C_Capsule();
		new Thread(root).start();
	}
}
