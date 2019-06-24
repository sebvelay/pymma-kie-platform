\connect keycloakdb

--
-- TOC entry 4096 (class 0 OID 317314)
-- Dependencies: 239
-- Data for Name: admin_event_entity; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4125 (class 0 OID 317777)
-- Dependencies: 268
-- Data for Name: associated_policy; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4099 (class 0 OID 317332)
-- Dependencies: 242
-- Data for Name: authentication_execution; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.authentication_execution VALUES ('c6c1cd34-212e-45e2-a0ad-ae816ceab18d', NULL, 'auth-cookie', 'master', '36214f70-b1fd-45bb-8eb2-06bd7037aa8e', 2, 10, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('3c645897-ab55-490e-85f9-adf39ad0de88', NULL, 'auth-spnego', 'master', '36214f70-b1fd-45bb-8eb2-06bd7037aa8e', 3, 20, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('c5e51784-6f80-428a-a23f-f9c9b62670f5', NULL, 'identity-provider-redirector', 'master', '36214f70-b1fd-45bb-8eb2-06bd7037aa8e', 2, 25, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('3188d4e7-8de4-44b1-9c87-80f59aee7143', NULL, NULL, 'master', '36214f70-b1fd-45bb-8eb2-06bd7037aa8e', 2, 30, true, 'f46c7bb6-d290-416e-9507-d4534ab5a6cb', NULL);
INSERT INTO public.authentication_execution VALUES ('ec5a7558-e030-4149-8f2a-a6a6c781df01', NULL, 'auth-username-password-form', 'master', 'f46c7bb6-d290-416e-9507-d4534ab5a6cb', 0, 10, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('5a8b7522-48e5-4591-97ba-3da479f78347', NULL, 'auth-otp-form', 'master', 'f46c7bb6-d290-416e-9507-d4534ab5a6cb', 1, 20, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('9ee0e2b6-7ff2-44b6-a57e-7deffa07cb30', NULL, 'direct-grant-validate-username', 'master', '1e6154a4-dc01-4359-b905-94dca266b62a', 0, 10, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('01617f3b-cd99-48a5-aac0-2268e06ffe82', NULL, 'direct-grant-validate-password', 'master', '1e6154a4-dc01-4359-b905-94dca266b62a', 0, 20, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('2a8e477f-703d-47c6-8dc3-7660733e68c9', NULL, 'direct-grant-validate-otp', 'master', '1e6154a4-dc01-4359-b905-94dca266b62a', 1, 30, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('67d48e01-1601-4fdf-9e2a-82de85cba482', NULL, 'registration-page-form', 'master', 'bb478049-afe1-42d1-a261-4626bdc43371', 0, 10, true, '8f91bfec-96f1-4ed0-a27b-9fb6feb9981e', NULL);
INSERT INTO public.authentication_execution VALUES ('a9f13c92-2913-4807-bf52-3e793172b6c6', NULL, 'registration-user-creation', 'master', '8f91bfec-96f1-4ed0-a27b-9fb6feb9981e', 0, 20, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('271cb353-7512-48de-9676-217045c363eb', NULL, 'registration-profile-action', 'master', '8f91bfec-96f1-4ed0-a27b-9fb6feb9981e', 0, 40, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('1a09f1b6-c5e6-468a-b324-d6a71f0afd69', NULL, 'registration-password-action', 'master', '8f91bfec-96f1-4ed0-a27b-9fb6feb9981e', 0, 50, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('8f4e20d7-e18a-4b10-af63-c1a2125355e2', NULL, 'registration-recaptcha-action', 'master', '8f91bfec-96f1-4ed0-a27b-9fb6feb9981e', 3, 60, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('8b82b498-ce1b-42ba-b98a-acd8c6c27eed', NULL, 'reset-credentials-choose-user', 'master', '4a64c8ba-48d4-4792-9c09-f28817ea5dc2', 0, 10, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('101f2963-b433-40be-bc87-9fd23a7df90d', NULL, 'reset-credential-email', 'master', '4a64c8ba-48d4-4792-9c09-f28817ea5dc2', 0, 20, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('3b68a38f-89f5-48ba-9c48-168e3430b654', NULL, 'reset-password', 'master', '4a64c8ba-48d4-4792-9c09-f28817ea5dc2', 0, 30, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('66aa0136-c730-4b29-9534-b17807936509', NULL, 'reset-otp', 'master', '4a64c8ba-48d4-4792-9c09-f28817ea5dc2', 1, 40, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('c47f7d41-cfec-4f5c-a28f-efcc1a93a114', NULL, 'client-secret', 'master', '42f1f24c-2bec-4765-ac9f-17218686ea7d', 2, 10, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('963eb1f8-98ad-4321-b6ee-9dfd8f9547a9', NULL, 'client-jwt', 'master', '42f1f24c-2bec-4765-ac9f-17218686ea7d', 2, 20, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('24e57f25-a2b1-42d8-8385-8449dd44c958', NULL, 'client-secret-jwt', 'master', '42f1f24c-2bec-4765-ac9f-17218686ea7d', 2, 30, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('8a52ecfd-1e6d-4a1a-b233-07c50c0773b6', NULL, 'client-x509', 'master', '42f1f24c-2bec-4765-ac9f-17218686ea7d', 2, 40, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('8e37007d-7663-42f1-8640-7ab91f883947', NULL, 'idp-review-profile', 'master', '2248b5ad-46a6-4631-bb6d-7fe5b4ae2274', 0, 10, false, NULL, '7ca1b2c5-4789-423c-88f2-cd2b0daeb229');
INSERT INTO public.authentication_execution VALUES ('cee53f77-28ba-406b-a5a8-80f14ebb5fa6', NULL, 'idp-create-user-if-unique', 'master', '2248b5ad-46a6-4631-bb6d-7fe5b4ae2274', 2, 20, false, NULL, 'aa0a2b48-479e-4bcf-b40a-df8305cbb4b7');
INSERT INTO public.authentication_execution VALUES ('596ee160-52b3-4365-adc7-c5deba5103a0', NULL, NULL, 'master', '2248b5ad-46a6-4631-bb6d-7fe5b4ae2274', 2, 30, true, '7a0c7637-3002-4ec2-95dc-0679be8fdee9', NULL);
INSERT INTO public.authentication_execution VALUES ('acc57e99-1d74-4ae1-b813-e1befaf25055', NULL, 'idp-confirm-link', 'master', '7a0c7637-3002-4ec2-95dc-0679be8fdee9', 0, 10, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('ef51091d-af2f-4661-9cb4-2f43127adf6d', NULL, 'idp-email-verification', 'master', '7a0c7637-3002-4ec2-95dc-0679be8fdee9', 2, 20, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('f08dd2d9-b1e3-4fc2-a7fd-8fbf10594c0f', NULL, NULL, 'master', '7a0c7637-3002-4ec2-95dc-0679be8fdee9', 2, 30, true, '9640f3b4-4c41-4f44-8bdc-c54d4d21d1e3', NULL);
INSERT INTO public.authentication_execution VALUES ('31f21543-28c0-4590-9de2-6dfd896c7da2', NULL, 'idp-username-password-form', 'master', '9640f3b4-4c41-4f44-8bdc-c54d4d21d1e3', 0, 10, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('a4161e66-bd61-47e5-9a1d-d4475d27efef', NULL, 'auth-otp-form', 'master', '9640f3b4-4c41-4f44-8bdc-c54d4d21d1e3', 1, 20, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('8bec50c7-cc33-498a-8322-9b5199e5287a', NULL, 'http-basic-authenticator', 'master', '14bba6c9-33e8-431c-b63a-619df3df1822', 0, 10, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('659c36e0-e8c3-4d1e-bbce-4c9983b2f095', NULL, 'docker-http-basic-authenticator', 'master', '3ee0b230-e04b-4aaa-b7aa-c57b5eddadb6', 0, 10, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('6d0720c4-4844-48ac-826d-e6940ccfef6f', NULL, 'no-cookie-redirect', 'master', 'fd3d7bb1-898d-42ec-9ea4-325b40f89bb1', 0, 10, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('c93c0cc7-fedb-4bb6-aa32-024a62072e49', NULL, 'basic-auth', 'master', 'fd3d7bb1-898d-42ec-9ea4-325b40f89bb1', 0, 20, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('6cbd7d44-e882-43a4-b9ec-7473b8610447', NULL, 'basic-auth-otp', 'master', 'fd3d7bb1-898d-42ec-9ea4-325b40f89bb1', 3, 30, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('f60ddaeb-450c-46da-a925-548fa46997ed', NULL, 'auth-spnego', 'master', 'fd3d7bb1-898d-42ec-9ea4-325b40f89bb1', 3, 40, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('0f18be9e-75cd-46b3-aeb0-9d4ac4a791ab', NULL, 'auth-cookie', 'demo', '1375a822-19ae-4b06-acfa-d24b953a92d5', 2, 10, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('f47d7b31-68cc-4111-930a-e0e3675c497c', NULL, 'auth-spnego', 'demo', '1375a822-19ae-4b06-acfa-d24b953a92d5', 3, 20, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('3054c69d-6eb3-4423-b7ba-a550f98f8dbf', NULL, 'identity-provider-redirector', 'demo', '1375a822-19ae-4b06-acfa-d24b953a92d5', 2, 25, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('52d8d7d3-44e8-4257-b314-8501ab787d63', NULL, NULL, 'demo', '1375a822-19ae-4b06-acfa-d24b953a92d5', 2, 30, true, '6e046263-af12-409c-a757-16099b90d83e', NULL);
INSERT INTO public.authentication_execution VALUES ('4deac954-ad6c-416f-965d-c48d5f730e83', NULL, 'auth-username-password-form', 'demo', '6e046263-af12-409c-a757-16099b90d83e', 0, 10, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('fd5cc0af-8778-462e-9712-bca03dc88bdd', NULL, 'auth-otp-form', 'demo', '6e046263-af12-409c-a757-16099b90d83e', 1, 20, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('16c9636a-a5da-4443-868e-c4345e1fb792', NULL, 'direct-grant-validate-username', 'demo', 'a5237c0b-2a2c-45a7-8126-56c8ddcedce3', 0, 10, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('071c9335-5777-444f-b153-c5b2815acbfd', NULL, 'direct-grant-validate-password', 'demo', 'a5237c0b-2a2c-45a7-8126-56c8ddcedce3', 0, 20, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('677590fe-6acc-4b01-ab6a-46741de9e80e', NULL, 'direct-grant-validate-otp', 'demo', 'a5237c0b-2a2c-45a7-8126-56c8ddcedce3', 1, 30, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('3904ddad-71a3-4065-8288-d1d601e743ce', NULL, 'registration-page-form', 'demo', '5b4056ff-d494-49c2-a498-5713f317bb5f', 0, 10, true, 'bf349d43-5353-4b5a-9b77-56d72814857d', NULL);
INSERT INTO public.authentication_execution VALUES ('55979cc2-aa74-4836-8032-46811efab3f0', NULL, 'registration-user-creation', 'demo', 'bf349d43-5353-4b5a-9b77-56d72814857d', 0, 20, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('1c80a1dc-1dea-44b3-8cf2-da5967f64f82', NULL, 'registration-profile-action', 'demo', 'bf349d43-5353-4b5a-9b77-56d72814857d', 0, 40, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('4c770976-3312-4e73-af0e-fba8305fca8a', NULL, 'registration-password-action', 'demo', 'bf349d43-5353-4b5a-9b77-56d72814857d', 0, 50, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('bfc9b583-7c9d-4ab8-8f41-3f262ab931c9', NULL, 'registration-recaptcha-action', 'demo', 'bf349d43-5353-4b5a-9b77-56d72814857d', 3, 60, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('476c6af2-72ce-4bea-a27d-d0ef38390f29', NULL, 'reset-credentials-choose-user', 'demo', '3d317980-1d0b-4c41-87eb-7b28e6e8a69c', 0, 10, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('3b09f120-8da9-431a-bb03-5dd7a407a0ab', NULL, 'reset-credential-email', 'demo', '3d317980-1d0b-4c41-87eb-7b28e6e8a69c', 0, 20, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('1ae9d355-c1bc-4738-992c-fd1894f35c5c', NULL, 'reset-password', 'demo', '3d317980-1d0b-4c41-87eb-7b28e6e8a69c', 0, 30, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('2d8b256e-736d-42ce-8896-24d1fd7c3589', NULL, 'reset-otp', 'demo', '3d317980-1d0b-4c41-87eb-7b28e6e8a69c', 1, 40, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('e8750e63-1777-4b30-aff7-68bbbc192782', NULL, 'client-secret', 'demo', 'f0e1c880-30c7-4627-a1b9-e93f64fcde14', 2, 10, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('47cf6cd4-3494-4f07-b974-ce5b168751b8', NULL, 'client-jwt', 'demo', 'f0e1c880-30c7-4627-a1b9-e93f64fcde14', 2, 20, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('5bd3afcb-e7fd-4812-8612-26fa19f257a8', NULL, 'client-secret-jwt', 'demo', 'f0e1c880-30c7-4627-a1b9-e93f64fcde14', 2, 30, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('cf90170d-d2b2-4ec2-b379-161f09d904b8', NULL, 'client-x509', 'demo', 'f0e1c880-30c7-4627-a1b9-e93f64fcde14', 2, 40, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('5ba7dd74-f252-41b0-95ac-647e22b26e0d', NULL, 'idp-review-profile', 'demo', '8a00fe8a-c70b-47ab-857b-9a6c068b3d2d', 0, 10, false, NULL, '58339553-c19f-4751-bd6c-7118cb0164f7');
INSERT INTO public.authentication_execution VALUES ('236823f8-0f00-450d-8edf-eadafda7e73d', NULL, 'idp-create-user-if-unique', 'demo', '8a00fe8a-c70b-47ab-857b-9a6c068b3d2d', 2, 20, false, NULL, '9200fb7b-057b-4b84-96fe-3e3ed5cd0feb');
INSERT INTO public.authentication_execution VALUES ('c80aa2d4-570c-4988-a920-13765e90ddf7', NULL, NULL, 'demo', '8a00fe8a-c70b-47ab-857b-9a6c068b3d2d', 2, 30, true, '886ebdab-abdb-4908-bd7c-8f932c84710f', NULL);
INSERT INTO public.authentication_execution VALUES ('fa93ac52-4ee2-48cb-951a-f1f09e5b31e1', NULL, 'idp-confirm-link', 'demo', '886ebdab-abdb-4908-bd7c-8f932c84710f', 0, 10, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('874f6f50-93fb-464c-bf76-a93fcb024bca', NULL, 'idp-email-verification', 'demo', '886ebdab-abdb-4908-bd7c-8f932c84710f', 2, 20, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('9b47e936-0630-4aec-bae1-9c2952b854bb', NULL, NULL, 'demo', '886ebdab-abdb-4908-bd7c-8f932c84710f', 2, 30, true, '31c0e494-6761-4092-bcc8-5a70f1ef3b8d', NULL);
INSERT INTO public.authentication_execution VALUES ('32524309-0dce-4cd9-9d18-64e49e150024', NULL, 'idp-username-password-form', 'demo', '31c0e494-6761-4092-bcc8-5a70f1ef3b8d', 0, 10, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('e5adc38a-bf95-4dee-b20e-eb28bd9cb1c5', NULL, 'auth-otp-form', 'demo', '31c0e494-6761-4092-bcc8-5a70f1ef3b8d', 1, 20, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('879cd1e1-d8df-4c21-87ac-ff4a5caaa3a5', NULL, 'http-basic-authenticator', 'demo', 'd29a9b9d-009f-4794-8d22-27d2c08a3066', 0, 10, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('0d17fd8b-59bb-438c-bfc5-baff82ba2c9c', NULL, 'docker-http-basic-authenticator', 'demo', '7f25aa09-fbb4-4f0f-a707-42ec929aaf69', 0, 10, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('53979935-25ef-47b2-bc2c-64427f544ad5', NULL, 'no-cookie-redirect', 'demo', 'bd7cf96b-7f13-43f4-84df-7536204758f1', 0, 10, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('8399b918-5da2-4640-808d-945d6eb512cd', NULL, 'basic-auth', 'demo', 'bd7cf96b-7f13-43f4-84df-7536204758f1', 0, 20, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('99bb2a36-366d-4404-94d5-1dac057874e1', NULL, 'basic-auth-otp', 'demo', 'bd7cf96b-7f13-43f4-84df-7536204758f1', 3, 30, false, NULL, NULL);
INSERT INTO public.authentication_execution VALUES ('bbaaf3e7-a7ad-45f4-8818-0889f65accc1', NULL, 'auth-spnego', 'demo', 'bd7cf96b-7f13-43f4-84df-7536204758f1', 3, 40, false, NULL, NULL);


--
-- TOC entry 4098 (class 0 OID 317326)
-- Dependencies: 241
-- Data for Name: authentication_flow; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.authentication_flow VALUES ('36214f70-b1fd-45bb-8eb2-06bd7037aa8e', 'browser', 'browser based authentication', 'master', 'basic-flow', true, true);
INSERT INTO public.authentication_flow VALUES ('f46c7bb6-d290-416e-9507-d4534ab5a6cb', 'forms', 'Username, password, otp and other auth forms.', 'master', 'basic-flow', false, true);
INSERT INTO public.authentication_flow VALUES ('1e6154a4-dc01-4359-b905-94dca266b62a', 'direct grant', 'OpenID Connect Resource Owner Grant', 'master', 'basic-flow', true, true);
INSERT INTO public.authentication_flow VALUES ('bb478049-afe1-42d1-a261-4626bdc43371', 'registration', 'registration flow', 'master', 'basic-flow', true, true);
INSERT INTO public.authentication_flow VALUES ('8f91bfec-96f1-4ed0-a27b-9fb6feb9981e', 'registration form', 'registration form', 'master', 'form-flow', false, true);
INSERT INTO public.authentication_flow VALUES ('4a64c8ba-48d4-4792-9c09-f28817ea5dc2', 'reset credentials', 'Reset credentials for a user if they forgot their password or something', 'master', 'basic-flow', true, true);
INSERT INTO public.authentication_flow VALUES ('42f1f24c-2bec-4765-ac9f-17218686ea7d', 'clients', 'Base authentication for clients', 'master', 'client-flow', true, true);
INSERT INTO public.authentication_flow VALUES ('2248b5ad-46a6-4631-bb6d-7fe5b4ae2274', 'first broker login', 'Actions taken after first broker login with identity provider account, which is not yet linked to any Keycloak account', 'master', 'basic-flow', true, true);
INSERT INTO public.authentication_flow VALUES ('7a0c7637-3002-4ec2-95dc-0679be8fdee9', 'Handle Existing Account', 'Handle what to do if there is existing account with same email/username like authenticated identity provider', 'master', 'basic-flow', false, true);
INSERT INTO public.authentication_flow VALUES ('9640f3b4-4c41-4f44-8bdc-c54d4d21d1e3', 'Verify Existing Account by Re-authentication', 'Reauthentication of existing account', 'master', 'basic-flow', false, true);
INSERT INTO public.authentication_flow VALUES ('14bba6c9-33e8-431c-b63a-619df3df1822', 'saml ecp', 'SAML ECP Profile Authentication Flow', 'master', 'basic-flow', true, true);
INSERT INTO public.authentication_flow VALUES ('3ee0b230-e04b-4aaa-b7aa-c57b5eddadb6', 'docker auth', 'Used by Docker clients to authenticate against the IDP', 'master', 'basic-flow', true, true);
INSERT INTO public.authentication_flow VALUES ('fd3d7bb1-898d-42ec-9ea4-325b40f89bb1', 'http challenge', 'An authentication flow based on challenge-response HTTP Authentication Schemes', 'master', 'basic-flow', true, true);
INSERT INTO public.authentication_flow VALUES ('1375a822-19ae-4b06-acfa-d24b953a92d5', 'browser', 'browser based authentication', 'demo', 'basic-flow', true, true);
INSERT INTO public.authentication_flow VALUES ('6e046263-af12-409c-a757-16099b90d83e', 'forms', 'Username, password, otp and other auth forms.', 'demo', 'basic-flow', false, true);
INSERT INTO public.authentication_flow VALUES ('a5237c0b-2a2c-45a7-8126-56c8ddcedce3', 'direct grant', 'OpenID Connect Resource Owner Grant', 'demo', 'basic-flow', true, true);
INSERT INTO public.authentication_flow VALUES ('5b4056ff-d494-49c2-a498-5713f317bb5f', 'registration', 'registration flow', 'demo', 'basic-flow', true, true);
INSERT INTO public.authentication_flow VALUES ('bf349d43-5353-4b5a-9b77-56d72814857d', 'registration form', 'registration form', 'demo', 'form-flow', false, true);
INSERT INTO public.authentication_flow VALUES ('3d317980-1d0b-4c41-87eb-7b28e6e8a69c', 'reset credentials', 'Reset credentials for a user if they forgot their password or something', 'demo', 'basic-flow', true, true);
INSERT INTO public.authentication_flow VALUES ('f0e1c880-30c7-4627-a1b9-e93f64fcde14', 'clients', 'Base authentication for clients', 'demo', 'client-flow', true, true);
INSERT INTO public.authentication_flow VALUES ('8a00fe8a-c70b-47ab-857b-9a6c068b3d2d', 'first broker login', 'Actions taken after first broker login with identity provider account, which is not yet linked to any Keycloak account', 'demo', 'basic-flow', true, true);
INSERT INTO public.authentication_flow VALUES ('886ebdab-abdb-4908-bd7c-8f932c84710f', 'Handle Existing Account', 'Handle what to do if there is existing account with same email/username like authenticated identity provider', 'demo', 'basic-flow', false, true);
INSERT INTO public.authentication_flow VALUES ('31c0e494-6761-4092-bcc8-5a70f1ef3b8d', 'Verify Existing Account by Re-authentication', 'Reauthentication of existing account', 'demo', 'basic-flow', false, true);
INSERT INTO public.authentication_flow VALUES ('d29a9b9d-009f-4794-8d22-27d2c08a3066', 'saml ecp', 'SAML ECP Profile Authentication Flow', 'demo', 'basic-flow', true, true);
INSERT INTO public.authentication_flow VALUES ('7f25aa09-fbb4-4f0f-a707-42ec929aaf69', 'docker auth', 'Used by Docker clients to authenticate against the IDP', 'demo', 'basic-flow', true, true);
INSERT INTO public.authentication_flow VALUES ('bd7cf96b-7f13-43f4-84df-7536204758f1', 'http challenge', 'An authentication flow based on challenge-response HTTP Authentication Schemes', 'demo', 'basic-flow', true, true);


--
-- TOC entry 4097 (class 0 OID 317320)
-- Dependencies: 240
-- Data for Name: authenticator_config; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.authenticator_config VALUES ('7ca1b2c5-4789-423c-88f2-cd2b0daeb229', 'review profile config', 'master');
INSERT INTO public.authenticator_config VALUES ('aa0a2b48-479e-4bcf-b40a-df8305cbb4b7', 'create unique user config', 'master');
INSERT INTO public.authenticator_config VALUES ('58339553-c19f-4751-bd6c-7118cb0164f7', 'review profile config', 'demo');
INSERT INTO public.authenticator_config VALUES ('9200fb7b-057b-4b84-96fe-3e3ed5cd0feb', 'create unique user config', 'demo');


--
-- TOC entry 4100 (class 0 OID 317337)
-- Dependencies: 243
-- Data for Name: authenticator_config_entry; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.authenticator_config_entry VALUES ('7ca1b2c5-4789-423c-88f2-cd2b0daeb229', 'missing', 'update.profile.on.first.login');
INSERT INTO public.authenticator_config_entry VALUES ('aa0a2b48-479e-4bcf-b40a-df8305cbb4b7', 'false', 'require.password.update.after.registration');
INSERT INTO public.authenticator_config_entry VALUES ('58339553-c19f-4751-bd6c-7118cb0164f7', 'missing', 'update.profile.on.first.login');
INSERT INTO public.authenticator_config_entry VALUES ('9200fb7b-057b-4b84-96fe-3e3ed5cd0feb', 'false', 'require.password.update.after.registration');


--
-- TOC entry 4126 (class 0 OID 317792)
-- Dependencies: 269
-- Data for Name: broker_link; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4056 (class 0 OID 316669)
-- Dependencies: 199
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.client VALUES ('294184bd-4645-4042-991d-aea04faace2f', true, true, 'master-realm', 0, false, 'b28d8d5a-09d6-4a8a-a8ac-ab64ea72701a', NULL, true, NULL, false, 'master', NULL, 0, false, false, 'master Realm', false, 'client-secret', NULL, NULL, NULL, true, false, false);
INSERT INTO public.client VALUES ('e188d744-175c-4fc7-aba3-0f85c9717119', true, false, 'account', 0, false, '55eb9ad2-b2c9-4219-86c3-06d5bd17c4e7', '/auth/realms/master/account', false, NULL, false, 'master', 'openid-connect', 0, false, false, '${client_account}', false, 'client-secret', NULL, NULL, NULL, true, false, false);
INSERT INTO public.client VALUES ('89124cff-2ab2-4dda-bbd3-f34f7223b14c', true, false, 'broker', 0, false, '88ae0c28-09bc-4e8c-bbd8-586773b06170', NULL, false, NULL, false, 'master', 'openid-connect', 0, false, false, '${client_broker}', false, 'client-secret', NULL, NULL, NULL, true, false, false);
INSERT INTO public.client VALUES ('05d6ae91-d774-40ab-b49f-7477ad5b272b', true, false, 'security-admin-console', 0, true, 'aa111450-67ec-4440-881b-99822ecd9c8a', '/auth/admin/master/console/index.html', false, NULL, false, 'master', 'openid-connect', 0, false, false, '${client_security-admin-console}', false, 'client-secret', NULL, NULL, NULL, true, false, false);
INSERT INTO public.client VALUES ('20c211e4-bab3-4730-a141-e47001412bb0', true, false, 'admin-cli', 0, true, '97d0f49c-dd1e-4c1e-82c3-f7b9863f312c', NULL, false, NULL, false, 'master', 'openid-connect', 0, false, false, '${client_admin-cli}', false, 'client-secret', NULL, NULL, NULL, false, false, true);
INSERT INTO public.client VALUES ('a685b6f1-7742-46c3-b47f-85b565e2e6e7', true, true, 'demo-realm', 0, false, 'e90315e0-f4c6-4dcd-95cd-85ba04c5998d', NULL, true, NULL, false, 'master', NULL, 0, false, false, 'demo Realm', false, 'client-secret', NULL, NULL, NULL, true, false, false);
INSERT INTO public.client VALUES ('c5214889-aca7-4b88-ae04-d7ea2d778989', true, false, 'realm-management', 0, false, '47c35dc9-c7dd-4125-83ce-57d508b35872', NULL, true, NULL, false, 'demo', 'openid-connect', 0, false, false, '${client_realm-management}', false, 'client-secret', NULL, NULL, NULL, true, false, false);
INSERT INTO public.client VALUES ('303fe57e-df0c-4707-85a8-0537bf566861', true, false, 'account', 0, false, 'b3dd56ba-578b-4e03-80f5-1e2b995d31fd', '/auth/realms/demo/account', false, NULL, false, 'demo', 'openid-connect', 0, false, false, '${client_account}', false, 'client-secret', NULL, NULL, NULL, true, false, false);
INSERT INTO public.client VALUES ('dc10b4ee-59f3-4f66-b140-8203afe8acb1', true, false, 'broker', 0, false, 'd141be21-943c-4683-b0d6-810b036bdc86', NULL, false, NULL, false, 'demo', 'openid-connect', 0, false, false, '${client_broker}', false, 'client-secret', NULL, NULL, NULL, true, false, false);
INSERT INTO public.client VALUES ('ef6433ae-3301-4d51-9932-d1899f66c4a7', true, false, 'security-admin-console', 0, true, '0b9efc59-789b-4452-ae37-5e2851218f2c', '/auth/admin/demo/console/index.html', false, NULL, false, 'demo', 'openid-connect', 0, false, false, '${client_security-admin-console}', false, 'client-secret', NULL, NULL, NULL, true, false, false);
INSERT INTO public.client VALUES ('53774c29-704c-4ae9-b09b-17185470ab5a', true, false, 'admin-cli', 0, true, '70fa4a81-28e1-473d-9daa-ac6ca9cd2c3f', NULL, false, NULL, false, 'demo', 'openid-connect', 0, false, false, '${client_admin-cli}', false, 'client-secret', NULL, NULL, NULL, false, false, true);
INSERT INTO public.client VALUES ('e2526a36-418c-4707-b3f4-d8bbd2cfc046', true, true, 'kie', 0, false, 'dd7fc22d-ba01-4387-b68a-316b12a741e8', NULL, false, 'http://localhost:8080', false, 'demo', 'openid-connect', -1, false, false, NULL, false, 'client-secret', 'http://kie-wb:8080', NULL, NULL, true, false, true);


--
-- TOC entry 4080 (class 0 OID 317043)
-- Dependencies: 223
-- Data for Name: client_attributes; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.client_attributes VALUES ('e2526a36-418c-4707-b3f4-d8bbd2cfc046', 'false', 'saml.server.signature');
INSERT INTO public.client_attributes VALUES ('e2526a36-418c-4707-b3f4-d8bbd2cfc046', 'false', 'saml.server.signature.keyinfo.ext');
INSERT INTO public.client_attributes VALUES ('e2526a36-418c-4707-b3f4-d8bbd2cfc046', 'false', 'saml.assertion.signature');
INSERT INTO public.client_attributes VALUES ('e2526a36-418c-4707-b3f4-d8bbd2cfc046', 'false', 'saml.client.signature');
INSERT INTO public.client_attributes VALUES ('e2526a36-418c-4707-b3f4-d8bbd2cfc046', 'false', 'saml.encrypt');
INSERT INTO public.client_attributes VALUES ('e2526a36-418c-4707-b3f4-d8bbd2cfc046', 'false', 'saml.authnstatement');
INSERT INTO public.client_attributes VALUES ('e2526a36-418c-4707-b3f4-d8bbd2cfc046', 'false', 'saml.onetimeuse.condition');
INSERT INTO public.client_attributes VALUES ('e2526a36-418c-4707-b3f4-d8bbd2cfc046', 'false', 'saml_force_name_id_format');
INSERT INTO public.client_attributes VALUES ('e2526a36-418c-4707-b3f4-d8bbd2cfc046', 'false', 'saml.multivalued.roles');
INSERT INTO public.client_attributes VALUES ('e2526a36-418c-4707-b3f4-d8bbd2cfc046', 'false', 'saml.force.post.binding');
INSERT INTO public.client_attributes VALUES ('e2526a36-418c-4707-b3f4-d8bbd2cfc046', 'false', 'exclude.session.state.from.auth.response');
INSERT INTO public.client_attributes VALUES ('e2526a36-418c-4707-b3f4-d8bbd2cfc046', 'false', 'tls.client.certificate.bound.access.tokens');
INSERT INTO public.client_attributes VALUES ('e2526a36-418c-4707-b3f4-d8bbd2cfc046', 'false', 'display.on.consent.screen');


--
-- TOC entry 4139 (class 0 OID 318051)
-- Dependencies: 282
-- Data for Name: client_auth_flow_bindings; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4055 (class 0 OID 316666)
-- Dependencies: 198
-- Data for Name: client_default_roles; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.client_default_roles VALUES ('e188d744-175c-4fc7-aba3-0f85c9717119', 'daf57b44-dc1a-4d08-9c40-a36987dea994');
INSERT INTO public.client_default_roles VALUES ('e188d744-175c-4fc7-aba3-0f85c9717119', '972bfb5d-ff9c-46c6-8a35-897dfb2b961c');
INSERT INTO public.client_default_roles VALUES ('303fe57e-df0c-4707-85a8-0537bf566861', '56d349f4-4b88-4986-b691-717ef57aa2d3');
INSERT INTO public.client_default_roles VALUES ('303fe57e-df0c-4707-85a8-0537bf566861', '6d5bf9b9-4301-48f9-9c4e-ed890b0f7302');


--
-- TOC entry 4138 (class 0 OID 317926)
-- Dependencies: 281
-- Data for Name: client_initial_access; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4082 (class 0 OID 317055)
-- Dependencies: 225
-- Data for Name: client_node_registrations; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4114 (class 0 OID 317575)
-- Dependencies: 257
-- Data for Name: client_scope; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.client_scope VALUES ('ee9315ac-d6f2-4218-9faf-b0f4e7c43824', 'offline_access', 'master', 'OpenID Connect built-in scope: offline_access', 'openid-connect');
INSERT INTO public.client_scope VALUES ('e1a81046-9411-4c04-b0dc-a530479c3a9c', 'role_list', 'master', 'SAML role list', 'saml');
INSERT INTO public.client_scope VALUES ('9ecfa9d2-2b77-4e00-990f-fe3519cd3d05', 'profile', 'master', 'OpenID Connect built-in scope: profile', 'openid-connect');
INSERT INTO public.client_scope VALUES ('c7c3febd-5e51-4525-85c5-313f049798e5', 'email', 'master', 'OpenID Connect built-in scope: email', 'openid-connect');
INSERT INTO public.client_scope VALUES ('b9496d2f-2730-439d-9001-b9a652bc9a5c', 'address', 'master', 'OpenID Connect built-in scope: address', 'openid-connect');
INSERT INTO public.client_scope VALUES ('ed12965d-49ed-4f59-b557-308ff1b476cf', 'phone', 'master', 'OpenID Connect built-in scope: phone', 'openid-connect');
INSERT INTO public.client_scope VALUES ('fe8ade79-8575-4f4e-ab22-61332ab02e91', 'roles', 'master', 'OpenID Connect scope for add user roles to the access token', 'openid-connect');
INSERT INTO public.client_scope VALUES ('8c04b7a5-27be-494e-8773-a7284a101027', 'web-origins', 'master', 'OpenID Connect scope for add allowed web origins to the access token', 'openid-connect');
INSERT INTO public.client_scope VALUES ('bcf4191f-bee6-4d62-b804-4d9683a6c557', 'offline_access', 'demo', 'OpenID Connect built-in scope: offline_access', 'openid-connect');
INSERT INTO public.client_scope VALUES ('c553a264-596a-4375-9cf7-a834cbbc47c4', 'role_list', 'demo', 'SAML role list', 'saml');
INSERT INTO public.client_scope VALUES ('f61ea2ca-9d62-4637-b30f-6489424ee38b', 'profile', 'demo', 'OpenID Connect built-in scope: profile', 'openid-connect');
INSERT INTO public.client_scope VALUES ('6c3cedb0-cc84-4048-8837-35ae16ea57a2', 'email', 'demo', 'OpenID Connect built-in scope: email', 'openid-connect');
INSERT INTO public.client_scope VALUES ('cac547c4-a336-4323-b85f-b3218c7580f7', 'address', 'demo', 'OpenID Connect built-in scope: address', 'openid-connect');
INSERT INTO public.client_scope VALUES ('93b96680-d750-4291-b92d-62179f81d1f9', 'phone', 'demo', 'OpenID Connect built-in scope: phone', 'openid-connect');
INSERT INTO public.client_scope VALUES ('f95d6362-5078-44ad-87cd-8e9295621032', 'roles', 'demo', 'OpenID Connect scope for add user roles to the access token', 'openid-connect');
INSERT INTO public.client_scope VALUES ('dd11bbfe-31e3-42a0-a167-efe527455b2c', 'web-origins', 'demo', 'OpenID Connect scope for add allowed web origins to the access token', 'openid-connect');


--
-- TOC entry 4115 (class 0 OID 317590)
-- Dependencies: 258
-- Data for Name: client_scope_attributes; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.client_scope_attributes VALUES ('ee9315ac-d6f2-4218-9faf-b0f4e7c43824', 'true', 'display.on.consent.screen');
INSERT INTO public.client_scope_attributes VALUES ('ee9315ac-d6f2-4218-9faf-b0f4e7c43824', '${offlineAccessScopeConsentText}', 'consent.screen.text');
INSERT INTO public.client_scope_attributes VALUES ('e1a81046-9411-4c04-b0dc-a530479c3a9c', 'true', 'display.on.consent.screen');
INSERT INTO public.client_scope_attributes VALUES ('e1a81046-9411-4c04-b0dc-a530479c3a9c', '${samlRoleListScopeConsentText}', 'consent.screen.text');
INSERT INTO public.client_scope_attributes VALUES ('9ecfa9d2-2b77-4e00-990f-fe3519cd3d05', 'true', 'display.on.consent.screen');
INSERT INTO public.client_scope_attributes VALUES ('9ecfa9d2-2b77-4e00-990f-fe3519cd3d05', '${profileScopeConsentText}', 'consent.screen.text');
INSERT INTO public.client_scope_attributes VALUES ('9ecfa9d2-2b77-4e00-990f-fe3519cd3d05', 'true', 'include.in.token.scope');
INSERT INTO public.client_scope_attributes VALUES ('c7c3febd-5e51-4525-85c5-313f049798e5', 'true', 'display.on.consent.screen');
INSERT INTO public.client_scope_attributes VALUES ('c7c3febd-5e51-4525-85c5-313f049798e5', '${emailScopeConsentText}', 'consent.screen.text');
INSERT INTO public.client_scope_attributes VALUES ('c7c3febd-5e51-4525-85c5-313f049798e5', 'true', 'include.in.token.scope');
INSERT INTO public.client_scope_attributes VALUES ('b9496d2f-2730-439d-9001-b9a652bc9a5c', 'true', 'display.on.consent.screen');
INSERT INTO public.client_scope_attributes VALUES ('b9496d2f-2730-439d-9001-b9a652bc9a5c', '${addressScopeConsentText}', 'consent.screen.text');
INSERT INTO public.client_scope_attributes VALUES ('b9496d2f-2730-439d-9001-b9a652bc9a5c', 'true', 'include.in.token.scope');
INSERT INTO public.client_scope_attributes VALUES ('ed12965d-49ed-4f59-b557-308ff1b476cf', 'true', 'display.on.consent.screen');
INSERT INTO public.client_scope_attributes VALUES ('ed12965d-49ed-4f59-b557-308ff1b476cf', '${phoneScopeConsentText}', 'consent.screen.text');
INSERT INTO public.client_scope_attributes VALUES ('ed12965d-49ed-4f59-b557-308ff1b476cf', 'true', 'include.in.token.scope');
INSERT INTO public.client_scope_attributes VALUES ('fe8ade79-8575-4f4e-ab22-61332ab02e91', 'true', 'display.on.consent.screen');
INSERT INTO public.client_scope_attributes VALUES ('fe8ade79-8575-4f4e-ab22-61332ab02e91', '${rolesScopeConsentText}', 'consent.screen.text');
INSERT INTO public.client_scope_attributes VALUES ('fe8ade79-8575-4f4e-ab22-61332ab02e91', 'false', 'include.in.token.scope');
INSERT INTO public.client_scope_attributes VALUES ('8c04b7a5-27be-494e-8773-a7284a101027', 'false', 'display.on.consent.screen');
INSERT INTO public.client_scope_attributes VALUES ('8c04b7a5-27be-494e-8773-a7284a101027', '', 'consent.screen.text');
INSERT INTO public.client_scope_attributes VALUES ('8c04b7a5-27be-494e-8773-a7284a101027', 'false', 'include.in.token.scope');
INSERT INTO public.client_scope_attributes VALUES ('bcf4191f-bee6-4d62-b804-4d9683a6c557', 'true', 'display.on.consent.screen');
INSERT INTO public.client_scope_attributes VALUES ('bcf4191f-bee6-4d62-b804-4d9683a6c557', '${offlineAccessScopeConsentText}', 'consent.screen.text');
INSERT INTO public.client_scope_attributes VALUES ('c553a264-596a-4375-9cf7-a834cbbc47c4', 'true', 'display.on.consent.screen');
INSERT INTO public.client_scope_attributes VALUES ('c553a264-596a-4375-9cf7-a834cbbc47c4', '${samlRoleListScopeConsentText}', 'consent.screen.text');
INSERT INTO public.client_scope_attributes VALUES ('f61ea2ca-9d62-4637-b30f-6489424ee38b', 'true', 'display.on.consent.screen');
INSERT INTO public.client_scope_attributes VALUES ('f61ea2ca-9d62-4637-b30f-6489424ee38b', '${profileScopeConsentText}', 'consent.screen.text');
INSERT INTO public.client_scope_attributes VALUES ('f61ea2ca-9d62-4637-b30f-6489424ee38b', 'true', 'include.in.token.scope');
INSERT INTO public.client_scope_attributes VALUES ('6c3cedb0-cc84-4048-8837-35ae16ea57a2', 'true', 'display.on.consent.screen');
INSERT INTO public.client_scope_attributes VALUES ('6c3cedb0-cc84-4048-8837-35ae16ea57a2', '${emailScopeConsentText}', 'consent.screen.text');
INSERT INTO public.client_scope_attributes VALUES ('6c3cedb0-cc84-4048-8837-35ae16ea57a2', 'true', 'include.in.token.scope');
INSERT INTO public.client_scope_attributes VALUES ('cac547c4-a336-4323-b85f-b3218c7580f7', 'true', 'display.on.consent.screen');
INSERT INTO public.client_scope_attributes VALUES ('cac547c4-a336-4323-b85f-b3218c7580f7', '${addressScopeConsentText}', 'consent.screen.text');
INSERT INTO public.client_scope_attributes VALUES ('cac547c4-a336-4323-b85f-b3218c7580f7', 'true', 'include.in.token.scope');
INSERT INTO public.client_scope_attributes VALUES ('93b96680-d750-4291-b92d-62179f81d1f9', 'true', 'display.on.consent.screen');
INSERT INTO public.client_scope_attributes VALUES ('93b96680-d750-4291-b92d-62179f81d1f9', '${phoneScopeConsentText}', 'consent.screen.text');
INSERT INTO public.client_scope_attributes VALUES ('93b96680-d750-4291-b92d-62179f81d1f9', 'true', 'include.in.token.scope');
INSERT INTO public.client_scope_attributes VALUES ('f95d6362-5078-44ad-87cd-8e9295621032', 'true', 'display.on.consent.screen');
INSERT INTO public.client_scope_attributes VALUES ('f95d6362-5078-44ad-87cd-8e9295621032', '${rolesScopeConsentText}', 'consent.screen.text');
INSERT INTO public.client_scope_attributes VALUES ('f95d6362-5078-44ad-87cd-8e9295621032', 'false', 'include.in.token.scope');
INSERT INTO public.client_scope_attributes VALUES ('dd11bbfe-31e3-42a0-a167-efe527455b2c', 'false', 'display.on.consent.screen');
INSERT INTO public.client_scope_attributes VALUES ('dd11bbfe-31e3-42a0-a167-efe527455b2c', '', 'consent.screen.text');
INSERT INTO public.client_scope_attributes VALUES ('dd11bbfe-31e3-42a0-a167-efe527455b2c', 'false', 'include.in.token.scope');


--
-- TOC entry 4140 (class 0 OID 318093)
-- Dependencies: 283
-- Data for Name: client_scope_client; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.client_scope_client VALUES ('e188d744-175c-4fc7-aba3-0f85c9717119', 'e1a81046-9411-4c04-b0dc-a530479c3a9c', true);
INSERT INTO public.client_scope_client VALUES ('20c211e4-bab3-4730-a141-e47001412bb0', 'e1a81046-9411-4c04-b0dc-a530479c3a9c', true);
INSERT INTO public.client_scope_client VALUES ('89124cff-2ab2-4dda-bbd3-f34f7223b14c', 'e1a81046-9411-4c04-b0dc-a530479c3a9c', true);
INSERT INTO public.client_scope_client VALUES ('294184bd-4645-4042-991d-aea04faace2f', 'e1a81046-9411-4c04-b0dc-a530479c3a9c', true);
INSERT INTO public.client_scope_client VALUES ('05d6ae91-d774-40ab-b49f-7477ad5b272b', 'e1a81046-9411-4c04-b0dc-a530479c3a9c', true);
INSERT INTO public.client_scope_client VALUES ('e188d744-175c-4fc7-aba3-0f85c9717119', '9ecfa9d2-2b77-4e00-990f-fe3519cd3d05', true);
INSERT INTO public.client_scope_client VALUES ('e188d744-175c-4fc7-aba3-0f85c9717119', 'c7c3febd-5e51-4525-85c5-313f049798e5', true);
INSERT INTO public.client_scope_client VALUES ('e188d744-175c-4fc7-aba3-0f85c9717119', 'fe8ade79-8575-4f4e-ab22-61332ab02e91', true);
INSERT INTO public.client_scope_client VALUES ('e188d744-175c-4fc7-aba3-0f85c9717119', '8c04b7a5-27be-494e-8773-a7284a101027', true);
INSERT INTO public.client_scope_client VALUES ('e188d744-175c-4fc7-aba3-0f85c9717119', 'ee9315ac-d6f2-4218-9faf-b0f4e7c43824', false);
INSERT INTO public.client_scope_client VALUES ('e188d744-175c-4fc7-aba3-0f85c9717119', 'b9496d2f-2730-439d-9001-b9a652bc9a5c', false);
INSERT INTO public.client_scope_client VALUES ('e188d744-175c-4fc7-aba3-0f85c9717119', 'ed12965d-49ed-4f59-b557-308ff1b476cf', false);
INSERT INTO public.client_scope_client VALUES ('20c211e4-bab3-4730-a141-e47001412bb0', '9ecfa9d2-2b77-4e00-990f-fe3519cd3d05', true);
INSERT INTO public.client_scope_client VALUES ('20c211e4-bab3-4730-a141-e47001412bb0', 'c7c3febd-5e51-4525-85c5-313f049798e5', true);
INSERT INTO public.client_scope_client VALUES ('20c211e4-bab3-4730-a141-e47001412bb0', 'fe8ade79-8575-4f4e-ab22-61332ab02e91', true);
INSERT INTO public.client_scope_client VALUES ('20c211e4-bab3-4730-a141-e47001412bb0', '8c04b7a5-27be-494e-8773-a7284a101027', true);
INSERT INTO public.client_scope_client VALUES ('20c211e4-bab3-4730-a141-e47001412bb0', 'ee9315ac-d6f2-4218-9faf-b0f4e7c43824', false);
INSERT INTO public.client_scope_client VALUES ('20c211e4-bab3-4730-a141-e47001412bb0', 'b9496d2f-2730-439d-9001-b9a652bc9a5c', false);
INSERT INTO public.client_scope_client VALUES ('20c211e4-bab3-4730-a141-e47001412bb0', 'ed12965d-49ed-4f59-b557-308ff1b476cf', false);
INSERT INTO public.client_scope_client VALUES ('89124cff-2ab2-4dda-bbd3-f34f7223b14c', '9ecfa9d2-2b77-4e00-990f-fe3519cd3d05', true);
INSERT INTO public.client_scope_client VALUES ('89124cff-2ab2-4dda-bbd3-f34f7223b14c', 'c7c3febd-5e51-4525-85c5-313f049798e5', true);
INSERT INTO public.client_scope_client VALUES ('89124cff-2ab2-4dda-bbd3-f34f7223b14c', 'fe8ade79-8575-4f4e-ab22-61332ab02e91', true);
INSERT INTO public.client_scope_client VALUES ('89124cff-2ab2-4dda-bbd3-f34f7223b14c', '8c04b7a5-27be-494e-8773-a7284a101027', true);
INSERT INTO public.client_scope_client VALUES ('89124cff-2ab2-4dda-bbd3-f34f7223b14c', 'ee9315ac-d6f2-4218-9faf-b0f4e7c43824', false);
INSERT INTO public.client_scope_client VALUES ('89124cff-2ab2-4dda-bbd3-f34f7223b14c', 'b9496d2f-2730-439d-9001-b9a652bc9a5c', false);
INSERT INTO public.client_scope_client VALUES ('89124cff-2ab2-4dda-bbd3-f34f7223b14c', 'ed12965d-49ed-4f59-b557-308ff1b476cf', false);
INSERT INTO public.client_scope_client VALUES ('294184bd-4645-4042-991d-aea04faace2f', '9ecfa9d2-2b77-4e00-990f-fe3519cd3d05', true);
INSERT INTO public.client_scope_client VALUES ('294184bd-4645-4042-991d-aea04faace2f', 'c7c3febd-5e51-4525-85c5-313f049798e5', true);
INSERT INTO public.client_scope_client VALUES ('294184bd-4645-4042-991d-aea04faace2f', 'fe8ade79-8575-4f4e-ab22-61332ab02e91', true);
INSERT INTO public.client_scope_client VALUES ('294184bd-4645-4042-991d-aea04faace2f', '8c04b7a5-27be-494e-8773-a7284a101027', true);
INSERT INTO public.client_scope_client VALUES ('294184bd-4645-4042-991d-aea04faace2f', 'ee9315ac-d6f2-4218-9faf-b0f4e7c43824', false);
INSERT INTO public.client_scope_client VALUES ('294184bd-4645-4042-991d-aea04faace2f', 'b9496d2f-2730-439d-9001-b9a652bc9a5c', false);
INSERT INTO public.client_scope_client VALUES ('294184bd-4645-4042-991d-aea04faace2f', 'ed12965d-49ed-4f59-b557-308ff1b476cf', false);
INSERT INTO public.client_scope_client VALUES ('05d6ae91-d774-40ab-b49f-7477ad5b272b', '9ecfa9d2-2b77-4e00-990f-fe3519cd3d05', true);
INSERT INTO public.client_scope_client VALUES ('05d6ae91-d774-40ab-b49f-7477ad5b272b', 'c7c3febd-5e51-4525-85c5-313f049798e5', true);
INSERT INTO public.client_scope_client VALUES ('05d6ae91-d774-40ab-b49f-7477ad5b272b', 'fe8ade79-8575-4f4e-ab22-61332ab02e91', true);
INSERT INTO public.client_scope_client VALUES ('05d6ae91-d774-40ab-b49f-7477ad5b272b', '8c04b7a5-27be-494e-8773-a7284a101027', true);
INSERT INTO public.client_scope_client VALUES ('05d6ae91-d774-40ab-b49f-7477ad5b272b', 'ee9315ac-d6f2-4218-9faf-b0f4e7c43824', false);
INSERT INTO public.client_scope_client VALUES ('05d6ae91-d774-40ab-b49f-7477ad5b272b', 'b9496d2f-2730-439d-9001-b9a652bc9a5c', false);
INSERT INTO public.client_scope_client VALUES ('05d6ae91-d774-40ab-b49f-7477ad5b272b', 'ed12965d-49ed-4f59-b557-308ff1b476cf', false);
INSERT INTO public.client_scope_client VALUES ('a685b6f1-7742-46c3-b47f-85b565e2e6e7', 'e1a81046-9411-4c04-b0dc-a530479c3a9c', true);
INSERT INTO public.client_scope_client VALUES ('a685b6f1-7742-46c3-b47f-85b565e2e6e7', '9ecfa9d2-2b77-4e00-990f-fe3519cd3d05', true);
INSERT INTO public.client_scope_client VALUES ('a685b6f1-7742-46c3-b47f-85b565e2e6e7', 'c7c3febd-5e51-4525-85c5-313f049798e5', true);
INSERT INTO public.client_scope_client VALUES ('a685b6f1-7742-46c3-b47f-85b565e2e6e7', 'fe8ade79-8575-4f4e-ab22-61332ab02e91', true);
INSERT INTO public.client_scope_client VALUES ('a685b6f1-7742-46c3-b47f-85b565e2e6e7', '8c04b7a5-27be-494e-8773-a7284a101027', true);
INSERT INTO public.client_scope_client VALUES ('a685b6f1-7742-46c3-b47f-85b565e2e6e7', 'ee9315ac-d6f2-4218-9faf-b0f4e7c43824', false);
INSERT INTO public.client_scope_client VALUES ('a685b6f1-7742-46c3-b47f-85b565e2e6e7', 'b9496d2f-2730-439d-9001-b9a652bc9a5c', false);
INSERT INTO public.client_scope_client VALUES ('a685b6f1-7742-46c3-b47f-85b565e2e6e7', 'ed12965d-49ed-4f59-b557-308ff1b476cf', false);
INSERT INTO public.client_scope_client VALUES ('303fe57e-df0c-4707-85a8-0537bf566861', 'c553a264-596a-4375-9cf7-a834cbbc47c4', true);
INSERT INTO public.client_scope_client VALUES ('53774c29-704c-4ae9-b09b-17185470ab5a', 'c553a264-596a-4375-9cf7-a834cbbc47c4', true);
INSERT INTO public.client_scope_client VALUES ('dc10b4ee-59f3-4f66-b140-8203afe8acb1', 'c553a264-596a-4375-9cf7-a834cbbc47c4', true);
INSERT INTO public.client_scope_client VALUES ('c5214889-aca7-4b88-ae04-d7ea2d778989', 'c553a264-596a-4375-9cf7-a834cbbc47c4', true);
INSERT INTO public.client_scope_client VALUES ('ef6433ae-3301-4d51-9932-d1899f66c4a7', 'c553a264-596a-4375-9cf7-a834cbbc47c4', true);
INSERT INTO public.client_scope_client VALUES ('303fe57e-df0c-4707-85a8-0537bf566861', 'f61ea2ca-9d62-4637-b30f-6489424ee38b', true);
INSERT INTO public.client_scope_client VALUES ('303fe57e-df0c-4707-85a8-0537bf566861', '6c3cedb0-cc84-4048-8837-35ae16ea57a2', true);
INSERT INTO public.client_scope_client VALUES ('303fe57e-df0c-4707-85a8-0537bf566861', 'f95d6362-5078-44ad-87cd-8e9295621032', true);
INSERT INTO public.client_scope_client VALUES ('303fe57e-df0c-4707-85a8-0537bf566861', 'dd11bbfe-31e3-42a0-a167-efe527455b2c', true);
INSERT INTO public.client_scope_client VALUES ('303fe57e-df0c-4707-85a8-0537bf566861', 'bcf4191f-bee6-4d62-b804-4d9683a6c557', false);
INSERT INTO public.client_scope_client VALUES ('303fe57e-df0c-4707-85a8-0537bf566861', 'cac547c4-a336-4323-b85f-b3218c7580f7', false);
INSERT INTO public.client_scope_client VALUES ('303fe57e-df0c-4707-85a8-0537bf566861', '93b96680-d750-4291-b92d-62179f81d1f9', false);
INSERT INTO public.client_scope_client VALUES ('53774c29-704c-4ae9-b09b-17185470ab5a', 'f61ea2ca-9d62-4637-b30f-6489424ee38b', true);
INSERT INTO public.client_scope_client VALUES ('53774c29-704c-4ae9-b09b-17185470ab5a', '6c3cedb0-cc84-4048-8837-35ae16ea57a2', true);
INSERT INTO public.client_scope_client VALUES ('53774c29-704c-4ae9-b09b-17185470ab5a', 'f95d6362-5078-44ad-87cd-8e9295621032', true);
INSERT INTO public.client_scope_client VALUES ('53774c29-704c-4ae9-b09b-17185470ab5a', 'dd11bbfe-31e3-42a0-a167-efe527455b2c', true);
INSERT INTO public.client_scope_client VALUES ('53774c29-704c-4ae9-b09b-17185470ab5a', 'bcf4191f-bee6-4d62-b804-4d9683a6c557', false);
INSERT INTO public.client_scope_client VALUES ('53774c29-704c-4ae9-b09b-17185470ab5a', 'cac547c4-a336-4323-b85f-b3218c7580f7', false);
INSERT INTO public.client_scope_client VALUES ('53774c29-704c-4ae9-b09b-17185470ab5a', '93b96680-d750-4291-b92d-62179f81d1f9', false);
INSERT INTO public.client_scope_client VALUES ('dc10b4ee-59f3-4f66-b140-8203afe8acb1', 'f61ea2ca-9d62-4637-b30f-6489424ee38b', true);
INSERT INTO public.client_scope_client VALUES ('dc10b4ee-59f3-4f66-b140-8203afe8acb1', '6c3cedb0-cc84-4048-8837-35ae16ea57a2', true);
INSERT INTO public.client_scope_client VALUES ('dc10b4ee-59f3-4f66-b140-8203afe8acb1', 'f95d6362-5078-44ad-87cd-8e9295621032', true);
INSERT INTO public.client_scope_client VALUES ('dc10b4ee-59f3-4f66-b140-8203afe8acb1', 'dd11bbfe-31e3-42a0-a167-efe527455b2c', true);
INSERT INTO public.client_scope_client VALUES ('dc10b4ee-59f3-4f66-b140-8203afe8acb1', 'bcf4191f-bee6-4d62-b804-4d9683a6c557', false);
INSERT INTO public.client_scope_client VALUES ('dc10b4ee-59f3-4f66-b140-8203afe8acb1', 'cac547c4-a336-4323-b85f-b3218c7580f7', false);
INSERT INTO public.client_scope_client VALUES ('dc10b4ee-59f3-4f66-b140-8203afe8acb1', '93b96680-d750-4291-b92d-62179f81d1f9', false);
INSERT INTO public.client_scope_client VALUES ('c5214889-aca7-4b88-ae04-d7ea2d778989', 'f61ea2ca-9d62-4637-b30f-6489424ee38b', true);
INSERT INTO public.client_scope_client VALUES ('c5214889-aca7-4b88-ae04-d7ea2d778989', '6c3cedb0-cc84-4048-8837-35ae16ea57a2', true);
INSERT INTO public.client_scope_client VALUES ('c5214889-aca7-4b88-ae04-d7ea2d778989', 'f95d6362-5078-44ad-87cd-8e9295621032', true);
INSERT INTO public.client_scope_client VALUES ('c5214889-aca7-4b88-ae04-d7ea2d778989', 'dd11bbfe-31e3-42a0-a167-efe527455b2c', true);
INSERT INTO public.client_scope_client VALUES ('c5214889-aca7-4b88-ae04-d7ea2d778989', 'bcf4191f-bee6-4d62-b804-4d9683a6c557', false);
INSERT INTO public.client_scope_client VALUES ('c5214889-aca7-4b88-ae04-d7ea2d778989', 'cac547c4-a336-4323-b85f-b3218c7580f7', false);
INSERT INTO public.client_scope_client VALUES ('c5214889-aca7-4b88-ae04-d7ea2d778989', '93b96680-d750-4291-b92d-62179f81d1f9', false);
INSERT INTO public.client_scope_client VALUES ('ef6433ae-3301-4d51-9932-d1899f66c4a7', 'f61ea2ca-9d62-4637-b30f-6489424ee38b', true);
INSERT INTO public.client_scope_client VALUES ('ef6433ae-3301-4d51-9932-d1899f66c4a7', '6c3cedb0-cc84-4048-8837-35ae16ea57a2', true);
INSERT INTO public.client_scope_client VALUES ('ef6433ae-3301-4d51-9932-d1899f66c4a7', 'f95d6362-5078-44ad-87cd-8e9295621032', true);
INSERT INTO public.client_scope_client VALUES ('ef6433ae-3301-4d51-9932-d1899f66c4a7', 'dd11bbfe-31e3-42a0-a167-efe527455b2c', true);
INSERT INTO public.client_scope_client VALUES ('ef6433ae-3301-4d51-9932-d1899f66c4a7', 'bcf4191f-bee6-4d62-b804-4d9683a6c557', false);
INSERT INTO public.client_scope_client VALUES ('ef6433ae-3301-4d51-9932-d1899f66c4a7', 'cac547c4-a336-4323-b85f-b3218c7580f7', false);
INSERT INTO public.client_scope_client VALUES ('ef6433ae-3301-4d51-9932-d1899f66c4a7', '93b96680-d750-4291-b92d-62179f81d1f9', false);
INSERT INTO public.client_scope_client VALUES ('e2526a36-418c-4707-b3f4-d8bbd2cfc046', 'c553a264-596a-4375-9cf7-a834cbbc47c4', true);
INSERT INTO public.client_scope_client VALUES ('e2526a36-418c-4707-b3f4-d8bbd2cfc046', 'f61ea2ca-9d62-4637-b30f-6489424ee38b', true);
INSERT INTO public.client_scope_client VALUES ('e2526a36-418c-4707-b3f4-d8bbd2cfc046', '6c3cedb0-cc84-4048-8837-35ae16ea57a2', true);
INSERT INTO public.client_scope_client VALUES ('e2526a36-418c-4707-b3f4-d8bbd2cfc046', 'f95d6362-5078-44ad-87cd-8e9295621032', true);
INSERT INTO public.client_scope_client VALUES ('e2526a36-418c-4707-b3f4-d8bbd2cfc046', 'dd11bbfe-31e3-42a0-a167-efe527455b2c', true);
INSERT INTO public.client_scope_client VALUES ('e2526a36-418c-4707-b3f4-d8bbd2cfc046', 'bcf4191f-bee6-4d62-b804-4d9683a6c557', false);
INSERT INTO public.client_scope_client VALUES ('e2526a36-418c-4707-b3f4-d8bbd2cfc046', 'cac547c4-a336-4323-b85f-b3218c7580f7', false);
INSERT INTO public.client_scope_client VALUES ('e2526a36-418c-4707-b3f4-d8bbd2cfc046', '93b96680-d750-4291-b92d-62179f81d1f9', false);


--
-- TOC entry 4116 (class 0 OID 317596)
-- Dependencies: 259
-- Data for Name: client_scope_role_mapping; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.client_scope_role_mapping VALUES ('ee9315ac-d6f2-4218-9faf-b0f4e7c43824', 'a7d642a2-bc13-49ed-aacc-b87f1a0d5b3a');
INSERT INTO public.client_scope_role_mapping VALUES ('bcf4191f-bee6-4d62-b804-4d9683a6c557', '8bcb18d5-4c73-422b-be0c-037077c71287');


--
-- TOC entry 4057 (class 0 OID 316681)
-- Dependencies: 200
-- Data for Name: client_session; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4103 (class 0 OID 317358)
-- Dependencies: 246
-- Data for Name: client_session_auth_status; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4081 (class 0 OID 317049)
-- Dependencies: 224
-- Data for Name: client_session_note; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4095 (class 0 OID 317236)
-- Dependencies: 238
-- Data for Name: client_session_prot_mapper; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4058 (class 0 OID 316687)
-- Dependencies: 201
-- Data for Name: client_session_role; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4104 (class 0 OID 317439)
-- Dependencies: 247
-- Data for Name: client_user_session_note; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4134 (class 0 OID 317842)
-- Dependencies: 277
-- Data for Name: component; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.component VALUES ('22cf9ac4-a278-40d6-abf6-52413a41a1cf', 'Trusted Hosts', 'master', 'trusted-hosts', 'org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy', 'master', 'anonymous');
INSERT INTO public.component VALUES ('b5889f62-ef5d-4bd1-b07c-bd9331670c33', 'Consent Required', 'master', 'consent-required', 'org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy', 'master', 'anonymous');
INSERT INTO public.component VALUES ('23c6616f-bc40-4d83-8c36-63c7adac9750', 'Full Scope Disabled', 'master', 'scope', 'org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy', 'master', 'anonymous');
INSERT INTO public.component VALUES ('8bdd3976-51a2-4691-96e7-c89fa4acdc61', 'Max Clients Limit', 'master', 'max-clients', 'org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy', 'master', 'anonymous');
INSERT INTO public.component VALUES ('89d9c3f3-247c-4552-9b51-1dcf5038d780', 'Allowed Protocol Mapper Types', 'master', 'allowed-protocol-mappers', 'org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy', 'master', 'anonymous');
INSERT INTO public.component VALUES ('bfaa140e-c1ba-4427-983a-1d23d7136615', 'Allowed Client Scopes', 'master', 'allowed-client-templates', 'org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy', 'master', 'anonymous');
INSERT INTO public.component VALUES ('d35ad741-d4e5-44c8-a202-eb57fe40b1e0', 'Allowed Protocol Mapper Types', 'master', 'allowed-protocol-mappers', 'org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy', 'master', 'authenticated');
INSERT INTO public.component VALUES ('7cc5276a-299f-4c68-b07b-b4a50a374014', 'Allowed Client Scopes', 'master', 'allowed-client-templates', 'org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy', 'master', 'authenticated');
INSERT INTO public.component VALUES ('9168d080-0ae8-4d4a-99f0-e4081bd24381', 'rsa-generated', 'master', 'rsa-generated', 'org.keycloak.keys.KeyProvider', 'master', NULL);
INSERT INTO public.component VALUES ('5eea2bdc-3f5b-4fe3-990a-b80dcd3c447c', 'hmac-generated', 'master', 'hmac-generated', 'org.keycloak.keys.KeyProvider', 'master', NULL);
INSERT INTO public.component VALUES ('8ccb7226-4bf0-4c55-9d63-bb5413c199f8', 'aes-generated', 'master', 'aes-generated', 'org.keycloak.keys.KeyProvider', 'master', NULL);
INSERT INTO public.component VALUES ('cad06a0e-58dc-4e64-a8a3-4341873eca72', 'rsa-generated', 'demo', 'rsa-generated', 'org.keycloak.keys.KeyProvider', 'demo', NULL);
INSERT INTO public.component VALUES ('908744c1-28b8-41a4-85bc-815bd33f390f', 'hmac-generated', 'demo', 'hmac-generated', 'org.keycloak.keys.KeyProvider', 'demo', NULL);
INSERT INTO public.component VALUES ('c1ee1303-92d1-4ad0-ab9e-fd48a91be4cc', 'aes-generated', 'demo', 'aes-generated', 'org.keycloak.keys.KeyProvider', 'demo', NULL);
INSERT INTO public.component VALUES ('a0574185-1a93-42fd-ac77-4e999b34ba8e', 'Trusted Hosts', 'demo', 'trusted-hosts', 'org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy', 'demo', 'anonymous');
INSERT INTO public.component VALUES ('0882b610-ede6-4f9c-9a07-bb869a4642e1', 'Consent Required', 'demo', 'consent-required', 'org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy', 'demo', 'anonymous');
INSERT INTO public.component VALUES ('bbaf2aa8-689e-438c-b132-ab47716f0181', 'Full Scope Disabled', 'demo', 'scope', 'org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy', 'demo', 'anonymous');
INSERT INTO public.component VALUES ('4e6ffa67-6c6e-4696-b51a-8809c1f514a2', 'Max Clients Limit', 'demo', 'max-clients', 'org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy', 'demo', 'anonymous');
INSERT INTO public.component VALUES ('2e53b2b0-a91d-4c8d-bdd6-201432166e26', 'Allowed Protocol Mapper Types', 'demo', 'allowed-protocol-mappers', 'org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy', 'demo', 'anonymous');
INSERT INTO public.component VALUES ('a426a3a1-2977-4508-824d-2283707dcb72', 'Allowed Client Scopes', 'demo', 'allowed-client-templates', 'org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy', 'demo', 'anonymous');
INSERT INTO public.component VALUES ('2531f4c2-1155-4c52-852d-f49e1f30c4fc', 'Allowed Protocol Mapper Types', 'demo', 'allowed-protocol-mappers', 'org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy', 'demo', 'authenticated');
INSERT INTO public.component VALUES ('1b6e6177-daaa-4dd8-b8b6-aa7df15623cc', 'Allowed Client Scopes', 'demo', 'allowed-client-templates', 'org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy', 'demo', 'authenticated');


--
-- TOC entry 4133 (class 0 OID 317836)
-- Dependencies: 276
-- Data for Name: component_config; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.component_config VALUES ('a8e6c203-4544-4165-8735-a04c9e86b295', '7cc5276a-299f-4c68-b07b-b4a50a374014', 'allow-default-scopes', 'true');
INSERT INTO public.component_config VALUES ('0c122da6-b9b6-4bb5-a1aa-0af58940cfcd', '89d9c3f3-247c-4552-9b51-1dcf5038d780', 'allowed-protocol-mapper-types', 'oidc-usermodel-property-mapper');
INSERT INTO public.component_config VALUES ('45b76750-0912-4f2f-959d-1cf8718115a4', '89d9c3f3-247c-4552-9b51-1dcf5038d780', 'allowed-protocol-mapper-types', 'saml-user-attribute-mapper');
INSERT INTO public.component_config VALUES ('ae990eca-02ec-42c0-97c0-2a096b29c0f2', '89d9c3f3-247c-4552-9b51-1dcf5038d780', 'allowed-protocol-mapper-types', 'oidc-address-mapper');
INSERT INTO public.component_config VALUES ('4a387be4-70d3-46bc-aa60-8f375294af5d', '89d9c3f3-247c-4552-9b51-1dcf5038d780', 'allowed-protocol-mapper-types', 'saml-user-property-mapper');
INSERT INTO public.component_config VALUES ('c02c0322-adb5-457d-b18d-ab598232f546', '89d9c3f3-247c-4552-9b51-1dcf5038d780', 'allowed-protocol-mapper-types', 'oidc-full-name-mapper');
INSERT INTO public.component_config VALUES ('ccf42132-c591-422e-9efc-8850c6b559eb', '89d9c3f3-247c-4552-9b51-1dcf5038d780', 'allowed-protocol-mapper-types', 'oidc-usermodel-attribute-mapper');
INSERT INTO public.component_config VALUES ('a6542fd7-ec86-43d6-b4f2-e51b908b641f', '89d9c3f3-247c-4552-9b51-1dcf5038d780', 'allowed-protocol-mapper-types', 'oidc-sha256-pairwise-sub-mapper');
INSERT INTO public.component_config VALUES ('c0f4a9b6-2d1b-439e-99d7-b56baca85e5c', '89d9c3f3-247c-4552-9b51-1dcf5038d780', 'allowed-protocol-mapper-types', 'saml-role-list-mapper');
INSERT INTO public.component_config VALUES ('ce71a0d1-17cb-4cc6-9272-f7ff25ed59e4', '22cf9ac4-a278-40d6-abf6-52413a41a1cf', 'client-uris-must-match', 'true');
INSERT INTO public.component_config VALUES ('aa8db10c-9974-4407-ab8b-e01380baf4db', '22cf9ac4-a278-40d6-abf6-52413a41a1cf', 'host-sending-registration-request-must-match', 'true');
INSERT INTO public.component_config VALUES ('e3e3c8f7-3d83-42a0-b395-052a766b875e', '8bdd3976-51a2-4691-96e7-c89fa4acdc61', 'max-clients', '200');
INSERT INTO public.component_config VALUES ('e41830f9-feb5-47d6-88f8-22b2d3a65f4a', 'd35ad741-d4e5-44c8-a202-eb57fe40b1e0', 'allowed-protocol-mapper-types', 'saml-user-property-mapper');
INSERT INTO public.component_config VALUES ('1550dbf3-fe4d-4f85-a286-cfdcf07dc8e0', 'd35ad741-d4e5-44c8-a202-eb57fe40b1e0', 'allowed-protocol-mapper-types', 'oidc-full-name-mapper');
INSERT INTO public.component_config VALUES ('6a5bb95f-d472-4a89-b5ce-9edfe2360bfc', 'd35ad741-d4e5-44c8-a202-eb57fe40b1e0', 'allowed-protocol-mapper-types', 'saml-user-attribute-mapper');
INSERT INTO public.component_config VALUES ('5220b6fb-5a9e-4284-aa60-36d02842dd2c', 'd35ad741-d4e5-44c8-a202-eb57fe40b1e0', 'allowed-protocol-mapper-types', 'oidc-usermodel-attribute-mapper');
INSERT INTO public.component_config VALUES ('7dd7e49d-f056-407d-8b2c-59c3644301bb', 'd35ad741-d4e5-44c8-a202-eb57fe40b1e0', 'allowed-protocol-mapper-types', 'oidc-address-mapper');
INSERT INTO public.component_config VALUES ('a4e8d260-3f3c-44b0-a630-d7df22e337a1', 'd35ad741-d4e5-44c8-a202-eb57fe40b1e0', 'allowed-protocol-mapper-types', 'oidc-usermodel-property-mapper');
INSERT INTO public.component_config VALUES ('77d3e0d5-2212-4cab-b3f8-93da359e2117', 'd35ad741-d4e5-44c8-a202-eb57fe40b1e0', 'allowed-protocol-mapper-types', 'saml-role-list-mapper');
INSERT INTO public.component_config VALUES ('bd958250-0f8a-43b7-b0a9-03fe77c1f2dd', 'd35ad741-d4e5-44c8-a202-eb57fe40b1e0', 'allowed-protocol-mapper-types', 'oidc-sha256-pairwise-sub-mapper');
INSERT INTO public.component_config VALUES ('51ae3883-f0d7-44a4-92b7-836e14bdf307', 'bfaa140e-c1ba-4427-983a-1d23d7136615', 'allow-default-scopes', 'true');
INSERT INTO public.component_config VALUES ('461bb217-9b6c-44ee-a43a-6089cf51553b', '8ccb7226-4bf0-4c55-9d63-bb5413c199f8', 'secret', 'H9vZd7e146nlsQ4aRFY4rg');
INSERT INTO public.component_config VALUES ('e79aada2-76dc-4706-b029-daaf4f9048fe', '8ccb7226-4bf0-4c55-9d63-bb5413c199f8', 'kid', 'a5e86330-7165-4c5c-a4ea-8226d5bbabd2');
INSERT INTO public.component_config VALUES ('f522bf7b-1a29-4c25-ab0b-e020a21fc0ca', '8ccb7226-4bf0-4c55-9d63-bb5413c199f8', 'priority', '100');
INSERT INTO public.component_config VALUES ('19a0f2a7-d028-42c8-8300-c9f868037c3b', '9168d080-0ae8-4d4a-99f0-e4081bd24381', 'priority', '100');
INSERT INTO public.component_config VALUES ('41e5355b-9ed2-4b7e-adb7-a7ff7be53cda', '9168d080-0ae8-4d4a-99f0-e4081bd24381', 'certificate', 'MIICmzCCAYMCBgFqC440nTANBgkqhkiG9w0BAQsFADARMQ8wDQYDVQQDDAZtYXN0ZXIwHhcNMTkwNDExMDgzODUwWhcNMjkwNDExMDg0MDMwWjARMQ8wDQYDVQQDDAZtYXN0ZXIwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCV6nd4eLNtbtmZcJq4+brk6fkjKVgYcU9f3pSNzhms4yxxPiJ2/LbqoWOj4LGCNVMMp8HuGQuoiGVxfwypTv9ZIhMB1ptIKxYrEi2UP4vnd15TMuSuhpu+6k8QV/DVPvw93UcQJkaV8lREtTDVp3zgSfDpYTCkbpTFonuss+kYz3LInCdPJqaU4oc9rNqp3vm2rHCJq2KIDK87kL1wyH6YxDjR6t2XIm+BhAKamPETbyqflDpx7iXp7uoDjjHm8sOTY6jeU1/9Oi6vGGJwsNoBD7ynOSBFd58t54DPQf3skJ2jq+19Q5NbTr5+IcOgAYa5JZsD+hXqUXezhxdRYOlNAgMBAAEwDQYJKoZIhvcNAQELBQADggEBADi08e7GJ1siqMAcYFlbbZa71vdPgiF0MDZWJ4htIS6lwc2hgK76lCWCt5IIGctnEeqdVEPFyhdtWNtsZvJ5+DRLdGYJ7pucMY4EANc0A4sTMFhRwRMYLMvTR+vSMAdwAVktPhNUyLFjoQQlp3lUYMIjl7CjkMkYB2XXHdbonanFZkEmOPovoASPSp6ZA56dvj7CdOxvVgvDPUXpJBJAQVH/qTDbWtDP8CEkMthEj8iYj24yb4kl2NIroKmIxSpNmkrXnW9WZktFGxKPjM3yjRrLHUQpRjlV1zGEBmjr96LmEke/lOcEkt75IGQq8Vqs6Rmq9wp5n0B6ozdr4iGHKUU=');
INSERT INTO public.component_config VALUES ('02d431a8-130c-48ea-a3f9-3d39d78fbd9f', '9168d080-0ae8-4d4a-99f0-e4081bd24381', 'privateKey', 'MIIEowIBAAKCAQEAlep3eHizbW7ZmXCauPm65On5IylYGHFPX96Ujc4ZrOMscT4idvy26qFjo+CxgjVTDKfB7hkLqIhlcX8MqU7/WSITAdabSCsWKxItlD+L53deUzLkroabvupPEFfw1T78Pd1HECZGlfJURLUw1ad84Enw6WEwpG6UxaJ7rLPpGM9yyJwnTyamlOKHPazaqd75tqxwiatiiAyvO5C9cMh+mMQ40erdlyJvgYQCmpjxE28qn5Q6ce4l6e7qA44x5vLDk2Oo3lNf/TourxhicLDaAQ+8pzkgRXefLeeAz0H97JCdo6vtfUOTW06+fiHDoAGGuSWbA/oV6lF3s4cXUWDpTQIDAQABAoIBACBCt3jmSH5ZJ1xcnf8GRpCyRNmrMPj5kUIpLMCjRGUu8cZgv5vpYBd3qXB/jtNdVETZ6OKq4xZeW64jz/6zMEHnEj4IB5xtITUdkU9gqWOjDVpMDmf8MZHQ7DixI/NPvYo2ju9LvU7ROg8bCqo/g6DkaIJgVs5buYoaIBEXGicrT3+voSvFOFWovu2IxxtN/6Zem2gnIxaFWIwTRgi9QzlVSPtJS4NnbhguqsQr7/lbc7G45iq3znrOHmoeB9RDJZzPqJV26PZ7ZmA/iqp2QdHzODlNedDHmJXuWoF2rGLezTSwjGMohYOfYPd/Kxc4OLG021ufAqaBcQdLNxbnNAECgYEA6bNJon9sSxtvm0J9cHzCKdPtj7YuU9LRxLkyJPampBQMpyO1M9VHgilXwrOHGmEls3j5RsL6UK6gk5sMpWu3mMyAo3cPHhveyKLMh5wnkA245i2g7hdAUElWyQTyR+wrasLtcid2bJ3JB0oGA9US78w9YZM7IEX/oN0w3wuVBE0CgYEApDiJA6IkCY5N5hzq0wDkV2vYIcdoTh6hw9ZmLmVFH7jJUPCDs/IyB8NLCWBeKnac8kFxCmFs0EPYRZhUNKNB0CTWZe8zAUmrwsaTSP7i/eOjCZT92JOpOXtbQHq6oUML8XLWUG3DjgYGJlCyEyPxzdZHjvJT21khuez31vmR+QECgYAleIW59Exczo1ai5KpUpefNUfF3yQWdJsFu9v6aUHW1RAiTGtlYDETAaX8peRnqmPtHtx5KBISla1RfW7NpzPM4B1QGGroHPffm5rHs5X0Vv7qiS5gfoHlXZz4pj5ioc9kUtTXlNQ8HiB5drj4LFXY9RpyZrT4O8D8/fo0CRtfkQKBgQCDYfe8QGw0ePU5tMjqJzyH9G/mFnMMjNU+qLj3RW4YAu6NIt87h/ke3M+boM21i0/lQRX/CBLWTHEqvMQhmLt+mvosc0KLNTWFjvkm3UUdepFliR3Nt9Ubd+V5oLbPbaSQdx+fGTHq3kbtQII3bfp2g4ichKMySg0BguSFyQVQAQKBgDxTRZTWK3UmmdDTXpUVIW3lnLQWo2PzdRiAa9gx6ioowl439yLokSs0BiI3lpoI/Hl9lLOsq1vN3FYb23p6zcSsLUK5nXjCvJ/Wf/slwqx/0w9IeC3HEjc/sQG2Yy2ktIplUpWndA9yOahMwa9POLrgvfSPljAutG3FhYxrnU7u');
INSERT INTO public.component_config VALUES ('3259ae7e-8e7c-4afd-93e8-047eb87f2763', '5eea2bdc-3f5b-4fe3-990a-b80dcd3c447c', 'priority', '100');
INSERT INTO public.component_config VALUES ('89f75cbb-4d6d-4a6d-aadb-b7f84b928000', '5eea2bdc-3f5b-4fe3-990a-b80dcd3c447c', 'secret', 'DXqEv7XQ0dfFKkMYcZ4yfrQuq-0f7HMb5mbyjvuFcgzcLImZOwKvVscwxA1DDLAfOKxgS-Z2igOslmROlFdB9A');
INSERT INTO public.component_config VALUES ('50d3b9fa-368b-4253-85e5-cbe4de67e62b', '5eea2bdc-3f5b-4fe3-990a-b80dcd3c447c', 'algorithm', 'HS256');
INSERT INTO public.component_config VALUES ('e09d79d2-ef0b-462e-81f9-8dbed6a70038', '5eea2bdc-3f5b-4fe3-990a-b80dcd3c447c', 'kid', 'dba6f15a-af81-466b-bb24-3912cab52e69');
INSERT INTO public.component_config VALUES ('4a63247b-2648-46b6-bff2-53638fcad359', 'cad06a0e-58dc-4e64-a8a3-4341873eca72', 'priority', '100');
INSERT INTO public.component_config VALUES ('e4df2f3c-5881-4524-b4f8-db3069df0277', 'cad06a0e-58dc-4e64-a8a3-4341873eca72', 'privateKey', 'MIIEpAIBAAKCAQEAoQBLWufOyICqNgRYzZZU/RsoosuKCH7gCsP/dOnfb8t3EUcZrt6rKvn9uSyVfCjJ0EhZBsaQQXNuyildY3Nbbs4uVlRUtHGZoqbnMZfdP1kZTrSnVLS+ckP19UoIVMZUykXEih8jmtmvXKCqeuB81GUW0zrUDHR3QCGihp5XyuLKvprXl+m3zUL7HYWlT6gXgWLx4C8P2XOcuj+G7/FHZjEvNWCUMp4EslVxMcmLoYjMs1y8sisXrpFbeE6blvoSNXL1RGanMJt9oVW/sWVw94wILavmiCxtd1VsZbzXTdBM8kX0Ro2So7OJKWBmBdq0M7v5U4A8yhkJt+2QYA3VtwIDAQABAoIBAFG+DoRXETvnZ4QJbPTkkL35HgaZhLmPgTy2CmtXmqPuD8Pr73RDhXBKgLGVm0fwhZaERP8VrYQtkWB+4/VsSk3VzNzz8BW1X6WfS954FlLlb93wBs0Q/2iQbL5fGh/AmqggCAuAh9lFEK3cvPGqLHbB5HbWdXblYu5ipVvn5PcSoxQDT4lVLFAzYyu/ktfN6VP4Gr9aU2rETop7JcWgHa02iwrI8XgOiJcLoMe74MLJlpKGa0mVc8Zm/o0E9SCWClZhCs1zWRZT9EuAtUgEe6cq6QmjOChEo20qArhwjZqXZxtc0eJ3MpZRZ72zkTYG9NkL9qv5sWbuNQIshdWz7kECgYEA434QJX3RiGzPOqJ14ti6tGA4huG1Fqa3YSitYx5irPj0GftyoDfVlEil29WIj2iJ3pwIldiE18vyf8TUANgqPPCv/17SXYSfgzgCgV8w55XD3c0Ssqna0CAcc3HvgmMg+mRXIkQatMOYqfm093b1KSnCdHgQpMDho9mBi7zs1/ECgYEAtS0yZBU6Al+br951V7Yt1hbz7Pm6mDP72XfMpA0Xervd0U+/ujUpptNtJFw7v+4CqKGvOy1KIVO2QU3NhqcnBrxfXUAGcVPeOsQCvvgw2KE0Umk+gstXDHhPTOr5rYYe2kPrjHLcUuKFVjL7t1vqvMFuqlNwecuEFtIle3T38CcCgYEAw8I4DLsPMpFHZdJlFRag2xStr8vL87j0tkO1qER+bxAvpagNhDdnr5kwOFKwl49CjG/Cp4dREQ1sGGx8blmVRylqmZqz9UtppBXvefo68B2dFWEhxOKK62umb+VMT+wTdcQggtkODhz+Ys4A3q3b21cXAPhMgimEQWWqp1+kg1ECgYAGgqhD5mteOHdMu4615ysAfXSoeFTXMU8EgoSNBJdqZqPS6K24ED796q0KWHIhNHlN+/ljA/6c8fgjopsXEpyZZl166VkR63SZ4f9DQ4Hl1u0dZQDFDG+FWDFpTyxHqwEi0Lv/IQV/yPoHne9jqF7oB39liXgKOGD3vPdx10MtkwKBgQCA1UgEV3o5fMu1EWcWFsmXnmQ/aALICu8/ef8OCsouSie2/yCOsNDReE988dcLYOl/aARsoHheR1Y6qaa3c1t2ymNGwzyHXhiPcxyWevGYuzy2cY5ClzhUCkQdrUbU2VkcWu1+sLjq9E7kAhaXr2tFyIjloRammKS3V/2H2o8h9A==');
INSERT INTO public.component_config VALUES ('88b58371-77fe-47af-b39e-758b2bdb1065', 'cad06a0e-58dc-4e64-a8a3-4341873eca72', 'certificate', 'MIIClzCCAX8CBgFqC5CxsTANBgkqhkiG9w0BAQsFADAPMQ0wCwYDVQQDDARkZW1vMB4XDTE5MDQxMTA4NDEzM1oXDTI5MDQxMTA4NDMxM1owDzENMAsGA1UEAwwEZGVtbzCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAKEAS1rnzsiAqjYEWM2WVP0bKKLLigh+4ArD/3Tp32/LdxFHGa7eqyr5/bkslXwoydBIWQbGkEFzbsopXWNzW27OLlZUVLRxmaKm5zGX3T9ZGU60p1S0vnJD9fVKCFTGVMpFxIofI5rZr1ygqnrgfNRlFtM61Ax0d0AhooaeV8riyr6a15fpt81C+x2FpU+oF4Fi8eAvD9lznLo/hu/xR2YxLzVglDKeBLJVcTHJi6GIzLNcvLIrF66RW3hOm5b6EjVy9URmpzCbfaFVv7FlcPeMCC2r5ogsbXdVbGW8103QTPJF9EaNkqOziSlgZgXatDO7+VOAPMoZCbftkGAN1bcCAwEAATANBgkqhkiG9w0BAQsFAAOCAQEABaMU8rUsxgFx96kOeu4VFKvMJCuzAWhaCMfIJL3GEYvs2szZs55hux9Np2ZbwV8G21GVBPEZ4CJvb/4/I7rlaTBQCDJbtfypYVPrVX1IqNRtlAgPtrhe1tRdgHRZDYhAVRIOd9odjUC8Nmxysayq4I3bZeQlZgVTC8r4mNsoy6ooMtowBeVmGVMhnJKuiFxrdGFtPkrd4xqVFM4IOqax6LdG/mcW+ge+IrGy8a2vcU2dqSpA03FZCsq+DkHU3aQtsOVuUNhqontNBa2/KnBUPDRwOFZo5VolgVH1RiLyZWGEPhc4y1LsOLx+Y4OeXdWFmpYQTQ4yg6JHE1IvrIyyLA==');
INSERT INTO public.component_config VALUES ('df104a46-1f03-4933-8944-74bdb3878327', 'c1ee1303-92d1-4ad0-ab9e-fd48a91be4cc', 'priority', '100');
INSERT INTO public.component_config VALUES ('e9b2f9ad-e4c4-414d-baf1-ecf6c868ca5d', 'c1ee1303-92d1-4ad0-ab9e-fd48a91be4cc', 'kid', '8de5b1d5-3c28-4d77-8c4f-698024671417');
INSERT INTO public.component_config VALUES ('5f47c516-ca97-4c5d-af98-9d795985d0cf', 'c1ee1303-92d1-4ad0-ab9e-fd48a91be4cc', 'secret', '1hBLPIVPd1Onhgzw5b9hyw');
INSERT INTO public.component_config VALUES ('f2b4eb87-554b-4ffe-b1a1-b46c9c9ba08f', '908744c1-28b8-41a4-85bc-815bd33f390f', 'algorithm', 'HS256');
INSERT INTO public.component_config VALUES ('9cd4dee7-8549-4250-8c1e-de86f2547fa6', '908744c1-28b8-41a4-85bc-815bd33f390f', 'secret', 'AUYePalhM8SLD1MYod29JYZrC1dkMVSiV92vp94uW73KlHYud1lWuf4JEvWlu8UJ_vmf3jcJQPZSNm5wvh2Aig');
INSERT INTO public.component_config VALUES ('f08eee47-743f-48e6-9941-b9d6e0c79f23', '908744c1-28b8-41a4-85bc-815bd33f390f', 'kid', 'cb82fc0b-89b8-4ab6-8b46-bd2f6d94093f');
INSERT INTO public.component_config VALUES ('a6c7a294-6e25-4ddf-a0ee-23a20afb17ef', '908744c1-28b8-41a4-85bc-815bd33f390f', 'priority', '100');
INSERT INTO public.component_config VALUES ('553c973b-4d3e-4f83-8c96-695ef5b33b2b', 'a0574185-1a93-42fd-ac77-4e999b34ba8e', 'host-sending-registration-request-must-match', 'true');
INSERT INTO public.component_config VALUES ('1616bc20-5c8a-464d-88e4-ea1dc8c32dfe', 'a0574185-1a93-42fd-ac77-4e999b34ba8e', 'client-uris-must-match', 'true');
INSERT INTO public.component_config VALUES ('a66c2264-6266-43da-a3a1-5b8810c31fc2', '2531f4c2-1155-4c52-852d-f49e1f30c4fc', 'allowed-protocol-mapper-types', 'saml-role-list-mapper');
INSERT INTO public.component_config VALUES ('d76e3da7-02f7-40de-914c-e5841b4049c1', '2531f4c2-1155-4c52-852d-f49e1f30c4fc', 'allowed-protocol-mapper-types', 'oidc-address-mapper');
INSERT INTO public.component_config VALUES ('c782ffb1-0d18-41ca-975b-7f16a080f0f3', '2531f4c2-1155-4c52-852d-f49e1f30c4fc', 'allowed-protocol-mapper-types', 'saml-user-attribute-mapper');
INSERT INTO public.component_config VALUES ('5703845d-de99-4f60-b318-46869ec5c4d2', '2531f4c2-1155-4c52-852d-f49e1f30c4fc', 'allowed-protocol-mapper-types', 'saml-user-property-mapper');
INSERT INTO public.component_config VALUES ('b7ef20ac-873c-4eca-910f-ed3f750b3876', '2531f4c2-1155-4c52-852d-f49e1f30c4fc', 'allowed-protocol-mapper-types', 'oidc-usermodel-property-mapper');
INSERT INTO public.component_config VALUES ('fc58957a-cb0e-4c95-b755-bccf137e37c5', '2531f4c2-1155-4c52-852d-f49e1f30c4fc', 'allowed-protocol-mapper-types', 'oidc-usermodel-attribute-mapper');
INSERT INTO public.component_config VALUES ('7924d055-680e-4e8c-aea8-486a9802a6d4', '2531f4c2-1155-4c52-852d-f49e1f30c4fc', 'allowed-protocol-mapper-types', 'oidc-full-name-mapper');
INSERT INTO public.component_config VALUES ('ccc0f79a-e95a-4e76-9778-d5ff33b932a6', '2531f4c2-1155-4c52-852d-f49e1f30c4fc', 'allowed-protocol-mapper-types', 'oidc-sha256-pairwise-sub-mapper');
INSERT INTO public.component_config VALUES ('62a1c379-a764-4c4e-aa40-0aab0957c3d5', '4e6ffa67-6c6e-4696-b51a-8809c1f514a2', 'max-clients', '200');
INSERT INTO public.component_config VALUES ('197dc945-20f0-4d6a-b3c5-d7a8d6e5ad7f', 'a426a3a1-2977-4508-824d-2283707dcb72', 'allow-default-scopes', 'true');
INSERT INTO public.component_config VALUES ('13537ce8-533b-4256-96fa-e934b9f8a5b1', '2e53b2b0-a91d-4c8d-bdd6-201432166e26', 'allowed-protocol-mapper-types', 'oidc-full-name-mapper');
INSERT INTO public.component_config VALUES ('0eea1993-958e-4f54-815e-03aa6197b753', '2e53b2b0-a91d-4c8d-bdd6-201432166e26', 'allowed-protocol-mapper-types', 'saml-user-attribute-mapper');
INSERT INTO public.component_config VALUES ('e0f9f3c7-62f6-4e0a-b3cc-18e074c8251e', '2e53b2b0-a91d-4c8d-bdd6-201432166e26', 'allowed-protocol-mapper-types', 'saml-user-property-mapper');
INSERT INTO public.component_config VALUES ('288627b7-ef61-4c2f-99c2-e1f3d3a1332d', '2e53b2b0-a91d-4c8d-bdd6-201432166e26', 'allowed-protocol-mapper-types', 'oidc-usermodel-property-mapper');
INSERT INTO public.component_config VALUES ('84d405ec-b0b7-4daf-9c9d-dcfd5f83eb4b', '2e53b2b0-a91d-4c8d-bdd6-201432166e26', 'allowed-protocol-mapper-types', 'saml-role-list-mapper');
INSERT INTO public.component_config VALUES ('82cc2147-6364-4c39-b134-3a049c415199', '2e53b2b0-a91d-4c8d-bdd6-201432166e26', 'allowed-protocol-mapper-types', 'oidc-usermodel-attribute-mapper');
INSERT INTO public.component_config VALUES ('bd902057-b339-4200-96a5-2c239420ac03', '2e53b2b0-a91d-4c8d-bdd6-201432166e26', 'allowed-protocol-mapper-types', 'oidc-address-mapper');
INSERT INTO public.component_config VALUES ('f78d779f-f14e-4e18-b97e-81e408aeeefc', '2e53b2b0-a91d-4c8d-bdd6-201432166e26', 'allowed-protocol-mapper-types', 'oidc-sha256-pairwise-sub-mapper');
INSERT INTO public.component_config VALUES ('c763c2c3-bd8e-4b1e-a430-fd9993788b50', '1b6e6177-daaa-4dd8-b8b6-aa7df15623cc', 'allow-default-scopes', 'true');


--
-- TOC entry 4059 (class 0 OID 316690)
-- Dependencies: 202
-- Data for Name: composite_role; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', 'a26e33d6-9cc1-4fdc-999d-f3119e6b5eea');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', 'cf48baa1-8dde-4fae-bbfa-66945e61d84b');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', 'a89fc342-e69c-40b6-b6ca-dd20da0267c7');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', '9403e3f1-fdc0-4b33-8ae0-72f36deecbe8');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', 'afda1459-20c2-4aed-bf0e-71d0c6c04c81');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', '2991aa72-4198-4818-8258-1aa0700e356c');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', '4f6d36c5-349b-48ff-9c90-e2922bb7638a');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', '55d7871d-52f0-4959-a1d3-fad60d8854a5');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', '190b4ad1-798d-46af-bc63-632711a6f7c4');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', 'bfb016ee-7391-478d-9ccc-b58f4dfaef1d');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', 'ac97d4c9-5836-4b05-a99c-7f89400b66ef');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', '10fc3ed9-a801-4ab6-bbfb-9ac9ae1239fc');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', 'e58cf422-555a-4769-92f9-133ae477beee');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', '10a609e7-29d5-454e-9174-6da10b4d51cb');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', '22fa3a40-17d0-4b37-aae1-6e5cae83211a');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', '66af8969-9172-4293-b1c0-127466ec67da');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', 'd59fa34d-5a44-4e46-a7b4-442b7b529954');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', '47d31a90-8a14-41d5-a737-b8b446340c5b');
INSERT INTO public.composite_role VALUES ('9403e3f1-fdc0-4b33-8ae0-72f36deecbe8', '22fa3a40-17d0-4b37-aae1-6e5cae83211a');
INSERT INTO public.composite_role VALUES ('9403e3f1-fdc0-4b33-8ae0-72f36deecbe8', '47d31a90-8a14-41d5-a737-b8b446340c5b');
INSERT INTO public.composite_role VALUES ('afda1459-20c2-4aed-bf0e-71d0c6c04c81', '66af8969-9172-4293-b1c0-127466ec67da');
INSERT INTO public.composite_role VALUES ('972bfb5d-ff9c-46c6-8a35-897dfb2b961c', '0f5571ca-a11c-4b5d-a0a8-99e7efb80c71');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', '39c78d32-5da9-485a-99be-c7eec7552d63');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', '92d9cce1-8d60-43d3-aa0d-f5bc5bc2796b');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', '833dd08e-faad-49e6-bdc2-1f34338e2175');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', '1d69f503-4d1a-4af9-a3d5-551bc1ff51c9');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', '023f745f-04da-43b5-8825-0e76cace5215');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', '57df4bb3-97cd-4e41-9d3a-c0cc4dc59aa1');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', '8e279fa9-cedb-4684-af66-cb822fe8f21b');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', 'dfce2376-907f-4ecd-94fb-691969f78e23');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', '7f023fd4-fd5a-4a27-9a30-dbafdad77b13');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', '2a5d6fde-5edd-4521-b310-b0f1aecf0ce5');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', '1e130064-5899-4e10-8fa1-982de2a564a5');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', '05e58b6f-3937-4968-a0dc-9f66207a3398');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', '23f7628d-3f9e-4aac-ad6a-1e6ab5f1d184');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', '19d4497c-6697-4f06-987e-364b25505b96');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', 'fb81f904-e548-4464-9f95-e62c77b6750a');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', '170f2d33-ef40-4c8b-b60f-371d0626e4d6');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', '2f43bb08-60aa-4575-8008-c19abacecc81');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', 'acdce3ee-eb47-461a-8570-a56c97b0b5ec');
INSERT INTO public.composite_role VALUES ('1d69f503-4d1a-4af9-a3d5-551bc1ff51c9', 'acdce3ee-eb47-461a-8570-a56c97b0b5ec');
INSERT INTO public.composite_role VALUES ('1d69f503-4d1a-4af9-a3d5-551bc1ff51c9', 'fb81f904-e548-4464-9f95-e62c77b6750a');
INSERT INTO public.composite_role VALUES ('023f745f-04da-43b5-8825-0e76cace5215', '170f2d33-ef40-4c8b-b60f-371d0626e4d6');
INSERT INTO public.composite_role VALUES ('e006a180-6555-42da-8ce4-5b1884af4358', 'ba9d76fd-6f7b-40db-94c2-16e4164c7832');
INSERT INTO public.composite_role VALUES ('e006a180-6555-42da-8ce4-5b1884af4358', '08379739-df9f-42d9-9c2b-7536e88b4525');
INSERT INTO public.composite_role VALUES ('e006a180-6555-42da-8ce4-5b1884af4358', '4d0a91ec-ec17-4b0a-b9f5-bfb96acc3952');
INSERT INTO public.composite_role VALUES ('e006a180-6555-42da-8ce4-5b1884af4358', '5f38f4cb-e789-4027-b0b2-2e5abba74677');
INSERT INTO public.composite_role VALUES ('e006a180-6555-42da-8ce4-5b1884af4358', 'ab991d94-c8b5-49db-8404-539412e86e21');
INSERT INTO public.composite_role VALUES ('e006a180-6555-42da-8ce4-5b1884af4358', 'fc3cae74-08b5-4d3f-8a84-603d581d1cbb');
INSERT INTO public.composite_role VALUES ('e006a180-6555-42da-8ce4-5b1884af4358', 'ef5fcad2-4823-44aa-848c-d4989f3156bc');
INSERT INTO public.composite_role VALUES ('e006a180-6555-42da-8ce4-5b1884af4358', 'a5b1adf4-4616-4ccd-a4b1-1557f9f0d9b3');
INSERT INTO public.composite_role VALUES ('e006a180-6555-42da-8ce4-5b1884af4358', '04ac9669-48fc-4999-83c7-bd5412f6aa66');
INSERT INTO public.composite_role VALUES ('e006a180-6555-42da-8ce4-5b1884af4358', 'f5303055-983d-43c8-9ee3-3622af2adf33');
INSERT INTO public.composite_role VALUES ('e006a180-6555-42da-8ce4-5b1884af4358', 'eb338b4e-88fd-42be-af16-afc5d2ab8168');
INSERT INTO public.composite_role VALUES ('e006a180-6555-42da-8ce4-5b1884af4358', '0f82fd27-8a72-4121-9f34-69866d4f4bb0');
INSERT INTO public.composite_role VALUES ('e006a180-6555-42da-8ce4-5b1884af4358', 'a36b483a-f284-454c-97d9-0fa3a6751ab5');
INSERT INTO public.composite_role VALUES ('e006a180-6555-42da-8ce4-5b1884af4358', 'b362e1f3-03a8-458d-a651-7de0eca5c457');
INSERT INTO public.composite_role VALUES ('e006a180-6555-42da-8ce4-5b1884af4358', '48b31ab0-5dd6-4615-ade9-44245f85f984');
INSERT INTO public.composite_role VALUES ('e006a180-6555-42da-8ce4-5b1884af4358', 'c517cb78-e15b-4f87-818a-fd0467400ebd');
INSERT INTO public.composite_role VALUES ('e006a180-6555-42da-8ce4-5b1884af4358', 'fb509b84-e094-4a9a-b7f3-75f8f151862d');
INSERT INTO public.composite_role VALUES ('4d0a91ec-ec17-4b0a-b9f5-bfb96acc3952', 'fb509b84-e094-4a9a-b7f3-75f8f151862d');
INSERT INTO public.composite_role VALUES ('4d0a91ec-ec17-4b0a-b9f5-bfb96acc3952', 'b362e1f3-03a8-458d-a651-7de0eca5c457');
INSERT INTO public.composite_role VALUES ('5f38f4cb-e789-4027-b0b2-2e5abba74677', '48b31ab0-5dd6-4615-ade9-44245f85f984');
INSERT INTO public.composite_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', '537bb0b6-11ad-4da4-b414-569f5e69fbd0');
INSERT INTO public.composite_role VALUES ('6d5bf9b9-4301-48f9-9c4e-ed890b0f7302', 'c035018c-aa29-4c02-af64-f33acf9240f5');
INSERT INTO public.composite_role VALUES ('e006a180-6555-42da-8ce4-5b1884af4358', 'ea80519a-a52f-4299-b4a5-711f7844709c');


--
-- TOC entry 4060 (class 0 OID 316693)
-- Dependencies: 203
-- Data for Name: credential; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.credential VALUES ('93055e3d-641f-49a1-8a0c-4cb13f7bd0dc', NULL, 27500, '\x5e8b9d15bbfc6d619a4ed0deaa8c3c54', 'password', 'NLvVgAphmMtNMS/GN2jA7chO5c5nrWnbn57zxlM1NSntVofvZsE5ROUL/ZuT4ZMB/n1mk7L4j6emhIWkIeGl/Q==', '37ce8dbb-14e2-4a9f-8bd8-a5b10a20a84d', 1554972125958, 0, 0, 0, 'pbkdf2-sha256');
INSERT INTO public.credential VALUES ('bb46a7a1-62d5-4717-a841-3b7c23113ce2', NULL, 27500, '\x009b858a4a0763cce12ad840038f80d3', 'password', 'fe/SkhKGuP3BGiorpPxvcAm/l5gLQNahwxIRShLnbPu+tE6T5TGeDfScJFzyMkO80lYwl4nW8I22vAUVY+yMPA==', 'd2f4dd75-57c7-4830-8797-f5bdfb406fac', 1554973877059, 0, 0, 0, 'pbkdf2-sha256');


--
-- TOC entry 4135 (class 0 OID 317880)
-- Dependencies: 278
-- Data for Name: credential_attribute; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4054 (class 0 OID 316660)
-- Dependencies: 197
-- Data for Name: databasechangelog; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.databasechangelog VALUES ('1.0.0.Final-KEYCLOAK-5461', 'sthorger@redhat.com', 'META-INF/jpa-changelog-1.0.0.Final.xml', '2019-04-11 10:40:25.374525', 1, 'EXECUTED', '7:4e70412f24a3f382c82183742ec79317', 'createTable tableName=APPLICATION_DEFAULT_ROLES; createTable tableName=CLIENT; createTable tableName=CLIENT_SESSION; createTable tableName=CLIENT_SESSION_ROLE; createTable tableName=COMPOSITE_ROLE; createTable tableName=CREDENTIAL; createTable tab...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('1.0.0.Final-KEYCLOAK-5461', 'sthorger@redhat.com', 'META-INF/db2-jpa-changelog-1.0.0.Final.xml', '2019-04-11 10:40:25.386216', 2, 'MARK_RAN', '7:cb16724583e9675711801c6875114f28', 'createTable tableName=APPLICATION_DEFAULT_ROLES; createTable tableName=CLIENT; createTable tableName=CLIENT_SESSION; createTable tableName=CLIENT_SESSION_ROLE; createTable tableName=COMPOSITE_ROLE; createTable tableName=CREDENTIAL; createTable tab...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('1.1.0.Beta1', 'sthorger@redhat.com', 'META-INF/jpa-changelog-1.1.0.Beta1.xml', '2019-04-11 10:40:25.417041', 3, 'EXECUTED', '7:0310eb8ba07cec616460794d42ade0fa', 'delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION; createTable tableName=CLIENT_ATTRIBUTES; createTable tableName=CLIENT_SESSION_NOTE; createTable tableName=APP_NODE_REGISTRATIONS; addColumn table...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('1.1.0.Final', 'sthorger@redhat.com', 'META-INF/jpa-changelog-1.1.0.Final.xml', '2019-04-11 10:40:25.420811', 4, 'EXECUTED', '7:5d25857e708c3233ef4439df1f93f012', 'renameColumn newColumnName=EVENT_TIME, oldColumnName=TIME, tableName=EVENT_ENTITY', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('1.2.0.Beta1', 'psilva@redhat.com', 'META-INF/jpa-changelog-1.2.0.Beta1.xml', '2019-04-11 10:40:25.486457', 5, 'EXECUTED', '7:c7a54a1041d58eb3817a4a883b4d4e84', 'delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION; createTable tableName=PROTOCOL_MAPPER; createTable tableName=PROTOCOL_MAPPER_CONFIG; createTable tableName=...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('1.2.0.Beta1', 'psilva@redhat.com', 'META-INF/db2-jpa-changelog-1.2.0.Beta1.xml', '2019-04-11 10:40:25.489648', 6, 'MARK_RAN', '7:2e01012df20974c1c2a605ef8afe25b7', 'delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION; createTable tableName=PROTOCOL_MAPPER; createTable tableName=PROTOCOL_MAPPER_CONFIG; createTable tableName=...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('1.2.0.RC1', 'bburke@redhat.com', 'META-INF/jpa-changelog-1.2.0.CR1.xml', '2019-04-11 10:40:25.546951', 7, 'EXECUTED', '7:0f08df48468428e0f30ee59a8ec01a41', 'delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete tableName=USER_SESSION; createTable tableName=MIGRATION_MODEL; createTable tableName=IDENTITY_P...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('1.2.0.RC1', 'bburke@redhat.com', 'META-INF/db2-jpa-changelog-1.2.0.CR1.xml', '2019-04-11 10:40:25.550433', 8, 'MARK_RAN', '7:a77ea2ad226b345e7d689d366f185c8c', 'delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete tableName=USER_SESSION; createTable tableName=MIGRATION_MODEL; createTable tableName=IDENTITY_P...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('1.2.0.Final', 'keycloak', 'META-INF/jpa-changelog-1.2.0.Final.xml', '2019-04-11 10:40:25.554731', 9, 'EXECUTED', '7:a3377a2059aefbf3b90ebb4c4cc8e2ab', 'update tableName=CLIENT; update tableName=CLIENT; update tableName=CLIENT', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('1.3.0', 'bburke@redhat.com', 'META-INF/jpa-changelog-1.3.0.xml', '2019-04-11 10:40:25.616048', 10, 'EXECUTED', '7:04c1dbedc2aa3e9756d1a1668e003451', 'delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_PROT_MAPPER; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete tableName=USER_SESSION; createTable tableName=ADMI...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('1.4.0', 'bburke@redhat.com', 'META-INF/jpa-changelog-1.4.0.xml', '2019-04-11 10:40:25.650007', 11, 'EXECUTED', '7:36ef39ed560ad07062d956db861042ba', 'delete tableName=CLIENT_SESSION_AUTH_STATUS; delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_PROT_MAPPER; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete table...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('1.4.0', 'bburke@redhat.com', 'META-INF/db2-jpa-changelog-1.4.0.xml', '2019-04-11 10:40:25.652312', 12, 'MARK_RAN', '7:d909180b2530479a716d3f9c9eaea3d7', 'delete tableName=CLIENT_SESSION_AUTH_STATUS; delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_PROT_MAPPER; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete table...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('1.5.0', 'bburke@redhat.com', 'META-INF/jpa-changelog-1.5.0.xml', '2019-04-11 10:40:25.665383', 13, 'EXECUTED', '7:cf12b04b79bea5152f165eb41f3955f6', 'delete tableName=CLIENT_SESSION_AUTH_STATUS; delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_PROT_MAPPER; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete table...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('1.6.1_from15', 'mposolda@redhat.com', 'META-INF/jpa-changelog-1.6.1.xml', '2019-04-11 10:40:25.680746', 14, 'EXECUTED', '7:7e32c8f05c755e8675764e7d5f514509', 'addColumn tableName=REALM; addColumn tableName=KEYCLOAK_ROLE; addColumn tableName=CLIENT; createTable tableName=OFFLINE_USER_SESSION; createTable tableName=OFFLINE_CLIENT_SESSION; addPrimaryKey constraintName=CONSTRAINT_OFFL_US_SES_PK2, tableName=...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('1.6.1_from16-pre', 'mposolda@redhat.com', 'META-INF/jpa-changelog-1.6.1.xml', '2019-04-11 10:40:25.682457', 15, 'MARK_RAN', '7:980ba23cc0ec39cab731ce903dd01291', 'delete tableName=OFFLINE_CLIENT_SESSION; delete tableName=OFFLINE_USER_SESSION', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('1.6.1_from16', 'mposolda@redhat.com', 'META-INF/jpa-changelog-1.6.1.xml', '2019-04-11 10:40:25.683718', 16, 'MARK_RAN', '7:2fa220758991285312eb84f3b4ff5336', 'dropPrimaryKey constraintName=CONSTRAINT_OFFLINE_US_SES_PK, tableName=OFFLINE_USER_SESSION; dropPrimaryKey constraintName=CONSTRAINT_OFFLINE_CL_SES_PK, tableName=OFFLINE_CLIENT_SESSION; addColumn tableName=OFFLINE_USER_SESSION; update tableName=OF...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('1.6.1', 'mposolda@redhat.com', 'META-INF/jpa-changelog-1.6.1.xml', '2019-04-11 10:40:25.684975', 17, 'EXECUTED', '7:d41d8cd98f00b204e9800998ecf8427e', 'empty', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('1.7.0', 'bburke@redhat.com', 'META-INF/jpa-changelog-1.7.0.xml', '2019-04-11 10:40:25.719038', 18, 'EXECUTED', '7:91ace540896df890cc00a0490ee52bbc', 'createTable tableName=KEYCLOAK_GROUP; createTable tableName=GROUP_ROLE_MAPPING; createTable tableName=GROUP_ATTRIBUTE; createTable tableName=USER_GROUP_MEMBERSHIP; createTable tableName=REALM_DEFAULT_GROUPS; addColumn tableName=IDENTITY_PROVIDER; ...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('1.8.0', 'mposolda@redhat.com', 'META-INF/jpa-changelog-1.8.0.xml', '2019-04-11 10:40:25.749418', 19, 'EXECUTED', '7:c31d1646dfa2618a9335c00e07f89f24', 'addColumn tableName=IDENTITY_PROVIDER; createTable tableName=CLIENT_TEMPLATE; createTable tableName=CLIENT_TEMPLATE_ATTRIBUTES; createTable tableName=TEMPLATE_SCOPE_MAPPING; dropNotNullConstraint columnName=CLIENT_ID, tableName=PROTOCOL_MAPPER; ad...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('1.8.0-2', 'keycloak', 'META-INF/jpa-changelog-1.8.0.xml', '2019-04-11 10:40:25.752181', 20, 'EXECUTED', '7:df8bc21027a4f7cbbb01f6344e89ce07', 'dropDefaultValue columnName=ALGORITHM, tableName=CREDENTIAL; update tableName=CREDENTIAL', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('authz-3.4.0.CR1-resource-server-pk-change-part1', 'glavoie@gmail.com', 'META-INF/jpa-changelog-authz-3.4.0.CR1.xml', '2019-04-11 10:40:25.992812', 45, 'EXECUTED', '7:6a48ce645a3525488a90fbf76adf3bb3', 'addColumn tableName=RESOURCE_SERVER_POLICY; addColumn tableName=RESOURCE_SERVER_RESOURCE; addColumn tableName=RESOURCE_SERVER_SCOPE', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('1.8.0', 'mposolda@redhat.com', 'META-INF/db2-jpa-changelog-1.8.0.xml', '2019-04-11 10:40:25.754404', 21, 'MARK_RAN', '7:f987971fe6b37d963bc95fee2b27f8df', 'addColumn tableName=IDENTITY_PROVIDER; createTable tableName=CLIENT_TEMPLATE; createTable tableName=CLIENT_TEMPLATE_ATTRIBUTES; createTable tableName=TEMPLATE_SCOPE_MAPPING; dropNotNullConstraint columnName=CLIENT_ID, tableName=PROTOCOL_MAPPER; ad...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('1.8.0-2', 'keycloak', 'META-INF/db2-jpa-changelog-1.8.0.xml', '2019-04-11 10:40:25.757311', 22, 'MARK_RAN', '7:df8bc21027a4f7cbbb01f6344e89ce07', 'dropDefaultValue columnName=ALGORITHM, tableName=CREDENTIAL; update tableName=CREDENTIAL', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('1.9.0', 'mposolda@redhat.com', 'META-INF/jpa-changelog-1.9.0.xml', '2019-04-11 10:40:25.771239', 23, 'EXECUTED', '7:ed2dc7f799d19ac452cbcda56c929e47', 'update tableName=REALM; update tableName=REALM; update tableName=REALM; update tableName=REALM; update tableName=CREDENTIAL; update tableName=CREDENTIAL; update tableName=CREDENTIAL; update tableName=REALM; update tableName=REALM; customChange; dr...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('1.9.1', 'keycloak', 'META-INF/jpa-changelog-1.9.1.xml', '2019-04-11 10:40:25.774547', 24, 'EXECUTED', '7:80b5db88a5dda36ece5f235be8757615', 'modifyDataType columnName=PRIVATE_KEY, tableName=REALM; modifyDataType columnName=PUBLIC_KEY, tableName=REALM; modifyDataType columnName=CERTIFICATE, tableName=REALM', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('1.9.1', 'keycloak', 'META-INF/db2-jpa-changelog-1.9.1.xml', '2019-04-11 10:40:25.775663', 25, 'MARK_RAN', '7:1437310ed1305a9b93f8848f301726ce', 'modifyDataType columnName=PRIVATE_KEY, tableName=REALM; modifyDataType columnName=CERTIFICATE, tableName=REALM', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('1.9.2', 'keycloak', 'META-INF/jpa-changelog-1.9.2.xml', '2019-04-11 10:40:25.7892', 26, 'EXECUTED', '7:b82ffb34850fa0836be16deefc6a87c4', 'createIndex indexName=IDX_USER_EMAIL, tableName=USER_ENTITY; createIndex indexName=IDX_USER_ROLE_MAPPING, tableName=USER_ROLE_MAPPING; createIndex indexName=IDX_USER_GROUP_MAPPING, tableName=USER_GROUP_MEMBERSHIP; createIndex indexName=IDX_USER_CO...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('authz-2.0.0', 'psilva@redhat.com', 'META-INF/jpa-changelog-authz-2.0.0.xml', '2019-04-11 10:40:25.836139', 27, 'EXECUTED', '7:9cc98082921330d8d9266decdd4bd658', 'createTable tableName=RESOURCE_SERVER; addPrimaryKey constraintName=CONSTRAINT_FARS, tableName=RESOURCE_SERVER; addUniqueConstraint constraintName=UK_AU8TT6T700S9V50BU18WS5HA6, tableName=RESOURCE_SERVER; createTable tableName=RESOURCE_SERVER_RESOU...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('authz-2.5.1', 'psilva@redhat.com', 'META-INF/jpa-changelog-authz-2.5.1.xml', '2019-04-11 10:40:25.839064', 28, 'EXECUTED', '7:03d64aeed9cb52b969bd30a7ac0db57e', 'update tableName=RESOURCE_SERVER_POLICY', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('2.1.0-KEYCLOAK-5461', 'bburke@redhat.com', 'META-INF/jpa-changelog-2.1.0.xml', '2019-04-11 10:40:25.8763', 29, 'EXECUTED', '7:f1f9fd8710399d725b780f463c6b21cd', 'createTable tableName=BROKER_LINK; createTable tableName=FED_USER_ATTRIBUTE; createTable tableName=FED_USER_CONSENT; createTable tableName=FED_USER_CONSENT_ROLE; createTable tableName=FED_USER_CONSENT_PROT_MAPPER; createTable tableName=FED_USER_CR...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('2.2.0', 'bburke@redhat.com', 'META-INF/jpa-changelog-2.2.0.xml', '2019-04-11 10:40:25.884809', 30, 'EXECUTED', '7:53188c3eb1107546e6f765835705b6c1', 'addColumn tableName=ADMIN_EVENT_ENTITY; createTable tableName=CREDENTIAL_ATTRIBUTE; createTable tableName=FED_CREDENTIAL_ATTRIBUTE; modifyDataType columnName=VALUE, tableName=CREDENTIAL; addForeignKeyConstraint baseTableName=FED_CREDENTIAL_ATTRIBU...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('2.3.0', 'bburke@redhat.com', 'META-INF/jpa-changelog-2.3.0.xml', '2019-04-11 10:40:25.894645', 31, 'EXECUTED', '7:d6e6f3bc57a0c5586737d1351725d4d4', 'createTable tableName=FEDERATED_USER; addPrimaryKey constraintName=CONSTR_FEDERATED_USER, tableName=FEDERATED_USER; dropDefaultValue columnName=TOTP, tableName=USER_ENTITY; dropColumn columnName=TOTP, tableName=USER_ENTITY; addColumn tableName=IDE...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('2.4.0', 'bburke@redhat.com', 'META-INF/jpa-changelog-2.4.0.xml', '2019-04-11 10:40:25.89762', 32, 'EXECUTED', '7:454d604fbd755d9df3fd9c6329043aa5', 'customChange', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('2.5.0', 'bburke@redhat.com', 'META-INF/jpa-changelog-2.5.0.xml', '2019-04-11 10:40:25.900681', 33, 'EXECUTED', '7:57e98a3077e29caf562f7dbf80c72600', 'customChange; modifyDataType columnName=USER_ID, tableName=OFFLINE_USER_SESSION', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('2.5.0-unicode-oracle', 'hmlnarik@redhat.com', 'META-INF/jpa-changelog-2.5.0.xml', '2019-04-11 10:40:25.90194', 34, 'MARK_RAN', '7:e4c7e8f2256210aee71ddc42f538b57a', 'modifyDataType columnName=DESCRIPTION, tableName=AUTHENTICATION_FLOW; modifyDataType columnName=DESCRIPTION, tableName=CLIENT_TEMPLATE; modifyDataType columnName=DESCRIPTION, tableName=RESOURCE_SERVER_POLICY; modifyDataType columnName=DESCRIPTION,...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('2.5.0-unicode-other-dbs', 'hmlnarik@redhat.com', 'META-INF/jpa-changelog-2.5.0.xml', '2019-04-11 10:40:25.918262', 35, 'EXECUTED', '7:09a43c97e49bc626460480aa1379b522', 'modifyDataType columnName=DESCRIPTION, tableName=AUTHENTICATION_FLOW; modifyDataType columnName=DESCRIPTION, tableName=CLIENT_TEMPLATE; modifyDataType columnName=DESCRIPTION, tableName=RESOURCE_SERVER_POLICY; modifyDataType columnName=DESCRIPTION,...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('2.5.0-duplicate-email-support', 'slawomir@dabek.name', 'META-INF/jpa-changelog-2.5.0.xml', '2019-04-11 10:40:25.921463', 36, 'EXECUTED', '7:26bfc7c74fefa9126f2ce702fb775553', 'addColumn tableName=REALM', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('2.5.0-unique-group-names', 'hmlnarik@redhat.com', 'META-INF/jpa-changelog-2.5.0.xml', '2019-04-11 10:40:25.924513', 37, 'EXECUTED', '7:a161e2ae671a9020fff61e996a207377', 'addUniqueConstraint constraintName=SIBLING_NAMES, tableName=KEYCLOAK_GROUP', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('2.5.1', 'bburke@redhat.com', 'META-INF/jpa-changelog-2.5.1.xml', '2019-04-11 10:40:25.926903', 38, 'EXECUTED', '7:37fc1781855ac5388c494f1442b3f717', 'addColumn tableName=FED_USER_CONSENT', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('3.0.0', 'bburke@redhat.com', 'META-INF/jpa-changelog-3.0.0.xml', '2019-04-11 10:40:25.929074', 39, 'EXECUTED', '7:13a27db0dae6049541136adad7261d27', 'addColumn tableName=IDENTITY_PROVIDER', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('3.2.0-fix', 'keycloak', 'META-INF/jpa-changelog-3.2.0.xml', '2019-04-11 10:40:25.930168', 40, 'MARK_RAN', '7:550300617e3b59e8af3a6294df8248a3', 'addNotNullConstraint columnName=REALM_ID, tableName=CLIENT_INITIAL_ACCESS', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('3.2.0-fix-with-keycloak-5416', 'keycloak', 'META-INF/jpa-changelog-3.2.0.xml', '2019-04-11 10:40:25.931338', 41, 'MARK_RAN', '7:e3a9482b8931481dc2772a5c07c44f17', 'dropIndex indexName=IDX_CLIENT_INIT_ACC_REALM, tableName=CLIENT_INITIAL_ACCESS; addNotNullConstraint columnName=REALM_ID, tableName=CLIENT_INITIAL_ACCESS; createIndex indexName=IDX_CLIENT_INIT_ACC_REALM, tableName=CLIENT_INITIAL_ACCESS', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('3.2.0-fix-offline-sessions', 'hmlnarik', 'META-INF/jpa-changelog-3.2.0.xml', '2019-04-11 10:40:25.935029', 42, 'EXECUTED', '7:72b07d85a2677cb257edb02b408f332d', 'customChange', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('3.2.0-fixed', 'keycloak', 'META-INF/jpa-changelog-3.2.0.xml', '2019-04-11 10:40:25.987991', 43, 'EXECUTED', '7:a72a7858967bd414835d19e04d880312', 'addColumn tableName=REALM; dropPrimaryKey constraintName=CONSTRAINT_OFFL_CL_SES_PK2, tableName=OFFLINE_CLIENT_SESSION; dropColumn columnName=CLIENT_SESSION_ID, tableName=OFFLINE_CLIENT_SESSION; addPrimaryKey constraintName=CONSTRAINT_OFFL_CL_SES_P...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('3.3.0', 'keycloak', 'META-INF/jpa-changelog-3.3.0.xml', '2019-04-11 10:40:25.990622', 44, 'EXECUTED', '7:94edff7cf9ce179e7e85f0cd78a3cf2c', 'addColumn tableName=USER_ENTITY', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('authz-3.4.0.CR1-resource-server-pk-change-part2-KEYCLOAK-6095', 'hmlnarik@redhat.com', 'META-INF/jpa-changelog-authz-3.4.0.CR1.xml', '2019-04-11 10:40:25.995589', 46, 'EXECUTED', '7:e64b5dcea7db06077c6e57d3b9e5ca14', 'customChange', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('authz-3.4.0.CR1-resource-server-pk-change-part3-fixed', 'glavoie@gmail.com', 'META-INF/jpa-changelog-authz-3.4.0.CR1.xml', '2019-04-11 10:40:25.996735', 47, 'MARK_RAN', '7:fd8cf02498f8b1e72496a20afc75178c', 'dropIndex indexName=IDX_RES_SERV_POL_RES_SERV, tableName=RESOURCE_SERVER_POLICY; dropIndex indexName=IDX_RES_SRV_RES_RES_SRV, tableName=RESOURCE_SERVER_RESOURCE; dropIndex indexName=IDX_RES_SRV_SCOPE_RES_SRV, tableName=RESOURCE_SERVER_SCOPE', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('authz-3.4.0.CR1-resource-server-pk-change-part3-fixed-nodropindex', 'glavoie@gmail.com', 'META-INF/jpa-changelog-authz-3.4.0.CR1.xml', '2019-04-11 10:40:26.015577', 48, 'EXECUTED', '7:542794f25aa2b1fbabb7e577d6646319', 'addNotNullConstraint columnName=RESOURCE_SERVER_CLIENT_ID, tableName=RESOURCE_SERVER_POLICY; addNotNullConstraint columnName=RESOURCE_SERVER_CLIENT_ID, tableName=RESOURCE_SERVER_RESOURCE; addNotNullConstraint columnName=RESOURCE_SERVER_CLIENT_ID, ...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('authn-3.4.0.CR1-refresh-token-max-reuse', 'glavoie@gmail.com', 'META-INF/jpa-changelog-authz-3.4.0.CR1.xml', '2019-04-11 10:40:26.018462', 49, 'EXECUTED', '7:edad604c882df12f74941dac3cc6d650', 'addColumn tableName=REALM', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('3.4.0', 'keycloak', 'META-INF/jpa-changelog-3.4.0.xml', '2019-04-11 10:40:26.036094', 50, 'EXECUTED', '7:0f88b78b7b46480eb92690cbf5e44900', 'addPrimaryKey constraintName=CONSTRAINT_REALM_DEFAULT_ROLES, tableName=REALM_DEFAULT_ROLES; addPrimaryKey constraintName=CONSTRAINT_COMPOSITE_ROLE, tableName=COMPOSITE_ROLE; addPrimaryKey constraintName=CONSTR_REALM_DEFAULT_GROUPS, tableName=REALM...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('3.4.0-KEYCLOAK-5230', 'hmlnarik@redhat.com', 'META-INF/jpa-changelog-3.4.0.xml', '2019-04-11 10:40:26.047273', 51, 'EXECUTED', '7:d560e43982611d936457c327f872dd59', 'createIndex indexName=IDX_FU_ATTRIBUTE, tableName=FED_USER_ATTRIBUTE; createIndex indexName=IDX_FU_CONSENT, tableName=FED_USER_CONSENT; createIndex indexName=IDX_FU_CONSENT_RU, tableName=FED_USER_CONSENT; createIndex indexName=IDX_FU_CREDENTIAL, t...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('3.4.1', 'psilva@redhat.com', 'META-INF/jpa-changelog-3.4.1.xml', '2019-04-11 10:40:26.048889', 52, 'EXECUTED', '7:c155566c42b4d14ef07059ec3b3bbd8e', 'modifyDataType columnName=VALUE, tableName=CLIENT_ATTRIBUTES', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('3.4.2', 'keycloak', 'META-INF/jpa-changelog-3.4.2.xml', '2019-04-11 10:40:26.050208', 53, 'EXECUTED', '7:b40376581f12d70f3c89ba8ddf5b7dea', 'update tableName=REALM', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('3.4.2-KEYCLOAK-5172', 'mkanis@redhat.com', 'META-INF/jpa-changelog-3.4.2.xml', '2019-04-11 10:40:26.051554', 54, 'EXECUTED', '7:a1132cc395f7b95b3646146c2e38f168', 'update tableName=CLIENT', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('4.0.0-KEYCLOAK-6335', 'bburke@redhat.com', 'META-INF/jpa-changelog-4.0.0.xml', '2019-04-11 10:40:26.054528', 55, 'EXECUTED', '7:d8dc5d89c789105cfa7ca0e82cba60af', 'createTable tableName=CLIENT_AUTH_FLOW_BINDINGS; addPrimaryKey constraintName=C_CLI_FLOW_BIND, tableName=CLIENT_AUTH_FLOW_BINDINGS', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('4.0.0-CLEANUP-UNUSED-TABLE', 'bburke@redhat.com', 'META-INF/jpa-changelog-4.0.0.xml', '2019-04-11 10:40:26.057486', 56, 'EXECUTED', '7:7822e0165097182e8f653c35517656a3', 'dropTable tableName=CLIENT_IDENTITY_PROV_MAPPING', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('4.0.0-KEYCLOAK-6228', 'bburke@redhat.com', 'META-INF/jpa-changelog-4.0.0.xml', '2019-04-11 10:40:26.067524', 57, 'EXECUTED', '7:c6538c29b9c9a08f9e9ea2de5c2b6375', 'dropUniqueConstraint constraintName=UK_JKUWUVD56ONTGSUHOGM8UEWRT, tableName=USER_CONSENT; dropNotNullConstraint columnName=CLIENT_ID, tableName=USER_CONSENT; addColumn tableName=USER_CONSENT; addUniqueConstraint constraintName=UK_JKUWUVD56ONTGSUHO...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('4.0.0-KEYCLOAK-5579-fixed', 'mposolda@redhat.com', 'META-INF/jpa-changelog-4.0.0.xml', '2019-04-11 10:40:26.11795', 58, 'EXECUTED', '7:6d4893e36de22369cf73bcb051ded875', 'dropForeignKeyConstraint baseTableName=CLIENT_TEMPLATE_ATTRIBUTES, constraintName=FK_CL_TEMPL_ATTR_TEMPL; renameTable newTableName=CLIENT_SCOPE_ATTRIBUTES, oldTableName=CLIENT_TEMPLATE_ATTRIBUTES; renameColumn newColumnName=SCOPE_ID, oldColumnName...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('authz-4.0.0.CR1', 'psilva@redhat.com', 'META-INF/jpa-changelog-authz-4.0.0.CR1.xml', '2019-04-11 10:40:26.132956', 59, 'EXECUTED', '7:57960fc0b0f0dd0563ea6f8b2e4a1707', 'createTable tableName=RESOURCE_SERVER_PERM_TICKET; addPrimaryKey constraintName=CONSTRAINT_FAPMT, tableName=RESOURCE_SERVER_PERM_TICKET; addForeignKeyConstraint baseTableName=RESOURCE_SERVER_PERM_TICKET, constraintName=FK_FRSRHO213XCX4WNKOG82SSPMT...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('authz-4.0.0.Beta3', 'psilva@redhat.com', 'META-INF/jpa-changelog-authz-4.0.0.Beta3.xml', '2019-04-11 10:40:26.136211', 60, 'EXECUTED', '7:2b4b8bff39944c7097977cc18dbceb3b', 'addColumn tableName=RESOURCE_SERVER_POLICY; addColumn tableName=RESOURCE_SERVER_PERM_TICKET; addForeignKeyConstraint baseTableName=RESOURCE_SERVER_PERM_TICKET, constraintName=FK_FRSRPO2128CX4WNKOG82SSRFY, referencedTableName=RESOURCE_SERVER_POLICY', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('authz-4.2.0.Final', 'mhajas@redhat.com', 'META-INF/jpa-changelog-authz-4.2.0.Final.xml', '2019-04-11 10:40:26.141307', 61, 'EXECUTED', '7:2aa42a964c59cd5b8ca9822340ba33a8', 'createTable tableName=RESOURCE_URIS; addForeignKeyConstraint baseTableName=RESOURCE_URIS, constraintName=FK_RESOURCE_SERVER_URIS, referencedTableName=RESOURCE_SERVER_RESOURCE; customChange; dropColumn columnName=URI, tableName=RESOURCE_SERVER_RESO...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('4.2.0-KEYCLOAK-6313', 'wadahiro@gmail.com', 'META-INF/jpa-changelog-4.2.0.xml', '2019-04-11 10:40:26.142984', 62, 'EXECUTED', '7:14d407c35bc4fe1976867756bcea0c36', 'addColumn tableName=REQUIRED_ACTION_PROVIDER', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('4.3.0-KEYCLOAK-7984', 'wadahiro@gmail.com', 'META-INF/jpa-changelog-4.3.0.xml', '2019-04-11 10:40:26.144402', 63, 'EXECUTED', '7:241a8030c748c8548e346adee548fa93', 'update tableName=REQUIRED_ACTION_PROVIDER', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('4.6.0-KEYCLOAK-7950', 'psilva@redhat.com', 'META-INF/jpa-changelog-4.6.0.xml', '2019-04-11 10:40:26.145849', 64, 'EXECUTED', '7:7d3182f65a34fcc61e8d23def037dc3f', 'update tableName=RESOURCE_SERVER_RESOURCE', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('4.6.0-KEYCLOAK-8377', 'keycloak', 'META-INF/jpa-changelog-4.6.0.xml', '2019-04-11 10:40:26.151788', 65, 'EXECUTED', '7:b30039e00a0b9715d430d1b0636728fa', 'createTable tableName=ROLE_ATTRIBUTE; addPrimaryKey constraintName=CONSTRAINT_ROLE_ATTRIBUTE_PK, tableName=ROLE_ATTRIBUTE; addForeignKeyConstraint baseTableName=ROLE_ATTRIBUTE, constraintName=FK_ROLE_ATTRIBUTE_ID, referencedTableName=KEYCLOAK_ROLE...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('4.6.0-KEYCLOAK-8555', 'gideonray@gmail.com', 'META-INF/jpa-changelog-4.6.0.xml', '2019-04-11 10:40:26.154062', 66, 'EXECUTED', '7:3797315ca61d531780f8e6f82f258159', 'createIndex indexName=IDX_COMPONENT_PROVIDER_TYPE, tableName=COMPONENT', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('4.7.0-KEYCLOAK-1267', 'sguilhen@redhat.com', 'META-INF/jpa-changelog-4.7.0.xml', '2019-04-11 10:40:26.156967', 67, 'EXECUTED', '7:c7aa4c8d9573500c2d347c1941ff0301', 'addColumn tableName=REALM', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('4.7.0-KEYCLOAK-7275', 'keycloak', 'META-INF/jpa-changelog-4.7.0.xml', '2019-04-11 10:40:26.162543', 68, 'EXECUTED', '7:b207faee394fc074a442ecd42185a5dd', 'renameColumn newColumnName=CREATED_ON, oldColumnName=LAST_SESSION_REFRESH, tableName=OFFLINE_USER_SESSION; addNotNullConstraint columnName=CREATED_ON, tableName=OFFLINE_USER_SESSION; addColumn tableName=OFFLINE_USER_SESSION; customChange; createIn...', '', NULL, '3.5.4', NULL, NULL, '4972025135');
INSERT INTO public.databasechangelog VALUES ('4.8.0-KEYCLOAK-8835', 'sguilhen@redhat.com', 'META-INF/jpa-changelog-4.8.0.xml', '2019-04-11 10:40:26.16548', 69, 'EXECUTED', '7:ab9a9762faaba4ddfa35514b212c4922', 'addNotNullConstraint columnName=SSO_MAX_LIFESPAN_REMEMBER_ME, tableName=REALM; addNotNullConstraint columnName=SSO_IDLE_TIMEOUT_REMEMBER_ME, tableName=REALM', '', NULL, '3.5.4', NULL, NULL, '4972025135');


--
-- TOC entry 4053 (class 0 OID 316655)
-- Dependencies: 196
-- Data for Name: databasechangeloglock; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.databasechangeloglock VALUES (1, false, NULL, NULL);


--
-- TOC entry 4141 (class 0 OID 318109)
-- Dependencies: 284
-- Data for Name: default_client_scope; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.default_client_scope VALUES ('master', 'ee9315ac-d6f2-4218-9faf-b0f4e7c43824', false);
INSERT INTO public.default_client_scope VALUES ('master', 'e1a81046-9411-4c04-b0dc-a530479c3a9c', true);
INSERT INTO public.default_client_scope VALUES ('master', '9ecfa9d2-2b77-4e00-990f-fe3519cd3d05', true);
INSERT INTO public.default_client_scope VALUES ('master', 'c7c3febd-5e51-4525-85c5-313f049798e5', true);
INSERT INTO public.default_client_scope VALUES ('master', 'b9496d2f-2730-439d-9001-b9a652bc9a5c', false);
INSERT INTO public.default_client_scope VALUES ('master', 'ed12965d-49ed-4f59-b557-308ff1b476cf', false);
INSERT INTO public.default_client_scope VALUES ('master', 'fe8ade79-8575-4f4e-ab22-61332ab02e91', true);
INSERT INTO public.default_client_scope VALUES ('master', '8c04b7a5-27be-494e-8773-a7284a101027', true);
INSERT INTO public.default_client_scope VALUES ('demo', 'bcf4191f-bee6-4d62-b804-4d9683a6c557', false);
INSERT INTO public.default_client_scope VALUES ('demo', 'c553a264-596a-4375-9cf7-a834cbbc47c4', true);
INSERT INTO public.default_client_scope VALUES ('demo', 'f61ea2ca-9d62-4637-b30f-6489424ee38b', true);
INSERT INTO public.default_client_scope VALUES ('demo', '6c3cedb0-cc84-4048-8837-35ae16ea57a2', true);
INSERT INTO public.default_client_scope VALUES ('demo', 'cac547c4-a336-4323-b85f-b3218c7580f7', false);
INSERT INTO public.default_client_scope VALUES ('demo', '93b96680-d750-4291-b92d-62179f81d1f9', false);
INSERT INTO public.default_client_scope VALUES ('demo', 'f95d6362-5078-44ad-87cd-8e9295621032', true);
INSERT INTO public.default_client_scope VALUES ('demo', 'dd11bbfe-31e3-42a0-a167-efe527455b2c', true);


--
-- TOC entry 4061 (class 0 OID 316699)
-- Dependencies: 204
-- Data for Name: event_entity; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4136 (class 0 OID 317886)
-- Dependencies: 279
-- Data for Name: fed_credential_attribute; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4127 (class 0 OID 317798)
-- Dependencies: 270
-- Data for Name: fed_user_attribute; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4128 (class 0 OID 317804)
-- Dependencies: 271
-- Data for Name: fed_user_consent; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4143 (class 0 OID 318135)
-- Dependencies: 286
-- Data for Name: fed_user_consent_cl_scope; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4129 (class 0 OID 317813)
-- Dependencies: 272
-- Data for Name: fed_user_credential; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4130 (class 0 OID 317823)
-- Dependencies: 273
-- Data for Name: fed_user_group_membership; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4131 (class 0 OID 317826)
-- Dependencies: 274
-- Data for Name: fed_user_required_action; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4132 (class 0 OID 317833)
-- Dependencies: 275
-- Data for Name: fed_user_role_mapping; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4085 (class 0 OID 317093)
-- Dependencies: 228
-- Data for Name: federated_identity; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4137 (class 0 OID 317902)
-- Dependencies: 280
-- Data for Name: federated_user; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4111 (class 0 OID 317512)
-- Dependencies: 254
-- Data for Name: group_attribute; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4110 (class 0 OID 317509)
-- Dependencies: 253
-- Data for Name: group_role_mapping; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4086 (class 0 OID 317099)
-- Dependencies: 229
-- Data for Name: identity_provider; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4087 (class 0 OID 317109)
-- Dependencies: 230
-- Data for Name: identity_provider_config; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4092 (class 0 OID 317215)
-- Dependencies: 235
-- Data for Name: identity_provider_mapper; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4093 (class 0 OID 317221)
-- Dependencies: 236
-- Data for Name: idp_mapper_config; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4109 (class 0 OID 317506)
-- Dependencies: 252
-- Data for Name: keycloak_group; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4062 (class 0 OID 316708)
-- Dependencies: 205
-- Data for Name: keycloak_role; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.keycloak_role VALUES ('ba03e603-2915-4466-a581-491b32229a7e', 'master', false, '${role_admin}', 'admin', 'master', NULL, 'master');
INSERT INTO public.keycloak_role VALUES ('a26e33d6-9cc1-4fdc-999d-f3119e6b5eea', 'master', false, '${role_create-realm}', 'create-realm', 'master', NULL, 'master');
INSERT INTO public.keycloak_role VALUES ('cf48baa1-8dde-4fae-bbfa-66945e61d84b', '294184bd-4645-4042-991d-aea04faace2f', true, '${role_create-client}', 'create-client', 'master', '294184bd-4645-4042-991d-aea04faace2f', NULL);
INSERT INTO public.keycloak_role VALUES ('a89fc342-e69c-40b6-b6ca-dd20da0267c7', '294184bd-4645-4042-991d-aea04faace2f', true, '${role_view-realm}', 'view-realm', 'master', '294184bd-4645-4042-991d-aea04faace2f', NULL);
INSERT INTO public.keycloak_role VALUES ('9403e3f1-fdc0-4b33-8ae0-72f36deecbe8', '294184bd-4645-4042-991d-aea04faace2f', true, '${role_view-users}', 'view-users', 'master', '294184bd-4645-4042-991d-aea04faace2f', NULL);
INSERT INTO public.keycloak_role VALUES ('afda1459-20c2-4aed-bf0e-71d0c6c04c81', '294184bd-4645-4042-991d-aea04faace2f', true, '${role_view-clients}', 'view-clients', 'master', '294184bd-4645-4042-991d-aea04faace2f', NULL);
INSERT INTO public.keycloak_role VALUES ('2991aa72-4198-4818-8258-1aa0700e356c', '294184bd-4645-4042-991d-aea04faace2f', true, '${role_view-events}', 'view-events', 'master', '294184bd-4645-4042-991d-aea04faace2f', NULL);
INSERT INTO public.keycloak_role VALUES ('4f6d36c5-349b-48ff-9c90-e2922bb7638a', '294184bd-4645-4042-991d-aea04faace2f', true, '${role_view-identity-providers}', 'view-identity-providers', 'master', '294184bd-4645-4042-991d-aea04faace2f', NULL);
INSERT INTO public.keycloak_role VALUES ('55d7871d-52f0-4959-a1d3-fad60d8854a5', '294184bd-4645-4042-991d-aea04faace2f', true, '${role_view-authorization}', 'view-authorization', 'master', '294184bd-4645-4042-991d-aea04faace2f', NULL);
INSERT INTO public.keycloak_role VALUES ('190b4ad1-798d-46af-bc63-632711a6f7c4', '294184bd-4645-4042-991d-aea04faace2f', true, '${role_manage-realm}', 'manage-realm', 'master', '294184bd-4645-4042-991d-aea04faace2f', NULL);
INSERT INTO public.keycloak_role VALUES ('bfb016ee-7391-478d-9ccc-b58f4dfaef1d', '294184bd-4645-4042-991d-aea04faace2f', true, '${role_manage-users}', 'manage-users', 'master', '294184bd-4645-4042-991d-aea04faace2f', NULL);
INSERT INTO public.keycloak_role VALUES ('ac97d4c9-5836-4b05-a99c-7f89400b66ef', '294184bd-4645-4042-991d-aea04faace2f', true, '${role_manage-clients}', 'manage-clients', 'master', '294184bd-4645-4042-991d-aea04faace2f', NULL);
INSERT INTO public.keycloak_role VALUES ('10fc3ed9-a801-4ab6-bbfb-9ac9ae1239fc', '294184bd-4645-4042-991d-aea04faace2f', true, '${role_manage-events}', 'manage-events', 'master', '294184bd-4645-4042-991d-aea04faace2f', NULL);
INSERT INTO public.keycloak_role VALUES ('e58cf422-555a-4769-92f9-133ae477beee', '294184bd-4645-4042-991d-aea04faace2f', true, '${role_manage-identity-providers}', 'manage-identity-providers', 'master', '294184bd-4645-4042-991d-aea04faace2f', NULL);
INSERT INTO public.keycloak_role VALUES ('10a609e7-29d5-454e-9174-6da10b4d51cb', '294184bd-4645-4042-991d-aea04faace2f', true, '${role_manage-authorization}', 'manage-authorization', 'master', '294184bd-4645-4042-991d-aea04faace2f', NULL);
INSERT INTO public.keycloak_role VALUES ('22fa3a40-17d0-4b37-aae1-6e5cae83211a', '294184bd-4645-4042-991d-aea04faace2f', true, '${role_query-users}', 'query-users', 'master', '294184bd-4645-4042-991d-aea04faace2f', NULL);
INSERT INTO public.keycloak_role VALUES ('66af8969-9172-4293-b1c0-127466ec67da', '294184bd-4645-4042-991d-aea04faace2f', true, '${role_query-clients}', 'query-clients', 'master', '294184bd-4645-4042-991d-aea04faace2f', NULL);
INSERT INTO public.keycloak_role VALUES ('d59fa34d-5a44-4e46-a7b4-442b7b529954', '294184bd-4645-4042-991d-aea04faace2f', true, '${role_query-realms}', 'query-realms', 'master', '294184bd-4645-4042-991d-aea04faace2f', NULL);
INSERT INTO public.keycloak_role VALUES ('47d31a90-8a14-41d5-a737-b8b446340c5b', '294184bd-4645-4042-991d-aea04faace2f', true, '${role_query-groups}', 'query-groups', 'master', '294184bd-4645-4042-991d-aea04faace2f', NULL);
INSERT INTO public.keycloak_role VALUES ('daf57b44-dc1a-4d08-9c40-a36987dea994', 'e188d744-175c-4fc7-aba3-0f85c9717119', true, '${role_view-profile}', 'view-profile', 'master', 'e188d744-175c-4fc7-aba3-0f85c9717119', NULL);
INSERT INTO public.keycloak_role VALUES ('972bfb5d-ff9c-46c6-8a35-897dfb2b961c', 'e188d744-175c-4fc7-aba3-0f85c9717119', true, '${role_manage-account}', 'manage-account', 'master', 'e188d744-175c-4fc7-aba3-0f85c9717119', NULL);
INSERT INTO public.keycloak_role VALUES ('0f5571ca-a11c-4b5d-a0a8-99e7efb80c71', 'e188d744-175c-4fc7-aba3-0f85c9717119', true, '${role_manage-account-links}', 'manage-account-links', 'master', 'e188d744-175c-4fc7-aba3-0f85c9717119', NULL);
INSERT INTO public.keycloak_role VALUES ('e4681338-33a2-42c4-a6da-454e47e0dd2e', '89124cff-2ab2-4dda-bbd3-f34f7223b14c', true, '${role_read-token}', 'read-token', 'master', '89124cff-2ab2-4dda-bbd3-f34f7223b14c', NULL);
INSERT INTO public.keycloak_role VALUES ('39c78d32-5da9-485a-99be-c7eec7552d63', '294184bd-4645-4042-991d-aea04faace2f', true, '${role_impersonation}', 'impersonation', 'master', '294184bd-4645-4042-991d-aea04faace2f', NULL);
INSERT INTO public.keycloak_role VALUES ('a7d642a2-bc13-49ed-aacc-b87f1a0d5b3a', 'master', false, '${role_offline-access}', 'offline_access', 'master', NULL, 'master');
INSERT INTO public.keycloak_role VALUES ('4195fcd6-6e49-42bb-8086-87f47a896950', 'master', false, '${role_uma_authorization}', 'uma_authorization', 'master', NULL, 'master');
INSERT INTO public.keycloak_role VALUES ('92d9cce1-8d60-43d3-aa0d-f5bc5bc2796b', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', true, '${role_create-client}', 'create-client', 'master', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', NULL);
INSERT INTO public.keycloak_role VALUES ('833dd08e-faad-49e6-bdc2-1f34338e2175', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', true, '${role_view-realm}', 'view-realm', 'master', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', NULL);
INSERT INTO public.keycloak_role VALUES ('1d69f503-4d1a-4af9-a3d5-551bc1ff51c9', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', true, '${role_view-users}', 'view-users', 'master', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', NULL);
INSERT INTO public.keycloak_role VALUES ('023f745f-04da-43b5-8825-0e76cace5215', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', true, '${role_view-clients}', 'view-clients', 'master', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', NULL);
INSERT INTO public.keycloak_role VALUES ('57df4bb3-97cd-4e41-9d3a-c0cc4dc59aa1', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', true, '${role_view-events}', 'view-events', 'master', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', NULL);
INSERT INTO public.keycloak_role VALUES ('8e279fa9-cedb-4684-af66-cb822fe8f21b', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', true, '${role_view-identity-providers}', 'view-identity-providers', 'master', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', NULL);
INSERT INTO public.keycloak_role VALUES ('dfce2376-907f-4ecd-94fb-691969f78e23', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', true, '${role_view-authorization}', 'view-authorization', 'master', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', NULL);
INSERT INTO public.keycloak_role VALUES ('7f023fd4-fd5a-4a27-9a30-dbafdad77b13', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', true, '${role_manage-realm}', 'manage-realm', 'master', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', NULL);
INSERT INTO public.keycloak_role VALUES ('2a5d6fde-5edd-4521-b310-b0f1aecf0ce5', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', true, '${role_manage-users}', 'manage-users', 'master', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', NULL);
INSERT INTO public.keycloak_role VALUES ('1e130064-5899-4e10-8fa1-982de2a564a5', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', true, '${role_manage-clients}', 'manage-clients', 'master', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', NULL);
INSERT INTO public.keycloak_role VALUES ('05e58b6f-3937-4968-a0dc-9f66207a3398', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', true, '${role_manage-events}', 'manage-events', 'master', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', NULL);
INSERT INTO public.keycloak_role VALUES ('23f7628d-3f9e-4aac-ad6a-1e6ab5f1d184', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', true, '${role_manage-identity-providers}', 'manage-identity-providers', 'master', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', NULL);
INSERT INTO public.keycloak_role VALUES ('19d4497c-6697-4f06-987e-364b25505b96', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', true, '${role_manage-authorization}', 'manage-authorization', 'master', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', NULL);
INSERT INTO public.keycloak_role VALUES ('fb81f904-e548-4464-9f95-e62c77b6750a', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', true, '${role_query-users}', 'query-users', 'master', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', NULL);
INSERT INTO public.keycloak_role VALUES ('170f2d33-ef40-4c8b-b60f-371d0626e4d6', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', true, '${role_query-clients}', 'query-clients', 'master', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', NULL);
INSERT INTO public.keycloak_role VALUES ('2f43bb08-60aa-4575-8008-c19abacecc81', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', true, '${role_query-realms}', 'query-realms', 'master', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', NULL);
INSERT INTO public.keycloak_role VALUES ('acdce3ee-eb47-461a-8570-a56c97b0b5ec', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', true, '${role_query-groups}', 'query-groups', 'master', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', NULL);
INSERT INTO public.keycloak_role VALUES ('e006a180-6555-42da-8ce4-5b1884af4358', 'c5214889-aca7-4b88-ae04-d7ea2d778989', true, '${role_realm-admin}', 'realm-admin', 'demo', 'c5214889-aca7-4b88-ae04-d7ea2d778989', NULL);
INSERT INTO public.keycloak_role VALUES ('ba9d76fd-6f7b-40db-94c2-16e4164c7832', 'c5214889-aca7-4b88-ae04-d7ea2d778989', true, '${role_create-client}', 'create-client', 'demo', 'c5214889-aca7-4b88-ae04-d7ea2d778989', NULL);
INSERT INTO public.keycloak_role VALUES ('08379739-df9f-42d9-9c2b-7536e88b4525', 'c5214889-aca7-4b88-ae04-d7ea2d778989', true, '${role_view-realm}', 'view-realm', 'demo', 'c5214889-aca7-4b88-ae04-d7ea2d778989', NULL);
INSERT INTO public.keycloak_role VALUES ('4d0a91ec-ec17-4b0a-b9f5-bfb96acc3952', 'c5214889-aca7-4b88-ae04-d7ea2d778989', true, '${role_view-users}', 'view-users', 'demo', 'c5214889-aca7-4b88-ae04-d7ea2d778989', NULL);
INSERT INTO public.keycloak_role VALUES ('5f38f4cb-e789-4027-b0b2-2e5abba74677', 'c5214889-aca7-4b88-ae04-d7ea2d778989', true, '${role_view-clients}', 'view-clients', 'demo', 'c5214889-aca7-4b88-ae04-d7ea2d778989', NULL);
INSERT INTO public.keycloak_role VALUES ('ab991d94-c8b5-49db-8404-539412e86e21', 'c5214889-aca7-4b88-ae04-d7ea2d778989', true, '${role_view-events}', 'view-events', 'demo', 'c5214889-aca7-4b88-ae04-d7ea2d778989', NULL);
INSERT INTO public.keycloak_role VALUES ('fc3cae74-08b5-4d3f-8a84-603d581d1cbb', 'c5214889-aca7-4b88-ae04-d7ea2d778989', true, '${role_view-identity-providers}', 'view-identity-providers', 'demo', 'c5214889-aca7-4b88-ae04-d7ea2d778989', NULL);
INSERT INTO public.keycloak_role VALUES ('ef5fcad2-4823-44aa-848c-d4989f3156bc', 'c5214889-aca7-4b88-ae04-d7ea2d778989', true, '${role_view-authorization}', 'view-authorization', 'demo', 'c5214889-aca7-4b88-ae04-d7ea2d778989', NULL);
INSERT INTO public.keycloak_role VALUES ('a5b1adf4-4616-4ccd-a4b1-1557f9f0d9b3', 'c5214889-aca7-4b88-ae04-d7ea2d778989', true, '${role_manage-realm}', 'manage-realm', 'demo', 'c5214889-aca7-4b88-ae04-d7ea2d778989', NULL);
INSERT INTO public.keycloak_role VALUES ('04ac9669-48fc-4999-83c7-bd5412f6aa66', 'c5214889-aca7-4b88-ae04-d7ea2d778989', true, '${role_manage-users}', 'manage-users', 'demo', 'c5214889-aca7-4b88-ae04-d7ea2d778989', NULL);
INSERT INTO public.keycloak_role VALUES ('f5303055-983d-43c8-9ee3-3622af2adf33', 'c5214889-aca7-4b88-ae04-d7ea2d778989', true, '${role_manage-clients}', 'manage-clients', 'demo', 'c5214889-aca7-4b88-ae04-d7ea2d778989', NULL);
INSERT INTO public.keycloak_role VALUES ('eb338b4e-88fd-42be-af16-afc5d2ab8168', 'c5214889-aca7-4b88-ae04-d7ea2d778989', true, '${role_manage-events}', 'manage-events', 'demo', 'c5214889-aca7-4b88-ae04-d7ea2d778989', NULL);
INSERT INTO public.keycloak_role VALUES ('0f82fd27-8a72-4121-9f34-69866d4f4bb0', 'c5214889-aca7-4b88-ae04-d7ea2d778989', true, '${role_manage-identity-providers}', 'manage-identity-providers', 'demo', 'c5214889-aca7-4b88-ae04-d7ea2d778989', NULL);
INSERT INTO public.keycloak_role VALUES ('a36b483a-f284-454c-97d9-0fa3a6751ab5', 'c5214889-aca7-4b88-ae04-d7ea2d778989', true, '${role_manage-authorization}', 'manage-authorization', 'demo', 'c5214889-aca7-4b88-ae04-d7ea2d778989', NULL);
INSERT INTO public.keycloak_role VALUES ('b362e1f3-03a8-458d-a651-7de0eca5c457', 'c5214889-aca7-4b88-ae04-d7ea2d778989', true, '${role_query-users}', 'query-users', 'demo', 'c5214889-aca7-4b88-ae04-d7ea2d778989', NULL);
INSERT INTO public.keycloak_role VALUES ('48b31ab0-5dd6-4615-ade9-44245f85f984', 'c5214889-aca7-4b88-ae04-d7ea2d778989', true, '${role_query-clients}', 'query-clients', 'demo', 'c5214889-aca7-4b88-ae04-d7ea2d778989', NULL);
INSERT INTO public.keycloak_role VALUES ('c517cb78-e15b-4f87-818a-fd0467400ebd', 'c5214889-aca7-4b88-ae04-d7ea2d778989', true, '${role_query-realms}', 'query-realms', 'demo', 'c5214889-aca7-4b88-ae04-d7ea2d778989', NULL);
INSERT INTO public.keycloak_role VALUES ('fb509b84-e094-4a9a-b7f3-75f8f151862d', 'c5214889-aca7-4b88-ae04-d7ea2d778989', true, '${role_query-groups}', 'query-groups', 'demo', 'c5214889-aca7-4b88-ae04-d7ea2d778989', NULL);
INSERT INTO public.keycloak_role VALUES ('56d349f4-4b88-4986-b691-717ef57aa2d3', '303fe57e-df0c-4707-85a8-0537bf566861', true, '${role_view-profile}', 'view-profile', 'demo', '303fe57e-df0c-4707-85a8-0537bf566861', NULL);
INSERT INTO public.keycloak_role VALUES ('6d5bf9b9-4301-48f9-9c4e-ed890b0f7302', '303fe57e-df0c-4707-85a8-0537bf566861', true, '${role_manage-account}', 'manage-account', 'demo', '303fe57e-df0c-4707-85a8-0537bf566861', NULL);
INSERT INTO public.keycloak_role VALUES ('c035018c-aa29-4c02-af64-f33acf9240f5', '303fe57e-df0c-4707-85a8-0537bf566861', true, '${role_manage-account-links}', 'manage-account-links', 'demo', '303fe57e-df0c-4707-85a8-0537bf566861', NULL);
INSERT INTO public.keycloak_role VALUES ('537bb0b6-11ad-4da4-b414-569f5e69fbd0', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', true, '${role_impersonation}', 'impersonation', 'master', 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', NULL);
INSERT INTO public.keycloak_role VALUES ('ea80519a-a52f-4299-b4a5-711f7844709c', 'c5214889-aca7-4b88-ae04-d7ea2d778989', true, '${role_impersonation}', 'impersonation', 'demo', 'c5214889-aca7-4b88-ae04-d7ea2d778989', NULL);
INSERT INTO public.keycloak_role VALUES ('87a129f4-f671-408a-a65e-333a1e41ab26', 'dc10b4ee-59f3-4f66-b140-8203afe8acb1', true, '${role_read-token}', 'read-token', 'demo', 'dc10b4ee-59f3-4f66-b140-8203afe8acb1', NULL);
INSERT INTO public.keycloak_role VALUES ('8bcb18d5-4c73-422b-be0c-037077c71287', 'demo', false, '${role_offline-access}', 'offline_access', 'demo', NULL, 'demo');
INSERT INTO public.keycloak_role VALUES ('b4547d26-237d-4100-8c80-377acd01f929', 'demo', false, '${role_uma_authorization}', 'uma_authorization', 'demo', NULL, 'demo');
INSERT INTO public.keycloak_role VALUES ('41f0087b-dca9-4c57-b047-625bb23edda6', 'demo', false, NULL, 'admin', 'demo', NULL, 'demo');
INSERT INTO public.keycloak_role VALUES ('34156f64-2937-4c35-870b-5f030d3469b0', 'demo', false, NULL, 'kiemgmt', 'demo', NULL, 'demo');
INSERT INTO public.keycloak_role VALUES ('bb3aa479-7ff1-419d-aeef-6bcf70bce70f', 'demo', false, NULL, 'rest-all', 'demo', NULL, 'demo');


--
-- TOC entry 4091 (class 0 OID 317212)
-- Dependencies: 234
-- Data for Name: migration_model; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.migration_model VALUES ('SINGLETON', '4.6.0');


--
-- TOC entry 4108 (class 0 OID 317496)
-- Dependencies: 251
-- Data for Name: offline_client_session; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4107 (class 0 OID 317490)
-- Dependencies: 250
-- Data for Name: offline_user_session; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4121 (class 0 OID 317719)
-- Dependencies: 264
-- Data for Name: policy_config; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4083 (class 0 OID 317080)
-- Dependencies: 226
-- Data for Name: protocol_mapper; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.protocol_mapper VALUES ('bff89aff-ad10-4eda-bf3d-38d3b13f73ea', 'locale', 'openid-connect', 'oidc-usermodel-attribute-mapper', '05d6ae91-d774-40ab-b49f-7477ad5b272b', NULL);
INSERT INTO public.protocol_mapper VALUES ('0aa8c5fc-ff2e-4616-8773-23cd6848f061', 'role list', 'saml', 'saml-role-list-mapper', NULL, 'e1a81046-9411-4c04-b0dc-a530479c3a9c');
INSERT INTO public.protocol_mapper VALUES ('2d4b58d4-7f37-4c25-92c7-ed146e0920dd', 'full name', 'openid-connect', 'oidc-full-name-mapper', NULL, '9ecfa9d2-2b77-4e00-990f-fe3519cd3d05');
INSERT INTO public.protocol_mapper VALUES ('d4774763-84d9-40d6-a887-1656770b05a2', 'family name', 'openid-connect', 'oidc-usermodel-property-mapper', NULL, '9ecfa9d2-2b77-4e00-990f-fe3519cd3d05');
INSERT INTO public.protocol_mapper VALUES ('8cfffa99-552c-4bcd-9373-3112b3f33ec1', 'given name', 'openid-connect', 'oidc-usermodel-property-mapper', NULL, '9ecfa9d2-2b77-4e00-990f-fe3519cd3d05');
INSERT INTO public.protocol_mapper VALUES ('20bbf7f1-68a6-48eb-b394-5a3f1452fa3d', 'middle name', 'openid-connect', 'oidc-usermodel-attribute-mapper', NULL, '9ecfa9d2-2b77-4e00-990f-fe3519cd3d05');
INSERT INTO public.protocol_mapper VALUES ('af034674-aaca-485e-bdbc-fab2e84904a4', 'nickname', 'openid-connect', 'oidc-usermodel-attribute-mapper', NULL, '9ecfa9d2-2b77-4e00-990f-fe3519cd3d05');
INSERT INTO public.protocol_mapper VALUES ('c81a5ba8-6068-46b4-963a-43212e5f0ef3', 'username', 'openid-connect', 'oidc-usermodel-property-mapper', NULL, '9ecfa9d2-2b77-4e00-990f-fe3519cd3d05');
INSERT INTO public.protocol_mapper VALUES ('9622b613-737f-4676-942e-3bc465f386ed', 'profile', 'openid-connect', 'oidc-usermodel-attribute-mapper', NULL, '9ecfa9d2-2b77-4e00-990f-fe3519cd3d05');
INSERT INTO public.protocol_mapper VALUES ('92efb932-947d-4d21-a4e9-0f4da65f386e', 'picture', 'openid-connect', 'oidc-usermodel-attribute-mapper', NULL, '9ecfa9d2-2b77-4e00-990f-fe3519cd3d05');
INSERT INTO public.protocol_mapper VALUES ('7dfb6670-5b65-4d0e-ac72-b7c9ba732ade', 'website', 'openid-connect', 'oidc-usermodel-attribute-mapper', NULL, '9ecfa9d2-2b77-4e00-990f-fe3519cd3d05');
INSERT INTO public.protocol_mapper VALUES ('d3d3a39a-a7a1-4d8e-829c-3a8d11972911', 'gender', 'openid-connect', 'oidc-usermodel-attribute-mapper', NULL, '9ecfa9d2-2b77-4e00-990f-fe3519cd3d05');
INSERT INTO public.protocol_mapper VALUES ('2ef94c94-5784-4dfc-9af7-273290dbd5a2', 'birthdate', 'openid-connect', 'oidc-usermodel-attribute-mapper', NULL, '9ecfa9d2-2b77-4e00-990f-fe3519cd3d05');
INSERT INTO public.protocol_mapper VALUES ('53afea84-ddd1-40a5-9a19-3d4fef5c4695', 'zoneinfo', 'openid-connect', 'oidc-usermodel-attribute-mapper', NULL, '9ecfa9d2-2b77-4e00-990f-fe3519cd3d05');
INSERT INTO public.protocol_mapper VALUES ('d2b03d2a-2fa7-4737-a42b-d30f0a98e5a0', 'locale', 'openid-connect', 'oidc-usermodel-attribute-mapper', NULL, '9ecfa9d2-2b77-4e00-990f-fe3519cd3d05');
INSERT INTO public.protocol_mapper VALUES ('590d8496-bc53-48d1-9bcd-63bd2d677683', 'updated at', 'openid-connect', 'oidc-usermodel-attribute-mapper', NULL, '9ecfa9d2-2b77-4e00-990f-fe3519cd3d05');
INSERT INTO public.protocol_mapper VALUES ('154d61a1-72f8-43a2-b373-af3c5d37fff3', 'email', 'openid-connect', 'oidc-usermodel-property-mapper', NULL, 'c7c3febd-5e51-4525-85c5-313f049798e5');
INSERT INTO public.protocol_mapper VALUES ('64094c4f-5f1f-4c4a-b78b-a28dfc1cc684', 'email verified', 'openid-connect', 'oidc-usermodel-property-mapper', NULL, 'c7c3febd-5e51-4525-85c5-313f049798e5');
INSERT INTO public.protocol_mapper VALUES ('df27c83f-4ec3-454f-96b1-43030ae47894', 'address', 'openid-connect', 'oidc-address-mapper', NULL, 'b9496d2f-2730-439d-9001-b9a652bc9a5c');
INSERT INTO public.protocol_mapper VALUES ('a2f3119b-9df1-4ab6-8922-6bffe1023f6e', 'phone number', 'openid-connect', 'oidc-usermodel-attribute-mapper', NULL, 'ed12965d-49ed-4f59-b557-308ff1b476cf');
INSERT INTO public.protocol_mapper VALUES ('01b4c2ce-55e5-4339-bb84-ebf6f58b4836', 'phone number verified', 'openid-connect', 'oidc-usermodel-attribute-mapper', NULL, 'ed12965d-49ed-4f59-b557-308ff1b476cf');
INSERT INTO public.protocol_mapper VALUES ('85d6deb2-855f-4f7d-9609-f49043b7c9dd', 'realm roles', 'openid-connect', 'oidc-usermodel-realm-role-mapper', NULL, 'fe8ade79-8575-4f4e-ab22-61332ab02e91');
INSERT INTO public.protocol_mapper VALUES ('de9470da-1138-4653-85f6-f4896f27b481', 'client roles', 'openid-connect', 'oidc-usermodel-client-role-mapper', NULL, 'fe8ade79-8575-4f4e-ab22-61332ab02e91');
INSERT INTO public.protocol_mapper VALUES ('206fde38-d018-49b7-8295-1338e13711f2', 'audience resolve', 'openid-connect', 'oidc-audience-resolve-mapper', NULL, 'fe8ade79-8575-4f4e-ab22-61332ab02e91');
INSERT INTO public.protocol_mapper VALUES ('cf513807-856b-4ecf-8a46-99660986a613', 'allowed web origins', 'openid-connect', 'oidc-allowed-origins-mapper', NULL, '8c04b7a5-27be-494e-8773-a7284a101027');
INSERT INTO public.protocol_mapper VALUES ('71c6d989-6e8e-4120-a92b-ddebe36daa89', 'role list', 'saml', 'saml-role-list-mapper', NULL, 'c553a264-596a-4375-9cf7-a834cbbc47c4');
INSERT INTO public.protocol_mapper VALUES ('1927cfb3-b6c1-4eaf-a6ea-2c2df7d0b330', 'full name', 'openid-connect', 'oidc-full-name-mapper', NULL, 'f61ea2ca-9d62-4637-b30f-6489424ee38b');
INSERT INTO public.protocol_mapper VALUES ('77610e5e-d24e-45b7-a69a-ef534e33ff2c', 'family name', 'openid-connect', 'oidc-usermodel-property-mapper', NULL, 'f61ea2ca-9d62-4637-b30f-6489424ee38b');
INSERT INTO public.protocol_mapper VALUES ('cf9d11fd-55c7-4f74-b3c9-1b9c860aa28e', 'given name', 'openid-connect', 'oidc-usermodel-property-mapper', NULL, 'f61ea2ca-9d62-4637-b30f-6489424ee38b');
INSERT INTO public.protocol_mapper VALUES ('dee380d6-f87a-4c1b-a327-73f48ed66bba', 'middle name', 'openid-connect', 'oidc-usermodel-attribute-mapper', NULL, 'f61ea2ca-9d62-4637-b30f-6489424ee38b');
INSERT INTO public.protocol_mapper VALUES ('7c6b3930-23fb-482d-80e0-cbeca6aced4d', 'nickname', 'openid-connect', 'oidc-usermodel-attribute-mapper', NULL, 'f61ea2ca-9d62-4637-b30f-6489424ee38b');
INSERT INTO public.protocol_mapper VALUES ('fa9dd10f-6c8a-41f1-bafd-783f343726b2', 'username', 'openid-connect', 'oidc-usermodel-property-mapper', NULL, 'f61ea2ca-9d62-4637-b30f-6489424ee38b');
INSERT INTO public.protocol_mapper VALUES ('f9c04745-cd98-4ec2-8d2f-f2dc46bceb91', 'profile', 'openid-connect', 'oidc-usermodel-attribute-mapper', NULL, 'f61ea2ca-9d62-4637-b30f-6489424ee38b');
INSERT INTO public.protocol_mapper VALUES ('d7838106-acce-4ccd-b119-909f3d3d4785', 'picture', 'openid-connect', 'oidc-usermodel-attribute-mapper', NULL, 'f61ea2ca-9d62-4637-b30f-6489424ee38b');
INSERT INTO public.protocol_mapper VALUES ('eba0de72-9611-4099-bb9e-cfdcdbf846e0', 'website', 'openid-connect', 'oidc-usermodel-attribute-mapper', NULL, 'f61ea2ca-9d62-4637-b30f-6489424ee38b');
INSERT INTO public.protocol_mapper VALUES ('cdbeef69-f4d0-48b9-acf6-a199d6c5aa14', 'gender', 'openid-connect', 'oidc-usermodel-attribute-mapper', NULL, 'f61ea2ca-9d62-4637-b30f-6489424ee38b');
INSERT INTO public.protocol_mapper VALUES ('2c70440b-6b7e-4131-b8a9-a3f8f86b70ff', 'birthdate', 'openid-connect', 'oidc-usermodel-attribute-mapper', NULL, 'f61ea2ca-9d62-4637-b30f-6489424ee38b');
INSERT INTO public.protocol_mapper VALUES ('871291e6-bfdd-4779-a177-a86d063ec121', 'zoneinfo', 'openid-connect', 'oidc-usermodel-attribute-mapper', NULL, 'f61ea2ca-9d62-4637-b30f-6489424ee38b');
INSERT INTO public.protocol_mapper VALUES ('50890b03-57e8-4a16-82ae-05f04db612d9', 'locale', 'openid-connect', 'oidc-usermodel-attribute-mapper', NULL, 'f61ea2ca-9d62-4637-b30f-6489424ee38b');
INSERT INTO public.protocol_mapper VALUES ('ba4a8412-bbf8-45db-861d-10c8390a4682', 'updated at', 'openid-connect', 'oidc-usermodel-attribute-mapper', NULL, 'f61ea2ca-9d62-4637-b30f-6489424ee38b');
INSERT INTO public.protocol_mapper VALUES ('4bb34258-e300-446d-8ecc-04666e59b4d8', 'email', 'openid-connect', 'oidc-usermodel-property-mapper', NULL, '6c3cedb0-cc84-4048-8837-35ae16ea57a2');
INSERT INTO public.protocol_mapper VALUES ('907c32dc-f448-4254-879d-4326a21fdf5a', 'email verified', 'openid-connect', 'oidc-usermodel-property-mapper', NULL, '6c3cedb0-cc84-4048-8837-35ae16ea57a2');
INSERT INTO public.protocol_mapper VALUES ('67b94a25-92ab-44b2-a867-45f37e1c6159', 'address', 'openid-connect', 'oidc-address-mapper', NULL, 'cac547c4-a336-4323-b85f-b3218c7580f7');
INSERT INTO public.protocol_mapper VALUES ('6dda0c5b-f25a-4da0-a5b2-e12a187fe855', 'phone number', 'openid-connect', 'oidc-usermodel-attribute-mapper', NULL, '93b96680-d750-4291-b92d-62179f81d1f9');
INSERT INTO public.protocol_mapper VALUES ('b8e7899c-c249-45ac-a8df-a936188d29ba', 'phone number verified', 'openid-connect', 'oidc-usermodel-attribute-mapper', NULL, '93b96680-d750-4291-b92d-62179f81d1f9');
INSERT INTO public.protocol_mapper VALUES ('28ae517e-6453-4fb7-b07a-e4a02c0835d7', 'realm roles', 'openid-connect', 'oidc-usermodel-realm-role-mapper', NULL, 'f95d6362-5078-44ad-87cd-8e9295621032');
INSERT INTO public.protocol_mapper VALUES ('76a6a5a5-90a9-4a08-ace9-7fb030de0a10', 'client roles', 'openid-connect', 'oidc-usermodel-client-role-mapper', NULL, 'f95d6362-5078-44ad-87cd-8e9295621032');
INSERT INTO public.protocol_mapper VALUES ('428de701-c65c-4e89-81ab-34b41fb46109', 'audience resolve', 'openid-connect', 'oidc-audience-resolve-mapper', NULL, 'f95d6362-5078-44ad-87cd-8e9295621032');
INSERT INTO public.protocol_mapper VALUES ('582a0492-0688-4d9f-9ed1-2629fe06887c', 'allowed web origins', 'openid-connect', 'oidc-allowed-origins-mapper', NULL, 'dd11bbfe-31e3-42a0-a167-efe527455b2c');
INSERT INTO public.protocol_mapper VALUES ('bad15684-b043-42e6-a47e-a8cc3ab23d1a', 'locale', 'openid-connect', 'oidc-usermodel-attribute-mapper', 'ef6433ae-3301-4d51-9932-d1899f66c4a7', NULL);


--
-- TOC entry 4084 (class 0 OID 317087)
-- Dependencies: 227
-- Data for Name: protocol_mapper_config; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.protocol_mapper_config VALUES ('bff89aff-ad10-4eda-bf3d-38d3b13f73ea', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('bff89aff-ad10-4eda-bf3d-38d3b13f73ea', 'locale', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('bff89aff-ad10-4eda-bf3d-38d3b13f73ea', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('bff89aff-ad10-4eda-bf3d-38d3b13f73ea', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('bff89aff-ad10-4eda-bf3d-38d3b13f73ea', 'locale', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('bff89aff-ad10-4eda-bf3d-38d3b13f73ea', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('0aa8c5fc-ff2e-4616-8773-23cd6848f061', 'false', 'single');
INSERT INTO public.protocol_mapper_config VALUES ('0aa8c5fc-ff2e-4616-8773-23cd6848f061', 'Basic', 'attribute.nameformat');
INSERT INTO public.protocol_mapper_config VALUES ('0aa8c5fc-ff2e-4616-8773-23cd6848f061', 'Role', 'attribute.name');
INSERT INTO public.protocol_mapper_config VALUES ('2d4b58d4-7f37-4c25-92c7-ed146e0920dd', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('2d4b58d4-7f37-4c25-92c7-ed146e0920dd', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('2d4b58d4-7f37-4c25-92c7-ed146e0920dd', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('d4774763-84d9-40d6-a887-1656770b05a2', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('d4774763-84d9-40d6-a887-1656770b05a2', 'lastName', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('d4774763-84d9-40d6-a887-1656770b05a2', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('d4774763-84d9-40d6-a887-1656770b05a2', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('d4774763-84d9-40d6-a887-1656770b05a2', 'family_name', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('d4774763-84d9-40d6-a887-1656770b05a2', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('8cfffa99-552c-4bcd-9373-3112b3f33ec1', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('8cfffa99-552c-4bcd-9373-3112b3f33ec1', 'firstName', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('8cfffa99-552c-4bcd-9373-3112b3f33ec1', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('8cfffa99-552c-4bcd-9373-3112b3f33ec1', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('8cfffa99-552c-4bcd-9373-3112b3f33ec1', 'given_name', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('8cfffa99-552c-4bcd-9373-3112b3f33ec1', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('20bbf7f1-68a6-48eb-b394-5a3f1452fa3d', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('20bbf7f1-68a6-48eb-b394-5a3f1452fa3d', 'middleName', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('20bbf7f1-68a6-48eb-b394-5a3f1452fa3d', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('20bbf7f1-68a6-48eb-b394-5a3f1452fa3d', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('20bbf7f1-68a6-48eb-b394-5a3f1452fa3d', 'middle_name', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('20bbf7f1-68a6-48eb-b394-5a3f1452fa3d', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('af034674-aaca-485e-bdbc-fab2e84904a4', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('af034674-aaca-485e-bdbc-fab2e84904a4', 'nickname', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('af034674-aaca-485e-bdbc-fab2e84904a4', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('af034674-aaca-485e-bdbc-fab2e84904a4', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('af034674-aaca-485e-bdbc-fab2e84904a4', 'nickname', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('af034674-aaca-485e-bdbc-fab2e84904a4', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('c81a5ba8-6068-46b4-963a-43212e5f0ef3', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('c81a5ba8-6068-46b4-963a-43212e5f0ef3', 'username', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('c81a5ba8-6068-46b4-963a-43212e5f0ef3', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('c81a5ba8-6068-46b4-963a-43212e5f0ef3', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('c81a5ba8-6068-46b4-963a-43212e5f0ef3', 'preferred_username', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('c81a5ba8-6068-46b4-963a-43212e5f0ef3', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('9622b613-737f-4676-942e-3bc465f386ed', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('9622b613-737f-4676-942e-3bc465f386ed', 'profile', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('9622b613-737f-4676-942e-3bc465f386ed', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('9622b613-737f-4676-942e-3bc465f386ed', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('9622b613-737f-4676-942e-3bc465f386ed', 'profile', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('9622b613-737f-4676-942e-3bc465f386ed', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('92efb932-947d-4d21-a4e9-0f4da65f386e', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('92efb932-947d-4d21-a4e9-0f4da65f386e', 'picture', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('92efb932-947d-4d21-a4e9-0f4da65f386e', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('92efb932-947d-4d21-a4e9-0f4da65f386e', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('92efb932-947d-4d21-a4e9-0f4da65f386e', 'picture', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('92efb932-947d-4d21-a4e9-0f4da65f386e', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('7dfb6670-5b65-4d0e-ac72-b7c9ba732ade', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('7dfb6670-5b65-4d0e-ac72-b7c9ba732ade', 'website', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('7dfb6670-5b65-4d0e-ac72-b7c9ba732ade', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('7dfb6670-5b65-4d0e-ac72-b7c9ba732ade', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('7dfb6670-5b65-4d0e-ac72-b7c9ba732ade', 'website', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('7dfb6670-5b65-4d0e-ac72-b7c9ba732ade', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('d3d3a39a-a7a1-4d8e-829c-3a8d11972911', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('d3d3a39a-a7a1-4d8e-829c-3a8d11972911', 'gender', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('d3d3a39a-a7a1-4d8e-829c-3a8d11972911', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('d3d3a39a-a7a1-4d8e-829c-3a8d11972911', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('d3d3a39a-a7a1-4d8e-829c-3a8d11972911', 'gender', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('d3d3a39a-a7a1-4d8e-829c-3a8d11972911', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('2ef94c94-5784-4dfc-9af7-273290dbd5a2', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('2ef94c94-5784-4dfc-9af7-273290dbd5a2', 'birthdate', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('2ef94c94-5784-4dfc-9af7-273290dbd5a2', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('2ef94c94-5784-4dfc-9af7-273290dbd5a2', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('2ef94c94-5784-4dfc-9af7-273290dbd5a2', 'birthdate', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('2ef94c94-5784-4dfc-9af7-273290dbd5a2', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('53afea84-ddd1-40a5-9a19-3d4fef5c4695', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('53afea84-ddd1-40a5-9a19-3d4fef5c4695', 'zoneinfo', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('53afea84-ddd1-40a5-9a19-3d4fef5c4695', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('53afea84-ddd1-40a5-9a19-3d4fef5c4695', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('53afea84-ddd1-40a5-9a19-3d4fef5c4695', 'zoneinfo', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('53afea84-ddd1-40a5-9a19-3d4fef5c4695', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('d2b03d2a-2fa7-4737-a42b-d30f0a98e5a0', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('d2b03d2a-2fa7-4737-a42b-d30f0a98e5a0', 'locale', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('d2b03d2a-2fa7-4737-a42b-d30f0a98e5a0', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('d2b03d2a-2fa7-4737-a42b-d30f0a98e5a0', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('d2b03d2a-2fa7-4737-a42b-d30f0a98e5a0', 'locale', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('d2b03d2a-2fa7-4737-a42b-d30f0a98e5a0', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('590d8496-bc53-48d1-9bcd-63bd2d677683', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('590d8496-bc53-48d1-9bcd-63bd2d677683', 'updatedAt', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('590d8496-bc53-48d1-9bcd-63bd2d677683', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('590d8496-bc53-48d1-9bcd-63bd2d677683', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('590d8496-bc53-48d1-9bcd-63bd2d677683', 'updated_at', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('590d8496-bc53-48d1-9bcd-63bd2d677683', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('154d61a1-72f8-43a2-b373-af3c5d37fff3', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('154d61a1-72f8-43a2-b373-af3c5d37fff3', 'email', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('154d61a1-72f8-43a2-b373-af3c5d37fff3', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('154d61a1-72f8-43a2-b373-af3c5d37fff3', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('154d61a1-72f8-43a2-b373-af3c5d37fff3', 'email', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('154d61a1-72f8-43a2-b373-af3c5d37fff3', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('64094c4f-5f1f-4c4a-b78b-a28dfc1cc684', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('64094c4f-5f1f-4c4a-b78b-a28dfc1cc684', 'emailVerified', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('64094c4f-5f1f-4c4a-b78b-a28dfc1cc684', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('64094c4f-5f1f-4c4a-b78b-a28dfc1cc684', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('64094c4f-5f1f-4c4a-b78b-a28dfc1cc684', 'email_verified', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('64094c4f-5f1f-4c4a-b78b-a28dfc1cc684', 'boolean', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('df27c83f-4ec3-454f-96b1-43030ae47894', 'formatted', 'user.attribute.formatted');
INSERT INTO public.protocol_mapper_config VALUES ('df27c83f-4ec3-454f-96b1-43030ae47894', 'country', 'user.attribute.country');
INSERT INTO public.protocol_mapper_config VALUES ('df27c83f-4ec3-454f-96b1-43030ae47894', 'postal_code', 'user.attribute.postal_code');
INSERT INTO public.protocol_mapper_config VALUES ('df27c83f-4ec3-454f-96b1-43030ae47894', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('df27c83f-4ec3-454f-96b1-43030ae47894', 'street', 'user.attribute.street');
INSERT INTO public.protocol_mapper_config VALUES ('df27c83f-4ec3-454f-96b1-43030ae47894', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('df27c83f-4ec3-454f-96b1-43030ae47894', 'region', 'user.attribute.region');
INSERT INTO public.protocol_mapper_config VALUES ('df27c83f-4ec3-454f-96b1-43030ae47894', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('df27c83f-4ec3-454f-96b1-43030ae47894', 'locality', 'user.attribute.locality');
INSERT INTO public.protocol_mapper_config VALUES ('a2f3119b-9df1-4ab6-8922-6bffe1023f6e', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('a2f3119b-9df1-4ab6-8922-6bffe1023f6e', 'phoneNumber', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('a2f3119b-9df1-4ab6-8922-6bffe1023f6e', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('a2f3119b-9df1-4ab6-8922-6bffe1023f6e', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('a2f3119b-9df1-4ab6-8922-6bffe1023f6e', 'phone_number', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('a2f3119b-9df1-4ab6-8922-6bffe1023f6e', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('01b4c2ce-55e5-4339-bb84-ebf6f58b4836', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('01b4c2ce-55e5-4339-bb84-ebf6f58b4836', 'phoneNumberVerified', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('01b4c2ce-55e5-4339-bb84-ebf6f58b4836', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('01b4c2ce-55e5-4339-bb84-ebf6f58b4836', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('01b4c2ce-55e5-4339-bb84-ebf6f58b4836', 'phone_number_verified', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('01b4c2ce-55e5-4339-bb84-ebf6f58b4836', 'boolean', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('85d6deb2-855f-4f7d-9609-f49043b7c9dd', 'true', 'multivalued');
INSERT INTO public.protocol_mapper_config VALUES ('85d6deb2-855f-4f7d-9609-f49043b7c9dd', 'foo', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('85d6deb2-855f-4f7d-9609-f49043b7c9dd', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('85d6deb2-855f-4f7d-9609-f49043b7c9dd', 'realm_access.roles', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('85d6deb2-855f-4f7d-9609-f49043b7c9dd', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('de9470da-1138-4653-85f6-f4896f27b481', 'true', 'multivalued');
INSERT INTO public.protocol_mapper_config VALUES ('de9470da-1138-4653-85f6-f4896f27b481', 'foo', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('de9470da-1138-4653-85f6-f4896f27b481', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('de9470da-1138-4653-85f6-f4896f27b481', 'resource_access.${client_id}.roles', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('de9470da-1138-4653-85f6-f4896f27b481', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('71c6d989-6e8e-4120-a92b-ddebe36daa89', 'false', 'single');
INSERT INTO public.protocol_mapper_config VALUES ('71c6d989-6e8e-4120-a92b-ddebe36daa89', 'Basic', 'attribute.nameformat');
INSERT INTO public.protocol_mapper_config VALUES ('71c6d989-6e8e-4120-a92b-ddebe36daa89', 'Role', 'attribute.name');
INSERT INTO public.protocol_mapper_config VALUES ('1927cfb3-b6c1-4eaf-a6ea-2c2df7d0b330', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('1927cfb3-b6c1-4eaf-a6ea-2c2df7d0b330', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('1927cfb3-b6c1-4eaf-a6ea-2c2df7d0b330', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('77610e5e-d24e-45b7-a69a-ef534e33ff2c', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('77610e5e-d24e-45b7-a69a-ef534e33ff2c', 'lastName', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('77610e5e-d24e-45b7-a69a-ef534e33ff2c', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('77610e5e-d24e-45b7-a69a-ef534e33ff2c', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('77610e5e-d24e-45b7-a69a-ef534e33ff2c', 'family_name', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('77610e5e-d24e-45b7-a69a-ef534e33ff2c', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('cf9d11fd-55c7-4f74-b3c9-1b9c860aa28e', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('cf9d11fd-55c7-4f74-b3c9-1b9c860aa28e', 'firstName', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('cf9d11fd-55c7-4f74-b3c9-1b9c860aa28e', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('cf9d11fd-55c7-4f74-b3c9-1b9c860aa28e', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('cf9d11fd-55c7-4f74-b3c9-1b9c860aa28e', 'given_name', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('cf9d11fd-55c7-4f74-b3c9-1b9c860aa28e', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('dee380d6-f87a-4c1b-a327-73f48ed66bba', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('dee380d6-f87a-4c1b-a327-73f48ed66bba', 'middleName', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('dee380d6-f87a-4c1b-a327-73f48ed66bba', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('dee380d6-f87a-4c1b-a327-73f48ed66bba', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('dee380d6-f87a-4c1b-a327-73f48ed66bba', 'middle_name', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('dee380d6-f87a-4c1b-a327-73f48ed66bba', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('7c6b3930-23fb-482d-80e0-cbeca6aced4d', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('7c6b3930-23fb-482d-80e0-cbeca6aced4d', 'nickname', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('7c6b3930-23fb-482d-80e0-cbeca6aced4d', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('7c6b3930-23fb-482d-80e0-cbeca6aced4d', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('7c6b3930-23fb-482d-80e0-cbeca6aced4d', 'nickname', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('7c6b3930-23fb-482d-80e0-cbeca6aced4d', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('fa9dd10f-6c8a-41f1-bafd-783f343726b2', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('fa9dd10f-6c8a-41f1-bafd-783f343726b2', 'username', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('fa9dd10f-6c8a-41f1-bafd-783f343726b2', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('fa9dd10f-6c8a-41f1-bafd-783f343726b2', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('fa9dd10f-6c8a-41f1-bafd-783f343726b2', 'preferred_username', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('fa9dd10f-6c8a-41f1-bafd-783f343726b2', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('f9c04745-cd98-4ec2-8d2f-f2dc46bceb91', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('f9c04745-cd98-4ec2-8d2f-f2dc46bceb91', 'profile', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('f9c04745-cd98-4ec2-8d2f-f2dc46bceb91', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('f9c04745-cd98-4ec2-8d2f-f2dc46bceb91', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('f9c04745-cd98-4ec2-8d2f-f2dc46bceb91', 'profile', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('f9c04745-cd98-4ec2-8d2f-f2dc46bceb91', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('d7838106-acce-4ccd-b119-909f3d3d4785', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('d7838106-acce-4ccd-b119-909f3d3d4785', 'picture', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('d7838106-acce-4ccd-b119-909f3d3d4785', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('d7838106-acce-4ccd-b119-909f3d3d4785', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('d7838106-acce-4ccd-b119-909f3d3d4785', 'picture', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('d7838106-acce-4ccd-b119-909f3d3d4785', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('eba0de72-9611-4099-bb9e-cfdcdbf846e0', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('eba0de72-9611-4099-bb9e-cfdcdbf846e0', 'website', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('eba0de72-9611-4099-bb9e-cfdcdbf846e0', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('eba0de72-9611-4099-bb9e-cfdcdbf846e0', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('eba0de72-9611-4099-bb9e-cfdcdbf846e0', 'website', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('eba0de72-9611-4099-bb9e-cfdcdbf846e0', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('cdbeef69-f4d0-48b9-acf6-a199d6c5aa14', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('cdbeef69-f4d0-48b9-acf6-a199d6c5aa14', 'gender', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('cdbeef69-f4d0-48b9-acf6-a199d6c5aa14', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('cdbeef69-f4d0-48b9-acf6-a199d6c5aa14', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('cdbeef69-f4d0-48b9-acf6-a199d6c5aa14', 'gender', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('cdbeef69-f4d0-48b9-acf6-a199d6c5aa14', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('2c70440b-6b7e-4131-b8a9-a3f8f86b70ff', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('2c70440b-6b7e-4131-b8a9-a3f8f86b70ff', 'birthdate', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('2c70440b-6b7e-4131-b8a9-a3f8f86b70ff', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('2c70440b-6b7e-4131-b8a9-a3f8f86b70ff', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('2c70440b-6b7e-4131-b8a9-a3f8f86b70ff', 'birthdate', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('2c70440b-6b7e-4131-b8a9-a3f8f86b70ff', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('871291e6-bfdd-4779-a177-a86d063ec121', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('871291e6-bfdd-4779-a177-a86d063ec121', 'zoneinfo', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('871291e6-bfdd-4779-a177-a86d063ec121', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('871291e6-bfdd-4779-a177-a86d063ec121', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('871291e6-bfdd-4779-a177-a86d063ec121', 'zoneinfo', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('871291e6-bfdd-4779-a177-a86d063ec121', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('50890b03-57e8-4a16-82ae-05f04db612d9', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('50890b03-57e8-4a16-82ae-05f04db612d9', 'locale', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('50890b03-57e8-4a16-82ae-05f04db612d9', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('50890b03-57e8-4a16-82ae-05f04db612d9', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('50890b03-57e8-4a16-82ae-05f04db612d9', 'locale', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('50890b03-57e8-4a16-82ae-05f04db612d9', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('ba4a8412-bbf8-45db-861d-10c8390a4682', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('ba4a8412-bbf8-45db-861d-10c8390a4682', 'updatedAt', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('ba4a8412-bbf8-45db-861d-10c8390a4682', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('ba4a8412-bbf8-45db-861d-10c8390a4682', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('ba4a8412-bbf8-45db-861d-10c8390a4682', 'updated_at', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('ba4a8412-bbf8-45db-861d-10c8390a4682', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('4bb34258-e300-446d-8ecc-04666e59b4d8', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('4bb34258-e300-446d-8ecc-04666e59b4d8', 'email', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('4bb34258-e300-446d-8ecc-04666e59b4d8', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('4bb34258-e300-446d-8ecc-04666e59b4d8', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('4bb34258-e300-446d-8ecc-04666e59b4d8', 'email', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('4bb34258-e300-446d-8ecc-04666e59b4d8', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('907c32dc-f448-4254-879d-4326a21fdf5a', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('907c32dc-f448-4254-879d-4326a21fdf5a', 'emailVerified', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('907c32dc-f448-4254-879d-4326a21fdf5a', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('907c32dc-f448-4254-879d-4326a21fdf5a', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('907c32dc-f448-4254-879d-4326a21fdf5a', 'email_verified', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('907c32dc-f448-4254-879d-4326a21fdf5a', 'boolean', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('67b94a25-92ab-44b2-a867-45f37e1c6159', 'formatted', 'user.attribute.formatted');
INSERT INTO public.protocol_mapper_config VALUES ('67b94a25-92ab-44b2-a867-45f37e1c6159', 'country', 'user.attribute.country');
INSERT INTO public.protocol_mapper_config VALUES ('67b94a25-92ab-44b2-a867-45f37e1c6159', 'postal_code', 'user.attribute.postal_code');
INSERT INTO public.protocol_mapper_config VALUES ('67b94a25-92ab-44b2-a867-45f37e1c6159', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('67b94a25-92ab-44b2-a867-45f37e1c6159', 'street', 'user.attribute.street');
INSERT INTO public.protocol_mapper_config VALUES ('67b94a25-92ab-44b2-a867-45f37e1c6159', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('67b94a25-92ab-44b2-a867-45f37e1c6159', 'region', 'user.attribute.region');
INSERT INTO public.protocol_mapper_config VALUES ('67b94a25-92ab-44b2-a867-45f37e1c6159', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('67b94a25-92ab-44b2-a867-45f37e1c6159', 'locality', 'user.attribute.locality');
INSERT INTO public.protocol_mapper_config VALUES ('6dda0c5b-f25a-4da0-a5b2-e12a187fe855', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('6dda0c5b-f25a-4da0-a5b2-e12a187fe855', 'phoneNumber', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('6dda0c5b-f25a-4da0-a5b2-e12a187fe855', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('6dda0c5b-f25a-4da0-a5b2-e12a187fe855', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('6dda0c5b-f25a-4da0-a5b2-e12a187fe855', 'phone_number', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('6dda0c5b-f25a-4da0-a5b2-e12a187fe855', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('b8e7899c-c249-45ac-a8df-a936188d29ba', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('b8e7899c-c249-45ac-a8df-a936188d29ba', 'phoneNumberVerified', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('b8e7899c-c249-45ac-a8df-a936188d29ba', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('b8e7899c-c249-45ac-a8df-a936188d29ba', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('b8e7899c-c249-45ac-a8df-a936188d29ba', 'phone_number_verified', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('b8e7899c-c249-45ac-a8df-a936188d29ba', 'boolean', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('28ae517e-6453-4fb7-b07a-e4a02c0835d7', 'true', 'multivalued');
INSERT INTO public.protocol_mapper_config VALUES ('28ae517e-6453-4fb7-b07a-e4a02c0835d7', 'foo', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('28ae517e-6453-4fb7-b07a-e4a02c0835d7', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('28ae517e-6453-4fb7-b07a-e4a02c0835d7', 'realm_access.roles', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('28ae517e-6453-4fb7-b07a-e4a02c0835d7', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('76a6a5a5-90a9-4a08-ace9-7fb030de0a10', 'true', 'multivalued');
INSERT INTO public.protocol_mapper_config VALUES ('76a6a5a5-90a9-4a08-ace9-7fb030de0a10', 'foo', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('76a6a5a5-90a9-4a08-ace9-7fb030de0a10', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('76a6a5a5-90a9-4a08-ace9-7fb030de0a10', 'resource_access.${client_id}.roles', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('76a6a5a5-90a9-4a08-ace9-7fb030de0a10', 'String', 'jsonType.label');
INSERT INTO public.protocol_mapper_config VALUES ('bad15684-b043-42e6-a47e-a8cc3ab23d1a', 'true', 'userinfo.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('bad15684-b043-42e6-a47e-a8cc3ab23d1a', 'locale', 'user.attribute');
INSERT INTO public.protocol_mapper_config VALUES ('bad15684-b043-42e6-a47e-a8cc3ab23d1a', 'true', 'id.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('bad15684-b043-42e6-a47e-a8cc3ab23d1a', 'true', 'access.token.claim');
INSERT INTO public.protocol_mapper_config VALUES ('bad15684-b043-42e6-a47e-a8cc3ab23d1a', 'locale', 'claim.name');
INSERT INTO public.protocol_mapper_config VALUES ('bad15684-b043-42e6-a47e-a8cc3ab23d1a', 'String', 'jsonType.label');


--
-- TOC entry 4063 (class 0 OID 316715)
-- Dependencies: 206
-- Data for Name: realm; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.realm VALUES ('master', 60, 300, 60, NULL, NULL, NULL, true, false, 0, NULL, 'master', 0, NULL, false, false, false, false, 'EXTERNAL', 1800, 36000, false, false, '294184bd-4645-4042-991d-aea04faace2f', 1800, false, NULL, false, false, false, false, 0, 1, 30, 6, 'HmacSHA1', 'totp', '36214f70-b1fd-45bb-8eb2-06bd7037aa8e', 'bb478049-afe1-42d1-a261-4626bdc43371', '1e6154a4-dc01-4359-b905-94dca266b62a', '4a64c8ba-48d4-4792-9c09-f28817ea5dc2', '42f1f24c-2bec-4765-ac9f-17218686ea7d', 2592000, false, 900, true, false, '3ee0b230-e04b-4aaa-b7aa-c57b5eddadb6', 0, false, 0, 0);
INSERT INTO public.realm VALUES ('demo', 60, 300, 300, NULL, NULL, NULL, true, false, 0, NULL, 'demo', 0, NULL, false, false, false, false, 'EXTERNAL', 1800, 36000, false, false, 'a685b6f1-7742-46c3-b47f-85b565e2e6e7', 1800, false, NULL, false, false, false, false, 0, 1, 30, 6, 'HmacSHA1', 'totp', '1375a822-19ae-4b06-acfa-d24b953a92d5', '5b4056ff-d494-49c2-a498-5713f317bb5f', 'a5237c0b-2a2c-45a7-8126-56c8ddcedce3', '3d317980-1d0b-4c41-87eb-7b28e6e8a69c', 'f0e1c880-30c7-4627-a1b9-e93f64fcde14', 2592000, false, 900, true, false, '7f25aa09-fbb4-4f0f-a707-42ec929aaf69', 0, false, 0, 0);


--
-- TOC entry 4064 (class 0 OID 316733)
-- Dependencies: 207
-- Data for Name: realm_attribute; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.realm_attribute VALUES ('_browser_header.contentSecurityPolicyReportOnly', '', 'master');
INSERT INTO public.realm_attribute VALUES ('_browser_header.xContentTypeOptions', 'nosniff', 'master');
INSERT INTO public.realm_attribute VALUES ('_browser_header.xRobotsTag', 'none', 'master');
INSERT INTO public.realm_attribute VALUES ('_browser_header.xFrameOptions', 'SAMEORIGIN', 'master');
INSERT INTO public.realm_attribute VALUES ('_browser_header.contentSecurityPolicy', 'frame-src ''self''; frame-ancestors ''self''; object-src ''none'';', 'master');
INSERT INTO public.realm_attribute VALUES ('_browser_header.xXSSProtection', '1; mode=block', 'master');
INSERT INTO public.realm_attribute VALUES ('_browser_header.strictTransportSecurity', 'max-age=31536000; includeSubDomains', 'master');
INSERT INTO public.realm_attribute VALUES ('bruteForceProtected', 'false', 'master');
INSERT INTO public.realm_attribute VALUES ('permanentLockout', 'false', 'master');
INSERT INTO public.realm_attribute VALUES ('maxFailureWaitSeconds', '900', 'master');
INSERT INTO public.realm_attribute VALUES ('minimumQuickLoginWaitSeconds', '60', 'master');
INSERT INTO public.realm_attribute VALUES ('waitIncrementSeconds', '60', 'master');
INSERT INTO public.realm_attribute VALUES ('quickLoginCheckMilliSeconds', '1000', 'master');
INSERT INTO public.realm_attribute VALUES ('maxDeltaTimeSeconds', '43200', 'master');
INSERT INTO public.realm_attribute VALUES ('failureFactor', '30', 'master');
INSERT INTO public.realm_attribute VALUES ('displayName', 'Keycloak', 'master');
INSERT INTO public.realm_attribute VALUES ('displayNameHtml', '<div class="kc-logo-text"><span>Keycloak</span></div>', 'master');
INSERT INTO public.realm_attribute VALUES ('offlineSessionMaxLifespanEnabled', 'false', 'master');
INSERT INTO public.realm_attribute VALUES ('offlineSessionMaxLifespan', '5184000', 'master');
INSERT INTO public.realm_attribute VALUES ('_browser_header.contentSecurityPolicyReportOnly', '', 'demo');
INSERT INTO public.realm_attribute VALUES ('_browser_header.xContentTypeOptions', 'nosniff', 'demo');
INSERT INTO public.realm_attribute VALUES ('_browser_header.xRobotsTag', 'none', 'demo');
INSERT INTO public.realm_attribute VALUES ('_browser_header.xFrameOptions', 'SAMEORIGIN', 'demo');
INSERT INTO public.realm_attribute VALUES ('_browser_header.contentSecurityPolicy', 'frame-src ''self''; frame-ancestors ''self''; object-src ''none'';', 'demo');
INSERT INTO public.realm_attribute VALUES ('_browser_header.xXSSProtection', '1; mode=block', 'demo');
INSERT INTO public.realm_attribute VALUES ('_browser_header.strictTransportSecurity', 'max-age=31536000; includeSubDomains', 'demo');
INSERT INTO public.realm_attribute VALUES ('bruteForceProtected', 'false', 'demo');
INSERT INTO public.realm_attribute VALUES ('permanentLockout', 'false', 'demo');
INSERT INTO public.realm_attribute VALUES ('maxFailureWaitSeconds', '900', 'demo');
INSERT INTO public.realm_attribute VALUES ('minimumQuickLoginWaitSeconds', '60', 'demo');
INSERT INTO public.realm_attribute VALUES ('waitIncrementSeconds', '60', 'demo');
INSERT INTO public.realm_attribute VALUES ('quickLoginCheckMilliSeconds', '1000', 'demo');
INSERT INTO public.realm_attribute VALUES ('maxDeltaTimeSeconds', '43200', 'demo');
INSERT INTO public.realm_attribute VALUES ('failureFactor', '30', 'demo');
INSERT INTO public.realm_attribute VALUES ('offlineSessionMaxLifespanEnabled', 'false', 'demo');
INSERT INTO public.realm_attribute VALUES ('offlineSessionMaxLifespan', '5184000', 'demo');
INSERT INTO public.realm_attribute VALUES ('actionTokenGeneratedByAdminLifespan', '43200', 'demo');
INSERT INTO public.realm_attribute VALUES ('actionTokenGeneratedByUserLifespan', '300', 'demo');


--
-- TOC entry 4113 (class 0 OID 317522)
-- Dependencies: 256
-- Data for Name: realm_default_groups; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4065 (class 0 OID 316739)
-- Dependencies: 208
-- Data for Name: realm_default_roles; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.realm_default_roles VALUES ('master', 'a7d642a2-bc13-49ed-aacc-b87f1a0d5b3a');
INSERT INTO public.realm_default_roles VALUES ('master', '4195fcd6-6e49-42bb-8086-87f47a896950');
INSERT INTO public.realm_default_roles VALUES ('demo', '8bcb18d5-4c73-422b-be0c-037077c71287');
INSERT INTO public.realm_default_roles VALUES ('demo', 'b4547d26-237d-4100-8c80-377acd01f929');


--
-- TOC entry 4090 (class 0 OID 317204)
-- Dependencies: 233
-- Data for Name: realm_enabled_event_types; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4066 (class 0 OID 316742)
-- Dependencies: 209
-- Data for Name: realm_events_listeners; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.realm_events_listeners VALUES ('master', 'jboss-logging');
INSERT INTO public.realm_events_listeners VALUES ('demo', 'jboss-logging');


--
-- TOC entry 4067 (class 0 OID 316745)
-- Dependencies: 210
-- Data for Name: realm_required_credential; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.realm_required_credential VALUES ('password', 'password', true, true, 'master');
INSERT INTO public.realm_required_credential VALUES ('password', 'password', true, true, 'demo');


--
-- TOC entry 4068 (class 0 OID 316753)
-- Dependencies: 211
-- Data for Name: realm_smtp_config; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4088 (class 0 OID 317119)
-- Dependencies: 231
-- Data for Name: realm_supported_locales; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4069 (class 0 OID 316765)
-- Dependencies: 212
-- Data for Name: redirect_uris; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.redirect_uris VALUES ('e188d744-175c-4fc7-aba3-0f85c9717119', '/auth/realms/master/account/*');
INSERT INTO public.redirect_uris VALUES ('05d6ae91-d774-40ab-b49f-7477ad5b272b', '/auth/admin/master/console/*');
INSERT INTO public.redirect_uris VALUES ('303fe57e-df0c-4707-85a8-0537bf566861', '/auth/realms/demo/account/*');
INSERT INTO public.redirect_uris VALUES ('ef6433ae-3301-4d51-9932-d1899f66c4a7', '/auth/admin/demo/console/*');
INSERT INTO public.redirect_uris VALUES ('e2526a36-418c-4707-b3f4-d8bbd2cfc046', 'http://kie-wb:8080/kie-wb/*');


--
-- TOC entry 4106 (class 0 OID 317453)
-- Dependencies: 249
-- Data for Name: required_action_config; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4105 (class 0 OID 317445)
-- Dependencies: 248
-- Data for Name: required_action_provider; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.required_action_provider VALUES ('2e21436d-a036-4201-a2cc-8e31a92031c6', 'VERIFY_EMAIL', 'Verify Email', 'master', true, false, 'VERIFY_EMAIL', 50);
INSERT INTO public.required_action_provider VALUES ('4ee118f4-2057-4299-88c2-76b93b47bf2e', 'UPDATE_PROFILE', 'Update Profile', 'master', true, false, 'UPDATE_PROFILE', 40);
INSERT INTO public.required_action_provider VALUES ('80cef3d4-dbe7-48ef-9832-d1ea259d7969', 'CONFIGURE_TOTP', 'Configure OTP', 'master', true, false, 'CONFIGURE_TOTP', 10);
INSERT INTO public.required_action_provider VALUES ('d70b6a59-3311-4595-90ee-ba4b80626ff5', 'UPDATE_PASSWORD', 'Update Password', 'master', true, false, 'UPDATE_PASSWORD', 30);
INSERT INTO public.required_action_provider VALUES ('39041234-6b07-4108-8e05-4a4ee01ebf74', 'terms_and_conditions', 'Terms and Conditions', 'master', false, false, 'terms_and_conditions', 20);
INSERT INTO public.required_action_provider VALUES ('3ce929be-50e8-4fb0-a8bf-86b0805e8fd6', 'VERIFY_EMAIL', 'Verify Email', 'demo', true, false, 'VERIFY_EMAIL', 50);
INSERT INTO public.required_action_provider VALUES ('75247606-739f-477e-b48c-3b7574225c78', 'UPDATE_PROFILE', 'Update Profile', 'demo', true, false, 'UPDATE_PROFILE', 40);
INSERT INTO public.required_action_provider VALUES ('e150aba1-f444-45e3-8b1c-bed7aae72b36', 'CONFIGURE_TOTP', 'Configure OTP', 'demo', true, false, 'CONFIGURE_TOTP', 10);
INSERT INTO public.required_action_provider VALUES ('11474d45-fe2f-4add-b15e-841ab2f723bb', 'UPDATE_PASSWORD', 'Update Password', 'demo', true, false, 'UPDATE_PASSWORD', 30);
INSERT INTO public.required_action_provider VALUES ('917a40df-7a78-499b-ab5e-c05be90356c8', 'terms_and_conditions', 'Terms and Conditions', 'demo', false, false, 'terms_and_conditions', 20);


--
-- TOC entry 4145 (class 0 OID 318174)
-- Dependencies: 288
-- Data for Name: resource_attribute; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4123 (class 0 OID 317747)
-- Dependencies: 266
-- Data for Name: resource_policy; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4122 (class 0 OID 317732)
-- Dependencies: 265
-- Data for Name: resource_scope; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4117 (class 0 OID 317666)
-- Dependencies: 260
-- Data for Name: resource_server; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4144 (class 0 OID 318150)
-- Dependencies: 287
-- Data for Name: resource_server_perm_ticket; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4120 (class 0 OID 317704)
-- Dependencies: 263
-- Data for Name: resource_server_policy; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4118 (class 0 OID 317674)
-- Dependencies: 261
-- Data for Name: resource_server_resource; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4119 (class 0 OID 317689)
-- Dependencies: 262
-- Data for Name: resource_server_scope; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4146 (class 0 OID 318193)
-- Dependencies: 289
-- Data for Name: resource_uris; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4147 (class 0 OID 318201)
-- Dependencies: 290
-- Data for Name: role_attribute; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4070 (class 0 OID 316768)
-- Dependencies: 213
-- Data for Name: scope_mapping; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4124 (class 0 OID 317762)
-- Dependencies: 267
-- Data for Name: scope_policy; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4072 (class 0 OID 316774)
-- Dependencies: 215
-- Data for Name: user_attribute; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4094 (class 0 OID 317227)
-- Dependencies: 237
-- Data for Name: user_consent; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4142 (class 0 OID 318125)
-- Dependencies: 285
-- Data for Name: user_consent_client_scope; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4073 (class 0 OID 316780)
-- Dependencies: 216
-- Data for Name: user_entity; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.user_entity VALUES ('37ce8dbb-14e2-4a9f-8bd8-a5b10a20a84d', NULL, 'a8b49462-faac-4f85-b349-30598e1a0bfd', false, true, NULL, NULL, NULL, 'master', 'admin', 1554972125852, NULL, 0);
INSERT INTO public.user_entity VALUES ('d2f4dd75-57c7-4830-8797-f5bdfb406fac', NULL, 'e1099f63-4112-49f5-8911-9d7f4d6cf542', false, true, NULL, NULL, NULL, 'demo', 'admin', 1554973862857, NULL, 0);


--
-- TOC entry 4074 (class 0 OID 316789)
-- Dependencies: 217
-- Data for Name: user_federation_config; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4101 (class 0 OID 317343)
-- Dependencies: 244
-- Data for Name: user_federation_mapper; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4102 (class 0 OID 317349)
-- Dependencies: 245
-- Data for Name: user_federation_mapper_config; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4075 (class 0 OID 316795)
-- Dependencies: 218
-- Data for Name: user_federation_provider; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4112 (class 0 OID 317519)
-- Dependencies: 255
-- Data for Name: user_group_membership; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4076 (class 0 OID 316801)
-- Dependencies: 219
-- Data for Name: user_required_action; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4077 (class 0 OID 316804)
-- Dependencies: 220
-- Data for Name: user_role_mapping; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.user_role_mapping VALUES ('4195fcd6-6e49-42bb-8086-87f47a896950', '37ce8dbb-14e2-4a9f-8bd8-a5b10a20a84d');
INSERT INTO public.user_role_mapping VALUES ('a7d642a2-bc13-49ed-aacc-b87f1a0d5b3a', '37ce8dbb-14e2-4a9f-8bd8-a5b10a20a84d');
INSERT INTO public.user_role_mapping VALUES ('972bfb5d-ff9c-46c6-8a35-897dfb2b961c', '37ce8dbb-14e2-4a9f-8bd8-a5b10a20a84d');
INSERT INTO public.user_role_mapping VALUES ('daf57b44-dc1a-4d08-9c40-a36987dea994', '37ce8dbb-14e2-4a9f-8bd8-a5b10a20a84d');
INSERT INTO public.user_role_mapping VALUES ('ba03e603-2915-4466-a581-491b32229a7e', '37ce8dbb-14e2-4a9f-8bd8-a5b10a20a84d');
INSERT INTO public.user_role_mapping VALUES ('b4547d26-237d-4100-8c80-377acd01f929', 'd2f4dd75-57c7-4830-8797-f5bdfb406fac');
INSERT INTO public.user_role_mapping VALUES ('56d349f4-4b88-4986-b691-717ef57aa2d3', 'd2f4dd75-57c7-4830-8797-f5bdfb406fac');
INSERT INTO public.user_role_mapping VALUES ('8bcb18d5-4c73-422b-be0c-037077c71287', 'd2f4dd75-57c7-4830-8797-f5bdfb406fac');
INSERT INTO public.user_role_mapping VALUES ('6d5bf9b9-4301-48f9-9c4e-ed890b0f7302', 'd2f4dd75-57c7-4830-8797-f5bdfb406fac');
INSERT INTO public.user_role_mapping VALUES ('41f0087b-dca9-4c57-b047-625bb23edda6', 'd2f4dd75-57c7-4830-8797-f5bdfb406fac');
INSERT INTO public.user_role_mapping VALUES ('34156f64-2937-4c35-870b-5f030d3469b0', 'd2f4dd75-57c7-4830-8797-f5bdfb406fac');
INSERT INTO public.user_role_mapping VALUES ('bb3aa479-7ff1-419d-aeef-6bcf70bce70f', 'd2f4dd75-57c7-4830-8797-f5bdfb406fac');


--
-- TOC entry 4078 (class 0 OID 316807)
-- Dependencies: 221
-- Data for Name: user_session; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4089 (class 0 OID 317122)
-- Dependencies: 232
-- Data for Name: user_session_note; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4071 (class 0 OID 316771)
-- Dependencies: 214
-- Data for Name: username_login_failure; Type: TABLE DATA; Schema: public; Owner: keycloack
--



--
-- TOC entry 4079 (class 0 OID 316820)
-- Dependencies: 222
-- Data for Name: web_origins; Type: TABLE DATA; Schema: public; Owner: keycloack
--

INSERT INTO public.web_origins VALUES ('e2526a36-418c-4707-b3f4-d8bbd2cfc046', 'http://kie-wb:8080');


-- Completed on 2019-04-11 17:24:01 CEST

--
-- PostgreSQL database dump complete
--

