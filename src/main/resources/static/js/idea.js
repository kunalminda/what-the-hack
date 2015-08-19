 $(document).ready(function(){
	      
	   $.urlParam = function(name, url) {
		    if (!url) {
		     url = window.location.href;
		    }
		    var results = new RegExp('[\\?&]' + name + '=([^&#]*)').exec(url);
		    if (!results) { 
		        return undefined;
		    }
		    return results[1] || undefined;
		}
	   
	   var idea = $.urlParam('idea');
	   console.log("idea no : "+idea);
	   
	   $.ajax({
	          url: "/idea/"+idea, 
	          async:false,
	          cache:false,
	          success: function(result){
	                 console.log(result);
	                 $(".objective").text(result.objective);
	                 $(".sectionIdea").text(result.section);
	                 $(".description").html(result.description);
	                 var votes = result.ideaUpVote - result.ideaDownVote;
	                 $(".score").text(votes);
	                 $("#ideaStatus").text("Status :"+result.ideaStatus);
	          }
	    });
      
       $("#voteup,#votedown").on("click",function(e){
    	     e.preventDefault();
    	  	 $(".form-group.voting-group").removeClass("hide");
    	  	 $("#upordown").val(e.currentTarget.id);
       });
	   
       $("#btnIdeaCancel").on("click",function(e){
    	   e.preventDefault();
    	   $(".form-group.voting-group").addClass("hide");
       });
       
       
	   $("#btnIdeaSubmit").on("click",function(e){
		   var email = $("#inputEmail").val();
		   if(validateEmail(email))
		  {
			   $("#inputEmail").removeClass("error").addClass("correct");
			   var upordown = $("#upordown").val();
			   if(upordown == "voteup"){
				   var upURL = "/ideastatus/"+idea+"/upvote"+"/email/"+email;
					$.ajax({
				   		url:upURL,
				   		success:function(response){	
				   			console.log(response);
				   			if(response){
					   			$(".form-group.voting-group").addClass("hide");
					   			var curVotes = parseInt($(".score").text());
					   			$(".score").text(++curVotes);
					   			$(".voting-label").text("you vote has been recorded.")
				   			}
				   			else{
				   				$(".voting-label").text("you have already voted.")
				   			}
				   		}
				   	});
			   }
			   
			   else if(upordown == "votedown"){
				   var downURL =  "/ideastatus/"+idea+"/downvote"+"/email/"+email;
				    console.log("downurl :"+downURL);
				    $.ajax({
		    			url:downURL,
				   		success:function(response){
				   			if(response){
					   			$(".form-group.voting-group").addClass("hide");
					   			var curVotes = parseInt($(".score").text());
					   			$(".score").text(--curVotes);
					   			$(".voting-label").text("you vote has been recorded.")
				   			}
				   			else{
				   				$(".voting-label").text("you have already voted.")
				   			}
				   		}
				   	});
			   }
	     }
		 else{
			 	$("#inputEmail").addClass("error");
		  }
	   });
	   
	   
	   function validateEmail(email){
		    return email.match(/^\"?[\w-_\.]*\"?@snapdeal\.com$/);        
	   }
	   
   });
