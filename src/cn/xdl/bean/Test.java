package cn.xdl.bean;

import java.io.Serializable;

public class Test implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int num;
	private String question;
	private String a;
	private String b;
	private String c;
	private String d;
	private int score;
	private String right;
	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Test(String question, String a, String b, String c, String d, int score,String right) {
		super();
		this.question = question;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.right=right;
		this.score = score;
	}

	public Test(int id, int num, String question, String a, String b, String c, String d, int score,String right) {
		super();
		this.id = id;
		this.num = num;
		this.question = question;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.right=right;
		this.score = score;
	}
	public Test(int num, String question, String a, String b, String c, String d, int score,String right) {
		super();
		this.num = num;
		this.question = question;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.right=right;
		this.score = score;
	}
	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Test [id=" + id + ", num=" + num + ", question=" + question + ", a=" + a + ", b=" + b + ", c=" + c
				+ ", d=" + d + ", score=" + score + "]";
	}
	
	
}
