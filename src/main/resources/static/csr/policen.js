document.addEventListener('DOMContentLoaded', () => {
    loadPolicen();
});

function loadPolicen() {
    fetch('/policen')
        .then(res => res.json())
        .then(data => {
            const tbody = document.querySelector('#policen-table tbody');
            tbody.innerHTML = '';
            data._embedded && data._embedded.policeList.forEach(police => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${police.id}</td>
                    <td>${police.nummer || ''}</td>
                    <td>${police.vertrag ? police.vertrag.id : ''}</td>
                    <td>
                        <button onclick="deletePolice(${police.id})">Löschen</button>
                    </td>
                `;
                tbody.appendChild(tr);
            });
        });
}

function deletePolice(id) {
    if (confirm('Police wirklich löschen?')) {
        fetch(`/policen/${id}`, { method: 'DELETE' })
            .then(() => loadPolicen());
    }
}
