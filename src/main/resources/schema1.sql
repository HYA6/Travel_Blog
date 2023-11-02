CREATE TABLE `USERINFO` (
	`user_num`	INT	NOT NULL,
	`user_id`	VARCHAR(100)	NOT NULL,
	`user_name`	VARCHAR(10)	NOT NULL,
	`user_password`	VARCHAR(100)	NULL,
	`user_age`	INT	NOT NULL,
	`user_email`	VARCHAR(100)	NOT NULL,
	`user_birhtday`	VARCHAR(20)	NOT NULL,
	`user_phone`	VARCHAR(20)	NULL,
	`user_gender`	VARCHAR(10)	NULL,
	`user_nickname`	VARCHAR(20)	NULL,
	`user_date`	DATE	NOT NULL
);

CREATE TABLE `POST` (
	`post_id`	INT	NOT NULL,
	`blog_id`	INT	NOT NULL,
	`category_id`	INT	NOT NULL,
	`post_form`	VARCHAR(10)	NOT NULL,
	`post_start_date`	VARCHAR(10)	NOT NULL,
	`post_end_date`	VARCHAR(10)	NOT NULL,
	`post_place`	VARCHAR(100)	NOT NULL,
	`post_subject`	VARCHAR(100)	NOT NULL,
	`post_tag`	VARCHAR(100)	NULL,
	`post_thumbnail`	VARCHAR(100)	NULL,
	`post_write`	DATE	NOT NULL,
	`post_update`	DATE	NULL,
	`post_visits`	INT	NULL	DEFAULT 0,
	`post_likes`	INT	NULL	DEFAULT 0,
	`post_comments`	INT	NULL	DEFAULT 0
);

CREATE TABLE `CATEGORY` (
	`category_id`	INT	NOT NULL,
	`blog_id`	INT	NOT NULL,
	`category_name`	VARCHAR(100)	NOT NULL,
	`category_private`	VARCHAR(10)	NOT NULL,
	`category_gup`	INT	NULL,
	`category_lev`	INT	NULL,
	`category_seq`	INT	NULL
);

CREATE TABLE `COMMENTS` (
	`comment_id`	INT	NOT NULL,
	`user_num`	INT	NOT NULL,
	`post_id`	INT	NOT NULL,
	`comment_content`	VARCHAR(200)	NOT NULL,
	`comment_date`	DATE	NOT NULL,
	`comment_gup`	INT	NULL,
	`comment_lev`	INT	NULL,
	`comment_seq`	INT	NULL
);

CREATE TABLE `BLOG` (
	`blog_id`	INT	NOT NULL,
	`user_num`	INT	NOT NULL,
	`blog_url`	VARCHAR(100)	NOT NULL,
	`blog_name`	VARCHAR(20)	NOT NULL,
	`blog_thema`	VARCHAR(10)	NOT NULL
);

CREATE TABLE `FLOW` (
	`flow_num`	INT	NOT NULL,
	`user_num`	INT	NOT NULL,
	`flow_id`	VARCHAR(100)	NOT NULL,
	`flow_nickName`	VARCHAR(10)	NULL,
	`flow_date`	DATE	NOT NULL,
	`flow_status`	VARCHAR(20)	NULL
);

CREATE TABLE `POST_IMAGES` (
	`image_id`	INT	NOT NULL,
	`post_id`	INT	NOT NULL,
	`image_name`	VARCHAR(100)	NOT NULL,
	`image_gup`	INT	NULL,
	`image_seq`	INT	NULL
);

CREATE TABLE `SNS` (
	`sns_id`	INT	NOT NULL,
	`user_num`	INT	NOT NULL,
	`sns_name`	VARCHAR(20)	NOT NULL,
	`sns_url`	VARCHAR(100)	NOT NULL
);

CREATE TABLE `USER_PROFILE` (
	`user_num`	INT	NOT NULL,
	`user_image`	VARCHAR(100)	NULL,
	`user_info`	VARCHAR(300)	NULL
);

CREATE TABLE `LIKED_COMMENTS` (
	`liked_id`	INT	NOT NULL,
	`user_num`	INT	NOT NULL,
	`comment_id`	INT	NOT NULL,
	`liked`	VARCHAR(10)	NULL
);

CREATE TABLE `LIKED_POST` (
	`liked_id`	INT	NOT NULL,
	`user_num`	INT	NOT NULL,
	`post_id`	INT	NOT NULL,
	`liked`	VARCHAR(10)	NULL
);

CREATE TABLE `POST_VISIT` (
	`visit_id`	INT	NOT NULL,
	`user_num`	INT	NOT NULL,
	`post_id`	INT	NOT NULL,
	`visit_date`	DATE	NOT NULL
);

CREATE TABLE `POST_TEXTS` (
	`text_id`	INT	NOT NULL,
	`post_id`	INT	NOT NULL,
	`text`	VARCHAR(1000)	NOT NULL,
	`text_gup`	INT	NULL,
	`text_seq`	INT	NULL
);

ALTER TABLE `USERS` ADD CONSTRAINT `PK_USERS` PRIMARY KEY (
	`user_num`
);

ALTER TABLE `POST` ADD CONSTRAINT `PK_POST` PRIMARY KEY (
	`post_id`
);

ALTER TABLE `CATEGORY` ADD CONSTRAINT `PK_CATEGORY` PRIMARY KEY (
	`category_id`
);

ALTER TABLE `COMMENTS` ADD CONSTRAINT `PK_COMMENTS` PRIMARY KEY (
	`comment_id`
);

ALTER TABLE `BLOG` ADD CONSTRAINT `PK_BLOG` PRIMARY KEY (
	`blog_id`
);

ALTER TABLE `FLOW` ADD CONSTRAINT `PK_FLOW` PRIMARY KEY (
	`flow_num`
);

ALTER TABLE `POST_IMAGES` ADD CONSTRAINT `PK_POST_IMAGES` PRIMARY KEY (
	`image_id`
);

ALTER TABLE `SNS` ADD CONSTRAINT `PK_SNS` PRIMARY KEY (
	`sns_id`
);

ALTER TABLE `USER_PROFILE` ADD CONSTRAINT `PK_USER_PROFILE` PRIMARY KEY (
	`user_num`
);

ALTER TABLE `LIKED_COMMENTS` ADD CONSTRAINT `PK_LIKED_COMMENTS` PRIMARY KEY (
	`liked_id`
);

ALTER TABLE `LIKED_POST` ADD CONSTRAINT `PK_LIKED_POST` PRIMARY KEY (
	`liked_id`
);

ALTER TABLE `POST_VISIT` ADD CONSTRAINT `PK_POST_VISIT` PRIMARY KEY (
	`visit_id`
);

ALTER TABLE `POST_TEXTS` ADD CONSTRAINT `PK_POST_TEXTS` PRIMARY KEY (
	`text_id`
);

ALTER TABLE `USER_PROFILE` ADD CONSTRAINT `FK_USERS_TO_USER_PROFILE_1` FOREIGN KEY (
	`user_num`
)
REFERENCES `USERS` (
	`user_num`
);

