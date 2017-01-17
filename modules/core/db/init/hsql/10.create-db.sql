-- begin SAMPLE_CUSTOMER
create table SAMPLE_CUSTOMER (
    ID bigint not null,
    UUID varchar(36),
    --
    NAME varchar(255),
    EMAIL varchar(255),
    DETAILS_ID bigint,
    --
    primary key (ID)
)^
-- end SAMPLE_CUSTOMER
-- begin SAMPLE_DETAILS
create table SAMPLE_DETAILS (
    ID bigint not null,
    ADDRESS varchar(255),
    DESCRIPTION varchar(255),
    --
    primary key (ID)
)^
-- end SAMPLE_DETAILS
