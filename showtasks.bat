call runcrud.bat
if "%ERRORLEVEL%" == "0" goto runbrowser
echo.
echo Unable to start runcrud.bat
goto fail


:runbrowser
echo Wait. Starting Tomcat server.
timeout 30
explorer "http://localhost:8080/crud/v1/task/getTasks"
if "%ERRORLEVEL%" == "0" goto end
echo.
echo There were problems with running browser.
goto fail


:fail
echo.
echo There were errors


:end
echo.
echo Work is finished