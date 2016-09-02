package com.example.max.navdrawer.Answers;

import com.example.max.navdrawer.Elements.*;
import com.example.max.navdrawer.Collections.*;
import com.example.max.navdrawer.Load.*;

public class AnswerParams {
	private Halt startHalt;
	private Halt destHalt;
	private BusCollection busCollection;
	private HaltCollection haltCollection;

	public AnswerParams(Halt startHalt, Halt destHalt, BusCollection busCollection, HaltCollection haltCollection) {
		this.startHalt = startHalt;
		this.destHalt = destHalt;
		this.busCollection = busCollection;
		this.haltCollection = haltCollection;
	}
	
	public Halt getStartHalt() {
		return startHalt;
	}
	
	public Halt getDestHalt() {
		return destHalt;
	}
	
	public BusCollection getBusCollection() {
		return busCollection;
	}
	
	public HaltCollection getHaltCollection() {
		return haltCollection;
	}
}
