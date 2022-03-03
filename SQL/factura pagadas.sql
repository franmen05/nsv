SELECT i.closed, i.closed_date, i.id,i.total, i.total_with_taxes,i.total_payment,i.total_refund
 -- DATE_FORMAT(i.closed_date,'%Y-%m-%d'),CURRENT_DATE()
FROM invoices i
WHERE closed=1
AND DATE_FORMAT(i.closed_date,'%Y-%m-%d')= CURRENT_DATE()


-- SELECT CURRENT_DATE()