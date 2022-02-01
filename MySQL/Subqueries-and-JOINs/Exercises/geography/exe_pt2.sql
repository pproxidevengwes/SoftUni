-- PT 2 --
USE geography;

#12. Highest Peaks in Bulgaria
SELECT c.country_code, m.mountain_range, p.peak_name, p.elevation
FROM countries AS c
JOIN mountains_countries AS mc ON mc.country_code = c.country_code
JOIN mountains AS m ON m.id = mc.mountain_id
JOIN peaks AS p ON p.mountain_id = m.id
WHERE c.country_code LIKE 'BG' AND p.elevation > 2835
ORDER BY p.elevation DESC;

#13. Count Mountain Ranges
SELECT c.country_code, count(mc.mountain_id) AS 'mountain_range'
FROM countries AS c
JOIN mountains_countries AS mc ON c.country_code = mc.country_code
JOIN mountains AS m ON m.id = mc.mountain_id
GROUP BY c.country_code
HAVING c.country_code IN ('BG', 'RU', 'US')
ORDER BY mountain_range DESC;

#14. Countries with Rivers
SELECT c.country_name, r.river_name FROM countries AS c
LEFT JOIN countries_rivers AS cr ON c.country_code = cr.country_code
LEFT JOIN rivers AS r ON r.id = cr.river_id
WHERE c.continent_code = 'AF'
ORDER BY c.country_name
LIMIT 5;

#15. *Continents and Currencies
SELECT c.continent_code, c.currency_code, count(*) AS 'currency_usage'
FROM countries AS c
GROUP BY c.continent_code, c.currency_code
HAVING currency_usage > 1 AND currency_usage = 
(SELECT count(*) AS 'most_used_currency'
FROM countries AS c2
WHERE c2.continent_code = c.continent_code
GROUP BY c2.currency_code
ORDER BY most_used_currency DESC
LIMIT 1)
ORDER BY c.continent_code, c.currency_code;

#16. Countries without any Mountains


#17. Highest Peak and Longest River by Country
SELECT c.country_name,
max(p.elevation) AS 'highest_peak_elevation',
max(r.length) AS 'longest_river_length' FROM countries AS c 
LEFT JOIN mountains_countries AS mc ON mc.country_code = c.country_code
LEFT JOIN peaks AS p ON mc.mountain_id = p.mountain_id
LEFT JOIN countries_rivers AS cr ON cr.country_code = c.country_code
LEFT JOIN rivers AS r ON r.id = cr.river_id
GROUP BY c.country_code
ORDER BY highest_peak_elevation DESC, 
longest_river_length DESC, c.country_name
LIMIT 5;
