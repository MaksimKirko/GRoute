package com.example.max.navdrawer.Collections;

import android.app.Activity;

import java.util.ArrayList;


import com.example.max.navdrawer.Elements.*;
import com.example.max.navdrawer.Load.*;

public class BusCollection {
	public ArrayList<Bus> buses;
	private ArrayList<String> text;
	private LoaderFromFile reader;
    private Activity activity;
    private int id;

    public void setText(ArrayList<String> text) {
        this.text = text;
    }

	public BusCollection(Activity activity, int id) {
		buses = new ArrayList<Bus>();
		reader = new LoaderFromFile(activity, id);
        this.activity = activity;
        this.id = id;
	}
	
	public BusCollection(Activity activity, int id, ArrayList<Bus> buses) {
		this.buses = buses;
		reader = new LoaderFromFile(activity, id);
        this.activity = activity;
        this.id = id;
	}

	public void getBusesFromFile() {
		this.text = reader.textToList(reader.getStringFromRawFile(activity, id));

		for (String line : this.text) {
            Bus bus = new Bus();
            bus = bus.parse(line);
            if (bus != null) {
                this.buses.add(bus);
            }
        }
	}

	// returns true if two buses have common halt and false otherwise
	public boolean isCommonHalt(Bus bus1, Bus bus2, boolean defaultOrReverse1, boolean defaultOrReverse2) {
		if(defaultOrReverse1 && defaultOrReverse2) {
			for(Halt h1 : bus1.defaultRoute.getHalts()) {
				if(bus2.defaultRoute.contains(h1) && !bus2.equals(bus1)) {
					return true;
				}
			}
		}
		else if(defaultOrReverse1 == false && defaultOrReverse2 == false){
			for(Halt h1 : bus1.reverseRoute.getHalts()) {
				if(bus2.reverseRoute.contains(h1) && !bus2.equals(bus1)) {
					return true;
				}
			}
		}
		else if(defaultOrReverse1 && defaultOrReverse2 == false){
			for(Halt h1 : bus1.defaultRoute.getHalts()) {
				if(bus2.reverseRoute.contains(h1) && !bus2.equals(bus1)) {
					return true;
				}
			}
		}
		else if(defaultOrReverse1 == false && defaultOrReverse2){
			for(Halt h1 : bus1.reverseRoute.getHalts()) {
				if(bus2.defaultRoute.contains(h1) && !bus2.equals(bus1)) {
					return true;
				}
			}
		}
		return false;
	}

	// return common halt for two buses
	//!!! can returns null
	//(bus1, bus2, true - search in default root, false - search in reverse root)
	public Halt getCommonHalt(Bus bus1, Bus bus2, boolean defaultOrReverse1, boolean defaultOrReverse2) {
		if(defaultOrReverse1 && defaultOrReverse2) {
			for(Halt h1 : bus1.defaultRoute.getHalts()) {
				if(bus2.defaultRoute.contains(h1) && !bus2.equals(bus1)) {
					return h1;
				}
			}
		}
		else if(defaultOrReverse1 == false && defaultOrReverse2 == false) {
			for(Halt h1 : bus1.reverseRoute.getHalts()) {
				if(bus2.reverseRoute.contains(h1) && !bus2.equals(bus1)) {
					return h1;
				}
			}
		}
		else if(defaultOrReverse1 && defaultOrReverse2 == false) {
			for(Halt h1 : bus1.defaultRoute.getHalts()) {
				if(bus2.reverseRoute.contains(h1) && !bus2.equals(bus1)) {
					return h1;
				}
			}
		}
		else if(defaultOrReverse1 == false && defaultOrReverse2) {
			for(Halt h1 : bus1.reverseRoute.getHalts()) {
				if(bus2.defaultRoute.contains(h1) && !bus2.equals(bus1)) {
					return h1;
				}
			}
		}
		return null;
	}

	public String[] getNames() {
		String[] arr = new String[this.buses.size()];
		for(int i = 0; i < arr.length ; i++) {
			arr[i] = this.buses.get(i).Number() + " : " + this.buses.get(i).defaultRoute.Name()
					 + "; " + this.buses.get(i).reverseRoute.Name();
		}
		return arr;
	}
}
