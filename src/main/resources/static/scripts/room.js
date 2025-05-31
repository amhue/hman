const roomsContainer = document.getElementById("rooms");

async function getRooms() {
    const url = "/api/rooms"
    try {
        const res = await fetch(url);
        const json = await res.json();
        console.log(json);
        json.forEach(room => {
            const roomDiv = roomsContainer
                .appendChild(document.createElement("div"));
            roomDiv
                .innerHTML = `Room number: ${room.room_number}<br />
                    Room type: ${room.roomType}<br />
                    ${room.occupied ? "Not available" : "Available"}`;
            roomDiv.className = "room"
        })
    } catch (e) {
        console.error(e.message);
    }
}

getRooms().then()