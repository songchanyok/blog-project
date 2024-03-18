CREATE TABLE article (
    id BIGINT AUTO_INCREMENT primary key,
    title varchar(255) not null,
    content varchar(255) not null,
    createTime timestamp not null default current_timestamp,
    updateTime timestamp not null default current_timestamp on update current_timestamp
);

CREATE TABLE comment(
    id BIGINT AUTO_INCREMENT primary key,
    comment varchar(255) not null,
    date timestamp not null default current_timestamp on update current_timestamp,
    article_id BIGINT not null
);

INSERT INTO article (title, content) VALUES ('블로그 제목', '블로그 내용');
INSERT INTO article (title, content) VALUES ('블로그 제목2', '블로그 내용2');
INSERT INTO article (title, content) VALUES ('블로그 제목3', '블로그 내용3');

INSERT INTO comment (comment,article_id) VALUES('댓글1',1);