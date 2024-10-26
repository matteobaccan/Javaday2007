@call _skypever.bat
java -cp %SKYPEJAR% skypeUser 1> skypeUser.log 2> skypeUser.err
type skypeUser.log|more
