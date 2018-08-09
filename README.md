# Pricing API



This module provides information of price movement(based on selected dates), average price movement(based on selected dates) and suggestion signal to buy/sell/hold(based on number of X days) for bitcoin. 

### Project setup:

Clone repository 
```
git clone https://github.com/arpitvj/pricing-api.git
```

Go to project root folder
```
cd pricing-api
```

Run project

```
sbt run
```

To run in Debug mode
```
sbt run -jvm-debug 5000
```


### APIs

1. **[GET]** /price/total-movement?from=2010-10-10&to=2012-10-10

Provides the total price movements between two given dates.


2. **[GET]** /price/average-movement?from=2016-10-10&to=2017-10-10

Provides average price movements between two given dates.

#### Logic
Calculates SMA(Simple Moving Average)
```
(Sum of all the prices between dates / Total number of price quantities)
```  
3. **[GET]** /suggestion?days=1

Provides the suggestion signal to `BUY` / `SELL` / `HOLD` bitcoin based on the number of days provided.
The number of days is just the amount of days needs to be taken into account to provide suggestion.

#### Logic
1. today's SMA = calculate (today - number of x days) SMA vs 25 days SMA
2. yesterday SMA = calculate (yesterday - number of x days) SMA vs (yesterday - 25 days) SMA
3. if (yesterday's calculate (yesterday - number of x days) < (yesterday - 25 days) SMA && today's (today - number of x days)SMA > (today's 25 days SMA)) => `BUY`
4. else if (yesterday's calculate (yesterday - number of x days) > (yesterday - 25 days) SMA && today's (today - number of x days)SMA < (today's 25 days SMA)) => `SELL`
5. else (yesterday's calculate (yesterday - number of x days) > (yesterday - 25 days) SMA && today's (today - number of x days)SMA > (today's 25 days SMA)) => `HOLD`
    
