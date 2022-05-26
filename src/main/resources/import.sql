INSERT INTO public.customer (customer_id, name, status) VALUES (1000, 'K. Varela', 'Gold');
INSERT INTO public.customer (customer_id, name, status) VALUES (1001, 'A. Bernard', 'Diamond');
INSERT INTO public.customer (customer_id, name, status) VALUES (1002, 'B. Bernard', 'Silver');
INSERT INTO public.customer (customer_id, name, status) VALUES (1003, 'C. Bernard', 'Diamond');
INSERT INTO public.customer (customer_id, name, status) VALUES (1004, 'D. Bernard', 'Gold');
INSERT INTO public.customer (customer_id, name, status) VALUES (1005, 'E. Bernard', 'Diamond');
INSERT INTO public.customer (customer_id, name, status) VALUES (1006, 'F. Bernard', 'Silver');
INSERT INTO public.customer (customer_id, name, status) VALUES (1007, 'G. Bernard', 'Diamond');
INSERT INTO public.customer (customer_id, name, status) VALUES (1008, 'H. Bernard', 'Gold');
INSERT INTO public.customer (customer_id, name, status) VALUES (1009, 'I. Bernard', 'Diamond');

INSERT INTO public.expense (sale_id, customer_id, amount, date, cashback_id) VALUES (1000, 1000, 20.00, '2022-05-01 00:19:26.000000', 1000);
INSERT INTO public.expense (sale_id, customer_id, amount, date, cashback_id) VALUES (1001, 1000, 20.00, '2022-05-01 00:19:26.000000', 1000);
INSERT INTO public.expense (sale_id, customer_id, amount, date, cashback_id) VALUES (1002, 1000, 150.00, '2022-02-02 00:19:54.000000', 1001);
INSERT INTO public.expense (sale_id, customer_id, amount, date, cashback_id) VALUES (1003, 1002, 20.00, '2022-05-03 00:19:26.000000', 1000);
INSERT INTO public.expense (sale_id, customer_id, amount, date, cashback_id) VALUES (1004, 1003, 150.00, '2022-05-04 00:19:54.000000', 1001);
INSERT INTO public.expense (sale_id, customer_id, amount, date, cashback_id) VALUES (1005, 1004, 20.00, '2022-02-05 00:19:26.000000', 1002);
INSERT INTO public.expense (sale_id, customer_id, amount, date, cashback_id) VALUES (1006, 1005, 150.00, '2022-05-06 00:19:54.000000', 1003);
INSERT INTO public.expense (sale_id, customer_id, amount, date, cashback_id) VALUES (1007, 1006, 20.00, '2022-05-07 00:19:26.000000', 1004);
INSERT INTO public.expense (sale_id, customer_id, amount, date, cashback_id) VALUES (1008, 1007, 150.00, '2022-05-08 00:19:54.000000', 1005);
INSERT INTO public.expense (sale_id, customer_id, amount, date, cashback_id) VALUES (1009, 1008, 20.00, '2022-05-09 00:19:26.000000', 1006);
INSERT INTO public.expense (sale_id, customer_id, amount, date, cashback_id) VALUES (1010, 1009, 151.00, '2021-05-08 00:19:54.000000', 1007);
INSERT INTO public.expense (sale_id, customer_id, amount, date, cashback_id) VALUES (1011, 1000, 152.00, '2022-02-08 00:19:54.000000', 1008);
INSERT INTO public.expense (sale_id, customer_id, amount, date, cashback_id) VALUES (1012, 1001, 153.00, '2021-05-08 00:19:54.000000', 1009);
INSERT INTO public.expense (sale_id, customer_id, amount, date, cashback_id) VALUES (1013, 1002, 154.00, '2022-02-08 00:19:54.000000', 1010);
INSERT INTO public.expense (sale_id, customer_id, amount, date, cashback_id) VALUES (1014, 1005, 155.00, '2021-05-08 00:19:54.000000', 1011);
INSERT INTO public.expense (sale_id, customer_id, amount, date, cashback_id) VALUES (1015, 1006, 156.00, '2021-02-08 00:19:54.000000', 1012);
INSERT INTO public.expense (sale_id, customer_id, amount, date, cashback_id) VALUES (1016, 1007, 157.00, '2021-05-08 00:19:54.000000', 1013);
INSERT INTO public.expense (sale_id, customer_id, amount, date, cashback_id) VALUES (1017, 1008, 158.00, '2021-02-08 00:19:54.000000', 1014);


-- INSERT INTO public.cashback (cashback_id, customer_id, amount) VALUES (1000, 1000, 3.00);
-- INSERT INTO public.cashback (cashback_id, customer_id, amount) VALUES (1001, 1001, 15.00);
-- INSERT INTO public.cashback (cashback_id, customer_id, amount) VALUES (1002, 1002, 15.00);
-- INSERT INTO public.cashback (cashback_id, customer_id, amount) VALUES (1003, 1003, 15.00);
-- INSERT INTO public.cashback (cashback_id, customer_id, amount) VALUES (1004, 1004, 15.00);
-- INSERT INTO public.cashback (cashback_id, customer_id, amount) VALUES (1005, 1005, 15.00);
-- INSERT INTO public.cashback (cashback_id, customer_id, amount) VALUES (1006, 1006, 15.00);
-- INSERT INTO public.cashback (cashback_id, customer_id, amount) VALUES (1007, 1007, 15.00);
-- INSERT INTO public.cashback (cashback_id, customer_id, amount) VALUES (1008, 1008, 15.00);
-- INSERT INTO public.cashback (cashback_id, customer_id, amount) VALUES (1009, 1009, 15.00);
--

