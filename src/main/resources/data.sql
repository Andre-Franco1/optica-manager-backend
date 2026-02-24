INSERT INTO clients (CPF, NAME, PHONE) VALUES ('911.037.110-91','Ana Silva', '11 9453345435');
INSERT INTO clients (CPF, NAME, PHONE) VALUES ('502.159.000-65', 'Fernando Nunes', '11 923830384');
INSERT INTO clients (CPF, NAME, PHONE) VALUES ('334.143.460-79', 'Victor Gomes', '11 903059371');

INSERT INTO ophthalmologists (NAME) VALUES ('Dr. Carlos da Silva');
INSERT INTO ophthalmologists (NAME) VALUES ('Dr. José Ferreira');
INSERT INTO ophthalmologists (NAME) VALUES ('Dr. Marcos Pascarella');

INSERT INTO users (NAME, EMAIL, PASSWORD, ACTIVE) VALUES ('André Franco', 'andrefranco@email.com', '123456', TRUE);

INSERT INTO users (NAME, EMAIL, PASSWORD, ACTIVE) VALUES ('Usuário Teste', 'a@a', '$2a$10$Asl7K3I4jAjS2CmBTxpmlu82hG46GI/S9B2bGWSvFb5JHiGK5dpF6', TRUE);

INSERT INTO roles (ROLE) VALUES ('ROLE_ADMIN');
INSERT INTO roles (ROLE) VALUES ('ROLE_OPERATOR');

INSERT INTO user_roles (ROLE_ID, USER_ID) VALUES (1, 1);
INSERT INTO user_roles (ROLE_ID, USER_ID) VALUES (2, 1);

INSERT INTO products (CODE, NAME) VALUES ('ARM001', 'Armação receituário Keen');
INSERT INTO products (CODE, NAME) VALUES ('LN003', 'Zeiss 1.6 Monofocal');
INSERT INTO products (CODE, NAME) VALUES ('ARM002', 'Armação solar Keen');


INSERT INTO frames (STOCK_QUANTITY, BRAND , TYPE, PRODUCT_ID) VALUES (150, 'KEEN', 'PRESCRIPTION', 1);
INSERT INTO frames (STOCK_QUANTITY, BRAND , TYPE, PRODUCT_ID) VALUES (150, 'STYLUS', 'SUNGLASS', 3);


INSERT INTO lenses (BRAND, INDEX, MATERIAL, TYPE, PRODUCT_ID) VALUES ('ZEISS', 'I160', 'HIGH_INDEX', 'MONOFOCAL', 2);

INSERT INTO lens_treatments (lens_id, treatments) VALUES (2, 'ANTI_REFLECTIVE');
INSERT INTO lens_treatments (lens_id, treatments) VALUES (2, 'UV');

INSERT INTO sales (ISSUE_DATE, DELIVERY_DATE, ESTIMATED_DELIVERY_DATE, TOTAL_AMOUNT, PAYMENT_METHOD, INSTALLMENTS, COMMENTS, SALE_STATUS, CLIENT_ID, USER_ID, CARD_BRAND) 
VALUES ('2025-06-26', '2025-07-02', '2025-06-26', 389.90, 'CASH', NULL, 'this is a comment of the sale', 'COMPLETED', 2, 1, NULL);

INSERT INTO sales (ISSUE_DATE, DELIVERY_DATE, ESTIMATED_DELIVERY_DATE, TOTAL_AMOUNT, PAYMENT_METHOD, INSTALLMENTS, COMMENTS, SALE_STATUS, CLIENT_ID, USER_ID, CARD_BRAND) 
VALUES ('2025-06-26', NULL, '2025-06-26', 389.90, 'CASH', NULL, 'this is a comment of the sale', 'PENDING', 2, 1, NULL);

INSERT INTO sale_items (PRODUCT_ID, SALE_ID, PRICE) VALUES (1, 1 , 150);
INSERT INTO sale_items (PRODUCT_ID, SALE_ID, PRICE) VALUES (2, 1, 420);

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
    ophthalmologist_id
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
    3
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
    ophthalmologist_id
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
    3
);
