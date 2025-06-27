document.addEventListener('DOMContentLoaded', () => {
    loadPayments();
    document.getElementById('payment-form').addEventListener('submit', savePayment);
    document.getElementById('cancel-edit-payment').addEventListener('click', resetFormPayment);
});

function loadPayments() {
    fetch('/payments')
        .then(res => res.json())
        .then(data => {
            const tbody = document.querySelector('#payments-table tbody');
            tbody.innerHTML = '';
            (Array.isArray(data) ? data : (data._embedded && (data._embedded.payments || data._embedded.paymentList)) || []).forEach(payment => {
                const id = payment.id || '';
                const amount = payment.amount || '';
                const dueDate = payment.dueDate ? payment.dueDate.substring(0,10) : '';
                const vertrag = payment.vertrag ? payment.vertrag.id : '';
                tbody.innerHTML += `
                    <tr>
                        <td>${id}</td>
                        <td>${amount}</td>
                        <td>${dueDate}</td>
                        <td>${vertrag}</td>
                        <td>
                            <button onclick="editPayment(${id}, '${amount}', '${dueDate}')">Bearbeiten</button>
                            <button onclick="deletePayment(${id})">Löschen</button>
                        </td>
                    </tr>
                `;
            });
        });
}

function savePayment(e) {
    e.preventDefault();
    const id = document.getElementById('payment-id').value;
    const amount = document.getElementById('payment-amount').value;
    const dueDate = document.getElementById('payment-dueDate').value;
    // vertragId als Erweiterung möglich
    const method = id ? 'PUT' : 'POST';
    const url = id ? `/payments/${id}` : '/payments';
    fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ amount, dueDate })
    }).then(() => {
        loadPayments();
        resetFormPayment();
    });
}

function editPayment(id, amount, dueDate) {
    document.getElementById('payment-id').value = id;
    document.getElementById('payment-amount').value = amount;
    document.getElementById('payment-dueDate').value = dueDate;
    document.getElementById('cancel-edit-payment').style.display = '';
}

function resetFormPayment() {
    document.getElementById('payment-id').value = '';
    document.getElementById('payment-amount').value = '';
    document.getElementById('payment-dueDate').value = '';
    document.getElementById('cancel-edit-payment').style.display = 'none';
}

function deletePayment(id) {
    if (confirm('Zahlung wirklich löschen?')) {
        fetch(`/payments/${id}`, { method: 'DELETE' })
            .then(() => loadPayments());
    }
}
