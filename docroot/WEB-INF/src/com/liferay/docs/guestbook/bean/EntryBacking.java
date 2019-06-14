package com.liferay.docs.guestbook.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.liferay.docs.guestbook.service.EntryLocalServiceUtil;
import com.liferay.docs.guestbook.service.persistence.EntryUtil;
import com.liferay.docs.guestbook.wrappers.Entry;
import com.liferay.faces.portal.context.LiferayFacesContext;

@ManagedBean
@RequestScoped
public class EntryBacking extends AbstractBacking {
	
	private Boolean hasAddPermission;
	@ManagedProperty(name = "guestbookBacking", value = "#{guestbookBacking}")
	protected GuestbookBacking guestbookBacking;

	public Boolean getHasAddPermission() {

		if (hasAddPermission == null) {
			LiferayFacesContext liferayFacesContext = LiferayFacesContext
					.getInstance();
			long scopeGroupId = liferayFacesContext.getScopeGroupId();
			hasAddPermission = liferayFacesContext
					.getThemeDisplay()
					.getPermissionChecker()
					.hasPermission(scopeGroupId, GuestbookBacking.MODEL,
							scopeGroupId, "ADD_ENTRY");
		}

		return hasAddPermission;
	}

	public void setHasAddPermission(Boolean hasAddPermission) {
		this.hasAddPermission = hasAddPermission;
	}

	public void add() {
		Entry entry = new Entry(EntryUtil.create(0L));
		LiferayFacesContext liferayFacesContext = LiferayFacesContext
				.getInstance();
		entry.setGroupId(liferayFacesContext.getScopeGroupId());
		entry.setGuestbookId(guestbookBacking.getSelectedGuestbook()
				.getGuestbookId());
		guestbookBacking.setSelectedEntry(entry);
		guestbookBacking.editEntry();
	}

	public void cancel() {
		guestbookBacking.select(guestbookBacking.getSelectedGuestbook());
	}

	public void save() {

		Entry entry = guestbookBacking.getSelectedEntry();
		LiferayFacesContext liferayFacesContext = LiferayFacesContext
				.getInstance();
		entry.setCompanyId(liferayFacesContext.getCompanyId());
		entry.setUserId(liferayFacesContext.getUserId());

		try {

			if (entry.getEntryId() == 0) {
				EntryLocalServiceUtil.addEntry(entry,
						liferayFacesContext.getUserId());
			} else {
				EntryLocalServiceUtil.updateEntry(entry);
			}

			addGlobalSuccessInfoMessage();
		} catch (Exception e) {
			addGlobalUnexpectedErrorMessage();
			logger.error(e);
		}

		guestbookBacking.select(guestbookBacking.getSelectedGuestbook());
	}

	public void setGuestbookBacking(GuestbookBacking guestbookBacking) {
		this.guestbookBacking = guestbookBacking;
	}

	public void delete(Entry entry) {

	     try {
	         EntryLocalServiceUtil.deleteEntry(entry);
	         addGlobalSuccessInfoMessage();
	     }
	     catch (Exception e) {
	         addGlobalUnexpectedErrorMessage();
	         logger.error(e);
	     }

	     guestbookBacking.select(guestbookBacking.getSelectedGuestbook());
	 }
	
	public void edit(Entry entry) {
	     guestbookBacking.setSelectedEntry(entry);
	     guestbookBacking.editEntry();
	 }
	
	
}
