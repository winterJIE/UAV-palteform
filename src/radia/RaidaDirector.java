package radia;

public class RaidaDirector {
	RadiaBuilder builder;
 
	public TypeOneRadia constructTypeOne(){
		builder=new TypeOneBuilder();
		builder.buildAngle();
		builder.buildRange();
		return (TypeOneRadia) builder.getResult();
		
		
	}
	public TypeTwoRadia constructTypeTwo(){
		builder=new TypeTwoBuilder();
		builder.buildAngle();
		builder.buildRange();
		return (TypeTwoRadia)builder.getResult();
	}
}
