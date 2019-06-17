# Script as a Service (SAAS)

build service without rebuild/restart or republish

## Feature

* everything is __async__
* everything is __dynamic__
* every service is __script__
* blocking is not an optional

## FeatureRoadMap
* [ ] call other script functions in script  
* [ ] generate script beans into spring context; maybe a bean map;  
* [ ] autowire script beans   
* [ ] dynamic script support more language(python, ts, dart2 etc.); maybe an extendable interface  

## Diagram

### dynamic routes & services
![structure diagram](asset/script_as_service.png)

### async solutions
* reactive based

![reactive-based-async](asset/reactive-based-async.png)

* coroutine based

![reactive-based-async](asset/coroutine-based-async.png)

## Asyncript
make groovy support async