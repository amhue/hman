async function getRooms() {
    const url = "/api/rooms"
    try {
        const res = await fetch(url);
        const json = await res.json();
        console.log(json);
    } catch (e) {
        console.error(e.message);
    }
}

getRooms().then()