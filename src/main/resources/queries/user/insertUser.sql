INSERT INTO users (USERNAME, EMAIL, PASSWORD, ROLES_ID)
VALUES (?, ?, ?, ?)
RETURNING id, username, email, password, created_at;