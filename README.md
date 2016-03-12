# at-backup
## About
at-backup is a simple tool writen in java. It can be used to make backup of directory to other directory.
## Usage 
1. Build at-backup
```
mvn package
```
2. Run at-backup with Your parameters
```
java -jar target/at-backup.jar -s <source-dir> -t <target-dir> -c <config filename> -l intervalInMiliseconds
```

## Parameters
* ```-s``` - source directory
* ```-t``` - target directory
* ```-c``` - xml config filename [ optional ]
* ```-l``` - infinity task executing interval in miliseconds [ optional ]

## XML Config file
* ```<beforeCommand>``` - this command will be executed before main task
* ```<afterCommand>``` - this command will be executed after main task

Example: 
```<?xml version="1.0" encoding="UTF-8" ?>
<config>
    <beforeCommand>net use z: \\AveShare\nas_backup /USER:root password</beforeCommand>
    <afterCommand>net use z: /delete /YES</afterCommand>
</config>```
