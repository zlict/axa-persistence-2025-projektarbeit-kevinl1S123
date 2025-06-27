document.addEventListener('DOMContentLoaded', () => {
    loadCustomers();
    document.getElementById('customer-form').addEventListener('submit', saveCustomer);
    document.getElementById('cancel-edit').addEventListener('click', resetForm);
});

function loadCustomers() {
    fetch('/customers')
        .then(res => res.json())
        .then(data => {
            const tbody = document.querySelector('#customers-table tbody');
            tbody.innerHTML = '';
            // Die API liefert ein Array, kein _embedded
            (Array.isArray(data) ? data : (data._embedded && (data._embedded.customers || data._embedded.customerList)) || []).forEach(customer => {
                const name = (customer.firstName || '') + ' ' + (customer.lastName || '');
                const email = customer.email || '';
                const id = customer.id || '';
                tbody.innerHTML += `
                    <tr>
                        <td>${id}</td>
                        <td>${name.trim()}</td>
                        <td>${email}</td>
                        <td>
                            <button onclick="editCustomer(${id}, '${customer.firstName || ''}', '${customer.lastName || ''}', '${email}')">Bearbeiten</button>
                            <button onclick="deleteCustomer(${id})">Löschen</button>
                        </td>
                    </tr>
                `;
            });
        });
}

function saveCustomer(e) {
    e.preventDefault();
    const id = document.getElementById('customer-id').value;
    const name = document.getElementById('customer-name').value.trim();
    const email = document.getElementById('customer-email').value;
    let firstName = name.split(' ')[0] || '';
    let lastName = name.split(' ').slice(1).join(' ') || '';
    const method = id ? 'PUT' : 'POST';
    const url = id ? `/customers/${id}` : '/customers';
    fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ firstName, lastName, email })
    }).then(() => {
        loadCustomers();
        resetForm();
    });
}

function editCustomer(id, firstName, lastName, email) {
    document.getElementById('customer-id').value = id;
    document.getElementById('customer-name').value = (firstName + ' ' + lastName).trim();
    document.getElementById('customer-email').value = email;
    document.getElementById('cancel-edit').style.display = '';
}

function resetForm() {
    document.getElementById('customer-id').value = '';
    document.getElementById('customer-name').value = '';
    document.getElementById('customer-email').value = '';
    document.getElementById('cancel-edit').style.display = 'none';
}

function deleteCustomer(id) {
    if (confirm('Kunde wirklich löschen?')) {
        fetch(`/customers/${id}`, { method: 'DELETE' })
            .then(() => loadCustomers());
    }
}
