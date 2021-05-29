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
VALUES ('User', 'User', 'user@user.com', '$2a$10$h0oOdH.VpWWMslVYdY.qQ.WyQ6HnE52Wc3EI5n7YH3DL6wm8CjVoS',
        'Avda. Independencia, 14, León', '24001', 'España', '987404530')^;

INSERT IGNORE INTO users(name, surname, email, password, Address, ZIPCode, Country, PhoneNumber)
VALUES ('User2', 'User2', 'user2@user.com', '$2a$10$esr0atHpssbhlyiu5SUiUORUzLvx8fZ8yh5suRt17CAgO8LEpaPvy',
        'Av. Lancia, 12, local 3, León', '24004', 'España', '987403130')^;

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
INSERT IGNORE INTO bids(price, completed, iduser, idproductdetails)
VALUES (215, 1, (SELECT IdUser FROM users WHERE Email = 'user@user.com'), 25)^;

INSERT IGNORE INTO bids(price, iduser, idproductdetails)
VALUES (255, (SELECT IdUser FROM users WHERE Email = 'user@user.com'), 22)^;

INSERT IGNORE INTO bids(price, iduser, idproductdetails)
VALUES (813, (SELECT IdUser FROM users WHERE Email = 'user@user.com'), 30)^;

INSERT IGNORE INTO bids(price, completed, iduser, idproductdetails)
VALUES (223, 1, (SELECT IdUser FROM users WHERE Email = 'user2@user.com'), 1)^;

INSERT IGNORE INTO bids(price, iduser, idproductdetails)
VALUES (821, (SELECT IdUser FROM users WHERE Email = 'user2@user.com'), 30)^;

/* ORDERS */

INSERT IGNORE INTO orders(ref, price, idbuyer, idseller, idproductdetails, IdPayMethodBuyer, IdPayMethodSeller)
VALUES ('7321401552789517581', 330, 3, 2, 15, 3, 2)^;

INSERT IGNORE INTO orders(ref, price, idbuyer, idseller, idproductdetails, IdPayMethodBuyer, IdPayMethodSeller)
VALUES ('7376701552706717555', 215, 2, 3, 25, 2, 3)^;

INSERT IGNORE INTO orders(ref, price, idbuyer, idseller, idproductdetails, IdPayMethodBuyer, IdPayMethodSeller)
VALUES ('7376213552988817555', 223, 3, 2, 1, 3, 2)^;

/* SHIPMENTS */

INSERT IGNORE INTO shipments(TrackingNumber, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('78832224578314392100', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), 'Poligono Industrial Fase 2, 3G., Calle 18, Ribaseca, León', '24198', 'España', 0, 1, 'WAREHOUSE', 'ON_WAY', 0, 1)^;

INSERT IGNORE INTO shipments(TrackingNumber, ShipDate, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('78832224578317682100', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), (SELECT DATE_ADD(NOW(), INTERVAL 6 DAY)), 'C/ User2 Nº 1, User2', '24198', 'España', 0, 0, 'HOME', 'PENDING_APPROVAL', 0, 1)^;

INSERT IGNORE INTO shipments(TrackingNumber, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('78832255578314392100', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), 'Poligono Industrial Fase 2, 3G., Calle 18, Ribaseca, León', '24198', 'España', 0, 1, 'WAREHOUSE', 'ON_WAY', 0, 2)^;

INSERT IGNORE INTO shipments(TrackingNumber, ShipDate, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('78832224578979682100', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), (SELECT DATE_ADD(NOW(), INTERVAL 6 DAY)), 'C/ User Nº 1, User', '24198', 'España', 0, 0, 'HOME', 'PENDING_APPROVAL', 0, 2)^;

INSERT IGNORE INTO shipments(TrackingNumber, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('78832255444314392100', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), 'Poligono Industrial Fase 2, 3G., Calle 18, Ribaseca, León', '24198', 'España', 0, 1, 'WAREHOUSE', 'ON_WAY', 0, 3)^;

INSERT IGNORE INTO shipments(TrackingNumber, ShipDate, ArrivalDate, Address, ZIPCode, Country, Completed, Sent, Type, Status, Approved, IdOrder)
VALUES ('7883222457895245282100', (SELECT DATE_ADD(NOW(), INTERVAL 3 DAY)), (SELECT DATE_ADD(NOW(), INTERVAL 6 DAY)), 'C/ User2 Nº 1, User2', '24198', 'España', 0, 0, 'HOME', 'PENDING_APPROVAL', 0, 3)^;

