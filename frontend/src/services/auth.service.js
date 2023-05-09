import axios from 'axios';

const API_URL = 'http://localhost:8081/';

class AuthService {
    login(user) {
        console.log(user)
        return axios.post(API_URL + 'authenticate', {
            email: user.email,
            password: user.password
        }).then(response => {
            console.log(response.data.token)
            if (response.data) {
                localStorage.setItem('user', JSON.stringify(response.data));
            }
            return response.data;
        });
    }

    logout() {
        localStorage.removeItem('user');
    }

    register(user) {
        console.log(user)
        return axios.post(API_URL + 'register', {
            email: user.email,
            password: user.password
        }).then(response => {
            console.log(response.data.token)
            if (response.data.token) {
                localStorage.setItem('user', JSON.stringify(response.data));
            }
            return response.data;
        });
    }
}

export default new AuthService();
