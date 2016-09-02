package com.example.max.navdrawer.Collections;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.example.max.navdrawer.Elements.*;
import com.example.max.navdrawer.Load.*;

public class HaltCollection {
	public ArrayList<Halt> halts;
	private ArrayList<String> text;
	private LoaderFromFile reader;
    private Activity activity;
    private int id;
	
	public HaltCollection(Activity activity, int id) {
		halts = new ArrayList<Halt>();
		reader = new LoaderFromFile(activity, id);
        this.activity = activity;
        this.id = id;
	}
	
	public HaltCollection(Activity activity, int id, ArrayList<Halt> halts) {
		this.halts = halts;
		reader = new LoaderFromFile(activity, id);
        this.activity = activity;
        this.id = id;
	}
	
	public void getHaltsFromFile() {
		this.text = reader.textToList(reader.getStringFromRawFile(activity, id));

		for (String line : this.text) {
            Halt halt = new Halt();
            halt = halt.parse(line);
            if (halt != null) {
                this.halts.add(halt);
            }
        }
	}
	
	// returns halt by it's name
	//!!! can returns null
	public Halt getByName(String name) {
		for(Halt h : halts) {
			if(h.Name().equals(name)) {
				return h;
			}
		}
		return null;
	}

	public Halt findByName(String name) {
		for(Halt h : this.halts) {
			if(h.Name().toLowerCase().contains(name.toLowerCase())) {
				return h;
			}
		}
		return null;
	}

	public String[] getNames() {
		String[] arr = new String[this.halts.size()];
		for(int i = 0; i < arr.length; i++) {
			if(this.halts.get(i).Direction().equals("Unknown")) {
				arr[i] = this.halts.get(i).Name();
			}
			else {
				arr[i] = this.halts.get(i).Name() + "(" + this.halts.get(i).Direction() + ")";
			}
		}
        Arrays.sort(arr);
        return arr;
	}

	// returns nearest halt for input coords
	//!!! can returns null
	public Halt getNearest(Coords coords) { 
		Halt halt = null;
		double minDistance = Math.sqrt(Math.pow((coords.X() - halts.get(0).Coords().X()), 2) + 
				(Math.pow((coords.Y() - halts.get(0).Coords().Y()), 2)));
		for(int i = 1; i < halts.size(); i++) {
			double distance = Math.sqrt(Math.pow((coords.X() - halts.get(i).Coords().X()), 2) + 
					(Math.pow((coords.Y() - halts.get(i).Coords().Y()), 2)));
			if(minDistance > distance) {
				minDistance = distance;
				halt = halts.get(i);
			}
		}
		return halt;
	}

	private boolean compareHalts(Halt h1, Halt h2) {
		if(h1 == null) {
			return true;
		}
		if(h1.Name().equals(h2.Name()) && h1.Direction().equals(h2.Direction())) {
			return false;
		}
		return true;
	}

	public Halt getNeighbour(Halt halt) {
		Halt nHalt = null;

		double minDistance = 100000;
		for(int i = 1; i < halts.size(); i++) {
			//System.out.println(halts.get(i).Name() + halts.get(i).Direction());
			double distance = Math.sqrt(Math.pow((halt.Coords().X() - halts.get(i).Coords().X()), 2) +
					(Math.pow((halt.Coords().Y() - halts.get(i).Coords().Y()), 2)));
			if(minDistance > distance && compareHalts(nHalt, halt) && distance > 0) {
				minDistance = distance;
				nHalt = halts.get(i);
			}
		}

		return nHalt;
	}

	public ArrayList<Halt> getNearests(Coords coords) {
		ArrayList<Halt> distHalts = this.halts;

		for(Halt h : distHalts) {
			double distance = Math.sqrt(Math.pow((coords.X() - h.Coords().X()), 2) +
					(Math.pow((coords.Y() - h.Coords().Y()), 2)));
			h.setDistance(distance);
		}
		Collections.sort(distHalts, Halt.compDistance);

		ArrayList<Halt> results = new ArrayList<Halt>();
		results.add(distHalts.get(0));
		results.add(distHalts.get(1));
		results.add(distHalts.get(2));
		return results;
	}
}
