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

/* PRODUCTS */
INSERT IGNORE INTO products(REF, BRAND, COLORWAY, GENDER, NAME, RELEASEDATE, RETAILPRICE, MODEL, TITLE, YEAR, URL, IDCATEGORY)
VALUES ('1f307b17-e640-4650-8d76-c6a37584e5a1', 'Nike', 'Red/Gold/Ice Blue', 'm', 'X-Mas in LA', '2020-12-30', 225,
        'Nike Lebron 18', 'Nike Lebron 18 X-Mas in LA', '2020',
        'https://images.stockx.com/images/Nike-Lebron-18-X-Mas-in-LA.png?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1609203421',
        (SELECT IdCategory FROM categories WHERE Type = 'Zapatillas'));

INSERT IGNORE INTO products(REF, BRAND, COLORWAY, GENDER, NAME, RELEASEDATE, RETAILPRICE, MODEL, TITLE, YEAR, URL, IDCATEGORY)
VALUES ('fa1a8868-9dd0-4b1b-a52f-dc248143d797', 'Nike', 'Silver/White/Turbo Green', 'm', 'Jetstream', '2020-12-26', 400,
        'Nike Adapt Auto Max', 'Nike Adapt Auto Max Jetstream', '2020',
        'https://images.stockx.com/images/Nike-Adapt-Auto-Max-Jetstream-US-Charger.png?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1611192562',
        (SELECT IdCategory FROM categories WHERE Type = 'Zapatillas'));

INSERT IGNORE INTO products(REF, BRAND, COLORWAY, GENDER, NAME, RELEASEDATE, RETAILPRICE, MODEL, TITLE, YEAR, URL, IDCATEGORY)
VALUES ('8334d027-dafb-426d-b209-b8e07bf1b15b', 'Adidas', 'Sand Taupe/Sand Taupe/Sand Taupe', 'm', 'Sand Taupe', '2020-12-19', 220,
        'adidas Yeezy Boost 350 V2', 'adidas Yeezy Boost 350 V2 Sand Taupe', '2020',
        'https://images.stockx.com/images/adidas-Yeezy-Boost-350-V2-Eliada-Product.jpg?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1609768689',
        (SELECT IdCategory FROM categories WHERE Type = 'Zapatillas'));

INSERT IGNORE INTO products(REF, BRAND, COLORWAY, GENDER, NAME, RELEASEDATE, RETAILPRICE, MODEL, TITLE, YEAR, URL, IDCATEGORY)
VALUES ('8334d027-dafb-676d-b209-b8e12bf1f15b', 'Gucci', 'Beige', 'm', 'Cotton Sweater', '2021-01-06', 1200,
        'Cotton Sweater Beige', 'Gucci x The North Face Cotton Sweater Beige', '2021',
        'https://images.stockx.com/images/Gucci-x-The-North-Face-Cotton-Sweater-Beige.jpg?fit=clip&bg=FFFFFF&w=700&h=500&auto=compress&q=90&dpr=2&trim=color&updated_at=1611134844&fm=webp&ixlib=react-9.0.3&w=1446',
        (SELECT IdCategory FROM categories WHERE Type = 'Ropa'));

INSERT IGNORE INTO products(REF, BRAND, COLORWAY, GENDER, NAME, RELEASEDATE, RETAILPRICE, MODEL, TITLE, YEAR, URL, IDCATEGORY)
VALUES ('8654d027-dafb-676d-b309-c8e12bf1f15b', 'Supreme', 'Red', 'm', 'Cross Box Logo', '2020-12-03', 168,
        'Cross Box Logo Hooded Sweatshirt', 'Supreme Cross Box Logo Hooded Sweatshirt Red', '2020',
        'https://images.stockx.com/images/Supreme-Cross-Box-Logo-Hooded-Sweatshirt-Red.jpg?fit=clip&bg=FFFFFF&w=700&h=500&auto=compress&q=90&dpr=2&trim=color&updated_at=1607005929&fm=webp&ixlib=react-9.0.3&w=1446',
        (SELECT IdCategory FROM categories WHERE Type = 'Ropa'));


