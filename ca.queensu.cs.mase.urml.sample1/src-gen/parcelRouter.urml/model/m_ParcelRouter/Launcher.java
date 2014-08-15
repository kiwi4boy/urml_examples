package model.m_ParcelRouter;
import urml.runtime.*;
public class Launcher {
	public static void main(String[] args) {
		Capsule root = new _C_ParcelRouter();
		new Thread(root).start();
	}
}
