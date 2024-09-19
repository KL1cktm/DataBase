--Вопрос 31
SELECT counts.supplier_id AS "Номер поставщика"
FROM counts
GROUP BY counts.supplier_id, counts.product_id
HAVING COUNT(DISTINCT counts.project_id) = (SELECT COUNT(DISTINCT counts.project_id) FROM counts GROUP BY counts.supplier_id)

SELECT COUNT(DISTINCT counts.project_id) FROM counts GROUP BY counts.supplier_id, counts.product_id

SELECT COUNT(DISTINCT project_id)
FROM counts
GROUP BY supplier_id

SELECT supplier_id AS "Human", product_id, COUNT(DISTINCT project_id)
FROM counts
GROUP BY supplier_id, product_id

SELECT COUNT(DISTINCT counts.project_id) FROM counts GROUP BY counts.supplier_id