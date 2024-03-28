let map;
let startMarker;
let endMarker;
let directionsService;
let directionsRenderer;

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: { lat: 53.9022, lng: 27.5619 }, // Координаты Минска или другого центрального города Беларуси
        zoom: 8 // Масштаб карты
    });

    directionsService = new google.maps.DirectionsService();
    directionsRenderer = new google.maps.DirectionsRenderer();
    directionsRenderer.setMap(map);

    // Обработчик клика по карте для добавления маркера
    map.addListener('click', function(event) {
        if (!startMarker) {
            startMarker = new google.maps.Marker({
                position: event.latLng,
                map: map,
                label: 'A', // Метка начальной точки
                draggable: true // Разрешить перетаскивание маркера
            });

            startMarker.addListener('dragend', function(event) {
                calculateAndDisplayRoute();
                sendCoordinatesToServer();
            });
        } else if (!endMarker) {
            endMarker = new google.maps.Marker({
                position: event.latLng,
                map: map,
                label: 'B', // Метка конечной точки
                draggable: true // Разрешить перетаскивание маркера
            });

            endMarker.addListener('dragend', function(event) {
                calculateAndDisplayRoute();
                sendCoordinatesToServer();
            });
        } else {
            // Удаляем существующие маркеры и маршрут при клике на новом месте
            startMarker.setMap(null);
            endMarker.setMap(null);
            directionsRenderer.setDirections({ routes: [] });

            // Создаем новые маркеры на новом месте
            startMarker = new google.maps.Marker({
                position: event.latLng,
                map: map,
                label: 'A',
                draggable: true
            });

            startMarker.addListener('dragend', function(event) {
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

        // Заполняем скрытые поля координатами
        document.getElementById("startLat").value = startMarker.getPosition().lat();
        document.getElementById("startLng").value = startMarker.getPosition().lng();
        document.getElementById("endLat").value = endMarker.getPosition().lat();
        document.getElementById("endLng").value = endMarker.getPosition().lng();

        directionsService.route(
            {
                origin: start,
                destination: end,
                travelMode: 'DRIVING'
            },
            function(response, status) {
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
    // Здесь вы можете отправить координаты начальной и конечной точек на ваш сервер для обработки
    if (startMarker && endMarker) {
        const startCoordinates = startMarker.getPosition();
        const endCoordinates = endMarker.getPosition();

        // Пример отправки данных на сервер с использованием AJAX запроса
        // Замените URL на URL вашего сервера и обработчика данных
        const url = '/saveCoordinates';
        const data = {
            startLat: startCoordinates.lat(),
            startLng: startCoordinates.lng(),
            endLat: endCoordinates.lat(),
            endLng: endCoordinates.lng()
        };

        // Отправляем данные на сервер
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
                // Обработка ответа сервера (если необходимо)
                console.log('Данные успешно отправлены на сервер:', data);
            })
            .catch(error => {
                console.error('Произошла ошибка:', error.message);
            });
    }
}
