cd /Users/rasim/Projects/ATM-Stimulator && rm -rf out/* && javac -d out $(find src -name "*.java") && java -cp out Main


 cd "C:\Users\Nahila\OneDrive\Documents\DevProjects\Java\ATM_Stimulator"; Remove-Item -Recurse -Force out -ErrorAction SilentlyContinue; mkdir out; javac -d out (Get-ChildItem -Recurse -Filter *.java src).FullName; java -cp out Main    