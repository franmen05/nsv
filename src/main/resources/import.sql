    /* Populate tables */
-- INSERT INTO customers(id,comment,create_date,email,last_name,last_update_date,name) VALUES(1,'comentario', '2017-08-28', 'profesor@bolsadeideas.com','Andres Apellido', '2017-08-28', 'Andres');
INSERT INTO customers (id, address, comment, create_date, email, last_name, last_update_date, name, passport, phone, rnc) VALUES(1, 'Rosa Roja numero 232423', 'comentario', '2017-08-28 00:00:00', 'profesor@bolsadeideas.com', 'Andres Apellido', '2017-08-28 00:00:00', 'Andres', '222324242342', '809-34-2-421', '2312' );
INSERT INTO customers (id, address, comment, create_date, email, last_name, last_update_date, name, passport, phone, rnc) VALUES(2, 'Rosa Roja numero 232423', 'comentario', '2017-08-28 00:00:00', 'profesor@bolsadeideas.com', 'De Los Santos', '2017-08-28 00:00:00', 'Guillermo', '222324242342', '809-34-2-421', '2312' );
/* Populate tabla productos */
INSERT INTO products (name, price, create_date) VALUES('Flete', 250, NOW());
INSERT INTO products (name, price, create_date) VALUES('Apple iPod shuffle', 1499990, NOW());
INSERT INTO products (name, price, create_date) VALUES('Sony Notebook Z110', 37990, NOW());
INSERT INTO products (name, price, create_date) VALUES('Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO products (name, price, create_date) VALUES('Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO products (name, price, create_date) VALUES('Mica Comoda 5 Cajones', 299990, NOW());
INSERT INTO products (name, price, create_date) VALUES('Compra por Amazon', 123490, NOW());
INSERT INTO products (name, price, create_date) VALUES('Compra por Ebay', 123490, NOW());
INSERT INTO products (name, price, create_date) VALUES('Compra en China', 123490, NOW());


INSERT INTO invoices (description, comment, customer_id,currency_id,  create_date) VALUES('Factura Bicicleta', 'Alguna nota importante!', 1,1, NOW());
INSERT INTO invoice_items (quantity,cost, invoice_id, product_id,description) VALUES(3,139, 1, 1,'item de apple');
INSERT INTO invoice_items (quantity,cost,discount, invoice_id, product_id,description) VALUES(3,250,0.1, 1, 2,'item de apple');


INSERT INTO additional_expenses (name, create_date) VALUES('Gestion',  NOW());
INSERT INTO additional_expenses (name, create_date) VALUES('Transporte',  NOW());
INSERT INTO additional_expenses (name, create_date) VALUES('Abogado',  NOW());

INSERT INTO taxes_group (name, create_date) VALUES('Generales',  NOW());
INSERT INTO taxes_group (name, create_date) VALUES('Adiciones',  NOW());



INSERT INTO taxes (name, value, create_date,tax_group_id) VALUES('ITBIS', 0.18, NOW(),1);
INSERT INTO taxes (name, value, create_date,tax_group_id) VALUES('Aduanas', 0.2, NOW(),1);
INSERT INTO taxes (name, value, create_date,tax_group_id) VALUES('Impuestos diversos', 0.01, NOW(),2 );

INSERT INTO payments_type (name, create_date) VALUES('Efectivo', NOW());
INSERT INTO payments_type (name, create_date) VALUES('Tarjeta de Credito', NOW());
INSERT INTO payments_type (name, create_date) VALUES('Tarjeta de Debito', NOW());





INSERT INTO ncf_series (id,description, create_date) VALUES('B','Dolar US', NOW());
INSERT INTO ncf_series (id,description, create_date) VALUES('B1','Dolar US', NOW());

INSERT INTO ncf_types (id,description, create_date) VALUES('01','Dolar US', NOW());
INSERT INTO ncf_types (id,description, create_date) VALUES('02','Dolar US', NOW());
INSERT INTO ncf_types (id,description, create_date) VALUES('03','Dolar US', NOW());


INSERT INTO ncf ( create_date, last_update_date, sequence, invoice_id, serie_id, type_id) VALUES( '2018-11-06 01:16:24', NOW(), '00000002', 1, 'B', '01');
INSERT INTO ncf ( create_date, last_update_date, sequence,  serie_id, type_id) VALUES	( '2018-11-06 01:16:24', NOW(), '00000003',  'B', '01');



-- Insert currencies records
INSERT INTO currencies (name, code, symbol, create_date) VALUES ('Pesos Dominicanos', 'DOP ', 'RD$',NOW());
INSERT INTO currencies (name, code, symbol, create_date) VALUES ('Dollars', 'USD', '$', NOW());
INSERT INTO currencies (name, code, symbol, create_date) VALUES ('Euro', 'EUR', '€', NOW());
INSERT INTO currencies (name, code, symbol, create_date) VALUES ('Yuan Renminbi', 'CNY', '¥', NOW());

--
-- INSERT INTO currencies (name, code, symbol) VALUES ('Leke', 'ALL', 'Lek');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Afghanis', 'AFN', '؋');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Pesos', 'ARS', '$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Guilders', 'AWG', 'ƒ');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Dollars', 'AUD', '$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('New Manats', 'AZN', 'ман');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Dollars', 'BSD', '$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Dollars', 'BBD', '$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Rubles', 'BYR', 'p.');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Dollars', 'BZD', 'BZ$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Dollars', 'BMD', '$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Bolivianos', 'BOB', '$b');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Convertible Marka', 'BAM', 'KM');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Pula', 'BWP', 'P');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Leva', 'BGN', 'лв');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Reais', 'BRL', 'R$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Pounds', 'GBP', '£');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Dollars', 'BND', '$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Riels', 'KHR', '៛');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Dollars', 'CAD', '$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Dollars', 'KYD', '$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Pesos', 'CLP', '$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Pesos', 'COP', '$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Colón', 'CRC', '₡');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Kuna', 'HRK', 'kn');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Pesos', 'CUP', '₱');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Koruny', 'CZK', 'Kč');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Kroner', 'DKK', 'kr');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Dollars', 'XCD', '$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Pounds', 'EGP', '£');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Colones', 'SVC', '$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Pounds', 'FKP', '£');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Dollars', 'FJD', '$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Cedis', 'GHC', '¢');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Pounds', 'GIP', '£');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Quetzales', 'GTQ', 'Q');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Pounds', 'GGP', '£');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Dollars', 'GYD', '$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Lempiras', 'HNL', 'L');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Dollars', 'HKD', '$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Forint', 'HUF', 'Ft');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Kronur', 'ISK', 'kr');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Rupees', 'INR', 'Rp');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Rupiahs', 'IDR', 'Rp');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Rials', 'IRR', '﷼');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Pounds', 'IMP', '£');
-- INSERT INTO currencies (name, code, symbol) VALUES ('New Shekels', 'ILS', '₪');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Dollars', 'JMD', 'J$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Yen', 'JPY', '¥');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Pounds', 'JEP', '£');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Tenge', 'KZT', 'лв');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Won', 'KPW', '₩');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Won', 'KRW', '₩');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Soms', 'KGS', 'лв');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Kips', 'LAK', '₭');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Lati', 'LVL', 'Ls');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Pounds', 'LBP', '£');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Dollars', 'LRD', '$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Switzerland Francs', 'CHF', 'CHF');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Litai', 'LTL', 'Lt');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Denars', 'MKD', 'ден');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Ringgits', 'MYR', 'RM');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Rupees', 'MUR', '₨');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Pesos', 'MXN', '$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Tugriks', 'MNT', '₮');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Meticais', 'MZN', 'MT');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Dollars', 'NAD', '$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Rupees', 'NPR', '₨');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Guilders', 'ANG', 'ƒ');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Dollars', 'NZD', '$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Cordobas', 'NIO', 'C$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Nairas', 'NGN', '₦');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Krone', 'NOK', 'kr');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Rials', 'OMR', '﷼');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Rupees', 'PKR', '₨');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Balboa', 'PAB', 'B/.');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Guarani', 'PYG', 'Gs');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Nuevos Soles', 'PEN', 'S/.');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Pesos', 'PHP', 'Php');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Zlotych', 'PLN', 'zł');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Rials', 'QAR', '﷼');
-- INSERT INTO currencies (name, code, symbol) VALUES ('New Lei', 'RON', 'lei');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Rubles', 'RUB', 'руб');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Pounds', 'SHP', '£');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Riyals', 'SAR', '﷼');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Dinars', 'RSD', 'Дин.');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Rupees', 'SCR', '₨');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Dollars', 'SGD', '$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Dollars', 'SBD', '$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Shillings', 'SOS', 'S');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Rand', 'ZAR', 'R');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Rupees', 'LKR', '₨');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Kronor', 'SEK', 'kr');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Dollars', 'SRD', '$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Pounds', 'SYP', '£');
-- INSERT INTO currencies (name, code, symbol) VALUES ('New Dollars', 'TWD', 'NT$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Baht', 'THB', '฿');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Dollars', 'TTD', 'TT$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Lira', 'TRY', 'TL');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Liras', 'TRL', '£');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Dollars', 'TVD', '$');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Hryvnia', 'UAH', '₴');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Pesos', 'UYU', '$U');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Sums', 'UZS', 'лв');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Bolivares Fuertes', 'VEF', 'Bs');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Dong', 'VND', '₫');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Rials', 'YER', '﷼');
-- INSERT INTO currencies (name, code, symbol) VALUES ('Zimbabwe Dollars', 'ZWD', 'Z$');


INSERT INTO companies ( rnc, address, comment, create_date, email, last_update_date, name, status, currency_id) VALUES ( '383423-32101-01', 'rosa roja #25 / El Rosan', '', '2018-12-08 18:18:31', 'you_know08@hotmail.com', NULL, 'Andres S.A.', 'ACTIVE', 1);
INSERT INTO companies ( rnc, address, comment, create_date, email, last_update_date, name, status, currency_id) VALUES ( '383423-32101-01', 'rosa roja #25 / El Rosan', '', '2018-12-08 18:18:31', 'you_know08@hotmail.com', NULL, 'Juan S.A.', 'ACTIVE', 1);

--     12345
INSERT INTO users (username, password, enabled) VALUES('andres', '$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG', 1);
INSERT INTO users (username, password, enabled) VALUES('admin', '$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS', 1);

INSERT INTO users_roles (user_id, rol) VALUES(1, 'ROLE_USER');
INSERT INTO users_roles (user_id, rol) VALUES(2, 'ROLE_USER');
INSERT INTO users_roles (user_id, rol) VALUES(2, 'ROLE_ADMIN');


INSERT INTO  roles ( authority) VALUES( 'ROLE_ADMIN');
INSERT INTO  roles ( authority) VALUES( 'ROLE_USER');
INSERT INTO  roles ( authority) VALUES( 'ROLE_SALER');
INSERT INTO  roles ( authority) VALUES( 'ROLE_OPERATIONS');
