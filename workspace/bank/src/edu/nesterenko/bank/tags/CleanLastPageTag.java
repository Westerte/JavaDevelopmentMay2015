package edu.nesterenko.bank.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;

public class CleanLastPageTag extends TagSupport {
	
	private static final long serialVersionUID = 8674212300359981907L;

	@Override 
	public int doStartTag() {
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		HttpSession session = request.getSession(true);
		session.setAttribute("last_page", "");
		return SKIP_BODY;
	}

}
