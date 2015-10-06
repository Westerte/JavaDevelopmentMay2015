package edu.nesterenko.bank.command;

import org.apache.log4j.Logger;

public class CommandFactory {
	private final static Logger LOG = 
			Logger.getLogger(CommandFactory.class);
	
	private CommandFactory() {
	}
	
	public static Command determineCommand(String commandName) {
		Command command = EmptyCommand.getInstance();
		if(commandName != null && !commandName.isEmpty()) {
			CommandEnum commandEnumValue;
			try {
				commandEnumValue = CommandEnum.valueOf(commandName.toUpperCase());
			} catch(IllegalArgumentException e) {
				LOG.error(e);
				return command;
			}
			switch(commandEnumValue) {
				case CHANGE_LOCALE:
					command = ChangeLocaleCommand.getInstance();
					break;
				case VIEW_ALL:
					command = ViewAllCommand.getInstance();
					break;
				case EDIT:
					command = EditCommand.getInstance();
					break;
				case DELETE: 
					command = DeleteCommand.getInstance();
					break;
				case ADD:
					command = AddCommand.getInstance();
					break;
				case ADD_PAGE:
					command = AddPageCommand.getInstance();
					break;
				case EDIT_PAGE:
					command = EditPageCommand.getInstance();
					break;
				}
		}
		return command;
	}

}