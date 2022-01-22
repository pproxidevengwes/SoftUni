-- PT 3 --
USE diablo;

#12. Games From 2011 and 2012 Year
SELECT name, date_format(start,'%Y-%m-%d') FROM games
WHERE year(start) IN (2011, 2012)
ORDER BY start, name
LIMIT 50;

#13. User Email Providers
SELECT user_name, REGEXP_REPLACE(email, '.*@', '') AS 'email provider'
FROM users
ORDER BY  `email provider`, user_name;

#14. Get Users with IP Address Like Pattern
SELECT user_name, ip_address FROM users
WHERE ip_address LIKE '___.1%.%.___'
ORDER BY user_name;

#15. Show All Games with Duration
SELECT name,
 (CASE 
  WHEN HOUR(g.start) BETWEEN 0 AND 11 THEN 'Morning'
  WHEN HOUR(g.start) BETWEEN 12 AND 17 THEN 'Afternoon'
  ELSE 'Evening'
 END) AS 'Part of the Day',
 (CASE
  WHEN g.duration <= 3 THEN 'Extra Short'
  WHEN g.duration BETWEEN 4 AND 6 THEN 'Short'
  WHEN g.duration BETWEEN 7 AND 10 THEN 'Long'
  ELSE 'Extra Long'
 END) AS 'Duration'
FROM games AS g;
