//
// Programa para controle de servomotores
//

#include <Servo.h>   // Inclui a biblioteca para comunicação com os servos

Servo servo1;        // Cria um objeto "Servo" para controle de um servomotor
Servo servo2;
Servo servo3;
Servo servo4;
Servo servo5;
Servo servo6;        // Podem ser criados até 6 servos

// Dados enviados e recebidos via comunicação serial
int nservo = 0;      // Número do servo
int angle = 0;       // Posição (ângulo) do servo
byte space = 0;      // Espaço em branco
byte separator = 0;  // Traço

void setup(){
  Serial.begin(9600); // Inicia a porta serial
  
  servo1.attach(3);   // Liga o servo1 ao pino 3 do Arduino
  servo2.attach(5);
  servo3.attach(6);
  servo4.attach(9);
  servo5.attach(10);
  servo6.attach(11);
  }

void loop(){
  // Trata de um dado, se houver
  if (Serial.available() >= 4){
    nservo = Serial.read();       // Lê o número do servo
    separator = Serial.read();    // Deve ler um traço
    angle = Serial.read();        // Lê a posição (ângulo) do servo
    space = Serial.read();        // Deve ler um espaço em branco
    Serial.flush();               // Descarrega os valores lidos
    if (nservo==1) {        // Se for para posicionar o servo1
      servo1.write(angle);  // Posiciona o servo1
      delay(15);            // Gera um atraso para o servo poder se posicionar
    }
    if (nservo==2) {        
      servo2.write(angle);  
      delay(15);            
    }
    if (nservo==3) {        
      servo3.write(angle);  
      delay(15);            
    }
    if (nservo==4) {        
      servo4.write(angle);  
      delay(15);            
    }
    if (nservo==5) {        
      servo5.write(angle);  
      delay(15);           
    }
    if (nservo==6) {        
      servo6.write(angle);  
      delay(15);            
    }
    Serial.print(nservo);            // Devolve o número do servo para o PC
    Serial.write(byte(separator));  // Escreve um traço
    Serial.print(angle);            // Devolve o ângulo para o PC
    Serial.write(byte(space));      // Escreve um espaço em branco
    Serial.print("\n");             // Pula uma linha
  }
}

