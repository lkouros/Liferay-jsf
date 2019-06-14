package com.liferay.docs.guestbook.wrappers;

import javax.el.ELContext;
import javax.faces.context.ExternalContext;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.docs.guestbook.model.GuestbookWrapper;
import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.faces.util.jsp.PageContextAdapter;
import com.liferay.faces.util.jsp.StringJspWriter;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.util.PortalUtil;
import com.liferay.taglib.security.PermissionsURLTag;

public class Guestbook extends GuestbookWrapper {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6709856504813975602L;
	private static final String MODEL = "com.liferay.docs.guestbook.model.Guestbook";

	private Boolean viewable;
	private static final Logger logger = LoggerFactory
			.getLogger(Guestbook.class);

	private String permissionsUrl;
	private Boolean deleteable;
	private Boolean permissible;
	private Boolean updateable;

	public Guestbook(com.liferay.docs.guestbook.model.Guestbook guestbook) {
		super(guestbook);
	}

	public Boolean getViewable() {

		if (viewable == null) {
			LiferayFacesContext liferayFacesContext = LiferayFacesContext
					.getInstance();
			long scopeGroupId = liferayFacesContext.getScopeGroupId();
			viewable = liferayFacesContext
					.getThemeDisplay()
					.getPermissionChecker()
					.hasPermission(scopeGroupId, MODEL, getGuestbookId(),
							ActionKeys.VIEW);
		}

		return viewable;
	}

	public String getPermissonsUrl() {
		if (permissionsUrl == null) {

			LiferayFacesContext liferayFacesContext = LiferayFacesContext
					.getInstance();
			ExternalContext externalContext = liferayFacesContext
					.getExternalContext();
			long scopeGroupId = liferayFacesContext.getScopeGroupId();

			PortletRequest portletRequest = (PortletRequest) externalContext
					.getRequest();
			HttpServletRequest httpServletRequest = PortalUtil
					.getHttpServletRequest(portletRequest);
			PortletResponse portletResponse = (PortletResponse) externalContext
					.getResponse();
			HttpServletResponse httpServletResponse = PortalUtil
					.getHttpServletResponse(portletResponse);
			ELContext elContext = liferayFacesContext.getELContext();
			StringJspWriter stringJspWriter = new StringJspWriter();
			PageContextAdapter pageContextAdapter = new PageContextAdapter(
					httpServletRequest, httpServletResponse, elContext,
					stringJspWriter);

			PermissionsURLTag permissionsURLTag = new PermissionsURLTag();

			permissionsURLTag.setPageContext(pageContextAdapter);
			permissionsURLTag.setModelResource(MODEL);
			permissionsURLTag.setModelResourceDescription(getName());
			permissionsURLTag.setRedirect("false");
			permissionsURLTag.setResourceGroupId(scopeGroupId);
			permissionsURLTag.setResourcePrimKey(String
					.valueOf(getGuestbookId()));

			permissionsURLTag.setVar(null);

			try {
				permissionsURLTag.doStartTag();
				permissionsURLTag.doEndTag();
				permissionsUrl = stringJspWriter.toString();
			} catch (Exception e) {

				logger.error(e);
			}
		}
		return permissionsUrl;
	}

	public Boolean getDeleteable() {

		if (deleteable == null) {
			LiferayFacesContext liferayFacesContext = LiferayFacesContext
					.getInstance();
			long scopeGroupId = liferayFacesContext.getScopeGroupId();
			deleteable = liferayFacesContext
					.getThemeDisplay()
					.getPermissionChecker()
					.hasPermission(scopeGroupId, MODEL, getGuestbookId(),
							ActionKeys.DELETE);
		}

		return deleteable;
	}

	public Boolean getPermissible() {

		if (permissible == null) {
			LiferayFacesContext liferayFacesContext = LiferayFacesContext
					.getInstance();
			long scopeGroupId = liferayFacesContext.getScopeGroupId();
			permissible = liferayFacesContext
					.getThemeDisplay()
					.getPermissionChecker()
					.hasPermission(scopeGroupId, MODEL, getGuestbookId(),
							ActionKeys.PERMISSIONS);
		}

		return permissible;
	}

	public Boolean getUpdateable() {

		if (updateable == null) {
			LiferayFacesContext liferayFacesContext = LiferayFacesContext
					.getInstance();
			long scopeGroupId = liferayFacesContext.getScopeGroupId();
			updateable = liferayFacesContext
					.getThemeDisplay()
					.getPermissionChecker()
					.hasPermission(scopeGroupId, MODEL, getGuestbookId(),
							ActionKeys.UPDATE);
		}

		return updateable;
	}

}
