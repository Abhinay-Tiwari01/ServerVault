// const API_BASE = 'http://localhost:8080';
import {API_BASE} from `../config.js` //IMPORT THE CONFIG FILE 

/* ── Panel switching ── */
function goToRegister() {
  document.getElementById('panelsTrack').classList.add('slide-left');
  document.body.classList.add('register-mode');
}
function goToLogin() {
  document.getElementById('panelsTrack').classList.remove('slide-left');
  document.body.classList.remove('register-mode');
}

/* ── Password toggles ── */
function bindEye(toggleId, inputId) {
  const toggle = document.getElementById(toggleId);
  const input = document.getElementById(inputId);
  if (!toggle || !input) return;
  toggle.addEventListener('click', () => {
    const isPass = input.type === 'password';
    input.type = isPass ? 'text' : 'password';
    toggle.textContent = isPass ? '🙈' : '👁';
  });
}
bindEye('toggle-password', 'password');
bindEye('toggle-create-password', 'createPassword');

/* ── Alert helper ── */
function showAlert(containerId, text, type = 'danger') {
  const box = document.getElementById(containerId);
  const small = box.querySelector('small') || box;
  if (!box) return;
  box.style.display = 'block';
  box.className = `feedback-box ${type}`;
  if (small !== box) small.textContent = text;
  else box.textContent = text;
}
function hideAlert(id) {
  const b = document.getElementById(id);
  if (b) b.style.display = 'none';
}

/* ── Login ── */
document.addEventListener('DOMContentLoaded', () => {
  ['username', 'password'].forEach(id => {
    document.getElementById(id)?.addEventListener('input', () => hideAlert('loginErrorBox'));
  });

  const form = document.getElementById('loginPage');
  if (form) {
    form.addEventListener('submit', async (e) => {
      e.preventDefault();
      const btn = document.getElementById('loginBtn');

      const userName = document.getElementById('username').value.trim();
      const password = document.getElementById('password').value.trim();

      hideAlert('loginErrorBox');

      if (!userName || !password) { showAlert('loginErrorBox', 'All fields required!'); return; }
      if (password.length < 4) { showAlert('loginErrorBox', 'Password must be at least 4 characters.'); return; }

      btn.classList.add('loading');
      try {
        const response = await fetch(`${API_BASE}/api/login/userLogin`,
          {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ userName, password })
          });
        const data = await response.json();
        console.log("Full response:", data);  // ← add this
        if (response.ok) {
          // save session storeage
          sessionStorage.setItem('user',JSON.stringify(data));
          sessionStorage.setItem('role',data.role);
          
          showAlert('loginErrorBox', 'Login successful! Redirecting…', 'success');
          setTimeout(() => {
            if (data.role === 'ADMIN') {
              window.location.href = '/FORMS/LoginPage/listOfUsers.html';  // ← fix this path
            } else {
              window.location.href = '/FORMS/FormEntry/menu.html';
            }
          }, 1500);
        } else {
          showAlert('loginErrorBox', data.message || data.error || 'Login failed.');
        }
      } catch (err) {
        showAlert('loginErrorBox', 'Network error: ' + err.message);
      } finally {
        btn.classList.remove('loading');
      }
    });
  }
});

/* ── Register ── */
function createAccount() {
  const btn = document.getElementById('registerBtn');
  const mobile = document.getElementById('mobile').value.trim();
  const userName = document.getElementById('userName').value.trim();
  const password = document.getElementById('createPassword').value.trim();

  hideAlert('registerErrorBox');

  if (!mobile || !userName || !password) {
    showAlert('registerErrorBox', 'All fields are required!');
    return;
  }

  btn.classList.add('loading');
  fetch(`${API_BASE}/api/login/createLogin`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ mobile, userName, password }),
  })
    .then(response => {
      if (response.ok) {
        showAlert('registerErrorBox', 'Account created! Redirecting to login…', 'success');
        document.getElementById('createForm').reset();
        setTimeout(() => { goToLogin(); }, 1800);
      } else {
        return response.text().then(msg => { throw new Error(msg || 'Username Already Exits | Mobile Number is Already Register | Registration failed.'); });
      }
    })
    .catch(err => {
      showAlert('registerErrorBox', 'Error: ' + err.message);
    })
    .finally(() => {
      btn.classList.remove('loading');
    });
    
}
// Auto-slide to register if #register in URL
document.addEventListener('DOMContentLoaded', () => {
    if (window.location.hash === '#register') {
        goToRegister();
    }
});