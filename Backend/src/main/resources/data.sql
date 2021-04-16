USE `inso2` ^;

/* ROLES */
INSERT IGNORE INTO roles(name)
VALUES ('ROLE_USER') ^;
INSERT IGNORE INTO roles(name)
VALUES ('ROLE_ADMIN') ^;

/* USERS */
INSERT IGNORE INTO users(name, surname, email, password, Address, ZIPCode, Country, PhoneNumber)
VALUES ('Admin', 'Admin', 'admin@admin.com', '$2a$10$YRA9C3jPTVrQs8wbIux7R.8Vx9R3uCp7g7Bg5rJ/Haj8y4C6PB.Cu',
        'C/ Admin', '24198', 'Spain', '987402130')^;

INSERT IGNORE INTO users(name, surname, email, password, Address, ZIPCode, Country, PhoneNumber)
VALUES ('User', 'User', 'user@user.com', '$2a$10$h0oOdH.VpWWMslVYdY.qQ.WyQ6HnE52Wc3EI5n7YH3DL6wm8CjVoS',
        'C/ User', '24198', 'Spain', '987404530')^;

INSERT IGNORE INTO users(name, surname, email, password, Address, ZIPCode, Country, PhoneNumber)
VALUES ('User2', 'User2', 'user2@user.com', '$2a$10$esr0atHpssbhlyiu5SUiUORUzLvx8fZ8yh5suRt17CAgO8LEpaPvy',
        'C/ User2', '24198', 'Spain', '987404530')^;

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
INSERT IGNORE INTO paymentMethods (Name, Number, CVV, ExpMonth, ExpYear, DefaultMethod, IdUser)
VALUES ('User User', 'SbwcEVenGSm4sjJV+5wgXY9VUODwp2CtTnWQSxlUZqQ=', 'NmC7te8QtgfJyTysOwiXiQ==', '5dMG8a6/5tTzwIIRNmT+EA==', '5dMG8a6/5tTzwIIRNmT+EA==', 1, 2)^;

INSERT IGNORE INTO paymentMethods (Name, Number, CVV, ExpMonth, ExpYear, DefaultMethod, IdUser)
VALUES ('User User', 'sOVC/hgza8MF/2jvTdezlSxC9eB/fkUsb3y/Eqi6xSM=', 'NQog9zEmpu80u5h5VFAdgQ==', 'GssQiQZlot4eSKWn+mDYew==', 'SchmHhUO5FQ0ikOQZAmylw==', 0, 2)^;

INSERT IGNORE INTO paymentMethods (Name, Number, CVV, ExpMonth, ExpYear, DefaultMethod, IdUser)
VALUES ('User2 User2', 'rkLYU9qn7hvIkOcGuqC8gylD8f/lZZwoWzJ4FU6GODA=', 'NmC7te8QtgfJyTysOwiXiQ==', '5dMG8a6/5tTzwIIRNmT+EA==', '5dMG8a6/5tTzwIIRNmT+EA==', 1, 3)^;

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
        'https://images.stockx.com/images/Gucci-x-The-North-Face-Cotton-Sweater-Beige.jpg?fit=clip&bg=FFFFFF&w=700&h=500&auto=compress&q=90&dpr=2&trim=color&updated_at=1611134844&fm=webp&ixlib=react-9.0.3&w=1446',
        (SELECT IdCategory FROM categories WHERE Type = 'Ropa'))^;

INSERT IGNORE INTO products(REF, BRAND, COLORWAY, GENDER, NAME, RELEASEDATE, RETAILPRICE, MODEL, TITLE, YEAR, URL,
                            IDCATEGORY)
VALUES ('8654d027-dafb-676d-b309-c8e12bf1f15b', 'Supreme', 'Red', 'M', 'Cross Box Logo', '2020-12-03', 168,
        'Cross Box Logo Hooded Sweatshirt', 'Supreme Cross Box Logo Hooded Sweatshirt Red', '2020',
        'https://images.stockx.com/images/Supreme-Cross-Box-Logo-Hooded-Sweatshirt-Red.jpg?fit=clip&bg=FFFFFF&w=700&h=500&auto=compress&q=90&dpr=2&trim=color&updated_at=1607005929&fm=webp&ixlib=react-9.0.3&w=1446',
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

/* ASKS */
INSERT IGNORE INTO asks(price, iduser, idproductdetails)
VALUES (251, (SELECT IdUser FROM users WHERE Email = 'user@user.com'), 1)^;

INSERT IGNORE INTO asks(price, completed, iduser, idproductdetails)
VALUES (330, 1, (SELECT IdUser FROM users WHERE Email = 'user@user.com'), 15)^;

INSERT IGNORE INTO asks(price, iduser, idproductdetails)
VALUES (350, (SELECT IdUser FROM users WHERE Email = 'user@user.com'), 16)^;

INSERT IGNORE INTO asks(price, iduser, idproductdetails)
VALUES (375, (SELECT IdUser FROM users WHERE Email = 'user@user.com'), 34)^;

INSERT IGNORE INTO asks(price, iduser, idproductdetails)
VALUES (240, (SELECT IdUser FROM users WHERE Email = 'user2@user.com'), 25)^;

INSERT IGNORE INTO asks(price, iduser, idproductdetails)
VALUES (315, (SELECT IdUser FROM users WHERE Email = 'user2@user.com'), 18)^;

INSERT IGNORE INTO asks(price, iduser, idproductdetails)
VALUES (399, (SELECT IdUser FROM users WHERE Email = 'user2@user.com'), 37)^;

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
VALUES ('7321401552789517581', 330, (SELECT IdUser FROM users WHERE Email = 'user2@user.com'),
        (SELECT IdUser FROM users WHERE Email = 'user@user.com'), (SELECT IdProductDetails
                                                                   FROM productDetails pd
                                                                            INNER JOIN products p ON pd.IdProduct = p.IdProduct
                                                                   WHERE p.Ref = 'fa1a8868-9dd0-4b1b-a52f-dc248143d797'
                                                                     AND pd.size = '43'), 3, 2)^;

INSERT IGNORE INTO orders(ref, price, idbuyer, idseller, idproductdetails, IdPayMethodBuyer, IdPayMethodSeller)
VALUES ('7376701552706717555', 215, (SELECT IdUser FROM users WHERE Email = 'user@user.com'),
        (SELECT IdUser FROM users WHERE Email = 'user2@user.com'), (SELECT IdProductDetails
                                                                    FROM productDetails pd
                                                                             INNER JOIN products p ON pd.IdProduct = p.IdProduct
                                                                    WHERE p.Ref = '8334d027-dafb-426d-b209-b8e07bf1b15b'
                                                                      AND pd.size = '44'), 2, 3)^;

INSERT IGNORE INTO orders(ref, price, idbuyer, idseller, idproductdetails, IdPayMethodBuyer, IdPayMethodSeller)
VALUES ('7376213552988817555', 223, (SELECT IdUser FROM users WHERE Email = 'user2@user.com'),
        (SELECT IdUser FROM users WHERE Email = 'user@user.com'), (SELECT IdProductDetails
                                                                   FROM productDetails pd
                                                                            INNER JOIN products p ON pd.IdProduct = p.IdProduct
                                                                   WHERE p.Ref = '1f307b17-e640-4650-8d76-c6a37584e5a1'
                                                                     AND pd.size = '40'), 3, 2)^;

/* SHIPMENTS */

INSERT IGNORE INTO shipments(trackingnumber, address, zipcode, country, idorder)
VALUES ('78832224578314392100', 'C/ User2', '24198', 'Spain', (SELECT IdOrder
                                                               FROM orders o
                                                                        INNER JOIN users b on o.IdBuyer = b.IdUser
                                                                        INNER JOIN users s on o.IdSeller = s.IdUser
                                                                        INNER JOIN productDetails pd on o.IdProductDetails = pd.IdProductDetails
                                                                        INNER JOIN products p on pd.IdProduct = p.IdProduct
                                                               WHERE b.Email = 'user2@user.com'
                                                                 and s.Email = 'user@user.com'
                                                                 and p.Ref = 'fa1a8868-9dd0-4b1b-a52f-dc248143d797'
                                                                 and pd.size = '43'))^;

INSERT IGNORE INTO shipments(trackingnumber, address, zipcode, country, completed, idorder)
VALUES ('43432224579612146100', 'C/ User', '24198', 'Spain', 1, (SELECT IdOrder
                                                                 FROM orders o
                                                                          INNER JOIN users b on o.IdBuyer = b.IdUser
                                                                          INNER JOIN users s on o.IdSeller = s.IdUser
                                                                          INNER JOIN productDetails pd on o.IdProductDetails = pd.IdProductDetails
                                                                          INNER JOIN products p on pd.IdProduct = p.IdProduct
                                                                 WHERE b.Email = 'user@user.com'
                                                                   and s.Email = 'user2@user.com'
                                                                   and p.Ref = '8334d027-dafb-426d-b209-b8e07bf1b15b'
                                                                   and pd.size = '44'))^;

INSERT IGNORE INTO shipments(trackingnumber, address, zipcode, country, idorder)
VALUES ('70432134579612416100', 'C/ User2', '24198', 'Spain', (SELECT IdOrder
                                                               FROM orders o
                                                                        INNER JOIN users b on o.IdBuyer = b.IdUser
                                                                        INNER JOIN users s on o.IdSeller = s.IdUser
                                                                        INNER JOIN productDetails pd on o.IdProductDetails = pd.IdProductDetails
                                                                        INNER JOIN products p on pd.IdProduct = p.IdProduct
                                                               WHERE b.Email = 'user2@user.com'
                                                                 and s.Email = 'user@user.com'
                                                                 and p.Ref = '1f307b17-e640-4650-8d76-c6a37584e5a1'
                                                                 and pd.size = '40'))^;

