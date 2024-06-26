#include <Wire.h>
#include <LiquidCrystal_I2C.h>

LiquidCrystal_I2C lcd(0x27, 16, 2); // Set the LCD address to 0x27 for a 16 chars and 2 line display

const int led1Pin = 18;
const int led2Pin = 15;

void setup() {
  lcd.init();         // Initialize the LCD
  lcd.backlight();    // Turn on the LCD screen backlight

  // Initialize serial communication
  Serial.begin(115200);

  // Set LED pins as outputs
  pinMode(led1Pin, OUTPUT);
  pinMode(led2Pin, OUTPUT);

    lcd.setCursor(0,0);
        lcd.print("*BUTTS TO DOORS*");


  
}

void loop() {
  // Turn on LED 1 and display "Opened"
  digitalWrite(led1Pin, HIGH);
  digitalWrite(led2Pin, LOW);

    lcd.setCursor(0, 1);
    lcd.print("   - WAITING -  ");
    delay(3000);

      digitalWrite(led1Pin, LOW);
  digitalWrite(led2Pin, HIGH);

    lcd.setCursor(0, 1);
    lcd.print("   - OPENED -   ");

 delay(4000);

       digitalWrite(led1Pin, HIGH);
  digitalWrite(led2Pin, LOW);

    lcd.setCursor(0, 1);
    lcd.print("   - CLOSED -   ");

 delay(4000);
 
  
/*
  if (digitalRead(led1Pin) == HIGH) {
    lcd.setCursor(0, 1);
    lcd.print("   - CLOSED -   ");
  } else if (digitalRead(led2Pin) == HIGH) {
    lcd.setCursor(0, 1);
    lcd.print("   - OPENED -   ");
  } else {
    lcd.setCursor(0, 1);
    lcd.print("                "); // Clear the line
  }

  delay(2000);  // Wait for 2 seconds

  // Turn off LED 1, turn on LED 2, and display "Closed"
  digitalWrite(led1Pin, LOW);
  digitalWrite(led2Pin, HIGH);

  if (digitalRead(led1Pin) == HIGH) {
    lcd.setCursor(0, 1);
    lcd.print("   - CLOSED -   ");
  } else if (digitalRead(led2Pin) == HIGH) {
    lcd.setCursor(0, 1);
    lcd.print("   - OPENED -   ");
  } else {
    lcd.setCursor(0, 1);
    lcd.print("                "); // Clear the line
  }*/

 // delay(2000);  // Wait for 2 seconds
}
