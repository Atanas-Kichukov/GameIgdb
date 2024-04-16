-- roles
INSERT INTO roles (id, role)
VALUES (1, 'ADMIN');
INSERT INTO roles (id, role)
VALUES (2, 'USER');
-- users
INSERT INTO users (id, username, password, first_name, last_name, email, age)
VALUES (1, 'daniel', 'ab0afa57ab0bd085eee4fccf0d6fff3f70db93c96680b370b8b7a0272afa75db5d23ce13b7262c91', 'Daniel','Gostev', 'gostev@gmail.com',29);

INSERT INTO users (id, username, password, first_name,last_name,email,age )
VALUES (2, 'martin', 'abd375b05a68cd42d4590bbab7e23c644ab2e05be2b220424baddef23d0c15a4f759231f40d1f91c', 'Martin','Sidov', 'marto@gmail.com',25);

INSERT INTO users (id, username, password, first_name,last_name,email,age )
VALUES (3, 'petia', '123456', 'Petia','Dimitrova', 'petia@gmail.com',22);
-- users roles
INSERT INTO users_roles (user_entity_id, roles_id)
VALUES (1, 2);

INSERT INTO users_roles (user_entity_id, roles_id)
VALUES (2, 1);
INSERT INTO users_roles (user_entity_id, roles_id)
VALUES (2, 2);

INSERT INTO users_roles (user_entity_id, roles_id)
VALUES (3,1);


-- game categories
INSERT INTO game_categories(id,categories)
VALUES(1,'ACTION');
INSERT INTO game_categories(id,categories)
VALUES(2,'ADVENTURE');
INSERT INTO game_categories(id,categories)
VALUES(3,'FIGHTING');
INSERT INTO game_categories(id,categories)
VALUES(4,'SPORT');
INSERT INTO game_categories(id,categories)
VALUES(5,'SHOOTER');
INSERT INTO game_categories(id,categories)
VALUES(6,'RACING');
INSERT INTO game_categories(id,categories)
VALUES(7,'PUZZLE');


-- games and pictures
INSERT INTO pictures(id,url,public_id)
VALUES(1, 'https://res.cloudinary.com/dwfhxuzqh/image/upload/v1713100382/dxnlczhsvnvvznjp0pum.webp','Valorant');
INSERT INTO games(id, name, age_restriction, picture_id, video_url, description, release_date, company, avg_rating, rating_count, author_id)
VALUES(1, 'Valorant', 16, 1, 'Wrdh5HrOCMc','Valorant is a free-to-play first-person tactical hero shooter developed and published by Riot Games, for Windows. Teased under the codename Project A in October 2019, the game began a closed beta period with limited access on April 7, 2020, followed by a release on June 2, 2020. The development of the game started in 2014. Valorant takes inspiration from the Counter-Strike series, borrowing several mechanics such as the buy menu, spray patterns, and inaccuracy while moving.', '2020-06-02', 'Riot Games', 7.5,1,2);
INSERT INTO games_categories(game_entity_id, categories_id)
VALUES(1,5);

INSERT INTO pictures(id,url,public_id)
VALUES(2, 'https://res.cloudinary.com/dwfhxuzqh/image/upload/v1713098504/lwdcmydcwuqjwgxirhk2.jpg','League Of Legends');
INSERT INTO games(id, name, age_restriction, picture_id, video_url, description, release_date, company, avg_rating, rating_count, author_id)
VALUES(2, 'League Of Legends', 13, 2, 'p4QG59y6FGE','League of Legends (LoL), commonly referred to as League, is a 2009 multiplayer online battle arena video game developed and published by Riot Games. Inspired by Defense of the Ancients, a custom map for Warcraft III. Since its release in October 2009, League has been free-to-play and is monetized through purchasable character customization. The game is available for Microsoft Windows and macOS. In the game, two teams of five players battle in player-versus-player combat, each team occupying and defending their half of the map. Each of the ten players controls a character, known as a "champion", with unique abilities and differing styles of play. During a match, champions become more powerful by collecting experience points, earning gold, and purchasing items to defeat the opposing team. In Leagues main mode, Summoners Rift, a team wins by pushing through to the enemy base and destroying their "Nexus", a large structure located within.', '2009-10-27', 'Riot Games', 4.6,1,1);
INSERT INTO games_categories(game_entity_id, categories_id)
VALUES(1,1);
INSERT INTO games_categories(game_entity_id, categories_id)
VALUES(1,2);

