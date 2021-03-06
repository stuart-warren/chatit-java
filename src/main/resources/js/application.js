$(function () {
    "use strict";

    var definition = {smile:{title:"Smile",codes:[":)",":=)",":-)"]},"sad-smile":{title:"Sad Smile",codes:[":(",":=(",":-("]},"big-smile":{title:"Big Smile",codes:[":D",":=D",":-D",":d",":=d",":-d"]},cool:{title:"Cool",codes:["8)","8=)","8-)","B)","B=)","B-)","(cool)"]},wink:{title:"Wink",codes:[":o",":=o",":-o",":O",":=O",":-O",";)",";-)"]},crying:{title:"Crying",codes:[";(",";-(",";=("]},sweating:{title:"Sweating",codes:["(sweat)","(:|"]},speechless:{title:"Speechless",codes:[":|",":=|",":-|"]},kiss:{title:"Kiss",codes:[":*",":=*",":-*"]},"tongue-out":{title:"Tongue Out",codes:[":P",":=P",":-P",":p",":=p",":-p"]},blush:{title:"Blush",codes:["(blush)",":$",":-$",":=$",':">']},wondering:{title:"Wondering",codes:[":^)"]},sleepy:{title:"Sleepy",codes:["|-)","I-)","I=)","(snooze)"]},dull:{title:"Dull",codes:["|(","|-(","|=("]},"in-love":{title:"In love",codes:["(inlove)"]},"evil-grin":{title:"Evil grin",codes:["]:)",">:)","(grin)"]},talking:{title:"Talking",codes:["(talk)"]},yawn:{title:"Yawn",codes:["(yawn)","|-()"]},puke:{title:"Puke",codes:["(puke)",":&",":-&",":=&"]},"doh!":{title:"Doh!",codes:["(doh)"]},angry:{title:"Angry",codes:[":@",":-@",":=@","x(","x-(","x=(","X(","X-(","X=("]},"it-wasnt-me":{title:"It wasn't me",codes:["(wasntme)"]},party:{title:"Party!!!",codes:["(party)"]},worried:{title:"Worried",codes:[":S",":-S",":=S",":s",":-s",":=s"]},mmm:{title:"Mmm...",codes:["(mm)"]},nerd:{title:"Nerd",codes:["8-|","B-|","8|","B|","8=|","B=|","(nerd)"]},"lips-sealed":{title:"Lips Sealed",codes:[":x",":-x",":X",":-X",":#",":-#",":=x",":=X",":=#"]},hi:{title:"Hi",codes:["(hi)"]},call:{title:"Call",codes:["(call)"]},devil:{title:"Devil",codes:["(devil)"]},angel:{title:"Angel",codes:["(angel)"]},envy:{title:"Envy",codes:["(envy)"]},wait:{title:"Wait",codes:["(wait)"]},bear:{title:"Bear",codes:["(bear)","(hug)"]},"make-up":{title:"Make-up",codes:["(makeup)","(kate)"]},"covered-laugh":{title:"Covered Laugh",codes:["(giggle)","(chuckle)"]},"clapping-hands":{title:"Clapping Hands",codes:["(clap)"]},thinking:{title:"Thinking",codes:["(think)",":?",":-?",":=?"]},bow:{title:"Bow",codes:["(bow)"]},rofl:{title:"Rolling on the floor laughing",codes:["(rofl)"]},whew:{title:"Whew",codes:["(whew)"]},happy:{title:"Happy",codes:["(happy)"]},smirking:{title:"Smirking",codes:["(smirk)"]},nodding:{title:"Nodding",codes:["(nod)"]},shaking:{title:"Shaking",codes:["(shake)"]},punch:{title:"Punch",codes:["(punch)"]},emo:{title:"Emo",codes:["(emo)"]},yes:{title:"Yes",codes:["(y)","(Y)","(ok)"]},no:{title:"No",codes:["(n)","(N)"]},handshake:{title:"Shaking Hands",codes:["(handshake)"]},skype:{title:"Skype",codes:["(skype)","(ss)"]},heart:{title:"Heart",codes:["(h)","<3","(H)","(l)","(L)"]},"broken-heart":{title:"Broken heart",codes:["(u)","(U)"]},mail:{title:"Mail",codes:["(e)","(m)"]},flower:{title:"Flower",codes:["(f)","(F)"]},rain:{title:"Rain",codes:["(rain)","(london)","(st)"]},sun:{title:"Sun",codes:["(sun)"]},time:{title:"Time",codes:["(o)","(O)","(time)"]},music:{title:"Music",codes:["(music)"]},movie:{title:"Movie",codes:["(~)","(film)","(movie)"]},phone:{title:"Phone",codes:["(mp)","(ph)"]},coffee:{title:"Coffee",codes:["(coffee)"]},pizza:{title:"Pizza",codes:["(pizza)","(pi)"]},cash:{title:"Cash",codes:["(cash)","(mo)","($)"]},muscle:{title:"Muscle",codes:["(muscle)","(flex)"]},cake:{title:"Cake",codes:["(^)","(cake)"]},beer:{title:"Beer",codes:["(beer)"]},drink:{title:"Drink",codes:["(d)","(D)"]},dance:{title:"Dance",codes:["(dance)","\\o/","\\:D/","\\:d/"]},ninja:{title:"Ninja",codes:["(ninja)"]},star:{title:"Star",codes:["(*)"]},mooning:{title:"Mooning",codes:["(mooning)"]},finger:{title:"Finger",codes:["(finger)"]},bandit:{title:"Bandit",codes:["(bandit)"]},drunk:{title:"Drunk",codes:["(drunk)"]},smoking:{title:"Smoking",codes:["(smoking)","(smoke)","(ci)"]},toivo:{title:"Toivo",codes:["(toivo)"]},rock:{title:"Rock",codes:["(rock)"]},headbang:{title:"Headbang",codes:["(headbang)","(banghead)"]},bug:{title:"Bug",codes:["(bug)"]},fubar:{title:"Fubar",codes:["(fubar)"]},poolparty:{title:"Poolparty",codes:["(poolparty)"]},swearing:{title:"Swearing",codes:["(swear)"]},tmi:{title:"TMI",codes:["(tmi)"]},heidy:{title:"Heidy",codes:["(heidy)"]},myspace:{title:"MySpace",codes:["(MySpace)"]},malthe:{title:"Malthe",codes:["(malthe)"]},tauri:{title:"Tauri",codes:["(tauri)"]},priidu:{title:"Priidu",codes:["(priidu)"]}};

    var header = $('.header');
    var rooms = $('.rooms');
    var content = $('.content');
    var users = $('.users');
    var input = $('.input');
    var status = $('.status');
    var myName = false;
    var author = null;
    var logged = false;
    var socket = $.atmosphere;
    var subSocket;
    var transport = 'websocket';
    var fallbackTransport = 'long-polling'
    var connected = false;
    var uuid = 0;

    $.emoticons.define(definition);

    header.html($('<div>', { text: 'Atmosphere Chat. Default transport is ' + transport + ', fallback is ' + fallbackTransport }));
    status.text('Choose chatroom:');
    input.removeAttr('disabled').focus();

    input.keydown(function (e) {
        if (e.keyCode === 13) {
            var msg = $(this).val();

            $(this).val('');
            if (!connected) {
                connect(msg);
                connected = true;
                return;
            }

            // First message received is always the author's name
            if (author == null) {
                author = msg;
            }

            input.removeAttr('disabled').focus();
//            // Private message
//            if (msg.indexOf(":") !== -1) {
//                var a = msg.split(":")[0];
//                subSocket.push(JSON.stringify({ user: a, message: msg}));
//            } else {
                subSocket.push(JSON.stringify({ author: author, message: msg, uuid: uuid }));
//            }

            if (myName === false) {
                myName = msg;
            }
        }
    });

    function connect(chatroom) {
        // We are now ready to cut the request
        var request = { url: document.location.toString() + 'chat/' + chatroom,
            contentType: "application/json",
            logLevel: 'debug',
            transport: transport,
            trackMessageLength: true,
            reconnectInterval: 5000,
            fallbackTransport: fallbackTransport};

        request.onOpen = function (response) {
            content.html($('<p>', { text: 'Atmosphere connected using ' + response.transport }));
            status.text('Choose name:');
            input.removeAttr('disabled').focus();
            transport = response.transport;
            uuid = response.request.uuid;
        };

        request.onReopen = function (response) {
            content.html($('<p>', { text: 'Atmosphere re-connected using ' + response.transport }));
            input.removeAttr('disabled').focus();
        };

        request.onMessage = function (response) {

            var message = response.responseBody;
            try {
                var json = JSON.parse(message);
            } catch (e) {
                console.log('This doesn\'t look like a valid JSON: ', message);
                return;
            }

            input.removeAttr('disabled').focus();
            if (json.rooms) {
                rooms.html($('<div>', { text: 'Current room: ' + chatroom}));

                var r = 'Available rooms: ';
                for (var i = 0; i < json.rooms.length; i++) {
                    r += json.rooms[i] + "  ";
                }
                rooms.append($('<div>', { text: r }))
            }

            if (json.users) {
                var r = 'Connected users: ';
                for (var i = 0; i < json.users.length; i++) {
                    r += json.users[i] + "  ";
                }
                users.html($('<div>', { text: r }))
            }

            if (json.author) {
                if (!logged && myName) {
                    logged = true;
                    status.text(myName + ': ').css('color', 'blue');
                } else {
                    var me = json.author == author;
                    var date = typeof(json.time) == 'string' ? parseInt(json.time) : json.time;
                    var msg = $.emoticons.replace(json.message);
                    addMessage(json.author, msg, me ? 'blue' : 'black', new Date(date));
                }
            }
        };

        request.onClose = function (response) {
            subSocket.push(JSON.stringify({ author: author, message: 'disconnecting' }));
        };

        request.onError = function (response) {
            content.html($('<p>', { text: 'Sorry, but there\'s some problem with your '
                + 'socket or the server is down' }));
            logged = false;
        };

        request.onReconnect = function (request, response) {
            content.html($('<p>', { text: 'Connection lost, trying to reconnect. Trying to reconnect ' + request.reconnectInterval}));
            input.attr('disabled', 'disabled');
        };

        subSocket = socket.subscribe(request);
    }

    function addMessage(author, message, color, datetime) {
        content.append('<p><span class="author" style="color:' + color + '">' + author +
            '</span> @ ' + '<time is="relative-time" datetime="' + datetime.toISOString() +
            '">' + datetime + '</time>:<span class="message"> ' + message + '</span></p>');
    }
});
