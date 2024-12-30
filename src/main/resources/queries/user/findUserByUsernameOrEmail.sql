SELECT u.id,
       u.username,
       u.email,
       u.password,
       u.created_at,
       r.name as role
from users u
inner join roles r on u.roles_id = r.id
where u.username = ?;