

CREATE TABLE if NOT EXISTS performer (
                  id BIGINT NOT NULL AUTO_INCREMENT,
                  professional_name VARCHAR(100) NOT NULL,
                  first_name VARCHAR(100) NOT NULL,
                  middle_name VARCHAR(100) NOT NULL,
                  last_name VARCHAR(100) NOT NULL,
                  PRIMARY KEY (id)
);

CREATE TABLE if NOT EXISTS album (
                id BIGINT NOT NULL AUTO_INCREMENT,
                performer_id BIGINT NOT NULL,
                edition_id BIGINT NOT NULL,
                 title VARCHAR(100) NOT NULL,
                 release_date DATE NOT NULL,
                 PRIMARY KEY (id),
                 FOREIGN KEY (performer_id) REFERENCES performer (id) ON DELETE CASCADE

);

CREATE TABLE if NOT EXISTS edition (
                 id BIGINT NOT NULL AUTO_INCREMENT,
                 album_id BIGINT NOT NULL,
                 description VARCHAR(100) NOT NULL,
                 PRIMARY KEY (id),
                 FOREIGN KEY (album_id) REFERENCES album (id) ON DELETE CASCADE
);

CREATE TABLE if NOT EXISTS track (
               id BIGINT NOT NULL AUTO_INCREMENT,
               edition_id BIGINT NOT NULL,
               track_number INT NOT NULL,
               title VARCHAR(100) NOT NULL,
               duration_in_seconds INT NOT NULL,
               PRIMARY KEY (id),
               FOREIGN KEY (edition_id) REFERENCES edition (id) ON DELETE CASCADE
);