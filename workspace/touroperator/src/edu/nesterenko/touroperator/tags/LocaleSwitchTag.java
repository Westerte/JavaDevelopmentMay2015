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
		if(locale == null) {
			locale = Locale.getDefault();
		}
		String localeToString = locale.toString();
		try {
			if("ru_RU".equals(localeToString)) {
				pageContext.include("/jspf/locale_links_ru.jspf");
			} else {
				pageContext.include("/jspf/locale_links_en.jspf");
			}
		} catch(ServletException | IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
}