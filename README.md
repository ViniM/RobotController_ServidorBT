RobotController_ServidorBT
==========================

Servidor e Cliente Bluetooth para interpretação e execução de comandos nos servomotores.

Esta trabalho possui um aplicativo cliente Android para mover até seis servomotores de 180º. Um servidor em Java interpreta os dados vindos do cliente e os envia ao arduino que por sua vez executa as mudanças de ângulo.

Os seguintes arquivos usados para fazer a conexão com a porta serial devem ser copiados na pasta do Java nos caminhos:

RXTXcomm.jar ---> <JAVA_HOME>\jre\lib\ext

rxtxSerial.dll ---> <JAVA_HOME>\jre\bin

rxtxParallel.dll ---> <JAVA_HOME>\jre\bin

Sketh  na pasta Arduino/Servo.ino deve ser carregada dentro do Arduino pela própria IDE do microcontrolador. 
http://arduino.cc/en/Main/Software

As bibliotecas externas devem ser adicionadas dentro do projeto no NetBeans e se encontram no diretório "lib", são elas:
jcommon.jar;
bluecove-2.1.1-SNAPSHOT.jar;
bluecove-gpl-2.1.1-SNAPSHOT.jar;
RXTXcomm.jar;

Execute o RobotController (Servidor) pelo Netbeans.

Arquivos da biblioteca Bluecove também podem ser encontrados aqui: https://code.google.com/p/bluecove/downloads/list


O Aplicativo cliente cujo código fonte se encontra no diretório "Aplicativo Android" deve ser carregado na plataforma "App Inventor" http://ai2.appinventor.mit.edu/ e na sessão "Blocks" o endereço MAC que esta como exemplo deverá ser substituido pelo endereço MAC de seu dispositivo Bluetooth.
Ir no menu "Build" e escolha a 1ª opção para escanear o QR Code e baixar a APK diretamente no seu celular. Ou a 2ª opção para baixar a APK no seu computador.

Instale no seu dispositivo móvel e  pareie o bluetooth do smartphone com o dispositivo do PC (Servidor) antes de iniciar o aplicativo.

Video demonstração:
http://youtu.be/_13USofRFNA

Projeto feito para o Trabalho de Conclusão de Curso.
