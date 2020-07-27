
//필요한 라이브러리 포함
#include <ESP8266WiFi.h>                          
#include <FirebaseArduino.h>     

//Firebase 호스트,db키 값 입력
#define FIREBASE_HOST "led-on-off-c039a.firebaseio.com"
#define FIREBASE_AUTH "AIzaSyDPJB7txY85s1Fda3dxZko2py5eiEy62fE" 

//와이파이 아이디와 비밀번호 입력
#define WIFI_SSID "구웨이이"
#define WIFI_PASSWORD "09090909"


String fireStatus = "";   // firebase DB로 부터 받은 값 저장 
int led = D4;             // NodeMCU LED pin   
                                                  
void setup() { 
  Serial.begin(9600); 
  delay(1000);   
  pinMode(led, OUTPUT); //led 출력                
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);    //WiFi 연결                           
  Serial.print("Connecting to "); 
  Serial.print(WIFI_SSID); 
  while (WiFi.status() != WL_CONNECTED) { 
    Serial.print("."); 
    delay(500); 
    } 
    Serial.println(); 
    Serial.print("Connected to "); 
    Serial.println(WIFI_SSID); 
    Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);   // firebase에 연결 
    Firebase.setString("LED_STATUS", "OFF");        //초기 LED 상태를 OFF로 설정
}

void loop() 
{ 
  fireStatus = Firebase.getString("LED_STATUS"); // Firebase DB로 부터 LED 상태 값 읽어오기
  if (fireStatus == "ON")   {                   // Firebase DB로 부터 받은 LED 상태 값이 “ON”이면 LED 켜기 
    Serial.println("Led Turn On");              // 시리얼 모니터에 “Led Turned ON” 문자열 출력 
    digitalWrite(led, HIGH);                    // LED 켜기
    } else if (fireStatus == "OFF")  {          // Firebase DB로 부터 LED 상태 값 읽어오기
      Serial.println("Led Turned OFF");         // 시리얼 모니터에 “Led Turned OFF” 문자열 출력 
      digitalWrite(led, LOW);                   // LED 끄기 
      } else { Serial.println("Command Error! Please send ON/OFF");
      }  
  }
