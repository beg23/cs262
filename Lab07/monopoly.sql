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

-- Create new types as needed. TODO: Unnecessary?
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

CREATE TYPE property AS ENUM (
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
CREATE TABLE Game (
	ID integer PRIMARY KEY, 
	time timestamp
	);

CREATE TABLE Player (
	ID integer PRIMARY KEY, 
	emailAddress varchar(50) NOT NULL,
	name varchar(50)
	);

CREATE TABLE Property (
	gameID integer REFERENCES Game(ID),
	playerID integer REFERENCES Player(ID),
	propertyName property,
	houses integer,
	hotel boolean
	);

CREATE TABLE PlayerGame (
	gameID integer REFERENCES Game(ID), 
	playerID integer REFERENCES Player(ID),
	cash integer,
	position square
	);

-- Allow users to select data from the tables.
GRANT SELECT ON Game TO PUBLIC;
GRANT SELECT ON Player TO PUBLIC;
GRANT SELECT ON PlayerGame TO PUBLIC;

-- Add sample records.
INSERT INTO Game VALUES (1, '2006-06-27 08:00:00');
INSERT INTO Game VALUES (2, '2006-06-28 13:20:00');
INSERT INTO Game VALUES (3, '2006-06-29 18:41:00');

INSERT INTO Player(ID, emailAddress) VALUES (1, 'me@calvin.edu');
INSERT INTO Player VALUES (2, 'king@gmail.edu', 'The King');
INSERT INTO Player VALUES (3, 'dog@gmail.edu', 'Dogbreath');

INSERT INTO Property VALUES (3, 3, 'boardwalk', 4, FALSE);
INSERT INTO Property VALUES (3, 3, 'parkPlace', 0, TRUE);

INSERT INTO PlayerGame VALUES (1, 1, 0.00, 'go');
INSERT INTO PlayerGame VALUES (1, 2, 0.00, 'boardwalk');
INSERT INTO PlayerGame VALUES (1, 3, 2350.00, 'stJamesPlace');
INSERT INTO PlayerGame VALUES (2, 1, 1000.00, 'luxuryTax');
INSERT INTO PlayerGame VALUES (2, 2, 0.00, 'atlanticAvenue');
INSERT INTO PlayerGame VALUES (2, 3, 500.00, 'chance2');
INSERT INTO PlayerGame VALUES (3, 2, 0.00, 'jail');
INSERT INTO PlayerGame VALUES (3, 3, 5500.00, 'communityChest3');