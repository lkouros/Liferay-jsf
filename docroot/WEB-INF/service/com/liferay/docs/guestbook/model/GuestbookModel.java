/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.docs.guestbook.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.StagedGroupedModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the Guestbook service. Represents a row in the &quot;GB_Guestbook&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.docs.guestbook.model.impl.GuestbookModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.docs.guestbook.model.impl.GuestbookImpl}.
 * </p>
 *
 * @author kourosl
 * @see Guestbook
 * @see com.liferay.docs.guestbook.model.impl.GuestbookImpl
 * @see com.liferay.docs.guestbook.model.impl.GuestbookModelImpl
 * @generated
 */
public interface GuestbookModel extends BaseModel<Guestbook>, StagedGroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a guestbook model instance should use the {@link Guestbook} interface instead.
	 */

	/**
	 * Returns the primary key of this guestbook.
	 *
	 * @return the primary key of this guestbook
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this guestbook.
	 *
	 * @param primaryKey the primary key of this guestbook
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this guestbook.
	 *
	 * @return the uuid of this guestbook
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this guestbook.
	 *
	 * @param uuid the uuid of this guestbook
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the guestbook ID of this guestbook.
	 *
	 * @return the guestbook ID of this guestbook
	 */
	public long getGuestbookId();

	/**
	 * Sets the guestbook ID of this guestbook.
	 *
	 * @param guestbookId the guestbook ID of this guestbook
	 */
	public void setGuestbookId(long guestbookId);

	/**
	 * Returns the group ID of this guestbook.
	 *
	 * @return the group ID of this guestbook
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this guestbook.
	 *
	 * @param groupId the group ID of this guestbook
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this guestbook.
	 *
	 * @return the company ID of this guestbook
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this guestbook.
	 *
	 * @param companyId the company ID of this guestbook
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this guestbook.
	 *
	 * @return the user ID of this guestbook
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this guestbook.
	 *
	 * @param userId the user ID of this guestbook
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this guestbook.
	 *
	 * @return the user uuid of this guestbook
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this guestbook.
	 *
	 * @param userUuid the user uuid of this guestbook
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this guestbook.
	 *
	 * @return the user name of this guestbook
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this guestbook.
	 *
	 * @param userName the user name of this guestbook
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this guestbook.
	 *
	 * @return the create date of this guestbook
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this guestbook.
	 *
	 * @param createDate the create date of this guestbook
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this guestbook.
	 *
	 * @return the modified date of this guestbook
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this guestbook.
	 *
	 * @param modifiedDate the modified date of this guestbook
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the name of this guestbook.
	 *
	 * @return the name of this guestbook
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this guestbook.
	 *
	 * @param name the name of this guestbook
	 */
	public void setName(String name);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(com.liferay.docs.guestbook.model.Guestbook guestbook);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.docs.guestbook.model.Guestbook> toCacheModel();

	@Override
	public com.liferay.docs.guestbook.model.Guestbook toEscapedModel();

	@Override
	public com.liferay.docs.guestbook.model.Guestbook toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}