SELECT i.closed, i.closed_date, i.id,i.total, i.total_without_taxes,i.total_payment,i.total_refund
 -- DATE_FORMAT(i.closed_date,'%Y-%m-%d'),CURRENT_DATE()
FROM invoices i
WHERE closed=1;
-- AND DATE_FORMAT(i.closed_date,'%Y-%m-%d')= CURRENT_DATE()


-- SELECT CURRENT_DATE()

SELECT * -- invoice_id
FROM payments p, invoices i 
WHERE p.invoice_id = i.id
-- and accounting_closing_id=13
;


 select invoice0_.id as id1_9_, invoice0_.create_date as create_d2_9_, invoice0_.created_by as created_3_9_, invoice0_.last_update_date as last_upd4_9_, invoice0_.closed as closed5_9_, invoice0_.closed_date as closed_d6_9_, invoice0_.comment as comment7_9_, invoice0_.company_id as company18_9_, invoice0_.currency_id as currenc19_9_, invoice0_.customer_id as custome20_9_, invoice0_.description as descript8_9_, invoice0_.discount as discount9_9_, invoice0_.has_ncf as has_ncf10_9_, invoice0_.has_tax as has_tax11_9_, invoice0_.payment_type_id as payment21_9_, invoice0_.status as status12_9_, invoice0_.subsidiary_id as subsidi22_9_, invoice0_.total as total13_9_, invoice0_.total_payment as total_p14_9_, invoice0_.total_refund as total_r15_9_, invoice0_.total_with_taxes as total_w16_9_, invoice0_.total_without_taxes as total_w17_9_, invoice0_.user_id as user_id23_9_ from invoices invoice0_ left outer join payments payments1_ on invoice0_.id=payments1_.invoice_id
 WHERE (payments1_.id is not null) and invoice0_.closed IS NULL;
 
 
 
 INSERT INTO `nsv`.`refunds` (`id`, `create_date`, `created_by`, `last_update_date`, `customer_id`, `invoice_id`) VALUES ('1', '2022-03-29 13:55:27', 'Jul;io', '2022-03-29 13:55:35', '1', '1');
 
  INSERT INTO `nsv`.`refunds` (`id`, `create_date`, `created_by`, `last_update_date`,  `invoice_id`) VALUES ('1', '2022-03-29 13:55:27', 'Jul;io', '2022-03-29 13:55:35', '1');