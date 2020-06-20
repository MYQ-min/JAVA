package cn.xdl.bean;

import java.io.Serializable;

public class TestResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String testdate;
	private String name;
	private int score;
	public TestResult() {
		super();
	}
	public TestResult(int id, String testdate, String name, int score) {
		super();
		this.id = id;
		this.testdate = testdate;
		this.name = name;
		this.score = score;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTestdate() {
		return testdate;
	}
	public void setTestdate(String testdate) {
		this.testdate = testdate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "考试日期=" + testdate + ", 用户名=" + name + ", 分数=" + score;
	}
	public TestResult(String testdate, String name, int score) {
		super();
		this.testdate = testdate;
		this.name = name;
		this.score = score;
	}
	
}
