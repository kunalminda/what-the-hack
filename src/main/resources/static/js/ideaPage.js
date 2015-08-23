$(document).ready(function() {
    
     $("#inputSection").change(function() {
         var value = $(this).val();
         console.log("value : " + value);
         if (value == -1) {
             $("#inputSection").css({
                 "border": "solid 1px red"
             });
             $("#inputIdea").hide();
         } else if (value == "idea") {
             $("#inputIdea").show();
         } else if (value == "feature") {
             $("#inputIdea").hide();
         }
     });
     
     function paginateTable(){
    	  $(".easyPaginateNav").remove();
    	  
    	   var head='';
    	     head += '<thead><tr><th class=\"num\">#<\/th>';
    	     head += '                     <th>Topic\/Submitted By<\/th>';
    	     head += '                     <th>Objective<\/th> <th>upvotes<\/th><th>downvotes<\/th>  <th>Submitted On<\/th><\/tr><\/thead>';
    	     
		 $('table.table').easyPaginate({
			    paginateElement: 'tbody.link',
			    elementsPerPage: 6,
			    effect: 'fade'
		 });
		 
		 $('table.table').prepend(head);
	 }
 
 
     $.ajax({
         url: "/ideas",
         cache: false,
         async: false,
         success:updateTable
     });

     $("#feature").on("click", function() {
    	 $("ul.nav.nav-tabs li").removeClass("active");
    	 $("#feature").parent().addClass("active");

         $.ajax({
             url: "/ideas?iof=feature",
             cache: false,
             async: false,
             success:updateTable
         });
     });
     
     $("#trending").on("click",function(){
    	 $("ul.nav.nav-tabs li").removeClass("active");
    	 $("#trending").parent().addClass("active");
    	 
    	 $.ajax({
             url: "/ideas/trend",
             cache: false,
             async: false,
             success:updateTable
         });
     });

     $("#idea").on("click", function() {
    	 $("ul.nav.nav-tabs li").removeClass("active");
    	 $("#idea").parent().addClass("active");
       
         $.ajax({
             url: "/ideas?iof=idea",
             cache: false,
             async: false,
             success:updateTable
         });
     });

     
     function updateTable(result){
    	  $(".table-striped.table-bordered tbody,.table-striped.table-bordered thead").remove();
    	     
          $.each(result, function(i, val) {
              i = i + 1;
              var votes = val.ideaUpVote - val.ideaDownVote;
              var html='';
          
              html += '<tbody class="link"><tr>';
              html += '<td class="num">' + i + '</td>';
              html += '<td colspan="6"><a href="/ideaDetail?idea=' + val.ideaNumber + '">' + val.ideaOverview +
                  '</a></td></tr>';
              html += '<tr><td></td><td><a href="/ideaDetail?idea=' + val.ideaNumber + '">' + val.email +
                  '</a></td><td><a>' + val.objective + '</a></td>' +
//                  '<td><a>' + val.section + '</a></td>' +
                  '<td class="num"><a>' + val.ideaUpVote + '</a></td>' +
                  '<td class="num"><a>' + val.ideaDownVote + '</a></td>' +
//                  '<td class="num"><a>' + val.ideaStatus + '</a></td>' +
                  /*    '<td class="num"><a>0</a></td>'+ */
                  '<td><a>' + moment(val.submittedOn).format("MMMM Do YYYY") + '</a></td></tr></tbody>';
              // console.log(html);
              $('table.table').append(html);
              
     });
          paginateTable();
     }
     
 });