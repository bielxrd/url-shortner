SELECT EXISTS (
    SELECT 1 FROM USERS WHERE EMAIL = ? OR USERNAME = ?
);