const weatherForm = document.querySelector(".weatherForm");
const cityInput = document.querySelector(".cityInput");
const card = document.querySelector(".card");
const apiKey = "f848137c0075875db0fd2af5e13ab83e";

weatherForm.addEventListener("submit", async event =>{
        event.preventDefault();

        const city = cityInput.value;

        if(city){
              try{
                const weatherData = await getWeatherData(city);
                displayWeatherInfo(weatherData);
              }
              catch(error){
                console.error(error);
                displayError(error);
              }
           
        }
        else{
            displayError("Please enter a city");
        }


});

async function getWeatherData(city) {
       
     const apiUrl = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${apiKey}`;

     const response = await fetch(apiUrl);
     console.log(response);

     if(!response.ok){
        throw new Error("could not fetch weather data");
     }

     return await response.json();
}

function displayWeatherInfo(data){
        const {name:city,main:{temp,humidity},weather:[{description,id}]} =data;

        card.textContent = "";
        card.style.display = "flex";

        const cityDisplay = document.createElement("h1");
        const tempDisplay = document.createElement("p");
        const humidityDisplay = document.createElement("p");
        const descDisplay = document.createElement("p");
        const weatherimg = document.createElement("img")
       

        cityDisplay.textContent = city;
        tempDisplay.textContent = `${((temp) - 273).toFixed(1)}°C`;
        humidityDisplay.textContent = `Humidity: ${humidity}%`;
        descDisplay.textContent = description;
        weatherimg.src = getWeatherEmoji(id);



        cityDisplay.classList.add("cityDisplay");
        tempDisplay.classList.add("tempDisplay");
        humidityDisplay.classList.add("humidityDisplay");
        descDisplay.classList.add("descDisplay");
        weatherimg.classList.add("weatherGif");


        card.appendChild(cityDisplay);
        card.appendChild(tempDisplay);
        card.appendChild(humidityDisplay);
        card.appendChild(descDisplay);
        card.appendChild(weatherimg);

}

function getWeatherEmoji(weatherId){

        switch(true){
           case (weatherId >=200 && weatherId < 300):
              return"weather img/thunderstorms-extreme-rain.svg";
          case (weatherId >=300 && weatherId < 400):
              return"weather img/drizzle.svg";
          case (weatherId >=500 && weatherId < 600):
              return"weather img/overcast-rain.svg";
          case (weatherId >=600 && weatherId < 700):
              return"weather img/overcast-hail.svg";
          case (weatherId >=700 && weatherId < 800):
              return"weather/smoke.svg";
          case (weatherId ===800):
               return "weather img/clear-day.svg"        
          case (weatherId >=801 && weatherId < 810):
                return"weather img/smoke.svg";
          default:
              return "weather/haze.svg";
        }

}

function displayError(message){

    const errorDisplay = document.createElement("p");
    errorDisplay.textContent = message;
    errorDisplay.classList.add("errorDisplay");

    card.textContent = ""
    card.style.display = "flex";
    card.appendChild(errorDisplay);

}