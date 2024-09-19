-- Юргилевич Е.В. гр.221702
-- Вариант 25

-- Вопрос 8
--Получить все такие тройки "номера поставщиков-номера деталей-номера проектов",
--для которых никакие из двух выводимых поставщиков,
--деталей и проектов не размещены в одном городе.
SELECT counts.supplier_id AS "Номер поставщика", 
       counts.product_id AS "Номер детали", 
       counts.project_id AS "Номер проекта"
FROM counts
JOIN suppliers ON counts.supplier_id = suppliers.id
JOIN products ON counts.product_id = products.id
JOIN projects ON counts.project_id = projects.id
WHERE suppliers.city <> products.city 
  AND suppliers.city <> projects.city
  AND products.city <> projects.city;
  
-- Вопрос 35
--Получить пары "номер поставщика-номер детали", такие,
--что данный поставщик не поставляет данную деталь.
SELECT suppliers.id AS "Номер поставщика",
	   products.id AS "Номер детали"
FROM suppliers
CROSS JOIN products
LEFT JOIN counts ON suppliers.id = counts.supplier_id
	AND products.id = counts.product_id
WHERE counts.supplier_id IS NULL;

-- Вопрос 7
--Получить все такие тройки "номера поставщиков-номера деталей-номера проектов", 
--для которых выводимые поставщик, деталь и проект не размещены в одном городе.
SELECT counts.supplier_id AS "Номер поставщика", 
       counts.product_id AS "Номер детали", 
       counts.project_id AS "Номер проекта"
FROM counts
JOIN suppliers ON counts.supplier_id = suppliers.id
JOIN products ON counts.product_id = products.id
JOIN projects ON counts.project_id = projects.id
WHERE suppliers.city <> products.city 
  AND suppliers.city <> projects.city
  AND products.city <> projects.city;

-- Вопрос 11
--Получить все пары названий городов, для которых поставщик из первого 
--города обеспечивает проект во втором городе.
SELECT DISTINCT suppliers.city AS "Поставщик город",
	   projects.city AS "Поставщик город"
FROM counts
JOIN suppliers ON counts.supplier_id = suppliers.id
JOIN projects ON counts.project_id = projects.id
WHERE suppliers.city <> projects.city;

--Вопрос 15
--Получить общее число проектов, обеспечиваемых поставщиком П1.
SELECT COUNT(DISTINCT counts.project_id) AS "Число проектов"
FROM counts
WHERE counts.supplier_id = 1;

--Вопрос 25
--Получить номера проектов, город которых стоит первым в алфавитном списке городов.
SELECT projects.id AS "Номер проекта"
FROM projects
WHERE projects.city = (SELECT projects.city
						FROM projects
						ORDER BY projects.city
						LIMIT 1);
	
--Вопрос 29
--Получить номера проектов, полностью обеспечиваемых поставщиком П1.
SELECT counts.project_id AS "Номер проекта"
FROM counts
GROUP BY project_id
HAVING MAX(counts.supplier_id) = 1;

--Вопрос 19
--Получить имена проектов, обеспечиваемых поставщиком П1.
SELECT DISTINCT projects.name AS "Название проекта"
FROM projects
INNER JOIN counts ON projects.id = counts.project_id
WHERE counts.supplier_id = 1

--Вопрос 5
--Получить все сочетания "цвета деталей-города деталей".
SELECT DISTINCT products.color AS "Цвет детали",
	   products.city AS "Город"
FROM products

--Вопрос 31
--Получить номера поставщиков, поставляющих одну и ту же деталь для всех проектов.
SELECT DISTINCT counts.supplier_id AS "Номер поставщика"
FROM counts
GROUP BY counts.supplier_id, counts.product_id
HAVING COUNT(DISTINCT counts.project_id) = (SELECT COUNT(DISTINCT c2.project_id) 
											FROM counts AS c2 
											WHERE c2.supplier_id = counts.supplier_id 
											GROUP BY c2.supplier_id) 