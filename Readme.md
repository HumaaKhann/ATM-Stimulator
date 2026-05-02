cd /Users/rasim/Projects/ATM-Stimulator && rm -rf out/* && javac -d out $(find src -name "*.java") && java -cp out Main


cd "C:\path\to\ATM-Stimulator"; Remove-Item -Recurse -Force out -ErrorAction SilentlyContinue; New-Item -ItemType Directory out | Out-Null; Get-ChildItem -Recurse -Filter *.java src | ForEach-Object FullName | Set-Content sources.txt; javac -d out @sources.txt; Remove-Item sources.txt; java -cp out Main
