import http from 'k6/http';
import { sleep } from 'k6';

export default function () {
    const url = 'https://mysite.test.ru:443/api/v1/test/tokens';
    const payload = JSON.stringify({
        username: 'user',
        password: 'pass',
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    http.post(url, payload, params);
    http.post(url, payload, params);
    http.post(url, payload, params);

    sleep(1);
}