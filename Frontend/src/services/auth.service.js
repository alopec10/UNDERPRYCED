import axios from 'axios';

const API_URL = 'http://localhost:8888/user/';

class AuthService {
    login(user) {
        return axios
            .post(API_URL + 'authenticate', {
                email: user.email,
                password: user.password
            })
            .then(response => {
                if (response.data.jwt) {
                    localStorage.setItem('user', JSON.stringify(response.data));
                }

                return response.data;
            });
    }

    logout() {
        localStorage.removeItem('user');
    }

    register(user) {
        return axios.post(API_URL + 'register', {
            email: user.email,
            password: user.password
        });
    }
}

export default new AuthService();