package edu.nesterenko.airline.command;

import edu.nesterenko.airline.exception.LogicalException;

public class CommandFactory {
	public static Command getCommand(CommandEnum command) throws LogicalException {
		switch (command) {
		case ADD_AIRBUS:
			return AddAirbusCommand.getInstance();
		case ADD_FREIGHTER:
			return AddFreighterCommand.getInstance();
		case DELETE:
			return DeleteCommand.getInstance();
		case FIND_ALL:
			 return FindAllCommand.getInstance();
		case FIND_BY_FUEL_CONSUMPTION:
			return FindByFuelConsumptionCommand.getInstance();
		case CALCULATE_GENERAL_BEARING_CAPACITY:
			return CalculateGeneralBearingCapacityCommand.getInstance();
		case CALCULATE_GENERAL_CAPACITY:
			return CalculateGeneralCapacityCommand.getInstance();
		case SORT_AIRPLAINS_BY_MAX_RANGE:
			return SortAirplainsByMaxRangeCommand.getInstance();
		case LOAD_FROM_HARDCODE:
			return LoadFromHardcodeCommand.getInstance();
		case LOAD_WITH_SAX:
			return LoadWithSaxParserCommand.getInstance();
		case LOAD_WITH_STAX:
			return LoadWithStaxParserCommand.getInstance();
		case LOAD_WITH_DOM:
			return LoadWithStaxParserCommand.getInstance();
		default:
			throw new LogicalException("Enum doesn't contain this value.");
		}		
	}
}
