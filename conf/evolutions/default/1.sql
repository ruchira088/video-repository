# ---!Ups
CREATE TABLE videos(
  id VARCHAR(255) PRIMARY KEY,
  added_at TIMESTAMP NOT NULL,
  file_name VARCHAR(255) NOT NULL,
  file_size REAL NOT NULL,
  location VARCHAR(1024) NOT NULL
);

# --!Downs
DROP TABLE videos;