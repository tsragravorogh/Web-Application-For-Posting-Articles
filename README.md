# Web-Application-For-Posting-Articles
## Общая информация
Данное REST приложение, написанное на Java при помощи библиотек Spring, позволяет просматривать добавленные статьи в порядке от новых к старым:

http://localhost:3000/api/getAllArticles
![articles](https://github.com/tsragravorogh/Web-Application-For-Posting-Articles/blob/master/images/2021-11-09_16-49-56.png)

Также доступен выбор просмотра тематик, которые задаются `2 строкой` в txt файле в zip:

http://localhost:3000/api/getAllArticles/{Topic}
![articles](https://github.com/tsragravorogh/Web-Application-For-Posting-Articles/blob/master/images/2021-11-09_16-53-29.png)

Чтобы добавить статью надо нажать на кнопку справа `Add New Article` и в всплывающем окне выбрать `.zip`:

![articles](https://github.com/tsragravorogh/Web-Application-For-Posting-Articles/blob/master/images/2021-11-09_16-53-02.png)

## Для запуска проекта
### Проверьте установленные jdk и maven:
#### `java -version`
#### `mvn -v`
### Также понадобится папка `zip`. Для ее создания перейдите в корень диска C:/ и создайте ее:
#### `c:`
#### `cd /`
#### `mkdir zip`
### Переместитесь в папку проекта и выполните:
#### `mvn clean install`
### Перейдите в папку target:
#### `cd target`
### Для запуска back стороны приложения выполните команду:
#### `java -jar Web-Application-For-Posting-Articles-0.0.1-SNAPSHOT.jar`
### Для запуска front стороны приложения скачайте проект - [front](https://github.com/tsragravorogh/articles)

