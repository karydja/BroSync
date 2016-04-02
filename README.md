# [WIP] FileSync
Java project developed for Distributed Programming class at Universidade Federal do Rio Grande do Norte, under the guidance of professor [Handerson Medeiros](https://sigaa.ufrn.br/sigaa/public/docente/portal.jsf?siape=2278805).

## What is it about?
The general ideia is: using FileSync, you should be able to sync the files under some directory with other users, like a Dropbox shared folder.

## Dependencies
- Postgresql 9.4.4 or higher

## Running this project
You need to set up your `src/Settings.java` class, which is responsible for all sensitive data. So rename it to `src/Settings.java` and, of course, rename the class name too.
- About Postgresql settings: this project has `file_sync` as its default database (as you can see in `SettingsSample` class), so you have to create this database in your machine (via `psql`, `pgAdmin` or something) or point this setting to another database.

Set the variables with valid values and you're ready to go :)

## License
See [LICENSE](https://github.com/karydja/FileSync/blob/master/LICENSE)

