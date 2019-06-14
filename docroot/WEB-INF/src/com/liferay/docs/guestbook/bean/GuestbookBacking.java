package com.liferay.docs.guestbook.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.liferay.docs.guestbook.service.EntryLocalServiceUtil;
import com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil;
import com.liferay.docs.guestbook.service.persistence.GuestbookUtil;
import com.liferay.docs.guestbook.wrappers.Entry;
import com.liferay.docs.guestbook.wrappers.Guestbook;
import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.portal.kernel.exception.SystemException;

@ManagedBean
@ViewScoped
public class GuestbookBacking extends AbstractBacking {

	public static final String MODEL = "com.liferay.docs.guestbook.model";

	private Boolean hasAddPermission;
	private Boolean hasViewPermission;

	public static final String DEFAULT_GUESTBOOK_NAME = "Main";

	private Guestbook originalGuestbook;

	private Guestbook selectedGuestbook;
	private List<Guestbook> guestbooks;

	private Entry selectedEntry;
	private List<Entry> entries;

	
	private boolean editingGuestbook;
	private boolean editingEntry;

	public void add() {
		setOriginalGuestbook(getSelectedGuestbook());

		Guestbook guestbook = new Guestbook(GuestbookUtil.create(0L));
		LiferayFacesContext liferayFacesContext = LiferayFacesContext
				.getInstance();
		guestbook.setGroupId(liferayFacesContext.getScopeGroupId());
		setSelectedGuestbook(guestbook);
		editGuestbook();
	}

	public void cancel() {
		select(getOriginalGuestbook());
	}

	public void createMainGuestbook() {

		try {

			LiferayFacesContext liferayFacesContext = LiferayFacesContext
					.getInstance();
			long scopeGroupId = liferayFacesContext.getScopeGroupId();

			com.liferay.docs.guestbook.model.Guestbook defaultGuestbook = (com.liferay.docs.guestbook.model.Guestbook) GuestbookLocalServiceUtil
					.getFirstGuestbookByName(scopeGroupId,
							DEFAULT_GUESTBOOK_NAME);

			// Create the default guestbook if it does not exist in the database
			if (defaultGuestbook == null) {
				logger.info("postConstruct: creating a default guestbook named "
						+ DEFAULT_GUESTBOOK_NAME + " ...");

				Guestbook guestbook = new Guestbook(GuestbookUtil.create(0L));
				guestbook.setName(DEFAULT_GUESTBOOK_NAME);
				guestbook.setGroupId(scopeGroupId);
				guestbook.setCompanyId(liferayFacesContext.getCompanyId());
				guestbook.setUserId(liferayFacesContext.getUserId());
				GuestbookLocalServiceUtil.addGuestbook(guestbook);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void editEntry() {
		editingEntry = true;
		editingGuestbook = false;
	}

	public void editGuestbook() {
		editingEntry = false;
		editingGuestbook = true;
	}

	@PostConstruct
	public void postConstruct() {
		createMainGuestbook();
	}

	public void save() {
		Guestbook guestbook = getSelectedGuestbook();
		LiferayFacesContext liferayFacesContext = LiferayFacesContext
				.getInstance();
		guestbook.setCompanyId(liferayFacesContext.getCompanyId());
		guestbook.setUserId(liferayFacesContext.getUserId());

		try {

			if (guestbook.getGuestbookId() == 0) {
				guestbook = new Guestbook(
						GuestbookLocalServiceUtil.addGuestbook(guestbook,
								liferayFacesContext.getUserId()));
			} else {
				guestbook = new Guestbook(
						GuestbookLocalServiceUtil.updateGuestbook(guestbook));
			}

			addGlobalSuccessInfoMessage();
		} catch (Exception e) {
			addGlobalUnexpectedErrorMessage();
			logger.error(e);
		}

		// Go back to master view
		select(guestbook);
	}

	public void select(Guestbook guestbook) {

		if (guestbook == null) {
			setSelectedGuestbook(null);
		} else {
			setSelectedGuestbook(guestbook);
		}

		// Force Guestbooks and Entries to reload
		setGuestbooks(null);
		setEntries(null);

		editingEntry = false;
		editingGuestbook = false;
	}

	public void setEditingEntry(boolean editingEntry) {
		this.editingEntry = editingEntry;
	}

	public void setEditingGuestbook(boolean editingGuestbook) {
		this.editingGuestbook = editingGuestbook;
	}

	public List<Entry> getEntries() {

		if (entries == null) {
			long scopeGroupId = LiferayFacesContext.getInstance()
					.getScopeGroupId();
			Guestbook selectedGuestbook = getSelectedGuestbook();

			try {
				entries = new ArrayList<Entry>();

				if (selectedGuestbook == null) {
					logger.info("getEntries: selectedGuestbook == null ... ");
				} else {
					List<com.liferay.docs.guestbook.model.Entry> list = EntryLocalServiceUtil
							.getEntries(scopeGroupId,
									selectedGuestbook.getGuestbookId());

					for (com.liferay.docs.guestbook.model.Entry entry : list) {
						entries.add(new Entry(entry));
					}
				}

			} catch (SystemException e) {
				logger.error(e);
			}
		}

		return entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}

	public List<Guestbook> getGuestbooks() {

		if (guestbooks == null) {
			long scopeGroupId = LiferayFacesContext.getInstance()
					.getScopeGroupId();

			try {
				guestbooks = new ArrayList<Guestbook>();

				List<com.liferay.docs.guestbook.model.Guestbook> list = GuestbookLocalServiceUtil
						.getGuestbooks(scopeGroupId);

				for (com.liferay.docs.guestbook.model.Guestbook guestbook : list) {
					guestbooks.add(new Guestbook(guestbook));
				}
			} catch (SystemException e) {
				logger.error(e);
			}
		}

		logger.info("getGuestbooks: guestbooks.size() = " + guestbooks.size());

		return guestbooks;
	}

	public void setGuestbooks(List<Guestbook> guestbooks) {
		this.guestbooks = guestbooks;
	}

	public Guestbook getOriginalGuestbook() {
		return originalGuestbook;
	}

	public void setOriginalGuestbook(Guestbook originalGuestbook) {
		this.originalGuestbook = originalGuestbook;
	}

	public Entry getSelectedEntry() {
		return selectedEntry;
	}

	public void setSelectedEntry(Entry selectedEntry) {
		this.selectedEntry = selectedEntry;
	}

	public Guestbook getSelectedGuestbook() {

		if (selectedGuestbook == null) {
			long scopeGroupId = LiferayFacesContext.getInstance()
					.getScopeGroupId();

			try {
				com.liferay.docs.guestbook.model.Guestbook firstGuestbookByName = (com.liferay.docs.guestbook.model.Guestbook) GuestbookLocalServiceUtil
						.getFirstGuestbookByName(scopeGroupId,
								DEFAULT_GUESTBOOK_NAME);

				if (firstGuestbookByName == null) {
					logger.info("getSelectedGuestbook: No Guestbook named "
							+ DEFAULT_GUESTBOOK_NAME);
				} else {
					selectedGuestbook = new Guestbook(firstGuestbookByName);
				}
			} catch (SystemException e) {
				logger.error(e);
			}
		}

		return selectedGuestbook;
	}

	public void setSelectedGuestbook(Guestbook selectedGuestbook) {
		this.selectedGuestbook = selectedGuestbook;
	}

	public boolean isEditingEntry() {
		return editingEntry;
	}

	public boolean isEditingGuestbook() {
		return editingGuestbook;
	}

	public Boolean getHasAddPermission() {

		if (hasAddPermission == null) {
			LiferayFacesContext liferayFacesContext = LiferayFacesContext
					.getInstance();
			long scopeGroupId = liferayFacesContext.getScopeGroupId();
			hasAddPermission = liferayFacesContext
					.getThemeDisplay()
					.getPermissionChecker()
					.hasPermission(scopeGroupId, MODEL, scopeGroupId,
							"ADD_GUESTBOOK");
		}

		return hasAddPermission;
	}

	public void setHasAddPermission(Boolean hasAddPermission) {
		this.hasAddPermission = hasAddPermission;
	}

	public Boolean getHasViewPermission() {

		if (hasViewPermission == null) {
			LiferayFacesContext liferayFacesContext = LiferayFacesContext
					.getInstance();
			long scopeGroupId = liferayFacesContext.getScopeGroupId();
			hasViewPermission = liferayFacesContext
					.getThemeDisplay()
					.getPermissionChecker()
					.hasPermission(scopeGroupId,
							"com.liferay.docs.guestbook.model.Guestbook",
							scopeGroupId, "VIEW");
		}

		return hasViewPermission;
	}

	public void setHasViewPermission(Boolean hasViewPermission) {
		this.hasViewPermission = hasViewPermission;
	}

	public void delete(Guestbook guestbook) {

		entries = getEntries();
		deleteGuestbookEntries();

		try {
			GuestbookLocalServiceUtil.deleteGuestbook(guestbook);
			addGlobalSuccessInfoMessage();
		} catch (Exception e) {
			addGlobalUnexpectedErrorMessage();
			logger.error(e);

		}

		if (DEFAULT_GUESTBOOK_NAME.equals(guestbook.getName())) {
			createMainGuestbook();

		}

		this.selectedGuestbook = null;

		setGuestbooks(null);
		setEntries(null);
		select(null);
	}

	public void deleteGuestbookEntries() {

		for (Entry entry : entries) {

			try {

				EntryLocalServiceUtil.deleteEntry(entry);
				addGlobalSuccessInfoMessage();
			} catch (Exception e) {
				addGlobalUnexpectedErrorMessage();
				logger.error(e);
			}

		}
	}
	
	public void edit(Guestbook guestbook){
		
		setSelectedGuestbook(guestbook);
		editGuestbook();
	}
}