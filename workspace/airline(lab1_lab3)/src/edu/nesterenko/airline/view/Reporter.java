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
	private static final Logger LOG = Logger.getLogger(Reporter.class);
	private Reporter() { }
	@SuppressWarnings("unchecked")
	public static void report(CommandEnum command, Response response, String filePath) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
			StringBuilder exitString = new StringBuilder();
			List<Airplane> airplanes = null;
			int sum;
			exitString.append(command);
			exitString.append('\n');
			switch (command) {
			case ADD_AIRBUS:
				exitString.append(response.getParameter(ResponseEnum.IS_OK).toString());
				break;
			case ADD_FREIGHTER:
				exitString.append(response.getParameter(ResponseEnum.IS_OK).toString());
				break;
			case DELETE:
				exitString.append(response.getParameter(ResponseEnum.IS_OK).toString());
				break;
			case FIND_ALL:
				airplanes = (List<Airplane>) response.getParameter(ResponseEnum.AIRPLANES_LIST);
				for(Airplane airplane : airplanes) {
					exitString.append(airplane.toString());
					exitString.append("\n");
				}
				break;
			case FIND_BY_FUEL_CONSUMPTION:
				airplanes = (List<Airplane>) response.getParameter(ResponseEnum.AIRPLANES_LIST);
				for (Airplane airplane : airplanes) {
					exitString.append(airplane.toString());
					exitString.append("\n");
				}
				break;
			case CALCULATE_GENERAL_BEARING_CAPACITY:
				sum = (int) response.getParameter(ResponseEnum.SUM);
				exitString.append(sum);
				break;
			case CALCULATE_GENERAL_CAPACITY:
				sum = (int) response.getParameter(ResponseEnum.SUM);
				exitString.append(sum);
				break;
			case SORT_AIRPLAINS_BY_MAX_RANGE:
				exitString.append(response.getParameter(ResponseEnum.IS_OK).toString());
				break;
			case LOAD_WITH_SAX:
				exitString.append(response.getParameter(ResponseEnum.IS_OK).toString());
				break;
			case LOAD_WITH_STAX:
				exitString.append(response.getParameter(ResponseEnum.IS_OK).toString());
				break;
			case LOAD_WITH_DOM:
				exitString.append(response.getParameter(ResponseEnum.IS_OK).toString());
				break;
			default:
				throw new LogicalException("Enum doesn't contain this value.");
			}		
			if (exitString.charAt(exitString.length() - 1) != '\n') {
				exitString.append("\n");
			}
			writer.write(exitString.toString());
		} catch (IOException | LogicalException e) {
			LOG.error(e);
		}
	}
}
