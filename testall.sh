pushd ~/workspace/TestMoney

echo;echo;echo;echo;
echo "testing badA"
javac -cp res/junit.jar:res/badA.jar src/se254/money/TestMoney.java -d bin/
java -cp res/junit.jar:res/badA.jar:bin/ se254.money.TestMoney

echo;echo;echo;echo;
echo "testing badB"
javac -cp res/junit.jar:res/badB.jar src/se254/money/TestMoney.java -d bin/
java -cp res/junit.jar:res/badB.jar:bin/ se254.money.TestMoney

echo;echo;echo;echo;
echo "testing badC"
javac -cp res/junit.jar:res/badC.jar src/se254/money/TestMoney.java -d bin/
java -cp res/junit.jar:res/badC.jar:bin/ se254.money.TestMoney

echo;echo;echo;echo;
echo "testing badD"
javac -cp res/junit.jar:res/badD.jar src/se254/money/TestMoney.java -d bin/
java -cp res/junit.jar:res/badD.jar:bin/ se254.money.TestMoney

echo;echo;echo;echo;
echo "testing badE"
javac -cp res/junit.jar:res/badE.jar src/se254/money/TestMoney.java -d bin/
java -cp res/junit.jar:res/badE.jar:bin/ se254.money.TestMoney

echo;echo;echo;echo;
echo "testing badF"
javac -cp res/junit.jar:res/badF.jar src/se254/money/TestMoney.java -d bin/
java -cp res/junit.jar:res/badF.jar:bin/ se254.money.TestMoney

echo;echo;echo;echo;
echo "testing badG"
javac -cp res/junit.jar:res/badG.jar src/se254/money/TestMoney.java -d bin/
java -cp res/junit.jar:res/badG.jar:bin/ se254.money.TestMoney

echo;echo;echo;echo;
echo "testing badH"
javac -cp res/junit.jar:res/badH.jar src/se254/money/TestMoney.java -d bin/
java -cp res/junit.jar:res/badH.jar:bin/ se254.money.TestMoney

echo;echo;echo;echo;
echo "testing badI"
javac -cp res/junit.jar:res/badI.jar src/se254/money/TestMoney.java -d bin/
java -cp res/junit.jar:res/badI.jar:bin/ se254.money.TestMoney

echo;echo;echo;echo;
echo "testing badJ"
javac -cp res/junit.jar:res/badJ.jar src/se254/money/TestMoney.java -d bin/
java -cp res/junit.jar:res/badJ.jar:bin/ se254.money.TestMoney

echo;echo;echo;echo;
echo "testing good"
javac -cp res/junit.jar:res/good.jar src/se254/money/TestMoney.java -d bin/
java -cp res/junit.jar:res/good.jar:bin/ se254.money.TestMoney

popd
