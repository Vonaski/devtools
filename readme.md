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

## Code Review Checklist

### Функциональность
- [ ] Код решает поставленную задачу полностью
- [ ] Обработаны граничные случаи (null, пустые данные, экстремальные значения)
- [ ] Обработка ошибок реализована корректно

### Тесты
- [ ] Добавлены тесты для нового функционала
- [ ] Все тесты проходят локально: ./gradlew test
- [ ] Покрыты позитивные и негативные сценарии
- [ ] JaCoCo coverage >= 80% для нового кода

### Читаемость и стиль
- [ ] Имена переменных, методов и классов отражают назначение
- [ ] Нет дублирования кода (DRY)
- [ ] Checkstyle проходит без ошибок: ./gradlew checkstyleMain
- [ ] Нет закомментированного кода или отладочного вывода (System.out.println)

### Документация
- [ ] README обновлён (если добавлена новая функциональность)
- [ ] Публичные методы имеют JavaDoc (если применимо)
- [ ] Runbook обновлён (если изменились команды)

### Производительность и безопасность
- [ ] Нет очевидных проблем производительности
- [ ] Нет хардкода паролей, токенов или конфиденциальных данных

## Примеры Code Review комментариев

### Конструктивный пример 1

**Проблема:** В методе `UserService  createUser()` (строка 42) отсутствует проверка входного параметра `user`, из-за чего возможен `NullPointerException`.

**Почему это важно:** При передаче `null` приложение завершится с ошибкой, что ухудшает стабильность и усложняет поиск причины.

**Предложение:** Добавить проверку через `Objects.requireNonNull(user)` или обработать `null` до обращения к полям объекта.

**Почему комментарий хороший:** Указывает конкретное место проблемы, объясняет последствия и предлагает понятный способ исправления.

---

### Конструктивный пример 2

**Проблема:** В классе `OrderRepository` (строки 75–90) два метода содержат одинаковую логику формирования SQL-запроса.

**Почему это важно:** Дублирование кода усложняет сопровождение и увеличивает вероятность ошибок при внесении изменений.

**Предложение:** Вынести общую логику в приватный метод или использовать общий билдер запросов.

**Почему комментарий хороший:** Комментарий относится к коду, а не к автору, объясняет последствия и предлагает конкретное решение.

---

### Токсичный пример 1

**Проблема:** «Кто вообще так пишет код?»

**Почему это плохо:** Комментарий направлен на автора, а не на проблему в коде. Он не содержит конкретики и не помогает понять, что нужно исправить.

**Предложение:** Вместо этого написать:

> **Проблема:** Метод `calculatePrice()` слишком большой (около 80 строк).
>
> **Почему это важно:** Такой код сложнее читать, тестировать и поддерживать.
>
> **Предложение:** Разбить метод на несколько небольших методов, каждый из которых выполняет одну задачу.

---

### Токсичный пример 2

**Проблема:** «Это ужасный код, переделай всё.»

**Почему это плохо:** Комментарий не объясняет, что именно неправильно, и не предлагает способ решения проблемы.

**Предложение:** Вместо этого написать:

> **Проблема:** В методе `saveOrder()` (строка 58) используется `System.out.println()` для логирования.
>
> **Почему это важно:** Такой вывод нельзя гибко настраивать и отключать в production.
>
> **Предложение:** Использовать логгер (`SLF4J` + `Logback`) вместо `System.out.println()`.

**Почему комментарий хороший:** Комментарий описывает конкретную проблему, объясняет её влияние и предлагает практический способ исправления.

## Результаты само-ревью DVT-9

### 1. Закомментированный код

**Файл:** `src/main/java/ru/mentee/power/ProgressDemo.java`

**Проблема:** В классе остался блок закомментированного кода, который больше не используется.

**Почему это важно:** Закомментированный код ухудшает читаемость проекта и затрудняет сопровождение. История изменений уже хранится в Git, поэтому неактуальный код не следует оставлять в комментариях.

**Предложение:** Удалить закомментированный код. При необходимости его всегда можно восстановить из Git History.

---

### 2. Использование `System.out.println()` для отладки

**Файл:** `src/main/java/ru/mentee/power/devtools/student/StudentList.java`

**Проблема:** В методе `addStudent()` используется `System.out.println()` для вывода информации.

**Почему это важно:** Отладочный вывод не подходит для production-кода. Его невозможно гибко настраивать, отключать или фильтровать, в отличие от логирования через специализированные библиотеки.

**Предложение:** Удалить `System.out.println()` или заменить его на логгер (`SLF4J` + `Logback`).

---

### 3. Остался комментарий `TODO`

**Файл:** `src/main/java/ru/mentee/power/devtools/student/StudentList.java`

**Проблема:** В коде присутствует комментарий `TODO`, который указывает на незавершённую задачу.

**Почему это важно:** Если `TODO` не сопровождается задачей в системе отслеживания, его легко забыть, и незавершённая работа может попасть в основную ветку проекта.

**Предложение:** Выполнить запланированное изменение либо оформить отдельную задачу (Issue) и удалить комментарий `TODO` из кода.

---

## Информационный поиск — результаты DVT-11

### Запросы и источники

| № | Запрос                  | Операторы | Официальный источник | Альтернатива | Статус | Дата       |
|---|-------------------------|-----------|----------------------|--------------|:------:|------------|
| 1 | Поиск Lombok            | site:search.maven.org "lombok" OR site:mvnrepository.com "lombok" | https://search.maven.org/search?q=lombok | https://mvnrepository.com/artifact/org.projectlombok/lombok | 200 | 02.08.2026 |
| 2 | Stream API Java         | site:docs.oracle.com "stream api" intitle:api | https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html | https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html | 200 | 02.08.2026 |
| 3 | Горячие клавиши IntelliJ IDEA | site:www.jetbrains.com/help "keymap" | https://www.jetbrains.com/help/idea/keymap-reference.html | Help → Keymap Reference в самой IDEA | 200 | 02.08.2026 |

### AI-промпты и сравнение

#### Промпт 1: План решения
Задача:
Настроить GitHub Actions для Java-проекта.

Контекст:
Проект использует Gradle. Необходимо автоматически запускать Checkstyle, тесты и JaCoCo при push в master и feature/**, а также при Pull Request в master.

Ограничения:
Использовать только GitHub Actions и Gradle Wrapper. Не изменять существующую структуру проекта.

Ожидаемый результат:
Создан workflow ci.yml, который успешно запускает проверку стиля, тесты и сборку проекта.

Критерии успеха:
Workflow успешно проходит в GitHub Actions, все проверки зелёные, Pull Request можно слить.

AI-1 (Chat-GPT):
1. Создать workflow
Создать файл .github/workflows/ci.yml.
2. Настроить триггеры
Запускать workflow при push в ветки master и feature/**, а также при pull_request в master.
3. Подготовить окружение
Выполнить checkout репозитория.
Установить JDK через actions/setup-java.
Разрешить выполнение gradlew.
4. Запустить проверки качества
Выполнить ./gradlew check, чтобы запустить Checkstyle, тесты и JaCoCo.
5. Собрать проект
Выполнить ./gradlew build для проверки успешной сборки проекта.
6. Проверить результат
Убедиться, что workflow успешно проходит в GitHub Actions, все проверки имеют статус Passed, а Pull Request можно безопасно объединить с master.

AI-2 (Gemini):
1. В корне вашего проекта создайте директорию .github/workflows/, если она ещё не существует. В ней создайте файл ci.yml.
Убедитесь, что исполнимый файл gradlew закоммичен в репозиторий и имеет права на выполнение
2. Конфигурация триггеров запуска workflow:Откройте файл .github/workflows/ci.yml и задайте имя пайплайна, а также условия его вызова (push в ветки master и feature/**, а также pull_request в master).
3. Определение Job и настройка Java-окружения:Добавьте описание задачи build, укажите операционную систему ubuntu-latest, шаги выкачки кода (actions/checkout) и установки JDK с поддержкой кэширования зависимостей Gradle (actions/setup-java).
4. Настройка запуска Checkstyle, тестов и JaCoCo:Добавьте шаги выполнения проверок с помощью Gradle Wrapper. Чтобы запустить проверку стиля, модульные тесты и генерацию отчёта JaCoCo за один запуск, объедините соответствущие задачи Gradle.
5. Загрузка отчётов и результатов проверок в artifacts:Чтобы иметь доступ к отчётам Checkstyle, HTML-отчётам тестов и JaCoCo прямо в интерфейсе GitHub Actions в случае ошибок, добавьте шаг сохранении артефактов (actions/upload-artifact). 
6. Фиксация изменений и проверка на GitHub

Сравнение: Оба ассистента предложили одинаковую последовательность действий: создать workflow, настроить триггеры, установить JDK и запускать проверки через Gradle. Ответ ChatGPT оказался более кратким и сосредоточенным на минимально необходимой настройке CI. Gemini дополнительно предложил сохранять артефакты (Checkstyle, JaCoCo и отчёты тестов), что полезно для анализа ошибок, но не являлось обязательным требованием задачи. Для выполнения задания был выбран ответ ChatGPT, так как он полностью покрывает требования и не содержит лишних шагов.

#### Промпт 2: Проверка кода


Задача:
Провести code review метода поиска студента по идентификатору.

Контекст:
Метод используется в Java-приложении для поиска объекта Student в коллекции. Необходимо оценить его корректность, безопасность и соответствие рекомендациям Java.

Ограничения:
Не изменять бизнес-логику метода. Предлагать только улучшения, связанные с качеством кода, читаемостью, обработкой ошибок и использованием современных возможностей Java.

Код для проверки:
```java
public Student findById(Long id) {
    for (Student student : students) {
        if (student.getId().equals(id)) {
            return student;
        }
    }
    return null;
}
```

Ожидаемый результат:
Найти потенциальные проблемы в реализации, объяснить их важность и предложить конкретные способы улучшения кода.

AI-1 (ChatGPT):

- Возможен `NullPointerException`, если `student.getId()` вернёт `null`.
- Вместо возврата `null` лучше использовать `Optional<Student>`.
- При необходимости добавить проверку входного параметра `id`.

AI-2 (Gemini):

- Использовать `Objects.equals(student.getId(), id)` для безопасного сравнения.
- Рассмотреть использование Stream API для повышения читаемости.
- Проверить обработку случая, когда `id == null`.

**Сравнение:** Оба ассистента указали на возможные проблемы с обработкой `null` и предложили улучшить реализацию метода. ChatGPT сделал акцент на контракте метода и использовании `Optional`, тогда как Gemini предложил использовать `Objects.equals` и Stream API для повышения читаемости. Для данной задачи был выбран ответ ChatGPT, так как он уделяет больше внимания безопасности и делает поведение метода более явным.

---

## Технологический стек проекта

### Языки и платформы
- Java 25 LTS — основной язык
- Gradle (через Gradle Wrapper) — система сборки

### Инструменты качества кода
- Checkstyle — статический анализ стиля (config/checkstyle/checkstyle.xml, ./gradlew checkstyleMain)
- JUnit 5 — тесты (./gradlew test)

### CI/CD
- GitHub Actions — Checkstyle и тесты на каждый коммит (.github/workflows/)

### Правила кода
- Стиль: Google Java Style (через Checkstyle)
- Коммиты: Conventional Commits (feat:, fix:, docs:)
- Ветки: feature/DVT-X, master — основная; PR обязателен для слияния

---

## Личный глоссарий терминов Dev Tools

### Категория: Java-экосистема

#### JDK — Java Development Kit
**Определение:** Development environment for building Java applications; includes javac, jar, javadoc and other development tools.
**Контекст:** нужен для компиляции исходного Java-кода, сборки и разработки приложений.
**Пример:** `javac Main.java` компилирует программу; IntelliJ IDEA использует установленный JDK как Project SDK.
**Источник:** https://docs.oracle.com/en/java/javase/

#### JRE — Java Runtime Environment
**Определение:** Runtime environment that provides the Java Virtual Machine (JVM) and core libraries required to run Java applications.
**Контекст:** используется для запуска Java-приложений без инструментов разработки.
**Пример:** Команда `java Main` запускает приложение с помощью JRE.
**Источник:** https://docs.oracle.com/javase/8/docs/technotes/guides/

#### JVM — Java Virtual Machine
**Определение:** Virtual machine that executes Java bytecode and provides platform independence.
**Контекст:** отвечает за выполнение Java-приложений, управление памятью и сборку мусора.
**Пример:** После компиляции `Main.class` выполняется командой `java Main`.
**Источник:** https://docs.oracle.com/javase/specs/jvms/

#### IDE — Integrated Development Environment
**Определение:** Software application that provides tools for writing, debugging, testing and managing source code.
**Контекст:** используется для разработки программ с автодополнением, отладкой и запуском проектов.
**Пример:** IntelliJ IDEA автоматически компилирует проект и подсвечивает ошибки.
**Источник:** https://www.jetbrains.com/help/idea/

#### SDK — Software Development Kit
**Определение:** Collection of software development tools, libraries and documentation for creating applications on a specific platform.
**Контекст:** используется для разработки программ под определённую платформу.
**Пример:** Android SDK предоставляет инструменты для разработки Android-приложений.
**Источник:** https://developer.android.com/tools

### Категория: Инструменты разработки

#### Gradle Wrapper — Gradle Wrapper
**Определение:** Scripts that automatically download and use the correct Gradle version for a project.
**Контекст:** позволяет всем разработчикам использовать одинаковую версию Gradle без отдельной установки.
**Пример:** `./gradlew build`
**Источник:** https://docs.gradle.org/current/userguide/gradle_wrapper.html

#### Build Tool — Build Tool
**Определение:** Software that automates compiling, testing, packaging and dependency management.
**Контекст:** используется для автоматизации процесса сборки проекта.
**Пример:** Gradle выполняет команды `build`, `test` и `check`.
**Источник:** https://docs.gradle.org/current/userguide/what_is_gradle.html

#### Repository — Repository
**Определение:** Storage location that contains project files and version history managed by Git.
**Контекст:** используется для хранения исходного кода и совместной разработки.
**Пример:** `git clone https://github.com/user/project.git`
**Источник:** https://git-scm.com/docs/gitrepository-layout

#### Commit — Commit
**Определение:** Snapshot of project changes recorded in a Git repository.
**Контекст:** используется для сохранения законченной части работы.
**Пример:** `git commit -m "Add CI workflow"`
**Источник:** https://git-scm.com/docs/git-commit

#### Branch — Branch
**Определение:** Independent line of development in a Git repository.
**Контекст:** используется для разработки новых функций без изменения основной ветки.
**Пример:** `git checkout -b feature/DVT-12`
**Источник:** https://git-scm.com/docs/git-branch

### Категория: Процессы и практики

#### Pull Request — Pull Request
**Определение:** Request to merge changes from one branch into another after review.
**Контекст:** используется для проверки изменений перед объединением веток.
**Пример:** Создание Pull Request из `feature/DVT-12` в `master`.
**Источник:** https://docs.github.com/en/pull-requests

#### Code Review — Code Review
**Определение:** Process of examining source code to improve quality and detect defects before merging.
**Контекст:** помогает находить ошибки и соблюдать стандарты кодирования.
**Пример:** Разработчик оставляет замечания в Pull Request перед одобрением.
**Источник:** https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/reviewing-changes-in-pull-requests

#### CI/CD — Continuous Integration / Continuous Delivery
**Определение:** Development practices that automate building, testing and software delivery.
**Контекст:** используется для автоматического запуска сборки и тестов после каждого изменения.
**Пример:** GitHub Actions запускает Checkstyle и JUnit после `git push`.
**Источник:** https://docs.github.com/en/actions

#### Checkstyle — Checkstyle
**Определение:** Static analysis tool that checks Java source code against coding standards.
**Контекст:** используется для проверки соответствия кода выбранному стилю.
**Пример:** `./gradlew checkstyleMain`
**Источник:** https://checkstyle.sourceforge.io/

#### Debug — Debugging
**Определение:** Process of identifying and fixing defects in software using debugging tools.
**Контекст:** используется для поиска причин ошибок во время выполнения программы.
**Пример:** В IntelliJ IDEA программа запускается в режиме Debug с пошаговым выполнением кода.
**Источник:** https://www.jetbrains.com/help/idea/debugging-code.html

---

## Вопросы по сложным терминам

### Вопрос 1: Gradle Wrapper
**Задача:** Понять, зачем в проекте используется Gradle Wrapper и чем он отличается от обычной установленной версии Gradle.

**Контекст:** При выполнении лабораторной работы все команды запускались через `./gradlew`, хотя Gradle уже установлен на компьютере.

**Ограничения:** Понимаю, что Wrapper связан с Gradle, но пока не ясно, зачем хранить дополнительные файлы (`gradlew`, `gradlew.bat`, папку `gradle/wrapper`) в репозитории.

**Ожидаемый результат:** Понять принцип работы Gradle Wrapper, его преимущества для командной разработки и случаи, когда следует использовать именно его.

**Критерии успеха:** Смогу объяснить, почему в проекте рекомендуется запускать `./gradlew build`, а не `gradle build`, и какую проблему это решает.

---

### Вопрос 2: CI/CD
**Задача:** Разобраться, как работает процесс Continuous Integration и Continuous Delivery в GitHub Actions.

**Контекст:** В проекте настроен workflow, который автоматически запускает Checkstyle, тесты и сборку после каждого `push` и `pull request`.

**Ограничения:** Понимаю, что проверки выполняются автоматически, но не до конца понимаю последовательность этапов и их назначение.

**Ожидаемый результат:** Понять, какие этапы включает CI/CD, что происходит после отправки изменений в репозиторий и почему автоматические проверки важны перед слиянием веток.

**Критерии успеха:** Смогу самостоятельно объяснить полный путь изменений от команды `git push` до успешного завершения GitHub Actions и слияния Pull Request.