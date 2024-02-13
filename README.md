# Beers App
This app is part of a technical interview.

<img src="https://github.com/otmaneTheDev/BeersApp/assets/60321522/cf8d05ba-f61e-4707-bea3-e4517c2bfa9a" width="200" height="450"/>
<img src="https://github.com/otmaneTheDev/BeersApp/assets/60321522/07363ff6-082f-46ad-a788-d22e01b237d0" width="200" height="450"/>


### 1.1. Tech Stack
|Feature|Library|Version|
|---|---|---|
| Dependency Injection | Dagger Hilt | 2.50 |
| HTTP Requests | Retrofit | 2.9.0 |
| Async/Reactivity | Coroutines + Flow | 1.8.0 |
| Paging | Paging 3 | 3.2.1 |

### 1.2 App using MVI architecture

The app follows the MVI architecture in the BeerListFragment

### 1.3 App is divided in different modules
 - :app contains the mainActivity and the BeerApp
 - :feature_beers:data -> Logic to retrieve the beer info
 - :feature_beers:domain -> Business logic
 - :feature_beers:presentation -> Ui layer to show the list and detail of a beer
 - :core -> Common logic for different modules
 - :ui_components -> Ui components 
