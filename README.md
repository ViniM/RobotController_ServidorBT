RobotController_ServidorBT
==========================

Servidor Bluetooth para interpretação e execução de comandos nos servomotores.


Os seguintes arquivos usados para fazer a conexão com a porta serial devem ser copiados na pasta do Java nos caminhos:
RXTXcomm.jar ---> <JAVA_HOME>\jre\lib\ext
rxtxSerial.dll ---> <JAVA_HOME>\jre\bin
rxtxParallel.dll ---> <JAVA_HOME>\jre\bin

As bibliotecas externas devem ser adicionadas dentro do projeto no NetBeans, são elas:
jcommon.jar
bluecove-2.1.1-SNAPSHOT.jar
bluecove-gpl-2.1.1-SNAPSHOT.jar

Se encontram no diretório "lib".


