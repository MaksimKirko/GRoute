package com.example.max.navdrawer.Answers;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.max.navdrawer.Elements.*;
import com.example.max.navdrawer.Collections.*;
import com.example.max.navdrawer.Load.*;

import java.util.ArrayList;

public class OneTransferAnswerArgs implements Parcelable, Comparable {
	final static String LOG_TAG = "myLogs";

	private int bus1;
    private String startHalt;
    private String direction1;
    private ArrayList<NoTransferAnswerArgs> gaps;

	public int getBus1() {
		return this.bus1;
	}

	public void setBus1(int number) {
		this.bus1 = number;
	}


	public String getStartHalt() {
        return this.startHalt;
    }

    public void setStartHalt(String line) {
        this.startHalt = line;
    }

    public String getDirection1() {
        return this.direction1;
    }

    public void setDirection1(String line) {
        this.direction1 = line;
    }

    public ArrayList<NoTransferAnswerArgs> getGaps() {
        return this.gaps;
    }

    public void setGaps(ArrayList<NoTransferAnswerArgs> gaps) {
        this.gaps = gaps;
    }

    private OneTransferAnswerArgs(Parcel in) {
        this();
        this.bus1 = in.readInt();
        this.startHalt = in.readString();
        this.direction1 = in.readString();
        in.readTypedList(gaps, NoTransferAnswerArgs.CREATOR);
    }

    public OneTransferAnswerArgs(){
        gaps = new ArrayList<NoTransferAnswerArgs>();
    }
    public OneTransferAnswerArgs(Bus bus1, Halt startHalt, ArrayList<NoTransferAnswerArgs> gaps) {
        this.bus1 = bus1.Number();
        this.startHalt = startHalt.Name();
        this.direction1 = startHalt.Direction();
        this.gaps = gaps;
    }

	public int describeContents() {
		return 0;
	}

	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

		public OneTransferAnswerArgs createFromParcel(Parcel in) {
			return new OneTransferAnswerArgs(in);
		}

		public OneTransferAnswerArgs[] newArray(int size) {
			return new OneTransferAnswerArgs[size];
		}

		public ArrayList<OneTransferAnswerArgs> newArrayList() {
			return  new ArrayList<OneTransferAnswerArgs>();
		}
	};

	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeInt(bus1);
		parcel.writeString(startHalt);
        parcel.writeString(direction1);
        parcel.writeTypedList(gaps);
	}

    @Override
    public int compareTo(Object another) {
        OneTransferAnswerArgs temp = (OneTransferAnswerArgs) another;
        if(this.getBus1() < temp.getBus1())
        {
      /* текущее меньше полученного */
            return -1;
        }
        else if(this.getBus1()> temp.getBus1())
        {
      /* текущее больше полученного */
            return 1;
        }
    /* текущее равно полученному */
        return 0;
    }
}
