cd /Users/rasim/Projects/ATM-Stimulator && rm -rf out/* && javac -d out $(find src -name "*.java") && java -cp out Main


cd /d "C:\path\to\ATM-Stimulator" && rmdir /s /q out 2>nul & mkdir out && for /r src %f in (*.java) do @echo %f>>sources.txt && javac -d out @sources.txt && del sources.txt && java -cp out Main
