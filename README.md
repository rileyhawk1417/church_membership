# Main branch

## Description 📓

The project is a template meant to be flexible in any area.
The current template is focused on dealing with school management.

## Todo Lists 🗒️

- [ ] Update tables.
- [ ] Possibly add an active menu to export to other formats such as PDF.
- [ ] Possibly fix the printer script in future as well.
- [ ] Figure out a way to ask for DB connection.
  - [ ] select type of database from the beginning.
- [ ] Copy record to clipboard.
- [ ] Work out update channel.
  - [ ] Setup code to update jar file or zip file.
  - [ ]  Ensure easy use for users to update;
- [ ] Write documentation for the software.


## DONE ✔️

- [x] Use Maven Build System.
- [x] Try to use JavaFX control and layouts
  - [x] Connect to JDBC driver for postgresql.
  - [x] Setup postgresql tables.
  - [x] Authenticate user or get message that user has been found.
  - [x] Read tables.
  - [x] Insert tables.
- [x] Refactor for login to use one screen instead of 3.
- [x] Fix Scrollpane and tableview.
- [x] Optimize dialog windows and extend them.
- [x] Delete table records.
- [x] Export tables to excel document.

### Database Options

- Sqlite: Makes it portable but less security.
- Mysql: Secure just needs configuring.
- Postgresql: Is my preference.

## Notes/Warnings ⚠️

- There are headaches along the way when dealing with this project.
- Sqlite does not use the file when packaged in jar format. 
  - It instead copies it and works outside the jar file

