function countdown(endT,callback) {
        var days,hours,minutes,sec,timer;
        
        end = new Date(endT);
        
        end = end.getTime(); //Get initial Date in Milliseconds,
        if (isNaN(end)) {
           alert('@ countdown.js @  "Invalid Date", valid format- mm/dd/yyyy hh:mm:ss TT ');
           return;
        }
        
        timer = setInterval(calculate,1000);//Timer to calculate remaining time

        function calculate(){
         var current = new Date();
         var remaining = parseInt((end - current.getTime())/1000);//remaining seconds, 
          
            if (remaining <= 0){
                clearInterval(timer);
                days=0;
                hours=0;
                minutes=0;
                sec=0;
                display(days,hours,minutes,sec);
                if (typeof callback === 'function' ) {
                    callback();
                }
                
            }else{
                
                days = parseInt(remaining/86400);
                remaining = (remaining%86400);
                hours = parseInt(remaining/3600);
                remaining = (remaining%3600);
                minutes = parseInt(remaining/60);
                remaining = (remaining%60);
                sec = parseInt(remaining);
                display(days,hours,minutes,sec);
                
                
            }
        }

        //Function For displaying Results in HTML page with specific ID's 
        function display(days,hours,minutes,sec) {
            var dl = days.toString().length;
            if (dl == "1") {
                sl = 2;
            }else{
                if (isNaN(dl)) {
                    sl = 3;
                }
                sl = dl;
            }

            var days = ("00"+days).slice(-sl);
            document.getElementById("days-1").innerHTML = days.substring(0,1);
            document.getElementById("days-0").innerHTML =  days.substring(1,2);

            var hours = ("0"+hours).slice(-2);
            document.getElementById("hours-1").innerHTML = hours.substring(0,1);
            document.getElementById("hours-0").innerHTML = hours.substring(1,2);

            var minutes = ("0"+minutes).slice(-2);
            document.getElementById("minutes-1").innerHTML = minutes.substring(0,1);
            document.getElementById("minutes-0").innerHTML = minutes.substring(1,2);

            var secs = ("0"+sec).slice(-2);
            document.getElementById("seconds-1").innerHTML = secs.substring(0,1);
            document.getElementById("seconds-0").innerHTML = secs.substring(1,2);

        }
     
    }