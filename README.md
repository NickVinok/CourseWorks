# CourseWorks

Общая информация
---

Проект представляет собой реализацию серверной части выпускной квалификационной бакалаврской работы на тему 
"Программный комплекс моделирования типовых нештатных ситуаций на объектах хранения нефтепродуктов".

Модули
---

Программный комплекс включает в себя:

интерфейс для взаимодействия с информационным обеспечением в виде реляционной базы данных MySQL;

математическое обеспечение, реализующее математический аппарат,представленный в приказе №404 МЧС РФ;

контроллер для взаимодействия с клиентской частью дипломного проекта.

Технологии
---

Основные технологии, использованные при разработке: Java 11, Spring boot, Spring Data JPA, MySQL 5.7, Maven

Требования к установке
---

Для запуска сервера необходимо наличие JVM, поддерживающей Java 11, MySQL версии 5.7 и Maven,
необходимо также наличие функционирующей базы данных.

Для получения рабочего дампа обращайтесь на почту: nickvinok@yandex.ru

Порт для отправки запросов на сервер по умолчанию: 8080

Запуск
---
Для простого запуска сервера в корне проекта необходимо выполнить команду:

`mvn package`

затем надо перейти в каталог target и выполнить команду:

`java -jar CourseWorks-1.0-SNAPSHOT.jar`

---
