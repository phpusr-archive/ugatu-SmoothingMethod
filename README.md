Курсовая работа по КОЭД (УГАТУ 3 курс, 5 семестр)
================================================

# Что делает программа

Программа на основании предудущих данных, делает прогноз на один шаг вперед, например: курс доллара, погода и т.д.

# Для компиляции и запуска нужно установить JDK

* [Скачать 32-битную версию](http://download.oracle.com/otn-pub/java/jdk/7u51-b13/jdk-7u51-windows-i586.exe)
* [Скачать 64-битную версию](http://download.oracle.com/otn-pub/java/jdk/7u51-b13/jdk-7u51-windows-x64.exe)

# Запуск

1. В корне проекта запустить файл: grailsw.bat (при первом запуске необходимо подключение к интернету, будет скачана и установлена библиотека Grails)
1. После успешного запуска должно отобразиться окно: <br/>
![Окно после запуска](http://cdn.joxi.ru/uploads/prod/2014/03/13/22a/12b/ff4854ae98652eb56bfd925d04678ba6a0a47fa4.jpg)
1. Ввести в консоль **run-app**
1. После успешного запуска, в консоли должно быть написано следующее: <br/>
![Успешный запуск проекта](http://cdn.joxi.ru/uploads/prod/2014/03/13/624/fa3/7d7b82fbb45840d982c8de085abaad7e5cd71164.jpg)
1. Нужно перейти по адресу: [http://localhost:8080/SmoothingMethod/](http://localhost:8080/SmoothingMethod/)
1. Главная страница программы, будет выглядеть вот так:
![Главная страница программы]()
1. Для остановки приложения нужно ввести команду: **stop-app** или просто закрыть консоль

# Некоторые пояснения

**run-app** - приложение будет запущено в режиме разработки:

* Можно менять файлы, они автоматически будут перекомпилированы
* БД каждый раз при запуске удаляется и создается новая с данными по умолчанию

**prod run-app** - приложение будет запущено в режиме демонстрации

* Работает быстрее, т.к. измененные файлы не будут перекомпилированы после запуска
* БД сохраняет свое состояние даже после остановки приложения

**resources/database** - здесь хранятся БД для двух режимов запуска
