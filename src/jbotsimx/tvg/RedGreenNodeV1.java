package jbotsimx.tvg;

import jbotsim.Clock;
import jbotsim.Node;
import jbotsim.event.ClockListener;

public class RedGreenNodeV1 extends Node implements ClockListener {
	public RedGreenNodeV1(){
		Clock.addClockListener(this, 10);
		System.out.println("fuck");
	}

	@Override
	public void onClock() {
		// TODO �Զ����ɵķ������
		if(super.getNeighbors().size()>0){
			super.setProperty("color", "green");
		} else {
			super.setProperty("color", "red");
		}
		
	}
	

}
