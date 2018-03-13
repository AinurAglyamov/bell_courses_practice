INSERT INTO Document_Type VALUES
  (1, 21, 'Паспорт гражданина РФ', 0),
  (2, 3, 'Свидетельство о рождении', 0),
  (3, 7, 'Военный билет', 0),
  (4, 12, 'Вид на жительство в РФ', 0);

INSERT INTO Country VALUES
  (1, 643, 'Российская федерация', 0),
  (2, 804, 'Украина', 0),
  (3, 112, 'Беларусь', 0),
  (4, 840, 'Соединенный Штаты Америки', 0),
  (5, 276, 'Германия', 0);

INSERT INTO Organization VALUES
  (1, 'ПАО "ВТБ"','Публичное акционерное общество "ВТБ"','7702070139','770943002', 'г. Санкт-Петербург, ул. Большая Морская, д. 29','+7 (495) 777-24-24', TRUE, 0 ),
  (2, 'ООО "ПромТрансБанк"','Общество с ограниченной ответственностью "ПромТрансБанк"','0274045684','025250001', 'г. Уфа, ул. Ленина, д. 70','+7 (347) 273-14-23', TRUE, 0 ),
  (3, 'СПАО "Ингосстрах"','Страховое публичное акционерное общество "Ингосстрах"','7705042179','770501001', 'г. Москва, ул. Пятницкая, д.12','+7 (495) 956-55-55', TRUE, 0 ),
  (4, 'ООО "Группа Ренессанс Страхование"','Общество с ограниченной ответственностью "Группа Ренессанс Страхование"','7724023076','772501001', 'г. Москва, Дербеневская наб., д. 7','+7 (495) 725-10-50', TRUE, 0 ),
  (5, 'ООО "Перспектива"','Общество с ограниченной ответственностью "Перспектива"','0274043784','025150001', 'г. Уфа, ул. Цюрупы, д. 32','+7 (347) 277-17-76', TRUE, 0 );


INSERT INTO Office VALUES
  (1, 'Арбатский','г. Москва, ул. Арбат, д. 35','+7 (495) 747-66-87', TRUE, 1, 0),
  (2, 'Белореченский','г. Уфа, ул. Софьи Перовской, д. 46','+7 (347) 214-32-45', TRUE, 1, 0),
  (3, 'Головной офис','г. Уфа, ул. Ленина, д. 70','+7 (347) 273-14-23', TRUE, 2, 0),
  (4, 'Проспект','г. Уфа, пр. Октября, д. 37','+7 (347) 223-97-58', TRUE, 2, 0),
  (5, 'Шаболовский','г. Москва, ул. Б. Тульская, д. 10','+7 (495) 956-55-55', TRUE, 3, 0),
  (6, 'Офис на Красной Пресне','г. Москва, ул. Климашкина, д. 21','+7 (495) 730-56-07', TRUE, 3, 0),
  (7, 'Центральный офис обслуживания','г. Москва, Дербеневская наб., д. 7','+7 (495) 740-04-04', TRUE, 4, 0),
  (8, 'Центр урегулирования убытков','г. Москва, ул. Правды, д. 8','+7 (495) 740-04-04', TRUE, 4, 0),
  (9, 'Офис в Сипайлово','г. Уфа, ул. Гагарина, д. 9','+7 (347) 253-44-76', TRUE, 5, 0),
  (10, 'Демский','г. Уфа, ул. Школьная, д. 13','+7 (495) 740-04-04', TRUE, 5, 0);

INSERT INTO Employee VALUES
  (1, 'Альбин', 'Андрей', 'Олегович', 'Менеджер', '+7 (999) 130-62-45', TRUE, '8006125672', '2010-07-27', 1, 1, 1, 0),
  (2, 'Витальев', 'Виктор', 'Викторович', 'Бухгалтер', '+7 (937) 241-48-95', TRUE, '7006125344', '2013-03-13', 1, 1, 1, 0),
  (3, 'Сергеева', 'Алевтина', 'Николаевна', 'Кассир', '+7 (973) 146-58-33', TRUE, '2206135668', '2012-09-26', 1, 1, 1, 0),
  (4, 'Андреева', 'Марина', 'Антоновна', 'Кассир', '+7 (973) 224-45-98', TRUE, '3116436214', '2014-12-29', 2, 1, 1, 0),
  (5, 'Альбертов', 'Альберт', 'Витальевич', 'Начальник отдела рисков', '+7 (927) 334-56-11', TRUE, '4336135193', '2006-02-23', 2, 1, 1, 0),
  (6, 'Узлов', 'Василий', 'Валерьевич', 'Управляющий директор', '+7 (950) 787-22-31', TRUE, '5810015144', '2001-04-02', 2, 1, 1, 0),
  (7, 'Панарин', 'Артемий', 'Сергеевич', 'Главный бухгалтер', '+7 (911) 712-45-90', TRUE, '1330517087', '2008-08-28', 3, 1, 1, 0),
  (8, 'Тарасенко', 'Владимир', 'Андреевич', 'Менеджер по работе с клиентами', '+7 (937) 354-47-86', TRUE, '3401570944', '2011-11-23', 3, 1, 1, 0),
  (9, 'Овечкин', 'Александр', 'Михайлович', 'Вице-президент', '+7 (917) 129-41-87', TRUE, '5210143411', '1994-06-11', 3, 1, 1, 0),
  (10, 'Мухаметшин', 'Айбулат', 'Русланович', 'Менеджер', '+7 (987) 283-38-11', TRUE, '6410126412', '2010-04-20', 4, 1, 1, 0),
  (11, 'Забаева', 'Ляйсан', 'Ильнуровна', 'Кассир', '+7 (999) 130-56-81', TRUE, '4610136482', '2014-07-03', 4, 1, 1, 0),
  (12, 'Лянцев', 'Олег', 'Дмитриевич', 'Кассир', '+7 (962) 501-22-97', TRUE, '7242567811', '2012-09-26', 4, 1, 1, 0),
  (13, 'Михайлов', 'Андрей', 'Васильевич', 'Страховой агент', '+7 (911) 203-81-42', TRUE, 'IМЮ134567 .', '1989-04-01', 5, 2, 1, 0),
  (14, 'Прохорова', 'Анна', 'Петровна', 'Страховой агент', '+7 (912) 481-48-10', TRUE, 'IЕР771136', '1990-03-15', 5, 2, 1, 0),
  (15, 'Алексеев', 'Михаил', 'Валентинович', 'Консультант', '+7 (906) 178-21-47', TRUE, 'IЕР426596', '1992-01-10', 5, 2, 1, 0),
  (16, 'Арутунян', 'Анзор', 'Ншанович', 'Специалист по страхованию', '+7 (937) 233-72-47', TRUE, '8810612534', '2008-11-23', 6, 1, 1, 0),
  (17, 'Драго', 'Иван', 'Иванович', 'Специалист по страхованию', '+7 (937) 213-72-47', TRUE, '6114576653', '2010-02-10', 6, 1, 1, 0),
  (18, 'Кузьмин', 'Сильвестр', 'Андреевич', 'Менеджер', '+7 (999) 233-75-41', TRUE, '3164546601', '2004-12-13', 6, 1, 1, 0),
  (19, 'Зайдельман', 'Константин', 'Израилевич', 'Актуарий', '+7 (996) 933-11-93', TRUE, 'АС4995103', '2012-12-15', 7, 3, 1, 0),
  (20, 'Каримов', 'Рустем', 'Вадимович', 'Страховой статистик', '+7 (991) 563-22-65', TRUE, 'КВ8895101', '2013-02-28', 7, 3, 1, 0),
  (21, 'Иванов', 'Сергей', 'Иванович', 'Страховой агент', '+7 (937) 341-43-71', TRUE, 'АС3495278', '2015-08-18', 7, 3, 1, 0),
  (22, 'Сакаева', 'Ироида', 'Индусовна', 'Офис-менеджер', '+7 (917) 678-46-72', TRUE, '5430527987', '2007-04-23', 8, 1, 1, 0),
  (23, 'Стовба', 'Артем', 'Алексеевич', 'Страховой агент', '+7 (963) 290-74-12', TRUE, '1346135543', '2013-10-22', 8, 1, 1, 0),
  (24, 'Петров', 'Владимир', 'Владимирович', 'Старший актуарий', '+7 (917) 567-99-81', TRUE, '212641279', '2013-09-21', 8, 1, 1, 0),
  (25, 'Хованский', 'Юрий', 'Михайлович', 'Инженер', '+7 (933) 797-11-82', TRUE, '332651279', '2008-10-25', 9, 1, 1, 0),
  (26, 'Федоров', 'Мирон', 'Янович', 'Юрисконсульт', '+7 (990) 477-02-31', TRUE, '802641349', '2008-02-21', 9, 1, 1, 0),
  (27, 'Пак', 'Вениамин', 'Иванович', 'Инспектор по кадрам', '+7 (933) 567-90-31', TRUE, '454641279', '2014-10-22', 9, 1, 1, 0),
  (28, 'Хусаинов', 'Динислам', 'Ирекович', 'Программист', '+7 (937) 778-44-32', TRUE, '231651379', '2014-08-12', 10, 1, 1, 0),
  (29, 'Хусаинова', 'Айсылу', 'Маратовна', 'Бухгалтер', '+7 (927) 665-43-21', TRUE, '433711359', '2015-05-14', 10, 1, 1, 0),
  (30, 'Андреев', 'Алексей', 'Алексеевич', 'Младший инженер', '+7 (927) 129-17-22', TRUE, '119651379', '2012-10-22', 10, 1, 1, 0);