import axios from 'axios';

const API_URL = 'http://localhost:8000/api/v1/';

class AuthService {
    login(user) {
        console.log("qwe", user)
        return axios.post(API_URL + 'users/login', {
            email: user.email,
            password: user.password
        }).then(response => {
            console.log(response.data)
            let newuser = response.data;
            if (response.data) {
                axios.get("http://localhost:8081/user",
                    {headers: {Authorization: 'Bearer ' + response.data.token}})
                    .then(r => {
                        console.log(r);
                        newuser.user = r.data;
                        console.log(newuser);
                        localStorage.setItem('user', JSON.stringify(newuser));
                        return newuser;
                    }).catch(e => {
                    console.log(e);
                });
            }
        });
    }

    logout() {
        localStorage.removeItem('user');
    }

    register(user) {
        console.log(user)
        return axios.post(API_URL + 'users', {
            email: user.email,
            password1: user.password,
            password2: user.password,
            username: user.email

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
