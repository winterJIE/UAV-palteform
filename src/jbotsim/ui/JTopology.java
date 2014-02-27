/*
 * This file is part of JBotSim.
 * 
 *    JBotSim is free software: you can redistribute it and/or modify it
 *    under the terms of the GNU Lesser General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *  
 *    Authors:
 *    Arnaud Casteigts		<casteig@site.uottawa.ca>
 */
package jbotsim.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import org.MovingNode;

import radia.RangeEnum;

import jbotsim.Link;
import jbotsim.Node;
import jbotsim.Topology;
import jbotsim._Properties;
import jbotsim.event.ConnectivityListener;
import jbotsim.event.MovementListener;
import jbotsim.event.PropertyListener;
import jbotsim.event.TopologyListener;

@SuppressWarnings("serial")
public class JTopology extends JPanel {
	protected ArrayList<ActionListener> actionListeners = new ArrayList<ActionListener>();
	protected ArrayList<String> actionCommands = new ArrayList<String>();
	protected Topology topo;
	protected JTopology jtopo = this;
	protected EventHandler handler = new EventHandler();
	protected boolean showDrawings = true;

	/**
	 * Creates a new JTopology with default topology.
	 */
	public JTopology() {
		this(new Topology());
	}

	/**
	 * Creates a new JTopology for the specified topology.
	 * 
	 * @param topo
	 *            The topology to encapsulate.
	 */
	public JTopology(Topology topo) {
		this.topo = topo;
		topo.addConnectivityListener(handler);
		topo.addTopologyListener(handler);
		topo.addMovementListener(handler);
		super.setLayout(null);
		super.setBackground(new Color(180, 180, 180));
		super.addMouseListener(handler);
		super.setPreferredSize(topo.getDimensions());
		for (Node n : topo.getNodes())
			handler.nodeAdded(n);
		topo.setProperty("popupRunning", false);
	}

	/**
	 * Registers the specified action listener to this JTopology.
	 * 
	 * @param al
	 *            The listener to add.
	 */
	public void addActionListener(ActionListener al) {
		actionListeners.add(al);
	}

	/**
	 * Unregisters the specified action listener to this JTopology.
	 * 
	 * @param al
	 *            The listener to remove.
	 */
	public void removeActionListener(ActionListener al) {
		actionListeners.remove(al);
	}

	/**
	 * Adds the specified action command to this JTopology.
	 * 
	 * @param command
	 *            The command name to add.
	 */
	public void addActionCommand(String command) {
		actionCommands.add(command);
	}

	/**
	 * Removes the specified action command from this JTopology.
	 * 
	 * @param command
	 *            The command name to remove.
	 */
	public void removeActionCommand(String command) {
		actionCommands.remove(command);
	}

	/**
	 * Disables the drawing of links and sensing radius (if any).
	 */
	public void disableDrawings() {
		showDrawings = false;
	}

	/**
	 * Paints this JTopology on the specified graphics (not supposed to be used
	 * explicitly).
	 */
	public void paint(Graphics g) {

		super.paint(g);
		// if (showDrawings){
		// Graphics2D g2d=(Graphics2D)g;
		// for(Link l : topo.getLinks())
		// paintLink(g2d, l);
		// for(Node n : topo.getNodes()){
		// double sR=n.getSensingRange();
		// if(sR>0){
		// g2d.setColor(Color.gray);
		// g2d.drawOval((int)n.getX()-(int)sR, (int)n.getY()-(int)sR, 2*(int)sR,
		// 2*(int)sR);
		// }
		// }
		// }
		double x1 = 0, y1=0, x2=0, y2=0;

		for (Node n : topo.getNodes()) {
			double x = n.getX();
			double y = n.getY();
			int a = RangeEnum.FIRST.getValue();
			int b = RangeEnum.SECOND.getValue();
			int c = RangeEnum.THIRD.getValue();
			int d = RangeEnum.FOURTH.getValue();
			int e = RangeEnum.FIFTH.getValue();
			int f = RangeEnum.SIXTH.getValue();
			int angle = n.getAngle();
//			System.out.println(angle);
			double direction = 2 * Math.PI - n.getDirection();
			int directionJiaodu = (int) ((direction / (Math.PI * 2)) * 360);
			int startAngle = directionJiaodu - angle / 2;
			g.setColor(Color.black);
			g.drawString(String.valueOf(n.getNum()), (int) x, (int) y);
			g.drawArc((int) (x - a), (int) (y - a), 2 * a, 2 * a, startAngle,
					angle);
			g.drawArc((int) (x - b), (int) (y - b), 2 * b, 2 * b, startAngle,
					angle);
			g.drawArc((int) (x - c), (int) (y - c), 2 * c, 2 * c, startAngle,
					angle);
			g.drawArc((int) (x - d), (int) (y - d), 2 * d, 2 * d, startAngle,
					angle);
			g.drawArc((int) (x - e), (int) (y - e), 2 * e, 2 * e, startAngle,
					angle);
			g.drawArc((int) (x - f), (int) (y - f), 2 * f, 2 * f, startAngle,
					angle);
//			if (angle == 90) {
//				System.out.println("90");
//				if (directionJiaodu <= 45 && directionJiaodu > 0) {
//					System.out.println("0-45");
//					x1 = f * Math.cos(directionJiaodu + angle / 2)+x;
//					y1 = f * Math.sin(directionJiaodu + angle / 2)+y;
//					x2 = f * Math.cos(angle / 2 - directionJiaodu)+x;
//					y2 = -f * Math.sin(angle / 2 - directionJiaodu)+y;
//				} else if (directionJiaodu <= 360 && directionJiaodu > 315) {
//					System.out.println("315-360");
//					x1 = f * Math.cos(360 - directionJiaodu + angle / 2)+x;
//					y1 = -f * Math.sin(360 - directionJiaodu + angle / 2)+y;
//					x2 = f * Math.cos(angle / 2 - 360 + directionJiaodu)+x;
//					y2 = -f * Math.sin(angle - 360 + directionJiaodu)+y;
//				} else if (direction <= 135 && direction > 45) {
//					System.out.println("45-135");
//					x1 = f * Math.cos(directionJiaodu - angle / 2)+x;
//					y1 = f * Math.sin(directionJiaodu - angle / 2)+y;
//					x2 = -f * Math.cos(180 - directionJiaodu - angle / 2)+x;
//					y2 = f * Math.sin(180 - directionJiaodu - angle / 2)+y;
//				} else if (directionJiaodu <= 275 && directionJiaodu > 135) {
//					System.out.println("135-275");
//					x1 = -f * Math.sin(directionJiaodu - angle / 2 - 90)+x;
//					y1 = f * Math.cos(directionJiaodu - angle / 2 - 90)+y;
//					x2 = -f * Math.sin(270 - directionJiaodu - angle / 2)+x;
//					y2 = -f * Math.cos(270 - directionJiaodu - angle / 2)+y;
//				} else if (directionJiaodu <= 315 && directionJiaodu > 225) {
//					System.out.println("225-315");
//					x1 = -f * Math.cos(directionJiaodu - angle / 2 - 180)+x;
//					y1 = -f * Math.sin(directionJiaodu - angle / 2 - 180)+y;
//					x2 = -f * Math.cos(360 - directionJiaodu - angle / 2)+x;
//					y2 = +f * Math.sin(360 - directionJiaodu - angle / 2)+y;
//				}
//
//			} else if(angle==120) {
//				System.out.println("120");
//				if (direction <= 120 && directionJiaodu > 60) {
//					System.out.println("60-120");
//					x1 = f * Math.cos(directionJiaodu - angle / 2)+x;
//					y1 = f * Math.sin(directionJiaodu - angle / 2)+y;
//					x2 = f * Math.cos(180 - directionJiaodu - angle / 2)+x;
//					y2 = f * Math.sin(180 - directionJiaodu - angle / 2)+y;
//				} else if (directionJiaodu <= 210 && directionJiaodu > 150) {
//					System.out.println("150-210");
//					x1 = f * Math.sin(directionJiaodu - 90 - angle / 2)+x;
//					y1 = f * Math.cos(directionJiaodu - 90 - angle / 2)+y;
//					x2 = -f * Math.cos(directionJiaodu + angle / 2 - 180)+x;
//					y2 = -f * Math.sin(directionJiaodu + angle / 2 - 180)+y;
//				} else if (directionJiaodu <= 300 && directionJiaodu > 240) {
//					System.out.println("240-300");
//					x1 = f * Math.sin(directionJiaodu - 180 - angle / 2)+x;
//					y1 = -f * Math.cos(directionJiaodu - 180 - angle / 2)+y;
//					x2 = -f * Math.cos(directionJiaodu + angle / 2 - 270)+x;
//					y2 = -f * Math.sin(directionJiaodu + angle / 2 - 270)+y;
//				} else if (directionJiaodu <= 360 && directionJiaodu > 330) {
//					System.out.println("330-360");
//					x1 = f * Math.sin(directionJiaodu - 270 - angle / 2)+x;
//					y2 = -f * Math.cos(directionJiaodu - 270 - angle / 2)+y;
//					x2 = f * Math.cos(angle / 2 - 360 + directionJiaodu)+x;
//					y2 = f * Math.sin(angle / 2 - 360 + directionJiaodu)+y;
//				} else if (directionJiaodu <= 30 && directionJiaodu > 0) {
//					System.out.println("0-30");
//					x1 = f * Math.cos(directionJiaodu + angle / 2)+x;
//					y1 = f * Math.sin(directionJiaodu + angle / 2)+y;
//					x2 = f * Math.cos(angle / 2 - directionJiaodu)+x;
//					y2 = -f * Math.sin(angle / 2 - directionJiaodu)+y;
//				} else if (directionJiaodu <= 60 && directionJiaodu > 30) {
//					System.out.println("30-60");
//					x1 = f * Math.cos(angle / 2 - directionJiaodu)+x;
//					y1 = -f * Math.sin(angle / 2 - directionJiaodu)+y;
//					x2 = -f * Math.sin(angle / 2 - 90 + directionJiaodu)+x;
//					y2 = f * Math.cos(angle / 2 - 90 + directionJiaodu)+y;
//				} else if (directionJiaodu <= 150 && directionJiaodu > 120) {
//					System.out.println("120-150");
//					x1 = f * Math.sin(angle / 2 + 90 - directionJiaodu)+x;
//					y1 = f * Math.cos(angle / 2 + 90 - directionJiaodu)+y;
//					x2 = -f * Math.cos(angle / 2 + directionJiaodu - 180)+x;
//					y2 = -f * Math.sin(angle / 2 + directionJiaodu - 180)+y;
//				} else if (directionJiaodu <= 240 && directionJiaodu > 210) {
//					System.out.println("210-240");
//					x1 = -f * Math.sin(angle / 2 - directionJiaodu + 180);
//					y1 = f * Math.cos(angle / 2 - directionJiaodu + 180)+y;
//					x2 = f * Math.sin(angle / 2 - 270 + directionJiaodu)+x;
//					y2 = -f * Math.cos(angle / 2 - 270 + directionJiaodu)+y;
//				} else if (directionJiaodu <= 330 && directionJiaodu > 300) {
//					System.out.println("300-330");
//					x1 = -f * Math.sin(angle / 2 - directionJiaodu + 270)+x;
//					y1 = -f * Math.cos(angle / 2 - directionJiaodu + 270)+y;
//					x2 = f * Math.cos(angle / 2 - 360 + directionJiaodu)+x;
//					y2 = f * Math.sin(angle / 2 - 360 + directionJiaodu)+y;
//				}
//			}
//			x1=-f*Math.cos(startAngle)+x;
//			y1=-f*Math.sin(startAngle)+y;
//			x2=-f*Math.cos(directionJiaodu+angle/2)+x;
//			y2=-f*Math.sin(directionJiaodu+angle/2)+y;
//			g.drawLine((int)x, (int)y, (int)x1, (int)y1);
//			System.out.println("("+x1+","+y1+"),("+x2+","+y2+"),");
//			g.drawLine((int)x, (int)y, (int)x2, (int)y2);
			// g.fillArc((int) (x - range), (int) (y - range), (int) range * 2,
			// (int) range * 2, startAngel, angel);

			// 画那两条线
			// double ox = x + range * Math.cos((360-directionJiaodu) -
			// angelJiaodu / 2);
			// double oy = y - range * Math.sin((360-directionJiaodu) -
			// angelJiaodu / 2);
			// g.drawLine((int) x, (int) y, (int) ox, (int) oy);
			// double ax = x + range * Math.cos((360-directionJiaodu) +
			// angelJiaodu / 2);
			// double ay = y - range * Math.sin((360-directionJiaodu) +
			// angelJiaodu / 2);
			// g.drawLine((int) x, (int) y, (int) ax, (int) ay);
		}
	}

	protected void paintLink(Graphics2D g2d, Link l) {
		Integer width = l.getWidth();
		String color = l.getColor();
		try {
			g2d.setColor((Color) Color.class.getField(color).get(color));
		} catch (Exception e) {
			System.err.println("Color " + color + " is not supported.");
		}
		g2d.setStroke(new BasicStroke(width));
		int srcX = (int) l.source.getX(), srcY = (int) l.source.getY();
		int destX = (int) l.destination.getX(), destY = (int) l.destination
				.getY();
		g2d.drawLine(srcX, srcY, (int) (srcX + (destX - srcX)),
				(int) (srcY + (destY - srcY)));
		// g2d.fillArc(srcX, srcY, 100 , 140, 0, 60);
	}

	class EventHandler implements TopologyListener, MovementListener,
			ConnectivityListener, PropertyListener, MouseListener,
			ActionListener {
		public void nodeAdded(Node n) {
			JNode jv = new JNode(n);
			n.setProperty("jnode", jv);
			n.addPropertyListener(this);
			add(jv);
			updateUI();
		}

		public void nodeRemoved(Node n) {
			JNode jn = (JNode) n.getProperty("jnode");
			remove(jn);
			updateUI();
		}

		public void linkAdded(Link l) {
			l.addPropertyListener(this);
			updateUI();
		}

		public void linkRemoved(Link l) {
			updateUI();
		}

		public void nodeMoved(Node n) {
			updateUI();
			((JNode) n.getProperty("jnode")).update();
		}

		public void propertyChanged(_Properties o, String property) {
			if (property.equals("color") && (o.getProperty("jnode") != null))
				((JNode) ((Node) o).getProperty("jnode")).updateUI();
			if (property.equals("id"))
				((JNode) ((Node) o).getProperty("jnode")).setToolTipText(o
						.toString());
			if (property.equals("width"))
				updateUI();
		}

		public void mousePressed(MouseEvent e) {
			if ((Boolean) topo.getProperty("popupRunning") == true) {
				topo.setProperty("popupRunning", false);
				return;
			}
			// if (e.getButton() == MouseEvent.BUTTON1) {
			// JPopupMenu popup = new JPopupMenu();
			// for (String type : Node.getModelsNames()) {
			// JMenuItem jitem = new JMenuItem(type);
			// jitem.setActionCommand("addNode " + type + " " + e.getX()
			// + " " + e.getY());
			// jitem.addActionListener(this);
			// popup.add(jitem);
			// }
			// if (popup.getComponentCount() > 1) {
			// topo.setProperty("popupRunning", true);
			// popup.show(jtopo, e.getX(), e.getY());
			// } else {
			// topo.addNode(e.getX(), e.getY(),
			// Node.newInstanceOfModel("default"));
			// }
			// }
			else if (e.getButton() == MouseEvent.BUTTON3) {
				JPopupMenu popup = new JPopupMenu();
				for (String command : actionCommands) {
					JMenuItem jitem = new JMenuItem(command);
					for (ActionListener al : actionListeners)
						jitem.addActionListener(al);
					jitem.addActionListener(this);
					popup.add(jitem);
				}
				topo.setProperty("popupRunning", true);
				popup.show(jtopo, e.getX(), e.getY());
			}
		}

		public void actionPerformed(ActionEvent arg0) {
			topo.setProperty("popupRunning", false);
			String[] args = arg0.getActionCommand().split(" ");
			if (args[0].equals("addNode")) {
				int x = Integer.parseInt(args[2]);
				int y = Integer.parseInt(args[3]);
				topo.addNode(x, y, Node.newInstanceOfModel(args[1]));
			}
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}
	}
}
