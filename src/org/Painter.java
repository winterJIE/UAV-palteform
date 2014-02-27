package org;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Painter extends JPanel {

	protected Graphics g;
	protected int x1;
	protected int x2;
	protected int y1;
	protected int y2;

	public Painter(int x1,int y1,int x2,int y2) {
		// TODO 自动生成的构造函数存根
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
		
	}
	public void paintComponent(Graphics g){
		g.setColor(Color.black);
		g.drawLine(x1, y1, x2, y2);
	}
	 
}
