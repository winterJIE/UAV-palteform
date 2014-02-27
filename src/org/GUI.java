package org;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.omg.CORBA.Current;

import radia.RangeEnum;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import jbotsim.Clock;
import jbotsim.Link;
import jbotsim.Node;
import jbotsim.Topology;
import jbotsim.event.ClockListener;
import jbotsim.ui.JTopology;
import radia.*;

public class GUI extends MouseAdapter implements ClockListener, ActionListener {
	JFrame mainJFrame;
	JPanel myJPanel1, myJPanel2, myJPanel3, myJPanel4, myJPanel20, myJPanel21,
			myJPanel22, myJPanel23, myJPanel111, myJPanel121, myJPanel122,
			myJPanel123, myJPanel131, myJPanel31, myJPanel41, myJPanel42,
			myJPanel43, myJPanel44, myJPanel45, myJPanel46, myJPanel47;
	JSplitPane leftJsplitpane, rightJsplitpane, mainJsplitpane;
	Topology tp;
	JTopology jp;
	JLabel jlabel1, jlabel2, jlabel3, jlabel32, jlabel4, jlabel41, jlabel411,
			jlabel42, jlabel421, jlabel43, jlabel431, jlabel44, jlabel441,
			jlabel45, jlabel451, jlabel46, jlabel47, jlabel471, jlabel461,
			jlabelUAVnum, jlabelSamFre, jlabelShowLinks;
	JTextField numText, showDataText, rangeText, UAVText, samFreText;
	JButton button, previous, ok;
	Object[][] Info;
	JScrollPane JSpane, JSpane2, JSpane3;
	MyTable dataTable;
	MyTable2 dataTable2;
	private JTable jTable, jtable2;
	JComboBox jcombobox;
	private ArrayList<ConnectionInfo> connectionInfo = new ArrayList<ConnectionInfo>();
	int count = 0;
	int samFre;
	static int num = 8;
	static FileOutputStream fis;
	static BufferedWriter bw;
	final ArrayList<Coordinate> temp = new ArrayList<Coordinate>();
	MyCanvas palette;

	private Object[][] data = new Object[8][8];
	private static Object[][] data2 = new Object[8][8];

	@SuppressWarnings("serial")
	public class MyCanvas extends JPanel {
		public void paintComponent(Graphics g) {
			g.setColor(Color.red);
			g.fillRect(10, 10, 60, 60);
		}
	}

	public Object[][] getData2() {
		return this.data2;
	}

	public Object[][] getData() {
		return this.data;
	}

	@SuppressWarnings("null")
	public GUI() {
		// TODO 自动生成的构造函数存根
		mainJFrame = new JFrame("演示");
		myJPanel1 = new JPanel();
		myJPanel2 = new JPanel();
		myJPanel3 = new JPanel();
		myJPanel4 = new JPanel();
		myJPanel21 = new JPanel();
		myJPanel22 = new JPanel();
		myJPanel23 = new JPanel();
		myJPanel111 = new JPanel();
		myJPanel122 = new JPanel();
		myJPanel123 = new JPanel();
		myJPanel121 = new JPanel();
		myJPanel131 = new JPanel();

		tp = new Topology(800, 700);
		jp = new JTopology(tp);
		myJPanel1.add(jp);

		leftJsplitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true,
				myJPanel1, myJPanel2);
		leftJsplitpane.setEnabled(false);
		rightJsplitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true,
				myJPanel3, myJPanel4);
		rightJsplitpane.setEnabled(true);
		mainJsplitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true,
				leftJsplitpane, rightJsplitpane);
		mainJsplitpane.setEnabled(false);

		mainJFrame.add(mainJsplitpane);
		mainJFrame.setSize(1400, 900);
		mainJFrame.setVisible(true);

		leftJsplitpane.setDividerLocation(0.7);
		rightJsplitpane.setDividerLocation(0.7);
		mainJsplitpane.setDividerLocation(0.67);

		jlabelUAVnum = new JLabel("UAV数量 ：");
		UAVText = new JTextField("8");
		UAVText.setColumns(7);
		jlabelSamFre = new JLabel("采样频率 ：");
		samFreText = new JTextField("1秒");
		samFreText.setColumns(7);

		// button = new JButton("提交");
		// button.addActionListener(this);

		// jlabelShowLinks = new JLabel("请选择所要展示UAV连接矩阵的时刻：");
		// String[] sexs = { "boy", "girl", "0", "1", "2", "3", "4", "5", "6",
		// "7", "8" };
		// jcombobox = new JComboBox(sexs);
		// jcombobox.setEditable(true);
		// jcombobox.setAutoscrolls(true);
		// jcombobox.setLightWeightPopupEnabled(true);

		myJPanel2.setLayout(new BoxLayout(myJPanel2, BoxLayout.Y_AXIS));
		myJPanel21.setLayout(new FlowLayout());
		myJPanel21.add(jlabelUAVnum);
		myJPanel21.add(UAVText);
		myJPanel21.add(jlabelSamFre);
		myJPanel21.add(samFreText);
		// myJPanel21.add(button);

		// myJPanel23.add(jlabelShowLinks);
		// myJPanel23.add(jcombobox);
		myJPanel2.add(myJPanel21);
		myJPanel2.add(myJPanel23);
		myJPanel2.add(myJPanel22);

		myJPanel3.setLayout(new BoxLayout(myJPanel3, BoxLayout.Y_AXIS));
		jlabel4 = new JLabel("UAV网络连接示意图");
		// jlabel31 = new JLabel("To TypeOneRadia:");
		// jlabel311 = new
		// JLabel("橘色-1.0，绿色-0.82，蓝色-0.64，粉色-0.46，黄色-0.28，黑色-0.1");
		// jlabel32 = new JLabel("To TypeTwoRadia");
		// jlabel321 = new
		// JLabel("橘色-1.0，绿色-0.85，蓝色-0.70，粉色-0.55，黄色-0.40，黑色-0.25");

		myJPanel3.add(jlabel4);
		// myJPanel3.add(jlabel31);
		// myJPanel3.add(jlabel311);
		// myJPanel3.add(jlabel32);
		// myJPanel3.add(jlabel321);

		myJPanel31 = new JPanel();

		myJPanel3.add(myJPanel31);
		myJPanel3.revalidate();

		myJPanel31.setBackground(Color.WHITE);

		myJPanel31.addMouseListener(this);
		myJPanel4.setLayout(new BoxLayout(myJPanel4, BoxLayout.Y_AXIS));
		jlabel41 = new JLabel("UAV's num: ");

		jlabel42 = new JLabel("location.x: ");
		jlabel43 = new JLabel("location.y: ");
		jlabel44 = new JLabel("direction: ");
		jlabel47 = new JLabel("radia's num: ");
		jlabel45 = new JLabel("speed: ");
		jlabel46 = new JLabel("internet: ");

		jlabel411 = new JLabel("");
		jlabel421 = new JLabel("");
		jlabel431 = new JLabel("");
		jlabel441 = new JLabel("");
		jlabel451 = new JLabel("");
		jlabel461 = new JLabel("");
		jlabel471 = new JLabel("");

		myJPanel41 = new JPanel();
		myJPanel42 = new JPanel();
		myJPanel43 = new JPanel();
		myJPanel44 = new JPanel();
		myJPanel45 = new JPanel();
		myJPanel46 = new JPanel();
		myJPanel47 = new JPanel();

		myJPanel41.setLayout(new BorderLayout());
		myJPanel42.setLayout(new BorderLayout());
		myJPanel43.setLayout(new BorderLayout());
		myJPanel44.setLayout(new BorderLayout());
		myJPanel45.setLayout(new BorderLayout());
		myJPanel46.setLayout(new BorderLayout());
		myJPanel47.setLayout(new BorderLayout());

		myJPanel41.add(jlabel41, BorderLayout.WEST);
		myJPanel41.add(jlabel411, BorderLayout.CENTER);
		myJPanel42.add(jlabel42, BorderLayout.WEST);
		myJPanel42.add(jlabel421, BorderLayout.CENTER);
		myJPanel43.add(jlabel43, BorderLayout.WEST);
		myJPanel43.add(jlabel431, BorderLayout.CENTER);
		myJPanel44.add(jlabel44, BorderLayout.WEST);
		myJPanel44.add(jlabel441, BorderLayout.CENTER);
		myJPanel45.add(jlabel45, BorderLayout.WEST);
		myJPanel45.add(jlabel451, BorderLayout.CENTER);
		myJPanel46.add(jlabel46, BorderLayout.WEST);
		myJPanel46.add(jlabel461, BorderLayout.CENTER);
		myJPanel47.add(jlabel47, BorderLayout.WEST);
		myJPanel47.add(jlabel471, BorderLayout.CENTER);

		myJPanel4.add(myJPanel41);
		myJPanel4.add(myJPanel42);
		myJPanel4.add(myJPanel43);
		myJPanel4.add(myJPanel44);
		myJPanel4.add(myJPanel47);
		myJPanel4.add(myJPanel45);
		myJPanel4.add(myJPanel46);

		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		double[] rx = new double[num];
		double[] ry = new double[num];

		rx[0] = 0.5;
		ry[0] = 0.17;
		rx[1] = 0.24;
		ry[1] = 0.29;
		rx[2] = 0.17;
		ry[2] = 0.5;
		rx[3] = 0.24;
		ry[3] = 0.72;
		rx[4] = 0.5;
		ry[4] = 0.84;
		rx[5] = 0.77;
		ry[5] = 0.72;
		rx[6] = 0.84;
		ry[6] = 0.5;
		rx[7] = 0.77;
		ry[7] = 0.29;

		for (int i = 0; i < num; i++) {
			MovingNode mnode = new MovingNode();
			mnode.setNum(i);
			tp.addNode(mnode);

			System.out.println(i);
			mnode.setRaida(i % 2);
			mnode.setDirection(Math.random() * 2 * Math.PI);

			mnode.setSpeed(3);

			System.out.println(mnode.getRadia().toString());

			int x = (int) (rx[i] * myJPanel31.getWidth());
			int y = (int) (ry[i] * myJPanel31.getHeight());
			palette = new MyCanvas();
			palette.setBounds(x, y, 20, 20);
			myJPanel31.setLayout(null);
			myJPanel31.add(palette);
			myJPanel31.revalidate();
			myJPanel31.updateUI();
			Coordinate coordinate = new Coordinate();
			coordinate.setX(x);
			coordinate.setY(y);
			coordinate.setNum(i);
			temp.add(coordinate);

		}

		myJPanel20 = new JPanel();
		myJPanel2.add(myJPanel20);
		myJPanel20.setLayout(new BorderLayout());
		dataTable = new MyTable(this);
		jTable = new JTable();
		this.JSpane = new JScrollPane();
		this.JSpane.add(jTable);
		this.JSpane.setViewportView(jTable);
		this.jTable.setModel(dataTable);
		myJPanel20.add(BorderLayout.EAST, this.JSpane);
		dataTable.fireTableDataChanged();
		jTable.updateUI();

		dataTable2 = new MyTable2(this);
		this.jtable2 = new JTable();
		this.JSpane2 = new JScrollPane();
		this.JSpane2.add(jtable2);
		this.JSpane2.setViewportView(jtable2);
		this.jtable2.setModel(dataTable2);
		myJPanel20.add(BorderLayout.WEST, this.JSpane2);
		dataTable2.fireTableDataChanged();
		jtable2.updateUI();
		showDataText = new JTextField();
		showDataText.setColumns(10);
		jlabelShowLinks = new JLabel("所要显示网络连接信息的编号：");
		myJPanel22.add(jlabelShowLinks);
		myJPanel22.add(showDataText);
		ok = new JButton("ok");
		ok.addActionListener(this);
		myJPanel22.add(ok);

		// previous = new JButton("previous");

		// myJPanel22.add(next);

		myJPanel3.updateUI();
		Clock.addClockListener(this, 100);
		myJPanel1.updateUI();
		myJPanel2.updateUI();
		myJPanel3.updateUI();
		myJPanel4.updateUI();

	}

	@Override
	public void onClock() {
		// TODO 自动生成的方法存根

		Graphics g = myJPanel31.getGraphics();
		List<Link> links = tp.getLinks(true);
		List<Node> nodes = tp.getNodes();

		Iterator<Coordinate> itr = temp.iterator();
		g.setColor(Color.WHITE);
		float lineWidth = 2.0f;
		((Graphics2D) g).setStroke(new BasicStroke(lineWidth));
		while (itr.hasNext()) {
			Iterator<Coordinate> i = temp.iterator();
			Coordinate a = itr.next();
			while (i.hasNext()) {
				Coordinate b = i.next();
				int xa = a.getX();
				int ya = a.getY();
				int xb = b.getX();
				int yb = b.getY();
				g.drawLine(xa + 15, ya + 15, xb + 15, yb + 15);
			}
		}

		for (Link l : links) {
			Node s = l.source;
			Node d = l.destination;

			double distance = s.distance(d);

			double result = 0;
			result = this.getTwoNodesAngle(s, d);
			double angles = s.getAngle();
			double angle = angles / 180 * Math.PI;

			if (result <= angle / 2) {

				Coordinate source = new Coordinate();
				Coordinate destination = new Coordinate();
				Iterator<Coordinate> it = temp.iterator();
				while (it.hasNext()) {
					Coordinate coordinate = it.next();
					if (coordinate.getNum() == s.getNum()) {
						source = coordinate;
					}
					if (coordinate.getNum() == d.getNum()) {
						destination = coordinate;
					}

				}
				final int xs = source.getX();
				final int ys = source.getY();
				final int xd = destination.getX();
				final int yd = destination.getY();
				g.setColor(Color.BLACK);
				if (distance <= RangeEnum.FIRST.getValue()) {

					float lineWidth1 = 2.0f;
					((Graphics2D) g).setStroke(new BasicStroke(lineWidth1));

				} else if (distance <= RangeEnum.SECOND.getValue()
						&& distance > RangeEnum.FIRST.getValue()) {

					float lineWidth1 = 1.8f;
					((Graphics2D) g).setStroke(new BasicStroke(lineWidth1));

				} else if (distance <= RangeEnum.THIRD.getValue()
						&& distance > RangeEnum.SECOND.getValue()) {
					float lineWidth1 = 1.6f;
					((Graphics2D) g).setStroke(new BasicStroke(lineWidth1));
				} else if (distance <= RangeEnum.FOURTH.getValue()
						&& distance > RangeEnum.THIRD.getValue()) {
					float lineWidth1 = 1.4f;
					((Graphics2D) g).setStroke(new BasicStroke(lineWidth1));
				} else if (distance <= RangeEnum.FIFTH.getValue()
						&& distance > RangeEnum.FOURTH.getValue()) {
					float lineWidth1 = 1.2f;
					((Graphics2D) g).setStroke(new BasicStroke(lineWidth1));
				} else {
					float lineWidth1 = 1.0f;
					((Graphics2D) g).setStroke(new BasicStroke(lineWidth1));
				}
				g.drawLine(xs + 15, ys + 15, xd + 15, yd + 15);
				myJPanel31.setVisible(true);
				myJPanel31.setLayout(null);

			}

		}

		for (Node n : nodes) {
			int nNumber = n.getNum();
			for (Node m : nodes) {
				int mNumber = m.getNum();
				double angles = n.getAngle();
				double angle = angles / 180 * Math.PI;
				System.out.println("angel/2:" + angle / 2);
				double result = 0;
				double distance = n.distance(m);

				if (n == m) {
					data[nNumber][mNumber] = 1.0;

				} else {
					result = this.getTwoNodesAngle(n, m);
					System.out.println("result:" + result);
					if (result <= angle / 2) {
						System.out.println("result <= angle / 2");
						if (distance <= RangeEnum.FIRST.getValue()
								&& distance > 0) {
							data[nNumber][mNumber] = n.getRadia().getRange(
									RangeEnum.FIRST.getValue());
						} else if (distance <= RangeEnum.SECOND.getValue()
								&& distance > RangeEnum.FIRST.getValue()) {
							data[nNumber][mNumber] = n.getRadia().getRange(
									RangeEnum.SECOND.getValue());
						} else if (distance <= RangeEnum.THIRD.getValue()
								&& distance > RangeEnum.SECOND.getValue()) {
							data[nNumber][mNumber] = n.getRadia().getRange(
									RangeEnum.THIRD.getValue());
						} else if (distance <= RangeEnum.FOURTH.getValue()
								&& distance > RangeEnum.THIRD.getValue()) {
							data[nNumber][mNumber] = n.getRadia().getRange(
									RangeEnum.FOURTH.getValue());
						} else if (distance <= RangeEnum.FIFTH.getValue()
								&& distance > RangeEnum.FOURTH.getValue()) {
							data[nNumber][mNumber] = n.getRadia().getRange(
									RangeEnum.FIFTH.getValue());
						} else if (distance <= RangeEnum.SIXTH.getValue()
								&& distance > RangeEnum.FIFTH.getValue()) {
							data[nNumber][mNumber] = n.getRadia().getRange(
									RangeEnum.SIXTH.getValue());
						}

					} else {
						data[nNumber][mNumber] = 0.0;
					}
				}
			}

		}
		Object[][] a = new Object[num][num];
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				a[i][j] = data[i][j];
			}
		}
		this.dataTable.datachaged(this);
		ConnectionInfo ci = new ConnectionInfo();
		ci.setTime(count);
		ci.setData(a);
		this.connectionInfo.add(ci);
		count++;

	}

	private double getTwoNodesAngle(Node m, Node n) {
		double result = 0.0;
		double director = 0.0;
		double[] vectorM = new double[2];
		double[] vectorN = new double[2];
		double PI = Math.PI;
		if (m.equals(n)) {
			return 1.0;
		} else {
			vectorN[0] = n.getX() - m.getX();
			vectorN[1] = n.getY() - m.getY();
			director = 2 * Math.PI - m.getDirection();
			if (0.0 == director || (2 * PI) == director) {
				vectorM[0] = 1.0;
				vectorM[1] = 0.0;
			} else if (0.0 < director && (PI / 2) > director) {
				vectorM[0] = 1.0;
				vectorM[1] = Math.tan(director);
			} else if ((PI / 2) == director) {
				vectorM[0] = 0.0;
				vectorM[1] = 1.0;
			} else if ((PI / 2) < director && PI > director) {
				vectorM[0] = -1.0;
				vectorM[1] = -Math.tan(director);
			} else if ((PI) == director) {
				vectorM[0] = -1.0;
				vectorM[1] = 0.0;
			} else if ((PI) < director && (PI * 1.50) > director) {
				vectorM[0] = -1.0;
				vectorM[1] = -Math.tan(director);
			} else if ((PI * 1.50) == director) {
				vectorM[0] = 0.0;
				vectorM[1] = -1.0;
			} else if ((PI * 1.50) < director && (PI * 2) > director) {
				vectorM[0] = 1.0;
				vectorM[1] = Math.tan(director);
			}
		}
		double cosNM = (vectorN[0] * vectorM[0] + vectorN[1] * vectorM[1])
				/ (Math.sqrt((vectorN[0] * vectorN[0] + vectorN[1] * vectorN[1])) * Math
						.sqrt((vectorM[0] * vectorM[0] + vectorM[1]
								* vectorM[1])));
		result = Math.acos(cosNM);
		return result;
	}

	public void mouseClicked(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		Iterator<Coordinate> it = temp.iterator();
		while (it.hasNext()) {
			Coordinate coordinate = it.next();
			if (((x - coordinate.getX()) < 20) && ((x - coordinate.getX()) > 0)
					&& (0 < (y - coordinate.getY()))
					&& ((y - coordinate.getY()) < 20)) {
				int num = coordinate.getNum();
				for (Node n : tp.getNodes()) {
					if (n.getNum() == num) {
						int number = n.getNum();
						jlabel411.setText("" + number);
						jlabel421.setText("" + n.getX());
						jlabel431.setText("" + n.getY());
						jlabel441.setText("" + n.getDirection());
						jlabel451.setText("" + n.getSpeed());
						jlabel471.setText("" + n.getRadia().toString());
						List<Link> list = n.getOutLinks();
						Iterator<Link> ite = list.iterator();
						String string = "TheNumberofNode:";
						while (ite.hasNext()) {
							int nodenumber = ite.next().getOtherEndpoint(n)
									.getNum();
							String nodenumb = nodenumber + ",";
							string += nodenumb;

						}
						jlabel461.setText(string);
						break;
					}
				}
				break;
			}

		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

		new GUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if (e.getSource() == ok) {
			int a = Integer.valueOf(showDataText.getText());
			data2 = connectionInfo.get(a).getData();
			System.out.println(connectionInfo.get(a) + "size:"
					+ connectionInfo.size());
			this.dataTable2.datachaged(this);

		}
	}

}
