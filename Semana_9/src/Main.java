import java.util.ArrayList;

import events.OnMessageListener;
import model.Orden;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet implements OnMessageListener{
	
	UDPConection udp;
	private ArrayList<Orden> ordenes;
	private int numeroOrden;
	PImage a,b,c,d;
	
	public static void main (String[] args) {
		PApplet.main("Main");
	}
	@Override
	public void settings() {
		size(500,800);
	}
	
	@Override
	public void setup() {
		udp = new UDPConection();
		udp.start();
		udp.setObsever(this);
		ordenes = new ArrayList<>();
		numeroOrden = 0;
		a = loadImage("data/1.png");
		b = loadImage("data/2.png");
		c = loadImage("data/3.png");
		d = loadImage("data/4.png");
		imageMode(CENTER);
		rectMode(CENTER);
		fill(0);
		textSize(30);
		noStroke();
	}
	
	@Override
	public void draw() {
		background(255);
	if(ordenes.size()>0) {
		for(int i=0;i<ordenes.size();i++) {
			int y = 100 + i*210;
		switch(ordenes.get(i).getType()) {
		case "1":
			image(a, 120, y);
			break;
		case "2":
			image(b, 120, y);
			break;	
		case "3":
			image(c, 120, y);
			break;
		case "4":
			image(d, 120, y);
			break;
		}
		fill(0);
		text(ordenes.get(i).getOrder(), 240, y-50);
		text(ordenes.get(i).getTime(), 240, y);
		fill(0,128,0);
		rect(290, y+50, 100, 60);
		fill(255);
		text("Ready", 250, y+60);
		}
		
	}
	}
	
	@Override
	public void mousePressed() {
		//despachar pedido
		if(ordenes.size()>0) {
			for(int i=0;i<ordenes.size();i++) {
				int y = 150 + i*210;
			if(mouseX>240&&mouseX<340&&mouseY>y-30&&mouseY<y+30) {
		udp.sendMsg("Ready",ordenes.get(i).getIpFrom(), Integer.parseInt(ordenes.get(i).getPortFrom()));
				ordenes.remove(i);
				
			}
			
			}}
	
	}
	@Override
	public void onOrderReceived(Orden orden) {
	if(ordenes.size()<4) {
		udp.sendMsg("Received",orden.getIpFrom(), Integer.parseInt(orden.getPortFrom()));
		numeroOrden++;
		orden.setOrder("Orden # "+numeroOrden);
		ordenes.add(orden);
		System.out.println(ordenes);
	}
	if(ordenes.size()>=4) {
		
		udp.sendMsg("Denied",orden.getIpFrom(), Integer.parseInt(orden.getPortFrom()));
	}
	}
	@Override
	public void onOrderReady(Orden orden) {
		udp.sendMsg("Ready",orden.getIpFrom(), Integer.parseInt(orden.getPortFrom()));
	}
	
}