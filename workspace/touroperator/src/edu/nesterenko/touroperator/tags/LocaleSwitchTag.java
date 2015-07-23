package edu.nesterenko.touroperator.tags;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;

public class LocaleSwitchTag extends TagSupport {
	private static final long serialVersionUID = -5786988903569874024L;

	@Override 
	public int doStartTag() {
		
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		HttpSession session = request.getSession(true);
		Locale locale = (Locale) session.getAttribute("locale");
		try {
			if(locale == null || locale.equals(Locale.getDefault()) ) {	
				pageContext.include("/jspf/locale_links_en.jspf");		
			} else {
				pageContext.include("/jspf/locale_links_ru.jspf");
			}
		} catch(ServletException | IOException e) {
			e.printStackTrace();
			System.out.println("kajsd;lkf");
		}
		return SKIP_BODY;
	}
}
