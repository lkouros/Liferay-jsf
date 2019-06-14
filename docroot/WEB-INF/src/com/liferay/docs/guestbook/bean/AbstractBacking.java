package com.liferay.docs.guestbook.bean;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;

abstract class AbstractBacking {

	protected static final Logger logger = LoggerFactory
			.getLogger(AbstractBacking.class);

	protected static final String UNEXPECTED_ERROR_MSG_ID = "an-unexpected-error-occurred";
	protected static final String SUCCESS_INFO_MSG_ID = "your-request-processed-successfully";
	
	public void addGlobalSuccessInfoMessage() {
	     LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
	     liferayFacesContext.addGlobalSuccessInfoMessage();
	 }

	 public void addGlobalUnexpectedErrorMessage() {
	     LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
	     liferayFacesContext.addGlobalUnexpectedErrorMessage();
	 }
	 
	 
}
