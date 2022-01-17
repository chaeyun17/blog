INSERT INTO blog_post (id,created_by,created_on,is_active,updated_by,updated_on,author_id,content,title,view_count) VALUES
	 (1,'SYSTEM','2022-01-15 15:23:44.960815',true,'SYSTEM','2022-01-15 16:20:36.073239',1,'안녕하세요','첫글입니다',0),
	 (2,'SYSTEM','2022-01-15 15:40:43.80274',true,'SYSTEM','2022-01-15 16:21:15.820022',1,'안녕하세요2','두번째글입니다',0),
	 (3,'SYSTEM','2022-01-15 15:41:54.233516',true,'SYSTEM','2022-01-15 15:42:54.233516',1,'안녕하세요3','세번째글',0),
	 (4,'SYSTEM','2022-01-15 15:42:29.066113',true,'SYSTEM','2022-01-15 16:25:54.840985',1,'안녕하세요4','네번째글',0);

--SELECT setval('blog_post_id_seq', 4, true);
