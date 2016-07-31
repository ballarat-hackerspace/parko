int lightS = 0;
int carold = 0;
int car = 0;
void setup() {
    pinMode(D7, OUTPUT);
     digitalWrite(D7, HIGH);
    pinMode(A5, INPUT);
    lightS = analogRead(A5);
    delay(1000);
     digitalWrite(D7, HIGH);
    delay(1000);
    lightS = analogRead(A5) + lightS;
    delay(1000);
    lightS = analogRead(A5) + lightS;
    delay(1000);
    lightS = analogRead(A5) + lightS;
    lightS = lightS / 5 - 300;
     digitalWrite(D7, LOW);
}

void loop()
{
    int lightL = analogRead(A5);
    if (lightL < lightS)
    {
       car = 1;
    }
    else
    {
       car = 2;
    }
    //car change- 1 = there is a car. 2 = no car.

    Serial.printf("light = %d/%d\n\r", lightL, lightS);

    if (car != carold)
    {
        digitalWrite(D7, HIGH);
        delay(1500);
        int lightL = analogRead(A5);
        if (lightL < lightS)
        {
           car = 1;
        }
        else
        {
           car = 2;
        }
        if(car != carold)
        {
            carold = car;

            if (lightL < lightS)
            {
                Spark.publish("occupied", "True");
                digitalWrite(D7, LOW);
            }
            else
            {
                Spark.publish("occupied", "False");
                digitalWrite(D7, LOW);
            }
        }
        digitalWrite(D7, LOW);
    }
    delay(1000);
}
