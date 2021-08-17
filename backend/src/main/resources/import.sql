insert into mal_code values (1, 'SQL-Injection');
insert into mal_code values (2, 'Web-Shell');
insert into mal_code values (3, 'RFI');
insert into mal_code values (4, 'XSS');

-- # 현재 시간 ~ 5분 전
insert into total(ip, timestamp, method, uri, protocol, res_code, res_data_size, referer) values('192.168.0.121', '2021-08-17T15:59:36.946549', 'GET', '/user', 'HTTP1.1', 200, 14, '/');
insert into total(ip, timestamp, method, uri, protocol, res_code, res_data_size, referer) values('211.168.0.74', '2021-08-17T15:59:36.946549', 'GET', '/user', 'HTTP1.1', 200, 14, '/');
insert into total(ip, timestamp, method, uri, protocol, res_code, res_data_size, referer) values('12.18.10.42', '2021-08-17T15:59:36.946549', 'GET', '/user', 'HTTP1.1', 200, 14, '/');
insert into total(ip, timestamp, method, uri, protocol, res_code, res_data_size, referer) values('54.68.20.41', '2021-08-17T15:59:36.946549', 'GET', '/user', 'HTTP1.1', 200, 14, '/');
insert into total(ip, timestamp, method, uri, protocol, res_code, res_data_size, referer) values('54.68.20.41', '2021-08-17T15:59:36.946549', 'GET', '/user', 'HTTP1.1', 200, 14, '/');
insert into total(ip, timestamp, method, uri, protocol, res_code, res_data_size, referer) values('54.68.20.41', '2021-08-17T15:59:36.946549', 'GET', '/user', 'HTTP1.1', 200, 14, '/');
insert into total(ip, timestamp, method, uri, protocol, res_code, res_data_size, referer) values('54.68.20.41', '2021-08-17T15:59:36.946549', 'GET', '/user', 'HTTP1.1', 200, 14, '/');
insert into total(ip, timestamp, method, uri, protocol, res_code, res_data_size, referer) values('54.68.20.41', '2021-08-17T15:59:36.946549', 'GET', '/user', 'HTTP1.1', 200, 14, '/');
insert into total(ip, timestamp, method, uri, protocol, res_code, res_data_size, referer) values('54.68.20.41', '2021-08-17T15:59:36.946549', 'GET', '/user', 'HTTP1.1', 200, 14, '/');
insert into total(ip, timestamp, method, uri, protocol, res_code, res_data_size, referer) values('54.68.20.41', '2021-08-17T15:59:36.946549', 'GET', '/user', 'HTTP1.1', 200, 14, '/');

-- # 현재 시간 ~ 8시간 전

-- # 현재 시간 ~ 7주 전

-- # 비정상 로그
insert into abnormal(ip, timestamp, method, uri, protocol, res_code, res_data_size, referer, mal_code_code) values('54.68.20.41', '2021-08-17T15:59:36.946549', 'GET', '/user', 'HTTP1.1', 200, 14, '/', 1);
insert into abnormal(ip, timestamp, method, uri, protocol, res_code, res_data_size, referer, mal_code_code) values('54.68.20.41', '2021-08-17T15:59:36.946549', 'GET', '/user', 'HTTP1.1', 200, 14, '/', 2);
insert into abnormal(ip, timestamp, method, uri, protocol, res_code, res_data_size, referer, mal_code_code) values('54.68.20.41', '2021-08-17T15:59:36.946549', 'GET', '/user', 'HTTP1.1', 200, 14, '/', 3);
insert into abnormal(ip, timestamp, method, uri, protocol, res_code, res_data_size, referer, mal_code_code) values('54.68.20.41', '2021-08-17T15:59:36.946549', 'GET', '/user', 'HTTP1.1', 200, 14, '/', 4);
insert into abnormal(ip, timestamp, method, uri, protocol, res_code, res_data_size, referer, mal_code_code) values('54.68.20.41', '2021-08-17T15:59:36.946549', 'GET', '/user', 'HTTP1.1', 200, 14, '/', 4);
insert into abnormal(ip, timestamp, method, uri, protocol, res_code, res_data_size, referer, mal_code_code) values('54.68.20.41', '2021-08-17T15:59:36.946549', 'GET', '/user', 'HTTP1.1', 200, 14, '/', 4);
insert into abnormal(ip, timestamp, method, uri, protocol, res_code, res_data_size, referer, mal_code_code) values('54.68.20.41', '2021-08-17T15:59:36.946549', 'GET', '/user', 'HTTP1.1', 200, 14, '/', 4);
insert into abnormal(ip, timestamp, method, uri, protocol, res_code, res_data_size, referer, mal_code_code) values('54.68.20.41', '2021-08-17T15:59:36.946549', 'GET', '/user', 'HTTP1.1', 200, 14, '/', 4);
insert into abnormal(ip, timestamp, method, uri, protocol, res_code, res_data_size, referer, mal_code_code) values('54.68.20.41', '2021-08-17T15:59:36.946549', 'GET', '/user', 'HTTP1.1', 200, 14, '/', 4);
insert into abnormal(ip, timestamp, method, uri, protocol, res_code, res_data_size, referer, mal_code_code) values('54.68.20.41', '2021-08-17T15:59:36.946549', 'GET', '/user', 'HTTP1.1', 200, 14, '/', 4);
insert into abnormal(ip, timestamp, method, uri, protocol, res_code, res_data_size, referer, mal_code_code) values('54.68.20.41', '2021-08-17T15:59:36.946549', 'GET', '/user', 'HTTP1.1', 200, 14, '/', 4);

-- # 미확인 로그
insert into unknown_log(ip, data) values ('192.168.0.1', '/method, HTTP1.1 200, 14');
insert into unknown_log(ip, data) values ('192.168.0.1', '/method, HTTP1.1 200, 14');
insert into unknown_log(ip, data) values ('192.168.0.1', '/method, HTTP1.1 200, 14');
insert into unknown_log(ip, data) values ('192.168.0.1', '/method, HTTP1.1 200, 14');
insert into unknown_log(ip, data) values ('192.168.0.1', '/method, HTTP1.1 200, 14');
insert into unknown_log(ip, data) values ('192.168.0.1', '/method, HTTP1.1 200, 14');
insert into unknown_log(ip, data) values ('192.168.0.1', '/method, HTTP1.1 200, 14');
insert into unknown_log(ip, data) values ('192.168.0.1', '/method, HTTP1.1 200, 14');
insert into unknown_log(ip, data) values ('192.168.0.1', '/method, HTTP1.1 200, 14');
insert into unknown_log(ip, data) values ('192.168.0.1', '/method, HTTP1.1 200, 14');
insert into unknown_log(ip, data) values ('192.168.0.1', '/method, HTTP1.1 200, 14');