

INSERT INTO Genre (title) VALUES ('Pop')
INSERT INTO Genre (title) VALUES ('Rock')
INSERT INTO Genre (title) VALUES ('Punk')
INSERT INTO Genre (title) VALUES ('Funk')
INSERT INTO Genre (title) VALUES ('Metal')
INSERT INTO Genre (title) VALUES ('Country')

INSERT INTO Artist (name, dateOfBirth) VALUES('Linkin Park', '1996-06-20')

INSERT INTO Album (title, commentary, dateOfRelease) VALUES ('Living Things', 'Living Things is the fifth studio album by ', '2012-06-20')

INSERT INTO Users (admin, email, firstName, lastName, passwordHash) VALUES ('true', 'admin@musiclib.com', 'Admin', 'MusicLibovy', 'hashThat')

COMMIT

INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Lost in the echo', 'https://www.youtube.com/watch?v=co4YpHTqmfQ', '1', '1', '2')