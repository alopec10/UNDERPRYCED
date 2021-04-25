USE `inso2` ^;

CREATE TABLE IF NOT EXISTS `users`
(
    `IdUser` int(11) NOT NULL AUTO_INCREMENT,
    `Name` nvarchar(20) NOT NULL,
    `Surname` nvarchar(30) NOT NULL,
    `Email` nvarchar(30) NOT NULL,
    `Password` nvarchar(100) NOT NULL,
    `Address` nvarchar(150) NULL,
    `ZIPCode` varchar(10) NULL,
    `Country` nvarchar(20) NULL,
    `PhoneNumber` varchar(20) NULL,
    `SellsCompleted` smallint NOT NULL DEFAULT 0,
    `PurchasesCompleted` smallint NOT NULL DEFAULT 0,
    UNIQUE(`Email`),
    PRIMARY KEY (`IDUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ^;

CREATE TABLE IF NOT EXISTS `roles`
(
    `IdRole` int(11) NOT NULL AUTO_INCREMENT,
    `Name` nvarchar(20) NOT NULL,
    PRIMARY KEY (`IdRole`),
    UNIQUE(`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ^;

CREATE TABLE IF NOT EXISTS `userRoles`
(
    `IdRole` int(11) NOT NULL,
    `IdUser` int(11) NOT NULL,
    PRIMARY KEY (`IdRole`, `IdUser`),
    CONSTRAINT `FK_ROLE_USER` FOREIGN KEY (`IdUser`) REFERENCES `users` (`IdUser`),
    CONSTRAINT `FK_USER_ROLE` FOREIGN KEY (`IdRole`) REFERENCES `roles` (`IdRole`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ^;

CREATE TABLE IF NOT EXISTS `paymentMethods`
(
    `IdPayMethod` int(11) NOT NULL AUTO_INCREMENT,
    `Name` nvarchar(50) NOT NULL,
    `Number` nvarchar(100) NOT NULL,
    `CVV` nvarchar(100) NOT NULL,
    `ExpMonth` nvarchar(100) NOT NULL,
    `ExpYear` nvarchar(100) NOT NULL,
    `DefaultMethod` bit(1) NOT NULL DEFAULT b'0',
    `IsActive` bit(1) NOT NULL DEFAULT b'1',
    `IdUser` int(11) NOT NULL,
    KEY `FK_PAYMENTMETHOD_USER` (`IdUser`),
    CONSTRAINT `FK_PAYMENTMETHOD_USER` FOREIGN KEY (`IdUser`) REFERENCES `users` (`IdUser`),
    UNIQUE(`Number`, `IdUser`),
    PRIMARY KEY (`IDPayMethod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ^;

CREATE TABLE IF NOT EXISTS `categories`
(
    `IdCategory` int(11) NOT NULL AUTO_INCREMENT,
    `Type` varchar(15) NOT NULL,
    UNIQUE(`Type`),
    PRIMARY KEY (`IdCategory`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ^;

CREATE TABLE IF NOT EXISTS `products`
(
    `IdProduct` int(11) NOT NULL AUTO_INCREMENT,
    `Ref` varchar(50) NOT NULL,
    `Brand` varchar(16) NOT NULL,
    `Colorway` varchar(40) NOT NULL,
    `Gender` enum('M','F','GS','PS') NOT NULL,
    `Name` varchar(50) NOT NULL,
    `ReleaseDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `RetailPrice` int NOT NULL,
    `Model` varchar(20) NOT NULL,
    `Title` varchar(80) NOT NULL,
    `Year` varchar(4) NOT NULL,
    `URL` varchar(300) NOT NULL,
    `IdCategory` int(11) NOT NULL,
    KEY `FK_PRODUCT_CATEGORY` (`IdCategory`),
    CONSTRAINT `FK_PRODUCT_CATEGORY` FOREIGN KEY (`IdCategory`) REFERENCES `categories` (`IdCategory`),
    UNIQUE(`Ref`),
    PRIMARY KEY (`IDProduct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ^;

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
    UNIQUE(`Size`, `IdProduct`),
    PRIMARY KEY (`IdProductDetails`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ^;

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
    UNIQUE(`IdUser`, `IdProductDetails`),
    PRIMARY KEY (`IdAsk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ^;

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
    UNIQUE(`IdUser`, `IdProductDetails`),
    PRIMARY KEY (`IdBid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ^;


CREATE TABLE IF NOT EXISTS `orders`
(
    `IdOrder` int(11) NOT NULL AUTO_INCREMENT,
    `Ref` nvarchar(100) NOT NULL,
    `Price` int NOT NULL,
    `Date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `IdBuyer` int(11) NOT NULL,
    `IdSeller` int(11) NOT NULL,
    `IdProductDetails` int(11) NOT NULL,
    `IdPayMethodBuyer` int(11) NOT NULL,
    `IdPayMethodSeller` int(11) NOT NULL,
    KEY `FK_ORDER_BUYER` (`IdBuyer`),
    CONSTRAINT `FK_ORDER_BUYER` FOREIGN KEY (`IdBuyer`) REFERENCES `users` (`IdUser`),
    KEY `FK_ORDER_SELLER` (`IdSeller`),
    CONSTRAINT `FK_ORDER_SELLER` FOREIGN KEY (`IdSeller`) REFERENCES `users` (`IdUser`),
    KEY `FK_ORDER_PRODUCTDETAILS` (`IdProductDetails`),
    CONSTRAINT `FK_ORDER_PRODUCTDETAILS` FOREIGN KEY (`IdProductDetails`) REFERENCES `productDetails` (`IdProductDetails`),
    KEY `FK_ORDER_PAYMENTBUYER` (`IdPayMethodBuyer`),
    CONSTRAINT `FK_ORDER_PAYMENTBUYER` FOREIGN KEY (`IdPayMethodBuyer`) REFERENCES `paymentMethods` (`IdPayMethod`),
    KEY `FK_ORDER_PAYMENTSELLER` (`IdPayMethodSeller`),
    CONSTRAINT `FK_ORDER_PAYMENTSELLER` FOREIGN KEY (`IdPayMethodSeller`) REFERENCES `paymentMethods` (`IdPayMethod`),
    UNIQUE(`Ref`),
    PRIMARY KEY (`IdOrder`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ^;

CREATE TABLE IF NOT EXISTS `shipments`
(
    `IdShipment` int(11) NOT NULL AUTO_INCREMENT,
    `TrackingNumber` varchar(100) NOT NULL,
    `ShipDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `ArrivalDate` datetime NOT NULL,
    `Address` nvarchar(150) NOT NULL,
    `ZIPCode` varchar(10) NOT NULL,
    `Country` nvarchar(20) NOT NULL,
    `Completed` bit(1)  NOT NULL DEFAULT b'0',
    `Sent` bit(1)  NOT NULL DEFAULT b'0',
    `Type` enum('WAREHOUSE', 'HOME') NOT NULL,
    `Approved`bit(1)  NOT NULL DEFAULT b'0',
    `IdOrder` int(11) NOT NULL,
    KEY `FK_SHIPMENT_ORDER` (`IDOrder`),
    CONSTRAINT `FK_SHIPMENT_ORDER` FOREIGN KEY (`IDOrder`) REFERENCES `orders` (`IDOrder`),
    UNIQUE(`TrackingNumber`),
    PRIMARY KEY (`IdShipment`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ^;

/* TRIGGERS */

/* Update lowest ask value in productDetails when an ask is inserted in the database */
DROP TRIGGER IF EXISTS modify_lowest_ask_after_insert ^;
CREATE TRIGGER modify_lowest_ask_after_insert
    AFTER INSERT
    ON `asks` FOR EACH ROW
BEGIN
    SET @lowestAsk = (SELECT LowestAsk FROM productDetails WHERE IdProductDetails = NEW.IdProductDetails);
    IF @lowestAsk IS NULL OR @lowestAsk > NEW.Price THEN
        UPDATE productDetails
        SET LowestAsk = NEW.Price
        WHERE IdProductDetails = NEW.IdProductDetails;
    END IF;
END ^;

/* Update lowest ask value in productDetails when an ask is updated in the database */
DROP TRIGGER IF EXISTS modify_lowest_ask_after_update ^;
CREATE TRIGGER modify_lowest_ask_after_update
    AFTER UPDATE
    ON `asks` FOR EACH ROW
BEGIN
    SET @lowestAsk = (SELECT LowestAsk FROM productDetails WHERE IdProductDetails = NEW.IdProductDetails);
    IF @lowestAsk IS NULL OR @lowestAsk = OLD.Price OR @lowestAsk > NEW.Price THEN
        SET @newLowestAsk = (SELECT MIN(Price) FROM asks WHERE IdProductDetails = NEW.IdProductDetails);
        UPDATE productDetails
        SET LowestAsk = @newLowestAsk
        WHERE IdProductDetails = NEW.IdProductDetails;
    END IF;
END ^;

/* Update lowest ask value in productDetails when an ask is deleted in the database */
DROP TRIGGER IF EXISTS modify_lowest_ask_after_delete ^;
CREATE TRIGGER modify_lowest_ask_after_delete
    AFTER DELETE
    ON `asks` FOR EACH ROW
BEGIN
    SET @lowestAsk = (SELECT LowestAsk FROM productDetails WHERE IdProductDetails = OLD.IdProductDetails);
    IF @lowestAsk = OLD.Price THEN
        SET @newLowestAsk = (SELECT MIN(Price) FROM asks WHERE IdProductDetails = OLD.IdProductDetails);
        UPDATE productDetails
        SET LowestAsk = @newLowestAsk
        WHERE IdProductDetails = OLD.IdProductDetails;
    END IF;
END ^;

/* Update highest bid value in productDetails when a bid is inserted in the database */
DROP TRIGGER IF EXISTS modify_highest_bid_after_insert ^;
CREATE TRIGGER modify_highest_bid_after_insert
    AFTER INSERT
    ON `bids` FOR EACH ROW
BEGIN
    SET @highestBid = (SELECT HighestBid FROM productDetails WHERE IdProductDetails = NEW.IdProductDetails);
    IF @highestBid IS NULL OR @highestBid < NEW.Price THEN
        UPDATE productDetails
        SET HighestBid = NEW.Price
        WHERE IdProductDetails = NEW.IdProductDetails;
    END IF;
END ^;

/* Update highest bid value in productDetails when a bid is inserted in the database */
DROP TRIGGER IF EXISTS modify_highest_bid_after_update ^;
CREATE TRIGGER modify_highest_bid_after_update
    AFTER UPDATE
    ON `bids` FOR EACH ROW
BEGIN
    SET @highestBid = (SELECT HighestBid FROM productDetails WHERE IdProductDetails = NEW.IdProductDetails);
    IF @highestBid IS NULL OR @highestBid < NEW.Price OR @highestBid = OLD.Price THEN
        SET @newHighestBid = (SELECT MAX(Price) FROM bids WHERE IdProductDetails = NEW.IdProductDetails);
        UPDATE productDetails
        SET HighestBid = @newHighestBid
        WHERE IdProductDetails = NEW.IdProductDetails;
    END IF;
END ^;

/* Update highest bid value in productDetails when a bid is deleted in the database */
DROP TRIGGER IF EXISTS modify_highest_bid_after_delete ^;
CREATE TRIGGER modify_highest_bid_after_delete
    AFTER DELETE
    ON `bids` FOR EACH ROW
BEGIN
    SET @highestBid = (SELECT HighestBid FROM productDetails WHERE IdProductDetails = OLD.IdProductDetails);
    IF @highestBid = OLD.Price THEN
        SET @newHighestBid = (SELECT MAX(Price) FROM bids WHERE IdProductDetails = OLD.IdProductDetails);
        UPDATE productDetails
        SET HighestBid = @newHighestBid
        WHERE IdProductDetails = OLD.IdProductDetails;
    END IF;
END ^;

/* Update last sale value in productDetails when an order is inserted in the database */
DROP TRIGGER IF EXISTS modify_last_sale_after_insert ^;
CREATE TRIGGER modify_last_sale_after_insert
    AFTER INSERT
    ON `orders` FOR EACH ROW
BEGIN
        UPDATE productDetails
        SET lastSale = NEW.Price
        WHERE IdProductDetails = NEW.IdProductDetails;
        UPDATE users
        SET PurchasesCompleted = PurchasesCompleted + 1
        WHERE IdUser = NEW.IdBuyer;
        UPDATE users
        SET SellsCompleted = SellsCompleted + 1
        WHERE IdUser = NEW.IdSeller;
END ^;