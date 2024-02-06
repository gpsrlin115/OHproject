const token = localStorage.getItem('jwtToken');

// Fetch API를 사용하여 요청 보내기
fetch('https://example.com/api/endpoint', {
    method: 'GET', // 또는 'POST', 'PUT', 'DELETE' 등
    headers: {
        // Bearer 스키마를 사용하여 헤더에 JWT 포함
        'Authorization': `Bearer ${token}`
    }
})
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error('Error:', error));