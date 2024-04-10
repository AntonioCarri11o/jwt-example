import axios from 'axios';
import { API_URL } from '../resources/Utilities';
import authService from './Auth.js';
const getHeroes = async() => {
    const token = authService.returnToken();
    const response = await axios.get(`${API_URL}/hero/list`, {
        headers: {
            Authorization: `Bearer ${token}`
        }
    });
    return response.data;
}

export default {
    getHeroes,
}