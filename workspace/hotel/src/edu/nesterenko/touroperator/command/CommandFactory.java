package edu.nesterenko.touroperator.command;


public class CommandFactory {
	
	private CommandFactory() {
	}
	
	public static Command determineCommand(String commandName) {
		Command command = EmptyCommand.getInstance();
		if(commandName != null && !commandName.isEmpty()) {
			switch(CommandEnum.valueOf(commandName.toUpperCase())) {
				case SIGNUP_PAGE:
					command = SignUpPageCommand.getInstance();
					break;
				case LOGIN_PAGE:
					command = LoginPageCommand.getInstance();
					break;
				case SIGNUP:	
					command = SignUpCommand.getInstance();
					break;
				case LOGIN:	
					command = LoginCommand.getInstance();
					break;
				case LOGOUT:	
					command = LogoutCommand.getInstance();
					break;
				case CHANGE_LOCALE:
					command = ChangeLocaleCommand.getInstance();
					break;
			}
		}
		return command;
	}

}
