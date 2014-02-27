package radia;

import java.text.DecimalFormat;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;

public class TypeTwoBuilder implements RadiaBuilder {

	private TypeTwoRadia radia = new TypeTwoRadia();
	// RangeEnum FIRST,SECOND,THIRD,FOURTH,FIFTH,SIXTH;
	EnumSet<RangeEnum> re = EnumSet.allOf(RangeEnum.class);

	public TypeTwoBuilder() {
		// TODO �Զ����ɵĹ��캯�����
	}

	@Override
	public void buildAngle() {
		// TODO �Զ����ɵķ������
		radia.setAngle(120);

	}

	@Override
	public void buildRange() {
		// TODO �Զ����ɵķ������
		HashMap<Integer, Double> range = new HashMap<Integer, Double>();
		double c = 1;
		int b;
		DecimalFormat df = new DecimalFormat("#.00");
		Iterator<RangeEnum> it = re.iterator();
		while (it.hasNext()) {
			b = it.next().getValue();
			String d = df.format(c);
			double h = Double.valueOf(d);
			range.put(b, h);
			c -= 0.15;
			
		}
		radia.setRange(range);
	}

	@Override
	public Radia getResult() {
		// TODO �Զ����ɵķ������
		return radia;
	}

}
