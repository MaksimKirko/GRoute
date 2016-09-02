package com.example.max.navdrawer.Elements;

import java.util.regex.Pattern;

public class Bus {
	private int number;
	
	public Route defaultRoute;
	public Route reverseRoute;
	
	public int Number() {
		return this.number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public Bus() {
		setNumber(0);
		this.defaultRoute = new Route();
		this.reverseRoute = new Route();
	}
	
	public Bus(int number, Route defaultRoute, Route reverseRoute) {
		setNumber(number);
		this.defaultRoute = defaultRoute;
		this.reverseRoute = reverseRoute;
	}
	
	public Bus parse(String line) {
		Bus bus = new Bus();

		Pattern pat = Pattern.compile("; ");
		String[] items = pat.split(line);
		bus.number = Integer.parseInt(items[0]);

		String[] defRoute = items[1].split(" : ");
		bus.defaultRoute.setName(defRoute[0]);
		defRoute = defRoute[1].split(" - ");
		for (String s : defRoute) {
			Halt h = new Halt();
			String[] is = s.split("\\(|\\)");
			h.setName(is[0]);
			h.setDirection(is[1]);
			bus.defaultRoute.getHalts().add(h);
		}

		String[] revRoute = items[2].split(" : ");
		bus.reverseRoute.setName(revRoute[0]);
		revRoute = revRoute[1].split(" - ");
		for (String s : revRoute) {
			Halt h = new Halt();
			String[] is = s.split("\\(|\\)");
			h.setName(is[0]);
			h.setDirection(is[1]);
			bus.reverseRoute.getHalts().add(h);
		}

		return bus;
	}
}
