INSERT INTO users(user_num, user_id, user_name, user_age, user_email, user_birhtday, user_password, user_date)
 VALUES (1, 'hya', '이하영', 24, 'sjo6382@gmail.com', '1999-06-08', '1234', NOW());

INSERT INTO blog(blog_id, blog_name, blog_thema, blog_url, user_num)
 VALUES (1, '여행 블로그', '국내여행,우정여행', 'http://localhost:9090/travel', 1);

INSERT INTO category(category_id, category_name, category_private, category_gup, category_lev, category_seq, blog_id)
 VALUES (1, '1번째 카테고리', 'N', 1, 0, 0, 1);

INSERT INTO post(post_id, post_form, post_start_date, post_end_date, post_place, post_subject, post_tag, post_thumbnail, post_write, post_likes, blog_id, category_id)
 VALUES (1, 'simple', '2023-10-11', '2023-10-14', '스위스', '1번째 게시글', '테스트', '1698890505707_1572710025871-9.jpg', NOW(), 5, 1, 1);
INSERT INTO post(post_id, post_form, post_start_date, post_end_date, post_place, post_subject, post_tag, post_thumbnail, post_write, post_likes, blog_id, category_id)
 VALUES (2, 'simple', '2023-10-11', '2023-10-14', '스위스', '2번째 게시글', '테스트', '1698890505707_1572710025871-9.jpg', NOW(), 7, 1, 1);
INSERT INTO post(post_id, post_form, post_start_date, post_end_date, post_place, post_subject, post_tag, post_thumbnail, post_write, post_likes, blog_id, category_id)
 VALUES (3, 'simple', '2023-10-11', '2023-10-14', '스위스', '3번째 게시글', '테스트', '1698890505707_1572710025871-9.jpg', NOW(), 9, 1, 1);
INSERT INTO post(post_id, post_form, post_start_date, post_end_date, post_place, post_subject, post_tag, post_thumbnail, post_write, post_likes, blog_id, category_id)
 VALUES (4, 'simple', '2023-10-11', '2023-10-14', '스위스', '4번째 게시글', '테스트', '1698890505707_1572710025871-9.jpg', NOW(), 1, 1, 1);
INSERT INTO post(post_id, post_form, post_start_date, post_end_date, post_place, post_subject, post_tag, post_thumbnail, post_write, post_likes, blog_id, category_id)
 VALUES (5, 'simple', '2023-10-11', '2023-10-14', '스위스', '5번째 게시글', '테스트', '1698890505707_1572710025871-9.jpg', NOW(), 0, 1, 1);
INSERT INTO post(post_id, post_form, post_start_date, post_end_date, post_place, post_subject, post_tag, post_thumbnail, post_write, post_likes, blog_id, category_id)
 VALUES (6, 'simple', '2023-10-11', '2023-10-14', '스위스', '6번째 게시글', '테스트', '1698890505707_1572710025871-9.jpg', NOW(), 10, 1, 1);

INSERT INTO POST_IMAGES(image_id, image_name, image_gup, image_seq, post_id)
 VALUES (1, '1698890505763_20230214_134124.jpg', 1, 1, 1);
INSERT INTO POST_IMAGES(image_id, image_name, image_gup, image_seq, post_id)
 VALUES (2, '1698890505763_20230214_134124.jpg', 1, 1, 2);
INSERT INTO POST_IMAGES(image_id, image_name, image_gup, image_seq, post_id)
 VALUES (3, '1698890505763_20230214_134124.jpg', 1, 1, 3);
INSERT INTO POST_IMAGES(image_id, image_name, image_gup, image_seq, post_id)
 VALUES (4, '1698890505763_20230214_134124.jpg', 1, 1, 4);
INSERT INTO POST_IMAGES(image_id, image_name, image_gup, image_seq, post_id)
 VALUES (5, '1698890505763_20230214_134124.jpg', 1, 1, 5);
INSERT INTO POST_IMAGES(image_id, image_name, image_gup, image_seq, post_id)
 VALUES (6, '1698890505763_20230214_134124.jpg', 1, 1, 6);

INSERT INTO POST_TEXTS(text_id, text, text_gup, text_seq, post_id)
 VALUES (1, '간단 양식 1번째 게시글', 1, 0, 1);
INSERT INTO POST_TEXTS(text_id, text, text_gup, text_seq, post_id)
 VALUES (2, '간단 양식 2번째 게시글', 1, 0, 2);
INSERT INTO POST_TEXTS(text_id, text, text_gup, text_seq, post_id)
 VALUES (3, '간단 양식 3번째 게시글', 1, 0, 3);
INSERT INTO POST_TEXTS(text_id, text, text_gup, text_seq, post_id)
 VALUES (4, '간단 양식 4번째 게시글', 1, 0, 4);
INSERT INTO POST_TEXTS(text_id, text, text_gup, text_seq, post_id)
 VALUES (5, '간단 양식 5번째 게시글', 1, 0, 5);
INSERT INTO POST_TEXTS(text_id, text, text_gup, text_seq, post_id)
 VALUES (6, '간단 양식 6번째 게시글', 1, 0, 6);