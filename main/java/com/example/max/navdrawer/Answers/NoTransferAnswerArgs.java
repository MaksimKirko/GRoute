package com.example.max.navdrawer.Answers;

import android.graphics.Path;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.max.navdrawer.Elements.*;
import com.example.max.navdrawer.Collections.*;
import com.example.max.navdrawer.Load.*;

import java.util.ArrayList;

public class NoTransferAnswerArgs implements Parcelable, Comparable {

    final static String LOG_TAG = "myLogs";

    private int bus;
	private String startHalt;
	private String destHalt;
    private String direction;

    private NoTransferAnswerArgs(Parcel in) {
        this.bus = in.readInt();
        this.startHalt = in.readString();
        this.destHalt = in.readString();
        this.direction = in.readString();
    }

    public int getBus() {
        return this.bus;
    }

    public void setBus(int number) {
        this.bus = number;
    }

    public String getStartHalt() {
        return this.startHalt;
    }

    public void setStartHalt(String line) {
        this.startHalt = line;
    }

    public String getDestHalt() {
        return this.destHalt;
    }

    public void setDestHalt(String line) {
        this.destHalt = line;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String line) {
        this.direction = line;
    }

    public NoTransferAnswerArgs(Bus bus, Halt startHalt, Halt destHalt, String direction) {
        this.bus = bus.Number();
        this.startHalt = startHalt.Name();
        this.destHalt = destHalt.Name();
        this.direction = direction;
    }

    public NoTransferAnswerArgs(int bus, String startHalt, String destHalt, String direction) {
        this.bus = bus;
        this.startHalt = startHalt;
        this.destHalt = destHalt;
        this.direction = direction;
    }

    public int describeContents() {
        return 0;
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public NoTransferAnswerArgs createFromParcel(Parcel in) {
            return new NoTransferAnswerArgs(in);
        }

        public NoTransferAnswerArgs[] newArray(int size) {
            return new NoTransferAnswerArgs[size];
        }

        public ArrayList<NoTransferAnswerArgs> newArrayList() {
            return  new ArrayList<NoTransferAnswerArgs>();
        }
    };

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(bus);
        parcel.writeString(startHalt);
        parcel.writeString(destHalt);
        parcel.writeString(direction);
    }

    @Override
    public int compareTo(Object another) {
        NoTransferAnswerArgs temp = (NoTransferAnswerArgs) another;
        if(this.getBus() < temp.getBus())
        {
      /* текущее меньше полученного */
            return -1;
        }
        else if(this.getBus()> temp.getBus())
        {
      /* текущее больше полученного */
            return 1;
        }
    /* текущее равно полученному */
        return 0;
    }
}
