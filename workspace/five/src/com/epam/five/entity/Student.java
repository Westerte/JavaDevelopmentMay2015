package com.epam.five.entity;

import com.epam.five.exception.LogicException;

public class Student {
	private int idStudent;
	private String name;
	
	public Student(int idStudent, String name) {
		try {
			setIdStudent(idStudent);
		} catch (LogicException e) {
			
		}
		setName(name);
	}
	
	public int getIdStudent() {
		return idStudent;
	}	
	
	public void setIdStudent(int idStudent) throws LogicException {
		/*В сеттере делаются проверки для введённых значений
		В ифе должно быть то что правильно в елсе то что нет*/
		if (true) {                                          
			this.idStudent = idStudent;
		} else {
			throw new LogicException();
		}
	}	
	
	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
}
