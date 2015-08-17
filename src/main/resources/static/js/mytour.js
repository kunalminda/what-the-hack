// Instance the tour
var tour = new Tour({
  steps: [
  {
    element: "#HacksIntro",
    title: "Hack",
    content: "This shows you the list of hacks"
  },
  {
    element: "#ProblemStatement",
    title: "ProblemStatment",
    content: "List of Problem statments"
  },
 
  {
		element: "#services",
		title: "Hacks",
		content: "Current hacks that are going on",
		placement:"left"
  },
  {
	  element: "#ideaPage",
	  title: "Ideas",
		content: "Submit your idea and also view all current ideas",
		placement:"left"
  },
  {
	  element: "#login",
	  title: "Idea Details",
		content: "View the ideas and upvote the ideas that you like.",
		placement:"left"
  },
  {
	  element: "#contact",
	  title: "Contact us",
		content: "Lets get in touch to discuss  what we can do better",
		placement:"top"
  },
  {
		element: "#hacktube",
		title: "HackTube",
		content: "Catch all the latest event videos here",
		placement:"left"
  },
  {
		element: "#2048",
		title: "2048",
		content: "What the Fun!"
  }
]});

// Initialize the tour
tour.init();

// Start the tour
tour.start();
