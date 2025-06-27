document.addEventListener('DOMContentLoaded', () => {
    loadDamageTypes();
    document.getElementById('damagetype-form').addEventListener('submit', saveDamageType);
    document.getElementById('cancel-edit-damagetype').addEventListener('click', resetFormDamageType);
});

function loadDamageTypes() {
    fetch('/damagetypes')
        .then(res => res.json())
        .then(data => {
            const tbody = document.querySelector('#damagetypes-table tbody');
            tbody.innerHTML = '';
            (Array.isArray(data) ? data : (data._embedded && (data._embedded.damagetypes || data._embedded.damageTypeList)) || []).forEach(dt => {
                const id = dt.id || '';
                const type = dt.type || '';
                tbody.innerHTML += `
                    <tr>
                        <td>${id}</td>
                        <td>${type}</td>
                        <td>
                            <button onclick="editDamageType(${id}, '${type}')">Bearbeiten</button>
                            <button onclick="deleteDamageType(${id})">Löschen</button>
                        </td>
                    </tr>
                `;
            });
        });
}

function saveDamageType(e) {
    e.preventDefault();
    const id = document.getElementById('damagetype-id').value;
    const type = document.getElementById('damagetype-type').value;
    const method = id ? 'PUT' : 'POST';
    const url = id ? `/damagetypes/${id}` : '/damagetypes';
    fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ type })
    }).then(() => {
        loadDamageTypes();
        resetFormDamageType();
    });
}

function editDamageType(id, type) {
    document.getElementById('damagetype-id').value = id;
    document.getElementById('damagetype-type').value = type;
    document.getElementById('cancel-edit-damagetype').style.display = '';
}

function resetFormDamageType() {
    document.getElementById('damagetype-id').value = '';
    document.getElementById('damagetype-type').value = '';
    document.getElementById('cancel-edit-damagetype').style.display = 'none';
}

function deleteDamageType(id) {
    if (confirm('Schadentyp wirklich löschen?')) {
        fetch(`/damagetypes/${id}`, { method: 'DELETE' })
            .then(() => loadDamageTypes());
    }
}
