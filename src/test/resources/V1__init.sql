create table UNICODE_TEST
(
	ID bigint identity constraint PK_MESSAGES_ID primary key,
	COMMENT varchar(10) not null,
	N_COMMENT nvarchar(10) not null
)
go