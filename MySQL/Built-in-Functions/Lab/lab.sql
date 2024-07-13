-- Lab --
#01. Find Book Titles
SELECT title FROM books
WHERE title LIKE 'The%'
ORDER BY id;

#02. Replace Titles
SELECT replace(title, 'The', '***') FROM books
WHERE title LIKE 'The%'
ORDER BY id;

#03. Sum Cost of All Books
SELECT ROUND(Sum(cost),2) As 'Total Cost'
FROM books;

#04. Days Lived
SELECT CONCAT(first_name, ' ', last_name) AS full_name, 
TIMESTAMPDIFF(DAY, born, died) AS days_lived 
FROM authors;

#05. Harry Potter Books
SELECT title FROM books
WHERE title LIKE 'Harry Potter%';
