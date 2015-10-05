package edu.nesterenko.bank.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import edu.nesterenko.bank.entity.City;
import edu.nesterenko.bank.entity.Client;
import edu.nesterenko.bank.entity.Disability;
import edu.nesterenko.bank.entity.Martial;
import edu.nesterenko.bank.entity.Nationality;
import edu.nesterenko.bank.logic.CityLogic;
import edu.nesterenko.bank.logic.ClientLogic;
import edu.nesterenko.bank.logic.DisabilityLogic;
import edu.nesterenko.bank.logic.LogicException;
import edu.nesterenko.bank.logic.MartialLogic;
import edu.nesterenko.bank.logic.NationalityLogic;
import edu.nesterenko.bank.resource.ConfigurationManager;

public class AddCommand implements Command {
	private final static Logger LOG = Logger.getLogger(AddCommand.class);
	private static AddCommand instance = new AddCommand();
	
	private AddCommand() {}
	
	public static AddCommand getInstance() {
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		String jspPath;
		String secondName = request.getParameter("secondName");
		String firstName = request.getParameter("firstName");
		String patronimic = request.getParameter("patronimic");
		String birthday = request.getParameter("birthday");
		String male = request.getParameter("male");
		String passportSeria = request.getParameter("passportSeria");
		String passportNumber = request.getParameter("passportNumber");
		String passportPlace = request.getParameter("passportPlace");
		String passportDate = request.getParameter("passportDate");
		String cid = request.getParameter("cid");
		String birthplace = request.getParameter("birthplace");
		String currentCity = request.getParameter("currentCity");
		String currentAddress = request.getParameter("currentAddress");
		String phoneHome = request.getParameter("phoneHome");
		String phoneMobile = request.getParameter("phoneMobile");
		String email = request.getParameter("email");
		String workPlace = request.getParameter("workPlace");
		String position = request.getParameter("position");
		String registrationAddress = request.getParameter("registrationAddress");
		String martial = request.getParameter("martial");
		String nationality = request.getParameter("nationality");
		String disability = request.getParameter("disability");
		String pensioner = request.getParameter("pensioner");
		String income = request.getParameter("income");
		try {
			ClientLogic.addClient(secondName, firstName, patronimic, birthday, male, passportSeria, passportNumber, passportPlace, 
					passportDate, cid, birthplace, currentCity, currentAddress, phoneHome, phoneMobile, email, workPlace, position,
					registrationAddress, martial, nationality, disability, pensioner, income);
			List<Client> clientList = ClientLogic.findAll();
			request.setAttribute("clientList", clientList);
			jspPath = ConfigurationManager.getProperty("path.page.main");
		} catch (LogicException e) {
			LOG.error(e);
			try {
				List<City> cityList = CityLogic.findAll();
				List<Martial> martialList = MartialLogic.findAll();
				List<Nationality> nationalityList = NationalityLogic.findAll();
				List<Disability> disabilityList = DisabilityLogic.findAll();
				request.setAttribute("cityList", cityList);
				request.setAttribute("martialList", martialList);
				request.setAttribute("nationalityList", nationalityList);
				request.setAttribute("disabilityList", disabilityList);
			} catch (LogicException e1) {
				LOG.error(e1);
			}
			request.setAttribute("status", "logicProblems");
			request.setAttribute("secondName", secondName);
			request.setAttribute("firstName", firstName);
			request.setAttribute("patronimic", patronimic);
			request.setAttribute("birthday", birthday);
			request.setAttribute("male", male);
			request.setAttribute("passportSeria", passportSeria);
			request.setAttribute("passportNumber", passportNumber);
			request.setAttribute("passportPlace", passportPlace);
			request.setAttribute("passportDate", passportDate);
			request.setAttribute("cid", cid);
			request.setAttribute("birthplace", birthplace);
			request.setAttribute("currentCity", currentCity);
			request.setAttribute("currentAddress", currentAddress);
			request.setAttribute("phoneHome", phoneHome);
			request.setAttribute("phoneMobile", phoneMobile);
			request.setAttribute("email", email);
			request.setAttribute("workPlace", workPlace);
			request.setAttribute("position", position);
			request.setAttribute("registrationAddress", registrationAddress);
			request.setAttribute("martial", martial);
			request.setAttribute("nationality", nationality);
			request.setAttribute("disability", disability);
			request.setAttribute("pensioner", pensioner);
			request.setAttribute("income", income);
			jspPath = ConfigurationManager.getProperty("path.page.add");			
		}		
		return jspPath;
	}
}
