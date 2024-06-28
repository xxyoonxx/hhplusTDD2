CREATE TABLE lecture (
                         lecture_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         title VARCHAR(255) NOT NULL
);
CREATE TABLE lecture_detail (
                                lecture_detail_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                lecture_id BIGINT NOT NULL,
                                current_cnt INT NOT NULL,
                                capacity INT NOT NULL,
                                start_date TIMESTAMP NOT NULL
);
CREATE TABLE lecture_registration (
                                      lecture_registration_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      lecture_detail_id BIGINT NOT NULL,
                                      user_id BIGINT NOT NULL
);