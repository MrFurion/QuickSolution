let map;
let startMarker;
let endMarker;
let directionsService;
let directionsRenderer;

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 53.9022, lng: 27.5619},
        zoom: 8
    });

    directionsService = new google.maps.DirectionsService();
    directionsRenderer = new google.maps.DirectionsRenderer();
    directionsRenderer.setMap(map);

    map.addListener('click', function (event) {
        if (!startMarker) {
            startMarker = new google.maps.Marker({
                position: event.latLng,
                map: map,
                label: 'A',
                draggable: true
            });

            startMarker.addListener('dragend', function (event) {
                calculateAndDisplayRoute();
                sendCoordinatesToServer();
            });
        } else if (!endMarker) {
            endMarker = new google.maps.Marker({
                position: event.latLng,
                map: map,
                label: 'B',
                draggable: true
            });

            endMarker.addListener('dragend', function (event) {
                calculateAndDisplayRoute();
                sendCoordinatesToServer();
            });
        } else {
            startMarker.setMap(null);
            endMarker.setMap(null);
            directionsRenderer.setDirections({routes: []});

            startMarker = new google.maps.Marker({
                position: event.latLng,
                map: map,
                label: 'A',
                draggable: true
            });

            startMarker.addListener('dragend', function (event) {
                calculateAndDisplayRoute();
                sendCoordinatesToServer();
            });

            endMarker = null;
        }

        if (startMarker && endMarker) {
            calculateAndDisplayRoute();
            sendCoordinatesToServer();
        }
    });
}

function calculateAndDisplayRoute() {
    if (startMarker && endMarker) {
        const start = startMarker.getPosition();
        const end = endMarker.getPosition();

        document.getElementById("startLat").value = start.lat();
        document.getElementById("startLng").value = start.lng();
        document.getElementById("endLat").value = end.lat();
        document.getElementById("endLng").value = end.lng();

        directionsService.route(
            {
                origin: start,
                destination: end,
                travelMode: 'DRIVING'
            },
            function (response, status) {
                if (status === 'OK') {
                    directionsRenderer.setDirections(response);
                } else {
                    window.alert('Ошибка запроса маршрута: ' + status);
                }
            }
        );
    }
}

function sendCoordinatesToServer() {

    if (startMarker && endMarker) {
        const startCoordinates = startMarker.getPosition();
        const endCoordinates = endMarker.getPosition();
        const url = '/saveCoordinates';
        const data = {
            "startApartment": {
                "lat": startCoordinates.lat(),
                "lng": startCoordinates.lng()
            },
            "finishApartment": {
                "lat": endCoordinates.lat(),
                "lng": endCoordinates.lng()
            }
        };

        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Ошибка отправки данных на сервер');
                }
                return response.json();
            })
            .then(data => {
                console.log('Данные успешно отправлены на сервер:', data);
            })
            .catch(error => {
                console.error('Произошла ошибка:', error.message);
            });
    }
}

window.onload = function () {
    setDefaultValues();
};

function setDefaultValues() {
    document.getElementById("startEntrance").value = "not available";
    document.getElementById("startFlat").value = "not available";
    document.getElementById("finishEntranceNumber").value = "not available";
    document.getElementById("finishFlatNumber").value = "not available";
}

function toggleMoreData() {
    var moreData = document.getElementById("moreData");
    if (moreData.style.display === "none") {
        moreData.style.display = "block";
        // Проверяем, были ли введены пользователем значения, и если нет, то устанавливаем дефолтные
        var startEntrance = document.getElementById("startEntrance").value;
        var startFlat = document.getElementById("startFlat").value;
        if (startEntrance === "") {
            document.getElementById("startEntrance").value = "not available";
        }
        if (startFlat === "") {
            document.getElementById("startFlat").value = "not available";
        }
    } else {
        moreData.style.display = "none";
    }
}

function toggleFinishMoreData() {
    var moreDataElement = document.getElementById("moreDataFinish");
    if (moreDataElement.style.display === "none") {
        var finishEntrance = document.getElementById("finishEntranceNumber").value;
        var finishFlat = document.getElementById("finishFlatNumber").value;
        if (finishEntrance === "") {
            document.getElementById("finishEntranceNumber").value = "not available";
        }
        if (finishFlat === "") {
            document.getElementById("finishFlatNumber").value = "not available";
        }
    } else {
        moreDataElement.style.display = "none";
    }
}