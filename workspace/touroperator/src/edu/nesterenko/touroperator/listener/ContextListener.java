package edu.nesterenko.touroperator.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.PropertyConfigurator;

@WebListener
public class ContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		String absoluteConfigPath = servletContext.getRealPath("") + servletContext.getInitParameter("log4j-config-path");
		System.setProperty("log4j-log-path", servletContext.getInitParameter("log4j-log-path"));
		PropertyConfigurator.configure(absoluteConfigPath);
	}

}
