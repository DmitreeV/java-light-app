<div align="center">
<img src="https://x-lines.ru/letters/i/cyrillicscript/0412/b4b4b6/38/0/jtwsq4dwfiyzyhy.png">
<p align="center"></p>
</div>

## Краткое описание

Light App это приложение, которое позволяет пользователям создавать аккаунты, создавать мероприятия после подписания договора на проведение мероприятий с компанией, записываться на посещение мероприятия и производить оплату за участие в мероприятии.

## Стек-технологий

* Hibernate
* Java 11
* Spring Boot
* JPA+API
* Spring Security
* Mapstruct
* Maven
* H2DataBase
* JUnit Test

## Функциональности проекта

- Пользователь имеет возможность зарегистрироваться в системе как потенциальный участник или администратор мероприятия;
- Администратор мероприятий имеет возможность создать мероприятие, указав его название, стоимость участия и др. при условии что с ним подписан действительный договор;
- Администратор имеет возможность оставить заявку на  подписание договора;
- Зарегистрированный пользователь имеет возможность записаться на мероприятие, указав свои данные (ФИО, возраст, пцр тест) после проверки которых он становится участником.

## Схема базы данных
![Light App Data Base diagram](https://github.com/DmitreeV/java-light-app/blob/main/image/db%20diagram%20light%20app.jpg)

## Системные требования

В данном репозитории представлен бэкенд приложения. Для проверки работоспособности приложение было протестировано по WEB API, для этого мной были написаны Postman тесты, они расположены в папке [postman](./postman/). Также написаны тесты JUnit.

Приложение работает корректно в текущем виде при наличии:

- установленный [JDK версии 11](https://docs.aws.amazon.com/corretto/),
- сборка с использованием [Maven](https://maven.apache.org/).

