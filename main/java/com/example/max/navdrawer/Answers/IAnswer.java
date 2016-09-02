package com.example.max.navdrawer.Answers;

import java.util.ArrayList;

public interface IAnswer<T> {
	public ArrayList<T> getAnswer(AnswerParams params);
}
