document.addEventListener('DOMContentLoaded', () => {
    loadVertraege();
    document.getElementById('vertrag-form').addEventListener('submit', saveVertrag);
    document.getElementById('cancel-edit-vertrag').addEventListener('click', resetFormVertrag);
});

function loadVertraege() {
    fetch('/vertrage')
        .then(res => res.json())
        .then(data => {
            const tbody = document.querySelector('#vertraege-table tbody');
            tbody.innerHTML = '';
            (Array.isArray(data) ? data : (data._embedded && (data._embedded.vertraege || data._embedded.vertragList)) || []).forEach(vertrag => {
                const id = vertrag.id || '';
                const state = vertrag.state || '';
                const start = vertrag.startingDate ? vertrag.startingDate.substring(0,10) : '';
                const end = vertrag.endingDate ? vertrag.endingDate.substring(0,10) : '';
                const customer = vertrag.customer ? (vertrag.customer.firstName + ' ' + vertrag.customer.lastName) : '';
                tbody.innerHTML += `
                    <tr>
                        <td>${id}</td>
                        <td>${state}</td>
                        <td>${start}</td>
                        <td>${end}</td>
                        <td>${customer}</td>
                        <td>
                            <button onclick="editVertrag(${id}, '${state}', '${start}', '${end}')">Bearbeiten</button>
                            <button onclick="deleteVertrag(${id})">Löschen</button>
                        </td>
                    </tr>
                `;
            });
        });
}

function saveVertrag(e) {
    e.preventDefault();
    const id = document.getElementById('vertrag-id').value;
    const state = document.getElementById('vertrag-state').value;
    const startingDate = document.getElementById('vertrag-start').value;
    const endingDate = document.getElementById('vertrag-end').value;
    // customerId kann als Erweiterung ergänzt werden
    const method = id ? 'PUT' : 'POST';
    const url = id ? `/vertrage/${id}` : '/vertrage';
    fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ state, startingDate, endingDate })
    }).then(() => {
        loadVertraege();
        resetFormVertrag();
    });
}

function editVertrag(id, state, start, end) {
    document.getElementById('vertrag-id').value = id;
    document.getElementById('vertrag-state').value = state;
    document.getElementById('vertrag-start').value = start;
    document.getElementById('vertrag-end').value = end;
    document.getElementById('cancel-edit-vertrag').style.display = '';
}

function resetFormVertrag() {
    document.getElementById('vertrag-id').value = '';
    document.getElementById('vertrag-state').value = '';
    document.getElementById('vertrag-start').value = '';
    document.getElementById('vertrag-end').value = '';
    document.getElementById('cancel-edit-vertrag').style.display = 'none';
}

function deleteVertrag(id) {
    if (confirm('Vertrag wirklich löschen?')) {
        fetch(`/vertrage/${id}`, { method: 'DELETE' })
            .then(() => loadVertraege());
    }
}
