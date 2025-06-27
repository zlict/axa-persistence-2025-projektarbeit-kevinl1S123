document.addEventListener('DOMContentLoaded', () => {
    loadClaims();
    document.getElementById('claim-form').addEventListener('submit', saveClaim);
    document.getElementById('cancel-edit-claim').addEventListener('click', resetFormClaim);
});

function loadClaims() {
    fetch('/claims')
        .then(res => res.json())
        .then(data => {
            const tbody = document.querySelector('#claims-table tbody');
            tbody.innerHTML = '';
            (Array.isArray(data) ? data : (data._embedded && (data._embedded.claims || data._embedded.claimList)) || []).forEach(claim => {
                const id = claim.id || '';
                const date = claim.date ? claim.date.substring(0,10) : '';
                const amount = claim.amount || '';
                const desc = claim.description || '';
                const vertrag = claim.vertrag ? claim.vertrag.id : '';
                tbody.innerHTML += `
                    <tr>
                        <td>${id}</td>
                        <td>${date}</td>
                        <td>${amount}</td>
                        <td>${desc}</td>
                        <td>${vertrag}</td>
                        <td>
                            <button onclick="editClaim(${id}, '${date}', '${amount}', '${desc}')">Bearbeiten</button>
                            <button onclick="deleteClaim(${id})">Löschen</button>
                        </td>
                    </tr>
                `;
            });
        });
}

function saveClaim(e) {
    e.preventDefault();
    const id = document.getElementById('claim-id').value;
    const date = document.getElementById('claim-date').value;
    const amount = document.getElementById('claim-amount').value;
    const description = document.getElementById('claim-desc').value;
    // vertragId als Erweiterung möglich
    const method = id ? 'PUT' : 'POST';
    const url = id ? `/claims/${id}` : '/claims';
    fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ date, amount, description })
    }).then(() => {
        loadClaims();
        resetFormClaim();
    });
}

function editClaim(id, date, amount, desc) {
    document.getElementById('claim-id').value = id;
    document.getElementById('claim-date').value = date;
    document.getElementById('claim-amount').value = amount;
    document.getElementById('claim-desc').value = desc;
    document.getElementById('cancel-edit-claim').style.display = '';
}

function resetFormClaim() {
    document.getElementById('claim-id').value = '';
    document.getElementById('claim-date').value = '';
    document.getElementById('claim-amount').value = '';
    document.getElementById('claim-desc').value = '';
    document.getElementById('cancel-edit-claim').style.display = 'none';
}

function deleteClaim(id) {
    if (confirm('Claim wirklich löschen?')) {
        fetch(`/claims/${id}`, { method: 'DELETE' })
            .then(() => loadClaims());
    }
}
