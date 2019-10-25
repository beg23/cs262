--
-- This SQL script builds a monopoly database, deleting any pre-existing version.
--
-- @author kvlinden
-- @modifier Bryant George (beg23)
-- @version 10/12/2019
--

-- Drop previous versions of the tables if they they exist, in reverse order of foreign keys.
DROP TABLE IF EXISTS Property;
DROP TABLE IF EXISTS PlayerGame;
DROP TABLE IF EXISTS Game;
DROP TABLE IF EXISTS Player;
DROP TYPE IF EXISTS propertyName;
DROP TYPE IF EXISTS square;

-- Create necessary types
CREATE TYPE square AS ENUM (
	'go', 'mediterraneanAvenue', 'communityChest1', 'balticAvenue',
	'incomeTax', 'readingRailroad', 'orientalAvenue', 'chance1',
	'vermontAvenue', 'connecticutAvenue', 'jail', 'stCharlesPlace',
	'electiricCompany', 'statesAvenue', 'virginiaAvenue', 'pennsylvaniaRailroad',
	'stJamesPlace', 'communityChest2', 'tennesseeAvenue', 'newYorkAvenue',
	'freeParking', 'kentuckyAvenue', 'chance2', 'indianaAvenue',
	'illinoisAvenue', 'bAndORailroad', 'atlanticAvenue', 'ventnorAvenue',
	'waterWorks', 'marvinGardens', 'goToJail', 'pacificAvenue',
	'northCarolinaAvenue', 'communityChest3', 'pennsylvaniaAvenue', 'shortLine',
	'chance3', 'parkPlace', 'luxuryTax', 'boardwalk'
	);

CREATE TYPE propertyName AS ENUM (
	'mediterraneanAvenue', 'balticAvenue',
	'readingRailroad', 'orientalAvenue',
	'vermontAvenue', 'connecticutAvenue', 'stCharlesPlace',
	'electiricCompany', 'statesAvenue', 'virginiaAvenue', 'pennsylvaniaRailroad',
	'stJamesPlace', 'tennesseeAvenue', 'newYorkAvenue',
	'kentuckyAvenue', 'indianaAvenue',
	'illinoisAvenue', 'bAndORailroad', 'atlanticAvenue', 'ventnorAvenue',
	'waterWorks', 'marvinGardens', 'pacificAvenue',
	'northCarolinaAvenue', 'pennsylvaniaAvenue', 'shortLine',
	'parkPlace', 'boardwalk'
	);

-- Create the schema.
CREATE TABLE Player (
	ID integer PRIMARY KEY, 
	emailAddress varchar(50) NOT NULL,
	name varchar(50)
	);

CREATE TABLE Game (
	ID integer PRIMARY KEY, 
	time timestamp,
    winnerID integer REFERENCES Player(ID)
	);

CREATE TABLE Property (
    ID integer PRIMARY KEY,
	gameID integer REFERENCES Game(ID),
	playerID integer REFERENCES Player(ID),
	propertyName propertyName,
	houses integer,
	hotel boolean
	);

CREATE TABLE PlayerGame (
    ID integer PRIMARY KEY,
	gameID integer REFERENCES Game(ID), 
	playerID integer REFERENCES Player(ID),
	cash integer,
	position square
	);

-- Allow users to select data from the tables.
GRANT SELECT ON Game TO PUBLIC;
GRANT SELECT ON Player TO PUBLIC;
GRANT SELECT ON PlayerGame TO PUBLIC;
GRANT SELECT ON Property TO PUBLIC;

-- Add sample records.
INSERT INTO Player(ID, emailAddress) VALUES (1, 'me@calvin.edu');
INSERT INTO Player VALUES (2, 'meso@gmail.edu', 'The King');
INSERT INTO Player VALUES (3, 'var@gmail.edu', 'Varderan');

INSERT INTO Game VALUES (1, '2006-06-28 13:20:00', 3);
INSERT INTO Game(ID, time) VALUES (2, '2019-10-19 13:20:00');
INSERT INTO Game(ID, time) VALUES (3, '2019-10-18 18:41:00');

INSERT INTO Property VALUES (1, 3, 3, 'boardwalk', 4, FALSE);
INSERT INTO Property VALUES (2, 3, 3, 'parkPlace', 0, TRUE);

INSERT INTO PlayerGame VALUES (1, 1, 1, 0.00, 'go');
INSERT INTO PlayerGame VALUES (2, 1, 2, 100.00, 'boardwalk');
INSERT INTO PlayerGame VALUES (3, 1, 3, 2350.00, 'stJamesPlace');
INSERT INTO PlayerGame VALUES (4, 2, 1, 1000.00, 'luxuryTax');
INSERT INTO PlayerGame VALUES (5, 2, 2, 0.00, 'atlanticAvenue');
INSERT INTO PlayerGame VALUES (6, 2, 3, 500.00, 'chance2');
INSERT INTO PlayerGame VALUES (7, 3, 2, 2050.00, 'jail');
INSERT INTO PlayerGame VALUES (8, 3, 3, 5500.00, 'communityChest3');
