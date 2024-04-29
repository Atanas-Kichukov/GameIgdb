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
VALUES(7,'MOBA');
INSERT INTO game_categories(id,categories)
VALUES(8,'MMORPG');
INSERT INTO game_categories(id,categories)
VALUES(9,'CO_OP');



-- games and pictures
INSERT INTO pictures(id,url,public_id)
VALUES(1, 'https://res.cloudinary.com/dwfhxuzqh/image/upload/v1713100382/dxnlczhsvnvvznjp0pum.webp','dxnlczhsvnvvznjp0pum.webp');
INSERT INTO games(id, name, age_restriction, picture_id, video_url, description, release_date, company, avg_rating, rating_count, author_id)
VALUES(1, 'Valorant', 16, 1, 'Wrdh5HrOCMc','Valorant is a free-to-play first-person tactical hero shooter developed and published by Riot Games,
for Windows. Teased under the codename Project A in October 2019, the game began a closed beta period with limited access on April 7, 2020,
followed by a release on June 2, 2020. The development of the game started in 2014. Valorant takes inspiration from the Counter-Strike series,
borrowing several mechanics such as the buy menu, spray patterns, and inaccuracy while moving.', '2020-06-02', 'Riot Games', 7.5,1,2);
INSERT INTO games_categories(game_entity_id, categories_id)
VALUES(1,5);

INSERT INTO pictures(id,url,public_id)
VALUES(2, 'https://res.cloudinary.com/dwfhxuzqh/image/upload/v1713098504/lwdcmydcwuqjwgxirhk2.jpg','lwdcmydcwuqjwgxirhk2.jpg');
INSERT INTO games(id, name, age_restriction, picture_id, video_url, description, release_date, company, avg_rating, rating_count, author_id)
VALUES(2, 'League Of Legends', 13, 2, 'p4QG59y6FGE','League of Legends (LoL), commonly referred to as League, is a 2009 multiplayer online battle
 arena video game developed and published by Riot Games. Inspired by Defense of the Ancients, a custom map for Warcraft III. Since its release in
 October 2009, League has been free-to-play and is monetized through purchasable character customization. The game is available for Microsoft Windows
and macOS. In the game, two teams of five players battle in player-versus-player combat, each team occupying and defending their half of the map.
 Each of the ten players controls a character, known as a "champion", with unique abilities and differing styles of play. During a match, champions
 become more powerful by collecting experience points, earning gold, and purchasing items to defeat the opposing team. In Leagues main mode, Summoners Rift
 , a team wins by pushing through to the enemy base and destroying their "Nexus", a large structure located within.', '2009-10-27', 'Riot Games', 4.6,1,1);
INSERT INTO games_categories(game_entity_id, categories_id)
VALUES(1,1);
INSERT INTO games_categories(game_entity_id, categories_id)
VALUES(1,2);

INSERT INTO pictures(id,url,public_id)
VALUES(3, 'https://res.cloudinary.com/dwfhxuzqh/image/upload/v1713377122/rneke9cgdnbw5z6kkudz.jpg','rneke9cgdnbw5z6kkudz.jpg');
INSERT INTO games(id, name, age_restriction, picture_id, video_url, description, release_date, company, avg_rating, rating_count, author_id)
VALUES(3, 'Need For Speed Payback', 12, 3, 'kc-OcOduEx0','Set in the underworld of Fortune Valley, you and your crew were divided by betrayal and
reunited by revenge to take down The House, a nefarious cartel that rules the city’s casinos, criminals and cops. In this corrupt gambler’s paradise
, the stakes are high and The House always wins.   Craft unique rides with deeper performance and visual customization than ever before. Push them to
 the limit when you narrowly escape the heat in epic cop battles. From insane heist missions to devastating car battles to jaw-dropping set piece moments,
  Need for Speed Payback delivers an edge-of-your-seat, adrenaline-fueled action-driving fantasy.', '2017-11-10', 'Ghost Games', 7.0,1,2);
INSERT INTO games_categories(game_entity_id, categories_id)
VALUES(3,6);
INSERT INTO games_categories(game_entity_id, categories_id)
VALUES(3,2);

INSERT INTO pictures(id,url,public_id)
VALUES(4, 'https://res.cloudinary.com/dwfhxuzqh/image/upload/v1713377083/ysuxnybff2tilksalnys.webp','ysuxnybff2tilksalnys.webp');
INSERT INTO games(id, name, age_restriction, picture_id, video_url, description, release_date, company, avg_rating, rating_count, author_id)
VALUES(4, 'Mortal Kombat 11 Ultimate', 18, 4, '86D2q2Uvg9M','Mortal Kombat: Deception is a 2004 fighting game developed and published by Midway.
 It is the sixth main installment in the Mortal Kombat franchise and a sequel to 2002 Mortal Kombat: Deadly Alliance. It was released for the
  PlayStation 2 and Xbox in October 2004, for the GameCube in March 2005 and later ported for the PlayStation Portable under the title Mortal Kombat:
  Unchained in November 2006. Mortal Kombat: Deception follows the storyline from the fifth installment, Deadly Alliance. Its story centers on the revival
  of the Dragon King Onaga, who attempts to conquer the realms featured in the series after defeating the sorcerers Quan Chi and Shang Tsung, the main
   antagonists in the previous game, and the Thunder God Raiden, defender of Earthrealm. The surviving warriors from the previous titles join forces to
    confront Onaga.Twenty-six characters are available to play in the game, with nine making their first appearance in the series. Deception contains several
     new features in the series, such as chess and puzzle games with the MK characters and an online mode. The Konquest Mode returns from Deadly Alliance,
      but follows the life of Shujinko, a warrior who is deceived by Onaga to search for artifacts to give Onaga more powers. Deception Konquest Mode differs
       greatly from the Konquest Mode of Deadly Alliance, however, containing elements of open-world exploration in between story progression, rather than the
        Kombat Tower of Deadly Alliance.', '2019-04-23', 'NetherRealm Studios', 9.0,1,1);
INSERT INTO games_categories(game_entity_id, categories_id)
VALUES(4,3);
INSERT INTO games_categories(game_entity_id, categories_id)
VALUES(4,1);
INSERT INTO games_categories(game_entity_id, categories_id)
VALUES(4,2);


INSERT INTO pictures(id,url,public_id)
VALUES(5, 'https://res.cloudinary.com/dwfhxuzqh/image/upload/v1713376271/wql5uhmevcxwatrxbeib.webp','ItTakesTwo');
INSERT INTO games(id, name, age_restriction, picture_id, video_url, description, release_date, company, avg_rating, rating_count, author_id)
VALUES(5, 'It Takes Two', 12, 5, 'GAWHzGNcTEw','Embark on the craziest journey of your life in It Takes Two, a genre-bending platform adventure
created purely for co-op. Invite a friend to join for free with Friend’s Pass and work together across a huge variety of gleefully disruptive
gameplay challenges. Play as the clashing couple Cody and May, two humans turned into dolls by a magic spell. Together, trapped in a fantastical
 world where the unpredictable hides around every corner, they are reluctantly challenged with saving their fractured relationship.
 Master unique and connected character abilities in every new level. Help each other across an abundance of unexpected obstacles and laugh-out-loud
  moments. Kick gangster squirrels’ furry tails, pilot a pair of underpants, DJ a buzzing night club, and bobsleigh through a magical snow globe.
  Embrace a heartfelt and hilarious story where narrative and gameplay weave into a uniquely metaphorical experience.
It Takes Two is developed by the award-winning studio Hazelight, the industry leader of cooperative play. They’re about to take you on a wild and
wondrous ride where only one thing is for certain: we’re better together.', '2021-03-26', 'Electronic Arts', 10.0,1,3);
INSERT INTO games_categories(game_entity_id, categories_id)
VALUES(5,9);
INSERT INTO games_categories(game_entity_id, categories_id)
VALUES(5,1);
INSERT INTO games_categories(game_entity_id, categories_id)
VALUES(5,2);

INSERT INTO pictures(id,url,public_id)
VALUES(6, 'https://res.cloudinary.com/dwfhxuzqh/image/upload/v1713376968/v2ahdfrfa8u8rsxbx5vk.webp','wql5uhmevcxwatrxbeib.webp');
INSERT INTO games(id, name, age_restriction, picture_id, video_url, description, release_date, company, avg_rating, rating_count, author_id)
VALUES(6, 'Elden Ring', 16, 6, 'AKXiKBnzpBQ','Elden Ring is a 2022 action role-playing game developed by FromSoftware.
 It was directed by Hidetaka Miyazaki with worldbuilding provided by fantasy writer George R. R. Martin. It was published
 for PlayStation 4, PlayStation 5, Windows, Xbox One, and Xbox Series X/S on February 25 by FromSoftware in Japan and Bandai
 Namco Entertainment internationally. Players control a customizable player character who is on a quest to repair the Elden Ring
  and become the new Elden Lord.Elden Ring is presented through a third-person perspective; players freely roam its interactive
  open world. The six main areas are traversed using the player character steed Torrent as the primary mode of travel. Linear,
  hidden dungeons can be explored to find useful items. Players can use several types of weapons and magic spells, including non-direct
   engagement enabled by stealth mechanics. Throughout the game world, checkpoints enable fast travel and allow players to improve
   their attributes using an in-game currency called runes. Elden Ring features an online multiplayer mode in which players join through
    cooperative play to fight bosses or engage in player-versus-player combat.', '2022-02-25', 'Bandai Namco', 7.9,1,2);
INSERT INTO games_categories(game_entity_id, categories_id)
VALUES(6,8);
INSERT INTO games_categories(game_entity_id, categories_id)
VALUES(6,1);
INSERT INTO games_categories(game_entity_id, categories_id)
VALUES(6,2);
INSERT INTO games_categories(game_entity_id, categories_id)
VALUES(6,3);

