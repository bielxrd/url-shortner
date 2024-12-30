INSERT INTO users_url (user_id, original_url, id_url)
VALUES (?, ?, ?)
RETURNING ID, USER_ID, ORIGINAL_URL, ID_URL, CREATED_AT;