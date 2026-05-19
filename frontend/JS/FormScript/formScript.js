/* formScript.js — all URLs corrected to /api/server/ */
// const API_BASE = "http://localhost:8080";
const API_BASE = IS_PRODUCTION 
  ? 'https://servervault-gf7i.onrender.com' 
  : 'http://localhost:8080';


/* ── Eye toggle ── */
(function () {
    const toggle = document.getElementById("toggle-password");
    const input  = document.getElementById("password");
    if (toggle && input) {
        toggle.addEventListener("click", () => {
            const isPass = input.type === "password";
            input.type = isPass ? "text" : "password";
            toggle.textContent = isPass ? "🙈" : "👁";
        });
    }
})();

/* ── Toggle form mode ── */
function toggleFormMode(mode) {
    const submitBtn = document.getElementById("submit-btn");
    const updateBtn = document.getElementById("update-btn");
    const heading   = document.getElementById("formHeading");

    if (mode === "edit") {
        submitBtn.classList.add("d-none");
        updateBtn.classList.remove("d-none");
        if (heading) heading.textContent = "Edit Company Entry";
    } else {
        submitBtn.classList.remove("d-none");
        updateBtn.classList.add("d-none");
        if (heading) heading.textContent = "Company Payment Entry";
    }
}

/* ── URL params ── */
const params = new URLSearchParams(window.location.search);
const isEdit = params.get("mode") === "edit";
const editId = params.get("id");

/* ── EDIT MODE — load from backend ── */
if (isEdit) {
    toggleFormMode("edit");

    // ← @PathVariable → /getCompanyById/{id} (after fixing controller)
    fetch(`${API_BASE}/api/server/getCompanyById/${editId}`)
        .then(res => {
            if (!res.ok) throw new Error("Record not found — id: " + editId);
            return res.json();
        })
        .then(record => {
            console.log("Edit record loaded:", record);
            document.getElementById("recordId").value                     = record.serverId       || "";
            document.querySelector("[name=companyName]").value             = record.companyName    || "";
            document.querySelector("[name=companyAddress]").value          = record.companyAddress || "";
            document.querySelector("[name=paymentAmount]").value           = record.paymentAmount  || "";
            document.querySelector("[name=paymentYear]").value             = record.paymentYear    || "";
            document.querySelector("[name=uploadDate]").value              = record.uploadDate     || "";
            document.querySelector("[name=serverName]").value              = record.serverIpName   || "";
            document.querySelector("[name=mcomp]").value                   = record.mcompName      || "";
            document.querySelector("[name=userName]").value                = record.loginUserName  || "";
            document.querySelector("[name=password]").value                = record.password       || "";
        })
        .catch(err => {
            Swal.fire({
                title: "Error loading record",
                text: err.message,
                icon: "error",
                background: "#16161f",
                color: "#f0f0f8"
            });
        });
}

/* ── Helper: build DTO from form — field names match ServerDetailsDTO ── */
function buildDTO(form) {
    const f = new FormData(form);
    return {
        companyName:    f.get("companyName")    || "",
        companyAddress: f.get("companyAddress") || "",
        paymentAmount:  f.get("paymentAmount")  || "",
        paymentYear:    f.get("paymentYear")    || "",
        uploadDate:     f.get("uploadDate")     || "",  
        serverIpName:   f.get("serverName")     || "",
        mcompName:      f.get("mcomp")          || "",
        loginUserName:  f.get("userName")       || "",
        password:       f.get("password")       || ""
    };
}

/* ── CREATE ── */
document.getElementById("data-form").addEventListener("submit", async function (e) {
    e.preventDefault();

    const btn  = document.getElementById("submit-btn");
    const text = btn.querySelector(".btn-text");
    btn.disabled = true;
    if (text) text.textContent = "Saving…";

    try {
        const response = await fetch(`${API_BASE}/api/server/createCompany`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(buildDTO(this))
        });

        if (!response.ok) {
            const msg = await response.text();
            throw new Error(msg || "Create failed");
        }

        await Swal.fire({
            title: "Entry Saved!",
            icon: "success",
            background: "#16161f",
            color: "#f0f0f8",
            timer: 1200,
            showConfirmButton: false
        });
        window.location.href = "/FORMS/FormEntry/menu.html";

    } catch (err) {
        Swal.fire({ title: "Error", text: err.message, icon: "error", background: "#16161f", color: "#f0f0f8" });
    } finally {
        btn.disabled = false;
        if (text) text.textContent = "Submit Entry";
    }
});

/* ── UPDATE ── */
document.getElementById("update-btn").addEventListener("click", async function () {
    const btn  = this;
    const text = btn.querySelector(".btn-text");
    btn.disabled = true;
    if (text) text.textContent = "Updating…";

    try {
        // ← Fixed: /api/server/ not /api/company/
        const response = await fetch(`${API_BASE}/api/server/updateCompany/${editId}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(buildDTO(document.getElementById("data-form")))
        });

        if (!response.ok) {
            const msg = await response.text();
            throw new Error(msg || "Update failed");
        }

        await Swal.fire({
            title: "Updated!",
            icon: "success",
            background: "#16161f",
            color: "#f0f0f8",
            timer: 1200,
            showConfirmButton: false
        });
        window.location.href = "/FORMS/FormEntry/menu.html";

    } catch (err) {
        Swal.fire({ title: "Error", text: err.message, icon: "error", background: "#16161f", color: "#f0f0f8" });
    } finally {
        btn.disabled = false;
        if (text) text.textContent = "Update Entry";
    }
});

/* ── CANCEL ── */
document.getElementById("cancel-btn").addEventListener("click", () => {
    window.location.href = "../../FORMS/FormEntry/menu.html";
});
