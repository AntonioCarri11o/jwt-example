import axios from 'axios';
import { API_URL } from '../resources/Utilities';
import authService from './Auth.js';
const saveUser = async(payload) => {
    try{
        const response = await axios.post(`${API_URL}/user/save`, payload);
        return response.data.error;
    } catch(error) {
        return true;
    }
}

const getUsers = async() => {
    const token = authService.returnToken();
    const response = await axios.get(`${API_URL}/user/list`, {
        headers: {
            Authorization: `Bearer ${token}`
        }
    });
    return response.data;
}
export default {
    saveUser,
    getUsers,
}