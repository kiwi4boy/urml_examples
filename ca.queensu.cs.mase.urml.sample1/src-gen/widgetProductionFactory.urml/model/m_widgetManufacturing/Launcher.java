package model.m_widgetManufacturing;
import urml.runtime.*;
public class Launcher {
	public static void main(String[] args) {
		Capsule root = new _C_SystemContainer();
		new Thread(root).start();
	}
}
