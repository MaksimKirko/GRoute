package com.example.max.navdrawer.Elements;

import java.util.ArrayList;

public class Route {
	private String name;
	private ArrayList<Halt> halts = new ArrayList<Halt>();
	
	public String Name() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Halt> getHalts() {
		return this.halts;
	}
	
	public void setHalts(ArrayList<Halt> halts) {
		this.halts = halts;
	}
	
	public Route() {
		setName("Unknown");
		halts = new ArrayList<Halt>();
	}
	
	public Route(String name, ArrayList<Halt> halts) {
		setName(name);
		this.halts = halts;
	}
	
	@Override
	public String toString() {
		String line = this.name + " : ";
		for(Halt h : this.halts) {
			line += h.Name() + " - ";
		}
		return line;
	}

	public int getIndex(Halt halt) {
		int index = -1;
		for(Halt h : halts) {
			index++;
			if(halt.Name().equals(h.Name()) && halt.Direction().equals(h.Direction())) {
				return index;
			}
		}
		return -1;
	}

	public boolean contains(Halt halt) {
		for(Halt h : halts) {
			if(halt.Name().equals(h.Name())) {
				return true;
			}
		}
		return false;
	}

	public String getDirection() {
		String[] temp = name.split(" - ");
		return temp[1];
	}

    public Halt getHaltByName(String name) {
        for(Halt h : halts) {
            if(h.Name().equals(name)) {
                return h;
            }
        }
        return null;
    }
}
