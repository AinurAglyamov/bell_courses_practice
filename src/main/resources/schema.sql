CREATE TABLE IF NOT EXISTS Organization (
  id        INTEGER PRIMARY KEY AUTO_INCREMENT,
  name      VARCHAR(50) NOT NULL,
  full_name VARCHAR(100) NOT NULL,
  inn       CHAR(10)    NOT NULL,
  kpp       CHAR(9)     NOT NULL,
  address   VARCHAR(100) NOT NULL,
  phone     VARCHAR(50) NOT NULL,
  is_active BOOLEAN     NOT NULL
);

CREATE TABLE IF NOT EXISTS Office (
  id        INTEGER PRIMARY KEY AUTO_INCREMENT,
  name      VARCHAR(50) NOT NULL,
  address   VARCHAR(50) NOT NULL,
  phone     VARCHAR(50) NOT NULL,
  is_active BOOLEAN     NOT NULL,
  org_id    INTEGER,
  FOREIGN KEY (org_id) REFERENCES Organization (id)
);

CREATE TABLE IF NOT EXISTS Document (
  code INTEGER PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
);

CREATE TABLE IF NOT EXISTS Country (
  code INTEGER PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Employee (
  id               INTEGER PRIMARY KEY AUTO_INCREMENT,
  first_name       VARCHAR(50) NOT NULL,
  second_name      VARCHAR(50) NOT NULL,
  middle_name      VARCHAR(50) NOT NULL,
  position         VARCHAR(50) NOT NULL,
  phone            VARCHAR(50) NOT NULL,
  is_identified    BOOLEAN NOT NULL,
  doc_number       VARCHAR(50) NOT NULL,
  doc_date         DATE,
  office_id        INTEGER,
  doc_code         INTEGER,
  citizenship_code INTEGER,
  FOREIGN KEY (office_id) REFERENCES Office (id),
  FOREIGN KEY (doc_code) REFERENCES Document (code),
  FOREIGN KEY (citizenship_code) REFERENCES Country (code)
);

CREATE TABLE IF NOT EXISTS User (
  id  INTEGER PRIMARY KEY AUTO_INCREMENT,
  name   VARCHAR(50) NOT NULL,
  email  VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL
);

CREATE INDEX IX_Office_Organization_Id
  ON Office (org_id);

CREATE INDEX IX_User_Office_Id
  ON Employee (office_id);

CREATE INDEX IX_User_Document_Id
  ON Employee (doc_code);

CREATE INDEX IX_User_Country_Id
  ON Employee (citizenship_code);

