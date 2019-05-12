//
// Copyright 2015 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

// FirebaseDemo_ESP8266 is a sample that demo the different functions
// of the FirebaseArduino API.
#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>

// Set these to run example.
#define FIREBASE_HOST "smart-b1875.firebaseio.com"
#define FIREBASE_AUTH "J6FRxqSBd1eHcbi18Yud4XGyQZCo23xv2Pvp23Eb"
#define WIFI_SSID "na"
#define WIFI_PASSWORD "kucinggarong"

#define echoPin 4 // Echo Pin
#define trigPin 5 // Trigger Pin
#define irPin 12 // ir pin
#define led D3
#define buzz 16 //Buzzer Pin
int ldrPin = A0;
int ldrValue = 0;
int maximumRange = 200; // Maximum range needed
int minimumRange = 0; // Minimum range needed
long duration, distance; // Duration used to calculate distance

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
}

int n = 0;

void loop() {
  // set value
  n = Firebase.getInt("LED_STATUS");
  if (n == 1) {
      Serial.print("LED ON");  
      digitalWrite(led, HIGH);
      return;
      delay(1000);
  }else{
    Serial.print("LED OFF");  
    digitalWrite(led, LOW);
  }
}
