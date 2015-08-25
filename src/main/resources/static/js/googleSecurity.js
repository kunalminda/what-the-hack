
function onSignIn(googleUser) {
    // Useful data for your client-side scripts:
    var profile = googleUser.getBasicProfile();

    console.log("ID: " + profile.getId()); 
    console.log("Name: " + profile.getName());
    console.log("Image URL: " + profile.getImageUrl());
    console.log("Email: " + profile.getEmail());

    // The ID token you need to pass to your backend:
    var id_token = googleUser.getAuthResponse().id_token;

    if (sessionStorage.getItem("userObj") == null) {
        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/tokensignin');
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onload = function() {
            console.log('Signed in as: ' + xhr.responseText);
        };
        xhr.send('idtoken=' + id_token);
        sessionStorage.setItem("userObj",profile.getEmail().toString());
        console.log('user object updated');
    }
};

function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    sessionStorage.removeItem("userObj");
    
    auth2.signOut().then(function() {
        console.log('User signed out.');
        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/tokensignout');
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onload = function() {
            console.log('Signed out as: ' + xhr.responseText);
        };
        xhr.send();
    });
}