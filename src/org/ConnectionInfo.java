package org;

public class ConnectionInfo {

	private int time;
	private Object[][] data;
	public ConnectionInfo( ) {
		// TODO �Զ����ɵĹ��캯�����
		 
	}
	public void setTime(int time){
		this.time=time;
	}
	public int getTime(){
		return time;
	}
	public void setData(Object[][] data){
		this.data=data;
	}
	public Object[][] getData(){
		return data;
	}
	public Object getObjectData(int i,int j){
		return this.data[i][j];
	}


}
