USE [master]
GO

DROP DATABASE IF EXISTS [FRUIT_STORE];
GO

CREATE DATABASE [FRUIT_STORE];
GO

USE [FRUIT_STORE];
GO

DROP TABLE IF EXISTS order_line;
DROP TABLE IF EXISTS [order];
DROP TABLE IF EXISTS status;
DROP TABLE IF EXISTS shipping_type;

DROP TABLE IF EXISTS shopping_cart_item;
DROP TABLE IF EXISTS shopping_cart;

DROP TABLE IF EXISTS user_payment;
DROP TABLE IF EXISTS user_address;
DROP TABLE IF EXISTS site_user;
DROP TABLE IF EXISTS provider;

DROP TABLE IF EXISTS product_configuration;
DROP TABLE IF EXISTS product_item;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS variation;
DROP TABLE IF EXISTS product_category;
DROP TABLE IF EXISTS product_type;

GO

CREATE TABLE product_category (
  id INT IDENTITY(0,1),
  name NVARCHAR(30),
  CONSTRAINT PK_product_category PRIMARY KEY (id)
);

CREATE TABLE product_type (
  id INT IDENTITY(0,1),
  name NVARCHAR(20),
  CONSTRAINT PK_product_type PRIMARY KEY (id)
);

CREATE TABLE variation (
  id INT IDENTITY(0,1), 
  product_type_id INT,
  value DECIMAL(15, 2),
  price_percentage DECIMAL(3, 2),
  CONSTRAINT PK_variation PRIMARY KEY (id),
  FOREIGN KEY (product_type_id) REFERENCES product_type(id),
  CONSTRAINT CHK_variation CHECK (value >= 0 AND price_percentage >= 0 AND price_percentage <= 1)
);

CREATE TABLE product (
  id INT IDENTITY(0,1),
  category_id INT,
  name NVARCHAR(100),
  description NVARCHAR(1000),
  product_image NVARCHAR(500),
  CONSTRAINT PK_product PRIMARY KEY (id),
  FOREIGN KEY (category_id) REFERENCES product_category(id)
);

CREATE TABLE product_item (
  id INT IDENTITY(0,1),
  product_id INT,
  qty_in_stock DECIMAL(15, 2),
  price DECIMAL(15, 2),
  product_type_id INT,
  CONSTRAINT PK_product_item PRIMARY KEY (id),
  FOREIGN KEY (product_id) REFERENCES product(id),
  FOREIGN KEY (product_type_id) REFERENCES product_type(id),
  CONSTRAINT CHK_product_item CHECK (qty_in_stock >= 0 AND price > 0)
);

CREATE TABLE product_configuration (
  id INT IDENTITY(0,1),
  product_item_id INT,
  variation_id INT,
  CONSTRAINT PK_producct_configuration PRIMARY KEY (id),
  CONSTRAINT FK_product_item_id FOREIGN KEY (product_item_id) REFERENCES product_item(id),
  CONSTRAINT FK_variation_id FOREIGN KEY (variation_id) REFERENCES variation(id)
);

CREATE TABLE site_user (
  id INT IDENTITY(0,1),
  username NVARCHAR(50) NOT NULL,
  password NVARCHAR(50) NOT NULL, 
  email NVARCHAR(100) NOT NULL, 
  phone_number NVARCHAR(10) NOT NULL,
  CONSTRAINT PK_site_user PRIMARY KEY (ID)
);

CREATE TABLE user_address (
  user_id INT NOT NULL,
  address NVARCHAR(150),
  is_main_address BIT,
  CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES site_user(id)
);

CREATE TABLE provider (
  id INT IDENTITY(0,1),
  name NVARCHAR(30) NOT NULL,
  CONSTRAINT PK_provider PRIMARY KEY (id)
);

CREATE TABLE user_payment (
  id INT IDENTITY(0,1),
  user_id INT NOT NULL,
  provider_id INT NOT NULL,
  account_number NVARCHAR(20) NOT NULL,
  expiracy_date DATE NOT NULL,
  CONSTRAINT PK_user_payment PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES site_user(id),
  FOREIGN KEY (provider_id) REFERENCES provider(id)
);

CREATE TABLE shopping_cart (
  id INT IDENTITY(0,1),
  user_id INT,
  CONSTRAINT PK_shopping_cart PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES site_user(id)
);

CREATE TABLE shopping_cart_item (
  id INT IDENTITY(0,1),
  cart_id INT,
  product_config_id INT,
  quantity DECIMAL(15, 2),
  CONSTRAINT PK_shopping_cart_item PRIMARY KEY (id),
  FOREIGN KEY (cart_id) REFERENCES shopping_cart(id),
  FOREIGN KEY (product_config_id) REFERENCES product_configuration(id)
);

CREATE TABLE shipping_type (
  id INT IDENTITY(0,1),
  type NVARCHAR(20),
  price DECIMAL(15, 2),
  CONSTRAINT PK_shipping_type PRIMARY KEY (id)
);

CREATE TABLE status (
  id INT IDENTITY(0,1),
  name NVARCHAR(10),
  CONSTRAINT PK_status PRIMARY KEY (id)
);

CREATE TABLE [order] (
  id INT IDENTITY(0,1),
  user_id INT,
  date DATETIME,
  payment_id INT,
  address NVARCHAR(150),
  shipping_type_id INT,
  total_price DECIMAL(15, 2),
  status_id INT,
  CONSTRAINT PK_order PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES site_user(id),
  FOREIGN KEY (payment_id) REFERENCES provider(id),
  FOREIGN KEY (shipping_type_id) REFERENCES shipping_type(id),
  FOREIGN KEY (status_id) REFERENCES status(id),
  CONSTRAINT CHK_payment CHECK (total_price >= 0)
);

CREATE TABLE order_line (
  id INT IDENTITY(0,1),
  product_config_id INT,
  order_id INT,
  quantity DECIMAL(15, 2),	
  calculated_price DECIMAL(15, 2),
  CONSTRAINT PK_order_line PRIMARY KEY(id),
  FOREIGN KEY (product_config_id) REFERENCES product_configuration(id),
  FOREIGN KEY (order_id) REFERENCES [order](id),
  CONSTRAINT CHK_calculated_price CHECK (calculated_price >= 0)
);

INSERT INTO product_category (name) VALUES
(N'Vegetables'),
(N'Fruits'),
(N'Legumes'),
(N'Nuts and seeds'),
(N'Herbs and spices'),
(N'Mushrooms');

INSERT INTO product (category_id, name, description, product_image) VALUES
(0, N'Potatoes', N'Versatile spuds for hearty comfort dishes and crispy fries.', N'https://clipart-library.com/image_gallery2/Potato-Free-Download-PNG.png'),
(0, N'Onions', N'Add zing to your meals with these aromatic bulbs.', N'https://www.freepnglogos.com/uploads/onion-png/onion-onions-png-image-pngpix-9.png'),
(0, N'Lettuce', N'Crisp, refreshing leaves for vibrant salads.', N'https://freepngimg.com/save/155958-green-organic-lettuce-free-download-png-hd/872x658'),
(0, N'Tomatoes', N'Juicy, sun-kissed orbs perfect for sauces and sandwiches.', N'https://www.transparentpng.com/thumb/tomato/PSJAVT-tomato-picture.png'),
(0, N'Cucumbers', N'Hydrating crunch in every bite—ideal for snacking.', N'https://freepngimg.com/save/12270-cucumber-png-file/680x404'),
(0, N'Eggplants', N'Elegant aubergines for rich, flavorful recipes.', N'https://www.pngall.com/wp-content/uploads/2016/04/Eggplant-Free-Download-PNG.png'),
(1, N'Apples', N'Crisp, juicy apples—nature’s perfect snack for a burst of sweetness.', N'https://www.freeiconspng.com/uploads/download-apples-png-image-red-apple-fruit-10.png'),
(1, N'Bananas', N'Golden bananas, creamy and energizing—a portable delight.', N'https://clipart-library.com/image_gallery2/Banana.png'),
(1, N'Oranges', N'Zesty oranges, a burst of sunshine in every slice.', N'https://static.vecteezy.com/system/resources/previews/022/787/263/non_2x/oranges-fruit-with-leaves-on-a-transparent-background-generative-ai-png.png'),
(1, N'Mangoes', N'Exotic mangoes—sweet, fragrant, and irresistible.', N'https://qwestore.com/png_images_min/93/Yellow-mango-Sliced-mango-PNG-6419.png'),
(1, N'Peaches', N'Juicy peaches, the taste of summer captured in each bite.', N'https://www.freeiconspng.com/uploads/peaches-slices-png-9.png'),
(1, N'Plums', N'Plums, velvety and rich—a royal treat.', N'https://pngimg.com/d/plum_PNG8674.png'),
(1, N'Strawberries', N'Ruby-red strawberries—sweetness in every heart-shaped gem.', N'https://www.freepnglogos.com/uploads/strawberry-png/strawberry-spooner-farms-strawberries-18.png'),
(1, N'Blueberries', N'Tiny blue dynamos—packed with antioxidants and flavor.', N'https://www.freepnglogos.com/uploads/blueberries-png/blueberries-blueberry-with-leaf-png-image-purepng-transparent-png-image-library-11.png'),
(1, N'Watermelons', N'Melons, juicy and refreshing—summer’s cool embrace.', N'https://clipart-library.com/image_gallery2/Watermelon-Free-Download-PNG.png'),
(2, N'Organic Adzuki Beans', N'Tiny, vibrant adzuki beans—packed with protein and perfect for hearty stews.', N'https://edisongrainery.com/cdn/shop/products/BAZ_Adzuki-Beans.png?v=1664838197&width=1080'),
(2, N'Garbanzo Beans (Chickpeas)', N'Creamy chickpeas—your versatile pantry staple for hummus, curries, and salads.', N'https://static.vecteezy.com/system/resources/previews/027/857/459/non_2x/chickpeas-in-the-bowl-on-isolated-background-healthy-and-vegan-concept-created-with-generative-ai-technology-png.png'),
(2, N'Mung Beans', N'Nutrient-rich mung beans—sprout them for crunchy goodness or cook them into savory dishes.', N'https://www.pngall.com/wp-content/uploads/4/Mung-Bean-PNG-File.png'),
(2, N'French Green Lentils', N'Elegant green lentils—ideal for soups, salads, and gourmet meals.', N'https://cdn11.bigcommerce.com/s-u58o2ll5bw/images/stencil/1280x1280/products/160/454/French-Lentils__54282.1550004810.png?c=2'),
(2, N'Black Beans', N'Bold black beans—add depth to your burritos, bowls, and chili.', N'https://png.pngtree.com/png-clipart/20231110/original/pngtree-black-beans-food-photo-png-image_13528950.png'),
(3, N'Raw Cashews', N'Bold black beans—add depth to your burritos, bowls, and chili.', N'https://pngimg.com/d/cashew_PNG11.png'),
(3, N'English Walnuts (Raw, No Shell)', N'Walnuts with a rich, earthy flavor—ideal for baking and salads.', N'https://i.etsystatic.com/16341877/r/il/701efc/3243089619/il_570xN.3243089619_rjdn.jpg'),
(3, N'Raw Pistachios (No Shell)', N'Vibrant green pistachios—add color and taste to your favorite treats.', N'https://www.livingnutz.com/cdn/shop/products/1LBSproutedPistachios-Front_1200x.png?v=1643398952'),
(3, N'Organic Pepitas (No Shell Pumpkin Seeds)', N'Nutrient-packed pepitas—sprinkle on salads or enjoy as a wholesome snack.', N'https://images.sellbrite.com/production/32906/PUMP-O-2/21f9299f-fb69-558a-bb9a-33ea59aa5195.jpg'),
(4, N'Allspice', N'Bold and aromatic, allspice adds warmth to pies, cakes, and marinades.', N'https://www.nedspice.com/app/uploads/2020/06/all-spice-Pimento-whole.png'),
(4, N'Anise', N'Licorice-scented anise—perfect for baked goods, sauces, and sausages.', N'https://static.vecteezy.com/system/resources/previews/035/459/945/non_2x/ai-generated-star-anise-spice-fruits-and-seeds-free-png.png'),
(4, N'Annatto', N'Brick-red annatto—natural dye for cheeses, oils, and rice, with a zesty twist.', N'https://www.southernstylespices.com/wp-content/uploads/2019/06/Anatto-Seed-bowl.png'),
(4, N'Black Peppercorns', N'Classic black peppercorns—grind for a burst of spicy flavor in every dish.', N'https://greensaffron.com/wp-content/uploads/2020/04/Black-Pepper-Bowl-min.png'),
(4, N'Cayenne Pepper', N'Fiery cayenne—add a kick to soups, stews, and your favorite recipes.', N'https://png.pngtree.com/png-clipart/20220911/original/pngtree-cayenne-peppers-png-image_8533681.png'),
(5, N'Shiitake Mushrooms', N'Exquisite shiitakes—common in Asian cuisine, with thin stems and an earthy, versatile taste.', N'https://static.vecteezy.com/system/resources/previews/023/296/545/original/watercolor-shiitake-mushroom-png.png'),
(5, N'Porcini Mushrooms', N'Rich and meaty porcinis—ideal for risottos and adding depth to sauces.', N'https://static.vecteezy.com/system/resources/previews/009/661/259/original/boletus-or-porcini-mushroom-watercolor-illustration-png.png'),
(5, N'Chanterelle Mushrooms', N'Golden-hued chanterelles—add a pop of color and delicate flavor to pasta dishes.', N'https://png.pngtree.com/png-clipart/20231026/original/pngtree-chanterelles-on-white-background-realistic-photo-png-image_13430496.png'),
(5, N'Enoki Mushrooms', N'Delicate enokis—perfect for soups, salads, and stir-fries.', N'https://static.vecteezy.com/system/resources/previews/023/296/548/original/watercolor-enoki-mushroom-png.png');

INSERT INTO product_type (name) VALUES 
(N'item'),
(N'kg'),
(N'container'),
(N'bottle');

INSERT INTO variation (product_type_id, value, price_percentage) VALUES 
(0, 5, 0.95),
(0, 10, 0.87),
(1, 5, 0.95),
(1, 10, 0.87),
(2, 1, 0.88),
(2, 3, 0.8),
(3, 3, 0.9),
(3, 7, 0.82),
(0, 0, 1),
(1, 0, 1),
(2, 0, 1),
(3, 0, 1);

INSERT INTO product_item (product_id, qty_in_stock, price, product_type_id) VALUES
(0, 500, 0.99, 1),
(1, 500, 0.50, 1),
(2, 500, 1.20, 1),
(3, 500, 1.50, 1),
(4, 500, 1.00, 1),
(5, 500, 2.50, 1),
(6, 200, 5.69, 1),
(7, 200, 6.99, 1),
(8, 200, 4.20, 1),
(9, 200, 3.49, 1),
(10, 100, 6.49, 1),
(11, 100, 2.00, 1),
(12, 7000, 1.49, 1),
(13, 7000, 7.00, 1),
(14, 100, 12.00, 1),
(15, 3000, 0.49, 3),
(16, 3000, 0.50, 3),
(17, 3000, 0.69, 3),
(18, 3000, 0.70, 3),
(19, 3000, 0.49, 3),
(20, 3000, 0.80, 3),
(21, 3000, 0.90, 3),
(22, 3000, 0.49, 3),
(23, 5000, 0.49, 3),
(24, 5000, 0.80, 3),
(25, 5000, 0.70, 3),
(26, 3000, 0.80, 3),
(27, 3000, 0.60, 3),
(28, 3000, 0.70, 1),
(29, 1000, 21.90, 1),
(30, 1000, 29.00, 1),
(31, 1000, 24.00, 1),
(32, 1000, 5.99, 1);

INSERT INTO product_configuration (product_item_id, variation_id) VALUES 
(0, 2),
(1, 2),
(2, 2),
(3, 2),
(4, 2),
(5, 2),
(6, 0),
(7, 2),
(8, 0),
(9, 0),
(10, 2),
(11, 0),
(12, 2),
(13, 2),
(14, 0),
(15, 2),
(16, 2),
(17, 2),
(18, 2),
(19, 2),
(20, 2),
(21, 2),
(22, 2),
(23, 2),
(24, 6),
(25, 6),
(26, 6),
(27, 6),
(28, 2),
(29, 2),
(30, 2),
(31, 2),
(32, 2),
(0, 3),
(1, 3),
(2, 3),
(3, 3),
(4, 3),
(5, 3),
(6, 1),
(7, 3),
(8, 1),
(9, 1),
(10, 3),
(11, 1),
(12, 3),
(13, 3),
(14, 1),
(15, 3),
(16, 3),
(17, 3),
(18, 3),
(19, 3),
(20, 3),
(21, 3),
(22, 3),
(23, 3),
(24, 7),
(25, 7),
(26, 7),
(27, 7),
(28, 3),
(29, 3),
(30, 3),
(31, 3),
(32, 3),
(0, 10),
(1, 10),
(2, 10),
(3, 10),
(4, 10),
(5, 10),
(6, 8),
(7, 10),
(8, 8),
(9, 8),
(10, 10),
(11, 8),
(12, 10),
(13, 10),
(14, 8),
(15, 10),
(16, 10),
(17, 10),
(18, 10),
(19, 10),
(20, 10),
(21, 10),
(22, 10),
(23, 10),
(24, 11),
(25, 11),
(26, 11),
(27, 11),
(28, 10),
(29, 10),
(30, 10),
(31, 10),
(32, 10);

INSERT INTO site_user (username, password, email, phone_number) VALUES
('KhaiTran', 'khai01', 'khaitran001@gmail.com', '0560544591'),
('DatVu', 'dat02', 'datvu002@gmail.com', '0990438966'),
('DungNguyen', 'dung03', 'dungnguyen003@gmail.com', '0858331784'),
('MinhChu', 'minh04', 'minhchu004@gmail.com', '0795358477'),
('VinhNguyen', 'vinh05', 'vinhnguyen005@gmail.com', '0563348312'),
('PhongDoan', 'khai06', 'phongdoan006@gmail.com', '0391845237');

INSERT INTO user_address VALUES
(0, '70 Thanh Long Street, Hai Chau District, Da Nang', 1),
(1, '633 Hoang Van Thu St., Ward 4, Tan Binh District, Ho Chi Minh City', 1),
(2, '65 Trung Nhi Street, Le Loi Ward, Hung Yen', 1),
(3, '3 An Duong Street, Lane 16, Tay Ho District, Ha Noi', 1),
(4, '1 Dong Da Street, Dong Da Ward, Binh Dinh', 1),
(5, '162 Le Thanh Tong, Bach Dang Ward, Quang Ninh', 1);

INSERT INTO provider (name) VALUES
('BIDV'),
('VietcomBank'),
('AgriBank'),
('ViettinBank'),
('SacomBank'),
('MBBank');

INSERT INTO user_payment (user_id, provider_id, account_number, expiracy_date) VALUES
(0, 0, '114910992912', '2025/08/23'),
(1, 1, '803860933189', '2025/02/09'),
(2, 2, '761827003144', '2026/05/20'),
(3, 3, '544758720612', '2024/08/02'),
(4, 4, '547948847065', '2024/03/20'),
(5, 5, '414622870486', '2027/06/15');


INSERT INTO shipping_type (type, price) VALUES
('Fast', 20.00),
('Normal', 10.00);

INSERT INTO status (name) VALUES
(N'Ordered'),
(N'In Transit'),
(N'Delivered'),
(N'Cancelled');

INSERT INTO shopping_cart (user_id) VALUES
(0),
(1),
(2),
(3),
(4),
(5);

INSERT INTO shopping_cart_item (cart_id, product_config_id, quantity) VALUES
(0, 45, 5),
(1, 56, 7),
(2, 12, 4),
(3, 33, 2),
(4, 61, 1),
(5, 02, 3);

INSERT INTO [order] (user_id, date, payment_id, address, shipping_type_id, total_price, status_id) VALUES
(0,'2023/06/23', 0, '70 Thanh Long Street, Hai Chau District, Da Nang', 1, 32.50, 0),
(1, '2023/07/15', 1, '633 Hoang Van Thu St., Ward 4, Tan Binh District, Ho Chi Minh City', 0, 50.45, 2),
(2, '2023/07/27', 2, '65 Trung Nhi Street, Le Loi Ward, Hung Yen', 0, 20.50, 3),
(3, '2023/09/13', 3, '3 An Duong Street, Lane 16, Tay Ho District, Ha Noi', 1, 32.27, 1),
(4, '2023/11/23', 4, '1 Dong Da Street, Dong Da Ward, Binh Dinh', 0, 45.36, 2),
(5, '2023/12/08', 5,'162 Le Thanh Tong, Bach Dang Ward, Quang Ninh', 1, 10.50, 3);

INSERT INTO order_line (product_config_id, order_id, quantity, calculated_price) VALUES
(67, 0, 2, 33.50),
(80, 1, 3, 51.67),
(52, 2, 1, 23.56),
(12, 3, 7, 34.76),
(03, 4, 2, 47.86),
(65, 5, 0, 13.45);

-----DATETIME - format: YYYY-MM-DD HH:MI:SS-----**

-----------------------------------------------------------------------**
-----------------------------------------------------------------------**
-----------------------------------------------------------------------**

-----------------Code dùng để hiển thị toàn bộ bảng--------------------**

DECLARE @sqlText VARCHAR(MAX)
SET @sqlText = ''
SELECT @sqlText = @sqlText + 'select '''+QUOTENAME(name)+'''; SELECT * FROM ' + QUOTENAME(name) + CHAR(13) FROM sys.tables
EXEC(@sqlText)
