package model.m_handshake;
import urml.runtime.*;
public class Launcher {
	public static void main(String[] args) {
		Capsule root = new _C_Handshake();
		new Thread(root).start();
	}
}
