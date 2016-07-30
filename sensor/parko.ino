int lightS = 0;
unsigned long lasttime = 0UL;

void setup() {
    pinMode(D7, OUTPUT);
    pinMode(A5, INPUT);

		// calibarte the sensor to current lighting conditions
    lightS = analogRead(A5);
    delay(1000);
    lightS = analogRead(A5) + lightS;
    delay(1000);
    lightS = analogRead(A5) + lightS;
    delay(1000);
    lightS = analogRead(A5) + lightS;
    delay(1000);
    lightS = analogRead(A5) + lightS;
    lightS = lightS / 5 - 300;
}

void loop()
{
    unsigned long now = millis();
    int lightL = analogRead(A5);

    Serial.printf("light = %d/%d\n\r", lightL, lightS);

    if (now-lasttime>60000UL)
    {
        lasttime = now;

        if (lightL < lightS)
        {
            Spark.publish("occupied", "True");
        }
        else
        {
            Spark.publish("occupied", "False");
        }
    }

    if (lightL < lightS)
    {
        digitalWrite(D7, HIGH);
    }
    else
    {
        digitalWrite(D7, LOW);
    }
}
