INSERT INTO `roles` (role_id, user_role_type)
VALUES
  (1, 'ADMIN'),
  (2, 'CHA'),
  (3, 'MA1'),
  (4, 'MA2'),
  (5, 'MA3'),
  (6, 'AD'),
  (7, 'SDD'),
  (8, 'DIRECTOR'),
  (9, 'IMPORTER');
INSERT INTO `users` (user_id, agency, email, first_name, last_name, mobile, nic, password, sso_id, status, user_name)
VALUES
  (NULL, 'slsi', 'ss', 'ss', 'ss', 'ss', 'ss', SHA2('admin', 224), 'ss', NULL,
         'admin');
INSERT INTO `user_roles` (id, role_id) VALUES (1, 1);

/* add test product*/
INSERT INTO `slsidb`.`products` (`hs_code`, `item_desc`, `serial_no`, `sls_no`, `slsi_unit`, `user_id`)
VALUES ('001', '001', '001', '001', '001', '001');

/* add test agent*/
INSERT INTO `slsidb`.`agents` (`agent_address1`, `name`, `nic`, `vat_number`)
VALUES ('agent_add', 'agent_name', '1234567890', 'v001');

