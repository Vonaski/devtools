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