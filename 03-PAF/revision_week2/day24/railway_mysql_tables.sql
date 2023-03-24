use railway;

CREATE TABLE `orders` (
  `order_id` varchar(8) NOT NULL,
  `order_date` date NOT NULL,
  `customer_name` varchar(128) NOT NULL,
  `ship_address` varchar(128) NOT NULL,
  `notes` text,
  `tax` decimal(2,2) DEFAULT '0.05',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `order_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product` varchar(64) NOT NULL,
  `unit_price` decimal(3,2) DEFAULT NULL,
  `discount` decimal(3,2) DEFAULT '1.00',
  `order_id` varchar(8) NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;