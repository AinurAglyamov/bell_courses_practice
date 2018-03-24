CREATE TABLE IF NOT EXISTS Document_Type (
  id      INTEGER PRIMARY KEY  AUTO_INCREMENT,
  code    INTEGER     NOT NULL,
  name    VARCHAR(50) NOT NULL,
  version INTEGER     NOT NULL
);

CREATE TABLE IF NOT EXISTS Country (
  id      INTEGER PRIMARY KEY  AUTO_INCREMENT,
  code    INTEGER     NOT NULL,
  name    VARCHAR(50) NOT NULL,
  version INTEGER     NOT NULL
);

CREATE TABLE IF NOT EXISTS Organization (
  id         INTEGER PRIMARY KEY AUTO_INCREMENT,
  name       VARCHAR(50),
  full_name  VARCHAR(100) NOT NULL,
  inn        CHAR(10)     NOT NULL,
  kpp        CHAR(9)      NOT NULL,
  address    VARCHAR(100) NOT NULL,
  phone      VARCHAR(50),
  is_active  BOOLEAN,
  country_id INTEGER,
  version    INTEGER      NOT NULL,
  FOREIGN KEY (country_id) REFERENCES Country (id)
);

CREATE TABLE IF NOT EXISTS Office (
  id        INTEGER PRIMARY KEY AUTO_INCREMENT,
  name      VARCHAR(50) NOT NULL,
  address   VARCHAR(50) NOT NULL,
  phone     VARCHAR(50),
  is_active BOOLEAN,
  org_id    INTEGER,
  version   INTEGER     NOT NULL,
  FOREIGN KEY (org_id) REFERENCES Organization (id)
);

CREATE TABLE IF NOT EXISTS Employee (
  id                INTEGER PRIMARY KEY AUTO_INCREMENT,
  second_name       VARCHAR(50) NOT NULL,
  first_name        VARCHAR(50) NOT NULL,
  middle_name       VARCHAR(50),
  position          VARCHAR(50),
  salary            DECIMAL(8, 2),
  registration_date DATE,
  phone             VARCHAR(50),
  doc_number        VARCHAR(50) NOT NULL,
  doc_date          DATE        NOT NULL,
  office_id         INTEGER,
  doc_type_id       INTEGER,
  country_id        INTEGER,
  version           INTEGER     NOT NULL,
  FOREIGN KEY (office_id) REFERENCES Office (id),
  FOREIGN KEY (doc_type_id) REFERENCES Document_Type (id),
  FOREIGN KEY (country_id) REFERENCES Country (id)
);

CREATE TABLE IF NOT EXISTS User (
  id        INTEGER PRIMARY KEY AUTO_INCREMENT,
  name      VARCHAR(50),
  login     VARCHAR(50) NOT NULL,
  password  VARCHAR(50) NOT NULL,
  code      VARCHAR(50) NOT NULL,
  email     VARCHAR(50),
  is_active BOOLEAN     NOT NULL,
  version   INTEGER     NOT NULL
);

CREATE INDEX IX_Office_Organization_Id
  ON Office (org_id);

CREATE INDEX IX_User_Office_Id
  ON Employee (office_id);

CREATE INDEX IX_Employee_Document_Id
  ON Employee (doc_type_id);

CREATE INDEX IX_Employee_Country_Id
  ON Employee (country_id);

CREATE INDEX IX_Organization_Country_Id
  ON Organization (country_id);

