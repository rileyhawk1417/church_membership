# Main branch

## Description 📓

The project is a template meant to be flexible in any area.
The current template is focused on dealing with school management.

## Todo Lists 🗒️

- [ ] Delete tables.
- [ ] Update tables.
- [x] Export tables to excel document.
- [ ] Possibly add an active menu to export to other formats such as PDF.
- [ ] Possibly fix the printer script in future as well.
- [ ] Figure out a way to ask for DB connection.
  - [ ] select type of database from the beginning.

## DONE ✔️

- [x] Use Maven Build System.
- [x] Try to use JavaFX control and layouts
  - [x] Receive input from fields and add event listener to "enter" key.
  - [x] Connect to JDBC driver for postgresql.
  - [x] Setup postgresql tables.
  - [x] Authenticate user or get message that user has been found.
  - [x] Read tables.
  - [x] Insert tables.

### Database Options

- Sqlite: Makes it portable but less security.
- Mysql: Secure just needs configuring.

## Notes/Warnings ⚠️

- There are headaches along the way when dealing with this project.
- Sqlite does not use the file when packaged in jar format. It instead copies it and works outside the jar file

