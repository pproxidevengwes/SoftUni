-- PT2 --

#08. Find Full Name
DELIMITER $$
CREATE PROCEDURE usp_get_holders_full_name()
BEGIN
SELECT concat_ws(' ', ah.first_name, ah.last_name) AS 'full_name'
FROM account_holders AS ah
ORDER BY full_name, ah.id;
END $$
DELIMITER ;

CALL usp_get_holders_full_name();
DROP PROCEDURE usp_get_holders_full_name;

#9. People with Balance Higher Than
DELIMITER $$
CREATE PROCEDURE usp_get_holders_with_balance_higher_than(money DOUBLE(19,4))
BEGIN
SELECT ah.first_name, ah.last_name FROM account_holders AS ah
RIGHT JOIN accounts AS a ON ah.id = a.account_holder_id
GROUP BY ah.id
HAVING sum(balance) > money
ORDER BY ah.id;
END $$
DELIMITER ;

CALL usp_get_holders_with_balance_higher_than(7000);
DROP PROCEDURE usp_get_holders_with_balance_higher_than

#10. Future Value Function 
DELIMITER $$
CREATE FUNCTION ufn_calculate_future_value(initial_sum DECIMAL(19,4), 
interest_rate DECIMAL(19,4), years INT)
RETURNS DECIMAL(19,4)
DETERMINISTIC
BEGIN
RETURN initial_sum * pow((1 + interest_rate), years);
END $$
DELIMITER ;

DROP FUNCTION ufn_calculate_future_value;

#11. Calculating Interest
DELIMITER $$
CREATE PROCEDURE usp_calculate_future_value_for_account(account_id INT, 
interest_rate DECIMAL(19,4))
BEGIN
SELECT a.id AS 'account_id', ah.first_name, ah.last_name,
a.balance AS 'currency_balance',
ufn_calculate_future_value(a.balance, interest_rate, 5) AS 'balance_in_5_years'
FROM account_holders AS ah
JOIN accounts AS a ON ah.id = a.account_holder_id
WHERE a.id = account_id;
END $$
DELIMITER ;

CALL usp_calculate_future_value_for_account(1,0.1);
DROP PROCEDURE usp_calculate_future_value_for_account;

#12. Deposit Money
DELIMITER $$
CREATE PROCEDURE usp_deposit_money(account_id INT, money_amount DECIMAL(19,4)) 
BEGIN
START TRANSACTION; 
IF(money_amount <= 0) THEN ROLLBACK;
ELSE
UPDATE accounts AS a
SET a.balance = a.balance + money_amount
WHERE a.id = account_id;
COMMIT;
END IF;
END $$
DELIMITER ;

CALL usp_deposit_money(1,10);
DROP PROCEDURE usp_deposit_money;

#13. Withdraw Money
DELIMITER $$
CREATE PROCEDURE usp_withdraw_money(account_id INT, money_amount DECIMAL(19,4))
BEGIN
START TRANSACTION; 
IF(money_amount < 0) OR ((SELECT COUNT(*) FROM accounts WHERE id = account_id) = 0) OR
((SELECT balance FROM accounts  WHERE id = account_id) < money_amount)
THEN ROLLBACK;
ELSE
UPDATE accounts AS a
SET a.balance = a.balance - money_amount
WHERE a.id = account_id;
COMMIT;
END IF;
END $$
DELIMITER ;

CALL usp_withdraw_money(1,10);
DROP PROCEDURE usp_withdraw_money;

#14. Money Transfer
DELIMITER $$
CREATE PROCEDURE usp_transfer_money(from_account_id INT, to_account_id INT, amount DECIMAL(19,4)) 
BEGIN
START TRANSACTION; 
IF(amount < 0) OR ((SELECT COUNT(*) FROM accounts WHERE id = from_account_id) = 0) OR
((SELECT COUNT(*) FROM accounts  WHERE id = to_account_id) = 0) OR
((SELECT balance FROM accounts  WHERE id = from_account_id) < amount)
THEN ROLLBACK;
ELSE
UPDATE accounts AS a
SET a.balance = a.balance - amount
WHERE a.id = from_account_id;
UPDATE accounts AS a
SET a.balance = a.balance + amount
WHERE a.id = to_account_id;
COMMIT;
END IF;
END $$
DELIMITER ;

CALL usp_transfer_money(1,2,10);
DROP PROCEDURE  usp_transfer_money;

#15. Log Accounts Trigger
CREATE TABLE `logs`(
`log_id` INT PRIMARY KEY AUTO_INCREMENT,
`account_id` INT NOT NULL,
`old_sum` DECIMAL(19,4) NOT NULL,
`new_sum` DECIMAL(19,4) NOT NULL
);

DELIMITER $$
CREATE TRIGGER tr_balance_updated
AFTER UPDATE ON accounts
FOR EACH ROW
BEGIN
IF OLD.balance <> NEW.balance THEN
INSERT INTO `logs` (account_id, old_sum, new_sum)
VALUES (OLD.id, OLD.balance, NEW.balance);
END IF;
END $$
DELIMITER ;

#16. Emails Trigger
CREATE TABLE notification_emails(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`recipient` INT NOT NULL,
`subject` VARCHAR(60) NOT NULL,
`body` VARCHAR(255) NOT NULL
);

DELIMITER $$
CREATE TRIGGER tr_notification
AFTER INSERT ON `logs`
FOR EACH ROW
BEGIN
INSERT INTO notification_emails(`recipient`, `subject`, `body`)
VALUES(
NEW.account_id, concat('Balance change for account: ', NEW.account_id),
concat('On ', DATE_FORMAT(NOW(), '%b %d %Y'),' at ', DATE_FORMAT(NOW(), '%r'), 
' your balance was changed from ', ROUND(NEW.old_sum, 2), ' to ', 
ROUND(NEW.new_sum, 2), '.'));
END $$
DELIMITER ;

