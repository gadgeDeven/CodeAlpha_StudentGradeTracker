# CodeAlpha_StudentGradeTracker

## Description
A Spring Boot web application for managing student grades. Features include adding/editing/deleting students, calculating average/highest/lowest scores, and viewing summary reports.

## Tech Stack
- Backend: Spring Boot 3.5.6, JPA/Hibernate, MySQL
- Frontend: JSP, Bootstrap 5, JSTL
- Validation: Bean Validation for name (min 2 chars) & grades (0-100)
- API: REST endpoint `/api/students/{id}/summary` for JSON

## Setup & Run
1. Install MySQL, create DB `grade_tracker` with user `root/root`.
2. Clone repo: `git clone https://github.com/gadgeDeven/CodeAlpha_StudentGradeTracker.git`
3. `mvn clean install`
4. `mvn spring-boot:run`
5. Open `http://localhost:8081/students`

## Features
- **Add Student**: Form with name & comma-separated grades (e.g., 85,92,78).
- **List**: View all students with actions (edit/delete), calculations.
- **Edit/Delete**: Update grades, confirm delete.
- **Report**: Summary table with overall average.
- **Validations**: Name required (2-100 chars), grades numeric 0-100.

#CodeAlphaIntern #JavaProject
