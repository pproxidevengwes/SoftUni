-- PT2 --
USE geography;

#10. Countries Holding 'A'
SELECT country_name, iso_code FROM countries
WHERE country_name LIKE '%a%a%a%'
ORDER BY iso_code;

#11. Mix of Peak and River Names
SELECT p.peak_name, r.river_name, 
lower(concat(left(p.peak_name, char_length(p.peak_name) - 1),
r.river_name)) AS 'mix'
FROM peaks AS p, rivers AS r
WHERE lower(right(p.peak_name, 1)) = lower(left(r.river_name, 1))
ORDER BY mix;
