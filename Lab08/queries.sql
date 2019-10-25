--
-- This SQL script queries the monopoly database.
--
-- @author Bryant George (beg23)
-- @version 10/25/2019
--
-- Note that because of how I have changed the sample data in monopoly.sql,
--  my results will be different than if you query Vander Linden's sample data.
--

-- 8.1.a

-- SELECT * FROM Game ORDER BY time DESC;

-- 8.1.b

-- SELECT * FROM Game WHERE time >= '2019-10-19' AND time < '2019-10-26';
-- or
-- SELECT * FROM Game WHERE time >= (NOW() - INTERVAL '7 days');

-- 8.1.c

-- SELECT * FROM Player WHERE name IS NOT NULL;

-- 8.1.d

-- SELECT DISTINCT playerID FROM PlayerGame WHERE cash > 2000;

-- 8.1.e

-- SELECT * FROM Player WHERE emailAddress LIKE '%@gmail.edu';

-- 8.2.a

-- SELECT PlayerGame.cash FROM PlayerGame, Player WHERE Player.ID = PlayerGame.playerID AND Player.name = 'The King' ORDER BY PlayerGame.cash DESC;

-- 8.2.b

-- SELECT Player.name FROM Player, PlayerGame, Game WHERE Game.time = '2006-06-28 13:20:00' AND PlayerGame.gameID = Game.ID AND PlayerGame.playerID = Player.ID ORDER BY PlayerGame.cash DESC LIMIT 1;

-- 8.2.c

-- The P1.ID < P2.ID ensures unique Player ID's share the same name, thus preventing the query from returning all Player names,
--  since P1.name = P2.name always if P1.ID = P2.ID.

-- 8.2.d

-- You may want to join a table to itself to look for multiple accounts with the same email to prevent dupe accounts.
--  Or in this monopoly example, you could join the Game table to itself to find days where multiple games occured on the same day.
