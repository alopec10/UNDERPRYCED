USE `inso2` ^;

/* ROLES */
INSERT IGNORE INTO roles(name)
VALUES ('ROLE_USER') ^;
INSERT IGNORE INTO roles(name)
VALUES ('ROLE_ADMIN') ^;

/* USERS */
INSERT IGNORE INTO users(name, surname, email, password, Address, ZIPCode, Country, PhoneNumber)
VALUES ('Admin', 'Admin', 'admin@admin.com', '$2a$10$YRA9C3jPTVrQs8wbIux7R.8Vx9R3uCp7g7Bg5rJ/Haj8y4C6PB.Cu',
        'Calle Bernardo del Carpio, 6, León', '24004', 'España', '987402130')^;

INSERT IGNORE INTO users(name, surname, email, password, Address, ZIPCode, Country, PhoneNumber)
VALUES ('Lucas', 'López Díaz', 'user@user.com', '$2a$10$h0oOdH.VpWWMslVYdY.qQ.WyQ6HnE52Wc3EI5n7YH3DL6wm8CjVoS',
        'Av. de Aviación Nº13 1B, La Virgen del Camino, León', '24198', 'España', '987404530')^;

INSERT IGNORE INTO users(name, surname, email, password, Address, ZIPCode, Country, PhoneNumber)
VALUES ('Joaquín', 'Domínguez López', 'user2@user.com', '$2a$10$esr0atHpssbhlyiu5SUiUORUzLvx8fZ8yh5suRt17CAgO8LEpaPvy',
        'Av. Quevedo Nº47 1B, León', '24009', 'España', '987403130')^;

/* USER ROLES */

INSERT IGNORE INTO userRoles (idrole, iduser)
values ((SELECT IdRole FROM roles WHERE Name = 'ROLE_ADMIN'),
        (SELECT IdUser FROM users WHERE Email = 'admin@admin.com'))^;

INSERT IGNORE INTO userRoles (idrole, iduser)
VALUES ((SELECT IdRole FROM roles WHERE Name = 'ROLE_USER'),
        (SELECT IdUser FROM users WHERE Email = 'user@user.com'))^;

INSERT IGNORE INTO userRoles (idrole, iduser)
VALUES ((SELECT IdRole FROM roles WHERE Name = 'ROLE_USER'),
        (SELECT IdUser FROM users WHERE Email = 'user2@user.com'))^;

/* PAYMENT METHODS */
INSERT IGNORE INTO paymentMethods (Name, Number, CVV, ExpMonth, ExpYear, DefaultMethod, IsActive, IdUser)
VALUES ('User User', 'SbwcEVenGSm4sjJV+5wgXY9VUODwp2CtTnWQSxlUZqQ=', 'NmC7te8QtgfJyTysOwiXiQ==', '5dMG8a6/5tTzwIIRNmT+EA==', '5dMG8a6/5tTzwIIRNmT+EA==', 1, 1, 2)^;

INSERT IGNORE INTO paymentMethods (Name, Number, CVV, ExpMonth, ExpYear, DefaultMethod, IsActive, IdUser)
VALUES ('User User', 'sOVC/hgza8MF/2jvTdezlSxC9eB/fkUsb3y/Eqi6xSM=', 'NQog9zEmpu80u5h5VFAdgQ==', 'GssQiQZlot4eSKWn+mDYew==', 'SchmHhUO5FQ0ikOQZAmylw==', 0, 1, 2)^;

INSERT IGNORE INTO paymentMethods (Name, Number, CVV, ExpMonth, ExpYear, DefaultMethod, IsActive, IdUser)
VALUES ('User2 User2', 'rkLYU9qn7hvIkOcGuqC8gylD8f/lZZwoWzJ4FU6GODA=', 'NmC7te8QtgfJyTysOwiXiQ==', '5dMG8a6/5tTzwIIRNmT+EA==', '5dMG8a6/5tTzwIIRNmT+EA==', 1, 1, 3)^;

/* CATEGORIES */
INSERT IGNORE INTO categories (Type)
VALUES ('Zapatillas')^;
INSERT IGNORE INTO categories (Type)
VALUES ('Ropa')^;

/* PRODUCTS */
INSERT IGNORE INTO products(REF, BRAND, COLORWAY, GENDER, NAME, RELEASEDATE, RETAILPRICE, MODEL, TITLE, YEAR, URL,
                            IDCATEGORY)
VALUES ('1f307b17-e640-4650-8d76-c6a37584e5a1', 'Nike', 'Red/Gold/Ice Blue', 'M', 'X-Mas in LA', '2020-12-30', 225,
        'Nike Lebron 18', 'Nike Lebron 18 X-Mas in LA', '2020',
        'https://images.stockx.com/images/Nike-Lebron-18-X-Mas-in-LA.png?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1609203421',
        (SELECT IdCategory FROM categories WHERE Type = 'Zapatillas'))^;

INSERT IGNORE INTO products(REF, BRAND, COLORWAY, GENDER, NAME, RELEASEDATE, RETAILPRICE, MODEL, TITLE, YEAR, URL,
                            IDCATEGORY)
VALUES ('fa1a8868-9dd0-4b1b-a52f-dc248143d797', 'Nike', 'Silver/White/Turbo Green', 'M', 'Jetstream', '2020-12-26', 400,
        'Nike Adapt Auto Max', 'Nike Adapt Auto Max Jetstream', '2020',
        'https://images.stockx.com/images/Nike-Adapt-Auto-Max-Jetstream-US-Charger.png?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1611192562',
        (SELECT IdCategory FROM categories WHERE Type = 'Zapatillas'))^;

INSERT IGNORE INTO products(REF, BRAND, COLORWAY, GENDER, NAME, RELEASEDATE, RETAILPRICE, MODEL, TITLE, YEAR, URL,
                            IDCATEGORY)
VALUES ('8334d027-dafb-426d-b209-b8e07bf1b15b', 'Adidas', 'Sand Taupe/Sand Taupe/Sand Taupe', 'M', 'Sand Taupe',
        '2020-12-19', 220,
        'adidas Yeezy Boost 350 V2', 'adidas Yeezy Boost 350 V2 Sand Taupe', '2020',
        'https://images.stockx.com/images/adidas-Yeezy-Boost-350-V2-Eliada-Product.jpg?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1609768689',
        (SELECT IdCategory FROM categories WHERE Type = 'Zapatillas'))^;

INSERT IGNORE INTO products(REF, BRAND, COLORWAY, GENDER, NAME, RELEASEDATE, RETAILPRICE, MODEL, TITLE, YEAR, URL,
                            IDCATEGORY)
VALUES ('8334d027-dafb-676d-b209-b8e12bf1f15b', 'Gucci', 'Beige', 'M', 'Cotton Sweater', '2021-01-06', 1200,
        'Cotton Sweater Beige', 'Gucci x The North Face Cotton Sweater Beige', '2021',
        'https://images.stockx.com/images/Gucci-x-The-North-Face-Cotton-Sweater-Beige.jpg?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1609203421',
        (SELECT IdCategory FROM categories WHERE Type = 'Ropa'))^;

INSERT IGNORE INTO products(REF, BRAND, COLORWAY, GENDER, NAME, RELEASEDATE, RETAILPRICE, MODEL, TITLE, YEAR, URL,
                            IDCATEGORY)
VALUES ('8654d027-dafb-676d-b309-c8e12bf1f15b', 'Supreme', 'Red', 'M', 'Cross Box Logo', '2020-12-03', 168,
        'Cross Box Logo Hooded Sweatshirt', 'Supreme Cross Box Logo Hooded Sweatshirt Red', '2020',
        'https://images.stockx.com/images/Supreme-Cross-Box-Logo-Hooded-Sweatshirt-Red.jpg?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1609203421',
        (SELECT IdCategory FROM categories WHERE Type = 'Ropa'))^;

INSERT IGNORE INTO products(REF, BRAND, COLORWAY, GENDER, NAME, RELEASEDATE, RETAILPRICE, MODEL, TITLE, YEAR, URL,
                            IDCATEGORY)
VALUES ('8654d027-dafb-676d-b309-chshds11', 'Jordan', 'British Khaki/Sail-Bright Crimson', 'M', 'Travis Scott British', '2021-04-30', 250,
        'Retro Travis Scott British Khaki', 'Jordan 6 Retro Travis Scott British Khaki', '2021',
        'https://images.stockx.com/images/Air-Jordan-6-Retro-Travis-Scott-British-Khaki-Product.jpg?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1609203421',
        (SELECT IdCategory FROM categories WHERE Type = 'Zapatillas'))^;

INSERT IGNORE INTO products(REF, BRAND, COLORWAY, GENDER, NAME, RELEASEDATE, RETAILPRICE, MODEL, TITLE, YEAR, URL,
                            IDCATEGORY)
VALUES ('8654d027-dafb-676d-b309-9909', 'Nike', 'Black/White-Clear', 'M', 'VaporMax Off-White', '2017-09-09', 250,
        'Air VaporMax Off-White', 'Nike Air VaporMax Off-White', '2017',
        'https://images.stockx.com/images/Nike-Air-VaporMax-Off-White-Product.jpg?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1609203421',
        (SELECT IdCategory FROM categories WHERE Type = 'Zapatillas'))^;

INSERT IGNORE INTO products(REF, BRAND, COLORWAY, GENDER, NAME, RELEASEDATE, RETAILPRICE, MODEL, TITLE, YEAR, URL,
                            IDCATEGORY)
VALUES ('8654d027-dafb-676d-2121-9909', 'Jordan', 'Black/Sport Blue-White', 'M', 'Jordan 1 Retro Fragment', '2014-12-27', 185,
        'Retro Fragment', 'Jordan 1 Retro Fragment', '2014',
        'https://images.stockx.com/images/Air-Jordan-1-Retro-Fragment-Product.jpg?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1609203421',
        (SELECT IdCategory FROM categories WHERE Type = 'Zapatillas'))^;

INSERT IGNORE INTO products(REF, BRAND, COLORWAY, GENDER, NAME, RELEASEDATE, RETAILPRICE, MODEL, TITLE, YEAR, URL,
                            IDCATEGORY)
VALUES ('8654d027-dafb-3321-2121-9909', 'Jordan', 'Sail/Rust Pink-White-Crimson', 'F', 'Retro Rust Pink', '2021-05-28', 190,
        'Retro Rust Pink', 'Jordan 3 Retro Rust Pink', '2021',
        'https://images.stockx.com/images/Air-Jordan-3-Retro-Rust-Pink-W.png?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1609203421',
        (SELECT IdCategory FROM categories WHERE Type = 'Zapatillas'))^;

INSERT IGNORE INTO products(REF, BRAND, COLORWAY, GENDER, NAME, RELEASEDATE, RETAILPRICE, MODEL, TITLE, YEAR, URL,
                            IDCATEGORY)
VALUES ('8654d027-dafb-5542-2121-9909', 'Adidas', 'House Blue/House Blue', 'F', 'Desert Boot House Blue', '2021-03-29', 295,
        'Desert Boot House Blue', 'adidas Yeezy Desert Boot House Blue', '2021',
        'https://images.stockx.com/images/adidas-Yeezy-Desert-Boot-House-Blue-W.jpg?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1609203421',
        (SELECT IdCategory FROM categories WHERE Type = 'Zapatillas'))^;

INSERT IGNORE INTO products(REF, BRAND, COLORWAY, GENDER, NAME, RELEASEDATE, RETAILPRICE, MODEL, TITLE, YEAR, URL,
                            IDCATEGORY)
VALUES ('8654d027-dafb-322-21221-9909', 'Adidas', 'Salt/Salt/Salt', 'F', 'Yeezy 500 Salt', '2018-11-30', 200,
        'Yeezy 500 Salt', 'adidas Yeezy 500 Salt', '2018',
        'https://images.stockx.com/images/adidas-Yeezy-500-Salt-Product.jpg?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1609203421',
        (SELECT IdCategory FROM categories WHERE Type = 'Zapatillas'))^;

INSERT IGNORE INTO products(REF, BRAND, COLORWAY, GENDER, NAME, RELEASEDATE, RETAILPRICE, MODEL, TITLE, YEAR, URL,
                            IDCATEGORY)
VALUES ('8659ggdd727-dafb-322-21221-9909', 'Nike', 'White/Black-Muslin', 'M', 'Mid Off-White', '2017-09-09', 130,
        'Blazer Mid Off-White', 'Nike Blazer Mid Off-White', '2017',
        'https://images.stockx.com/images/Nike-Blazer-Mid-Off-White-Product.jpg?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1609203421',
        (SELECT IdCategory FROM categories WHERE Type = 'Zapatillas'))^;

INSERT IGNORE INTO products(REF, BRAND, COLORWAY, GENDER, NAME, RELEASEDATE, RETAILPRICE, MODEL, TITLE, YEAR, URL,
                            IDCATEGORY)
VALUES ('865fa321d727-dafb-51512-21221-99fs1', 'Supreme', 'White', 'M', 'SHIRT Split Box Logo Tee White', '2018-09-13', 54,
        'Comme des Garcons SHIRT Split Box Logo Tee', 'Supreme Comme des Garcons SHIRT Split Box Logo Tee White', '2018',
        'https://images.stockx.com/images/Supreme-Comme-des-Garcons-SHIRT-Split-Box-Logo-Tee-White.jpg?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1609203421',
        (SELECT IdCategory FROM categories WHERE Type = 'Ropa'))^;

INSERT IGNORE INTO products(REF, BRAND, COLORWAY, GENDER, NAME, RELEASEDATE, RETAILPRICE, MODEL, TITLE, YEAR, URL,
                            IDCATEGORY)
VALUES ('865fa321d52ggs-dafb-51gs2-21221-921s1', 'Gucci', 'Color Brown/Beige', 'M', 'Canvas Shorts Brown/Beige', '2021-06-01', 980,
        'GG Canvas Shorts Brown/Beige', 'Gucci x The North Face GG Canvas Shorts Brown/Beige', '2021',
        'https://images.stockx.com/images/Gucci-x-The-North-Face-GG-Canvas-Shorts-Brown-Beige-1.jpg?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1609203421',
        (SELECT IdCategory FROM categories WHERE Type = 'Ropa'))^;

INSERT IGNORE INTO products(REF, BRAND, COLORWAY, GENDER, NAME, RELEASEDATE, RETAILPRICE, MODEL, TITLE, YEAR, URL,
                            IDCATEGORY)
VALUES ('865fa353252ggs-dafb-51gs2-21falg-921s1', 'Nike', 'Sail', 'F', 'Warm Up T-Shirt Sail', '2020-11-19', 45,
        'Warm Up T-Shirt Sail', 'FEAR OF GOD x Nike Warm Up T-Shirt Sail', '2021',
        'https://images.stockx.com/images/FEAR-OF-GOD-x-Nike-Warm-Up-T-Shirt-Sail.jpg?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1609203421',
        (SELECT IdCategory FROM categories WHERE Type = 'Ropa'))^;

INSERT IGNORE INTO products(REF, BRAND, COLORWAY, GENDER, NAME, RELEASEDATE, RETAILPRICE, MODEL, TITLE, YEAR, URL,
                            IDCATEGORY)
VALUES ('865fa353gs22ggs-dafb-51gs2-21falg-921as', 'Adidas', 'Oxen', 'F', 'Holy Spirit Crewneck Oxen', '2019-01-24', 225,
        'Holy Spirit Crewneck Oxen', 'adidas Kanye West Holy Spirit Crewneck Oxen', '2019',
        'https://images.stockx.com/images/Kanye-West-Holy-Spirit-Crewneck-Oxen.png?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1609203421',
        (SELECT IdCategory FROM categories WHERE Type = 'Ropa'))^;

INSERT IGNORE INTO products(REF, BRAND, COLORWAY, GENDER, NAME, RELEASEDATE, RETAILPRICE, MODEL, TITLE, YEAR, URL,
                            IDCATEGORY)
VALUES ('865fa353gs22ggs-dafb-51gs2-21falg-mj98', 'Off-White', 'White/Black', 'M', 'Mona Lisa Hoodie', '2021-02-02', 565,
        'Mona Lisa Hoodie White/Black', 'OFF-WHITE Mona Lisa Hoodie White/Black', '2021',
        'https://images.stockx.com/images/OFF-WHITE-Mona-Lisa-Hoodie-White-Black.jpg?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1609203421',
        (SELECT IdCategory FROM categories WHERE Type = 'Ropa'))^;

INSERT IGNORE INTO products(REF, BRAND, COLORWAY, GENDER, NAME, RELEASEDATE, RETAILPRICE, MODEL, TITLE, YEAR, URL,
                            IDCATEGORY)
VALUES ('865fa353gs22ggs-3f121-51gs2-21f12-mj98', 'Supreme', 'Black', 'M', 'Split Crewneck Sweatshirt', '2018-08-20', 158,
        'Split Crewneck Sweatshirt Black', 'Supreme Split Crewneck Sweatshirt Black', '2018',
        'https://images.stockx.com/images/Supreme-Split-Crewneck-Sweatshirt-Black-v2.jpg?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1609203421',
        (SELECT IdCategory FROM categories WHERE Type = 'Ropa'))^;

INSERT IGNORE INTO products(REF, BRAND, COLORWAY, GENDER, NAME, RELEASEDATE, RETAILPRICE, MODEL, TITLE, YEAR, URL,
                            IDCATEGORY)
VALUES ('1f43b12h31j-3f121-51gs2-21f12-mj98', 'Jordan', 'Olive', 'M', 'Cactus Jack Highest Hoodie', '2019-10-11', 95,
        'Cactus Jack Highest Hoodie Olive', 'Travis Scott Jordan Cactus Jack Highest Hoodie Olive', '2019',
        'https://images.stockx.com/images/Travis-Scott-Jordan-Cactus-Jack-Highest-Hoodie-Olive.png?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1609203421',
        (SELECT IdCategory FROM categories WHERE Type = 'Ropa'))^;

INSERT IGNORE INTO products(REF, BRAND, COLORWAY, GENDER, NAME, RELEASEDATE, RETAILPRICE, MODEL, TITLE, YEAR, URL,
                            IDCATEGORY)
VALUES ('1f431f3g3k31j-3f121-51gs2-21f12-mj98', 'Off-White', 'Black', 'M', 'OFF-WHITE x Jordan Hoodie', '2020-02-15', 180,
        'OFF-WHITE x Jordan Hoodie Black', 'OFF-WHITE x Jordan Hoodie Black', '2020',
        'https://images.stockx.com/images/OFF-WHITE-x-Jordan-Hoodie-Black.jpg?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1609203421',
        (SELECT IdCategory FROM categories WHERE Type = 'Ropa'))^;

/* PRODUCT DETAILS */

/* Nike Lebron 18 */
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('40', (SELECT IdProduct FROM products WHERE Ref = '1f307b17-e640-4650-8d76-c6a37584e5a1'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('40.5', (SELECT IdProduct FROM products WHERE Ref = '1f307b17-e640-4650-8d76-c6a37584e5a1'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('41', (SELECT IdProduct FROM products WHERE Ref = '1f307b17-e640-4650-8d76-c6a37584e5a1'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('42', (SELECT IdProduct FROM products WHERE Ref = '1f307b17-e640-4650-8d76-c6a37584e5a1'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('42.5', (SELECT IdProduct FROM products WHERE Ref = '1f307b17-e640-4650-8d76-c6a37584e5a1'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('43', (SELECT IdProduct FROM products WHERE Ref = '1f307b17-e640-4650-8d76-c6a37584e5a1'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('44', (SELECT IdProduct FROM products WHERE Ref = '1f307b17-e640-4650-8d76-c6a37584e5a1'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('44.5', (SELECT IdProduct FROM products WHERE Ref = '1f307b17-e640-4650-8d76-c6a37584e5a1'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('45', (SELECT IdProduct FROM products WHERE Ref = '1f307b17-e640-4650-8d76-c6a37584e5a1'))^;

/* Nike Adapt Auto Max */
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('40', (SELECT IdProduct FROM products WHERE Ref = 'fa1a8868-9dd0-4b1b-a52f-dc248143d797'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('40.5', (SELECT IdProduct FROM products WHERE Ref = 'fa1a8868-9dd0-4b1b-a52f-dc248143d797'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('41', (SELECT IdProduct FROM products WHERE Ref = 'fa1a8868-9dd0-4b1b-a52f-dc248143d797'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('42', (SELECT IdProduct FROM products WHERE Ref = 'fa1a8868-9dd0-4b1b-a52f-dc248143d797'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('42.5', (SELECT IdProduct FROM products WHERE Ref = 'fa1a8868-9dd0-4b1b-a52f-dc248143d797'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('43', (SELECT IdProduct FROM products WHERE Ref = 'fa1a8868-9dd0-4b1b-a52f-dc248143d797'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('44', (SELECT IdProduct FROM products WHERE Ref = 'fa1a8868-9dd0-4b1b-a52f-dc248143d797'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('44.5', (SELECT IdProduct FROM products WHERE Ref = 'fa1a8868-9dd0-4b1b-a52f-dc248143d797'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('45', (SELECT IdProduct FROM products WHERE Ref = 'fa1a8868-9dd0-4b1b-a52f-dc248143d797'))^;

/* adidas Yeezy Boost 350 V2 */
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('40', (SELECT IdProduct FROM products WHERE Ref = '8334d027-dafb-426d-b209-b8e07bf1b15b'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('40.5', (SELECT IdProduct FROM products WHERE Ref = '8334d027-dafb-426d-b209-b8e07bf1b15b'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('41', (SELECT IdProduct FROM products WHERE Ref = '8334d027-dafb-426d-b209-b8e07bf1b15b'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('42', (SELECT IdProduct FROM products WHERE Ref = '8334d027-dafb-426d-b209-b8e07bf1b15b'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('42.5', (SELECT IdProduct FROM products WHERE Ref = '8334d027-dafb-426d-b209-b8e07bf1b15b'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('43', (SELECT IdProduct FROM products WHERE Ref = '8334d027-dafb-426d-b209-b8e07bf1b15b'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('44', (SELECT IdProduct FROM products WHERE Ref = '8334d027-dafb-426d-b209-b8e07bf1b15b'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('44.5', (SELECT IdProduct FROM products WHERE Ref = '8334d027-dafb-426d-b209-b8e07bf1b15b'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('45', (SELECT IdProduct FROM products WHERE Ref = '8334d027-dafb-426d-b209-b8e07bf1b15b'))^;

/* Gucci x The North Face Cotton Sweater Beige */
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XS', (SELECT IdProduct FROM products WHERE Ref = '8334d027-dafb-676d-b209-b8e12bf1f15b'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('S', (SELECT IdProduct FROM products WHERE Ref = '8334d027-dafb-676d-b209-b8e12bf1f15b'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('M', (SELECT IdProduct FROM products WHERE Ref = '8334d027-dafb-676d-b209-b8e12bf1f15b'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('L', (SELECT IdProduct FROM products WHERE Ref = '8334d027-dafb-676d-b209-b8e12bf1f15b'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XL', (SELECT IdProduct FROM products WHERE Ref = '8334d027-dafb-676d-b209-b8e12bf1f15b'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XXL', (SELECT IdProduct FROM products WHERE Ref = '8334d027-dafb-676d-b209-b8e12bf1f15b'))^;

/* Supreme Cross Box Logo Hooded Sweatshirt Red */
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XS', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-b309-c8e12bf1f15b'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('S', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-b309-c8e12bf1f15b'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('M', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-b309-c8e12bf1f15b'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('L', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-b309-c8e12bf1f15b'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XL', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-b309-c8e12bf1f15b'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XXL', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-b309-c8e12bf1f15b'))^;

/* Jordan 6 Retro Travis Scott British Khaki */
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('40', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-b309-chshds11'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('40.5', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-b309-chshds11'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('41', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-b309-chshds11'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('42', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-b309-chshds11'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('42.5', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-b309-chshds11'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('43', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-b309-chshds11'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('44', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-b309-chshds11'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('44.5', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-b309-chshds11'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('45', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-b309-chshds11'))^;

/* Nike Air VaporMax Off-White */
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('40', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-b309-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('40.5', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-b309-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('41', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-b309-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('42', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-b309-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('42.5', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-b309-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('43', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-b309-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('44', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-b309-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('44.5', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-b309-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('45', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-b309-9909'))^;

/* Jordan 1 Retro Fragment */
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('40', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-2121-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('40.5', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-2121-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('41', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-2121-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('42', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-2121-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('42.5', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-2121-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('43', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-2121-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('44', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-2121-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('44.5', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-2121-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('45', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-676d-2121-9909'))^;

/* Jordan 3 Retro Rust Pink */
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('35', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-3321-2121-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('36', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-3321-2121-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('36.5', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-3321-2121-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('37', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-3321-2121-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('37.5', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-3321-2121-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('38', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-3321-2121-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('39', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-3321-2121-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('39.5', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-3321-2121-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('40', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-3321-2121-9909'))^;

/* adidas Yeezy Desert Boot House Blue */
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('35', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-5542-2121-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('36', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-5542-2121-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('36.5', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-5542-2121-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('37', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-5542-2121-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('37.5', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-5542-2121-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('38', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-5542-2121-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('39', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-5542-2121-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('39.5', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-5542-2121-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('40', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-5542-2121-9909'))^;

/* adidas Yeezy 500 Salt */
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('35', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-322-21221-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('36', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-322-21221-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('36.5', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-322-21221-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('37', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-322-21221-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('37.5', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-322-21221-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('38', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-322-21221-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('39', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-322-21221-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('39.5', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-322-21221-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('40', (SELECT IdProduct FROM products WHERE Ref = '8654d027-dafb-322-21221-9909'))^;

/* Nike Blazer Mid Off-white */
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('40', (SELECT IdProduct FROM products WHERE Ref = '8659ggdd727-dafb-322-21221-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('40.5', (SELECT IdProduct FROM products WHERE Ref = '8659ggdd727-dafb-322-21221-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('41', (SELECT IdProduct FROM products WHERE Ref = '8659ggdd727-dafb-322-21221-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('42', (SELECT IdProduct FROM products WHERE Ref = '8659ggdd727-dafb-322-21221-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('42.5', (SELECT IdProduct FROM products WHERE Ref = '8659ggdd727-dafb-322-21221-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('43', (SELECT IdProduct FROM products WHERE Ref = '8659ggdd727-dafb-322-21221-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('44', (SELECT IdProduct FROM products WHERE Ref = '8659ggdd727-dafb-322-21221-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('44.5', (SELECT IdProduct FROM products WHERE Ref = '8659ggdd727-dafb-322-21221-9909'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('45', (SELECT IdProduct FROM products WHERE Ref = '8659ggdd727-dafb-322-21221-9909'))^;

/* Supreme Comme des Garcons SHIRT Split Box Logo Tee White */
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XS', (SELECT IdProduct FROM products WHERE Ref = '865fa321d727-dafb-51512-21221-99fs1'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('S', (SELECT IdProduct FROM products WHERE Ref = '865fa321d727-dafb-51512-21221-99fs1'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('M', (SELECT IdProduct FROM products WHERE Ref = '865fa321d727-dafb-51512-21221-99fs1'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('L', (SELECT IdProduct FROM products WHERE Ref = '865fa321d727-dafb-51512-21221-99fs1'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XL', (SELECT IdProduct FROM products WHERE Ref = '865fa321d727-dafb-51512-21221-99fs1'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XXL', (SELECT IdProduct FROM products WHERE Ref = '865fa321d727-dafb-51512-21221-99fs1'))^;

/* Gucci x The North Face GG Canvas Shorts Brown/Beige */
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XS', (SELECT IdProduct FROM products WHERE Ref = '865fa321d52ggs-dafb-51gs2-21221-921s1'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('S', (SELECT IdProduct FROM products WHERE Ref = '865fa321d52ggs-dafb-51gs2-21221-921s1'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('M', (SELECT IdProduct FROM products WHERE Ref = '865fa321d52ggs-dafb-51gs2-21221-921s1'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('L', (SELECT IdProduct FROM products WHERE Ref = '865fa321d52ggs-dafb-51gs2-21221-921s1'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XL', (SELECT IdProduct FROM products WHERE Ref = '865fa321d52ggs-dafb-51gs2-21221-921s1'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XXL', (SELECT IdProduct FROM products WHERE Ref = '865fa321d52ggs-dafb-51gs2-21221-921s1'))^;

/* FEAR OF GOD x Nike Warm Up T-Shirt Sail */
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XS', (SELECT IdProduct FROM products WHERE Ref = '865fa353252ggs-dafb-51gs2-21falg-921s1'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('S', (SELECT IdProduct FROM products WHERE Ref = '865fa353252ggs-dafb-51gs2-21falg-921s1'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('M', (SELECT IdProduct FROM products WHERE Ref = '865fa353252ggs-dafb-51gs2-21falg-921s1'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('L', (SELECT IdProduct FROM products WHERE Ref = '865fa353252ggs-dafb-51gs2-21falg-921s1'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XL', (SELECT IdProduct FROM products WHERE Ref = '865fa353252ggs-dafb-51gs2-21falg-921s1'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XXL', (SELECT IdProduct FROM products WHERE Ref = '865fa353252ggs-dafb-51gs2-21falg-921s1'))^;

/* adidas Kanye West Holy Spirit Crewneck Oxen */
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XS', (SELECT IdProduct FROM products WHERE Ref = '865fa353gs22ggs-dafb-51gs2-21falg-921as'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('S', (SELECT IdProduct FROM products WHERE Ref = '865fa353gs22ggs-dafb-51gs2-21falg-921as'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('M', (SELECT IdProduct FROM products WHERE Ref = '865fa353gs22ggs-dafb-51gs2-21falg-921as'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('L', (SELECT IdProduct FROM products WHERE Ref = '865fa353gs22ggs-dafb-51gs2-21falg-921as'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XL', (SELECT IdProduct FROM products WHERE Ref = '865fa353gs22ggs-dafb-51gs2-21falg-921as'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XXL', (SELECT IdProduct FROM products WHERE Ref = '865fa353gs22ggs-dafb-51gs2-21falg-921as'))^;

/* OFF-WHITE Mona Lisa Hoodie White/Black */
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XS', (SELECT IdProduct FROM products WHERE Ref = '865fa353gs22ggs-dafb-51gs2-21falg-mj98'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('S', (SELECT IdProduct FROM products WHERE Ref = '865fa353gs22ggs-dafb-51gs2-21falg-mj98'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('M', (SELECT IdProduct FROM products WHERE Ref = '865fa353gs22ggs-dafb-51gs2-21falg-mj98'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('L', (SELECT IdProduct FROM products WHERE Ref = '865fa353gs22ggs-dafb-51gs2-21falg-mj98'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XL', (SELECT IdProduct FROM products WHERE Ref = '865fa353gs22ggs-dafb-51gs2-21falg-mj98'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XXL', (SELECT IdProduct FROM products WHERE Ref = '865fa353gs22ggs-dafb-51gs2-21falg-mj98'))^;

/* Supreme Split Crewneck Sweatshirt Black */
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XS', (SELECT IdProduct FROM products WHERE Ref = '865fa353gs22ggs-3f121-51gs2-21f12-mj98'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('S', (SELECT IdProduct FROM products WHERE Ref = '865fa353gs22ggs-3f121-51gs2-21f12-mj98'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('M', (SELECT IdProduct FROM products WHERE Ref = '865fa353gs22ggs-3f121-51gs2-21f12-mj98'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('L', (SELECT IdProduct FROM products WHERE Ref = '865fa353gs22ggs-3f121-51gs2-21f12-mj98'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XL', (SELECT IdProduct FROM products WHERE Ref = '865fa353gs22ggs-3f121-51gs2-21f12-mj98'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XXL', (SELECT IdProduct FROM products WHERE Ref = '865fa353gs22ggs-3f121-51gs2-21f12-mj98'))^;

/* Travis Scott Jordan Cactus Jack Highest Hoodie Olive */
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XS', (SELECT IdProduct FROM products WHERE Ref = '1f43b12h31j-3f121-51gs2-21f12-mj98'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('S', (SELECT IdProduct FROM products WHERE Ref = '1f43b12h31j-3f121-51gs2-21f12-mj98'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('M', (SELECT IdProduct FROM products WHERE Ref = '1f43b12h31j-3f121-51gs2-21f12-mj98'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('L', (SELECT IdProduct FROM products WHERE Ref = '1f43b12h31j-3f121-51gs2-21f12-mj98'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XL', (SELECT IdProduct FROM products WHERE Ref = '1f43b12h31j-3f121-51gs2-21f12-mj98'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XXL', (SELECT IdProduct FROM products WHERE Ref = '1f43b12h31j-3f121-51gs2-21f12-mj98'))^;

/* OFF-WHITE x Jordan Hoodie Black */
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XS', (SELECT IdProduct FROM products WHERE Ref = '1f431f3g3k31j-3f121-51gs2-21f12-mj98'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('S', (SELECT IdProduct FROM products WHERE Ref = '1f431f3g3k31j-3f121-51gs2-21f12-mj98'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('M', (SELECT IdProduct FROM products WHERE Ref = '1f431f3g3k31j-3f121-51gs2-21f12-mj98'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('L', (SELECT IdProduct FROM products WHERE Ref = '1f431f3g3k31j-3f121-51gs2-21f12-mj98'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XL', (SELECT IdProduct FROM products WHERE Ref = '1f431f3g3k31j-3f121-51gs2-21f12-mj98'))^;
INSERT IGNORE INTO productDetails(Size, IdProduct)
VALUES ('XXL', (SELECT IdProduct FROM products WHERE Ref = '1f431f3g3k31j-3f121-51gs2-21f12-mj98'))^;

/* ASKS */

/* Nike Lebron 18 */
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (251, 2, 1)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (247, 3, 2)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (312, 2, 3)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (298, 3, 4)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (295, 2, 5)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (212, 3, 6)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (300, 2, 7)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (312, 3, 8)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (370, 2, 9)^;

/* Nike Adapt Auto Max */
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (453, 3, 10)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (398, 2, 11)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (332, 3, 12)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (679, 2, 13)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (341, 3, 14)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (431, 2,15)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (449, 3, 16)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (370, 3, 18)^;

/* adidas Yeezy Boost 350 V2 */
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (300, 2, 19)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (290, 3, 20)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (276, 2, 21)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (354, 3, 22)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (450, 2, 23)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (350, 3, 24)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (311, 2, 25)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (300, 3, 26)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (298, 2, 27)^;

/* Gucci x The North Face Cotton Sweater Beige */
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (900, 3, 28)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (887, 2, 29)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (987, 3, 30)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (867, 2, 31)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (890, 3, 32)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (1021, 2, 33)^;

/* Supreme Cross Box Logo Hooded Sweatshirt Red */
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (540, 2, 34)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (387, 3, 35)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (448, 3, 37)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (594, 3, 38)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (525, 2, 39)^;

/* Jordan 6 Retro Travis Scott British Khaki */
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (251, 2, 40)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (247, 3, 41)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (312, 2, 42)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (298, 3, 43)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (295, 2, 44)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (212, 3, 45)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (300, 2, 46)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (312, 3, 47)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (288, 2, 48)^;

/* Nike Air VaporMax Off-White */
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (700, 2, 49)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (687, 3, 50)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (690, 2, 51)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (730, 3, 52)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (776, 2, 53)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (820, 3, 54)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (669, 2, 55)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (700, 3, 56)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (760, 2, 57)^;

/* Jordan 1 Retro Fragment */
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (2540, 2, 58)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (2782, 3, 59)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (3141, 2, 60)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (2800, 3, 61)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (5000, 2, 62)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (3500, 3, 63)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (3170, 2, 64)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (2990, 3, 65)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (5300, 2, 66)^;

/* Jordan 3 Retro Rust Pink */
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (350, 2, 67)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (420, 3, 68)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (445, 2, 69)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (360, 3, 70)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (410, 2, 71)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (470, 3, 72)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (390, 2, 73)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (377, 3, 74)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (402, 2, 75)^;

/* adidas Yeezy Desert Boot House Blue */
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (470, 2, 76)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (360, 3, 77)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (500, 2, 78)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (390, 3, 79)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (387, 2, 80)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (465, 3, 81)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (412, 2, 82)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (340, 3, 83)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (402, 2, 84)^;

/* adidas Yeezy 500 Salt */
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (470, 2, 85)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (360, 3, 86)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (500, 2, 87)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (390, 3, 88)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (387, 2, 89)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (465, 3, 90)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (412, 2, 91)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (340, 3, 92)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (402, 2, 93)^;

/* Nike Blazer Mid Off-white */
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (1380, 2, 94)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (1870, 3, 95)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (1983, 2, 96)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (2032, 3, 97)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (2200, 2, 98)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (1884, 3, 99)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (1940, 2, 100)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (2302, 3, 101)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (1965, 2, 102)^;

/* Supreme Comme des Garcons SHIRT Split Box Logo Tee White */
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (260, 2, 103)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (312, 3, 104)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (298, 3, 105)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (287, 3, 106)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (301, 2, 107)^;

/* Gucci x The North Face GG Canvas Shorts Brown/Beige */
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (1140, 2, 109)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (1132, 3, 109)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (1153, 3, 110)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (1232, 3, 111)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (1244, 3, 112)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (1231, 2, 113)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (1200, 2, 114)^;

/* FEAR OF GOD x Nike Warm Up T-Shirt Sail */
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (170, 2, 115)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (182, 3, 116)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (179, 3, 117)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (176, 3, 118)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (192, 2, 119)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (169, 2, 120)^;

/* adidas Kanye West Holy Spirit Crewneck Oxen */
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (290, 2, 121)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (312, 3, 122)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (321, 3, 123)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (345, 3, 124)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (298, 2, 125)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (270, 2, 126)^;

/* OFF-WHITE Mona Lisa Hoodie White/Black */
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (312, 2, 127)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (432, 3, 128)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (543, 3, 129)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (575, 3, 130)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (498, 2, 131)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (478, 2, 132)^;

/* Supreme Split Crewneck Sweatshirt Black */
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (312, 2, 133)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (432, 3, 134)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (498, 3, 135)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (476, 3, 137)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (450, 2, 137)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (431, 2, 138)^;

/* Travis Scott Jordan Cactus Jack Highest Hoodie Olive */
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (190, 2, 139)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (245, 3, 140)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (298, 3, 141)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (198, 3, 142)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (245, 2, 143)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (233, 2, 144)^;

/* OFF-WHITE x Jordan Hoodie Black */
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (312, 2, 145)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (345, 3, 146)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (398, 3, 147)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (366, 3, 148)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (412, 2, 149)^;
INSERT IGNORE INTO asks(price, iduser, idproductdetails) VALUES (403, 2, 150)^;

/* BIDS */

/* Nike Lebron 18 */
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (231, 3, 1)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (243, 2, 2)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (298, 3, 3)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (270, 2, 4)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (262, 3, 5)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (197, 2, 6)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (296, 3, 7)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (300, 2, 8)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (332, 3, 9)^;

/* Nike Adapt Auto Max */
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (432, 2, 10)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (365, 3, 11)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (312, 2, 12)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (672, 3, 13)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (332, 2, 14)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (412, 3,15)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (453, 2, 16)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (354, 2, 18)^;

/* adidas Yeezy Boost 350 V2 */
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (284, 3, 19)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (275, 2, 20)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (263, 3, 21)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (332, 2, 22)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (412, 3, 23)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (301, 3, 25)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (287, 2, 26)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (254, 3, 27)^;

/* Gucci x The North Face Cotton Sweater Beige */
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (875, 2, 28)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (865, 3, 29)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (943, 2, 30)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (830, 3, 31)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (865, 2, 32)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (987, 3, 33)^;

/* Supreme Cross Box Logo Hooded Sweatshirt Red */
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (521, 3, 34)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (364, 2, 35)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (412, 2, 37)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (567, 2, 38)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (502, 3, 39)^;

/* Jordan 6 Retro Travis Scott British Khaki */
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (232, 3, 40)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (212, 2, 41)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (298, 3, 42)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (276, 2, 43)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (281, 3, 44)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (198, 2, 45)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (287, 3, 46)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (301, 2, 47)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (276, 3, 48)^;

/* Nike Air VaporMax Off-White */
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (678, 3, 49)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (632, 2, 50)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (665, 3, 51)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (712, 2, 52)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (754, 3, 53)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (804, 2, 54)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (651, 3, 55)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (687, 2, 56)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (732, 3, 57)^;

/* Jordan 1 Retro Fragment */
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (2370, 3, 58)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (2654, 2, 59)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (3112, 3, 60)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (2653, 2, 61)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (4124, 3, 62)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (3321, 2, 63)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (3004, 3, 64)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (2940, 2, 65)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (5240, 3, 66)^;

/* Jordan 3 Retro Rust Pink */
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (298, 3, 67)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (378, 2, 68)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (412, 3, 69)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (343, 2, 70)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (387, 3, 71)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (443, 2, 72)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (387, 3, 73)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (354, 2, 74)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (378, 3, 75)^;

/* adidas Yeezy Desert Boot House Blue */
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (453, 3, 76)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (343, 2, 77)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (487, 3, 78)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (361, 2, 79)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (351, 3, 80)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (446, 2, 81)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (401, 3, 82)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (325, 2, 83)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (398, 3, 84)^;

/* adidas Yeezy 500 Salt */
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (432, 3, 85)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (345, 2, 86)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (487, 3, 87)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (367, 2, 88)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (383, 3, 89)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (454, 2, 90)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (403, 3, 91)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (312, 2, 92)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (387, 3, 93)^;

/* Nike Blazer Mid Off-white */
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (1270, 3, 94)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (1750, 2, 95)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (1876, 3, 96)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (1983, 2, 97)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (2143, 3, 98)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (1854, 2, 99)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (1913, 3, 100)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (2287, 2, 101)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (1943, 3, 102)^;

/* Supreme Comme des Garcons SHIRT Split Box Logo Tee White */
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (235, 3, 103)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (301, 2, 104)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (287, 2, 105)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (265, 2, 106)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (287, 3, 107)^;

/* Gucci x The North Face GG Canvas Shorts Brown/Beige */
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (1132, 2, 110)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (1205, 2, 111)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (1213, 2, 112)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (1205, 3, 113)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (1176, 3, 114)^;

/* FEAR OF GOD x Nike Warm Up T-Shirt Sail */
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (154, 3, 115)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (165, 2, 116)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (154, 2, 117)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (165, 2, 118)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (165, 3, 119)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (151, 3, 120)^;

/* adidas Kanye West Holy Spirit Crewneck Oxen */
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (276, 3, 121)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (301, 2, 122)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (312, 2, 123)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (321, 2, 124)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (287, 3, 125)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (250, 3, 126)^;

/* OFF-WHITE Mona Lisa Hoodie White/Black */
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (300, 3, 127)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (412, 2, 128)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (540, 2, 129)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (565, 2, 130)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (487, 3, 131)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (465, 3, 132)^;

/* Supreme Split Crewneck Sweatshirt Black */
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (301, 3, 133)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (412, 2, 134)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (467, 2, 135)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (453, 2, 137)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (431, 3, 137)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (420, 3, 138)^;

/* Travis Scott Jordan Cactus Jack Highest Hoodie Olive */
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (176, 3, 139)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (231, 2, 140)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (267, 2, 141)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (178, 2, 142)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (224, 3, 143)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (212, 3, 144)^;

/* OFF-WHITE x Jordan Hoodie Black */
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (303, 3, 145)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (325, 2, 146)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (387, 2, 147)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (354, 2, 148)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (401, 3, 149)^;
INSERT IGNORE INTO bids(price, iduser, idproductdetails) VALUES (387, 3, 150)^;

/* ORDERS */

/* Usuario 1 */

INSERT IGNORE INTO orders(ref, price, priceSeller, priceBuyer, idbuyer, idseller, idproductdetails, IdPayMethodBuyer, IdPayMethodSeller)
VALUES ('7321401552789517581', 263, 221.70, 304.30, 2, 3, 1, 2, 3)^;
INSERT IGNORE INTO orders(ref, price, priceSeller, priceBuyer, idbuyer, idseller, idproductdetails, IdPayMethodBuyer, IdPayMethodSeller)
VALUES ('7321401552789517582', 442, 382.80, 501.20, 2, 3, 10, 2, 3)^;
INSERT IGNORE INTO orders(ref, price, priceSeller, priceBuyer, idbuyer, idseller, idproductdetails, IdPayMethodBuyer, IdPayMethodSeller)
VALUES ('7321401552789517583', 312, 265.80, 358.20, 2, 3, 19, 2, 3)^;
INSERT IGNORE INTO orders(ref, price, priceSeller, priceBuyer, idbuyer, idseller, idproductdetails, IdPayMethodBuyer, IdPayMethodSeller)
VALUES ('7321401552789517584', 912, 805.80, 1018.20, 2, 3, 28, 2, 3)^;
INSERT IGNORE INTO orders(ref, price, priceSeller, priceBuyer, idbuyer, idseller, idproductdetails, IdPayMethodBuyer, IdPayMethodSeller)
VALUES ('7321401552789517585', 513, 446.70, 579.30, 2, 3, 34, 2, 3)^;
INSERT IGNORE INTO orders(ref, price, priceSeller, priceBuyer, idbuyer, idseller, idproductdetails, IdPayMethodBuyer, IdPayMethodSeller)
VALUES ('7321401552789517586', 256, 215.40, 296.60, 2, 3, 40, 2, 3)^;
INSERT IGNORE INTO orders(ref, price, priceSeller, priceBuyer, idbuyer, idseller, idproductdetails, IdPayMethodBuyer, IdPayMethodSeller)
VALUES ('7321401552789517587', 697, 612.30, 781.70, 2, 3, 49, 2, 3)^;
INSERT IGNORE INTO orders(ref, price, priceSeller, priceBuyer, idbuyer, idseller, idproductdetails, IdPayMethodBuyer, IdPayMethodSeller)
VALUES ('7321401552789517588', 2470, 2208.00, 2732.00, 2, 3, 58, 2, 3)^;
INSERT IGNORE INTO orders(ref, price, priceSeller, priceBuyer, idbuyer, idseller, idproductdetails, IdPayMethodBuyer, IdPayMethodSeller)
VALUES ('7321401552789517589', 343, 293.70, 392.30, 2, 3, 67, 2, 3)^;
INSERT IGNORE INTO orders(ref, price, priceSeller, priceBuyer, idbuyer, idseller, idproductdetails, IdPayMethodBuyer, IdPayMethodSeller)
VALUES ('7321401552789517590', 464, 402.60, 525.40, 2, 3, 76, 2, 3)^;

/* User 2 */
INSERT IGNORE INTO orders(ref, price, priceSeller, priceBuyer, idbuyer, idseller, idproductdetails, IdPayMethodBuyer, IdPayMethodSeller)
VALUES ('7321401552789517591', 480, 417.00, 543.00, 3, 2, 85, 3, 2)^;
INSERT IGNORE INTO orders(ref, price, priceSeller, priceBuyer, idbuyer, idseller, idproductdetails, IdPayMethodBuyer, IdPayMethodSeller)
VALUES ('7321401552789517592', 1440, 1281.00, 1599.00, 3, 2, 94, 3, 2)^;
INSERT IGNORE INTO orders(ref, price, priceSeller, priceBuyer, idbuyer, idseller, idproductdetails, IdPayMethodBuyer, IdPayMethodSeller)
VALUES ('7321401552789517593', 254, 213.60, 294.40, 3, 2, 103, 3, 2)^;
INSERT IGNORE INTO orders(ref, price, priceSeller, priceBuyer, idbuyer, idseller, idproductdetails, IdPayMethodBuyer, IdPayMethodSeller)
VALUES ('7321401552789517594', 1105, 979.50, 1230.50, 3, 2, 109, 3, 2)^;
INSERT IGNORE INTO orders(ref, price, priceSeller, priceBuyer, idbuyer, idseller, idproductdetails, IdPayMethodBuyer, IdPayMethodSeller)
VALUES ('7321401552789517595', 172, 139.80, 204.20, 3, 2, 115, 3, 2)^;
INSERT IGNORE INTO orders(ref, price, priceSeller, priceBuyer, idbuyer, idseller, idproductdetails, IdPayMethodBuyer, IdPayMethodSeller)
VALUES ('7321401552789517596', 285, 241.50, 328.50, 3, 2, 121, 3, 2)^;
INSERT IGNORE INTO orders(ref, price, priceSeller, priceBuyer, idbuyer, idseller, idproductdetails, IdPayMethodBuyer, IdPayMethodSeller)
VALUES ('7321401552789517597', 321, 273.90, 368.10, 3, 2, 127, 3, 2)^;
INSERT IGNORE INTO orders(ref, price, priceSeller, priceBuyer, idbuyer, idseller, idproductdetails, IdPayMethodBuyer, IdPayMethodSeller)
VALUES ('7321401552789517598', 321, 273.90, 368.10, 3, 2, 133, 3, 2)^;
INSERT IGNORE INTO orders(ref, price, priceSeller, priceBuyer, idbuyer, idseller, idproductdetails, IdPayMethodBuyer, IdPayMethodSeller)
VALUES ('7321401552789517599', 185, 151.50, 218.50, 3, 2, 139, 3, 2)^;
INSERT IGNORE INTO orders(ref, price, priceSeller, priceBuyer, idbuyer, idseller, idproductdetails, IdPayMethodBuyer, IdPayMethodSeller)
VALUES ('7321401552789517100', 302, 256.80, 347.20, 3, 2, 145, 3, 2)^;


/* SHIPMENTS */

/* ORDERS USER 1 */

INSERT IGNORE INTO shipments(TrackingNumber, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('78832224578314392100', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), 'Poligono Industrial Fase 2, 3G., Calle 18, Ribaseca, León', '24391',
        'España', 0, 1, 'WAREHOUSE', 'ON_WAY', 0, 1)^;
INSERT IGNORE INTO shipments(TrackingNumber, ShipDate, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('78832224578317682101', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), (SELECT DATE_ADD(NOW(), INTERVAL 6 DAY)), 'Av. de Aviación Nº13 1B, La Virgen del Camino, León', '24198',
        'España', 0, 0, 'HOME', 'PENDING_APPROVAL', 0, 1)^;

INSERT IGNORE INTO shipments(TrackingNumber, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('78832224578314392102', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), 'Poligono Industrial Fase 2, 3G., Calle 18, Ribaseca, León', '24391',
        'España', 0, 1, 'WAREHOUSE', 'ON_WAY', 0, 2)^;
INSERT IGNORE INTO shipments(TrackingNumber, ShipDate, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('78832224578317682103', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), (SELECT DATE_ADD(NOW(), INTERVAL 6 DAY)), 'Av. de Aviación Nº13 1B, La Virgen del Camino, León', '24198',
        'España', 0, 0, 'HOME', 'PENDING_APPROVAL', 0, 2)^;

INSERT IGNORE INTO shipments(TrackingNumber, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('78832224578314392104', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), 'Poligono Industrial Fase 2, 3G., Calle 18, Ribaseca, León', '24391',
        'España', 0, 1, 'WAREHOUSE', 'ON_WAY', 0, 3)^;
INSERT IGNORE INTO shipments(TrackingNumber, ShipDate, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('78832224578317682105', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), (SELECT DATE_ADD(NOW(), INTERVAL 6 DAY)), 'Av. de Aviación Nº13 1B, La Virgen del Camino, León', '24198',
        'España', 0, 0, 'HOME', 'PENDING_APPROVAL', 0, 3)^;

INSERT IGNORE INTO shipments(TrackingNumber, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('68832224578314392144', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), 'Poligono Industrial Fase 2, 3G., Calle 18, Ribaseca, León', '24391',
        'España', 0, 1, 'WAREHOUSE', 'ON_WAY', 0, 4)^;
INSERT IGNORE INTO shipments(TrackingNumber, ShipDate, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('78832224578317682145', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), (SELECT DATE_ADD(NOW(), INTERVAL 6 DAY)), 'Av. de Aviación Nº13 1B, La Virgen del Camino, León', '24198',
        'España', 0, 0, 'HOME', 'PENDING_APPROVAL', 0, 4)^;

INSERT IGNORE INTO shipments(TrackingNumber, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('68832224578314392105', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), 'Poligono Industrial Fase 2, 3G., Calle 18, Ribaseca, León', '24391',
        'España', 0, 1, 'WAREHOUSE', 'ON_WAY', 0, 5)^;
INSERT IGNORE INTO shipments(TrackingNumber, ShipDate, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('78832224578317682106', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), (SELECT DATE_ADD(NOW(), INTERVAL 6 DAY)), 'Av. de Aviación Nº13 1B, La Virgen del Camino, León', '24198',
        'España', 0, 0, 'HOME', 'PENDING_APPROVAL', 0, 5)^;

INSERT IGNORE INTO shipments(TrackingNumber, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('68832224578314392106', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), 'Poligono Industrial Fase 2, 3G., Calle 18, Ribaseca, León', '24391',
        'España', 0, 1, 'WAREHOUSE', 'ON_WAY', 0, 6)^;
INSERT IGNORE INTO shipments(TrackingNumber, ShipDate, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('78832224578317682107', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), (SELECT DATE_ADD(NOW(), INTERVAL 6 DAY)), 'Av. de Aviación Nº13 1B, La Virgen del Camino, León', '24198',
        'España', 0, 0, 'HOME', 'PENDING_APPROVAL', 0, 6)^;

INSERT IGNORE INTO shipments(TrackingNumber, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('68832224578314392107', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), 'Poligono Industrial Fase 2, 3G., Calle 18, Ribaseca, León', '24391',
        'España', 0, 1, 'WAREHOUSE', 'ON_WAY', 0, 7)^;
INSERT IGNORE INTO shipments(TrackingNumber, ShipDate, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('78832224578317682108', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), (SELECT DATE_ADD(NOW(), INTERVAL 6 DAY)), 'Av. de Aviación Nº13 1B, La Virgen del Camino, León', '24198',
        'España', 0, 0, 'HOME', 'PENDING_APPROVAL', 0, 7)^;

INSERT IGNORE INTO shipments(TrackingNumber, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('68832224578314392108', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), 'Poligono Industrial Fase 2, 3G., Calle 18, Ribaseca, León', '24391',
        'España', 0, 1, 'WAREHOUSE', 'ON_WAY', 0, 8)^;
INSERT IGNORE INTO shipments(TrackingNumber, ShipDate, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('78832224578317682109', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), (SELECT DATE_ADD(NOW(), INTERVAL 6 DAY)), 'Av. de Aviación Nº13 1B, La Virgen del Camino, León', '24198',
        'España', 0, 0, 'HOME', 'PENDING_APPROVAL', 0, 8)^;

INSERT IGNORE INTO shipments(TrackingNumber, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('68832224578314392109', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), 'Poligono Industrial Fase 2, 3G., Calle 18, Ribaseca, León', '24391',
        'España', 0, 1, 'WAREHOUSE', 'ON_WAY', 0, 9)^;
INSERT IGNORE INTO shipments(TrackingNumber, ShipDate, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('78832224578317682110', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), (SELECT DATE_ADD(NOW(), INTERVAL 6 DAY)), 'Av. de Aviación Nº13 1B, La Virgen del Camino, León', '24198',
        'España', 0, 0, 'HOME', 'PENDING_APPROVAL', 0, 9)^;

INSERT IGNORE INTO shipments(TrackingNumber, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('68832224578314392110', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), 'Poligono Industrial Fase 2, 3G., Calle 18, Ribaseca, León', '24391',
        'España', 0, 1, 'WAREHOUSE', 'ON_WAY', 0, 10)^;
INSERT IGNORE INTO shipments(TrackingNumber, ShipDate, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('78832224578317682111', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), (SELECT DATE_ADD(NOW(), INTERVAL 6 DAY)), 'Av. de Aviación Nº13 1B, La Virgen del Camino, León', '24198',
        'España', 0, 0, 'HOME', 'PENDING_APPROVAL', 0, 10)^;

/* ORDERS USER2 */

INSERT IGNORE INTO shipments(TrackingNumber, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('68832224578314392111', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), 'Poligono Industrial Fase 2, 3G., Calle 18, Ribaseca, León', '24391',
        'España', 0, 1, 'WAREHOUSE', 'ON_WAY', 0, 11)^;
INSERT IGNORE INTO shipments(TrackingNumber, ShipDate, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('78832224578317682112', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), (SELECT DATE_ADD(NOW(), INTERVAL 6 DAY)), 'Av. Quevedo Nº47 1B, León', '24009',
        'España', 0, 0, 'HOME', 'PENDING_APPROVAL', 0, 11)^;

INSERT IGNORE INTO shipments(TrackingNumber, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('68832224578314392112', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), 'Poligono Industrial Fase 2, 3G., Calle 18, Ribaseca, León', '24391',
        'España', 0, 1, 'WAREHOUSE', 'ON_WAY', 0, 12)^;
INSERT IGNORE INTO shipments(TrackingNumber, ShipDate, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('78832224578317682113', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), (SELECT DATE_ADD(NOW(), INTERVAL 6 DAY)), 'Av. Quevedo Nº47 1B, León', '24009',
        'España', 0, 0, 'HOME', 'PENDING_APPROVAL', 0, 12)^;

INSERT IGNORE INTO shipments(TrackingNumber, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('68832224578314392113', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), 'Poligono Industrial Fase 2, 3G., Calle 18, Ribaseca, León', '24391',
        'España', 0, 1, 'WAREHOUSE', 'ON_WAY', 0, 13)^;
INSERT IGNORE INTO shipments(TrackingNumber, ShipDate, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('78832224578317682114', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), (SELECT DATE_ADD(NOW(), INTERVAL 6 DAY)), 'Av. Quevedo Nº47 1B, León', '24009',
        'España', 0, 0, 'HOME', 'PENDING_APPROVAL', 0, 13)^;

INSERT IGNORE INTO shipments(TrackingNumber, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('68832224578314392114', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), 'Poligono Industrial Fase 2, 3G., Calle 18, Ribaseca, León', '24391',
        'España', 0, 1, 'WAREHOUSE', 'ON_WAY', 0, 14)^;
INSERT IGNORE INTO shipments(TrackingNumber, ShipDate, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('78832224578317682115', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), (SELECT DATE_ADD(NOW(), INTERVAL 6 DAY)), 'Av. Quevedo Nº47 1B, León', '24009',
        'España', 0, 0, 'HOME', 'PENDING_APPROVAL', 0, 14)^;

INSERT IGNORE INTO shipments(TrackingNumber, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('68832224578314392115', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), 'Poligono Industrial Fase 2, 3G., Calle 18, Ribaseca, León', '24391',
        'España', 0, 1, 'WAREHOUSE', 'ON_WAY', 0, 15)^;
INSERT IGNORE INTO shipments(TrackingNumber, ShipDate, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('78832224578317682116', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), (SELECT DATE_ADD(NOW(), INTERVAL 6 DAY)), 'Av. Quevedo Nº47 1B, León', '24009',
        'España', 0, 0, 'HOME', 'PENDING_APPROVAL', 0, 15)^;

INSERT IGNORE INTO shipments(TrackingNumber, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('68832224578314392116', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), 'Poligono Industrial Fase 2, 3G., Calle 18, Ribaseca, León', '24391',
        'España', 0, 1, 'WAREHOUSE', 'ON_WAY', 0, 16)^;
INSERT IGNORE INTO shipments(TrackingNumber, ShipDate, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('78832224578317682117', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), (SELECT DATE_ADD(NOW(), INTERVAL 6 DAY)), 'Av. Quevedo Nº47 1B, León', '24009',
        'España', 0, 0, 'HOME', 'PENDING_APPROVAL', 0, 16)^;

INSERT IGNORE INTO shipments(TrackingNumber, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('68832224578314392117', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), 'Poligono Industrial Fase 2, 3G., Calle 18, Ribaseca, León', '24391',
        'España', 0, 1, 'WAREHOUSE', 'ON_WAY', 0, 17)^;
INSERT IGNORE INTO shipments(TrackingNumber, ShipDate, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('78832224578317682118', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), (SELECT DATE_ADD(NOW(), INTERVAL 6 DAY)), 'Av. Quevedo Nº47 1B, León', '24009',
        'España', 0, 0, 'HOME', 'PENDING_APPROVAL', 0, 17)^;

INSERT IGNORE INTO shipments(TrackingNumber, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('68832224578314392118', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), 'Poligono Industrial Fase 2, 3G., Calle 18, Ribaseca, León', '24391',
        'España', 0, 1, 'WAREHOUSE', 'ON_WAY', 0, 18)^;
INSERT IGNORE INTO shipments(TrackingNumber, ShipDate, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('78832224578317682119', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), (SELECT DATE_ADD(NOW(), INTERVAL 6 DAY)), 'Av. Quevedo Nº47 1B, León', '24009',
        'España', 0, 0, 'HOME', 'PENDING_APPROVAL', 0, 18)^;

INSERT IGNORE INTO shipments(TrackingNumber, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('68832224578314392119', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), 'Poligono Industrial Fase 2, 3G., Calle 18, Ribaseca, León', '24391',
        'España', 0, 1, 'WAREHOUSE', 'ON_WAY', 0, 19)^;
INSERT IGNORE INTO shipments(TrackingNumber, ShipDate, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('78832224578317682120', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), (SELECT DATE_ADD(NOW(), INTERVAL 6 DAY)), 'Av. Quevedo Nº47 1B, León', '24009',
        'España', 0, 0, 'HOME', 'PENDING_APPROVAL', 0, 19)^;

INSERT IGNORE INTO shipments(TrackingNumber, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('68832224578314392120', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), 'Poligono Industrial Fase 2, 3G., Calle 18, Ribaseca, León', '24391',
        'España', 0, 1, 'WAREHOUSE', 'ON_WAY', 0, 20)^;
INSERT IGNORE INTO shipments(TrackingNumber, ShipDate, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('78832224578317682121', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), (SELECT DATE_ADD(NOW(), INTERVAL 6 DAY)), 'Av. Quevedo Nº47 1B, León', '24009',
        'España', 0, 0, 'HOME', 'PENDING_APPROVAL', 0, 20)^;


