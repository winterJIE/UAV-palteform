package org;

public class Coordinate {

	int x;
	int y;
	int num;
	public Coordinate(){
		
	}
	public Coordinate(int x,int y,int num) {
		// TODO 自动生成的构造函数存根
		this.x=x;
		this.y=y;
		this.num=num;
	}
	public void setX(int x){
		this.x=x;
	}
	public int getX(){
		return x;
	}

	public void setY(int y){
		this.y=y;
	}
	public int getY(){
		return y;
		
	}
	public void setNum(int num){
		this.num=num;
	}
	public int getNum(){
		return num;
	}
	 
}
