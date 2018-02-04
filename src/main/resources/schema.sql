CREATE TABLE IF NOT EXISTS ROLES
(
	ROLE_ID INTEGER(10) PRIMARY KEY,
	ROLE VARCHAR(64 CHAR) NOT NULL
);
CREATE UNIQUE INDEX IF NOT EXISTS ROLES_ROLE_INDEX ON ROLES (ROLE);
COMMENT ON COLUMN ROLES.ROLE_ID IS 'Unique ID of the role.';
COMMENT ON COLUMN ROLES.ROLE IS 'Unique name of the role.';
COMMENT ON TABLE ROLES IS 'Roles that can be given to users. This is a configuration table.';

CREATE TABLE IF NOT EXISTS USERS
(
	USER_ID INTEGER(10) AUTO_INCREMENT PRIMARY KEY,
	USERNAME VARCHAR(64 CHAR) NOT NULL,
	PASSWORD VARCHAR(255 CHAR) NOT NULL,
	ENABLED BOOLEAN NOT NULL,
	ROLE_ID INTEGER(10),
	CONSTRAINT ROLE_ID_FK FOREIGN KEY (ROLE_ID) REFERENCES ROLES (ROLE_ID) ON DELETE SET NULL ON UPDATE CASCADE
);
CREATE UNIQUE INDEX IF NOT EXISTS USERS_USERNAME_INDEX ON USERS (USERNAME);
COMMENT ON COLUMN USERS.USER_ID IS 'Unique ID of the user.';
COMMENT ON COLUMN USERS.USERNAME IS 'Unique name of the user.';
COMMENT ON COLUMN USERS.PASSWORD IS 'Hashed password of the user.';
COMMENT ON COLUMN USERS.ENABLED IS 'If the user is enabled or not.';
COMMENT ON COLUMN USERS.ROLE_ID IS 'Role granted to the user.';
COMMENT ON TABLE USERS IS 'All the application users.';

CREATE TABLE IF NOT EXISTS PLAYERS
(
	PLAYER_ID INTEGER(10) AUTO_INCREMENT PRIMARY KEY,
	USER_ID INTEGER(10) NOT NULL,
	NAME VARCHAR(32 CHAR) NOT NULL,
	SURNAME VARCHAR(32 CHAR) NOT NULL,
	GENDER VARCHAR(1 CHAR) NOT NULL,
	GUEST VARCHAR(1 CHAR) NOT NULL CHECK (GUEST in ('Y', 'N')),
	CREATION_TIMESTAMP TIMESTAMP,
	CONSTRAINT USER_ID_FK FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE UNIQUE INDEX IF NOT EXISTS PLAYERS_FULL_NAME_INDEX ON PLAYERS (USER_ID, NAME, SURNAME);
COMMENT ON TABLE PLAYERS IS 'All the players of the application, namely guest users and real users (with access credentials).';

CREATE TABLE IF NOT EXISTS ACTIVITIES
(
	ACTIVITY_ID INTEGER(10) AUTO_INCREMENT PRIMARY KEY,
	ACTIVITY_DATE DATE NOT NULL,
	ACTIVITY_TIME TIME,
	DURATION TIME,
	FIRST_PLAYER_ID INTEGER(10),
	SECOND_PLAYER_ID INTEGER(10),
	ACTIVITY_TYPE VARCHAR(16 CHAR) NOT NULL,
	CLUB VARCHAR(128 CHAR),
	TOURNAMENT VARCHAR(128 CHAR),
	NOTES VARCHAR(1024 CHAR),
	CREATION_TIMESTAMP TIMESTAMP,
	CONSTRAINT FIRST_PLAYER_ID_FK FOREIGN KEY (FIRST_PLAYER_ID) REFERENCES PLAYERS (PLAYER_ID),
	CONSTRAINT SECOND_PLAYER_ID_FK FOREIGN KEY (SECOND_PLAYER_ID) REFERENCES PLAYERS (PLAYER_ID)
);
COMMENT ON TABLE ACTIVITIES IS 'The activities performed by all the users.';

CREATE TABLE IF NOT EXISTS RESULTS
(
	ACTIVITY_ID INTEGER(10) PRIMARY KEY,
	"3_OR_5_SETTER" INTEGER(1) NOT NULL CHECK ("3_OR_5_SETTER" in (3, 5)),
	LAST_SET_TIEBREAK VARCHAR(1 CHAR) NOT NULL CHECK (LAST_SET_TIEBREAK in ('Y', 'N')),
	SET_1_P_1 INTEGER,
	SET_1_P_2 INTEGER,
	SET_2_P_1 INTEGER,
	SET_2_P_2 INTEGER,
	SET_3_P_1 INTEGER,
	SET_3_P_2 INTEGER,
	SET_4_P_1 INTEGER,
	SET_4_P_2 INTEGER,
	SET_5_P_1 INTEGER,
	SET_5_P_2 INTEGER,
	CONSTRAINT ACTIVITY_ID_FK FOREIGN KEY (ACTIVITY_ID) REFERENCES ACTIVITIES (ACTIVITY_ID) ON DELETE CASCADE ON UPDATE CASCADE
);
COMMENT ON TABLE RESULTS IS 'The results of the matches (for activities that have results).';

CREATE OR REPLACE VIEW ACTIVITIES_AND_RESULTS AS
	SELECT
		A.ACTIVITY_ID,
		A.ACTIVITY_DATE,
		A.ACTIVITY_TIME,
		A.DURATION,
		A.ACTIVITY_TYPE,
		A.CLUB,
		A.TOURNAMENT,
		A.NOTES,
		A.FIRST_PLAYER_ID,
		P1.NAME AS FIRST_PLAYER_NAME,
		P1.SURNAME AS FIRST_PLAYER_SURNAME,
		A.SECOND_PLAYER_ID,
		P2.NAME AS SECOND_PLAYER_NAME,
		P2.SURNAME AS SECOND_PLAYER_SURNAME,
		R.SET_1_P_1,
		R.SET_1_P_2,
		R.SET_2_P_1,
		R.SET_2_P_2,
		R.SET_3_P_1,
		R.SET_3_P_2,
		R.SET_4_P_1,
		R.SET_4_P_2,
		R.SET_5_P_1,
		R.SET_5_P_2
	FROM ACTIVITIES A
		INNER JOIN PLAYERS P1 ON A.FIRST_PLAYER_ID = P1.PLAYER_ID
		INNER JOIN PLAYERS P2 ON A.SECOND_PLAYER_ID = P2.PLAYER_ID
		LEFT JOIN RESULTS R ON A.ACTIVITY_ID = R.ACTIVITY_ID;
COMMENT ON VIEW ACTIVITIES_AND_RESULTS IS 'Activities with their corresponding results (if any)';
