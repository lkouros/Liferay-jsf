create index IX_F5591FB6 on GB_Entry (groupId, guestbookId);

create index IX_9294AD47 on GB_Guestbook (groupId);
create index IX_FA13ABC6 on GB_Guestbook (groupId, name);
create index IX_ABB2E591 on GB_Guestbook (uuid_);
create index IX_9314A9F7 on GB_Guestbook (uuid_, companyId);
create unique index IX_EDD4239 on GB_Guestbook (uuid_, groupId);