/* usersList.js — original logic fully preserved, UI states added */
// const API_BASE = 'http://localhost:8080';
import {API_BASE} from `../config.js` //IMPORT THE CONFIG FILE 


let userList = [];

/* ── Load data from backend ── */
async function loadUsers() {
  try {
    // const response = await fetch("http://localhost:8080/api/login/getAllUsers");
    const response = await fetch(`${API_BASE}/api/login/getAllUsers`);

    const data = await response.json();
    userList = data;
    renderTable();
  } catch (err) {
    console.error("Failed to load users", err);
    showError("Failed to load users: " + err.message);
  } finally {
    hideLoading();
  }
}

// Delete function for users By Id
async function deleteUser(userId, userName) {
    const result = await Swal.fire({
        title: `Delete ${userId}?`,
        title: `Delete ${userName}?`,
        text: "This action cannot be undone.",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#ff4d6a',
        cancelButtonColor: '#444',
        confirmButtonText: 'Yes, delete',
        cancelButtonText: 'Cancel',
        background: '#16161f',
        color: '#f0f0f8'
    });

    if (!result.isConfirmed) return;

    try {
        const response = await fetch(`${API_BASE}/api/login/deleteById/${userId}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            Swal.fire({
                title: 'Deleted!',
                text: `${userName} has been removed.`,
                icon: 'success',
                background: '#16161f',
                color: '#f0f0f8',
                timer: 1500,
                showConfirmButton: false
            });
            await loadUsers();  // ← refreshes table automatically
        } else {
            throw new Error("Delete failed — check backend endpoint");
        }
    } catch (err) {
        Swal.fire({
            title: 'Error',
            text: err.message,
            icon: 'error',
            background: '#16161f',
            color: '#f0f0f8'
        });
    }
}

// DELETE ALL USERS PERMENTALLY
async function deleteAllusers() {
    const result = await Swal.fire({
        title: 'Delete ALL Users?',
        text: "This will permanently remove every user. Cannot be undone!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#ff4d6a',
        cancelButtonColor: '#444',
        confirmButtonText: 'Yes, delete',
        cancelButtonText: 'Cancel',
        background: '#16161f',
        color: '#f0f0f8'
    });

    if (!result.isConfirmed) return;

    try {
        const response = await fetch(`${API_BASE}/api/login/deleteAllUsers`, {
            method: 'DELETE'
        });

        if (response.ok) {
            Swal.fire({
                title: 'Deleted!',
                text: `All Uers has been removed.`,
                icon: 'success',
                background: '#16161f',
                color: '#f0f0f8',
                timer: 1500,
                showConfirmButton: false
            });
            await loadUsers();  // ← refreshes table automatically
        } else {
            throw new Error("Delete failed — check backend endpoint");
        }
    } catch (err) {
        Swal.fire({
            title: 'Error',
            text: err.message,
            icon: 'error',
            background: '#16161f',
            color: '#f0f0f8'
        });
    }
}

/* ── Render table rows from userList ── */
function renderTable() {
  const tbody = document.getElementById("userTableBody");
  tbody.innerHTML = "";

  const empty = document.getElementById("tableEmpty");

  if (!userList.length) {
    if (empty) empty.style.display = "block";
    return;
  }
  if (empty) empty.style.display = "none";

  userList.forEach((user, index) => {
    const tr = document.createElement("tr");
    tr.style.animationDelay = `${index * 40}ms`;
    tr.innerHTML = `
      <td>${index + 1}</td>
      <td>${user.userId}</td>
      <td>${user.userName}</td>
      <td>${user.getEncryptedPassword || '••••••'}</td>
      <td>${user.mobile}</td>
      <td class="actions-column">
        <a href="/FORMS/LoginPage/forgetPassword.html?userId=${user.userId}" class="btn btn-primary btn-sm me-1">
          Reset PW
        </a>
        <button class="btn btn-danger btn-sm" onclick="deleteUser(${user.userId}, '${user.userName}')">
          Delete
        </button>
      </td>
    `;
    tbody.appendChild(tr);
  });
}

/* ── Filter table by username / mobile (original function name kept) ── */
function filterUsers() {
  const filter = document.getElementById("searchBox").value.trim().toLowerCase();
  const tbody  = document.getElementById("userTableBody");
  const empty  = document.getElementById("tableEmpty");
  tbody.innerHTML = "";

  const filtered = userList.filter(u =>
    u.userName.toLowerCase().includes(filter) ||
    u.mobile.toLowerCase().includes(filter)
  );

  if (!filtered.length) {
    if (empty) { empty.style.display = "block"; empty.textContent = "No users match your search."; }
    return;
  }
  if (empty) empty.style.display = "none";

  filtered.forEach((user, index) => {
    const tr = document.createElement("tr");
    tr.style.animationDelay = `${index * 40}ms`;
    tr.innerHTML = `
      <td>${index + 1}</td>
      <td>${user.userId}</td>
      <td>${user.userName}</td>
      <td>••••••</td>
      <td>${user.mobile}</td>
      <td class="actions-column">
        <a href="/FORMS/LoginPage/forgetPassword.html?userId=${user.userId}" class="btn btn-primary btn-sm me-1">
          Reset PW
        </a>
        <a href="/deleteUser/${user.userId}" class="btn btn-danger btn-sm me-1"
           onclick="return confirm('Delete user ${user.userName}?');">
          Delete
        </a>
      </td>
    `;
    tbody.appendChild(tr);
  });
}

/* ── Show error in UI (original function kept) ── */
function showError(message) {
  const box = document.createElement("div");
  box.className = "mt-2 text-danger";
  box.textContent = message;
  const main = document.querySelector("main");
  if (main) main.prepend(box);
}

/* ── Loading state helpers ── */
function hideLoading() {
  const loading = document.getElementById("tableLoading");
  if (loading) loading.style.display = "none";
}

/* ── Load on DOMContentLoaded (original behaviour) ── */
document.addEventListener("DOMContentLoaded", () => {
  loadUsers();
});
