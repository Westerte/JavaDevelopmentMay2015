package edu.nesterenko.bank.command;

import java.text.SimpleDateFormat;
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


public class EditPageCommand implements Command {
	private final static Logger LOG = Logger.getLogger(EditPageCommand.class);
	private static EditPageCommand instance = new EditPageCommand();
	
	private EditPageCommand() {}
	
	public static EditPageCommand getInstance() {
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		String jspPath;
		String id = request.getParameter("id");
		SimpleDateFormat dateFormat = new SimpleDateFormat(ConfigurationManager.getProperty("date.format"));
		try {
			Client client = ClientLogic.getClientById(id);
			request.setAttribute("id", client.getId());
			request.setAttribute("secondName", client.getSecondName());
			request.setAttribute("firstName", client.getFirstName());
			request.setAttribute("patronimic", client.getPatronimic());
			request.setAttribute("birthday", dateFormat.format(client.getBirthday()));
			request.setAttribute("male", client.getMale());
			request.setAttribute("passportSeria", client.getPassportSeria());
			request.setAttribute("passportNumber", client.getPassportNumber());
			request.setAttribute("passportPlace", client.getPassportPlace());
			request.setAttribute("passportDate", dateFormat.format(client.getPassportDate()));
			request.setAttribute("cid", client.getCid());
			request.setAttribute("birthplace", client.getBirthplace());
			request.setAttribute("currentCity", client.getCurrentCity().getId());
			request.setAttribute("currentAddress", client.getCurrentAddress());
			request.setAttribute("phoneHome", client.getPhoneHome());
			request.setAttribute("phoneMobile", client.getPhoneMobile());
			request.setAttribute("email", client.getEmail());
			request.setAttribute("workPlace", client.getWorkPlace());
			request.setAttribute("position", client.getPosition());
			request.setAttribute("registrationAddress", client.getRegistrationAddress());
			request.setAttribute("martial", client.getMartial().getId());
			request.setAttribute("nationality", client.getNationality().getId());
			request.setAttribute("disability", client.getDisability().getId());
			request.setAttribute("pensioner", client.isPensioner());
			request.setAttribute("income", client.getIncome());
			List<City> cityList = CityLogic.findAll();
			List<Martial> martialList = MartialLogic.findAll();
			List<Nationality> nationalityList = NationalityLogic.findAll();
			List<Disability> disabilityList = DisabilityLogic.findAll();
			request.setAttribute("cityList", cityList);
			request.setAttribute("martialList", martialList);
			request.setAttribute("nationalityList", nationalityList);
			request.setAttribute("disabilityList", disabilityList);
			jspPath = ConfigurationManager.getProperty("path.page.add");
			jspPath = ConfigurationManager.getProperty("path.page.edit");
		} catch (LogicException e) {
			LOG.error(e);
			request.setAttribute("status", "problems");
			jspPath = ConfigurationManager.getProperty("path.page.main");
		}
		return jspPath;
	}
}
