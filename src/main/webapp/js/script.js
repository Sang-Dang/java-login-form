const sections = document.querySelectorAll("section.content");
const navButtons = document.querySelectorAll("nav button");
const emailRegex = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
const studentIDRegex = /^SE\d{6}$/;
const passwordRegex = /^(?=.*\d)(?=.*[!@#$%^&*])[a-zA-Z\d!@#$%^&*]{8,}$/;
const flyouts = document.querySelectorAll("flyout");

function validateInputs() {
    validateOnScreenNonBlankInputs();
    console.log(document.querySelectorAll(".container section.active .error").length);
    return document.querySelectorAll(".container section.active .error").length == 0;
}

function validateFlyoutInputs() {
    validateOnFlyoutNonBlankInputs();
    return document.querySelectorAll("section.flyout.active .error").length == 0;
}

function switchTabs(item) {
    if (!item.classList.contains("active")) {
        navButtons.forEach(button => { button.classList.remove("active"); });
        sections.forEach(section => {
            if (section.id == item.classList[0]) {
                section.classList.add("active");
            } else {
                section.classList.remove("active");
            }
        });
        item.classList.add("active");
    }
}

function toggleFlyout(item, toggleTo) {
    console.log("Toggle");
    if (toggleTo) {
        item.classList.add("active");
        item.children[0].classList.add("active");
    } else {
        item.classList.remove("active");
        item.children[0].classList.remove("active");
    }
}

navButtons.forEach(item => {
    item.addEventListener('click', () => {
        switchTabs(item);
    })
})

function toggleErrorInput(input) {
    input.classList.toggle("error");
    input.nextSibling.classList.toggle("error");
}

function toggleErrorInput(input, toggleTo, errormsg) {
    if (toggleTo === true) {
        input.classList.add("error");
        input.nextElementSibling.classList.add("error");
        input.nextElementSibling.nextElementSibling.classList.add("error");
        input.nextElementSibling.nextElementSibling.innerHTML = errormsg;
    } else {
        input.classList.remove("error");
        input.nextElementSibling.classList.remove("error");
        input.nextElementSibling.nextElementSibling.classList.remove("error");
        input.nextElementSibling.nextElementSibling.innerHTML = "";
    }
}

function validateOnScreenNonBlankInputs() {
    var inputfields = document.querySelectorAll("section.active .input");
    inputfields.forEach(item => {
        if(item.value == "") {
            toggleErrorInput(item, true, "Cannot be empty");
        } else {
            toggleErrorInput(item, false, "Cannot be empty");
        }
    })
}

function validateOnFlyoutNonBlankInputs() {
    var inputfields = document.querySelectorAll("section.flyout.active .input");
    inputfields.forEach(item => {
        if(item.value == "") {
            toggleErrorInput(item, true, "Cannot be empty");
        } else {
            toggleErrorInput(item, false, "Cannot be empty");
        }
    })
}

function validateNonBlankInputs() {
    var nonblank = document.querySelectorAll("section.active .nonblank");
    for (let i = 0; i < nonblank.length; i++) {
        nonblank[i].addEventListener('blur', () => {
            if (nonblank[i].value == "") {
                toggleErrorInput(nonblank[i], true, "Cannot be empty");
            } else {
                toggleErrorInput(nonblank[i], false, "Cannot be empty");
            }
        })
    }
}

function validateEmails() {
    document.querySelectorAll("section.active .validemail").forEach(item => {
        item.addEventListener('blur', () => {
            if (!emailRegex.test(item.value)) {
                toggleErrorInput(item, true, "Email invalid");
            } else {
                toggleErrorInput(item, false, "Email invalid");
            }
        })
    });
}

function validateStudentID() {
    document.querySelectorAll("section.active .validstudentid").forEach(item => {
        item.addEventListener('blur', () => {
            if (!studentIDRegex.test(item.value)) {
                toggleErrorInput(item, true, "StudentID format SE------");
            } else {
                toggleErrorInput(item, false, "StudentID format SE------");
            }
        })
    })
}

function validatePassword() {
    document.querySelectorAll("section.active .validpassword").forEach(item => {
        item.addEventListener('blur', () => {
            if (!passwordRegex.test(item.value)) {
                toggleErrorInput(item, true, "Password too simple");
            } else {
                toggleErrorInput(item, false, "Password too simple");
            }
        })
    })
}

function validator() {
    validateNonBlankInputs();
    validateStudentID();
    validateEmails();
    validatePassword();
}

validator();

document.querySelectorAll("nav button").forEach(item => {
    item.addEventListener('click', () => {
        validator();
    })
})

document.querySelectorAll(".flyouttoggle").forEach(item => {
    item.addEventListener('click', () => {
        validateEmails();
    })
})

