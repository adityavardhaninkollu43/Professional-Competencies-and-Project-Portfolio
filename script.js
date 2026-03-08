const workGrid = document.getElementById("workGrid");
const skillsContainer = document.getElementById("skillsContainer");

window.onload = () => {
    loadSkills();
    loadProjects();
    const savedBio = localStorage.getItem("portfolio_bio");
    if (savedBio) document.getElementById("displayProfileDesc").innerText = savedBio;
};

function logout() { alert("Logging out..."); window.location.href = "login.html"; }

// Modal Controls
function openM(id) { document.getElementById(id).style.display = "block"; }
function closeM(id) { document.getElementById(id).style.display = "none"; }

document.getElementById("openModalBtn").onclick = () => openM("projectModal");
document.getElementById("openSkillModalBtn").onclick = () => openM("skillModal");
document.getElementById("openEditProfileBtn").onclick = () => {
    document.getElementById("editBioInput").value = document.getElementById("displayProfileDesc").innerText;
    openM("editProfileModal");
};

window.onclick = (e) => {
    if (e.target.className === "modal") e.target.style.display = "none";
};

// Skills Logic
document.getElementById("saveSkillBtn").onclick = () => {
    const name = document.getElementById("skillNameInput").value;
    if (!name.trim()) return;
    addSkillToDOM(name);
    saveSkills();
    closeM("skillModal");
    document.getElementById("skillNameInput").value = "";
};

function addSkillToDOM(name) {
    const tag = document.createElement("span");
    tag.className = "skill-tag";
    tag.innerText = name;
    tag.oncontextmenu = (e) => { e.preventDefault(); tag.remove(); saveSkills(); };
    skillsContainer.appendChild(tag);
}

function saveSkills() {
    const skills = Array.from(document.querySelectorAll(".skill-tag")).map(t => t.innerText);
    localStorage.setItem("portfolio_skills", JSON.stringify(skills));
}

function loadSkills() {
    JSON.parse(localStorage.getItem("portfolio_skills") || "[]").forEach(addSkillToDOM);
}

// Project Logic
document.getElementById("addProjectBtn").onclick = () => {
    const title = document.getElementById("projectTitle").value;
    const desc = document.getElementById("projectDesc").value;
    const url = document.getElementById("projectURL").value || "#";
    const imgFile = document.getElementById("projectImage").files[0];

    if (!title.trim() || !desc.trim()) return alert("Fill fields!");

    const reader = new FileReader();
    reader.onload = (e) => {
        const data = { title, desc, url, image: e.target.result };
        addProjectToDOM(data);
        saveProjects();
    };
    if (imgFile) reader.readAsDataURL(imgFile);
    else addProjectToDOM({ title, desc, url, image: null });
    
    closeM("projectModal");
};

function addProjectToDOM(data) {
    const card = document.createElement("div");
    card.className = "project-card";
    card.innerHTML = `
        <div class="project-img">${data.image ? `<img src="${data.image}">` : data.title}</div>
        <div class="project-info">
            <h4>${data.title}</h4><p>${data.desc}</p>
            <div style="display:flex; gap:10px; margin-top:15px;">
                <a href="${data.url}" target="_blank" class="btn" style="padding:8px 16px; margin:0; font-size:0.8rem;">View</a>
                <button class="btn-delete">Delete</button>
            </div>
        </div>`;
    card.querySelector(".btn-delete").onclick = () => { card.remove(); saveProjects(); };
    workGrid.appendChild(card);
}

function saveProjects() {
    const cards = Array.from(document.querySelectorAll(".project-card")).map(c => ({
        title: c.querySelector("h4").innerText,
        desc: c.querySelector("p").innerText,
        url: c.querySelector("a").href,
        image: c.querySelector("img") ? c.querySelector("img").src : null
    }));
    localStorage.setItem("portfolio_projects", JSON.stringify(cards));
}

function loadProjects() {
    JSON.parse(localStorage.getItem("portfolio_projects") || "[]").forEach(addProjectToDOM);
}

// Profile Bio
document.getElementById("saveBioBtn").onclick = () => {
    const bio = document.getElementById("editBioInput").value;
    document.getElementById("displayProfileDesc").innerText = bio;
    localStorage.setItem("portfolio_bio", bio);
    closeM("editProfileModal");
};