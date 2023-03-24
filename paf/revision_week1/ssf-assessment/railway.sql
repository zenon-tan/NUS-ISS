CREATE TABLE `pizzadb` (
  `pizza_name` varchar(128) NOT NULL,
  `img_url` varchar(255) NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`pizza_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pizza` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pizza_name` varchar(128) NOT NULL,
  `size` decimal(3,2) NOT NULL,
  `quantity` int NOT NULL,
  `cart_id` varchar(8) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cart_id` (`cart_id`),
  KEY `pizza_name_idx` (`pizza_name`),
  CONSTRAINT `pizza_ibfk_1` FOREIGN KEY (`cart_id`) REFERENCES `pizza_cart` (`cart_id`),
  CONSTRAINT `pizza_name` FOREIGN KEY (`pizza_name`) REFERENCES `pizzadb` (`pizza_name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pizza_cart` (
  `cart_id` varchar(8) NOT NULL,
  `price` float NOT NULL,
  `order_id` varchar(8) NOT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `order_id_idx` (`order_id`),
  CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `pizza_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `customer` (
  `customer_id` varchar(8) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(8) NOT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pizza_order` (
  `id` varchar(8) NOT NULL,
  `delivery_date` date NOT NULL,
  `is_rush` varchar(8) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `total_cost` float NOT NULL,
  `customer_id` varchar(8) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_id_idx` (`customer_id`),
  CONSTRAINT `customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



