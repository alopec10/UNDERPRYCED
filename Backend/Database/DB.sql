CREATE DATABASE IF NOT EXISTS `inso2` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `inso2`;

CREATE TABLE IF NOT EXISTS `users`
(
    `IdUser` int(11) NOT NULL AUTO_INCREMENT,
    `Name` nvarchar(20) NOT NULL,
    `Surname` nvarchar(30) NOT NULL,
    `Email` nvarchar(30) NOT NULL,
    `Password` nvarchar(100) NOT NULL,
    `Address` nvarchar(40) NULL,
    `ZIPCode` varchar(10) NULL,
    `Country` nvarchar(20) NULL,
    `PhoneNumber` varchar(20) NULL,
    `SellsCompleted` smallint NOT NULL DEFAULT 0,
    `PurchasesCompleted` smallint NOT NULL DEFAULT 0,
    PRIMARY KEY (`IDUser`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `roles`
(
    `IdRole` int(11) NOT NULL AUTO_INCREMENT,
    `Name` nvarchar(20) NOT NULL,
    PRIMARY KEY (`IdRole`),
    UNIQUE(`Name`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `userRoles`
(
    `IdRole` int(11) NOT NULL,
    `IdUser` int(11) NOT NULL,
    PRIMARY KEY (`IdRole`, `IdUser`),
    CONSTRAINT `FK_ROLE_USER` FOREIGN KEY (`IdUser`) REFERENCES `users` (`IdUser`),
    CONSTRAINT `FK_USER_ROLE` FOREIGN KEY (`IdRole`) REFERENCES `roles` (`IdRole`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT IGNORE INTO roles(name) VALUES('ROLE_USER');
INSERT IGNORE INTO roles(name) VALUES('ROLE_ADMIN');

CREATE TABLE IF NOT EXISTS `paymentMethods`
(
    `IdPayMethod` int(11) NOT NULL AUTO_INCREMENT,
    `Number` varchar(16) NOT NULL,
    `CVV` varchar(3) NOT NULL,
    `ExpMonth` varchar(2) NOT NULL,
    `ExpYear` varchar(2) NOT NULL,
    `DefaultMethod` bit(1) NOT NULL DEFAULT b'0',
    `IdUser` int(11) NOT NULL,
    KEY `FK_PAYMENTMETHOD_USER` (`IdUser`),
    CONSTRAINT `FK_PAYMENTMETHOD_USER` FOREIGN KEY (`IdUser`) REFERENCES `users` (`IdUser`),
    PRIMARY KEY (`IDPayMethod`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `categories`
(
    `IdCategory` int(11) NOT NULL AUTO_INCREMENT,
    `Type` varchar(15) NOT NULL,
    PRIMARY KEY (`IdCategory`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `products`
(
    `IdProduct` int(11) NOT NULL AUTO_INCREMENT,
    `Ref` varchar(20) NOT NULL,
    `Brand` varchar(16) NOT NULL,
    `Colorway` varchar(16) NOT NULL,
    `Gender` enum('m','f','gs','ps') NOT NULL,
    `Name` varchar(20) NOT NULL,
    `ReleaseDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `RetailPrice` int NOT NULL,
    `Model` varchar(20) NOT NULL,
    `Title` varchar(30) NOT NULL,
    `Year` varchar(4) NOT NULL,
    `URL` varchar(300) NOT NULL,
    `IdCategory` int(11) NOT NULL,
    KEY `FK_PRODUCT_CATEGORY` (`IdCategory`),
    CONSTRAINT `FK_PRODUCT_CATEGORY` FOREIGN KEY (`IdCategory`) REFERENCES `categories` (`IdCategory`),
    PRIMARY KEY (`IDProduct`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `productDetails`
(
    `IdProductDetails` int(11) NOT NULL AUTO_INCREMENT,
    `Size` varchar(4) NOT NULL,
    `HighestBid` int NULL,
    `LowestAsk` int NULL,
    `LastSale` int NULL,
    `IdProduct` int(11) NOT NULL,
    KEY `FK_PRODUCTDETAILS_PRODUCT` (`IdProduct`),
    CONSTRAINT `FK_PRODUCTDETAILS_PRODUCT` FOREIGN KEY (`IdProduct`) REFERENCES `products` (`IdProduct`),
    PRIMARY KEY (`IdProductDetails`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `asks`
(
    `IdAsk` int(11) NOT NULL AUTO_INCREMENT,
    `Price` int NOT NULL,
    `Date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `Completed` bit(1)  NOT NULL DEFAULT b'0',
    `IdUser` int(11) NOT NULL,
    `IdProductDetails` int(11) NOT NULL,
    KEY `FK_ASK_USER` (`IdUser`),
    CONSTRAINT `FK_ASK_USER` FOREIGN KEY (`IdUser`) REFERENCES `users` (`IdUser`),
    KEY `FK_ASK_PRODUCTDETAILS` (`IdProductDetails`),
    CONSTRAINT `FK_ASK_PRODUCTDETAILS` FOREIGN KEY (`IdProductDetails`) REFERENCES `productDetails` (`IdProductDetails`),
    PRIMARY KEY (`IdAsk`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `bids`
(
    `IdBid` int(11) NOT NULL AUTO_INCREMENT,
    `Price` int NOT NULL,
    `Date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `Completed` bit(1)  NOT NULL DEFAULT b'0',
    `IdUser` int(11) NOT NULL,
    `IdProductDetails` int(11) NOT NULL,
    KEY `FK_BID_USER` (`IdUser`),
    CONSTRAINT `FK_BID_USER` FOREIGN KEY (`IdUser`) REFERENCES `users` (`IdUser`),
    KEY `FK_BID_PRODUCTDETAILS` (`IdProductDetails`),
    CONSTRAINT `FK_BID_PRODUCTDETAILS` FOREIGN KEY (`IdProductDetails`) REFERENCES `productDetails` (`IdProductDetails`),
    PRIMARY KEY (`IdBid`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `orders`
(
    `IdOrder` int(11) NOT NULL AUTO_INCREMENT,
    `Price` int NOT NULL,
    `Date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,

    `IdBuyer` int(11) NOT NULL,
    `IdSeller` int(11) NOT NULL,
    `IdProductDetails` int(11) NOT NULL,

    KEY `FK_ORDER_BUYER` (`IdBuyer`),
    CONSTRAINT `FK_ORDER_BUYER` FOREIGN KEY (`IdBuyer`) REFERENCES `users` (`IdUser`),
    KEY `FK_ORDER_SELLER` (`IdSeller`),
    CONSTRAINT `FK_ORDER_SELLER` FOREIGN KEY (`IdSeller`) REFERENCES `users` (`IdUser`),
    KEY `FK_ORDER_PRODUCTDETAILS` (`IdProductDetails`),
    CONSTRAINT `FK_ORDER_PRODUCTDETAILS` FOREIGN KEY (`IdProductDetails`) REFERENCES `productDetails` (`IdProductDetails`),
    PRIMARY KEY (`IdOrder`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `shipments`
(
    `IdShipment` int(11) NOT NULL AUTO_INCREMENT,
    `TrackingNumber` varchar(20) NOT NULL,
    `ShipDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `Address` nvarchar(40) NOT NULL,
    `ZIPCode` varchar(10) NOT NULL,
    `Country` nvarchar(20) NOT NULL,
    `Completed` bit(1)  NOT NULL DEFAULT b'0',
    `IdOrder` int(11) NOT NULL,

    KEY `FK_SHIPMENT_ORDER` (`IDOrder`),
    CONSTRAINT `FK_SHIPMENT_ORDER` FOREIGN KEY (`IDOrder`) REFERENCES `orders` (`IDOrder`),
    PRIMARY KEY (`IdShipment`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

