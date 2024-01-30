use maindb;
drop table movie;
create table movie(
 movie_id VARCHAR(36) ,
    movie_title VARCHAR(255),
    director VARCHAR(255),
    producer VARCHAR(255),
    income_company VARCHAR(255),
    distribution_company VARCHAR(255),
    release_date VARCHAR(30),
    movie_type VARCHAR(255),
    movie_style VARCHAR(255),
    nationality VARCHAR(255),
    total_screen_count INT,
    sales_price DOUBLE,
    viewing_number INT,
    seoul_sales_price DOUBLE,
    seoul_viewing_number INT,
    genre VARCHAR(255),
    grade VARCHAR(255),
    movie_subdivision VARCHAR(255),
    primary key(genre)
);
select * from movie;
CREATE TABLE logs (
    id INT not null AUTO_INCREMENT PRIMARY KEY,  #AUTO_INCREMENT -> 추가 될때 마다 자동으로 번호 추가 
    log_message VARCHAR(255) NOT NULL
);
select * from logs;
drop table log;
