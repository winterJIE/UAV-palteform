package radia;

import java.text.DecimalFormat;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;

public class TypeOneBuilder implements RadiaBuilder {

	private TypeOneRadia radia = new TypeOneRadia();
	private EnumSet<RangeEnum> re= EnumSet.allOf(RangeEnum.class);
	public TypeOneBuilder() {
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void buildAngle() {
		// TODO 自动生成的方法存根
		radia.setAngle(90);
	}

	@Override
	public void buildRange() {

		// TODO 自动生成的方法存根
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
			c -= 0.18;
		}
		radia.setRange(range);
	}

	@Override
	public Radia getResult() {
		// TODO 自动生成的方法存根
		return radia;
	}

}
