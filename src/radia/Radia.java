package radia;

import java.util.HashMap;

public  abstract class Radia {

	private String type;
	private int angle=0;
	private HashMap<Integer,Double> range=new HashMap<Integer,Double>();
	public Radia() {
		// TODO 自动生成的构造函数存根
	}
	public void setType(String type){
		this.type=type;
	}
	public String getType(){
		return type;
	}
	public void setAngle(int angle){
		this.angle=angle;
	}
	public int getAngle(){
		return angle;
	}
	public void setRange(HashMap<Integer,Double> range){
		this.range=range;
	}
	public HashMap<Integer,Double> getRange(){
		return range;
	}
	public void setRange(int a,double b){
		this.range.put(a, b);
	}
	public Double getRange(int a){
		return this.range.get(a);
	}
	public abstract String toString();

	 
}
