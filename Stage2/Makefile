compile:
	@javac -d ../classes ../src/State.java ../src/SwitchState.java ../src/Sensor.java ../src/MagneticSensor.java ../src/Door.java ../src/Window.java
	@javac -d ../classes -cp ../classes ../src/Central.java ../src/Siren.java ../src/AePlayWave.java
	@javac -d ../classes -cp ../classes Stage2.java
	@echo Compilación completada
run:
	@java -cp ../classes Stage2
clean:
	@rm -rf ../classes
	@echo Archivos eliminados