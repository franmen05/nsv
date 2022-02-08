SELECT i.closed, i.closed_date, i.id
 -- DATE_FORMAT(i.closed_date,'%Y-%m-%d'),CURRENT_DATE()
FROM invoices i
WHERE closed=1
AND DATE_FORMAT(i.closed_date,'%Y-%m-%d')= CURRENT_DATE()


-- SELECT CURRENT_DATE()