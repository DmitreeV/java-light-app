MERGE INTO principal (id, name, inn)
    KEY (id)
    VALUES (1, 'LIGHT DIGITAL', '7801710661');

MERGE INTO admins (id, org_name, email, role)
    KEY(id)
    VALUES (1, 'Aleksandr', 'alx@mail.ru', 'ADMIN');

MERGE INTO participants (id, fio, age, email, pcr_test, role)
    KEY (id)
    VALUES (1, 'Aleksandr Aleksandrovic Popov', 40, 'alx1980@mail.ru', 'YES', 'PARTICIPANT');

MERGE INTO participants (id, fio, age, email, pcr_test, role)
    KEY (id)
    VALUES (2, 'Ivan Ivanovic Ivanov', 33, 'ivanovic@mail.ru', 'NO', 'PARTICIPANT');