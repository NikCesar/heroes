let playerParty = [];
let heroes = [];

function addToParty(id) {
    console.log(id);
    console.log(heroes.find(x => x.id === id));
    playerParty.members.push(heroes.find(x => x.id === id));
    updateLists()
}

function removeFromParty(id) {
    playerParty.members.splice(heroes.find(x => x.id === id), 1);
    updateLists()
    console.log(playerParty)
}

function updateLists() {
    $('#parties').empty();
    playerParty.members.forEach(x => {
        $('#parties').append('<li><a onclick="removeFromParty(\'' + x.id + '\')">' + x.name + '</a></li>');
    });
    $('#heroes').empty();
    heroes.forEach(x => {
        if (playerParty.members.includes(x)) {
            $('#heroes').append('<li><a class="disabled">' + x.name + '</a></li>');
        } else {
            $('#heroes').append('<li><a onclick="addToParty(\'' + x.id + '\')">' + x.name + '</a></li>');
        }
    });

}


$.ajax({
    url: 'http://localhost:8080/camp/heroes',
    data: {},
    error: function () {
        alert('Service unavailable');
    },
    success: function (data) {
        heroes = data;
        data.forEach(x => {
            $('#heroes').append('<li><a onclick="addToParty(\'' + x.id + '\')">' + x.name + '</a></li>');
        });
    },
    type: 'GET'
});

$.ajax({
    url: 'http://localhost:8080/camp/parties/1',
    data: {},
    error: function () {
        alert('Service unavailable');
    },
    success: function (data) {
        playerParty = data;
        if (data.members.length > 0) {
            data.members.forEach(x => {
                $('#parties').append('<li><a onclick="removeFromParty(\'' + x.id + '\')">' + x.name + '</a></li>');
            });
        }
    },
    type: 'GET'
});