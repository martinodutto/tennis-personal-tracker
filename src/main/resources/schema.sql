CREATE TABLE IF NOT EXISTS PLAYERS
(
	PLAYER_ID INT AUTO_INCREMENT PRIMARY KEY,
	NAME VARCHAR(32 CHAR) NOT NULL,
	SURNAME VARCHAR(32 CHAR) NOT NULL,
	GENDER VARCHAR(1 CHAR) NOT NULL,
	GUEST VARCHAR(1 CHAR) NOT NULL CHECK (GUEST in ('Y', 'N')),
	CREATION_TIMESTAMP TIMESTAMP
);
COMMENT ON TABLE PLAYERS IS 'All the players of the application, namely guest users and real users (with access credentials).';