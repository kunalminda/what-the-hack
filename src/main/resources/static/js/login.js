



function login(){
  var formElement = document.getElementById("login");
  var formData = new FormData(formElement);
  $.ajax({
    url: '/user/signup',
    data:formData,
    error: function() {
      console.log("error has occured");
    },
    dataType: 'jsonp',
    success: function(data) {
    console.log(data);
    },
    type: 'POST'
  });
}
