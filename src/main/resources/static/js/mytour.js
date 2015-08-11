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
  }

]});

// Initialize the tour
tour.init();

// Start the tour
tour.start();
