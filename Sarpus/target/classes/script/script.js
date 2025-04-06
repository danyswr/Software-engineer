document.addEventListener("DOMContentLoaded", () => {
    // Toggle mobile menu
    const menuToggle = document.querySelector(".menu-toggle")
    const sidebar = document.querySelector(".sidebar")
  
    if (menuToggle) {
      menuToggle.addEventListener("click", () => {
        sidebar.classList.toggle("active")
      })
    }
  
    // Form validation
    const forms = document.querySelectorAll("form")
  
    forms.forEach((form) => {
      form.addEventListener("submit", (event) => {
        const requiredFields = form.querySelectorAll("[required]")
        let isValid = true
  
        requiredFields.forEach((field) => {
          if (!field.value.trim()) {
            isValid = false
            field.classList.add("error")
          } else {
            field.classList.remove("error")
          }
        })
  
        if (!isValid) {
          event.preventDefault()
          alert("Please fill in all required fields.")
        }
      })
    })
  
    // Password confirmation check
    const registerForm = document.querySelector('form[action="/register"]')
  
    if (registerForm) {
      const passwordField = registerForm.querySelector("#password")
      const confirmPasswordField = registerForm.querySelector("#confirmPassword")
  
      registerForm.addEventListener("submit", (event) => {
        if (passwordField.value !== confirmPasswordField.value) {
          event.preventDefault()
          alert("Passwords do not match.")
          confirmPasswordField.classList.add("error")
        }
      })
    }
  })
  
  