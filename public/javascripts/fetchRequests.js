function fetchRequests() {
    fetch('/request')
        .then(response => response.json())
        .then(data => {
          console.log(data);
            let tableBody = document.getElementById("list-requests-table");
            data.forEach(request => {
                let row = `<tr class="request-row" data-status="${request.status}">
                    <td>${request.inputFileName}</td>
                    <td>${request.status}</td>
                    <td>${request.status === "COMPLETED" ? `<button class="btn btn-sm btn-light" onclick="downloadResult('${request.id}','${request.outputFileName}')">Download</button>` : "Processing..."}</td>
                </tr>`;
                tableBody.innerHTML += row;
            });
        });
}

function sortRequests() {
    let filter = document.getElementById("sortOrder").value;
    document.querySelectorAll(".request-row").forEach(row => {
        row.style.display = filter === "ALL" || row.dataset.status === filter ? "" : "none";
    });
}

function downloadResult(id, fileName) {
  const url = `/request/${encodeURIComponent(id)}`;

  const a = document.createElement('a');
  a.href = url;
  a.download = fileName;
  a.style.display = 'none';
  document.body.appendChild(a);
  a.click();
  document.body.removeChild(a);
}

fetchRequests();