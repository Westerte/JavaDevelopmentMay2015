package edu.nesterenko.touroperator.command;

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
				case COUNTRY_LIST_PAGE:
					command = CountryListPageCommand.getInstance();
					break;
				case PROFILE_PAGE:
					command = ProfilePageCommand.getInstance();
					break;
				case ADMIN_PANEL_PAGE: 
					command = AdminPanelPageCommand.getInstance();
					break;
				case ADD_COUNTRY:
					command = AddCountryCommand.getInstance();
					break;
				case CITY_LIST_PAGE:
					command = CityListPageCommand.getInstance();
					break;
				case ADD_CITY:
					command = AddCityCommand.getInstance();
					break;
				case RESORT_LIST_PAGE: 
					command = ResortListPageCommand.getInstance();
					break;
				case ADD_RESORT:
					command = AddResortCommand.getInstance();
					break;
				case RESORT_HOTEL_LIST_PAGE:
					command = ResortHotelListPageCommand.getInstance();
					break;
				case ADD_RESORT_HOTEL:
					command = AddResortHotelCommand.getInstance();
					break;
				case REST_TYPE_LIST_PAGE:
					command = RestTypeListPageCommand.getInstance();
					break;
				case ADD_REST_TYPE: 
					command = AddRestTypeCommand.getInstance();
					break;
				case TOUR_LIST_PAGE:
					command = TourListPageCommand.getInstance();
					break;
				case ADD_TOUR:
					command = AddTourCommand.getInstance();
					break;
			}
		}
		return command;
	}

}
