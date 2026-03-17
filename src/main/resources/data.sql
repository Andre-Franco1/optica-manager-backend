INSERT INTO units (CODE, NAME, LOGO_PATH, PHONE_NUMBER, STREET, NUMBER) VALUES ('MDS', 'Ótica Morada do Sol', 'src/main/resources/static/mds.jpeg', '(11) 4712-8286', 'Avenida Tiradentes', '196');
INSERT INTO units (CODE, NAME, LOGO_PATH, PHONE_NUMBER, STREET, NUMBER) VALUES ('ALLE', 'Ótica Alle', 'src/main/resources/static/alle.jpeg', '(11) 4712-1947', 'Avenida Tiradentes', '120');

INSERT INTO clients (CPF, NAME, PHONE, UNIT_ID) VALUES ('911.037.110-91','Ana Silva', '11 9453345435', 1);
INSERT INTO clients (CPF, NAME, PHONE, UNIT_ID) VALUES ('502.159.000-65', 'Fernando Nunes', '11 923830384', 1);
INSERT INTO clients (CPF, NAME, PHONE, UNIT_ID) VALUES ('334.143.460-79', 'Victor Gomes', '11 903059371', 1);

INSERT INTO ophthalmologists (NAME) VALUES ('Dr. Carlos da Silva');
INSERT INTO ophthalmologists (NAME) VALUES ('Dr. José Ferreira');
INSERT INTO ophthalmologists (NAME) VALUES ('Dr. Marcos Pascarella');

INSERT INTO users (NAME, EMAIL, PASSWORD, ACTIVE, UNIT_ID) VALUES ('André Franco', 'andrefranco@email.com', '123456', TRUE, 1);

INSERT INTO users (NAME, EMAIL, PASSWORD, ACTIVE, UNIT_ID) VALUES ('Usuário Teste', 'a@a', '$2a$10$Asl7K3I4jAjS2CmBTxpmlu82hG46GI/S9B2bGWSvFb5JHiGK5dpF6', TRUE, 1);
INSERT INTO users (NAME, EMAIL, PASSWORD, ACTIVE, UNIT_ID) VALUES ('Usuário Teste2', 'b@b', '$2a$10$Asl7K3I4jAjS2CmBTxpmlu82hG46GI/S9B2bGWSvFb5JHiGK5dpF6', TRUE, 2);

INSERT INTO roles (ROLE) VALUES ('ROLE_ADMIN');
INSERT INTO roles (ROLE) VALUES ('ROLE_OPERATOR');

INSERT INTO user_roles (ROLE_ID, USER_ID) VALUES (1, 1);
INSERT INTO user_roles (ROLE_ID, USER_ID) VALUES (2, 1);

INSERT INTO user_roles (ROLE_ID, USER_ID) VALUES (1, 2);
INSERT INTO user_roles (ROLE_ID, USER_ID) VALUES (2, 2);

INSERT INTO user_roles (ROLE_ID, USER_ID) VALUES (1, 3);
INSERT INTO user_roles (ROLE_ID, USER_ID) VALUES (2, 3);

INSERT INTO products (CODE, NAME, UNIT_ID) VALUES ('ARM001', 'Armação receituário Keen', 1);
INSERT INTO products (CODE, NAME, UNIT_ID) VALUES ('LN003', 'Zeiss 1.6 Monofocal', 1);
INSERT INTO products (CODE, NAME, UNIT_ID) VALUES ('ARM002', 'Armação solar Keen', 1);


INSERT INTO frames (STOCK_QUANTITY, BRAND , TYPE, PRODUCT_ID) VALUES (150, 'KEEN', 'PRESCRIPTION', 1);
INSERT INTO frames (STOCK_QUANTITY, BRAND , TYPE, PRODUCT_ID) VALUES (150, 'STYLUS', 'SUNGLASS', 3);


INSERT INTO lenses (BRAND, INDEX, MATERIAL, TYPE, PRODUCT_ID) VALUES ('ZEISS', 'I160', 'HIGH_INDEX', 'MONOFOCAL', 2);

INSERT INTO lens_treatments (lens_id, treatments) VALUES (2, 'ANTI_REFLECTIVE');
INSERT INTO lens_treatments (lens_id, treatments) VALUES (2, 'UV');

/*
INSERT INTO sales (ISSUE_DATE, DELIVERY_DATE, ESTIMATED_DELIVERY_DATE, TOTAL_AMOUNT, PAYMENT_METHOD, INSTALLMENTS, COMMENTS, SALE_STATUS, CLIENT_ID, PRESCRIPTION_ID, USER_ID, CARD_BRAND, UNIT_ID) 
VALUES ('2025-06-26', '2025-07-02', '2025-06-26', 389.90, 'CASH', NULL, 'this is a comment of the sale', 'COMPLETED', 2, NULL, 1, NULL, 1);

INSERT INTO sales (ISSUE_DATE, DELIVERY_DATE, ESTIMATED_DELIVERY_DATE, TOTAL_AMOUNT, PAYMENT_METHOD, INSTALLMENTS, COMMENTS, SALE_STATUS, CLIENT_ID, PRESCRIPTION_ID, USER_ID, CARD_BRAND, UNIT_ID) 
VALUES ('2025-06-26', NULL, '2025-06-26', 389.90, 'CASH', NULL, 'this is a comment of the sale', 'PENDING', 2, NULL, 1, NULL, 1);

INSERT INTO sale_items (PRODUCT_ID, SALE_ID, PRICE, QUANTITY) VALUES (1, 1 , 150, 1);
INSERT INTO sale_items (PRODUCT_ID, SALE_ID, PRICE, QUANTITY) VALUES (2, 1, 420, 1);

INSERT INTO sale_items (PRODUCT_ID, SALE_ID, PRICE, QUANTITY) VALUES (1, 2 , 150, 1);
INSERT INTO sale_items (PRODUCT_ID, SALE_ID, PRICE, QUANTITY) VALUES (2, 2, 420, 1);
*/

-- SALES
INSERT INTO sales 
(ISSUE_DATE, ESTIMATED_DELIVERY_DATE, DELIVERY_DATE, SUBTOTAL, DISCOUNT_PERCENTAGE, TOTAL_AMOUNT, COMMENTS, DELIVERY_STATUS, CLIENT_ID, PRESCRIPTION_ID, USER_ID, UNIT_ID)
VALUES 
('2025-06-26 10:00:00', '2025-07-02', '2025-07-02', 570.00, 0, 570.00, 'this is a comment of the sale', 'DELIVERED', 2, NULL, 1, 1);

INSERT INTO sales 
(ISSUE_DATE, ESTIMATED_DELIVERY_DATE, DELIVERY_DATE, SUBTOTAL, DISCOUNT_PERCENTAGE, TOTAL_AMOUNT, COMMENTS, DELIVERY_STATUS, CLIENT_ID, PRESCRIPTION_ID, USER_ID, UNIT_ID)
VALUES 
('2025-06-26 11:00:00', '2025-07-02', NULL, 570.00, 0, 570.00, 'this is a comment of the sale', 'PENDING', 2, NULL, 1, 1);


-- SALE ITEMS
INSERT INTO sale_items (PRODUCT_ID, SALE_ID, UNIT_PRICE, QUANTITY, SUBTOTAL) VALUES (1, 1, 150.00, 1, 150.00);
INSERT INTO sale_items (PRODUCT_ID, SALE_ID, UNIT_PRICE, QUANTITY, SUBTOTAL) VALUES (2, 1, 420.00, 1, 420.00);

INSERT INTO sale_items (PRODUCT_ID, SALE_ID, UNIT_PRICE, QUANTITY, SUBTOTAL) VALUES (1, 2, 150.00, 1, 150.00);
INSERT INTO sale_items (PRODUCT_ID, SALE_ID, UNIT_PRICE, QUANTITY, SUBTOTAL) VALUES (2, 2, 420.00, 1, 420.00);


-- PAYMENTS
INSERT INTO payments 
(AMOUNT, INSTALLMENTS, PAYMENT_DATE, PAYMENT_METHOD, CARD_BRAND, STATUS, SALE_ID)
VALUES 
(570.00, NULL, '2025-06-26', 'CASH', NULL, 'PAID', 1);

INSERT INTO payments 
(AMOUNT, INSTALLMENTS, PAYMENT_DATE, PAYMENT_METHOD, CARD_BRAND, STATUS, SALE_ID)
VALUES 
(570.00, 6, '2025-06-26', 'CREDIT_CARD', 'VISA', 'PENDING', 2);


-- PAYMENT INSTALLMENTS (para o pagamento parcelado)
INSERT INTO payment_installments (INSTALLMENT_NUMBER, DUE_DATE, AMOUNT, PAYMENT_ID)
VALUES (1, '2025-07-26', 95.00, 2);

INSERT INTO payment_installments (INSTALLMENT_NUMBER, DUE_DATE, AMOUNT, PAYMENT_ID)
VALUES (2, '2025-08-26', 95.00, 2);

INSERT INTO payment_installments (INSTALLMENT_NUMBER, DUE_DATE, AMOUNT, PAYMENT_ID)
VALUES (3, '2025-09-26', 95.00, 2);

INSERT INTO payment_installments (INSTALLMENT_NUMBER, DUE_DATE, AMOUNT, PAYMENT_ID)
VALUES (4, '2025-10-26', 95.00, 2);

INSERT INTO payment_installments (INSTALLMENT_NUMBER, DUE_DATE, AMOUNT, PAYMENT_ID)
VALUES (5, '2025-11-26', 95.00, 2);

INSERT INTO payment_installments (INSTALLMENT_NUMBER, DUE_DATE, AMOUNT, PAYMENT_ID)
VALUES (6, '2025-12-26', 95.00, 2);

INSERT INTO prescriptions (
    date,
    distance_od_spherical,
    distance_od_cylindrical,
    distance_od_axis,
    distance_od_dnp,
    distance_od_addition,

    distance_os_spherical,
    distance_os_cylindrical,
    distance_os_axis,
    distance_os_dnp,
    distance_os_addition,

    distance_dp,

    near_od_spherical,
    near_od_cylindrical,
    near_od_axis,
    near_od_dnp,
    near_od_height,

    near_os_spherical,
    near_os_cylindrical,
    near_os_axis,
    near_os_dnp,
    near_os_height,

    near_dp,

    notes,
    client_id,
    ophthalmologist_id,
    unit_id
) VALUES (
    '2026-01-02',
    -1.25, -0.50, 90, 31.5, 1.75,
    -0.75, -0.25, 80, 32.0, 1.75,
    62.0,
    -0.50, -0.25, 85, 31.5, 18.0,
    -0.25, -0.25, 95, 32.0, 18.0,
    62.0,
    'Paciente relata leve fotofobia.',
    1,
    3,
    1
);

INSERT INTO prescriptions (
    date,
    distance_od_spherical,
    distance_od_cylindrical,
    distance_od_axis,
    distance_od_dnp,
    distance_od_addition,

    distance_os_spherical,
    distance_os_cylindrical,
    distance_os_axis,
    distance_os_dnp,
    distance_os_addition,

    distance_dp,

    near_od_spherical,
    near_od_cylindrical,
    near_od_axis,
    near_od_dnp,
    near_od_height,

    near_os_spherical,
    near_os_cylindrical,
    near_os_axis,
    near_os_dnp,
    near_os_height,

    near_dp,

    notes,
    client_id,
    ophthalmologist_id,
    unit_id
) VALUES (
    '2026-01-02',
    -1.25, -0.50, 90, 31.5, 1.75,
    -0.75, -0.25, 80, 32.0, 1.75,
    62.0,
    -0.50, -0.25, 85, 31.5, 18.0,
    -0.25, -0.25, 95, 32.0, 18.0,
    62.0,
    'Paciente relata leve fotofobia.',
    1,
    3,
    1
);





--UNIT 2--

INSERT INTO clients (CPF, NAME, PHONE, UNIT_ID) VALUES ('612.355.280-57', 'Maria de Fátima', '11 903049371', 2);

INSERT INTO products (CODE, NAME, UNIT_ID) VALUES ('ARM001', 'Armação receituário Keen', 2);
INSERT INTO products (CODE, NAME, UNIT_ID) VALUES ('LN003', 'Zeiss 1.67 Monofocal', 2);


INSERT INTO frames (STOCK_QUANTITY, BRAND , TYPE, PRODUCT_ID) VALUES (80, 'OAKLEY', 'PRESCRIPTION', 4);

INSERT INTO lenses (BRAND, INDEX, MATERIAL, TYPE, PRODUCT_ID) VALUES ('ZEISS', 'I167', 'HIGH_INDEX', 'MONOFOCAL', 5);

INSERT INTO lens_treatments (lens_id, treatments) VALUES (5, 'ANTI_REFLECTIVE');
INSERT INTO lens_treatments (lens_id, treatments) VALUES (5, 'UV');

-- SALE
INSERT INTO sales
(ISSUE_DATE, ESTIMATED_DELIVERY_DATE, DELIVERY_DATE, SUBTOTAL, DISCOUNT_PERCENTAGE, TOTAL_AMOUNT, COMMENTS, DELIVERY_STATUS, CLIENT_ID, PRESCRIPTION_ID, USER_ID, UNIT_ID)
VALUES
('2025-06-26 12:00:00', '2025-06-26', NULL, 570.00, 0, 570.00, 'this is a comment of the sale', 'PENDING', 4, NULL, 2, 2);


-- SALE ITEMS
INSERT INTO sale_items (PRODUCT_ID, SALE_ID, UNIT_PRICE, QUANTITY, SUBTOTAL)
VALUES (4, 3, 150.00, 1, 150.00);

INSERT INTO sale_items (PRODUCT_ID, SALE_ID, UNIT_PRICE, QUANTITY, SUBTOTAL)
VALUES (5, 3, 420.00, 1, 420.00);


-- PAYMENT (CASH)
INSERT INTO payments
(AMOUNT, INSTALLMENTS, PAYMENT_DATE, PAYMENT_METHOD, CARD_BRAND, STATUS, SALE_ID)
VALUES
(570.00, NULL, '2025-06-26', 'CASH', NULL, 'PENDING', 3);