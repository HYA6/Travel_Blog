INSERT INTO users(user_num, user_id, user_name, user_age, user_email, user_birhtday, user_password, user_date)
 VALUES (1, 'hya', '이하영', 24, 'sjo6382@gmail.com', '1999-06-08', '1234', NOW());

INSERT INTO blog(blog_id, blog_name, blog_thema, blog_url, user_num)
 VALUES (1, '여행 블로그', '국내여행,우정여행', 'http://localhost:9090/travel', 1);

INSERT INTO category(category_id, category_name, category_private, category_gup, category_lev, category_seq, blog_id)
 VALUES (1, '1번째 카테고리', 'N', 1, 0, 0, 1);

