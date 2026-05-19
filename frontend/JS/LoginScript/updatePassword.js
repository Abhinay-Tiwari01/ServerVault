/* updatePassword.js — original logic preserved, eye toggle added */
// const API_BASE = 'http://localhost:8080';
const API_BASE = IS_PRODUCTION 
  ? 'https://servervault-gf7i.onrender.com' 
  : 'http://localhost:8080';

/* ── Eye toggle ── */
(function () {
  const toggle = document.getElementById("toggle-fp-password");
  const input = document.getElementById("password");
  if (toggle && input) {
    toggle.addEventListener("click", () => {
      const isPass = input.type === "password";
      input.type = isPass ? "text" : "password";
      toggle.textContent = isPass ? "🙈" : "👁";
    });
  }
})();


/* ── Fetch user by mobile — fires on every keystroke ── */
function fetchUserByMobile(mobile) {
  if (mobile.length < 10) {
    document.getElementById("username").value = "";
    document.getElementById("userId").value = "";
    return;
  }
  fetch(`${API_BASE}/api/login/findUserbyMobile/${mobile}`, {
    method: "GET",
    headers: { "Content-Type": "application/json" }
  })
    .then(response => {
      if (response.ok) {
        return response.json();
      }
      else {
        throw new Error("User not found");
      }
    })
    .then(user => {
      console.log("User from API:", user);  
      // Auto-fill username and hidden userId
      document.getElementById("username").value = user.userName;  // ← fills username field
      document.getElementById("userId").value = user.userId;    // ← fills hidden field
      showAlert("errorBox", "User found! Enter new password.", "success");
    })
    .catch(err => {
      document.getElementById("username").value = "";
      document.getElementById("userId").value = "";
      showAlert("errorBox", "No user found with this mobile.", "danger");
    });
}

/* ── Original updatePassword — all tags & ids unchanged ── */
function updatePassword() {
  const mobile = document.getElementById("usersMobile").value.trim();
  const newPassword = document.getElementById("password").value.trim();
  const userId = document.getElementById("userId").value.trim();
  const userName = document.getElementById("username").value.trim();

  console.log("userId:", userId);
  console.log("userName:", userName);
  console.log("newPassword:", newPassword);
  if (!mobile) {
    showAlert("errorBox", "Mobile Number is required!", "danger");
    return;
  }
  if (!newPassword) {
    showAlert("errorBox", "Enter new password!", "danger");
    return;
  } if (!userName) {
    showAlert("errorBox", "No user found for this mobile!", "danger");
    return;
  }
  if (!userId) {
    showAlert("errorBox", "User ID not found!", "danger");
    return;
  }
  const btn = document.getElementById("updateBtn");
  const spinner = document.getElementById("updateSpinner");
  const label = btn.querySelector(".btn-label");

  if (btn && spinner && label) {
    spinner.classList.remove("d-none");
    label.textContent = "Updating…";
    btn.disabled = true;
  }

  fetch(`${API_BASE}/api/login/forgetPassword/${userId}`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: newPassword,
  })
    .then(response => {
      if (response.ok) {
        showAlert("errorBox", "Password updated successfully!", "success");
        document.getElementById("updateForm").reset();
        setTimeout(() => {
          window.location.href = "/FORMS/LoginPage/LoginAndCreatePage.html";
        }, 1500);
      } else {
        return response.text().then(msg => {
          throw new Error(msg || "Update failed");
        });
      }
    })
    .catch(err => {
      showAlert("errorBox", "Error: " + err.message, "danger");
    })
    .finally(() => {
      if (btn && spinner && label) {
        spinner.classList.add("d-none");
        label.textContent = "Update Password";
        btn.disabled = false;
      }
    });
}

/* ── Original showAlert — id & behaviour preserved ── */
function showAlert(containerId, text, type = "danger") {
  const box = document.getElementById(containerId);
  if (!box) return;
  box.style.display = "block";
  box.className = `feedback-box mt-2 text-${type}`;
  box.textContent = text;
}
