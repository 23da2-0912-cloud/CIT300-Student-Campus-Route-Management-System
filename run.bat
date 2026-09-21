@echo off
echo Compiling Java project...
if not exist out mkdir out
javac -d out src\*.java
if errorlevel 1 (
    echo.
    echo Compilation failed. Please read the error message above.
    pause
    exit /b 1
)
echo.
echo Starting the system...
echo.
java -cp out Main
pause
