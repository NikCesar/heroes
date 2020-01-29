let party = [];
let heroes = [];
let mounts = [];
let armors = [];
let weapons = [];

$(document).ready(() => {
    loadHeroes();
    loadParties();
    loadMounts();
    loadArmors();
    loadWeapons();
});

const addToParty = (id) => {
    party.members.push(heroes.find(x => x.id === id));
    updateLists();
};

const removeFromParty = (id) => {
    party.members.splice(heroes.find(x => x.id === id), 1);
    updateLists();
};

const updateLists = () => {
    $('#parties').empty();
    party.members.forEach(hero => {
        $('#parties').append('<li><b>' + hero.name + '</b> <span class="hero_class">[' + hero.heroType + '] </span><i>HP: ' + hero.hp +
            ', DEF: ' + hero.def + ', ATK: ' + hero.atk + ', CRIT: ' + hero.critChance + ', DODGE: ' + hero.dodgeChance + ', INI: ' + hero.initiative + '</i><a class="hero_toggle  hero_toggle_remove" onclick="removeFromParty(\'' + hero.id + '\')">-</a></li>');
    });
    $('#heroes').empty();
    heroes.forEach(hero => {
        if (party.members.includes(hero)) {
            $('#heroes').append('<li class="gray"><b>' + hero.name + '</b> <span class="hero_class">[' + hero.heroType + '] </span><i>HP: ' + hero.hp +
                ', DEF: ' + hero.def + ', ATK: ' + hero.atk + ', CRIT: ' + hero.critChance + ', DODGE: ' + hero.dodgeChance + ', INI: ' + hero.initiative + '</i></li>');
        } else {
            $('#heroes').append('<li><b>' + hero.name + '</b> <span class="hero_class">[' + hero.heroType + '] </span><i>HP: ' + hero.hp +
                ', DEF: ' + hero.def + ', ATK: ' + hero.atk + ', CRIT: ' + hero.critChance + ', DODGE: ' + hero.dodgeChance + ', INI: ' + hero.initiative + '</i><a class="hero_toggle  hero_toggle_add" onclick="addToParty(\'' + hero.id + '\')">+</a></li>');
        }
    });
};

const loadHeroes = () => {
    $.ajax({
        url: 'http://localhost:8080/camp/heroes',
        data: {},
        error: () => alert('Service unavailable'),
        success: function(data) {
            heroes = data;
            if (data.length > 0) {
                $('#heroes').empty();
                data.forEach(hero => {
                    $('#heroes').append('<li><b>' + hero.name + '</b> <span class="hero_class">[' + hero.heroType + '] </span><i>HP: ' + hero.hp +
                        ', DEF: ' + hero.def + ', ATK: ' + hero.atk + ', CRIT: ' + hero.critChance + ', DODGE: ' + hero.dodgeChance + ', INI: ' + hero.initiative + '</i><a class="hero_toggle hero_toggle_add" onclick="addToParty(\'' + hero.id + '\')">+</a></li>');
                });
            }
        },
        type: 'GET'
    });
};

const loadParties = () => {
    $.ajax({
        url: 'http://localhost:8080/camp/parties/1',
        data: {},
        error: () => alert('Service unavailable'),
        success: function(data) {
            party = data;
            if (data.members.length > 0) {
                $('#parties').empty();
                data.members.forEach(hero => {
                    $('#parties').append('<li><b>' + hero.name + '</b> <span class="hero_class">[' + hero.heroType + '] </span><i>HP: ' + hero.hp +
                        ', DEF: ' + hero.def + ', ATK: ' + hero.atk + ', CRIT: ' + hero.critChance + ', DODGE: ' + hero.dodgeChance + ', INI: ' + hero.initiative + '</i><a class="hero_toggle  hero_toggle_remove" onclick="removeFromParty(\'' + hero.id + '\')">-</a></li>');
                });
            }
            return data;
        },
        type: 'GET'
    });
};

const loadMounts = () => {
    $.ajax({
        url: 'http://localhost:8080/equipment/mounts',
        data: {},
        error: () => alert('Service unavailable'),
        success: (data) => mounts = data,
        type: 'GET'
    });
};

const loadArmors = () => {
    $.ajax({
        url: 'http://localhost:8080/equipment/armors',
        data: {},
        error: () => alert('Service unavailable'),
        success: (data) => armors = data,
        type: 'GET'
    });
};

const loadWeapons = () => {
    $.ajax({
        url: 'http://localhost:8080/equipment/weapons',
        data: {},
        error: () => alert('Service unavailable'),
        success: (data) => weapons = data,
        type: 'GET'
    });
};

const saveParty = () => {
    $.ajax({
        url: 'http://localhost:8080/camp/parties/1',
        data: JSON.stringify(party),
        error: () => alert('Service unavailable'),
        success: () => alert('party saved!'),
        type: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
    });
};
