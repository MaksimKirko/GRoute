package com.example.max.navdrawer.Answers;

import java.util.ArrayList;
import java.util.Collections;

import com.example.max.navdrawer.Elements.*;
import com.example.max.navdrawer.Collections.*;
import com.example.max.navdrawer.Load.*;

public class OneTransferAnswer implements IAnswer<OneTransferAnswerArgs> {

    public ArrayList<OneTransferAnswerArgs> getAnswer(AnswerParams params) {
        ArrayList<OneTransferAnswerArgs> results = null;

        for(Bus bus : params.getBusCollection().buses) {

            //compare default routes
            if(bus.defaultRoute.contains(params.getStartHalt()) && !bus.defaultRoute.contains(params.getDestHalt())) {

                for(Bus bus2 : params.getBusCollection().buses) {

                    int startIndex = bus.defaultRoute.getIndex(params.getStartHalt());
                    int destIndex = bus2.defaultRoute.getIndex(params.getDestHalt());
                    Halt commonHalt = null;
                    int commonIndex1 = -1;
                    int commonIndex2 = -1;
                    if(params.getBusCollection().isCommonHalt(bus, bus2, true, true)) {
                        commonHalt = params.getBusCollection().getCommonHalt(bus, bus2, true, true);
                        if(commonHalt != null) {
                            //System.out.println(commonHalt.Name());
                            commonIndex1 = bus.defaultRoute.getIndex(commonHalt);
                            commonIndex2 = bus2.defaultRoute.getIndex(commonHalt);
                        }
                    }

                    if(!bus2.equals(bus) && bus2.defaultRoute.contains(params.getDestHalt()) && commonHalt != null
                            && (startIndex < destIndex && startIndex > -1 &&  commonIndex1 > startIndex && commonIndex2 < destIndex && commonIndex2 > -1)
                            && commonHalt.Name() != params.getStartHalt().Name()) {
                        if(results == null) {
                            results = new ArrayList<OneTransferAnswerArgs>();
                        }
                        int index = getIndexByBusNumber(bus.Number(), results);
                        if(index != -1) {
                            results.get(index).getGaps().add(new NoTransferAnswerArgs(bus2, commonHalt, params.getDestHalt(), commonHalt.Direction()));
                        }
                        else {
                            ArrayList<NoTransferAnswerArgs> gaps = new ArrayList<NoTransferAnswerArgs>();
                            gaps.add(new NoTransferAnswerArgs(bus2, commonHalt, params.getDestHalt(), commonHalt.Direction()));
                            results.add(new OneTransferAnswerArgs(bus, params.getStartHalt(), gaps));
                        }
                    }
                }
            }

            //compare reverse routes
            if(bus.reverseRoute.contains(params.getStartHalt()) && !bus.reverseRoute.contains(params.getDestHalt())) {

                for(Bus bus2 : params.getBusCollection().buses) {

                    int startIndex = bus.reverseRoute.getIndex(params.getStartHalt());
                    int destIndex = bus2.reverseRoute.getIndex(params.getDestHalt());
                    Halt commonHalt = null;
                    int commonIndex1 = -1;
                    int commonIndex2 = -1;
                    if(params.getBusCollection().isCommonHalt(bus, bus2, false, false)) {
                        commonHalt = params.getBusCollection().getCommonHalt(bus, bus2, false, false);
                        if(commonHalt != null) {
                            //System.out.println(commonHalt.Name());
                            commonIndex1 = bus.reverseRoute.getIndex(commonHalt);
                            commonIndex2 = bus2.reverseRoute.getIndex(commonHalt);
                        }
                    }

                    if(!bus2.equals(bus) && bus2.reverseRoute.contains(params.getDestHalt()) && commonHalt != null
                            && (startIndex < destIndex && startIndex > -1 &&  commonIndex1 > startIndex && commonIndex2 < destIndex && commonIndex2 > -1)
                            && commonHalt.Name() != params.getStartHalt().Name()) {
                        if(results == null) {
                            results = new ArrayList<OneTransferAnswerArgs>();
                        }
                        int index = getIndexByBusNumber(bus.Number(), results);
                        if(index != -1) {
                            results.get(index).getGaps().add(new NoTransferAnswerArgs(bus2, commonHalt, params.getDestHalt(), commonHalt.Direction()));
                        }
                        else {
                            ArrayList<NoTransferAnswerArgs> gaps = new ArrayList<NoTransferAnswerArgs>();
                            gaps.add(new NoTransferAnswerArgs(bus2, commonHalt, params.getDestHalt(), commonHalt.Direction()));
                            results.add(new OneTransferAnswerArgs(bus, params.getStartHalt(), gaps));
                        }
                    }
                }
            }

            //compare default and reverse routes
            if(bus.defaultRoute.contains(params.getStartHalt()) && !bus.defaultRoute.contains(params.getDestHalt())) {

                for(Bus bus2 : params.getBusCollection().buses) {

                    int startIndex = bus.defaultRoute.getIndex(params.getStartHalt());
                    int destIndex = bus.reverseRoute.getIndex(params.getDestHalt());
                    Halt commonHalt = null;
                    int commonIndex1 = -1;
                    int commonIndex2 = -1;
                    if(params.getBusCollection().isCommonHalt(bus, bus2, true, false)) {
                        commonHalt = params.getBusCollection().getCommonHalt(bus, bus2, true, false);
                        if(commonHalt != null) {
                            //System.out.println(commonHalt.Name());
                            commonIndex1 = bus.defaultRoute.getIndex(commonHalt);
                            commonIndex2 = bus2.reverseRoute.getIndex(commonHalt);
                        }
                    }

                    if(!bus2.equals(bus) && bus2.reverseRoute.contains(params.getDestHalt()) && commonHalt != null
                            && (startIndex < destIndex && startIndex > -1 &&  commonIndex1 > startIndex && commonIndex2 < destIndex && commonIndex2 > -1)
                            && commonHalt.Name() != params.getStartHalt().Name()) {
                        if(results == null) {
                            results = new ArrayList<OneTransferAnswerArgs>();
                        }
                        int index = getIndexByBusNumber(bus.Number(), results);
                        if(index != -1) {
                            results.get(index).getGaps().add(new NoTransferAnswerArgs(bus2, commonHalt, params.getDestHalt(), commonHalt.Direction()));
                        }
                        else {
                            ArrayList<NoTransferAnswerArgs> gaps = new ArrayList<NoTransferAnswerArgs>();
                            gaps.add(new NoTransferAnswerArgs(bus2, commonHalt, params.getDestHalt(), commonHalt.Direction()));
                            results.add(new OneTransferAnswerArgs(bus, params.getStartHalt(), gaps));
                        }
                    }
                }
            }

            //compare reverse and default routes
            if(bus.reverseRoute.contains(params.getStartHalt()) && !bus.reverseRoute.contains(params.getDestHalt())) {

                for(Bus bus2 : params.getBusCollection().buses) {

                    int startIndex = bus.reverseRoute.getIndex(params.getStartHalt());
                    int destIndex = bus.defaultRoute.getIndex(params.getDestHalt());
                    Halt commonHalt = null;
                    int commonIndex1 = -1;
                    int commonIndex2 = -1;
                    if(params.getBusCollection().isCommonHalt(bus, bus2, false, true)) {
                        commonHalt = params.getBusCollection().getCommonHalt(bus, bus2, false, true);
                        if(commonHalt != null) {
                            //System.out.println(commonHalt.Name());
                            commonIndex1 = bus.reverseRoute.getIndex(commonHalt);
                            commonIndex2 = bus2.defaultRoute.getIndex(commonHalt);
                        }
                    }

                    if(!bus2.equals(bus) && bus2.defaultRoute.contains(params.getDestHalt()) && commonHalt != null
                            && (startIndex < destIndex && startIndex > -1 &&  commonIndex1 > startIndex && commonIndex2 < destIndex && commonIndex2 > -1)
                            && commonHalt.Name() != params.getStartHalt().Name()) {
                        if(results == null) {
                            results = new ArrayList<OneTransferAnswerArgs>();
                        }
                        int index = getIndexByBusNumber(bus.Number(), results);
                        if(index != -1) {
                            results.get(index).getGaps().add(new NoTransferAnswerArgs(bus2, commonHalt, params.getDestHalt(), commonHalt.Direction()));
                        }
                        else {
                            ArrayList<NoTransferAnswerArgs> gaps = new ArrayList<NoTransferAnswerArgs>();
                            gaps.add(new NoTransferAnswerArgs(bus2, commonHalt, params.getDestHalt(), commonHalt.Direction()));
                            results.add(new OneTransferAnswerArgs(bus, params.getStartHalt(), gaps));
                        }
                    }
                }
            }
        }
        return results;
    }

    public void deleteExtras(ArrayList<OneTransferAnswerArgs> oneTrResults, ArrayList<NoTransferAnswerArgs> noTrResults) {
        if(oneTrResults.size() > 1) {
            for (int i = 0; i < oneTrResults.size(); i++) {
                for (int j = i + 1; j < oneTrResults.size(); j++) {
                    if (oneTrResults.get(i).getBus1() == oneTrResults.get(j).getBus1()) {
                        oneTrResults.remove(j);
                    }

                    for (NoTransferAnswerArgs temp : noTrResults) {
                        for(NoTransferAnswerArgs ntaa : oneTrResults.get(i).getGaps()) {
                            if (ntaa.getBus() == temp.getBus()) {
                                oneTrResults.remove(j);
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < oneTrResults.size(); i++) {
                if(oneTrResults.get(oneTrResults.size() - 1).getBus1() == oneTrResults.get(i).getBus1()) {
                    oneTrResults.remove(oneTrResults.size() - 1);
                    return;
                }
            }
        }
        if(oneTrResults.size() > 1) {
            for (int i = 0; i < oneTrResults.size(); i++) {
                if(oneTrResults.get(oneTrResults.size() - 1).getBus1() == oneTrResults.get(i).getBus1()) {
                    oneTrResults.remove(oneTrResults.size() - 1);
                    return;
                }
            }
        }
    }

    private int getIndexByBusNumber(int number, ArrayList<OneTransferAnswerArgs> res) {
        int i = 0;
        for(OneTransferAnswerArgs temp : res) {
            if(temp.getBus1() == number) {
                return i;
            }
            i++;
        }
        return -1;
    }
}

