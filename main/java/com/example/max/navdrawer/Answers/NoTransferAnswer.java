package com.example.max.navdrawer.Answers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.example.max.navdrawer.Elements.*;
import com.example.max.navdrawer.Collections.*;
import com.example.max.navdrawer.Load.*;

public class NoTransferAnswer implements IAnswer<NoTransferAnswerArgs> {
    public ArrayList<NoTransferAnswerArgs> getAnswer(AnswerParams params) {
        ArrayList<NoTransferAnswerArgs> results = null;
        for(Bus bus : params.getBusCollection().buses) {
            int startIndex = bus.defaultRoute.getIndex(params.getStartHalt());
            int destIndex = bus.defaultRoute.getIndex(params.getDestHalt());
            System.out.println(startIndex + " " + destIndex);
            if(bus.defaultRoute.contains(params.getStartHalt()) && bus.defaultRoute.contains(params.getDestHalt())
                    && (startIndex < destIndex && startIndex > -1)) {
                if(results == null) {
                    results = new ArrayList<NoTransferAnswerArgs>();
                }
                NoTransferAnswerArgs ans = new NoTransferAnswerArgs(bus, params.getStartHalt(),
                        params.getDestHalt(), params.getStartHalt().Direction());
                if(!results.contains(ans)) {
                    results.add(ans);
                    continue;
                }
            }

            startIndex = bus.reverseRoute.getIndex(params.getStartHalt());
            destIndex = bus.reverseRoute.getIndex(params.getDestHalt());
            System.out.println(startIndex + " " + destIndex);
            if(bus.reverseRoute.contains(params.getStartHalt()) && bus.reverseRoute.contains(params.getDestHalt())
                    && (startIndex < destIndex && startIndex > -1)) {
                if(results == null) {
                    results = new ArrayList<NoTransferAnswerArgs>();
                }
                NoTransferAnswerArgs ans = new NoTransferAnswerArgs(bus, params.getStartHalt(),
                        params.getDestHalt(), params.getStartHalt().Direction());
                if(!results.contains(ans)) {
                    results.add(ans);
                    continue;
                }
            }
        }
        return results;
    }

	public void deleteExtras(ArrayList<NoTransferAnswerArgs> results) {
        if(results.size() > 1) {
            for (int i = 0; i < results.size(); i++) {
                for (int j = i + 1; j < results.size(); j++) {
                    if (results.get(i).getBus() == results.get(j).getBus()) {
                        results.remove(j);
                    }
                }
            }
        }
        if(results.size() > 1) {
            for (int i = 0; i < results.size(); i++) {
                for (int j = i + 1; j < results.size(); j++) {
                    if (results.get(i).getBus() == results.get(j).getBus()) {
                        results.remove(j);
                    }
                }
            }
        }
	}
}
