compile:
	@javac -d ../classes ../src/State.java ../src/SwitchState.java ../src/Sensor.java ../src/MagneticSensor.java ../src/Door.java ../src/Window.java
	@javac -d ../classes -cp ../classes ../src/Siren.java ../src/AePlayWave.java
	@javac -d ../classes -cp ../classes ../src/Person.java ../src/Central.java ../src/PIR_Detector.java ../src/Recta.java ../src/Circulo.java 
	@javac -d ../classes -cp ../classes Stage3.java
	@echo Compilación completada
run:
	@java -cp ../classes Stage3 config.txt
clean:
	@rm -rf ../classes
	@echo Archivos eliminados