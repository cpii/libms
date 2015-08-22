-- SECURITY SEED DATA --
-- Groups
insert into groups (name) values ('Administrators')
insert into groups (name) values ('Staff')
insert into groups (name) values ('Members')
insert into groups (name) values ('Users')
-- Authorities
insert into authority (authority)  values ('ROLE_ADMINISTRATOR');
insert into authority (authority)  values ('ROLE_STAFF');
insert into authority (authority)  values ('ROLE_MEMBER');
insert into authority (authority)  values ('ROLE_USER');
-- Group Authorities
insert into group_authority (group_id, authority_id) select g.group_id, authority_id from groups g, authority where g.name = 'Administrators' and authority = 'ROLE_ADMINISTRATOR';
insert into group_authority (group_id, authority_id) select g.group_id, authority_id from groups g, authority where g.name = 'Administrators' and authority = 'ROLE_USER';
insert into group_authority (group_id, authority_id) select g.group_id, authority_id from groups g, authority where g.name = 'Staff' and authority = 'ROLE_STAFF';
insert into group_authority (group_id, authority_id) select g.group_id, authority_id from groups g, authority where g.name = 'Staff' and authority = 'ROLE_USER';
insert into group_authority (group_id, authority_id) select g.group_id, authority_id from groups g, authority where g.name = 'Members' and authority = 'ROLE_MEMBER';
insert into group_authority (group_id, authority_id) select g.group_id, authority_id from groups g, authority where g.name = 'Members' and authority = 'ROLE_USER';
insert into group_authority (group_id, authority_id) select g.group_id, authority_id from groups g, authority where g.name = 'Users' and authority = 'ROLE_USER';
-- USERS
insert into users (USERNAME, FIRST_NAME, MIDDLE_NAME, LAST_NAME, PASSWORD, SALT, EMAIL, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CREDENTIALS_NON_EXPIRED, ENABLED, deleted, FAILED_LOGIN_ATTEMPTS, CREATED_BY, CREATED_ON, OPTLOCK, UPDATED_BY, UPDATED_ON) select 'admin1', 'John', 'D', 'Administrator', 'password', 'salt', 'admin1@example.com', 1, 1, 1, 1, 0, 0, 'SYSTEM', getdate(), 0, null, null;
insert into users (USERNAME, FIRST_NAME, MIDDLE_NAME, LAST_NAME, PASSWORD, SALT, EMAIL, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CREDENTIALS_NON_EXPIRED, ENABLED, deleted, FAILED_LOGIN_ATTEMPTS, CREATED_BY, CREATED_ON, OPTLOCK, UPDATED_BY, UPDATED_ON) select 'staff1', 'Sally', 'T', 'Staff', 'password', 'salt', 'staff1@example.com', 1, 1, 1, 1, 0, 0, 'SYSTEM', getdate(), 0, null, null;
insert into users (USERNAME, FIRST_NAME, MIDDLE_NAME, LAST_NAME, PASSWORD, SALT, EMAIL, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CREDENTIALS_NON_EXPIRED, ENABLED, deleted, FAILED_LOGIN_ATTEMPTS, CREATED_BY, CREATED_ON, OPTLOCK, UPDATED_BY, UPDATED_ON) select 'member1', 'Mary', 'N', 'Member', 'password', 'salt', 'member1@example.com', 1, 1, 1, 1, 0, 0, 'SYSTEM', getdate(), 0, null, null;
insert into users (USERNAME, FIRST_NAME, MIDDLE_NAME, LAST_NAME, PASSWORD, SALT, EMAIL, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CREDENTIALS_NON_EXPIRED, ENABLED, deleted, FAILED_LOGIN_ATTEMPTS, CREATED_BY, CREATED_ON, OPTLOCK, UPDATED_BY, UPDATED_ON) select 'member2', 'Jake', 'N', 'Member', 'password', 'salt', 'member2@example.com', 1, 1, 1, 1, 0, 0, 'SYSTEM', getdate(), 0, null, null;
-- 
insert into group_members select u.users_id, g.group_id from users u, groups g where g.name = 'Administrators' and u.username = 'admin1';
insert into group_members select u.users_id, g.group_id from users u, groups g where g.name = 'Staff' and u.username = 'staff1';
insert into group_members select u.users_id, g.group_id from users u, groups g where g.name = 'Members' and u.username = 'member1';
insert into group_members select u.users_id, g.group_id from users u, groups g where g.name = 'Members' and u.username = 'member2';

-- Books and stuff
insert into PUBLISHER (name, email, phone) values ('Random House', 'random@house.com', 212-323-9876);
insert into PUBLISHER (name, email, phone) values ('Bantum Books', 'bantum@books.com', 313-323-9876);
insert into PUBLISHER (name, email, phone) values ('Penguin', 'penguin@antartica.com', 414-323-9876);

insert into Author (BIRTHDAY, FIRST_NAME, LAST_NAME, Gender, deleted, optlock) values ('1875-07-25', 'James', 'Corbett', 'M', 0, 0);
insert into Author (BIRTHDAY, FIRST_NAME, LAST_NAME, Gender, deleted, optlock) values ('1880-03-21', 'James', 'Kirk', 'M', 0, 0);

insert into book (DELETED, OPTLOCK, cost, ISBN, DATE_PUBLISHED, TITLE, PUBLISHER_ID) select 0, 0, 12.95, '1000EE', '1935-05-23', 'The Temple Tiger', publisher_id from PUBLISHER where name = 'Random House';
insert into book (DELETED, OPTLOCK, cost, ISBN, DATE_PUBLISHED, TITLE, PUBLISHER_ID) select 0, 0, 10.25, '1000EF', '1945-03-13', 'Jungle Lore', publisher_id from PUBLISHER where name = 'Random House';
insert into book (DELETED, OPTLOCK, cost, ISBN, DATE_PUBLISHED, TITLE, PUBLISHER_ID) select 0, 0, 3.75, '1100EF', '1975-01-02', 'Spock''s Logic', publisher_id from PUBLISHER where name = 'Bantum Books';
insert into book (DELETED, OPTLOCK, cost, ISBN, DATE_PUBLISHED, TITLE, PUBLISHER_ID) select 0, 0, 10.25, '1200EF', '1945-03-13', 'Space, the final frontier', publisher_id from PUBLISHER where name = 'Bantum Books';

insert into BOOK_AUTHOR (BOOK_ID,AUTHOR_ID) select book_id, author_id from book, author where book.title = 'The Temple Tiger' and author.LAST_NAME = 'Corbett';
insert into BOOK_AUTHOR (BOOK_ID,AUTHOR_ID) select book_id, author_id from book, author where book.title = 'Jungle Lore' and author.LAST_NAME = 'Corbett';
insert into BOOK_AUTHOR (BOOK_ID,AUTHOR_ID) select book_id, author_id from book, author where book.title = 'Spock''s Logic' and author.LAST_NAME = 'Kirk';
insert into BOOK_AUTHOR (BOOK_ID,AUTHOR_ID) select book_id, author_id from book, author where book.title = 'Space, the final frontier' and author.LAST_NAME = 'Kirk';

insert into BOOK_COPY (DELETED, OPTLOCK, IS_AVAILBLE, CONDITION, LOCATION, SEQUENCE_ID, BOOK_ID) select 0, 0, 1, 'New', '23E', 1001, book_id from book where title = 'The Temple Tiger';
insert into BOOK_COPY (DELETED, OPTLOCK, IS_AVAILBLE, CONDITION, LOCATION, SEQUENCE_ID, BOOK_ID) select 0, 0, 1, 'New', '23E', 1002, book_id from book where title = 'The Temple Tiger';
insert into BOOK_COPY (DELETED, OPTLOCK, IS_AVAILBLE, CONDITION, LOCATION, SEQUENCE_ID, BOOK_ID) select 0, 0, 1, 'New', '23E', 1003, book_id from book where title = 'The Temple Tiger';
insert into BOOK_COPY (DELETED, OPTLOCK, IS_AVAILBLE, CONDITION, LOCATION, SEQUENCE_ID, BOOK_ID) select 0, 0, 1, 'New', '24W', 1001, book_id from book where title = 'Jungle Lore';
insert into BOOK_COPY (DELETED, OPTLOCK, IS_AVAILBLE, CONDITION, LOCATION, SEQUENCE_ID, BOOK_ID) select 0, 0, 1, 'New', '24E', 1002, book_id from book where title = 'Jungle Lore';
insert into BOOK_COPY (DELETED, OPTLOCK, IS_AVAILBLE, CONDITION, LOCATION, SEQUENCE_ID, BOOK_ID) select 0, 0, 1, 'New', '125NW', 1001, book_id from book where title = 'Spock''s Logic';
insert into BOOK_COPY (DELETED, OPTLOCK, IS_AVAILBLE, CONDITION, LOCATION, SEQUENCE_ID, BOOK_ID) select 0, 0, 1, 'New', '125NW', 1002, book_id from book where title = 'Spock''s Logic';
insert into BOOK_COPY (DELETED, OPTLOCK, IS_AVAILBLE, CONDITION, LOCATION, SEQUENCE_ID, BOOK_ID) select 0, 0, 1, 'New', '124NW', 1001, book_id from book where title = 'Space, the final frontier';
insert into BOOK_COPY (DELETED, OPTLOCK, IS_AVAILBLE, CONDITION, LOCATION, SEQUENCE_ID, BOOK_ID) select 0, 0, 1, 'New', '124NW', 1002, book_id from book where title = 'Space, the final frontier';

-- clean up

delete from BOOK_AUTHOR
delete from author
delete from BORROWED_ITEM
delete from book_copy
delete from book;
delete from publisher;
