document.getElementById("uploadForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Prevent default form submission

    let formData = new FormData();
    let fileInput = document.getElementById("videoFile");
    formData.append("video", fileInput.files[0]);

    fetch("/request", {
        method: "POST",
        body: formData
    })
    .then(response => response.text())
    .then(data => {
        document.getElementById("error-msg").hidden = true;
        document.getElementById("success-msg").hidden = false;
        location.reload();
    })
    .catch(error => {
        document.getElementById("error-msg").hidden = false;
        document.getElementById("success-msg").hidden = true;
        console.error("Error:", error);
    });
});
