package model.m_ConsumerProducer;
import urml.runtime.*;
public class Launcher {
	public static void main(String[] args) {
		Capsule root = new _C_ConsumerProducer();
		new Thread(root).start();
	}
}
