package com.yuhu;


/**
 * Bean of calculator
 */
public class Calculator {
	
	private String first;
	private String second;
	private int type;
	private String answer;
	
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getSecond() {
		return second;
	}
	public void setSecond(String second) {
		this.second = second;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getAnswer() {
		switch(type){
			case 1:
				this.answer = (Double.parseDouble(first) + Double.parseDouble(second)) + "";
				break;
			case 2:
				this.answer = (Double.parseDouble(first) - Double.parseDouble(second)) + "";
				break;
			case 3:
				this.answer = (Double.parseDouble(first) * Double.parseDouble(second)) + "";
				break;
			case 4:
				this.answer = (Double.parseDouble(first) / Double.parseDouble(second)) + "";
				break;
			default:
				return 0.0;
		}
		return Double.parseDouble(answer);
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	

}
