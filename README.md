# at-backup
## About
at-backup is a simple tool writen in java. It can be used to make backup of directory to other directory.
## Usage 
* Build at-backup
```bash
mvn package
```
* You can also build an assemble with all dependencies 
```bash
mvn clean compile assembly:single
```

* Run at-backup with Your parameters
```bash
java -jar target/at-backup.jar -s <source-dir> -t <target-dir> -c <config filename> -l intervalInMiliseconds
```

## Parameters
* ```-s``` - source directory
* ```-t``` - target directory
* ```-c``` - xml config filename [ optional ]
* ```-l``` - infinity task executing interval in miliseconds [ optional ]
* ```-log``` - log level, possible options: DEBUG, INFO ...
* ```-mode``` - synchronization mode, at the moment only one option is available: RemoveOrphans
* ```-io``` - IO mode. Real or ReadOnly

## Source/target directories
* You can provide absolute path like "C:/source" or partition/disk root name. To do this You must prefix partition name with "@". Example:
```bash
java -jar at-backup.jar -s @Data -t @Backup
```
* @Data and @Backup parameters will be replaced with real directory path

## Modes
* RemoveOrphans - Removes files in target no longer existing in source

## IO Modes
* Real - All operations are executed physically on hard disk
* ReadOnly - Only read operations are executed. Application will not modify the file system.

## XML Config file
* ```<beforeCommand>``` - this command will be executed before main task
* ```<afterCommand>``` - this command will be executed after main task

Example: 

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<config>
    <beforeCommand>net use z: \\AveShare\nas_backup /USER:root password</beforeCommand> <!-- Share will be mounted before task -->
    <afterCommand>net use z: /delete /YES</afterCommand> <!-- After task the same share is unmounted -->
</config>
```
