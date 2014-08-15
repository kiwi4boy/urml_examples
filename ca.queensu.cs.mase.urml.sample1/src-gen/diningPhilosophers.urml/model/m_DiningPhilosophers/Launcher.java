package model.m_DiningPhilosophers;
import urml.runtime.*;
public class Launcher {
	public static void main(String[] args) {
		Capsule root = new _C_PhilosophersRing();
		new Thread(root).start();
	}
}
