package edu.nesterenko.bank.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {
	String execute(HttpServletRequest request);
}
