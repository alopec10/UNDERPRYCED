USE `inso2`;

/* ROLES */
INSERT IGNORE INTO roles(name) VALUES('ROLE_USER');
INSERT IGNORE INTO roles(name) VALUES('ROLE_ADMIN');

/* USERS */
INSERT IGNORE INTO users(name, surname, email, password, Address, ZIPCode, Country, PhoneNumber)
VALUES ('Admin', 'Admin', 'admin@admin.com', '$10$72uMGfOL4QxEBIlc756deO.AyDLwWAkjksNut.EoBdY3L85ORAvmq',
        'C/ Admin', '24198', 'Spain', '987402130');

INSERT IGNORE INTO users(name, surname, email, password, Address, ZIPCode, Country, PhoneNumber)
VALUES ('User', 'User', 'user@user.com', '$10$ZO/IaPBLyH149lqDETQjeuAjbTG0YaCYqdTXcMcNy3dhE0U7SDBrm',
        'C/ User', '24198', 'Spain', '987404530');

/* USER ROLES */

INSERT IGNORE INTO userroles (idrole, iduser)
values ((SELECT IdRole FROM roles WHERE Name = 'ROLE_ADMIN'),
        (SELECT IdUser FROM users WHERE Email='admin@admin.com'));

INSERT IGNORE INTO userroles (idrole, iduser)
VALUES ((SELECT IdRole FROM roles WHERE Name = 'ROLE_USER'),
        (SELECT IdUser FROM users WHERE Email='user@user.com'));

/* CATEGORIES */
INSERT IGNORE INTO categories (Type) VALUES ('Zapatillas');
INSERT IGNORE INTO categories (Type) VALUES ('Ropa');

