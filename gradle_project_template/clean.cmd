@echo off
echo Cleaning project... 
del .project .classpath 2>NUL:
rd /s/q bin build .settings .gradle 2>NUL:
echo Project is cleaned
pause
