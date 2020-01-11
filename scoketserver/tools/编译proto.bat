
cd ..
cd proto
CALL protoc.exe --java_out=../src --proto_path=../proto/ ../proto/*.proto
pause
