package org;

import radia.Radia;
import radia.RaidaDirector;
import radia.TypeOneRadia;
import radia.TypeTwoRadia;
import jbotsim.Clock;
import jbotsim.Node;
import jbotsim.Topology;
import jbotsim.event.ClockListener;
import jbotsim.ui.JViewer;

public class MovingNode extends Node implements ClockListener {
//	RaidaDirector director;
//	TypeOneRadia typeOne= director.constructTypeOne();
//	TypeTwoRadia typeTwo=director.constructTypeTwo();
//	Radia[] a= { typeOne, typeTwo };
//	Radia radia;

	public MovingNode() {
		// TODO 自动生成的构造函数存根
		 
		Clock.addClockListener(this, 10);
//		director = new RaidaDirector();
//		
	
		
	}

//	public void setRaida(int b) {
//		this.radia = a[b];
//	}
//
//	public Radia getRadia() {
//		return radia;
//	}
//
//	public int getAngle() {
//		return radia.getAngle();
//	}
//
//	public void setAngle(int angle) {
//		this.radia.setAngle(angle);
//		System.out.println("1");
//	}

	@Override
	public void onClock() {
		// TODO 自动生成的方法存根
		move(3);
		wrapLocation();

	}

}
