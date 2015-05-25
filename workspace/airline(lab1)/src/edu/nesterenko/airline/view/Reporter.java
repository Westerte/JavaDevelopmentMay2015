package edu.nesterenko.airline.view;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import edu.nesterenko.airline.bean.Response;
import edu.nesterenko.airline.bean.ResponseEnum;
import edu.nesterenko.airline.command.CommandEnum;
import edu.nesterenko.airline.entity.Airplane;
import edu.nesterenko.airline.exception.LogicalException;

public class Reporter {
	private final static Logger LOG = Logger.getLogger(Reporter.class);
	private Reporter() {}
	public static void report(CommandEnum command, Response response, String filePath) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
			String exitString = "";
			List<Airplane> airplanes = null;
			int sum;
			switch (command) {
			case ADD_AIRBUS:
				exitString = response.getParameter(ResponseEnum.IS_OK).toString() + "\n";
				break;
			case ADD_FREIGHTER:
				exitString = response.getParameter(ResponseEnum.IS_OK).toString() + "\n";
				break;
			case DELETE:
				exitString = response.getParameter(ResponseEnum.IS_OK).toString() + "\n";
				break;
			case FIND_ALL:
				airplanes = (List<Airplane>) response.getParameter(ResponseEnum.AIRPLANES_LIST);
				for(Airplane airplane : airplanes) {
					exitString += airplane.toString() + "\n";
				}
				break;
			case FIND_BY_FUEL_CONSUMPTION:
				airplanes = (List<Airplane>) response.getParameter(ResponseEnum.AIRPLANES_LIST);
				for(Airplane airplane : airplanes) {
					exitString += airplane.toString() + "\n";
				}
				break;
			case CALCULATE_GENERAL_BEARING_CAPACITY:
				sum = (int) response.getParameter(ResponseEnum.SUM);
				exitString += sum + "\n";
				break;
			case CALCULATE_GENERAL_CAPACITY:
				sum = (int) response.getParameter(ResponseEnum.SUM);
				exitString += sum + "\n";
				break;
			case SORT_AIRPLAINS_BY_MAX_RANGE:
				exitString = response.getParameter(ResponseEnum.IS_OK).toString() + "\n";
				break;
			case LOAD_FROM_HARDCODE:
				exitString = response.getParameter(ResponseEnum.IS_OK).toString() + "\n";
				break;
			case LOAD_WITH_SAX:
				exitString = response.getParameter(ResponseEnum.IS_OK).toString() + "\n";
				break;
			default:
				throw new LogicalException("Enum doesn't contain this value.");
			}		
			exitString = command + "\n" + exitString;
			writer.write(exitString);
		} catch (IOException | LogicalException e) {
			LOG.error(e);
		}
	}
}