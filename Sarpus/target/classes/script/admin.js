document.addEventListener("DOMContentLoaded", () => {
    // Filter feedback
    const filterButtons = document.querySelectorAll(".filter-btn")
    const feedbackCards = document.querySelectorAll(".feedback-card")
  
    filterButtons.forEach((button) => {
      button.addEventListener("click", function () {
        // Remove active class from all buttons
        filterButtons.forEach((btn) => btn.classList.remove("active"))
  
        // Add active class to clicked button
        this.classList.add("active")
  
        const filter = this.getAttribute("data-filter")
  
        // Show/hide feedback cards based on filter
        feedbackCards.forEach((card) => {
          if (filter === "all") {
            card.style.display = "block"
          } else {
            if (card.getAttribute("data-status") === filter) {
              card.style.display = "block"
            } else {
              card.style.display = "none"
            }
          }
        })
      })
    })
  })
  
  