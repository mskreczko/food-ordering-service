insert into roles(role_id, name) values(1, 'ROLE_ADMIN')
insert into roles(role_id, name) values(2, 'ROLE_CUSTOMER')
insert into products(product_id, name, price, description, image_path) values(1, 'Hamburger', 6.0, 'meat, tomato, lettuce, cheese', '/products_images/hamburger-g44891ec8b_640.png');
insert into products(product_id, name, price, description, image_path) values(2, 'Sandwich', 4.0, 'ham, cheese, tomato, egg', '/products_images/sandwich-g2eeca4951_640.png');
insert into products(product_id, name, price, description, image_path) values(3, 'Hot-Dog', 3.0, 'sausage, cheese, mustard, lettuce', '/products_images/sandwich-g887adbce4_640.png');
insert into products(product_id, name, price, description, image_path) values(4, 'Fries', 2.0, 'just fries', '/products_images/french-fries-g4d81fdbcc_640.png');
insert into products(product_id, name, price, description, image_path) values(5, 'Pizza', 5.0, 'mushrooms, olives', '/products_images/pizza-gc2331ba48_640.png');
insert into products(product_id, name, price, description, image_path) values(6, 'Coffee', 3.0, 'cup of coffee', '/products_images/coffee-g774341695_640.png');
insert into products(product_id, name, price, description, image_path) values(7, 'Water', 1.5, 'mineral water', '/products_images/bottle-gc70d32d09_640.png');
insert into products(product_id, name, price, description, image_path) values(8, 'Bacon', 2.0, 'just bacon', '/products_images/bacon-g086e0a2c7_640.png');
insert into products(product_id, name, price, description, image_path) values(9, 'Toast', 1.5, 'some bread', '/products_images/bread-g10fa3b166_640.png');
insert into products(product_id, name, price, description, image_path) values(10, 'Cola', 3.0, 'just cola', '/products_images/lemonade-gc8a431195_640.png');
insert into promo_codes(promocode_id, code, discount) values(1, 'ABC12D', 0.10);
insert into users(user_id, email, enabled, name, password) values(1, 'admin@admin.com', true, 'admin', '$2a$10$RcDDFB5kfBuXzuB35y9S/eGvednpdRKYRHl8i08.u0Utfn18tuXIq')
insert into users_roles(user_id, role_id) values(1, 1)