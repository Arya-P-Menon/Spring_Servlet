package com.ibm.quiz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "que_opts")
public class Option {

	@Id
	@GeneratedValue
	private int opid;
	@Column(name = "opt")
	private char option;
	@Column(length = 40)
	private String text;
	private int flag;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "qid")
	private Question que;

	public int getOpid() {
		return opid;
	}

	public void setOpid(int opid) {
		this.opid = opid;
	}

	public char getOption() {
		return option;
	}

	public void setOption(char option) {
		this.option = option;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Question getQue() {
		return que;
	}

	public void setQue(Question que) {
		this.que = que;
	}
	
	
}
