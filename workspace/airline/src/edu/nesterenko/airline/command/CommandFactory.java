package edu.nesterenko.airline.command;


import edu.nesterenko.airline.exception.LogicalException;

public class CommandFactory {
	
	private CommandFactory() {
	}
	
	public static Command determineCommand(String command) throws LogicalException {
		
		switch (CommandEnum.valueOf(command.toUpperCase())) {
		/*case ADD_AIRBUS:
			return AddAirbusCommand.getInstance();
		case ADD_FREIGHTER:
			return AddFreighterCommand.getInstance();
		case DELETE:
			return DeleteCommand.getInstance();		
		case CALCULATE_GENERAL_BEARING_CAPACITY:
			return CalculateGeneralBearingCapacityCommand.getInstance();*/
		case FIND_ALL:
			 return FindAllCommand.getInstance();
		case CALCULATE_GENERAL_CAPACITY:
			return CalculateGeneralCapacityCommand.getInstance();
		case FIND_BY_FUEL_CONSUMPTION:
			return FindByFuelConsumptionCommand.getInstance();
		case SORT_AIRPLAINS_BY_MAX_RANGE:
			return SortAirplainsByMaxRangeCommand.getInstance();
		case LOAD_WITH_DOM:
			return LoadWithDomParserCommand.getInstance();
		default:
			throw new LogicalException("Enum doesn't contain this value.");
		}
	}

}
