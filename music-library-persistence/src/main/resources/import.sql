

INSERT INTO Genre (title) VALUES ('Pop')
INSERT INTO Genre (title) VALUES ('Rock')
INSERT INTO Genre (title) VALUES ('Punk')
INSERT INTO Genre (title) VALUES ('Funk')
INSERT INTO Genre (title) VALUES ('Metal')
INSERT INTO Genre (title) VALUES ('Country')

INSERT INTO Artist (name, dateOfBirth) VALUES('Linkin Park', '1996-06-20')
INSERT INTO Artist (name, dateOfBirth) VALUES('Adele', '1988-05-05')
INSERT INTO Artist (name, dateOfBirth) VALUES('Red Hot Chili Peppers', '1983-01-05')


INSERT INTO Album (title, artist_id, commentary, dateOfRelease) VALUES ('Living Things', '1','Living Things is the fifth studio album by Linkin Park', '2012-06-20')
INSERT INTO Album (title, artist_id, commentary, dateOfRelease) VALUES ('Minutes To Midnight', '1','Minutes to Midnight is the third studio album by American rock band Linkin Park, released on May 14, 2007, through Warner Bros. Records. The album was produced by Mike Shinoda and Rick Rubin.', '2007-05-14')
INSERT INTO Album (title, artist_id, commentary, dateOfRelease) VALUES ('Meteora', '1','Meteora is the second studio album by American rock band Linkin Park. It was released on March 25, 2003 through Warner Bros. Records.', '2003-03-25')

INSERT INTO Album (title, artist_id, commentary, dateOfRelease) VALUES ('21', '2','21 is the second studio album by British singer Adele. The album was named after the age of the singer during its production. ', '2014-01-24')
INSERT INTO Album (title, artist_id, commentary, dateOfRelease) VALUES ('25', '2','25 is the third studio album by British singer and songwriter Adele. Following the release and international success of her second studio album 21.', '2015-11-20')

INSERT INTO Album (title, artist_id, commentary, dateOfRelease) VALUES ('Californication', '3','Californication is the Chili Peppers'' most commercially successful studio release internationally, with over 15 million copies sold worldwide, and more than 6 million in the United States alone.', '1999-07-08')
INSERT INTO Album (title, artist_id, commentary, dateOfRelease) VALUES ('The Getaway', '3','The album was preceded by the first single "Dark Necessities" on May 5, 2016. "Dark Necessities" became the band''s 13th number-one single on the Billboard Alternative Songs chart.', '2016-05-05')



INSERT INTO Users (admin, email, firstName, lastName, passwordHash) VALUES ('true', 'admin@musiclib.com', 'Admin', 'MusicLibovy', 'hashThat')
INSERT INTO Users (admin, email, firstName, lastName, passwordHash) VALUES ('false', 'user1@musiclib.com', 'Basic', 'User', 'hashThat')
INSERT INTO Users (admin, email, firstName, lastName, passwordHash) VALUES ('false', 'piskula@gmail.com', 'Piskula', 'Oravcok', 'hashThat')

INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Lost in the echo', 'https://www.youtube.com/watch?v=co4YpHTqmfQ', '1', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('In My Remains', 'https://www.youtube.com/watch?v=QLFiuNdQrzI', '1', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Burn It Down', 'https://www.youtube.com/watch?v=dxytyRy-O1k', '1', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Lies Greed Misery', 'https://www.youtube.com/watch?v=9Dq9q6afIP8', '1', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('I''ll Be Gone', 'https://www.youtube.com/watch?v=Y1wM5ljye28', '1', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Castle of Glass', 'https://www.youtube.com/watch?v=ScNNfyq3d_w', '1', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Victimized', 'https://www.youtube.com/watch?v=-6eUCOFXuxo', '1', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Roads Untraveled', 'https://www.youtube.com/watch?v=KLgQKiUk7ms', '1', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Skin To Bone', 'https://www.youtube.com/watch?v=NwK4mxK7c2w', '1', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Until It Breaks', 'https://www.youtube.com/watch?v=ZEQJm49_9OE', '1', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Tinfoil', 'https://www.youtube.com/watch?v=M2YPuL-niQg', '1', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Powerless', 'https://www.youtube.com/watch?v=32BOmle7Z6w', '1', '1', '2')

INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Wake', 'https://www.youtube.com/watch?v=Me7TJDHCELk', '2', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Given Up', 'https://www.youtube.com/watch?v=0xyxtzD54rM', '2', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Leave Out All The Rest', 'https://www.youtube.com/watch?v=yZIummTz9mM', '2', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Bleed It Out', 'https://www.youtube.com/watch?v=OnuuYcqhzCE', '2', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Shadow Of The Day', 'https://www.youtube.com/watch?v=n1PCW0C1aiM', '2', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('What I''ve Done', 'https://www.youtube.com/watch?v=8sgycukafqQ', '2', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Hands Held High', 'https://www.youtube.com/watch?v=gG4P3ayBzVY', '2', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('No More Sorrow', 'https://www.youtube.com/watch?v=rW4uBvP2Dqc', '2', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Valentine''s Day', 'https://www.youtube.com/watch?v=KAFOpywZbMM', '2', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('In Between', 'https://www.youtube.com/watch?v=YgVzhgygYfs', '2', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('In Pieces', 'https://www.youtube.com/watch?v=NaRBn6QIMcQ', '2', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('The Little Things Give You Away', 'https://www.youtube.com/watch?v=Gs0t8LXH6lw', '2', '1', '2')

INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Foreword', 'https://www.youtube.com/watch?v=U6R-twDkrcI', '3', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Don''t stay', 'https://www.youtube.com/watch?v=oWfGOVWrueo', '3', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Somewhere I Belong', 'https://www.youtube.com/watch?v=zsCD5XCu6CM', '3', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Lying From You', 'https://www.youtube.com/watch?v=NjdgcHdzvac', '3', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Hit The Floor', 'https://www.youtube.com/watch?v=oMals9XXQY8', '3', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Easier To Run', 'https://www.youtube.com/watch?v=U5zdmjVeQzE', '3', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Faint', 'https://www.youtube.com/watch?v=LYU-8IFcDPw', '3', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Figure.09', 'https://www.youtube.com/watch?v=LpC0SKU6O00', '3', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Breaking The Habit', 'https://www.youtube.com/watch?v=v2H4l9RpkwM', '3', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('From The Inside', 'https://www.youtube.com/watch?v=YLHpvjrFpe0', '3', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Nobody''s Listening', 'https://www.youtube.com/watch?v=QJ87793QXes', '3', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Session', 'https://www.youtube.com/watch?v=J1KqQYsUYIk', '3', '1', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Numb', 'https://www.youtube.com/watch?v=kXYiU_JCYtU', '3', '1', '2')

INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Rolling in the Deep', 'https://www.youtube.com/watch?v=rYEDA3JcQqw', '4', '2', '1')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Turning Tables', 'https://www.youtube.com/watch?v=omkUG4kugvw', '4', '2', '1')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Don''t You Remember', 'https://www.youtube.com/watch?v=RDRwqTNLGDs', '4', '2', '1')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Rumour Has It', 'https://www.youtube.com/watch?v=uK3MLlTL5Ko', '4', '2', '1')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Set Fire to the Rain', 'https://www.youtube.com/watch?v=Ri7-vnrJD3k', '4', '2', '1')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Someone like You', 'https://www.youtube.com/watch?v=hLQl3WQQoQ0', '4', '2', '1')

INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Hello', 'https://www.youtube.com/watch?v=YQHsXMglC9A', '5', '2', '1')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Send My Love (To Your New Lover)', 'https://www.youtube.com/watch?v=fk4BbF7B29w', '5', '2', '1')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('I Miss You', 'https://www.youtube.com/watch?v=qWK8xH_ZHAw', '5', '2', '1')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('When We Were Young', 'https://www.youtube.com/watch?v=413YpOZh0nI', '5', '2', '1')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Remedy', 'https://www.youtube.com/watch?v=N7yaZjznM8Y', '5', '2', '1')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Water Under the Bridge', 'https://www.youtube.com/watch?v=s_T5bpmBrwI', '5', '2', '1')

INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Around the World', 'https://www.youtube.com/watch?v=a9eNQZbjpJk', '6', '3', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Parallel Universe', 'https://www.youtube.com/watch?v=oy_7_tvtc7M', '6', '3', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Scar Tissue', 'https://www.youtube.com/watch?v=mzJj5-lubeM', '6', '3', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Otherside', 'https://www.youtube.com/watch?v=rn_YodiJO6k', '6', '3', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Get on Top', 'https://www.youtube.com/watch?v=hUaHLU3pM-w', '6', '3', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Californication', 'https://www.youtube.com/watch?v=YlUKcNNmywk', '6', '3', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Easily', 'https://www.youtube.com/watch?v=wSxZgmRGtdI', '6', '3', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Porcelain', 'https://www.youtube.com/watch?v=aCw6DIQHuhM', '6', '3', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Right on Time', 'https://www.youtube.com/watch?v=W8rnegWFlXs', '6', '3', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Road Trippin', 'https://www.youtube.com/watch?v=11GYvfYjyV0', '6', '3', '2')

INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('The Getaway', 'https://www.youtube.com/watch?v=5w4d_mmJOGM', '7', '3', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Dark Necessities', 'https://www.youtube.com/watch?v=Q0oIoR9mLwc', '7', '3', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('We Turn Red', 'https://www.youtube.com/watch?v=I7rlCyvHejY', '7', '3', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Go Robot', 'https://www.youtube.com/watch?v=HI-8CVixZ5o', '7', '3', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('The Longest Wave', 'https://www.youtube.com/watch?v=10k8IuT636g', '7', '3', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Goodbye Angels', 'https://www.youtube.com/watch?v=C9Ir68MO2zg', '7', '3', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Sick Love', 'https://www.youtube.com/watch?v=I2XfVml6o24', '7', '3', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Feasting on the Flowers', 'https://www.youtube.com/watch?v=dK64johvmSA', '7', '3', '2')
INSERT INTO Song (title , youtubeLink, album_id, artist_id, genre_id) VALUES ('Dreams of a Samurai', 'https://www.youtube.com/watch?v=_Qj9j94fdZY', '7', '3', '2')



