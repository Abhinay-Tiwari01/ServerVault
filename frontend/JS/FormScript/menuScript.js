/* menuScript.js — connected to /api/server/ backend, all bugs fixed */
// const API_BASE = "http://localhost:8080";
import {API_BASE} from `../config.js` //IMPORT THE CONFIG FILE 


let dataList = [];

const searchInput   = document.getElementById("searchInput");
const tableBody     = document.getElementById("tableBody");
const noMatch       = document.getElementById("noMatch");
const selectAll     = document.getElementById("selectAll");
const selectLabel   = document.getElementById("selectLabel");
const headerCheck   = document.getElementById("headerCheck");
const bulkDeleteBtn = document.getElementById("bulkDeleteBtn");
const tableLoading  = document.getElementById("tableLoading");

/* ── Search toggle ── */
const searchToggle    = document.getElementById("searchToggle");
const searchInputWrap = document.getElementById("searchInputWrap");
const searchClose     = document.getElementById("searchClose");

searchToggle.addEventListener("click", () => {
    searchInputWrap.classList.toggle("open");
    if (searchInputWrap.classList.contains("open")) {
        setTimeout(() => searchInput.focus(), 300);
    }
});
searchClose.addEventListener("click", () => {
    searchInputWrap.classList.remove("open");
    searchInput.value = "";
    renderTable(dataList);
});

/* ── LOAD ALL FROM BACKEND ── */
async function loadData() {
    try {
        if (tableLoading) tableLoading.style.display = "flex";
        const response = await fetch(`${API_BASE}/api/server/getAllCompanyInfo`);
        if (!response.ok) throw new Error("Failed to fetch");
        dataList = await response.json();
        renderTable(dataList);
    } catch (err) {
        console.error("Load error:", err);
        showError("Failed to load data: " + err.message);
    } finally {
        if (tableLoading) tableLoading.style.display = "none";
    }
}

/* ── RENDER TABLE ── */
function renderTable(data) {
    tableBody.innerHTML = "";
    if (tableLoading) tableLoading.style.display = "none";

    if (!data || data.length === 0) {
        noMatch.style.display = "block";
        return;
    }
    noMatch.style.display = "none";

    data.forEach((item, index) => {
        const row = document.createElement("tr");

        // ← use serverId consistently (matches your entity/DTO field name)
        row.dataset.serverId = item.serverId;
        row.style.animationDelay = `${index * 30}ms`;

        row.innerHTML = `
            <td><input class="row-check" type="checkbox" value="${item.serverId}" /></td>
            <td><strong>${item.companyName   || "—"}</strong></td>
            <td><strong>${item.companyAddress   || "—"}</strong></td>
            <td><span class="cell-tag tag-year">${item.paymentYear || "—"}</span></td>
            <td><span class="cell-tag tag-year">${item.paymentAmount || "—"}</span></td>
            <td>${item.uploadDate            || "—"}</td>
            <td><span class="cell-tag tag-server">${item.serverIpName || "—"}</span></td>
            <td>${item.mcompName             || "—"}</td>
            <td>${item.loginUserName         || "—"}</td>
            <td class="action-cell">
                <div class="action-wrap">
                    <button class="action-btn edit-btn">Edit</button>
                    <button class="action-btn delete-btn">Delete</button>
                </div>
            </td>
        `;
        tableBody.appendChild(row);
    });

    updateSelectCount();
    updateBulkDeleteState();
}

/* ── INITIAL LOAD ── */
document.addEventListener("DOMContentLoaded", () => {
    loadData();
});

/* ── SEARCH ── */
searchInput.addEventListener("input", function () {
    const filter = this.value.toLowerCase();
    const filtered = dataList.filter(item =>
        (item.companyName   || "").toLowerCase().includes(filter) ||
        (item.serverIpName  || "").toLowerCase().includes(filter) ||
        (item.loginUserName || "").toLowerCase().includes(filter)
    );
    renderTable(filtered);
});

/* ── SELECT COUNT ── */
function updateSelectCount() {
    const count = document.querySelectorAll(".row-check:checked").length;
    selectLabel.textContent = count > 0 ? `${count} selected` : "Select all";
}

/* ── BULK DELETE ENABLE ── */
function updateBulkDeleteState() {
    const anyChecked = document.querySelectorAll(".row-check:checked").length > 0;
    bulkDeleteBtn.disabled = !anyChecked;
}

/* ── SELECT ALL ── */
selectAll.addEventListener("change", function () {
    document.querySelectorAll(".row-check").forEach(cb => cb.checked = this.checked);
    updateSelectCount();
    updateBulkDeleteState();
});

/* ── HEADER CHECK ── */
headerCheck.addEventListener("change", function () {
    document.querySelectorAll(".row-check").forEach(cb => cb.checked = this.checked);
    updateBulkDeleteState();
});

/* ── GLOBAL CLICK HANDLER ── */
document.addEventListener("click", async function (e) {
    const deleteBtn = e.target.closest(".delete-btn");
    const editBtn   = e.target.closest(".edit-btn");
    const checkBox  = e.target.closest(".row-check");

    /* DELETE SINGLE */
    if (deleteBtn) {
        const row      = deleteBtn.closest("tr");
        const serverId = row?.dataset.serverId;  // ← consistent key
        const name     = row?.querySelector("td:nth-child(2)")?.textContent.trim() || "this record";

        Swal.fire({
            title: `Delete ${name}?`,
            text: "This action cannot be undone.",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#ff4d6a",
            cancelButtonColor: "#444",
            confirmButtonText: "Yes, delete",
            cancelButtonText: "Cancel",
            background: "#16161f",
            color: "#f0f0f8"
        }).then(async result => {
            if (!result.isConfirmed) return;
            try {
                const res = await fetch(`${API_BASE}/api/server/deleteById/${serverId}`, {
                    method: "DELETE"
                });
                if (!res.ok) throw new Error("Delete failed");

                await Swal.fire({
                    title: "Deleted!",
                    icon: "success",
                    background: "#16161f",
                    color: "#f0f0f8",
                    timer: 1200,
                    showConfirmButton: false
                });
                await loadData();
            } catch (err) {
                Swal.fire({ title: "Error", text: err.message, icon: "error", background: "#16161f", color: "#f0f0f8" });
            }
        });
    }

    /* EDIT */
    if (editBtn) {
        const row      = editBtn.closest("tr");
        const serverId = row?.dataset.serverId;  // ← consistent key
        window.location.href = `/FORMS/FormEntry/form.html?mode=edit&id=${serverId}`;
    }

    /* ROW CHECKBOX */
    if (checkBox) {
        updateSelectCount();
        updateBulkDeleteState();
    }
});

/* ── BULK DELETE ── */
bulkDeleteBtn.addEventListener("click", function () {
    const checked = document.querySelectorAll(".row-check:checked");
    const count   = checked.length;

    Swal.fire({
        title: `Delete ${count} record${count > 1 ? "s" : ""}?`,
        text: "This action cannot be undone.",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#ff4d6a",
        cancelButtonColor: "#444",
        confirmButtonText: "Yes, delete all",
        cancelButtonText: "Cancel",
        background: "#16161f",
        color: "#f0f0f8"
    }).then(async result => {
        if (!result.isConfirmed) return;

        // ← use id from checkbox value (set to serverId in renderTable)
        const ids = Array.from(checked).map(cb => cb.value);
        try {
            await Promise.all(
                ids.map(id => fetch(`${API_BASE}/api/server/deleteById/${id}`, { method: "DELETE" }))
            );
            await loadData();
            Swal.fire({ title: "Done!", icon: "success", background: "#16161f", color: "#f0f0f8", timer: 1200, showConfirmButton: false });
        } catch (err) {
            Swal.fire({ title: "Error", text: err.message, icon: "error", background: "#16161f", color: "#f0f0f8" });
        }
    });
});

/* ── Show error ── */
function showError(message) {
    const box = document.createElement("div");
    box.className = "mt-2 text-danger";
    box.textContent = message;
    const main = document.querySelector("main");
    if (main) main.prepend(box);
}
