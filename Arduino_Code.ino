#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <FirebaseArduino.h>

#define KEY "j4rEPYY_cCPQFAhcFej-Bxl-HZtmwI3Sn5DIw6BVz83" //Key Untuk IFTTT
// Set these to run example.
#define FIREBASE_HOST "smart-b1875.firebaseio.com"
#define FIREBASE_AUTH "J6FRxqSBd1eHcbi18Yud4XGyQZCo23xv2Pvp23Eb"
#define WIFI_SSID "CariWifi"
#define WIFI_PASSWORD "kucinggarong"
#define EVENT_NAME2 "ultra"

#define echoPin D2 // Echo Pin
#define trigPin D1 // Trigger Pin
#define irPin D6 // ir pin
#define led D3
#define buzz D0 //Buzzer Pin
int ldrPin = A0;
int ldrValue = 0;
int maximumRange = 200; // Maximum range needed
int minimumRange = 0; // Minimum range needed
long duration, distance; // Duration used to calculate distance

void trigger(String iftttEventName, String iftttKey)
{
  trigger(iftttEventName, iftttKey, "", "", "");
}

void trigger(String iftttEventName, String iftttKey, String value1, String value2, String value3)
{
  HTTPClient http;
  String json = "{\"value1\":\"" + value1 + "\", \"value2\":\"" + value2 + "\", \"value3\":\"" + value3 + "\"}";
  http.begin("http://maker.ifttt.com/trigger/" + iftttEventName + "/with/key/" + iftttKey);
  http.addHeader("Content-Type", "application/JSON");
  http.addHeader("Content-Length", String(json.length()));
  int httpCode = http.POST(json);
  http.end();
}

void setup() {
  Serial.begin(9600);

  // connect to wifi.
 
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("connecting");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println();
  Serial.print("connected: ");
  Serial.println(WiFi.localIP());

  pinMode(led, OUTPUT);
  pinMode(trigPin, OUTPUT);
  pinMode(echoPin, INPUT);
  pinMode(irPin, INPUT);
  pinMode(buzz, OUTPUT);

  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
  Firebase.setFloat("LED_STATUS", 0);
  Firebase.setInt("keadaan",0);
  Firebase.setInt("distance",0);
}

int n = 0;

void loop() {
  // set value
  n = Firebase.getInt("LED_STATUS");
  if (n == 1) {
      Serial.print("LED ON");  
      digitalWrite(led, HIGH);
  }else{
    Serial.print("LED OFF");  
    digitalWrite(led, LOW);
  }

  digitalWrite(trigPin, LOW); 
  delayMicroseconds(2); 

  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10); 
 
  digitalWrite(trigPin, LOW);
  duration = pulseIn(echoPin, HIGH);

  //Calculate the distance (in cm) based on the speed of sound.
  distance = duration/58.2;
  
  Serial.print("");
  Serial.print("Jarak distance : ");
  Serial.println(distance);
  Firebase.setInt("distance",distance);
  
  if (distance > 100 ){
  /* Send a negative number to computer and Turn LED ON 
  to indicate "out of range" */
  Serial.print("Komputer Hilang ");
  trigger(EVENT_NAME2,KEY);
  digitalWrite (buzz,HIGH);
  Firebase.setInt("keadaan",1);
  return;
  }else {
  /* Send the distance to the computer using Serial protocol, and
  turn LED OFF to indicate successful reading. */
  Serial.print("Komputer Aman ");
  digitalWrite (buzz,LOW);
  Firebase.setInt("keadaan",0);
  } 
  delay(1000);
  //if(digitalRead(irPin)==LOW){
    //Serial.println("Ada Orang");  
    //trigger(EVENT_NAME,KEY);
  
  //}else{
    //Serial.println("clear");  
  //}
  //delay(1000);
  //ldrValue = analogRead(ldrPin);
  //Serial.print("Nilai Cahaya : ");
  //Serial.println(ldrValue);
}
