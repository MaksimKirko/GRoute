package com.example.max.navdrawer.Elements;

import java.util.Comparator;

public class Halt {
	private String name;
	private Coords coords;
	private String direction;
	private int id;
	private double distance;

	public String Name() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String Direction() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Coords Coords() {
		return this.coords;
	}

	public void setCoords(Coords coords) {
		this.coords = coords;
	}

	public int ID() {
		return this.id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public double Distance() {
		return this.distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Halt() {
		setName("Unknown");
		setDirection("(Unknown)");
		setCoords(new Coords(0, 0));
		setID(0);
		setDistance(0);
	}

	public Halt(String name, Coords coords) {
		setName(name);
		setDirection("(Unknown)");
		setCoords(coords);
		setID(0);
		setDistance(0);
	}

	public Halt(String name, String direction, Coords coords) {
		setName(name);
		setDirection(direction);
		setCoords(coords);
		setID(0);
		setDistance(0);
	}

	public Halt(String name, String direction, Coords coords, int id) {
		setName(name);
		setDirection(direction);
		setCoords(coords);
		setID(id);
		setDistance(0);
	}

	public static Comparator<Halt> compDistance = new Comparator<Halt>() {

		public int compare(Halt h1, Halt h2) {
			return Double.compare(h1.Distance(), h2.Distance());
		}
	};

	public Halt parse(String line) {
		Halt halt = new Halt();
		//System.out.println(line);
		String[] mas = line.split("; ");
		halt.setID(Integer.parseInt(mas[0]));
		String[] is = mas[1].split("\\(|\\)");
		halt.setName(is[0]);
		halt.setDirection(is[1]);
		if(mas.length > 2) {
			halt.coords = coords.getCoordsFromString(mas[2]);
		}
		return halt;
	}
}
