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
	        	  	 if(result.collabarators.length >= 6)
	        	  	 {
	        	  		 $("#btnJoinIdea").css({"background-color": "gray","color":"darkgray","pointer-events":"none"});
	        	  	 }
	        	  	 
	                 console.log(result);
	                 $(".objective").text(result.objective);
	                 $(".sectionIdea").text(result.section);
	                 $(".description").html(result.description);
	                 
	                 var urls = result.url.split(",");
	                 console.log(urls);
	                 var htmlVal = "";
	                 $.each(urls,function(i,val){
	                	 htmlVal += "<a>"+val+"</a>";
	                 });
                	 $(".url").html(htmlVal);
	                 
	                 $(".collabarators").html(result.collabarators.toString());
	                 var votes = result.ideaUpVote - result.ideaDownVote;
	                 $(".score").text(votes);
	                 $("#ideaStatus").text("Status :"+result.ideaStatus);
	                 $(".idea-title").text(result.ideaOverview);
	                 $(".idea-email").text(result.email);
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
       
       $("#btnJoinCancel").on("click",function(e){
    	   e.preventDefault();
    	   $(".form-group.join-group").addClass("hide");
       });
       
       $("#btnJoinIdea").on("click",function(e){
    	   e.preventDefault();
    	   $(".join-group").removeClass("hide");
       });
       
       $("#btnJoinSubmit").on("click",function(e){
    	   e.preventDefault();
    	   $(".join-group input,.join-group label").text("").val("");
    	   var email = $("#inputJoinEmail").val();
    	   $.ajax({
 	          url: "/idea/"+idea+"/email/"+email+"/", 
 	          async:false,
 	          cache:false,
 	          success: function(result){
 	        	  if(result)
 	        		  $(".join-label").text("your collab has been recorded.");
 	        	  else
 	        		 $(".join-label").text("buddy,you are already on!");
 	          }
    	   });
    	   
       });
       
       $(".glyphicon-pencil.white").on("click",function(e){
    	   e.preventDefault();
    	   
       });
       
	   $("#btnIdeaSubmit").on("click",function(e){
		   var email = $("#inputEmail").val();
		   if(validateEmail(email))
		  {
			   $("#inputEmail").removeClass("error").addClass("correct");
			   var upordown = $("#upordown").val();
			   if(upordown == "voteup"){
				   var upURL = "/ideastatus/"+idea+"/upvote"+"/email/"+email+"/";
					$.ajax({
				   		url:upURL,
				   		success:function(data){	
				   			if(data.Status){
					   			$(".form-group.voting-group").addClass("hide");
					   			var curVotes = parseInt($(".score").text());
					   			$(".score").text(++curVotes);
					   			$(".voting-label").text("Your vote has been recorded.")
				   			}
				   			else{
				   				$(".voting-label").text("Oops buddy ! You have already voted.")
				   			}
				   		}
				   	});
			   }
			   
			   else if(upordown == "votedown"){
				   var downURL =  "/ideastatus/"+idea+"/downvote"+"/email/"+email+"/";
				    console.log("downurl :"+downURL);
				    $.ajax({
		    			url:downURL,
				   		success:function(data){
				   			if(data.Status){
					   			$(".form-group.voting-group").addClass("hide");
					   			var curVotes = parseInt($(".score").text());
					   			$(".score").text(--curVotes);
					   			$(".voting-label").text("Your vote has been recorded.")
				   			}
				   			else{
				   				$(".voting-label").text("Oops buddy ! You have already voted.")
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
