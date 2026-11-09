var overlay = document.getElementById("overlay");
var subtitle = document.getElementById("subtitle");
var footer = document.getElementById("footer");
var normalSubtitle = subtitle.textContent;
var normalFooter = footer.textContent;

function showDialog(id, subtitleText, footerText) {
    var dialogs = document.getElementsByClassName("dialog");
    for (var i = 0; i < dialogs.length; i++) {
        dialogs[i].style.display = "none";
    }
    document.getElementById(id).style.display = "block";
    overlay.classList.add("open");
    subtitle.textContent = subtitleText;
    footer.textContent = footerText;
}

function closeDialog() {
    overlay.classList.remove("open");
    subtitle.textContent = normalSubtitle;
    footer.textContent = normalFooter;
}
