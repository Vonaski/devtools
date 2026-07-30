[![CI](https://github.com/Vonaski/devtools/actions/workflows/ci.yml/badge.svg)](https://github.com/Vonaski/devtools/actions/workflows/ci.yml)
# DevTools

Небольшой учебный проект на Java 25 и Gradle, демонстрирующий использование:

- Java Record
- бизнес-логики в отдельном классе
- JUnit 5
- AssertJ
- Gradle Toolchain

---

## Requirements

- JDK 25
- Gradle Wrapper (входит в проект)
- IntelliJ IDEA

---
 
# Quick Start

## Запуск приложения

### Через Gradle Tool Window

```
Tasks
 └── application
      └── run
```

### Через Run Anything

Нажмите:

```
Ctrl + Ctrl
```

Введите:

```
gradle run
```

---

## Сборка проекта

### Через Gradle Tool Window

```
Tasks
 └── build
      └── build
```

### Через Run Anything

```
gradle build
```

---

## Запуск тестов

### Через Gradle Tool Window

```
Tasks
 └── verification
      └── test
```

### Через Run Anything

```
gradle test
```

---

# Project Structure

```
src
├── main
│   └── java
│       └── ru
│           └── mentee
│               └── power
│                   ├── MenteeProgress.java
│                   └── ProgressDemo.java
│
└── test
    └── java
        └── ru
            └── mentee
                └── power
                    └── MenteeProgressTest.java
```

---

# Packages

## ru.mentee.power

Основной пакет приложения.

Содержит:

- `MenteeProgress` — модель данных и бизнес-логика.
- `ProgressDemo` — демонстрационный запуск приложения.
- `MenteeProgressTest` — модульные тесты.

Использование собственного пакета помогает избежать конфликтов имен классов и логически объединяет связанные компоненты проекта.

---

# Data Model

| Поле | Тип | Описание |
|------|-----|----------|
| `menteeName` | `String` | Имя участника |
| `sprintNumber` | `int` | Номер текущего спринта |
| `plannedHoursPerWeek` | `int` | Планируемое количество часов в неделю |

---

# Business Rule

Метод

```java
readyForSprint();
```

возвращает:

- `true`, если

```
plannedHoursPerWeek >= 3
```

- `false`, если

```
plannedHoursPerWeek < 3
```

---

# Example Output

```
Sprint 2 → Ilyas: planned 20 h
Status: sprint ready
```

---

# Running Tests

Проект использует:

- JUnit 5
- AssertJ

Все тесты находятся в:

```
src/test/java
```

Запуск:

```
gradle test
```
---

# Branch Naming Rule

Для разработки используется следующая схема именования веток:

- `master` — основная ветка проекта (или `main`, если проект использует современную схему именования).
- `feature/DVT-X` — ветка для выполнения отдельной задачи, где `X` — номер задачи.

## Examples

```
master
feature/DVT-3
``` 

Новые изменения - рекомендуется выполнять в отдельной ветке `feature/DVT-X`. После завершения работы изменения объединяются с основной веткой через Pull Request или Merge.

<pre>
Before push:

git status

The repository must be clean.

Expected output:

nothing to commit, working tree clean
</pre>

## Сценарий ручной проверки DVT-6

В рамках задачи DVT-6 была выполнена ручная проверка работоспособности функционала подсчета прогресса менти и валидации данных.

### 1. Сборка и запуск приложения
Запуск главного класса ProgressTracker и проверка консольного вывода суммарного прогресса:
$ ./gradlew run
* Ожидаемый результат: В консоль выводится строка с корректным расчетом: "Суммарно: пройдено 25 из 36 уроков, осталось 11 уроков".

### 2. Запуск автоматических тестов
Запуск разработанных Unit-тестов (включая тесты на валидацию record Mentee и граничные случаи подсчета прогресса):
$ ./gradlew test
* Ожидаемый результат: Тесты успешно пройдены, статус сборки: BUILD SUCCESSFUL.

### 3. Отладка и инспектирование переменных (Debug)
Для проверки корректности работы цикла в методе calculateTotalProgress была запущена отладка с точкой останова (breakpoint) внутри цикла while (на операции суммирования):
* Проверка вкладки Variables: На паузе внутри цикла проверено состояние счетчиков и индексов (idx, totalCompleted, totalTotal).

### 4. Динамическое вычисление выражений (Evaluate Expression)
На 3-й итерации цикла (idx == 2), когда данные по первым двум менти уже суммированы (totalCompleted == 13), выполнено выражение через Alt + F8:
totalCompleted + mentees[idx].completedLessons()
* Ожидаемый результат: Выражение возвращает точное значение 25 (13 + 12 за последнюю итерацию), подтверждая математическую корректность алгоритма до шага сохранения в переменную.

## Кодстайл-гайд проекта devtools

Проект следует правилам Google Java Style Guide с адаптацией.
Автоматическая проверка: ./gradlew checkstyleMain

### 1. Именование методов: camelCase
До:    public void add_student(Student s) { }
После: public void addStudent(Student student) { }
Почему: Java Convention требует camelCase для методов.
Источник: https://google.github.io/styleguide/javaguide.html#s5.3-camel-case

### 2. Пробелы после if/for/while
До:    if(condition) {
После: if (condition) {
Почему: улучшает читаемость, отделяет ключевое слово от выражения.
Источник: Oracle Code Conventions — Whitespace

### 3. Длина строки: максимум 120 символов
До:    public List getStudentsFromSpecificCityWithVeryLongName...
После: public List getStudentsByCity(String city) {
Почему: длинные строки затрудняют чтение в редакторе и при code review.
Источник: https://google.github.io/styleguide/javaguide.html#s4.4-column-limit

### 4. Порядок импортов
До:    import java.util.List; import java.util.ArrayList; import java.io.File;
После: import java.io.File; import java.util.ArrayList; import java.util.List;
Почему: алфавитный порядок упрощает поиск импортов.
Источник: IntelliJ IDEA → Code → Optimize Imports

### 5. Фигурные скобки для if/for/while
До:    if (condition) doSomething();
После: if (condition) { doSomething(); }
Почему: скобки обязательны даже для однострочных блоков.
Источник: https://google.github.io/styleguide/javaguide.html#s4.1.1-braces-always-used

## Code Quality

В проекте используется **Checkstyle** для проверки соответствия Java Code Style и **JaCoCo** для контроля покрытия тестами.

### Запуск Checkstyle

Проверка основного и тестового кода:

```bash
./gradlew checkstyleMain
./gradlew checkstyleTest
```

или одновременно:

```bash
./gradlew check
```

После выполнения отчеты будут доступны по следующим путям:

```
build/reports/checkstyle/main.html
build/reports/checkstyle/test.html
```

### Запуск тестов и генерация покрытия

```bash
./gradlew test jacocoTestReport
```

HTML-отчет JaCoCo:

```
build/reports/jacoco/test/html/index.html
```

### Проверка минимального покрытия

```bash
./gradlew jacocoTestCoverageVerification
```

Если покрытие инструкций меньше 80%, задача завершится с ошибкой.

### Полная проверка проекта

```bash
./gradlew clean check
```

Команда выполнит:

- компиляцию проекта;
- запуск всех тестов;
- проверку Checkstyle;
- генерацию отчетов JaCoCo;
- проверку минимального покрытия тестами.